/**
 * 這是一個因電影票ID不存在而退票失敗的例外class
 * @author bruce0621
 *
 */
public class CancelFailed extends Exception {
	/**
	 * 提供丟出錯誤的地方輸入錯誤訊息
	 * @param msg
	 */
	public CancelFailed (String msg){
		super(msg);
	}
}
