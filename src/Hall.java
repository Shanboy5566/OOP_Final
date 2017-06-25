import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;
import org.apache.commons.io.IOUtils;

public class Hall {
	public ArrayList<String> SetSeat(int num){
		return null;
	}
	public ArrayList<String> SetSeat(char c, int i) throws RegionSeatNotExist, ConSeqOfRowSeatNotExist {
		return null;
	}
	public ArrayList<String> SetSeat(String region, int i) throws RegionSeatNotExist{
		return null;
	}
	public int getSeatNum() {
		return SeatNum;
	}

	public void setSeatNum(int seatNum) {
		SeatNum = seatNum;
	}

	private int SeatNum;
	private String HallName="Parent Hall default";
	
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
