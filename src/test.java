import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class test {

	public static void main(String[] args) throws JSONException, IOException, MovieNotExist, RegionSeatNotExist, ConSeqOfRowSeatNotExist, MovieIsNotExist {
//		SmallHall small = new SmallHall("1");
//		BigHall big = new BigHall("2");
////		big.SetSeat('L', 2);
//		big.SetSeat("red", 2);
		
//		System.out.println("small[0][9] occupied = " + small.seat[0][9].isOccupied());
//		small.seat[0][9].setOccupied(true);
//		System.out.println("small[0][9] occupied = " + small.seat[0][9].isOccupied());
//		
//		System.out.println("big[0][9] id = " + big.seat[0][9].getId());
//		System.out.println("big[0][9] occupied = "+big.seat[0][9].isOccupied());
//		big.seat[0][9].setOccupied(true);
//		System.out.println("big[0][9] occupied = "+big.seat[0][9].isOccupied());
		
	
//		System.out.println("1valid = "+big.CheckSeatValid('A', 4));
//		big.SetSeat('A', 4);
////		System.out.println("2valid = "+big.CheckSeatValid('A', 4));
//		
////		System.out.println("3valid = "+small.CheckSeatValid('A', 4));
//		System.out.println("4occupied = "+small.CheckSeatOccupied('A', 4));
//		System.out.println("7id = "+small.CheckSeatId('A', 4));
//		System.out.println("the number of seats="+small.getSeatNum());
//		small.SetSeat('A', 4);
//		System.out.println("the number of seats="+small.getSeatNum());
////		System.out.println("5valid = "+small.CheckSeatValid('A', 4));
//		System.out.println("6occupied = "+small.CheckSeatOccupied('A', 4));
//		
//		MovieInfo info = new MovieInfo();
//		System.out.println("Hall:"+info.movielist.get(2).getHall().getHallName());
////		System.out.println(info.movielist.get(2).getBHall().HallName);
//		
//		System.out.println("movieinfo:");
//		info.getMovieInfo("3R47wXhjjLqnLWef6HU155ek");
//		info.getGreaterScoreMovie(7.5);
//		
//		
//		System.out.println("the number of seat="+info.getMovie("3R47wXhjjLqnLWef6HU155ek").getHall().getSeatNum());
//		info.getMovie("3R47wXhjjLqnLWef6HU155ek").getHall().SetSeat('A', 8);
//		System.out.println("the number of seat="+info.getMovie("3R47wXhjjLqnLWef6HU155ek").getHall().getSeatNum());
//		
		MovieInfo info2 = new MovieInfo();
//		System.out.println("seatnum1="+info2.getMovie("H55WYTppJHIwZyCfUHJLIbWC", "11：30").getHall().getSeatNum());
//		info2.getMovie("H55WYTppJHIwZyCfUHJLIbWC", "11：30").getHall().SetSeat("red", 24);
//		System.out.println("seatnum2="+info2.getMovie("H55WYTppJHIwZyCfUHJLIbWC", "11：30").getMovieRemainSeat());
//		info2.getMovie("H55WYTppJHIwZyCfUHJLIbWC", "11：30").getMovieInfo();
//		info2.getMovie("H55WYTppJHIwZyCfUHJLIbWC", "11：30").setSeat("red", 24);
//		System.out.println("seatnum3="+info2.getMovie("H55WYTppJHIwZyCfUHJLIbWC", "11：30").getMovieRemainSeat());
		
//		System.out.println("seatnum4="+info2.getMovie("pYXEwQEWFBBFarOD5LBJmnTU", "17：50").getMovieRemainSeat());
//		info2.getMovie("pYXEwQEWFBBFarOD5LBJmnTU", "17：50").getMovieInfo();
//		info2.getMovie("pYXEwQEWFBBFarOD5LBJmnTU", "17：50").setSeat('E', 4);
//		System.out.println("seatnum5="+info2.getMovie("pYXEwQEWFBBFarOD5LBJmnTU", "17：50").getMovieRemainSeat());
//		System.out.println("seatnum4="+info2.getMovie("H55WYTppJHIwZyCfUHJLIbWC", "11：30").getHall().getSeatNum());
//		System.out.println(info2.getMovie("H55WYTppJHIwZyCfUHJLIbWC", "11：30").getClassification());
//		info2.GetGivenMovieList(2, "20:00" , "22:00" , "180分鐘" , "0分鐘");
//		info2.GetMovieOfGivenRow(8, 'L');
//		info2.GetMovieOfGivenRow(8, 'L');
//		info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").setSeat(2);
//		info2.getMovie("WGd6f01Om27eSmo9X3b6cuXu", "09：10").setSeat(2);
		
		System.out.println("remain seat="+info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").getMovieRemainSeat());
		ArrayList<String> s = info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").setSeat("red", 24);
		System.out.println("your seat is "+s);
		System.out.println("remain seat="+info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").getMovieRemainSeat());
		System.out.println("Is reset valid? : "+info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").ResetSeatOccupied(s));
		System.out.println("remain seat="+info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").getMovieRemainSeat());
		
//		System.out.println(info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").setSeat(2));
//		System.out.println(info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").setSeat('A', 2));
		
		
		
//		System.out.println(info2.getMovie("WGd6f01Om27eSmo9X3b6cuXu", "09：10").setSeat(2));
//		System.out.println(info2.getMovie("WGd6f01Om27eSmo9X3b6cuXu", "09：10").setSeat('A', 2));
		
		info2.GetMovieOfGivenRegion(24,"red");
//		System.out.println();
//		System.out.println();
//		info2.GetMovieOfGivenRow(8, 'L');
		
//		System.out.println(info2.getMovie("3R47wXhjjLqnLWef6HU155ek", "09：40").setSeat(2));
//		System.out.println();
//		System.out.println(info2.isMovieIdValid("3R47wXhjjLqnLWef6HU155ek"));
//		System.out.println(info2.isMovieIdValid("3R47wXhjjLqnLWef6HU155e"));
//		int num = info2.GetMovieRemainSeat("3R47wXhjjLqnLWef6HU155ek","09：40");
//		System.out.println("seat="+num);
//		info2.getMovie("3R47wXhjjLqnLWef6HU155ek","09：40").getHall().SetSeat("red", 2);
		
//		String a = "10：10";
//		String now = "09：50";
//		String[] a_arr = a.split("：");
//		String[] now_arr = now.split("：");
//		int min = (Integer.parseInt(now_arr[1])+20)%60;
//		int hour = Integer.parseInt(now_arr[0]) + ((Integer.parseInt(now_arr[1])+20)/60);
//		int min_start = Integer.parseInt(a_arr[1]);
//		int hour_start = Integer.parseInt(a_arr[0]);
//		
//		boolean flag = false;
//		if(hour<hour_start){
//			flag = true;
//		}
//		else if(hour == hour_start){
//			if(min <= min_start){
//				flag = true;
//			}
//		}
//		else{
//			flag = false;
//		}
//		
//		System.out.println(flag);
		
		
//		System.out.println();
		
		
	}

}
