package pack;

import java.util.Arrays;

/***********************************************************************
 * 
 * FloatArrayOperand class contains the definitions and methods
 * available to an array of double values.
 * 
 ***********************************************************************
 *
 * @author Vagner Machado - QC - Fall 2019
 * 
 ***********************************************************************
 *
 */
public class FloatArrayOperand extends Data 
{
	private int [] listSize;
	private double [] data;

	/**
	 * constructor for array object
	 * @param x - the list size for the array
	 * @param y - the data to be store in the array
	 */
	public FloatArrayOperand(int [] x, double [] y)
	{
		listSize = x;
		data = y;
	}

	@Override
	public Object getDataValue() {
		return "float array of size list " + Arrays.toString(listSize);
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
	 * print - prints the list size and array double values.
	 * @return - a String with values of list size and data in the double array. 
	 */
	public String print() 
	{
		return "float array of size list " + Arrays.toString(listSize);
	}

	/**
	 * get - accessor for a double contained in the array.
	 * @param x - the index location for the desired object
	 * @return - the integer in the valid position of programs ends in error if index is invalid.
	 */
	public double get(int [] x)
	{

		int result = 0;
		if(x.length != listSize.length)
		{
			System.out.println("\nConflicting list size and parameter length passed into FloatArrayOperand 'get' method, while executing " +
					VM.instructionArray[VM.programCounter].print());
			System.exit(-1);
		}

		int value;
		for (int i = 0; i < x.length; i++)
		{
			value = x[i];
			if(value < 0 || value >= listSize[i])
			{
				System.out.println("Array Out Of Bounds: " + value + " passed into FloatArrayOperand 'get' method, while executing " + 
						VM.instructionArray[VM.programCounter].print() + ". See below:\n" + value + " is OutOfBounds for index " + i + 
						" in list " + Arrays.toString(listSize));
				System.exit(-1);
			}
			 result = result * listSize[i]  + value;
		}

		return data[result];
	}

	/**
	 * set - setter method that adds a double value to a position.
	 * @param val - the value to be stored
	 * @param params - the index in which to save the value.
	 */
	public void set(double val, int [] params)
	{
		int result = 0;
		if(params.length != listSize.length)
		{
			System.out.println("\nConflicting list size and parameter length passed into FloatArrayOperand 'set' method, while executing " + 
					VM.instructionArray[VM.programCounter].print());
			System.exit(-1);
		}

		int value;
		for (int i = 0; i < params.length; i++)
		{
			value = params[i];
			if(value < 0 || value >= listSize[i])
			{
				System.out.println("Array Out Of Bounds: " + value + " passed into FloatArrayOperand 'set' method, while executing " + 
						VM.instructionArray[VM.programCounter].print() + ". See below:\n" + value + " is OutOfBounds for index " + i + 
						" in list " + Arrays.toString(listSize));
				System.exit(-1);
			}
			 result = result * listSize[i]  + value;
		}

		data[result] = val;
	}

}


