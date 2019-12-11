package pack;

import java.util.Arrays;

/**********************************************************************
 * 
 * IntegerArrayOperand class contains the definitions and methods
 * available to an array of integer values.
 * 
 **********************************************************************
 *
 * @author Vagner Machado - QC - Fall 2019
 * 
 **********************************************************************
 */
public class IntegerArrayOperand extends Data 
{
	private int [] listSize;
	private int [] data;

	/**
	 * constructor for array object
	 * @param x - the list size for the array
	 * @param y - the data to be store in the array
	 */
	public IntegerArrayOperand(int [] x, int [] y)
	{
		listSize = x;
		data = y;
	}

	@Override
	public Object getDataValue() 
	{
		return "int array of size list " + Arrays.toString(listSize);
	}

	/**
	 * getListSize - accessor method for the list size of array
	 * @return - listSize
	 */
	public int [] getListSize()
	{
		return listSize;
	}

	/**
	 * print - prints the list size and integers in the array.
	 * @return - a String with values of list size and data in the integer array. 
	 */
	public String print() 
	{
		return "int array of size list " + Arrays.toString(listSize);
	}

	/**
	 * get - accessor for a integer contained in the array.
	 * @param x - the index location for the desired object
	 * @return - the integer in the valid position of programs ends in error if index is invalid.
	 */
	public int get(int [] x)
	{

		int result = 0;
		if(x.length != listSize.length)
		{
			System.out.println("Conflicting list size and parameter length passed into IntegerArrayOperand 'get' method, while executing " + 
					VM.instructionArray[VM.programCounter].print());
			System.exit(-1);
		}

		int value;
		for (int i = 0; i < x.length; i++)
		{
			value = x[i];
			if(value < 0 || value >= listSize[i])
			{
				System.out.println("Array Out Of Bounds: " + value + " passed into IntegerArrayOperand 'get' method, while executing " + 
						VM.instructionArray[VM.programCounter].print() + ". See below:\n" + value + " is OutOfBounds for index " + i + 
						" in list " +
						Arrays.toString(listSize));
				System.exit(-1);
			}
			 result = result * listSize[i]  + value;
//			for(int j = i + 1; j < x.length; j++)
//				value *= (listSize[j]);
//			result += value;
		}

		return data[result];
	}
	
	/**
	 * set - setter method that adds a integer value to a position.
	 * @param val - the value to be stored
	 * @param params - the index in which to save the value.
	 */
	public void set(int val, int [] params)
	{
		int result = 0;
		if(params.length != listSize.length)
		{
			System.out.println("Conflicting list size and parameter length passed into IntegerArrayOperand 'get' method, while executing " + 
					VM.instructionArray[VM.programCounter].print());
			System.exit(-1);
		}

		int value;
		for (int i = 0; i < params.length; i++)
		{
			value = params[i];
			if(value < 0 || value >= listSize[i])
			{
				System.out.println("Array Out Of Bounds: " + value + " passed into IntegerArrayOperand 'set' method, while executing " + 
						VM.instructionArray[VM.programCounter].print() + ". See below:\n" + value + " is OutOfBounds for index " + i + 
						" in list " + Arrays.toString(listSize));
				System.exit(-1);
			}
			
			 result = result * listSize[i]  + value;
		}

		data[result] = val;
	}

	//  This is the original version for the array access with O(n^2) kept here for future reference.
//	/**
//	 * set - setter method that adds a integer value to a position.
//	 * @param val - the value to be stored
//	 * @param params - the index in which to save the value.
//	 */
//	public void set(int val, int [] params)
//	{
//		int result = 0;
//		if(params.length != listSize.length)
//		{
//			System.out.println("Conflicting list size and parameter length passed into IntegerArrayOperand 'get' method, while executing " + 
//					VM.instructionArray[VM.programCounter].print());
//			System.exit(-1);
//		}
//
//		int value;
//		for (int i = 0; i < params.length; i++)
//		{
//			value = params[i];
//			if(value < 0 || value >= listSize[i])
//			{
//				System.out.println("Array Out Of Bounds: " + value + " passed into IntegerArrayOperand 'set' method, while executing " + 
//						VM.instructionArray[VM.programCounter].print() + ". See below:\n" + value + " is OutOfBounds for index " + i + 
//						" in list " + Arrays.toString(listSize));
//				System.exit(-1);
//			}
//			for(int j = i + 1; j < params.length; j++)
//				value *= (listSize[j]);
//			result += value;
//		}
//
//		data[result] = val;
//	}

	
	//******************   BRUTE TEST  ***********************
	//	public static void main(String [] a)
	//	{
	//		System.out.println("mainnnnnn");
	//		int [] s = {2,3,4};
	//		int [] v = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
	//		int [] p = {1,2,1};
	//		IntegerArrayOperand x = new IntegerArrayOperand(s,v);
	//		for (int i = 0; i < s[0]; i++ )
	//			for (int j = 0; j < s[1]; j++ )
	//				for (int k = 0; k < s[2]; k++ )
	//				{
	//					IntegerArrayOperand x = new IntegerArrayOperand(s,v);
	//					
	//					System.out.println(x.get(p) + " locate in " + i +", " + j + ", " + k);
	//				}
	//		x.set(27, p);
	//		System.out.println(x.get(p));
	//	}
}


