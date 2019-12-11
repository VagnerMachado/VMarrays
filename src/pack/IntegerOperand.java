package pack;

public class IntegerOperand extends Data
{
	private int value;

	public IntegerOperand(int v)
	{
		value = v;
	}
	public String print() {
		
		return String.valueOf(value);
	}

	public Object getDataValue() 
	{
		return value;
	}
}
