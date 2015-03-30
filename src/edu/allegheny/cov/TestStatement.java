package edu.allegheny.cov;

import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * Data representation of a statement.  Includes ID, source code,
 * source file, suspiciousness value, and a list indicating which
 * tests executed or did not execute the statement.
 * 
 * @author Tristan Challener
 *
 */
public class TestStatement implements Comparable< TestStatement > {
    private String id;
    private String source;
    private TestFile file;
    private double susp;
    private ArrayList< Boolean > testsExecuting;

    /**
     * Create a new instance with specified ID, source file, and source code.
     * Suspiciousness is initialized to zero, and no tests are listed.
     * 
     * @param i ID.
     * @param f Source file.
     * @param s Source code.
     */
    public TestStatement( String i, TestFile f, String s ) {
        id = i;
        file = f;
        source = s;
        susp = 0;
        testsExecuting = new ArrayList< Boolean >();
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
     * Getter for source file.
     * 
     * @return Source file.
     */
    public TestFile getFile() {
        return file;
    }

    /**
     * Getter for ID.
     * 
     * @return ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for suspiciousness.
     * 
     * @param sp Suspiciousness
     */
    public void setSusp( double sp ) {
        susp = sp;
    }

    /**
     * Getter for suspiciousness
     * 
     * @return Suspiciousness.
     */
    public double getSusp( ) {
        return susp;
    }

    /**
     * Getter for executed test list.
     * 
     * @return Boolean list indicating, for each test case, whether
     * this statement was executed.
     */
    public ArrayList< Boolean > getCoverage() {
        return testsExecuting;
    }

    /**
     * Mark the indicated test as covering this statement. Fill in gaps
     * in coverage list with false (if a test is not marked covering,
     * it is assumed to not cover the statement).
     * 
     * @param idx Index of test to mark as covering.
     */
    public void markCovered( int idx ) {
        while( testsExecuting.size() < idx ) {
            testsExecuting.add( false );
        }
        testsExecuting.add( true );
    }

    /**
     * Returns the total number of tests consider for coverage on this
     * statement.
     * 
     * @return Size of executing test list.
     */
    public int numTestCases() {
        return testsExecuting.size();
    }

    /**
     * Format suspiciousness to a minimum of one decimal place
     * and a maximum of 4 decimal places, with a leading zero,
     * and return the string representation.
     * 
     * @return Formatted string representation of suspiciousness value.
     */
    public String suspToString() {
        DecimalFormat fmt = new DecimalFormat( "0.0###" );
        return "(" + id + ", " + fmt.format( susp ) + ")";
    }

    /**
     * {@link Comparable} override method.  Smaller suspiciousness
     * values are considered higher for comparison (so, for example,
     * a sorted list of TestStatements from lowest to highest will 
     * actually be order in descending order of suspiciousness).  
     */
    public int compareTo( TestStatement st ) {
		Double thisSp = new Double( susp );
		Double thatSp = new Double( st.getSusp() );
		return thatSp.compareTo( thisSp );
	}
}
