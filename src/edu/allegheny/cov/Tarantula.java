package edu.allegheny.cov;

public class Tarantula implements REFunction {
	public double analyze( int Iaef, int Iaep, int Ianf, int Ianp ) {
		double aef, aep, anf, anp;
		
		aef = (double) Iaef;
		aep = (double) Iaep;
		anf = (double) Ianf;
		anp = (double) Ianp;
		
		return ( aef / ( aef + anf ) )
		     / ( aef / ( aef + anf )
		       + aep / ( aep + anp ) );
	}

	public String toString() {
		return "Tarantula";
	}
}
