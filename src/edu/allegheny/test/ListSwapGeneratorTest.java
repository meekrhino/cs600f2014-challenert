package edu.allegheny.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import au.com.bytecode.opencsv.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import edu.allegheny.listswap.*;

public class ListSwapGeneratorTest 
{
	private PrintStream original;
	
	@Before
	/*
	 * Record original System.out to preserve output from external calls to the test class
	 */
	public void recordSystemOutput()
	{
		original = System.out;
	}
	
	@After
	/*
	 * Reset System.out to standard output
	 */
	public void resetSystemOutput()
	{
		System.setOut( original );
	}
	
    @Test
    /*
     * This junit test will run the swapList method upon a list that contains Strings.
     */
	public void testString() 
    {
    	ArrayList<Object> testlist = new ArrayList<Object>();
        String t1 = "T1";
        String t2 = "T2";
        String t3 = "T3";
        String t4 = "T4";

        testlist.add(t1);
        testlist.add(t2);
        testlist.add(t3);
        testlist.add(t4);
	    String expected = new String("L1: \t[T2, T1, T3, T4]\nL2: \t[T3, T2, T1, T4]\nL3: \t[T4, T2, T3, T1]\nL4: \t" +
	    		"[T1, T3, T2, T4]\nL5: \t[T1, T4, T3, T2]\nL6: \t[T1, T2, T4, T3]");
	    String actual = ListSwapGenerator.listsToString(ListSwapGenerator.swapList(testlist));
	    assertEquals(expected, actual);
	}
    
    @Test
    /*
     * This junit test will run the swapList method upon a list that contains doubles.
     */
	public void testDouble() 
    {
    	ArrayList<Object> list = new ArrayList<Object>();
        double t1 = 1;
        double t2 = 2;
        double t3 = 3;
        double t4 = 4;

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
	    String expected = new String("L1: \t[2.0, 1.0, 3.0, 4.0]\nL2: \t[3.0, 2.0, 1.0, 4.0]\nL3: \t[4.0, 2.0, 3.0, 1.0]\nL4: \t" +
	    		"[1.0, 3.0, 2.0, 4.0]\nL5: \t[1.0, 4.0, 3.0, 2.0]\nL6: \t[1.0, 2.0, 4.0, 3.0]");
	    String actual = ListSwapGenerator.listsToString(ListSwapGenerator.swapList(list));
	    assertEquals(expected, actual);
	}
    
    @Test
    /*
     * This junit test will run the swapList method upon a list that contains ints.
     */
	public void testInt() 
    {
    	ArrayList<Object> list = new ArrayList<Object>();
        int t1 = 1;
        int t2 = 2;
        int t3 = 3;
        int t4 = 4;

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
	    String expected = new String("L1: \t[2, 1, 3, 4]\nL2: \t[3, 2, 1, 4]\nL3: \t[4, 2, 3, 1]\nL4: \t" +
	    		"[1, 3, 2, 4]\nL5: \t[1, 4, 3, 2]\nL6: \t[1, 2, 4, 3]");
	    String actual = ListSwapGenerator.listsToString(ListSwapGenerator.swapList(list));
	    assertEquals(expected, actual);
	}
    
    @Test
    /*
     * This junit test will run the swapList method upon a list that contains mixed objects.
     */
	public void testMixed() 
    {
    	ArrayList<Object> list = new ArrayList<Object>();
        int t1 = 1;
        String t2 = "2";
        double t3 = 3.0;
        int t4 = 4;

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
	    String expected = new String("L1: \t[2, 1, 3.0, 4]\nL2: \t[3.0, 2, 1, 4]\nL3: \t[4, 2, 3.0, 1]\nL4: \t" +
	    		"[1, 3.0, 2, 4]\nL5: \t[1, 4, 3.0, 2]\nL6: \t[1, 2, 4, 3.0]");
	    String actual = ListSwapGenerator.listsToString(ListSwapGenerator.swapList(list));
	    assertEquals(expected, actual);
	}
    
    @Test
    /*
     * This junit test will run the swapList method upon a list that is empty. 
     *  In this case no swaps will be made.
     */
	public void testListEmpty() 
    {
    	ArrayList<Object> list = new ArrayList<Object>();
        
	    String expected = new String("");
	    String actual = ListSwapGenerator.listsToString(ListSwapGenerator.swapList(list));
	    assertEquals(expected, actual);
	}

