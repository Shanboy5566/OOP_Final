/** This is a program that can serve as exception handling for ticket that is not exist.
* @version 1.0
* @since   2017-06-26 
*/
public class TicketIsNotExist extends Exception {
	/**
	* This is a method used to handle exception. 
	* @param msg	input error message.
	*/
	public TicketIsNotExist(String msg){
		super(msg);
	}
}
