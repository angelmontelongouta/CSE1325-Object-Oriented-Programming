import java.util.Scanner; 
import java.util.Arrays;

public class Roll 
{
    public static void main(String[] args) 
	{
		
		int rolls = Integer.parseInt(args[0]);
		int numFaces = Integer.parseInt(args[1]);
		
		int[] rollsArray;
		rollsArray = new int[rolls];
		int sum = 0; 
		
		for(int j = 0; j < rolls; j++)
		{
			double numRolled = (Math.random()*numFaces);
			int numRolledInt = (int)numRolled+1;
			rollsArray[j] = numRolledInt;
			
			sum = sum + rollsArray[j];
		}
		
		Arrays.sort(rollsArray);
		
		for(int i = 0; i < rolls; i++)
		{
			System.out.print(rollsArray[i]+" ");
		}
		System.out.println();
		
		System.out.println("Sum = " +sum);
		double average = (double)sum/rolls;
		System.out.println("Average = " +average);
    }
}
