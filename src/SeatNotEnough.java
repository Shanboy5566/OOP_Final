/** This is a program that can serve as exception handling for no enough seat.
* @version 1.0
* @since   2017-06-26 
*/
public class SeatNotEnough extends Exception {
	/**
	* This is a method used to handle exception. 
	* @param msg	input error message.
	*/
	public SeatNotEnough(String msg){
		super(msg);
	}
}
