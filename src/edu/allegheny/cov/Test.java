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
        if( args.length < 2 ) {
            System.out.println( "Please provide CodeCover container and case application name." );
            System.exit( 0 );
        }
        try {
            ir = ParseContainer.parse( "containers/" + args[ 0 ] );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

        CoverageReport cov = new CoverageReport( ir );
        cov.printAnalysis( args[ 1 ] );
	}
}
