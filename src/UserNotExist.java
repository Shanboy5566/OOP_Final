/**
* 使用者不存在的例外處理
* @version 1.0
* @since   2017-06-26 
 */
public class UserNotExist extends Exception {
	/**
	* This a constructor for UserNotExist
	* @param msg
	*/
	public UserNotExist(String msg){
		super(msg);
	}
}
