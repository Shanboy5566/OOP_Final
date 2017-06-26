/**
 * 這是一個指定條件查詢但找不到相關電影的例外 
 * 
 * @author bruce0621
 * @since 2017/06/26
 */
public class MovieIsNotExist extends Exception {
	/**
	 * 提供丟出錯誤的地方輸入錯誤訊息
	 * @param msg
	 */
	public MovieIsNotExist(String msg){
		super(msg);
	}
}
