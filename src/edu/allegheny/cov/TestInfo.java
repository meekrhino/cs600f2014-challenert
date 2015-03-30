package edu.allegheny.cov;

import java.util.ArrayList;

/**
 * Data representation of a single test case.  Stores pass/fail information,
 * as well as listing whether each statement was executed by this test.
 * 
 * @author Tristan
 *
 */
public class TestInfo {
	private boolean pass;
	private ArrayList< Boolean > exec;

	/**
	 * Create an instance with the specified pass or fail status, as well
	 * as a list describing which statements were executed by this test.
	 * 
	 * @param p Pass/fail status.
	 * @param e List of statement execution status.
	 */
	public TestInfo( boolean p, ArrayList< Boolean > e ) {
		pass = p;
		exec = new ArrayList< Boolean >( e );
	}

	/**
	 * Getter for pass/fail status.
	 * 
	 * @return True if the test passed, false otherwise.
	 */
	public boolean passed() {
		return pass;
	}

	/**
	 * Getter for statement execution status list.
	 * 
	 * @return Statement execution status list.
	 */
	public ArrayList< Boolean > statsExec() {
		return new ArrayList< Boolean >( exec ); 
	}
}
