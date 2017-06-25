
public class Seat {
	
	private String id;
	private String row;
	private int seatNum;
	private boolean Occupied;
	private boolean Valid = true; // self-defined
	private String regoin;
	
	public boolean isValid() {
		return Valid;
	}
	public void setValid(boolean valid) {
		Valid = valid;
	}
	public boolean isOccupied() {
		return Occupied;
	}
	public void setOccupied(boolean occupied) {
		Occupied = occupied;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
	public String getRegoin() {
		return regoin;
	}
	public void setRegoin(String regoin) {
		this.regoin = regoin;
	}
	
	
	
	
}
