import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 這個class會繼承Hall class
 * 並且擁有總座位數、灰色區域座位總數、藍色區域座位總數、黃色區域座位總數、紅色區域座位總數
 * 、廳的名字以及自定義的Seat這個class的2D Array
 * 
 * @since 2017/06/26
 * @version 1.4
 * @author bruce0621
 *
 */
public class BigHall extends Hall {

	private int SeatNum = 407;
	private int NumOfGray = 277;
	private int NumOfBlue = 44;
	private int NumOfYellow = 62;
	private int NumOfRed = 24;

	private String HallName;
	private Seat[][] seat = new Seat[13][39];
	/**
	 * 這是一個預設建構子
	 */
	public BigHall() {

	}
	/**
	 * 根據輸入的name來更新廳的名字
	 * ，再來初始化Seat的2D Array
	 * 
	 * @param name
	 * @throws JSONException
	 * @throws IOException
	 */
	public BigHall(String name) throws JSONException, IOException {
		
		this.setHallName(name);
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 39; j++) {
				seat[i][j] = new Seat();
			}
		}

		JSONArray Small = new JSONArray(readJsonFile("/Users/bruce0621/Documents/workspace/Final/src/big_room.json"));

		int k = 0;

		// System.out.println("Small len=" + Small.length());

		for (int i = 0; i < 13; i++) {

			JSONObject home = Small.getJSONObject(k);
			if (home.getString("row").equals("L")) {
				for (int j = 0; j < 39; j++) {
					JSONObject homeL = Small.getJSONObject(k);
					// System.out.println(
					// "i=" + i + " j=" + j + " equalsL " +
					// homeL.getString("row") + homeL.getInt("seatNum"));
					if (homeL.get("occupied").toString().equals("null")) {
						seat[i][j].setValid(false);
						// System.out.println("i=" + i + " j=" + j + " null " +
						// seat[i][j].isOccupied() + " "
						// + homeL.getString("row") + homeL.getInt("seatNum"));
					} else {
						// seat[i][j].setOccupied(homeL.get("occupied").toString());
						// System.out.println("i=" + i + " j=" + j + " " +
						// seat[i][j].isOccupied() + " "
						// + homeL.getString("row") + homeL.getInt("seatNum"));

					}
					seat[i][j].setId(homeL.getString("id"));
					seat[i][j].setRow(homeL.getString("row"));
					seat[i][j].setSeatNum(homeL.getInt("seatNum"));
					seat[i][j].setRegoin(homeL.getString("region"));
					k++;
				}
			} else {
				
				for (int j = 0; j < 38; j++) {
					JSONObject homeElse = Small.getJSONObject(k);
					if (j == 38) {
						continue;
					}
					if (homeElse.get("occupied").toString().equals("null")) {
						seat[i][j].setValid(false);
						// System.out.println("i=" + i + " j=" + j + " null " +
						// seat[i][j].isOccupied() + " "
						// + homeElse.getString("row") +
						// homeElse.getInt("seatNum"));
					} else {
						// seat[i][j].setOccupied(homeElse.get("occupied").toString());
						// System.out.println("i=" + i + " j=" + j + " " +
						// seat[i][j].isOccupied() + " "
						// + homeElse.getString("row") +
						// homeElse.getInt("seatNum"));

					}
					seat[i][j].setId(homeElse.getString("id"));
					seat[i][j].setRow(homeElse.getString("row"));
					seat[i][j].setSeatNum(homeElse.getInt("seatNum"));
					seat[i][j].setRegoin(homeElse.getString("region"));
					k++;
				}
			}
		}
