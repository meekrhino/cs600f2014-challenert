package edu.allegheny.cov;

public class Kulczynski2 implements REFunction {
	public double analyze( int aef, int aep, int anf, int anp ) {
		return (double) 1/2 * ( ( (double) aef / ( aef + anf ) )
			              + ( (double) aep / ( aep + anp ) ) );
	}

	public String toString() {
		return "Kulczynski 2";
	}
}
