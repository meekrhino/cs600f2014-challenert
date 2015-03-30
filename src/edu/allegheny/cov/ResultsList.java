package edu.allegheny.cov;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Helper list class with functionality for normalizing and sorting
 * to simplify suspiciousness evaluation.
 * 
 * @author Tristan Challener
 *
 */
public class ResultsList {
	ArrayList< TestStatement > list;
	REFunction re;

	/**
	 * General constructor, sets size of list and risk evaluation function.
	 * 
	 * @param size Initial list size.
	 * @param re Risk evaluation function for use in analysis.
	 */
	public ResultsList( int size, REFunction re ) {
		list = new ArrayList< TestStatement >( size );
		this.re = re;
	}

	/**
	 * Add TestStatement to list.
	 * 
	 * @param ts TestStatement to add.
	 */
	public void add( TestStatement ts ) {
		list.add( ts );
	}

	/**
	 * Retrieve suspiciousness of selected index in list.
	 * 
	 * @param idx Index to retrieve.
	 * @return Suspiciousness value of TestStatement at index.
	 */
	public double get( int idx ) {
		return list.get( idx ).getSusp();
	}

	/**
	 * Return the internal list.
	 * 
	 * @return Internal list of TestStatements
	 */
	public ArrayList< TestStatement > list() {
		return list;
	}
	
	/**
	 * Normalize suspiciousness values of the statement list
	 * between zero and one. 
	 */
	public void normalize() {
		double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
		
		for( TestStatement temp : list ) {
			if( temp.getSusp() > max ) { 
				max = temp.getSusp();
			}
			if( temp.getSusp() < min ) {
				min = temp.getSusp();
			}
		}
		
		for( int i = 0; i < list.size(); i++ ) {
			list.get( i ).setSusp( ( list.get( i ).getSusp() - min ) / ( max - min ) );
		}
	}

	/**
	 * Return a string representation of the suspiciousness values
	 * in the list. Format is:
	 * 
	 *		REFunciton { Susp string 1, Susp string 2, ..., Susp string n }
	 */
	public String toString() {
		String ret = re.toString() + " { ";
		for( int i = 0; i < list.size() - 1; i++ ) {
			ret += list.get( i ).suspToString() + ", ";
		}
		ret += list.get( list.size() - 1 ).suspToString() + " }";
		return ret;
	}

	/**
	 * Sort the TestStatments in the list according to suspiciousness
	 * value, with the first elements in the list having the highest 
	 * suspiciousness value.  Return the instance.
	 * 
	 * @return This instance, after sorting.
	 */
	public ResultsList sort() {
		TestStatement[] listTemp = new TestStatement[ list.size() ];
		for( int i = 0; i < list.size(); i++ ) {
			listTemp[ i ] = list.get( i );
		}
		Arrays.sort( listTemp );
		list = new ArrayList< TestStatement >( listTemp.length );
		for( int i = 0; i < listTemp.length; i++ ) {
			list.add( listTemp[ i ] );
		}
		return this;
	}
}
