/**
 * 這是一個因不存在連續座位而產生的例外
 * @author bruce0621
 * @since 2017/06/26
 */
public class NoContinuousSeat extends Exception {
	/**
	 * 提供丟出錯誤的地方輸入錯誤訊息
	 * @param msg
	 */
	public NoContinuousSeat(String msg){
		super(msg);
	}
}
