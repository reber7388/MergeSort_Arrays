import java.io.*;

public class MergeSort 
{
	public static void main (String[] args)
	{
		String str = "integers2"; //file name to write the output to
		int arrayA[] = new int [1000000]; //int array with x elements
		populateArray(arrayA); //call function to populate the array
		
		System.out.println("Before sorting: ");
		//printArray(arrayA);
		
		arrayA = mergeSort(arrayA); 
		
		System.out.println("\nAfter sorting: ");
		//printArray(arrayA);
		sendToFile(str, arrayA);
		
	}
	
	public static void sendToFile(String fileName, int[] arrayToOutput)
	{
		try 
		{
			BufferedWriter outputWriter = null;
			outputWriter = new BufferedWriter(new FileWriter(fileName));
			
			for(int x = 0; x < arrayToOutput.length; x++)
			{
				outputWriter.write(arrayToOutput[x] + " ");
				if(x%50 == 49)
				{
					outputWriter.newLine();
				}
			}
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static int[] mergeSort(int[] B)
	{
		if (B.length <= 1) //this is the base case
		{
			return B;
		}
		
		int midpoint = B.length / 2; //find a midpoint and divide it in half
		int[] leftSide = new int[midpoint];
		int[] rightSide;
		if (B.length %2 == 0)//this means its an even number
		{
			rightSide = new int[midpoint];
		}
		else //else its an odd number so make the right side one element larger
		{
			rightSide = new int[midpoint+1]; 
		}
		
		int[] result = new int[B.length]; //the final array that will hold the sorted elements
		
		for(int i = 0; i < midpoint; i++)
		{
			leftSide[i] = B[i];//traverse array B and add the elements to the leftSide array
		}
		
		int x = 0;
		for(int j = midpoint; j < B.length; j++)//start at the midpoint and populate the rightSide array till you reach the end
		{
			if(x < rightSide.length)//just a precaution to make sure x cannot be out of bounds
			{
				rightSide[x] = B[j];//rightSide array needs to start at the 0 index
				x++;
			}
		}
		
		leftSide = mergeSort(leftSide);
		rightSide = mergeSort(rightSide);
		
		result = merge(leftSide, rightSide);
		
		return result; 
	}
	
	public static int[] merge(int[] left, int[] right)
	{
		int lengthResult = left.length + right.length;
		int[] result = new int[lengthResult]; 
		int indexLeft = 0;
		int indexRight = 0;
		int indexResult = 0;
		
		while (indexLeft < left.length || indexRight < right.length)//while there are still elements to merge
		{
			if(indexLeft < left.length && indexRight < right.length)//if the left index and the right index are less than their respective lengths
			{
				if(left[indexLeft] <= right[indexRight])//if the left is smaller or equal to the right add the left element
				{
					result[indexResult] = left[indexLeft]; //adding left element to the result array
					indexLeft++;
					indexResult++;
				}
				else //else the right side is smaller so add it the the result array
				{
					result[indexResult] = right[indexRight];
					indexRight++;
					indexResult++;
				}
			}
			else if(indexLeft < left.length) //if there are no more elements on the right side, add the rest of the left elements to the result array
			{
				result[indexResult] = left[indexLeft];
				indexLeft++;
				indexResult++;
			}
			else if(indexRight < right.length)
			{
				result[indexResult] = right[indexRight];
				indexRight++;
				indexResult++;
			}
		}
		
		return result; 
		
	}
	
	
	public static void printArray(int[] B)
	{
		for (int i = 0; i  < B.length; i++)
		{
			System.out.print(B[i] + " ");
		}
	}
	
	
	public static int[] populateArray(int[] B)
	{
		for (int i = 0; i < B.length; i++)
		{
			B[i] = (int)(Math.random()*100000);//generate numbers from 0-x
		}
		return B;
	}
	
	
	
	
}
