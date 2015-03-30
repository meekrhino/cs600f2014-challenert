package edu.allegheny.cov;

/**
 * Risk evaluation function interface for suspiciousness evaluation.
 * 
 * @author Tristan Challener
 *
 */
public interface REFunction {
	/**
	 * Determine the suspiciousness of a statement according to
	 * the specific function.  Takes as input the number of
	 * passing and failing tests that executed and did not execute
	 * the statement.
	 * 
	 * @param aef Number of tests that executed the statement and failed.
	 * @param aep Number of tests that executed the statement and passed.
	 * @param anf Number of tests that did not execute the statement and failed.
	 * @param anp Number of tests that did not execute the statement and passed.
	 * @return Suspiciousness value.
	 */
	public double analyze( int aef, int aep, int anf, int anp );
	
	/**
	 * 
	 * @return Name of the REFunction.
	 */
	public String toString();
}
