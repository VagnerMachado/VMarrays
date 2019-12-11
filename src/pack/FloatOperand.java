package pack;

public class FloatOperand extends Data
{
	private double value;

	public FloatOperand( double v)
	{
		value = v;
	}
	public String print()
	{	
		return String.valueOf(value);
	}

	public Object getDataValue() 
	{
		return value;
	}
}