//		this.SetNumOfRegion();
//		System.out.println("NumOfGray="+NumOfGray);
//		System.out.println("NumOfBlue="+NumOfBlue);
//		System.out.println("NumOfYellow="+NumOfYellow);
//		System.out.println("NumOfRed="+NumOfRed);
	}
	/**
	 * 這是一個提供外界取得Seat Array的方法
	 * 
	 * @return seat
	 */
	public Seat[][] getSeat() {
		return seat;
	}
	/**
	 * 這是一個提供外界取得座位總數的方法
	 * 
	 * @return SeatNum
	 */
	public int getSeatNum() {
		return SeatNum;
	}
	/**
	 * 這是一個提供外界設定座位總數的方法
	 */
	public void setSeatNum(int seatNum) {
		SeatNum = seatNum;
	}
	/**
	 * 這是一個提供外界取得廳位名稱的方法
	 * 
	 * @return HallName
	 */
	public String getHallName() {
		return HallName;
	}
	/**
	 * 這是一個提供外界設定廳位名稱的方法
	 */
	public void setHallName(String hallName) {
		HallName = hallName;
	}
	/**
	 * 根據輸入的列數(字元)行數(整數)，來檢查座位是否有效
	 * 若有效，則能詢問是否座位賣出，且return ture
	 * 若無效，座位永久不可賣出，且return false
	 * @param c
	 * @param i
	 * @return flag
	 */
	public boolean CheckSeatValid(char c, int i) {
		int row = c - 'A';
		// System.out.println("row = " + row);
		int col = i - 1;
		boolean flag = false;
		try {
			if (seat[row][col].isValid()) {
				flag = true;
			} else {
				throw new SeatInvalid();
			}
		} catch (SeatInvalid e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	/**
	 * 根據輸入的列數(字元)行數(整數)，來檢查座位是否有效
	 * 若有效，則能詢問是否座位賣出，且return ture
	 * 若無效，座位永久不可賣出，且return false
	 * @param c 
	 * @param i
	 * @return seat[row][col].isOccupied()
	 */
	public boolean CheckSeatOccupied(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].isOccupied();

	}
	/**
	 * 提供外界取得指定列內剩餘的座位數
	 * 
	 * @return empty
	 */
	public int NumOfEmptySeatOfRow(char c){
		int row = c - 'A';
		int empty = 0;
		if(c == 'L'){
			for(int i =0;i<39;i++){
				if(!seat[row][i].isOccupied()){
					empty++;
				}
			}
		}
		else{
			for(int i =0;i<38;i++){
				if(!seat[row][i].isOccupied()){
					empty++;
				}
			}
		}
		return empty;
	}
	/**
	 * 提供外界輸入數列數(字元)行數(整數)，來取得seat ID
	 * @param c
	 * @param i
	 * @return seat[row][col].getId()
	 */
	public String CheckSeatId(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].getId();

	}
	/**
	 * 提供外界輸入數列數(字元)行數(整數)，來取得seat Region
	 * 
	 * @param c
	 * @param i
	 * @return seat[row][col].getRegoin()
	 */
	public String CheckSeatRegion(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].getRegoin();
	}
	/**
	 * 提供外界一個最基本訂票的方法，輸入票張個數來完成訂票
	 * 若訂票成功，則會將seat設定為已賣出，並且return一個String的ArrayList裡頭記載著訂票完成的座位資訊
	 * 
	 * @return s
	 */
	public ArrayList<String> SetSeat(int num){
		int tmp = 0;
//		boolean flag = false;
		ArrayList<String> s = new ArrayList<String>();
		for(int i =0;i<13;i++){
			if(i==11){
				for(int j=0;j<39;j++){
					if(seat[i][j].isValid()&&!seat[i][j].isOccupied()){
						seat[i][j].setOccupied(true);
						SeatNum--;
						String region = seat[i][j].getRegoin();
						this.setRegionNum(region);
						tmp++;
						char row =(char)(i+65);
						int col = j+1;
						String Scol = "";
						if(col<10){
							Scol = String.format("%02d", col);
							s.add(row+"_"+Scol);
						}
						else{
							s.add(row +"_"+ Integer.toString(col));
						}
					}
					if(tmp==num){
//						flag = true;
						break;
					}
				}
			}
			else{
				for(int j=0;j<38;j++){
//					System.out.println("here");
					if(seat[i][j].isValid()&&!seat[i][j].isOccupied()){
//						System.out.println("row="+i+"col="+j);
						seat[i][j].setOccupied(true);
						SeatNum--;
						String region = seat[i][j].getRegoin();
						this.setRegionNum(region);
						tmp++;
						char row =(char)(i+65);
						int col = j+1;
						s.add(row+"_"+Integer.toString(col));
					}
					if(tmp==num){
						break;
					}
				}
			}
			if(tmp==num){
				break;
			}
		}
		return s;
	}
	/**
	 * 提供外界一個最條件訂票的方法，輸入指定區域、票張個數、是否要求連續來完成訂票
	 * 若訂票成功，則會將seat設定為已賣出，並且return一個String的ArrayList裡頭記載著訂票完成的座位資訊
	 * 
	 * @return s
	 */
	public ArrayList<String> SetSeat(String region, int i ,boolean flag) throws RegionSeatNotExist, NoContinuousSeat{
//		boolean flag = false;
		String seq = FindSeqOfRegion(region,i,flag);
		ArrayList<String> s = new ArrayList<String>();
		if(seq.equals("not enough")){
			s.add("not enough");
			return s;
		}
		
//		flag = true;
//		System.out.println("seq="+seq);
		String[] tmp = seq.split(" ");
		for(int k=0;k<tmp.length;k=k+2){
			seat[Integer.parseInt(tmp[k])][Integer.parseInt(tmp[k+1])].setOccupied(true);
			SeatNum--;
			setRegionNum(region);
			char row =(char)(Integer.parseInt(tmp[k])+65);
			int col = Integer.parseInt(tmp[k+1])+1;
			String Scol = "";
			if(col<10){
				Scol = String.format("%02d", col);
				s.add(row+"_"+Scol);
			}
			else{
				s.add(row +"_"+ Integer.toString(col));
			}
//			System.out.println("row="+tmp[k]+" col="+tmp[k+1]);
		}
		return s;
	}
	/**
	 * 根據使用者輸入的區域，讓指定的區域總座位數減1
	 * @param region
	 */
	private void setRegionNum(String region) {
		switch(region){
		case "gray":
			NumOfGray--;
			break;
		case "blue":
			NumOfBlue--;
			break;
		case "yellow":
			NumOfYellow--;
			break;
		case "red":
//			System.out.println("NumOfRed="+NumOfRed);
			NumOfRed--;
			break;
		}
		
	}
	/**
	 * 根據使用者輸入的區域，讓指定的區域總座位數加1
	 */
	public void resetRegionNum(String region) {
		switch(region){
		case "gray":
			NumOfGray++;
			break;
		case "blue":
			NumOfBlue++;
			break;
		case "yellow":
			NumOfYellow++;
			break;
		case "red":
//			System.out.println("NumOfRed"+NumOfRed);
			NumOfRed++;
			break;
		}
		
	}
	/**
	 * 條件訂票中會使用的一個private的method，輸入區域、張數、是否連續，則會去尋找
	 * 指定區域中可能存在的座位，最後將有效的座位寫進一個String並回傳。
	 * 若不存在連續座位或座位數不夠，則會進入例外
	 * @param region
	 * @param num
	 * @param flag
	 * @return
	 * @throws RegionSeatNotExist
	 * @throws NoContinuousSeat
	 */
	private String FindSeqOfRegion(String region, int num , boolean flag) throws RegionSeatNotExist, NoContinuousSeat {
		int tmp = 0;
		String seq = "";
		for(int i = 0;i<13;i++){
			if(i==11){
				for(int j=0;j<39;j++){
					if(tmp!=num){
						if(seat[i][j].getRegoin().equals(region)&&seat[i][j].isValid()&&!seat[i][j].isOccupied()){
							seq = seq + i + " " + j + " ";
							tmp++;
							if(!isRegionSeqValid(seq)&&flag==true){
								tmp = 1;
								seq = i + " ";
								continue;
							}
							if(tmp==num){
								break;
							}
						}
					}
				}
				
			}
			else{
//				System.out.println("here");
				for(int j=0;j<38;j++){
//					System.out.println("j="+j);
					if(tmp!=num){
//						System.out.println("if in");
//						System.out.println(seat[i][j].getRegoin());
						if(seat[i][j].getRegoin().equals(region)&&seat[i][j].isValid()&&!seat[i][j].isOccupied()){
//							System.out.println("if in 2");
							seq = seq + i + " " + j + " ";
							tmp++;
//							System.out.println("inside seq="+seq);
							if(!isRegionSeqValid(seq)&&flag==true){
								tmp = 1;
								seq = i + " ";
								continue;
							}
							if(tmp==num){
								break;
							}
						}
					}
				}
			}
//			System.out.println("seq="+seq);
			
			if(tmp==num){
				break;
			}
		}
//		System.out.println("now seq="+seq);
//		System.out.println("isSeqValid? "+isSeqValid(seq));
		if(tmp!=num&&flag==true){
			throw new NoContinuousSeat("沒有連續排的座位");
		}
		if(tmp!=num){
			return "not enough";
		}
		return seq;
	}
	/**
	 * FindSeqOfRegion會存取的一個private方法，只有當指定連續條件時才會去會去檢查輸入String
	 * 是否為有效的座位順序
	 * @param seq
	 * @return flag
	 */
	private boolean isRegionSeqValid(String seq) {
		String[] tmp = seq.split(" ");
//		System.out.println("tmp len="+tmp.length);
		if(seq.equals("")||tmp.length==1){
			return false;
		}
		String row = tmp[0];
		String col = tmp[1];
		boolean flag = true;
		
		int op = 0;
		for(int i = 2;i<tmp.length;i=i+2){
			if(tmp[i].equals(row)){
				op = Integer.parseInt(tmp[i+1]) - Integer.parseInt(col);
				if(op!=1){
					flag = false;
				}
				row = tmp[i];
				col = tmp[i+1];
			}
			else{
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * 提供外界一個最條件訂票的方法，輸入指定列數、票張個數、是否要求連續來完成訂票
	 * 若訂票成功，則會將seat設定為已賣出，並且return一個String的ArrayList裡頭記載著訂票完成的座位資訊
	 * 
	 * @return s
	 */
	public ArrayList<String> SetSeat(char c, int i, boolean flag) throws ConSeqOfRowSeatNotExist, NoContinuousSeat {
		int row = c - 'A';
		ArrayList<String> s = new ArrayList<String>();
//		boolean flag = false;
		String seq = FindConSeqOfRow(row,i,flag);
		if(seq.equals("not enough")){
			s.add("not enough");
			return s;
		}
//		flag = true;
//		System.out.println(seq);
		String[] seqarr = seq.split(" ");
//		System.out.println("NumOfGray="+NumOfGray);
//		System.out.println("NumOfBlue="+NumOfBlue);
//		System.out.println("NumOfYellow="+NumOfYellow);
//		System.out.println("NumOfRed="+NumOfRed);
		for(int j=0;j<seqarr.length;j++){
			seat[row][Integer.parseInt(seqarr[j])].setOccupied(true);
			setRegionNum(seat[row][Integer.parseInt(seqarr[j])]);
			SeatNum--;
			int col = Integer.parseInt(seqarr[j]) + 1;
			String Scol = "";
			if(col<10){
				Scol = String.format("%02d", col);
				s.add(c+"_"+Scol);
			}
			else{
				s.add(c +"_"+ Integer.toString(col));
			}
		}
//		System.out.println("NumOfGray="+NumOfGray);
//		System.out.println("NumOfBlue="+NumOfBlue);
//		System.out.println("NumOfYellow="+NumOfYellow);
//		System.out.println("NumOfRed="+NumOfRed);
		return s;
	}
	/**
	 * 提供一個方法輸數座位Seat，自動判斷它的區域為何，再去將對應的區域總位數1
	 * @param seat
	 */
	private void setRegionNum(Seat seat) {
		String color = seat.getRegoin();
		switch(color){
		case "gray":
			NumOfGray--;
			break;
		case "blue":
			NumOfBlue--;
			break;
		case "yellow":
			NumOfYellow--;
			break;
		case "red":
			NumOfRed--;
			break;
		
		}
			
		
	}
	/**
	 * 條件訂票中會使用的一個private的method，輸入列數、張數、是否連續，則會去尋找
	 * 指定區域中可能存在的座位，最後將有效的座位寫進一個String並回傳。
	 * 若不存在連續座位或座位數不夠，則會進入例外
	 * 
	 * @param row
	 * @param num
	 * @param flag
	 * @return
	 * @throws ConSeqOfRowSeatNotExist
	 * @throws NoContinuousSeat
	 */
	private String FindConSeqOfRow(int row , int num ,boolean flag) throws ConSeqOfRowSeatNotExist, NoContinuousSeat {
		int tmp = 0;
		String seq = "";
		if(row==11){
			for(int i=0;i<39;i++){
				if(seat[row][i].isValid()){
					if(!seat[row][i].isOccupied()){
						tmp++;
						seq = seq + i +" ";
						if(!isSeqValid(seq)&&flag==true){
							tmp = 1;
							seq = i + " ";
							continue;
						}
						if(tmp==num){
							break;
						}
						continue;
					}
				}
				tmp = 0;
				seq = "";
			}
			if(tmp!=num&&!isSeqValid(seq)&&flag==true){
				throw new NoContinuousSeat("沒有連續排的座位");
			}
			if(tmp!=num){
				return "not enough";
			}
			else{
				return seq;
			}
			
		}
		else{
			for(int i=0;i<38;i++){
//				System.out.println("seat[row]["+i+"]"+seat[row][i].isOccupied());
				if(seat[row][i].isValid()){
					if(!seat[row][i].isOccupied()){
//						System.out.println("seat[row]["+i+"]"+seat[row][i].isOccupied());
						tmp++;
						seq = seq + i + " ";
						if(!isSeqValid(seq)&&flag==true){
							tmp = 1;
							seq = i + " ";
							continue;
						}
						if(tmp==num){
							break;
						}
						continue;
					}
				}
				tmp = 0;
				seq = "";
			}
//			System.out.println("seq="+seq);
//			System.out.println("tmp="+tmp);
//			System.out.println("num="+num);
//			System.out.println("isSeqValid? "+isSeqValid(seq));
			if(tmp!=num&&flag==true){
				throw new NoContinuousSeat("沒有連續排的座位");
			}
			if(tmp!=num){
				throw new ConSeqOfRowSeatNotExist("Seat not enough");
			}
			else{
				return seq;
			}
			
		}
		
	}
	/**
	 * FindConSeqOfRow會存取的一個private方法，只有當指定連續條件時才會去會去檢查輸入String
	 * 是否為有效的座位順序
	 * 
	 * @param seq
	 * @return flag
	 */
	private boolean isSeqValid(String seq) {
		String[] tmp = seq.split(" ");
		String basis = tmp[0];
		boolean flag = true;
		if(seq.equals("")){
			flag = false;
		}
		int op=0;
		for(int i=1;i<tmp.length;i++){
			op = Integer.parseInt(tmp[i]) - Integer.parseInt(basis);
			if(op!=1){
				flag = false;
			}
			basis = tmp[i];
		}
		return flag;
	}
	/**
	 * 提供外界取得灰色區域總座位數的方法
	 * @return NumOfGray
	 */
	public int getNumOfGray() {
		return NumOfGray;
	}
	/**
	 * 提供外界取得藍色區域總座位數的方法
	 * @return NumOfBlue
	 */
	public int getNumOfBlue() {
		return NumOfBlue;
	}
	/**
	 * 提供外界取得黃色區域總座位數的方法
	 * @return NumOfYellow
	 */
	public int getNumOfYellow() {
		return NumOfYellow;
	}
	/**
	 * 提供外界取得紅色區域總座位數的方法
	 * @return NumOfRed
	 */
	public int getNumOfRed() {
		return NumOfRed;
	}
//	private void SetNumOfRegion(){
//		for(int i=0;i<13;i++){
//			if(i==11){
//				for(int j=0;j<39;j++){
//					if(seat[i][j].isValid()){
//						String color = seat[i][j].getRegoin();
////						System.out.println("row="+i+"col="+j);
////						System.out.println("color="+color);
//						switch(color){
//						case "gray":
//							NumOfGray++;
//							break;
//						case "blue":
//							NumOfBlue++;
//							break;
//						case "yellow":
//							NumOfYellow++;
//							break;
//						case "red":
//							NumOfRed++;
//							break;
//						}
//					}
//				}
//			}
//			else{
//				for(int j=0;j<38;j++){
//					if(seat[i][j].isValid()){
//						String color = seat[i][j].getRegoin();
////						System.out.println("row="+i+"col="+j);
////						System.out.println("color="+color);
//						switch(color){
//						case "gray":
//							NumOfGray++;
//							break;
//						case "blue":
//							NumOfBlue++;
//							break;
//						case "yellow":
//							NumOfYellow++;
//							break;
//						case "red":
//							NumOfRed++;
//							break;
//						}
//					}
//				}
//			}
//		}
//	}
	

}
