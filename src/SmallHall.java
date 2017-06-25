import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmallHall extends Hall {
	private int SeatNum = 144;
	private String HallName;

	Seat[][] seat = new Seat[9][16];

	public SmallHall() {

	}

	public SmallHall(String name) throws JSONException, IOException {
		this.setHallName(name);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 16; j++) {
				seat[i][j] = new Seat();
			}
		}

		JSONArray Small = new JSONArray(readJsonFile("/Users/bruce0621/Documents/workspace/Final/src/small_room.json"));

		int k = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 16; j++) {
				JSONObject home = Small.getJSONObject(k);
				seat[i][j].setId(home.getString("id"));
				seat[i][j].setRow(home.getString("row"));
				seat[i][j].setSeatNum(home.getInt("seatNum"));
				seat[i][j].setOccupied(home.getBoolean("occupied"));
				k++;
			}
		}
	}

	public String getHallName() {
		return HallName;
	}

	public void setHallName(String hallName) {
		HallName = hallName;
	}

	public int getSeatNum() {
		return SeatNum;
	}

	public void setSeatNum(int seatNum) {
		SeatNum = seatNum;
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
	
	public void SetSeat(char c, int i) throws ConSeqOfRowSeatNotExist {
//		if (CheckSeatValid(c, i)) {
//			int row = c - 'A';
//			int col = i - 1;
//			seat[row][col].setOccupied(true);
//			this.setSeatNum(this.getSeatNum() - 1);
//		}
		int row = c - 'A';
		String seq = FindConSeqOfRow(row,i);
//		System.out.println(seq);
		String[] seqarr = seq.split(" ");
		
		for(int j=0;j<seqarr.length;j++){
			seat[row][Integer.parseInt(seqarr[j])].setOccupied(true);
			SeatNum--;
		}
		
	}

	private String FindConSeqOfRow(int row, int num) throws  ConSeqOfRowSeatNotExist {
		int tmp = 0;
		String seq = "";
		for(int i=0;i<16;i++){
			if(seat[row][i].isValid()&&seat[row][i+1].isValid()){
				if(!seat[row][i].isOccupied()&&!seat[row][i+1].isOccupied()){
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

	public boolean CheckSeatOccupied(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].isOccupied();

	}
	public int NumOfEmptySeatOfRow(char c){
		int row = c - 'A';
		if(row>8){
			return 0;
		}
		int empty = 0;
		for(int i =0;i<16;i++){
			if(!seat[row][i].isOccupied()){
				empty++;
			}
		}
		return empty;
	}

	public String CheckSeatId(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].getId();

	}

}
