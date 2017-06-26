import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/** This is a program that can serve as a small hall class.
 */
/**
* 
* @version 1.0
* @since   2017-06-26 
 */

public class SmallHall extends Hall {
	public Seat[][] getSeat() {
		return seat;
	}

	private int SeatNum = 144;
	private String HallName;

	private Seat[][] seat = new Seat[9][16];

	public SmallHall() {

	}
	/**
	* SmallHall constructor. 
	* @param String	name
	* @throws JSONException, IOException
	*/
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
	/**
	* Hall name getter.  
	* @return String	HallName
	*/
	public String getHallName() {
		return HallName;
	}
	/**
	* Hall name setter.  
	* @param String	hallname
	*/

	public void setHallName(String hallName) {
		HallName = hallName;
	}
	/**
	* Seat number getter.  
	* @return int	HallName
	*/
	public int getSeatNum() {
		return SeatNum;
	}
	/**
	* Seat number setter.  
	* @param int	seatNum
	*/
	public void setSeatNum(int seatNum) {
		SeatNum = seatNum;
	}
	/**
	* Check if seat is valid 
	* @param int	c
	* @param int	i
	* @return boolean	flag
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
	* Set seat
	* @param int	num
	* @return ArrayList<String>	s
	*/
	public ArrayList<String> SetSeat(int num){
		int tmp = 0;
		ArrayList<String> s = new ArrayList<String>();
//		boolean flag = false;
//		System.out.println("here");
		for(int i =0;i<9;i++){
			for(int j=0;j<16;j++){
				if(seat[i][j].isValid()&&!seat[i][j].isOccupied()){
//					System.out.println("row="+i+"col="+j);
					seat[i][j].setOccupied(true);
					SeatNum--;
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
					if(tmp==num){
//						flag = true;
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
	* Set seat
	* @param char	c
	* @param int	i
	* @param boolean	flag
	* @throws ConSeqOfRowSeatNotExist, NoContinuousSeat.
	* @return ArrayList<String>	s
	*/
	public ArrayList<String> SetSeat(char c, int i , boolean flag) throws ConSeqOfRowSeatNotExist, NoContinuousSeat {
//		if (CheckSeatValid(c, i)) {
//			int row = c - 'A';
//			int col = i - 1;
//			seat[row][col].setOccupied(true);
//			this.setSeatNum(this.getSeatNum() - 1);
//		}
		int row = c - 'A';
		ArrayList<String> s = new ArrayList<String>();
//		boolean flag = false;
		String seq = FindConSeqOfRow(row,i,flag);
//		flag =true;
//		System.out.println(seq);
		String[] seqarr = seq.split(" ");
		
		for(int j=0;j<seqarr.length;j++){
			seat[row][Integer.parseInt(seqarr[j])].setOccupied(true);
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
		return s;
	}
	/**
	* Find continuous seats in a given row
	* @param char	c
	* @param int	i
	* @param boolean	flag
	* @throws ConSeqOfRowSeatNotExist, NoContinuousSeat.
	* @return String	seq
	*/
	private String FindConSeqOfRow(int row, int num , boolean flag) throws  ConSeqOfRowSeatNotExist, NoContinuousSeat {
		int tmp = 0;
		String seq = "";
		for(int i=0;i<16;i++){
			if(seat[row][i].isValid()){
				if(!seat[row][i].isOccupied()){
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
//			tmp = 0;
//			seq = "";
		}
//		System.out.println("seq="+seq);
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
	/**
	* Check if sequence is valid
	* @param String	seq
	* @return boolean	flag
	*/
	private boolean isSeqValid(String seq) {
		String[] tmp = seq.split(" ");
		String basis = tmp[0];
		boolean flag = true;
		if(seq.equals("")){
			flag = false;
		}
		if(tmp.length==1){
			flag = true;
		}
		for(int i=1;i<tmp.length;i++){
			if((basis.equals("3")&&tmp[i].equals("4"))||(basis.equals("11")&&tmp[i].equals("12"))){
				flag = false;
			}
			basis = tmp[i];
		}
		return flag;
		
	}
	/**
	* Check if seat is occupied
	* @param char	c
	* @param int	i
	* @return boolean	isOccupied().
	*/
	public boolean CheckSeatOccupied(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].isOccupied();

	}
	/**
	* Check the number of empty seat in a given row
	* @param char	c
	* @return int	empty.
	*/
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
	/**
	* Check seat id number
	* @param char	c
	* @param int	i
	* @return String	getId().
	*/
	public String CheckSeatId(char c, int i) {
		int row = c - 'A';
		int col = i - 1;

		return seat[row][col].getId();

	}

}
