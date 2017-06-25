
//package src;
import java.util.HashMap;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;
import org.apache.commons.io.IOUtils;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 這個TicketOrderService提供 1.一般訂票(Booking) 2.條件訂票(ConditionalBooking)
 * 3.退票(cancel) 4.查詢(inquiry)
 * 
 * @author Roy
 *
 */
public class TicketOrderService {

	// private String user_ID; //使用者ID
	// private String movie_ID; //電影ID
	// private String movie; //電影名稱
	// private String time; //開始時間
	// private int time_minute; //開始時間（幾分）
	// private int ticketNumber; //電影票張數

	private int MovieTicket_ID; // 最後被發行的電影票ID
	private HashMap<String, Ticket> User_ticket; // 儲存所有的ID和ticket資料
	private HashMap<Integer, User> User_Info;

	public MovieInfo robot;
	public User userlist;
	public ArrayList<Ticket> ticketlist;
	private int ticketid = 0;

	public TicketOrderService() throws JSONException, IOException {
		// robot = new MovieInfo();
		robot = new MovieInfo();
		userlist = new User();
		ticketlist = new ArrayList<Ticket>();
	}

	public Ticket getTicket(String TicketId) {
		int key = -1;
		for (int i = 0; i < ticketlist.size(); i++) {
			if (ticketlist.get(i).getTicketID().equals(TicketId)) {
				key = i;
			}
		}
		if (key == -1) {

		}
		return ticketlist.get(key);
	}

	public boolean isTicketValid(String TicketId) {
		boolean flag = false;
		for (int i = 0; i < ticketlist.size(); i++) {
			if (ticketlist.get(i).getTicketID().equals(TicketId)) {
				flag = true;
			}
		}
		return flag;
	}

	// public void Booking(int user_ID, String movie_ID, String time, int
	// ticketNumber) throws JSONException, IOException, MovieNotExist {
	//
	// int SeatNum=robot.GetMovieRemainSeat(movie_ID, time);
	// ArrayList<String> s = new ArrayList<String>();
	// User.setUser(user_ID);
	// robot.setMovieInfo(movie_ID);
	//
	// Classification cl= new Classification();
	// cl.setClassificaiton(robot.classification);
	// int limit=cl.getAgelimit();
	//
	// robot.movielist.get(2).getHall().getHallName();
	//
	// if(SeatNum>0){
	// System.out.println(movie_ID+" 於 " + time+"目前仍有 "+ SeatNum +"個位子");
	// s = robot.getMovie(movie_ID, time).setSeat(ticketNumber);
	// }else if(SeatNum<0){
	// System.out.println("失敗。"+movie_ID +" 於 " + time+"座位數量不夠");
	// }else if(User.user_age < limit ){
	// System.out.println("失敗。該電影分級為 "+ cl.getClassificaiton() + " 級 ，"+
	// User.user_age +"歲無法購買");
	// }
	// }
	public ArrayList<String> Booking(int user_ID, String movie_ID, String time, int ticketNumber) throws JSONException, IOException,
			MovieNotExist, MovieIsNotExist, UserNotExist, CanNotWatchThisMovie, SeatNotEnough {
		Movie movie = robot.getMovie(movie_ID, time);
		ArrayList<String> s = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		User user = userlist.GetUser(user_ID);
		Ticket ticket = null;
		if (ticketNumber <= movie.getMovieRemainSeat()) {
			if (user.user_age >= movie.getClassification().getAgelimit()) {
				s = movie.setSeat(ticketNumber);
				String moviename = movie.getMovieName();
				Classification c = movie.getClassification();
				String infor = movie.getInfor();
				String StartTime = movie.getTime();
				Hall hall = movie.getHall();
				for (int i = 0; i < s.size(); i++) {
					ticketid++;
					String ticketID = String.format("%03d", ticketid);
					ticket = new Ticket(ticketID, movie, s.get(i), c, infor, StartTime, hall);
					ticketlist.add(ticket);
					id.add(ticketID);
//					System.out.println(movie.getId());
				}
				System.out.println(movie.getId() + " 於 " + movie.getTime() + " 目前仍有 " + movie.getMovieRemainSeat());

			} else {
				Classification c = movie.getClassification();
				throw new CanNotWatchThisMovie("失敗, 該電影分級為" + c.getClassificaiton() + ", " + user.user_age + "歲無法購買");
			}
		} else {
			throw new SeatNotEnough("失敗," + movie.getId() + "於" + movie.getTime() + "座位數量不夠");
		}
		return id;
	}

