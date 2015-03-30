package edu.allegheny.listswap;

import au.com.bytecode.opencsv.*;
import edu.allegheny.listswap.ListSwapGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVListIO
{
    public static void main(String[] args)  
	{
		if (args.length == 1)
		{
		    writeListsToFiles(readListsFromFile(args[0], ','), ',');
		}
        else if (args.length == 3 && args[0].equals("-d"))
        {
		    writeListsToFiles(readListsFromFile(args[2], args[1].charAt(0)), args[1].charAt(0));
        }
        else
        {
            System.out.println("Invalid usage.\nExample usage: java CSVListIO [-d DELIMITER] FILENAME");
        }
	}	

    /**
     * Given a file path and delimiter, returns a list of string arrays representing the input csv,
     * where each array is one line from the csv and each individual string is a single element
     * @param filePath The relative path to the input csv file
     * @param delimiter The delimiter to be used to parse the input csv file
     */
	public static List<String[]> readListsFromFile(String filePath, char delimiter)  
    {
    	try
    	{
			CSVReader reader = new CSVReader(new FileReader(filePath), delimiter);

			return reader.readAll();
		}
		catch (IOException e)
		{
			System.out.println("Failed to open file: " + filePath);
		}

		return null;
    }

    /**
     * Takes a list of arrays of strings and outputs one csv file for each array which includes
     * all of the possible swaps for that array.
     * @param lists List of string arrays, where each array is one row in the input csv
     * @param delimiter The delimiter to use for the output csv files
     */
    public static void writeListsToFiles(List<String[]> lists, char delimiter)  
    {
        int listNum = 0;
		for (String[] listItems : lists)
		{
            try
			{
				ArrayList<Object> entries = new ArrayList<Object>(listItems.length);

				entries.addAll(Arrays.asList(listItems));

				ArrayList<ArrayList<Object>> results = ListSwapGenerator.swapList(entries);

				CSVWriter writer = new CSVWriter(new FileWriter("swapped_lists_row" + listNum + ".csv"), delimiter);

				for (ArrayList<Object> list : results)
				{
					String[] strings = new String[list.size()];

					for (int i = 0; i < list.size(); i++)
						strings[i] = list.get(i).toString();

					writer.writeNext(strings);
				}

				writer.close();

				listNum++;
			}
			catch (IOException e)
			{
			    System.out.println("Failed writing to file: swapped_lists_row" + listNum + ".csv");

				System.exit(1);
			}
        }
    }
}
