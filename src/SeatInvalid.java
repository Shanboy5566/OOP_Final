/** This is a program that can check if seat is valid.
* @version 1.0
* @since   2017-06-26 
*/
public class SeatInvalid extends Exception {
	/**
	* This is a method used to handle exception. 
	*/
	SeatInvalid(){
		super("This seat is not exist");
	}
}
