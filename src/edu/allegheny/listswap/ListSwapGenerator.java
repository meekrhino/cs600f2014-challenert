package edu.allegheny.listswap;

import java.util.ArrayList;

public class ListSwapGenerator
{
	/**
	 * This method takes a list of objects and returns all lists that can be obtained
	 * by swapping two items in the list.
	 *
	 * @param list The list to generate additional lists from.
	 * @return A list of all lists obtained by swapping two element in the input list.
	 */
    public static ArrayList<ArrayList<Object>> swapList(ArrayList<Object> list)
    {
        ArrayList<Object> temp;
        ArrayList<ArrayList<Object>> newList = new ArrayList<ArrayList<Object>>();
        for(int i = 0; i < list.size(); i++)
        {
            for(int j = i + 1; j < list.size(); j++)
            {
                temp = new ArrayList<Object>(list.size());
                temp.addAll(list);
                temp.set(i, list.get(j));
                temp.set(j, list.get(i));
				newList.add(temp);
            }
        }
        return newList;
    }

	/**
	 * Prints a list of lists in the format provided in the Requirements Document.
	 *
	 * For example, the result of running swapList() with L = {1, 2, 3} would
	 * result in the string:
	 * 	L1 = {2, 1, 3}
	 * 	L2 = {3, 2, 1}
	 * 	L3 = {1, 3, 2}
	 *
	 * @param list A list of lists to print in the specified format.
	 * @return A string description of the lists.
	 */
    public static String listsToString(ArrayList<ArrayList<Object>> list)
    {
        String ret = "";
        int listSize = list.size();

        for(int i = 0; i < listSize; i++)
		{
            ret += "L" + (i + 1) + ": \t" + list.get(i);
            if (i <= listSize-1)
            	ret += "\n";
		}

        return ret;
    }
}
