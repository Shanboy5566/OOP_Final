/**
 * 這是一個使用者因年齡無法觀看電影的例外
 * @author bruce0621
 *
 */
public class CanNotWatchThisMovie extends Exception {
	/**
	 * 提供丟出錯誤的地方輸入錯誤訊息
	 * @param msg
	 */
	public CanNotWatchThisMovie(String msg){
		super(msg);
	}
}