	@Test
	/*
	 * This junit test will run the swapList method upon an a list that is only of 1 element.
	 * Thus there should not be any swaps made. 
	 */
	public void testListSizeOne()
	{
		ArrayList<Object> list = new ArrayList<Object>(1);

		list.add(1);

		String expected = new String("");
		String actual = ListSwapGenerator.listsToString(ListSwapGenerator.swapList(list));
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * This junit test checks to ensure that the correct number of swap are being made when running the swapList
	 * method in the ListSwapGenerator class. 
	 */
	public void testNumberOfResults()
	{
		ArrayList<Object> list = new ArrayList<Object>();
		Random rand = new Random();
		int randInt;

		for (int listSize = 50; listSize <= 150; listSize += 25)
		{
			for (int i = 0; i < listSize; i++)
			{
				randInt = rand.nextInt();
				list.add(randInt);
			}

			int sum = 0;
			for (int i = 1; i <= listSize; i++)
				sum += (i-1);

			assertEquals(ListSwapGenerator.swapList(list).size(), sum);

			list.clear();
		}
	}

    @Test
    /*
     * This JUnit test tests the use of listSwap with ArrayLists of Objects of various types.
     */
    public void testDifferentObjects()
    {
        ArrayList<Object> list = new ArrayList<Object>(3);
        
        list.add(new Bear(150, 3, 'm'));
        list.add(new Bear(200, 6, 'f'));
        list.add(new Fish(1, 1, 'm'));
        String expected = new String("L1: \t[Bear: 200, 6, f, Bear: 150, 3, m, Fish: 1, 1, m]\nL2: \t" +
                "[Fish: 1, 1, m, Bear: 200, 6, f, Bear: 150, 3, m]\nL3: \t[Bear: 150, 3, m, Fish: 1, 1, m, Bear: 200, 6, f]");
        String actual = ListSwapGenerator.listsToString(ListSwapGenerator.swapList(list));
        assertEquals(expected, actual);
    }

	@Test
	/*
	 * This junit test will test the CSVListIO class such that it takes in a csv file and then runs the ListSwapGenerator 
	 * upon the imported list and then saves the swap(s) inside a new file.  Includes multiple calls to main(), using 
	 * different arguments to cover all branches
	 */
	public void testCSV()
	{
	    String expected = "";
	    String actual = "";

		String filename = "test/testcsv.csv";
		String filename2 = "test/swappedcsv.csv";
		String swapname = "swapped_lists_row0.csv";

		char delimiter = ',';

		try
		{
			// Build up the input file.
			FileWriter writer = new FileWriter(filename); 

			writer.append("\"1\"" + delimiter + "\"2\"" + delimiter + "\"3\"");

			// Write the input file.
			writer.flush();
			writer.close();

			// Build up the expected output file.
			FileWriter writer1 = new FileWriter(filename2); 

			writer1.append("\"2\"" + delimiter + "\"1\"" + delimiter + "\"3\"" + "\n");
			writer1.append("\"3\"" + delimiter + "\"2\"" + delimiter + "\"1\"" + "\n");
			writer1.append("\"1\"" + delimiter + "\"3\"" + delimiter + "\"2\"" + "\n");

			// Write the expected output file.
			writer1.flush();
			writer1.close();

			// Create the argument list for main().
			String[] args = new String[1];
			args[0] = filename;
			CSVListIO.main(args);

			// Read in the expected output file.
			CSVReader reader1 = new CSVReader(new FileReader(filename2), delimiter);

			// Read in the actual output file.
			CSVReader reader = new CSVReader(new FileReader(swapname), delimiter);

			// Convert the read-in files to lists of string arrays.
			List<String[]> e = reader.readAll();
			List<String[]> a = reader1.readAll();

			// Convert these lists of string arrays into a single string array.
			String[] expect = null;
			for (Object object : e) 
			{
				expect = (String[]) object;
				expected += "" + expect[0] + "," + expect[1] + "," + expect[2];

			}

			// Convert these lists of string arrays into a single string array.
			String[] act = null;
			for (Object object : a) 
			{
				act = (String[]) object;
				actual += "" + act[0] + "," + act[1] + "," + act[2];

			}

			// Assert that these two string arrays are equal, i.e.,
			// that the actual output of CSVListIO is as expected.
			assertEquals(expected, actual);


			// Test the main function with valid argument usage.
			args = new String[3];
			args[0] = "-d";
			args[1] = ",";
			args[2] = filename;
			CSVListIO.main(args);

			reader1 = new CSVReader(new FileReader(filename2), delimiter);

			reader = new CSVReader(new FileReader(swapname), delimiter);

			e = reader.readAll();
			a = reader1.readAll();

			expect = null;
			for (Object object : e) 
			{
				expect = (String[]) object;
				expected += "" + expect[0] + "," + expect[1] + "," + expect[2];

			}

			act = null;
			for (Object object : a) 
			{
				act = (String[]) object;
				actual += "" + act[0] + "," + act[1] + "," + act[2];

			}

			assertEquals(expected, actual);

			// Test the main function with invalid flag value.
			args = new String[3];
			args[0] = "-WRONG";
			args[1] = ",";
			args[2] = filename;

			// intercept system output
			ByteArrayOutputStream output = new ByteArrayOutputStream(100);
			System.setOut(new PrintStream(output));
        
			String expectedOut = "Invalid usage.\nExample usage: java CSVListIO [-d DELIMITER] FILENAME\r\n";
			CSVListIO.main(args);

			assertEquals(expectedOut, output.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Test that the IOException is handled properly when the user provides
	 * an invalid file path for the CSVListIO tool.
	 */
	public void testIOExceptionInReadListsFromFile()
    {
        // Intercept the console output.
        ByteArrayOutputStream output = new ByteArrayOutputStream(100);        
        System.setOut(new PrintStream(output));        

        CSVListIO.readListsFromFile("asdf.<", ',');
		assertEquals(output.toString(),"Failed to open file: asdf.<\r\n");
    }

    @Test
    /*
     * This test is used to test the main method of CSVListIO by inputting incorrect arguments.
     */
    public void testMainInvalidUsage() 
    {
        // Intercept console output
        ByteArrayOutputStream output = new ByteArrayOutputStream(100);
        System.setOut(new PrintStream(output));
        String[] args = {"10", "10"};
		CSVListIO.main(args);
        assertEquals(output.toString(), "Invalid usage.\nExample usage: java CSVListIO [-d DELIMITER] FILENAME\r\n");
    }
}