	public ArrayList<String> ConditionalBooking(int user_ID, String movie_ID, String time, int ticketNumber, String region,
			boolean contin) throws MovieNotExist, JSONException, IOException, RegionSeatNotExist,
			ConSeqOfRowSeatNotExist, MovieIsNotExist, UserNotExist, CanNotWatchThisMovie, SeatNotEnough, NoContinuousSeat {
		Movie movie = robot.getMovie(movie_ID, time);
		ArrayList<String> s = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		User user = userlist.GetUser(user_ID);
		if (ticketNumber <= movie.getMovieRemainSeat()) {
			if (user.user_age >= movie.getClassification().getAgelimit()) {
				s = movie.setSeat(region, ticketNumber,contin);
				String moviename = movie.getMovieName();
				Classification c = movie.getClassification();
				String infor = movie.getInfor();
				String StartTime = movie.getTime();
				Hall hall = movie.getHall();
				for (int i = 0; i < s.size(); i++) {
					ticketid++;
					String ticketID = String.format("%03d", ticketid);
					ticketlist.add(new Ticket(ticketID, movie, s.get(i), c, infor, StartTime, hall));
					id.add(ticketID);
//					System.out.println(movie.getId());
				}
				System.out.println(movie.getId() + " 於 " + movie.getTime() + " 目前仍有 gray:" + movie.getHall().getNumOfGray()
						+" blue:"+movie.getHall().getNumOfBlue()+" yellow:"+movie.getHall().getNumOfYellow()
						+" red:"+movie.getHall().getNumOfRed());
			} else {
				Classification c = movie.getClassification();
				throw new CanNotWatchThisMovie("失敗, 該電影分級為" + c.getClassificaiton() + ", " + user.user_age + "歲無法購買");
			}
		} else {
			throw new SeatNotEnough("失敗," + movie.getId() + "於" + movie.getTime() + "座位數量不夠");
		}
		return id;
		// int SeatNum=robot.GetMovieRemainSeat(movie_ID, time);
		// User.setUser(user_ID);
		// robot.setMovieInfo(movie_ID);
		//
		// Classification cl= new Classification();
		// cl.setClassificaiton(robot.classification);
		// int limit=cl.getAgelimit();
		//
		//
		// if(SeatNum>0){
		// System.out.println(movie_ID+" 於 " + time+"目前仍有 "+ SeatNum +"個位子");
		// robot.getMovie(movie_ID, time).setSeat(region , ticketNumber);
		// }else if(SeatNum<0){
		// System.out.println("失敗。"+movie_ID +" 於 " + time+"座位數量不夠");
		// }else if(User.user_age < limit ){
		// System.out.println("失敗。該電影分級為 "+ cl.getClassificaiton() + " 級 ，"+
		// User.user_age +"歲無法購買");
		// }else if(robot.getMovie(movie_ID,time).getHall().getHallName()=="峨嵋"
		// || robot.getMovie(movie_ID,time).getHall().getHallName()=="崆峒"){
		// System.out.println("失敗，該電影放映廳為小廳，無法指定區域");
		// }
	}

