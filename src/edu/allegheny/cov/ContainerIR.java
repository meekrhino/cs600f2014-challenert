package edu.allegheny.cov;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Intermediate representation for a CodeCover container.
 * Processes a DOM basic representation of the XML container,
 * locating source file and coverage information necessary for
 * suspiciousness evaluation.
 * 
 * @author Tristan Challener
 *
 */
public class ContainerIR {
    private ArrayList< TestFile > files;
    private ArrayList< String > testCases;

    /**
     * Process DOM provided to locate all source file, basic statement,
     * and coverage information relevant for suspiciousness evaluation.
     * 
     * @param doc DOM root object for the XML container
     */
    public ContainerIR( Document doc ) {
        files = new ArrayList< TestFile >();
        testCases = new ArrayList< String >();

        // Build IR from DOM
        Node cont = doc.getFirstChild();  // This should be "TestSessionContainer" node

        // First find SrcFileList node
        Node srcList = null;
        for ( Node child = cont.getFirstChild(); child != null; child = child.getNextSibling() ) {
            if( child.getNodeName() == "SrcFileList" ) {
                srcList = child;
                break;
            }
        }
        if( srcList == null ) {
            System.out.println( "No source files found!" );
            return;
        }

        // Locate all source files and add to list
        String source = "EMPTY";
        String filename = "NOFILE";
        int id = 0;
        for( Node srcFile = srcList.getFirstChild(); srcFile != null; srcFile = srcFile.getNextSibling() ) {
            if( srcFile.getNodeName() == "SrcFile" ) {
                NamedNodeMap attributes = srcFile.getAttributes();
                source = attributes.getNamedItem( "Content" ).getNodeValue();
                filename = attributes.getNamedItem( "Filename" ).getNodeValue();
                id = Integer.parseInt( attributes.getNamedItem( "Intrnl_Id" ).getNodeValue() );
                files.add( new TestFile( source, filename, id ) );
            }
        }

        // Get hierarchy root
        Node root = null;
        for( Node child = cont.getFirstChild(); child != null; child = child.getNextSibling() ) {
            if( child.getNodeName() == "MASTRoot" ) {
                root = child;
                break;
            }
        }

        // Find statements
        findStatements( root );

        // Find coverage for all statements found
        checkCoverage( cont );
    }

    /**
     * Process DOM from root to locate all basic statements, adding
     * each identified statement to its source file in the IR.
     * 
     * @param root Root of DOM tree from which to begin searching.
     */
    private void findStatements( Node root ) {
        if( root == null ) {
            return;
        }

        // Find all basic statements
        for( Node stmt = root.getFirstChild(); stmt != null; stmt = stmt.getNextSibling() ) {
            if( stmt.getNodeName() == "BasicStmnt" ) {
                NamedNodeMap attributes = stmt.getAttributes();
                String id = attributes.getNamedItem( "CovItemId" ).getNodeValue();
                String sourceFileFullName = attributes.getNamedItem( "CovItemPrefix" ).getNodeValue();
                Node location = null;
                for( Node locList = stmt.getFirstChild(); locList != null; locList = locList.getNextSibling() ) {
                    if( locList.getNodeName() == "LocList" ) {
                        for( Node loc = locList.getFirstChild(); loc != null; loc = loc.getNextSibling() ) {
                            if( loc.getNodeName() == "Loc" ) {
                                location = loc;
                                break;
                            }
                        }
                        break;
                    }
                }
                NamedNodeMap locAttributes = location.getAttributes();
                int start = Integer.parseInt( locAttributes.getNamedItem( "StartOffset" ).getNodeValue() );
                int end = Integer.parseInt( locAttributes.getNamedItem( "EndOffset" ).getNodeValue() );
                int sourceId = Integer.parseInt( locAttributes.getNamedItem( "SrcFileId" ).getNodeValue() );
                TestFile file = findFile( sourceId );
                file.setFilename( sourceFileFullName );
                String source = file.getSourceOffset( start, end );
                TestStatement tStmt = new TestStatement( id, file, source );
                file.addStatement( tStmt );
            }
            else {
                findStatements( stmt );
            }
        }
    }

    /**
     * Process DOM from root to identify which statements
     * were executed by which test case.  Store test case
     * names in IR.
     * 
     * @param root Root of DOM tree from which to begin searching.
     */
    private void checkCoverage( Node root ) {
        Node testSession = null;
        for( Node child = root.getFirstChild(); child != null; child = child.getNextSibling() ) {
            if( child.getNodeName() == "TestSession" ) {
                testSession = child;
                break;
            }
        }

        int testCaseIndex = 0;
        for( Node testCase = testSession.getFirstChild(); testCase != null; testCase = testCase.getNextSibling() ) {
            if( testCase.getNodeName() == "TestCase" ) {
                NamedNodeMap attributes = testCase.getAttributes();
                testCases.add( attributes.getNamedItem( "Name" ).getNodeValue() );
                for( Node covList = testCase.getFirstChild(); covList != null; covList = covList.getNextSibling() ) {
                    if( covList.getNodeName() == "CovList" ) {
                        for( Node covPre = covList.getFirstChild(); covPre != null; covPre = covPre.getNextSibling() ) {
                            if( covPre.getNodeName() == "CovPrefix" ) {
                                NamedNodeMap covPreAttributes = covPre.getAttributes();
                                String filename = covPreAttributes.getNamedItem( "CovItemPrefix" ).getNodeValue();
                                TestFile file = findFile( filename );
                                for( Node cov = covPre.getFirstChild(); cov != null; cov = cov.getNextSibling() ) {
                                    if( cov.getNodeName() == "Cov" ) {
                                        NamedNodeMap covAttributes = cov.getAttributes();
                                        String id = covAttributes.getNamedItem( "CovItemId" ).getNodeValue();
                                        TestStatement ts = file.getStatement( id );
                                        if( ts != null ) {
                                            ts.markCovered( testCaseIndex );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                testCaseIndex++;
            }
        }

        // Normalize size of coverage lists by adding false to the end (must not be covered)
        for( TestFile tf : files ) {
            for( TestStatement ts : tf.stmts() ) {
                while( ts.numTestCases() < testCaseIndex ) {
                    ts.getCoverage().add( false );
                }
            }
        }
    }

    /**
     * Find TestFile in list of files with the provided Internal ID.
     * 
     * @param id ID to search for.
     * @return TestFile that matches requested ID.  Null if not found.
     */
    public TestFile findFile( int id ) {
        for( TestFile t : files ) {
            if( t.getId() == id ) {
                return t;
            }
        }
        return null;
    }

    /**
     * Find TestFile in list of files with the specified name.
     * 
     * @param file Name of file to search for.
     * @return TestFile that matches requested name.  Null if not found.
     */
    public TestFile findFile( String file ) {
        for( TestFile f : files ) {
            if( f.getFilename().equals( file ) ) {
                return f;
            }
        }
        return null;
    }

    /**
     * Number of files in the IR.
     * 
     * @return Number of files.
     */
    public int numFiles() {
        return files.size();
    }

    /**
     * Return file list.
     * 
     * @return List of files found.
     */
    public ArrayList< TestFile > files() {
        return files;
    }    

    /**
     * Simple string format of file list.  Format is:
     * 
     * 		Files: [ Filename1 ][ Filename2 ]...[ FilenameN ]
     */
    public String toString() {
        String ret = "Files: ";
        for( TestFile f : files ) {
            ret += "[ " + f.getFilename() + " ]";
        }
        return ret;
    }

    /**
     * Return test case list.
     * 
     * @return List of test cases.
     */
    public ArrayList< String > getTestCases() {
        return testCases;
    }
}

