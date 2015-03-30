package edu.allegheny.cov;

import java.util.ArrayList;

/**
 * Data representation of a file.  Includes source code,
 * file name, list of statements in the file, and an ID.
 * 
 * @author Tristan Challener
 *
 */
public class TestFile {
    private String source;
    private String filename;
    private ArrayList< TestStatement > statementList;
    private int id;

    /**
     * Create a new instance with specified source code, filename,
     * and ID.
     * 
     * @param s
     * @param f
     * @param i
     */
    public TestFile( String s, String f, int i ) {
        source = s;
        filename = f;
        id = i;
        statementList = new ArrayList< TestStatement >();
    }

    /**
     * Getter for source code.
     * 
     * @return Source code.
     */
    public String getSource() {
        return source;
    }

    /**
     * Getter for file name.
     * 
     * @return File name.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Setter for file name.
     * 
     * @param f File name.
     */
   public void setFilename( String f )  {
        filename = f;
    }

   /**
    * Getter for ID.
    * 
    * @return ID.
    */
   public int getId()  {
        return id;
    }

   /**
    * Add the specified statement to the file.
    *
    * @param tStmt TestStatement to add.
    */
   public void addStatement( TestStatement tStmt ) {
        statementList.add( tStmt );
    }

   /**
    * Retrieve the segment of the source that begins and
    * ends at the specified character offsets.
    * 
    * @param start Start index.
    * @param end End index.
    * @return String containing the specified segment of
    * the file source code.
    */
    public String getSourceOffset( int start, int end ) {
        return source.substring( start, end + 1 );
    }

    /**
     * Getter for the list of statements in the file.
     * 
     * @return Statement list.
     */
    public ArrayList< TestStatement > stmts() {
        return statementList;
    }

    /**
     * Retrieve the statement in this file with the given 
     * statement ID.
     * 
     * @param id ID to search for.
     * @return Statement that matches specified ID, or null if
     * the ID was not found.
     */
    public TestStatement getStatement( String id ) {
        for( TestStatement ts : statementList ) {
            if( ts.getId().equals( id ) ) {
                return ts;
            }
        }
        return null;
    }
}
