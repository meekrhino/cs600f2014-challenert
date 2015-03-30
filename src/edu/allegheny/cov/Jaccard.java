package edu.allegheny.cov;

public class Jaccard implements REFunction {
	public double analyze( int aef, int aep, int anf, int anp ) {
		return ( (double) aef / (aef + anf + aep ) );
	}

	public String toString() {
		return "Jaccard";
	}
}
