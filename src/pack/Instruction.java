package pack;

/**
 * Instruction - abstract class used to derive all the instructions 
 * supported by the virtual machine.
 *
 ***************************************************************************************************
 *                      @author Vagner Machado - QC ID 23651127 - Fall 2019
 ***************************************************************************************************
 *
 */
public abstract class Instruction
{
	/**
	 * print - used to display the instruction data
	 * @return - a string with function data
	 */
	public abstract String print();	
	
	/**
	 * getValue - accessor for value stored in Instruction object
	 * @return - the value stored in object.
	 */
	public abstract Object getValue();
	
	/**
	 * defines the execution for each instruction
	 */	
	public abstract void execute();
}
