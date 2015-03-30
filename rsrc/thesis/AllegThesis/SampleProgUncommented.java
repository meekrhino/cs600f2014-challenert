public class SampleProg
{
   private int dummyVar; // an instance variable

   public static void main(String[] args)
   {
      System.out.println("hello world.");
      // Avoid long lines in your program; split them up:
      dummyVar = Integer.parseInt("1234") 
           + 17 * ("abcde".substring(1,3).length() 
           + 11 - 1000;
      // Use small indents; don't use the tab key:
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
}
