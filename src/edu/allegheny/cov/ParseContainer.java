package edu.allegheny.cov;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import org.w3c.dom.Document;

/**
 * Provides static functionality for parsing a CodeCover XML container
 * and returning intermediate representation for suspiciousness
 * processing.
 * 
 * @author Tristan Challener
 *
 */
public class ParseContainer {
    static final String outputEncoding = "UTF-8";

    /** 
     * Parse CodeCover XML container into an intermediate representation
     * with DOM XML parsing. 
     * 
     * @param filename Fully qualified path for XML container.
     * @return Intermediate representation object.
     * @throws Exception Throws an exception when any problem occurs in
     * parsing, including format errors.  Most likely indicates that either
     * the filename was not specified correctly or the file specified was not
     * a CodeCover XML container.
     */
    public static ContainerIR parse( String filename ) throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments( true );
        dbf.setIgnoringElementContentWhitespace( true );
        DocumentBuilder db = dbf.newDocumentBuilder();
        OutputStreamWriter errorWriter = new OutputStreamWriter( System.err, outputEncoding );
        db.setErrorHandler( new MyErrorHandler( new PrintWriter( errorWriter, true ) ) );
        Document doc = db.parse(new File(filename));
        ContainerIR ir = new ContainerIR( doc );
        return ir;
    }

    /**
     * Copyright (c) 2014, Oracle America, Inc.
	 * All rights reserved.
     * 
     * @author Oracle DOM online tutorial.  This tutorial can be found
     * at http://docs.oracle.com/javase/tutorial/jaxp/dom/readingXML.html.
     * 
     * This simple error handler prints relevant information about problems
     * that occur while parsing an XML document.
     */
    private static class MyErrorHandler implements ErrorHandler {

        private PrintWriter out;

        MyErrorHandler(PrintWriter out) {
            this.out = out;
        }

        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
            }

            String info = "URI=" + systemId + " Line=" + spe.getLineNumber() +
                ": " + spe.getMessage();
            return info;
        }

        public void warning(SAXParseException spe) throws SAXException {
            out.println("Warning: " + getParseExceptionInfo(spe));
        }

        public void error(SAXParseException spe) throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        public void fatalError(SAXParseException spe) throws SAXException {
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    }
}