	public ArrayList<String> ConditionalBooking(int user_ID, String movie_ID, String time, int ticketNumber, char row , 
			boolean contin)
			throws MovieNotExist, JSONException, IOException, RegionSeatNotExist, ConSeqOfRowSeatNotExist,
			MovieIsNotExist, UserNotExist, CanNotWatchThisMovie, SeatNotEnough, NoContinuousSeat {
		Movie movie = robot.getMovie(movie_ID, time);
		ArrayList<String> s = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		User user = userlist.GetUser(user_ID);
		if (ticketNumber <= movie.getMovieRemainSeat()) {
			if (user.user_age >= movie.getClassification().getAgelimit()) {
				s = movie.setSeat(row, ticketNumber , contin);
				String moviename = movie.getMovieName();
				Classification c = movie.getClassification();
				String infor = movie.getInfor();
				String StartTime = movie.getTime();
				Hall hall = movie.getHall();
				for (int i = 0; i < s.size(); i++) {
					ticketid++;
					String ticketID = String.format("%03d", ticketid);
					ticketlist.add(new Ticket(ticketID, movie, s.get(i), c, infor, StartTime, hall));
					id.add(ticketID);
//					System.out.println(movie.getId());
				}
				System.out.println(movie.getId() + "於" + movie.getTime() + "目前仍有" + movie.getMovieRemainSeat());
			} else {
				Classification c = movie.getClassification();
				throw new CanNotWatchThisMovie("失敗, 該電影分級為" + c.getClassificaiton() + ", " + user.user_age + "歲無法購買");
			}
		} else {
			throw new SeatNotEnough("失敗," + movie.getId() + "於" + movie.getTime() +" " +"座位數量不夠");
		}
		return id;
		// int SeatNum=robot.GetMovieRemainSeat(movie_ID, time);
		// User.setUser(user_ID);
		// robot.setMovieInfo(movie_ID);
		//
		// Classification cl= new Classification();
		// cl.setClassificaiton(robot.classification);
		// int limit=cl.getAgelimit();
		//
		//
		// if(SeatNum>0){
		// System.out.println(movie_ID+" 於 " + time+"目前仍有 "+ SeatNum +"個位子");
		// robot.getMovie(movie_ID, time).setSeat(row , ticketNumber);
		// }else if(SeatNum<0){
		// System.out.println("失敗。"+movie_ID +" 於 " + time+"座位數量不夠");
		// }else if(User.user_age < limit ){
		// System.out.println("失敗。該電影分級為 "+ cl.getClassificaiton() + " 級 ，"+
		// User.user_age +"歲無法購買");
		// }else if(robot.getMovie(movie_ID,time).getHall().getHallName()=="峨嵋"
		// || robot.getMovie(movie_ID,time).getHall().getHallName()=="崆峒"){
		// System.out.println("失敗，該電影放映廳為小廳，無法指定區域");
		// }
	}

	public void cancel(String MovieTicket_ID) throws MovieNotExist, CancelFailed { // 退票

		Date dNow = new Date();
		SimpleDateFormat formatter1 = new SimpleDateFormat("E yyyy.MM.dd '/ 'a hh:mm:ss  ");
		SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm");
		System.out.println("現在時刻: " + formatter1.format(dNow));

		Movie movie = this.getTicket(MovieTicket_ID).getMovie();
		ArrayList<String> seat = new ArrayList<String>();
		seat.add(MovieTicket_ID);

		// robot.setMovieInfo(MovieTicket_ID);

		String a = movie.getTime();
		String now = formatter2.format(dNow);
//		System.out.println("now="+now);
		String[] a_arr = a.split("：");
		String[] now_arr = now.split(":");
//		System.out.println("now_arr[0]="+now_arr[0]);
		int a_total = Integer.parseInt(a_arr[0]) * 60 + Integer.parseInt(a_arr[1]);
		int now_total = Integer.parseInt(now_arr[0]) * 60 + Integer.parseInt(now_arr[1]);

		try {
			if (this.isTicketValid(MovieTicket_ID)) {
				if (a_total - now_total >= 20) {
					// for(int i=0;i)
					movie.ResetSeatOccupied(seat);
					System.out.println("退票成功，全額退款");
					//
				} else {
					throw new CancelFailed("退票失敗，退票需於開場時間前20分鐘前");
//					System.out.println("退票失敗，退票需於開場時間前20分鐘前");
				}
			}
		} catch (CancelFailed e) {
			System.out.println(e.getMessage());
		}

	}
}
