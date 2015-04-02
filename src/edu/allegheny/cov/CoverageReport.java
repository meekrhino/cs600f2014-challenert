package edu.allegheny.cov;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Converts intermediate container representation into
 * a simplified data format for easy evaluation of 
 * suspiciousness.
 * 
 * @author Tristan Challener
 *
 */
public class CoverageReport {
	private ArrayList< Integer > aef;
	private ArrayList< Integer > aep;
	private ArrayList< Integer > anf;
	private ArrayList< Integer > anp;
    private ArrayList< TestStatement > stmts;
	public static final REFunction[] REFUNCTIONS =
		{ new Jaccard(), new Tarantula(), new Kulczynski2(),
		  new Ample(), new Ochiai() };
	
	/** 
	 * Construct a new CoverageReport from the provided intermediate
	 * container representation.
	 * 
	 * @param con Container IR object.
	 */
    public CoverageReport( ContainerIR con ) {
        ArrayList< String > testCaseList = con.getTestCases();
        ArrayList< Boolean > passFailList = con.getPassFail();

        stmts = new ArrayList< TestStatement >();
        for( TestFile file : con.files() ) {
            for( TestStatement stat : file.stmts() ) {
                stmts.add( stat );
            }
        }
        
        
        // Iterate through test cases and check coverage information
        ArrayList< TestInfo > l = new ArrayList< TestInfo >( testCaseList.size() );
        for( int i = 0; i < testCaseList.size(); i++ ) {
            ArrayList< Boolean > exec = new ArrayList< Boolean >();
            for( TestStatement stmt : stmts ) {
                exec.add( stmt.getCoverage().get( i ) );
            }

            boolean pass = passFailList.get( i );
            l.add( new TestInfo( pass, exec ) );
        }

        
        TestList info = new TestList( l );

        aef = new ArrayList< Integer >( info.size() );
		aep = new ArrayList< Integer >( info.size() );
		anf = new ArrayList< Integer >( info.size() );
		anp = new ArrayList< Integer >( info.size() );

		for( int i = 0; i < info.size(); i++ ) {
			aef.add( 0 );
			aep.add( 0 );
			anf.add( 0 );
			anp.add( 0 );
		}

		for( TestInfo test : info.list() ) {
			int i = 0;
			if( test.passed() ) {
				for( Boolean exec : test.statsExec() ) {
					if( exec ) {
						aep.set( i, aep.get( i ) + 1 );
					}
					else {
						anp.set( i, anp.get( i ) + 1 );
					}
					i++;
				}
			}
			else {
				for( Boolean exec : test.statsExec() ) {
					if( exec ) {
						aef.set( i, aef.get( i ) + 1 );
					}
					else {
						anf.set( i, anf.get( i ) + 1 );
					}
					i++;
				}
			}
		}
	}


    /**
	 * Return the number of tests that executed the
	 * statement with a specific index and failed.
	 * 
	 * @param idx Index of statement.
	 * @return Number of failing executing tests.
	 */
	public int aef( int idx ) {
		return aef.get( idx );
	}

	/**
	 * Return the number of tests that executed the
	 * statement with a specific index and passed.
	 * 
	 * @param idx Index of statement.
	 * @return Number of passing executing tests.
	 */
	public int aep( int idx ) {
		return aep.get( idx );
	}
	
	/**
	 * Return the number of tests that did not execute the
	 * statement with a specific index and failed.
	 * 
	 * @param idx Index of statement.
	 * @return Number of failing non-executing tests.
	 */
	public int anf( int idx ) {
		return anf.get( idx );
	}

	/**
	 * Return the number of tests that did not execute the
	 * statement with a specific index and passed.
	 * 
	 * @param idx Index of statement.
	 * @return Number of passing non-executing tests.
	 */
	public int anp( int idx ) {
		return anp.get( idx );
	}

	/**
	 * Print the coverage report to standard output.
	 */
	public void print() {
        String currentFile = "";
		System.out.printf("    A < aef  aep  anf  anp >\n");
		for( int i = 0; i < aef.size(); i++ ) {
            TestStatement stmt = stmts.get( i );
            if( !stmt.getFile().getFilename().equals( currentFile ) )
            {
                currentFile = stmt.getFile().getFilename();
                if( i > 0 ) {
                    System.out.printf( "      '--------------------'\n" );
                }
                System.out.printf( ">%s\n", currentFile );
                System.out.printf( "      .--------------------.\n" );
            }
			System.out.printf( "%5s |%4d %4d %4d %4d |\n", stmt.getId(), aef.get( i ), aep.get( i ), anf.get( i ), anp.get( i ) );
		}
        System.out.printf( "      '--------------------'\n" );
	}

	/** 
	 * Analyze the coverage report according to the provided risk evaluation
	 * function, optionally normalizing the results.
	 * 
	 * @param re Risk evaluation to evaluate with.
	 * @param norm Normalize results to zero to one scale if true.
	 * @return ResultsList containing the suspiciousness value of each statement.
	 */
	public ResultsList analyze( REFunction re, boolean norm ) {
		ResultsList ret = new ResultsList( aef.size(), re );
		for( int i = 0; i < aef.size(); i++ ) {
            TestStatement ts = stmts.get( i );
            ts.setSusp( re.analyze( aef.get( i ), aep.get( i ), anf.get( i ), anp.get( i ) ) );
			ret.add( ts );
		}
		if( norm )
		{
			ret.normalize();
		}
		return ret;
	}
	
	/**
	 * Perform analysis on the coverage report using each risk evaluation function,
	 * then write the results with the provided filename in CSV format.
	 * 
	 * @param caseApp Name of the file to be written.  File will be written in
	 * the results directory.  Also forms the CaseApplication attribute.
	 */
	public void printAnalysis( String caseApp ) {
		try {
			File file = new File("results");
			if (!file.exists()) {
				if (file.mkdir()) {
				}
			}
			CSVWriter writer = new CSVWriter( new FileWriter( "results/" + caseApp + ".csv" ) );
			ArrayList< TestStatement > spL;
			String[] row = new String[ 7 ];
			row[ 0 ] = "Function";
			row[ 1 ] = "StatementID";
			row[ 2 ] = "Filename";
			row[ 3 ] = "Suspiciousness";
			row[ 4 ] = "Rank";
			row[ 5 ] = "StatementCount";
			row[ 6 ] = "CaseApplication";
			writer.writeNext( row );
			for( REFunction re : REFUNCTIONS ) {
				spL = this.analyze( re, true ).sort().list();
				for( int i = 0; i < spL.size(); i++ ) {
					TestStatement ts = spL.get( i );
					row[ 0 ] = re.toString();
					row[ 1 ] = ts.getId();
					row[ 2 ] = ts.getFile().getFilename();
					row[ 3 ] = "" + ts.getSusp();
					row[ 4 ] = "" + ( i + 1 );
					row[ 5 ] = "" + spL.size();
					row[ 6 ] = caseApp;
					writer.writeNext( row );
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
