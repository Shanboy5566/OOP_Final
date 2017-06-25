package src;
import java.util.HashMap;
/**
 * 這個Ticket class描述了一張電影票應該要有的屬性如下：
 *  (1)電影票編號 ticketID(int) ;   
 *  (2)電影名字 movieName(String);     
 *  (3)分級 classification(int); 
 *  (4)片長 Running_Time(int);          
 *  (5)開始時間(小時)Time_Hour(int); 
 *  (6)開始時間(分鐘)int Time_Minute;	//開始時間(分鐘)
 *  (7) String hall;        //廳院
 *  
 *
 */

public class Ticket {

    private int ticketID;    	//電影票編號
    private String movieName;    //電影名字
    private String seat;      //座位編號
    private String classification; //分級
    private int Running_Time;    //片長
    private String time;        //開始時間(小時)
    private String hall;        //廳院


    public Ticket(int ticketID, String movieName,String seat, String classification, int Running_Time, String time, String hall) {
        this.setTicketID(ticketID);
        this.movieName = movieName;
        this.setSeat(seat);
        this.classification=classification;
        this.Running_Time=Running_Time;
        this.time = time;
        this.hall = hall;
    }

    
    public String getTime() {
        return time;
    }

    public void printTicket() {
        System.out.println("電影名稱 ： " + movieName + "\n"
        + "場次時間 : " + time +"\n"
        + "廳院 : " + hall +"\n"
        + "座位 : "); //需要呼叫這張票的座位
    }

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public int getRunning_Time() {
		return Running_Time;
	}

	public void setRunning_Time(int running_Time) {
		Running_Time = running_Time;
	}


	public String getSeat() {
		return seat;
	}


	public void setSeat(String seat) {
		this.seat = seat;
	}
}

