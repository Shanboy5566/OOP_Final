import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;
import org.apache.commons.io.IOUtils;

public class Hall {
	public Seat[][] getSeat() {
		return seat;
	}
	public ArrayList<String> SetSeat(int num){
		return null;
	}
	public ArrayList<String> SetSeat(char c, int i) throws RegionSeatNotExist, ConSeqOfRowSeatNotExist {
		return null;
	}
	public ArrayList<String> SetSeat(String region, int i) throws RegionSeatNotExist{
		return null;
	}
	public int getNumOfGray() {
		return 0;
	}

	public int getNumOfBlue() {
		return 0;
	}

	public int getNumOfYellow() {
		return 0;
	}

	public int getNumOfRed() {
		return 0;
	}
	public int getSeatNum() {
		return SeatNum;
	}
	public void resetRegionNum(String region){
		
	}

	public void setSeatNum(int seatNum) {
		SeatNum = seatNum;
	}

	private int SeatNum;
	
	private String HallName="Parent Hall default";
	private Seat[][] seat;
	
	public String getHallName() {
		return HallName;
	}

	public void setHallName(String hallName) {
		HallName = hallName;
	}
	public int NumOfEmptySeatOfRow(char c){
		return 0;
	}

	public Hall(){
		
	}
	public Hall(String name){
		HallName = name;
	}
	
	public String readJsonFile(String filename) throws IOException {
	    try (InputStream is = new FileInputStream(filename)) {
	        return IOUtils.toString(is, StandardCharsets.UTF_8);
	    }    
	}
	
}
