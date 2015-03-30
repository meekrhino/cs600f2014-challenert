/**
 * SampleProg -- a class demonstrating something
 * having to do with this senior thesis.
 *
 * @author   A. Student
 * @version  3 (10 December 2013)
 * 
 * Some portions of code adapted from Alan Turing's Tetris program;
 * relevant portions are commented.
 *
 * Revision history:
 *     10 December 2013 -- added ultra-widget functionality
 *     18 November 2013 -- added code to import image files
 */
public class SampleProg
{
   private int dummyVar; // an instance variable

   public static void main(String[] args)
   {
      //=================================================
      // This section of code adapted from Alan Turing's
      // Tetris code. Used with permission.
      // http://turinggames.com
      //=================================================
      System.out.println("hello world.");

      dummyVar = Integer.parseInt("1234") 
           + 17 * ("abcde".substring(1,3).length() 
           + 11 - 1000;

      for (int i = 0; i < 10; i++)
      {
         for (int j = 0; j < 10; j++)
         {
            if (i > j)
            {
               System.out.println(i);
            }
         }
      }
   }

   /**
    * foo -- returns the square root of x, iterated n times
    *
    * @param    x   the value to be interatively processed
    * @param    n   number of times to iterate square root
    * @return   sqrt(sqrt(...())) [n times]
    */
   public double foo(double x, int n)
   {
       for (int i = 0; i < n; i++)
           x = Math.sqrt(x);
       return x;
   }
}
