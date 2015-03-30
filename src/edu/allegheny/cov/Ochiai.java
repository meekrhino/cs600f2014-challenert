package edu.allegheny.cov;

public class Ochiai implements REFunction {
	public double analyze( int aef, int aep, int anf, int anp ) {
		return ( (double) aef / Math.sqrt( ( aef + anf ) + ( anp + aep ) ) );
	}

	public String toString() {
		return "Ochiai";
	}
}
