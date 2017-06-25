import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BigHall extends Hall {

	public Seat[][] getSeat() {
		return seat;
	}

	private int SeatNum = 407;
	private int NumOfGray = 277;
	private int NumOfBlue = 44;
	private int NumOfYellow = 62;
	private int NumOfRed = 24;

	private String HallName;
	private Seat[][] seat = new Seat[13][39];

	public BigHall() {

	}

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

	public int getSeatNum() {
		return SeatNum;
	}

	public void setSeatNum(int seatNum) {
		SeatNum = seatNum;
	}

	public String getHallName() {
		return HallName;
	}

	public void setHallName(String hallName) {
		HallName = hallName;
	}

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

	public boolean CheckSeatOccupied(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].isOccupied();

	}
	
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

	public String CheckSeatId(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].getId();

	}

	public String CheckSeatRegion(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].getRegoin();
	}
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
						s.add(row+"_"+Integer.toString(col));
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
	public ArrayList<String> SetSeat(String region, int i) throws RegionSeatNotExist{
//		boolean flag = false;
		String seq = FindSeqOfRegion(region,i);
		ArrayList<String> s = new ArrayList<String>();
//		flag = true;
//		System.out.println("seq="+seq);
		String[] tmp = seq.split(" ");
		for(int k=0;k<tmp.length;k=k+2){
			seat[Integer.parseInt(tmp[k])][Integer.parseInt(tmp[k+1])].setOccupied(true);
			SeatNum--;
			setRegionNum(region);
			char row =(char)(Integer.parseInt(tmp[k])+65);
			int col = Integer.parseInt(tmp[k+1])+1;
			
			s.add(row +"_"+ Integer.toString(col));
//			System.out.println("row="+tmp[k]+" col="+tmp[k+1]);
		}
		return s;
	}
	
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

	private String FindSeqOfRegion(String region, int num) throws RegionSeatNotExist {
		int tmp = 0;
		String seq = "";
		for(int i = 0;i<13;i++){
			if(i==11){
				for(int j=0;j<39;j++){
					if(tmp!=num){
						if(seat[i][j].getRegoin().equals(region)&&seat[i][j].isValid()&&!seat[i][j].isOccupied()){
							seq = seq + i + " " + j + " ";
							tmp++;
						}
					}
				}
				
			}
			else{
				for(int j=0;j<38;j++){
					if(tmp!=num){
						if(seat[i][j].getRegoin().equals(region)&&seat[i][j].isValid()&&!seat[i][j].isOccupied()){
							seq = seq + i + " " + j + " ";
							tmp++;
						}
					}
				}
			}
			if(tmp==num){
				break;
			}
		}
		if(tmp!=num){
			throw new RegionSeatNotExist("Seat not enough");
		}
		return seq;
	}

	public ArrayList<String> SetSeat(char c, int i) throws ConSeqOfRowSeatNotExist {
		int row = c - 'A';
		ArrayList<String> s = new ArrayList<String>();
//		boolean flag = false;
		String seq = FindConSeqOfRow(row,i);
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
			s.add(c+"_"+Integer.toString(col));
		}
//		System.out.println("NumOfGray="+NumOfGray);
//		System.out.println("NumOfBlue="+NumOfBlue);
//		System.out.println("NumOfYellow="+NumOfYellow);
//		System.out.println("NumOfRed="+NumOfRed);
		return s;
	}
	
	
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

	private String FindConSeqOfRow(int row , int num) throws ConSeqOfRowSeatNotExist {
		int tmp = 0;
		String seq = "";
		if(row==11){
			for(int i=0;i<39-num+1;i++){
				if(seat[row][i].isValid()&&seat[row][i+1].isValid()){
					if(!seat[row][i].isOccupied()&&!seat[row][i+1].isOccupied()){
						tmp++;
						seq = seq + i +" ";
						if(tmp==num){
							break;
						}
						continue;
					}
				}
				tmp = 0;
				seq = "";
			}
			if(tmp!=num){
				throw new ConSeqOfRowSeatNotExist("Seat not enough");
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
						if(tmp==num){
							break;
						}
						continue;
					}
				}
				tmp = 0;
				seq = "";
			}
			if(tmp!=num){
				throw new ConSeqOfRowSeatNotExist("Seat not enough");
			}
			else{
				return seq;
			}
			
		}
		
	}

	public int getNumOfGray() {
		return NumOfGray;
	}

	public int getNumOfBlue() {
		return NumOfBlue;
	}

	public int getNumOfYellow() {
		return NumOfYellow;
	}

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
