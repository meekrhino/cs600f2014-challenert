package edu.allegheny.cov;

public class Kulczynski2 implements REFunction {
	public double analyze( int Iaef, int Iaep, int Ianf, int Ianp ) {
		double aef, aep, anf, anp;
		
		aef = (double) Iaef;
		aep = (double) Iaep;
		anf = (double) Ianf;
		anp = (double) Ianp;
		
		return 0.5d * ( (  aef / ( aef + anf ) ) + ( aef / ( aef + aep ) ) );
	}

	public String toString() {
		return "Kulczynski 2";
	}
}
