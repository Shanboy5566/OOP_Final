/**
 * 這是一個指定區域訂票，但不存在符合條件的例外
 * @author bruce0621
 * @since 2017/06/26
 */
public class RegionSeatNotExist extends Exception {
	/**
	 * 提供丟出錯誤的地方輸入錯誤訊息
	 * @param msg
	 */
	RegionSeatNotExist(String meg){
		super(meg);
	}
}
