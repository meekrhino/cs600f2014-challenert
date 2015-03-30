package edu.allegheny.cov;

import java.util.ArrayList;

/**
 * Helper list class with functionality for storing a different
 * size value, which represents the number of statements per test
 * rather than the number of tests.
 * 
 * @author Tristan Challener
 *
 */
public class TestList {
	private ArrayList< TestInfo > list;
	private int stats;

	/**
	 * Create an instance, copying the specified list values as the initial
	 * values for the TestList
	 * 
	 * @param l List to copy from
	 */
	public TestList( ArrayList< TestInfo> l ) {
		list = new ArrayList< TestInfo >( l.size() );
		list.addAll( l );
		if( l.size() > 0 ) {
			stats = l.get( 0 ).statsExec().size();
		}
	}

	/**
	 * Add the specified test to the list.
	 * 
	 * @param t Test to add.
	 */
	public void addTest( TestInfo t ) {
		list.add( t );
	}

	/**
	 * Getter for size.
	 * 
	 * @return Number of statements per test.
	 */
	public int size() {
		return stats;
	}

	/**
	 * Getter for internal list of tests.
	 * 
	 * @return Internal list of tests.
	 */
	public ArrayList< TestInfo > list() {
		return list;
	}

	/**
	 * Retrieve the specified element in the list.
	 * 
	 * @param idx Index of element to return.
	 * @return Specified element of the list.
	 */
	public TestInfo get( int idx ) {
		return list.get( idx );
	}
}
