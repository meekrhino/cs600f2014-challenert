package edu.allegheny.cov;

public class Ochiai implements REFunction {
	public double analyze( int Iaef, int Iaep, int Ianf, int Ianp ) {
		double aef, aep, anf, anp;
		
		aef = (double) Iaef;
		aep = (double) Iaep;
		anf = (double) Ianf;
		anp = (double) Ianp;
		
		return ( aef / Math.sqrt( ( aef + anf ) * ( aef + aep ) ) );
	}

	public String toString() {
		return "Ochiai";
	}
}
