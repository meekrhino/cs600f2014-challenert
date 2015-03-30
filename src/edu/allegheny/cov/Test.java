package edu.allegheny.cov;

/**
 * Main class.  Processes a CodeCover container, executing the
 * specified test class, and outputs to the provided file.
 * 
 * @author Tristan Challener
 *
 */
public class Test {
	
	/**
	 * Processes CodeCover container and output to specified file.
	 * 
	 * @param args Fully qualified test class name, fully qualified container
	 * path, and output file name ( output path is fixed ).
	 */
	public static void main( String[] args ) {
        ContainerIR ir = null;
        if( args.length < 3 ) {
            System.out.println( "Please specify test class, CodeCover container, and output file." );
            System.exit( 0 );
        }
        try {
            ir = ParseContainer.parse( args[ 1 ] );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

        CoverageReport cov = new CoverageReport( ir, args[ 0 ] );
        cov.printAnalysis( args[ 2 ] );
	}
}
