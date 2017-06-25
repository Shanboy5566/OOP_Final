package src;
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
 * 這個TicketOrderService提供
 * 1.一般訂票(Booking)
 * 2.條件訂票(ConditionalBooking)
 * 3.退票(cancel)
 * 4.查詢(inquiry)
 * 
 * @author Roy
 *
 */
public class TicketOrderService {
	
	private String user_ID;    //使用者ID
    private String movie_ID;    //電影ID
    private String movie;    //電影名稱
    private String time;        //開始時間
    private int time_minute;      //開始時間（幾分）
    private int ticketNumber;     //電影票張數
    
    private int MovieTicket_ID;   //最後被發行的電影票ID
    private HashMap<String, Ticket> User_ticket; //儲存所有的ID和ticket資料
    private HashMap<Integer, User> User_Info;
    MovieInfo robot;
    
    
    public TicketOrderService() throws JSONException, IOException{
    	robot = new MovieInfo();
    }


    public void Booking(int user_ID, String movie_ID, String time, int ticketNumber) throws JSONException, IOException, MovieNotExist {
    	
    	int SeatNum=robot.GetMovieRemainSeat(movie_ID, time);
    	User.setUser(user_ID);
    	robot.setMovieInfo(movie_ID);
    	
    	Classification cl= new Classification();
    	cl.setClassificaiton(robot.classification);
    	int limit=cl.getAgelimit();
    	
    	robot.movielist.get(2).getHall().getHallName();
    	
    	if(SeatNum>0){
    		System.out.println(movie_ID+" 於 " + time+"目前仍有 "+ SeatNum +"個位子");
    		robot.getMovie(movie_ID, time).setSeat(ticketNumber);
    	}else if(SeatNum<0){
    		System.out.println("失敗。"+movie_ID +" 於 " + time+"座位數量不夠");
    	}else if(User.user_age < limit ){
    		System.out.println("失敗。該電影分級為 "+ cl.getClassificaiton() + " 級 ，"+ User.user_age +"歲無法購買");
    	}
    }
     
    
    public void ConditionalBooking(int user_ID, String movie_ID, String time, int ticketNumber,String region) throws MovieNotExist, JSONException, IOException, RegionSeatNotExist, ConSeqOfRowSeatNotExist {

    	int SeatNum=robot.GetMovieRemainSeat(movie_ID, time);
    	User.setUser(user_ID);
    	robot.setMovieInfo(movie_ID);
    	
    	Classification cl= new Classification();
    	cl.setClassificaiton(robot.classification);
    	int limit=cl.getAgelimit();
    	
    	
    	if(SeatNum>0){
    		System.out.println(movie_ID+" 於 " + time+"目前仍有 "+ SeatNum +"個位子");
    			robot.getMovie(movie_ID, time).setSeat(region , ticketNumber);
    	}else if(SeatNum<0){
    		System.out.println("失敗。"+movie_ID +" 於 " + time+"座位數量不夠");
    	}else if(User.user_age < limit ){
    		System.out.println("失敗。該電影分級為 "+ cl.getClassificaiton() + " 級 ，"+ User.user_age +"歲無法購買");
    	}else if(robot.getMovie(movie_ID,time).getHall().getHallName()=="峨嵋" || robot.getMovie(movie_ID,time).getHall().getHallName()=="崆峒"){
    		System.out.println("失敗，該電影放映廳為小廳，無法指定區域");
    	}
    }
     
    public void ConditionalBooking(int user_ID, String movie_ID, String time, int ticketNumber,char row) throws MovieNotExist, JSONException, IOException, RegionSeatNotExist, ConSeqOfRowSeatNotExist {

    	int SeatNum=robot.GetMovieRemainSeat(movie_ID, time);
    	User.setUser(user_ID);
    	robot.setMovieInfo(movie_ID);
    	
    	Classification cl= new Classification();
    	cl.setClassificaiton(robot.classification);
    	int limit=cl.getAgelimit();
    	
    	
    	if(SeatNum>0){
    		System.out.println(movie_ID+" 於 " + time+"目前仍有 "+ SeatNum +"個位子");
    		robot.getMovie(movie_ID, time).setSeat(row , ticketNumber);
    	}else if(SeatNum<0){
    		System.out.println("失敗。"+movie_ID +" 於 " + time+"座位數量不夠");
    	}else if(User.user_age < limit ){
    		System.out.println("失敗。該電影分級為 "+ cl.getClassificaiton() + " 級 ，"+ User.user_age +"歲無法購買");
    	}else if(robot.getMovie(movie_ID,time).getHall().getHallName()=="峨嵋" || robot.getMovie(movie_ID,time).getHall().getHallName()=="崆峒"){
    		System.out.println("失敗，該電影放映廳為小廳，無法指定區域");
    	}
    }
    	
    public void cancel(String MovieTicket_ID) throws MovieNotExist {    //退票
    	
		Date dNow = new Date();
		SimpleDateFormat formatter1 = new SimpleDateFormat("E yyyy.MM.dd '/ 'a hh:mm:ss  ");
		SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm");
		System.out.println("現在時刻: " + formatter1.format(dNow));
		
		robot.setMovieInfo(MovieTicket_ID);
		
		String a = robot.time;
		String now =formatter2.format(dNow);
		String[] a_arr = a.split("：");
		String[] now_arr = now.split("：");
		int a_total=Integer.parseInt(a_arr[0])*60+Integer.parseInt(a_arr[1]);
		int now_total=Integer.parseInt(now_arr[0])*60+Integer.parseInt(now_arr[1]);
		
		try{
			robot.isMovieIdValid(MovieTicket_ID);
			if(now_total-a_total>=20){
				for(int i=0;i<)
				System.out.println("退票成功，全額退款");
				//
			}else{
				System.out.println("退票失敗，退票需於開場時間前20分鐘前");
			}}catch (MovieNotExist e){
			System.out.println(e.getMessage());
		}

    }
}

