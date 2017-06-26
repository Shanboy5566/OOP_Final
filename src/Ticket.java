//package src;
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
	/**
	* movie getter. 
	* @return movie.
	*/
	public Movie getMovie() {
		return movie;
	}

	/**
	* movie setter.  
	* @param movie
	*/
	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	private String ticketID;    	//電影票編號
    private Movie movie;    //電影名字
    private String seat;      //座位編號
    private Classification classification; //分級
    private String Running_Time;    //片長
    private String time;        //開始時間(小時)
    private Hall hall;        //廳院
    
    /**
   	* ticket id getter.  
   	* @return String	ticketID
   	*/
    public String getTicketID() {
		return ticketID;
	}
    
    /**
	* ticket id  setter.  
	* @param ticketID
	*/
	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

    /**
	* seat getter.  
	* @param ticketID
	* @return String	seat
	*/
	public String getSeat() {
		return seat;
	}
    /**
	* seat  setter.  
	* @param seat
	*/

	public void setSeat(String seat) {
		this.seat = seat;
	}
	
    /**
	* classification getter.  
	* @return Classification	classification
	*/

	public Classification getClassification() {
		return classification;
	}
	/**
	* seat  setter.  
	* @param seat
	*/

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

    /**
	* classification getter.  
	* @return Classification	classification
	*/
	public String getRunning_Time() {
		return Running_Time;
	}
	/**
	* Running_Time  setter.  
	* @param running_Time
	*/

	public void setRunning_Time(String running_Time) {
		Running_Time = running_Time;
	}
	
	/**
	* Time getter.  
	* @return String	time
	*/

	public String getTime() {
		return time;
	}
	/**
	* Time  setter.  
	* @param time
	*/

	public void setTime(String time) {
		this.time = time;
	}

	/**
	* Hall getter.  
	* @return Hall	hall
	*/

	public Hall getHall() {
		return hall;
	}

	/**
	* Hall  setter.  
	* @param hall
	*/

	public void setHall(Hall hall) {
		this.hall = hall;
	}
	/**
	* Ticket constructor. 
	* @param ticketID
	* @param movie
	* @param seat
	* @param classification
	* @param Running_Time
	* @param time
	* @param hall
	*
	*/
    public Ticket(String ticketID, Movie movie,String seat, Classification classification,
    		String Running_Time, String time, Hall hall) {
        this.ticketID = ticketID;
        this.movie = movie;
        this.seat = seat;
        this.classification = classification;
        this.Running_Time = Running_Time;
        this.time = time;
        this.hall = hall;
    }
    
	/**
	* print ticket information.  
	*/
    public void getTicketInfo() {
        System.out.println("電影名稱 ： " + movie.getMovieName() + "\n"
        + "場次時間 : " + time +"\n"
        + "廳院 : " + hall.getHallName() +"\n"
        + "座位 : "+seat); //需要呼叫這張票的座位
    }
}
