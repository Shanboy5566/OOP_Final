import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;
import org.apache.commons.io.IOUtils;
/**
 * 這是一個電影廳的描述，擁有一個自定義class Seat的2D Array，並且提供外界訂座位、取得座位數相關的method
 * @author bruce0621
 * @since 2016/06/26
 * @version 1.4
 */
public class Hall {
	/**
	 * 這是一個提供外界存取2D座位陣列的method
	 * @return seat
	 */
	public Seat[][] getSeat() {
		return seat;
	}
	/**
	 * 提供一個基本訂票方法，只要輸入票張數即可
	 * 期待繼承此class的人會實作
	 * 若無人繼承，直接呼叫此method將會回傳null
	 * @param num
	 * @return null
	 */
	public ArrayList<String> SetSeat(int num){
		return null;
	}
	/**
	 * 提供一個條件訂票方法，輸入指定排數、票數、是否連續
	 * 期待繼承此class的人會實作
	 * 若無人繼承，直接呼叫此method將會回傳null
	 * @param c
	 * @param i
	 * @param flag
	 * @return
	 * @throws RegionSeatNotExist
	 * @throws ConSeqOfRowSeatNotExist
	 * @throws NoContinuousSeat
	 */
	public ArrayList<String> SetSeat(char c, int i ,boolean flag) throws RegionSeatNotExist, ConSeqOfRowSeatNotExist, NoContinuousSeat {
		return null;
	}
	/**
	 * 提供一個條件訂票方法，輸入指定區域、票數、是否連續
	 * 期待繼承此class的人會實作
	 * 若無人繼承，直接呼叫此method將會回傳null
	 * @param region
	 * @param i
	 * @param flag
	 * @return
	 * @throws RegionSeatNotExist
	 * @throws NoContinuousSeat
	 */
	public ArrayList<String> SetSeat(String region, int i,boolean flag) throws RegionSeatNotExist, NoContinuousSeat{
		return null;
	}
	/**
	 * 期待BigHall會繼承並實作
	 * 若無人繼承，呼叫此method將會回傳0
	 * @return 0
	 */
	public int getNumOfGray() {
		return 0;
	}
	/**
	 * 期待BigHall會繼承並實作
	 * 若無人繼承，呼叫此method將會回傳0
	 * @return 0
	 */
	public int getNumOfBlue() {
		return 0;
	}
	/**
	 * 期待BigHall會繼承並實作
	 * 若無人繼承，呼叫此method將會回傳0
	 * @return 0
	 */
	public int getNumOfYellow() {
		return 0;
	}
	/**
	 * 期待BigHall會繼承並實作
	 * 若無人繼承，呼叫此method將會回傳0
	 * @return 0
	 */
	public int getNumOfRed() {
		return 0;
	}
	/**
	 * 期待會被繼承並實作
	 * 若無人繼承，呼叫此method將會回傳SeatNum
	 * @return SeatNum
	 */
	public int getSeatNum() {
		return SeatNum;
	}
	/**
	 * 期待BigHall會繼承並實作
	 * 若無人繼承，呼叫此method將不會有任何動作
	 * @param region
	 */
	public void resetRegionNum(String region){
		
	}
	/**
	 * 期待會被繼承
	 * 若無人繼承，呼叫此method將會回傳SeatNum
	 * @param seatNum
	 */
	public void setSeatNum(int seatNum) {
		SeatNum = seatNum;
	}

	private int SeatNum;
	
	private String HallName="Parent Hall default";
	private Seat[][] seat;
	/**
	 * 期待會被繼承
	 * 若無人繼承，呼叫此method將會回傳HallName
	 * @return HallName
	 */
	public String getHallName() {
		return HallName;
	}
	/**
	 * 期待會被繼承
	 * 若無人繼承，呼叫此method將會更新HallName
	 * @param hallName
	 */
	public void setHallName(String hallName) {
		HallName = hallName;
	}
	/**
	 * 期待會被繼承
	 * 若無人繼承，呼叫此method將會回傳0
	 * @param c
	 * @return
	 */
	public int NumOfEmptySeatOfRow(char c){
		return 0;
	}
	/**
	 * Default constructor
	 */
	public Hall(){
		
	}
	/**
	 * 透過指定電影院名字更新HallName，並建造Hall
	 * @param name
	 */
	public Hall(String name){
		HallName = name;
	}
	/**
	 * 提供繼承此class的人都有Json File的爬蟲
	 * @param filename
	 * @return IOUtils.toString(is, StandardCharsets.UTF_8)
	 * @throws IOException
	 */
	public String readJsonFile(String filename) throws IOException {
	    try (InputStream is = new FileInputStream(filename)) {
	        return IOUtils.toString(is, StandardCharsets.UTF_8);
	    }    
	}
	
}
