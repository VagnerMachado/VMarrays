package pack;

/************************************************************************************
 *
 * Data class that will be extended by FloatOperator, IntegerOperator,
 * IntegerArrayOperand and FloatArrayOperand. These are the four data 
 * types to be supported by the constructed virtual machine.
 * 
 * @author Vagner Machado
 * 
 *************************************************************************************
 */
public abstract class Data
{
	/**
	 * getDataValue - the accessor method for the value of the data contained in Data object.
	 * @return - the value of the data stored by the Data object.
	 */
	abstract Object getDataValue();
}
