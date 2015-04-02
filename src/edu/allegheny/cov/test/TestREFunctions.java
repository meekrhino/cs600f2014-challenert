package edu.allegheny.cov.test;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.allegheny.cov.Ample;
import edu.allegheny.cov.Kulczynski2;
import edu.allegheny.cov.Ochiai;
import edu.allegheny.cov.REFunction;
import edu.allegheny.cov.Jaccard;
import edu.allegheny.cov.Tarantula;

public class TestREFunctions {
	static final int[] AEP = { 5, 1, 1, 1 };
	static final int[] AEF = { 1, 5, 1, 1 };
	static final int[] ANP = { 1, 1, 5, 1 };
	static final int[] ANF = { 1, 1, 1, 5 };
	
	@Test
	public void TestJaccard() {
		REFunction re = new Jaccard();
		double analysis = re.analyze( AEF[ 0 ], AEP[ 0 ], ANF[ 0 ], ANP[ 0 ] );
		assertEquals( 0.1429d, analysis, 0.0001d );
		
		analysis = re.analyze( AEF[ 1 ], AEP[ 1 ], ANF[ 1 ], ANP[ 1 ] );
		assertEquals( 0.7143d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 2 ], AEP[ 2 ], ANF[ 2 ], ANP[ 2 ] );
		assertEquals( 0.3333d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 3 ], AEP[ 3 ], ANF[ 3 ], ANP[ 3 ] );
		assertEquals( 0.1429d, analysis, 0.0001d );
		
		assertEquals( re.toString(), "Jaccard" );
	}
	
	@Test
	public void TestTarantula() {
		REFunction re = new Tarantula();
		double analysis = re.analyze( AEF[ 0 ], AEP[ 0 ], ANF[ 0 ], ANP[ 0 ] );
		assertEquals( 0.375d, analysis, 0.0001d );
		
		analysis = re.analyze( AEF[ 1 ], AEP[ 1 ], ANF[ 1 ], ANP[ 1 ] );
		assertEquals( 0.625d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 2 ], AEP[ 2 ], ANF[ 2 ], ANP[ 2 ] );
		assertEquals( 0.75d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 3 ], AEP[ 3 ], ANF[ 3 ], ANP[ 3 ] );
		assertEquals( 0.25d, analysis, 0.0001d );
		
		assertEquals( re.toString(), "Tarantula" );
	}
	
	@Test
	public void TestKulczynski2() {
		REFunction re = new Kulczynski2();
		double analysis = re.analyze( AEF[ 0 ], AEP[ 0 ], ANF[ 0 ], ANP[ 0 ] );
		assertEquals( 0.3333, analysis, 0.0001d );
		
		analysis = re.analyze( AEF[ 1 ], AEP[ 1 ], ANF[ 1 ], ANP[ 1 ] );
		assertEquals( 0.8333d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 2 ], AEP[ 2 ], ANF[ 2 ], ANP[ 2 ] );
		assertEquals( 0.5d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 3 ], AEP[ 3 ], ANF[ 3 ], ANP[ 3 ] );
		assertEquals( 0.3333d, analysis, 0.0001d );
		
		assertEquals( re.toString(), "Kulczynski 2" );
	}
	
	@Test
	public void TestOchiai() {
		REFunction re = new Ochiai();
		double analysis = re.analyze( AEF[ 0 ], AEP[ 0 ], ANF[ 0 ], ANP[ 0 ] );
		assertEquals( 0.2887, analysis, 0.0001d );
		
		analysis = re.analyze( AEF[ 1 ], AEP[ 1 ], ANF[ 1 ], ANP[ 1 ] );
		assertEquals( 0.8333d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 2 ], AEP[ 2 ], ANF[ 2 ], ANP[ 2 ] );
		assertEquals( 0.5d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 3 ], AEP[ 3 ], ANF[ 3 ], ANP[ 3 ] );
		assertEquals( 0.2887d, analysis, 0.0001d );
		
		assertEquals( re.toString(), "Ochiai" );
	}
	
	@Test
	public void TestAmple() {
		REFunction re = new Ample();
		double analysis = re.analyze( AEF[ 0 ], AEP[ 0 ], ANF[ 0 ], ANP[ 0 ] );
		assertEquals( -0.3333, analysis, 0.0001d );
		
		analysis = re.analyze( AEF[ 1 ], AEP[ 1 ], ANF[ 1 ], ANP[ 1 ] );
		assertEquals( 0.3333d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 2 ], AEP[ 2 ], ANF[ 2 ], ANP[ 2 ] );
		assertEquals( 0.3333d, analysis, 0.0001d );

		analysis = re.analyze( AEF[ 3 ], AEP[ 3 ], ANF[ 3 ], ANP[ 3 ] );
		assertEquals( -0.3333d, analysis, 0.0001d );
		
		assertEquals( re.toString(), "Ample" );
	}
}
