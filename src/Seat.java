/**
 * 這是一個實作自定義class Seat的class
 * ，Seat將會擁有：
 * 	(1) id
 * 	(2) 列數
 *  (3) 座位數
 *  (4) 售出狀況
 *  (5) 是否有效
 *  (6) 區域
 * @author bruce0621
 * @since 2017/06/26
 */
public class Seat {
	
	private String id;
	private String row;
	private int seatNum;
	private boolean Occupied;
	private boolean Valid = true; // self-defined
	private String regoin;
	/**
	 * 提供外界得知該座位是否有效，若有效則return true，反之
	 * @return Valid
	 */
	public boolean isValid() {
		return Valid;
	}
	/**
	 * 提供外界更新Valid的值
	 * @param valid
	 */
	public void setValid(boolean valid) {
		Valid = valid;
	}
	/**
	 * 提供外界得知該座位是否賣出，若賣出則return true，反之
	 * @return
	 */
	public boolean isOccupied() {
		return Occupied;
	}
	/**
	 * 提供外界更新Occupied值
	 * @param occupied
	 */
	public void setOccupied(boolean occupied) {
		Occupied = occupied;
	}
	/**
	 * 提供外界取得該座位ID
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 提供外界更新座位id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 提供外界取得該座位的列數
	 * @return row
	 */
	public String getRow() {
		return row;
	}
	/**
	 * 提供外界更新座位的列數值
	 * @param row
	 */
	public void setRow(String row) {
		this.row = row;
	}
	/**
	 * 提供外界取得座位數
	 * @return seatNum
	 */
	public int getSeatNum() {
		return seatNum;
	}
	/**
	 * 提供外界更新座位數
	 * @param seatNum
	 */
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	/**
	 * 提供外界取得該座位區域
	 * @return region
	 */
	public String getRegoin() {
		return regoin;
	}
	/**
	 * 提供外界更新該座位區域
	 * @param regoin
	 */
	public void setRegoin(String regoin) {
		this.regoin = regoin;
	}
	
	
	
	
}
