import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;
import org.apache.commons.io.IOUtils;

/**
 * 這個MovieInfo Class會創建一個ArrayList，其中元素都是Movie的type
 * 根據助教提供的Movie_info.json去爬出所有的JSONObject，接著再從這些Object裡面擷取出各種屬性資訊 所以一旦New
 * MovieInfo這個Object，會output出一個電影的ArrayList
 * 
 * @author bruce0621
 *
 */
public class MovieInfo {
	public ArrayList<Movie> movielist = new ArrayList<Movie>();// 不為各個播放時間new hall
	public ArrayList<Movie> movie = new ArrayList<Movie>();// 為每個播放時間new hall

	/**
	 * 這是一個JSON的爬蟲，負責把JSON檔裡頭的資訊爬出來
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public String readJsonFile(String filename) throws IOException {
		try (InputStream is = new FileInputStream(filename)) {
			return IOUtils.toString(is, StandardCharsets.UTF_8);
		}
	}

	/**
	 * 將JSON檔爬出來的結果一一建造各個Movie，再塞入movielist中
	 * 
	 * @throws JSONException
	 * @throws IOException
	 */
	public MovieInfo() throws JSONException, IOException {
		JSONArray arr = new JSONArray(readJsonFile("/Users/bruce0621/Documents/workspace/Final/src/movie_info.json"));

		for (int i = 0; i < arr.length(); i++) {
			JSONObject home = arr.getJSONObject(i);
			String time = home.getString("time").trim();
			String[] tmp = time.split("、");
			for (int j = 0; j < tmp.length; j++) {
				movie.add(new Movie(home, tmp[j]));
			}
			movielist.add(new Movie(home));
		}
	}

	/**
	 * 輸入電影id，輸出該電影的名稱、分級、播放時間、廳位資訊 如果輸入的電影id無效，則key值不會被更新，會是default值-1
	 * 
	 * @param id
	 * @throws MovieIsNotExist 
	 */
	public void getMovieInfo(String id) throws MovieIsNotExist {
		int key = -1;
		for (int i = 0; i < movielist.size(); i++) {
			// System.out.println("i="+i+" movieid="+movielist.get(i).getId());
			if (movielist.get(i).getId().equals(id)) {
				key = i;
			}
		}
		if (key == -1) {
			throw new MovieIsNotExist("The movie is not exist");
		}
		System.out.println("電影名稱：" + movielist.get(key).getMovieName());
		System.out.println("分級：" + movielist.get(key).getClassification());
		System.out.println("播映時間：" + movielist.get(key).getTime());
		System.out.println("廳位：" + movielist.get(key).getHall().getHallName());
	}

	/**
	 * 輸入電影id，output是對應該id的電影Object
	 * 
	 * @param id
	 * @return Movie
	 * @throws MovieIsNotExist 
	 */
	public Movie getMovie(String id, String time) throws MovieIsNotExist {
		int key = -1;
		for (int i = 0; i < movie.size(); i++) {
			// System.out.println("i="+i+" movieid="+movielist.get(i).getId());
			if (movie.get(i).getId().equals(id)) {
				if (movie.get(i).getTime().equals(time)) {
					key = i;
				}
			}
		}
		if (key == -1) {
			throw new MovieIsNotExist("The movie is not exist");
		}
		Movie Rmovie = movie.get(key);
		return Rmovie;
	}

	/**
	 * 輸入指定分數，output出所有大於等於該指定分數的電影名稱
	 * 
	 * @param score
	 */
	public void getGreaterScoreMovie(double score) {
		// String[] ScoreArr = null;
		double score1 = 0;
		double score2 = 0;
		for (int i = 0; i < movielist.size(); i++) {
			String[] ScoreArr = movielist.get(i).getScore().split("/");
			score1 = Double.parseDouble(ScoreArr[0]);
			score2 = Double.parseDouble(ScoreArr[1]);
			if (score < 10) {
				if (score1 >= score) {
					if(i==movielist.size()-1){
						System.out.print(movielist.get(i).getId());
					}
					else{
						System.out.print(movielist.get(i).getId() + ", ");
					}
				}
			} else {
				if (score2 >= score) {
					if(i==movielist.size()-1){
						System.out.print(movielist.get(i).getId());
					}
					else{
						System.out.print(movielist.get(i).getId() + ", ");
					}
				}
			}
		}
		// double score1 = Double.parseDouble(ScoreArr[0]);
		// double score2 = Double.parseDouble(ScoreArr[1]);

	}

	/**
	 * 輸入指定電影id，output出該電影剩餘座位數
	 * 
	 * @param id
	 * @return seat
	 * @throws MovieNotExist
	 */
	public int GetMovieRemainSeat(String id, String time) throws MovieNotExist {// work
																				// here
																				// 6/24
		int key = -1;
		for (int i = 0; i < movie.size(); i++) {
			// System.out.println("i="+i+" movieid="+movielist.get(i).getId());
			if (movie.get(i).getId().equals(id)) {
				if (movie.get(i).getTime().equals(time)) {
					key = i;
				}
			}
		}
		if (key == -1) {
			throw new MovieNotExist("The movie is not exist");
			// System.out.println("The movie is not exist");
		}
		int seat = movie.get(key).getHall().getSeatNum();
		return seat;
	}
	/**
	 * 提供查詢之功能，輸入需求座位數, 指定條件[最早/最晚播映時間, 最大/最小片長]
	 * 將符合指定條件的電影ID、播放場次時間印出
	 * @param NumOfseat
	 * @param EarlyTime
	 * @param LastTime
	 * @param MaxInfor
	 * @param MinInfor
	 */
	public void GetGivenMovieList(int NumOfseat, String EarlyTime, String LastTime, String MaxInfor, String MinInfor) {
		int EHour;
		int LHour;
		int EMin;
		int LMin;
		int Maxinfor;
		int Mininfor;

		String[] ETime = EarlyTime.split(":");
		String[] LTime = LastTime.split(":");
		String[] MInfor = MaxInfor.split("分");
		String[] mInfor = MinInfor.split("分");

		EHour = Integer.parseInt(ETime[0]);
		LHour = Integer.parseInt(LTime[0]);
		EMin = Integer.parseInt(ETime[1]);
		LMin = Integer.parseInt(LTime[1]);
		
		Maxinfor = Integer.parseInt(MInfor[0]);
		Mininfor = Integer.parseInt(mInfor[0]);

		for (int i = 0; i < movielist.size(); i++) {
			int key = movielist.get(i).getInfor().indexOf('分');
			int Infor = Integer.parseInt(movielist.get(i).getInfor().substring(5, key));
			String[] Time = movielist.get(i).getTime().split("、");

			int hour;
			int min;
			if (Infor >= Mininfor && Infor <= Maxinfor) {
				for (int j = 0; j < Time.length; j++) {
					// System.out.println("Time[j]="+Time[j]);
					Time[j] = Time[j].trim();
					// System.out.println("Time[j]="+Time[j]);
					String[] time = Time[j].split("：");
					// String[] time = Time[j].split(":");
					// time[0] = time[0].trim();
					// System.out.println("time[0]="+time[0]);
					if (time[0].charAt(0) == '0') {
						time[0] = time[0].substring(1);
					}
					// System.out.println("time[0]="+time[0]);
					hour = Integer.parseInt(time[0]);
					min = Integer.parseInt(time[1]);
					// System.out.println("hour="+hour);
					// System.out.println("min="+min);
					// System.out.println("EHour="+EHour);
					// System.out.println("LHour="+LHour);
					// System.out.println("EMin="+EMin);
					// System.out.println("LMin="+LMin);
					if (hour >= EHour && min >= EMin) {
						if (hour < LHour) {
							if (NumOfseat < movielist.get(i).getHall().getSeatNum()) {
//								System.out.println("hall=" + movielist.get(i).getHall().getHallName());
//								System.out.println("seatNum=" + movielist.get(i).getHall().getSeatNum());
								System.out.println(movielist.get(i).getId() + ", " + Time[j]);
							}
						} else if (hour == LHour) {
							if (min < LMin) {
								if (NumOfseat < movielist.get(i).getHall().getSeatNum()) {
//									System.out.println("hall=" + movielist.get(i).getHall().getHallName());
//									System.out.println("seatNum=" + movielist.get(i).getHall().getSeatNum());
									System.out.println(movielist.get(i).getId() + ", " + Time[j]);
								}
							}
						}
					}

				}
			}
		}

	}
	/**
	 * 提供查詢限定排數座位尚有票的電影列表，輸入需求座位數, 指定列數
	 * ，輸出符合條件的所有電影ID、播放場次
	 * @param NumOfseat
	 * @param row
	 * @throws MovieIsNotExist
	 */
	public void GetMovieOfGivenRow(int NumOfseat, char row) throws MovieIsNotExist {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		String s = "";
		int k = 0;
		for (int i = 0; i < movie.size(); i++) {
			if (NumOfseat <= movie.get(i).getHall().NumOfEmptySeatOfRow(row)) {
				tmp.add(i);
			}
		}
		if(tmp.size()==0){
			throw new MovieIsNotExist("The movie is not exist");
		}
		for (int i = 0; i < tmp.size(); i++) {
			if (!s.equals(movie.get(tmp.get(i)).getId())) {
				if (k == 0) {
					System.out.print(movie.get(tmp.get(i)).getId() + ", ");
					s = movie.get(tmp.get(i)).getId();
					k = 1;
				} else {
					System.out.println();
					System.out.print(movie.get(tmp.get(i)).getId() + ", ");
					s = movie.get(tmp.get(i)).getId();
				}
			}
			if(i!=tmp.size()-1){
				if (!s.equals(movie.get(i + 1).getId())) {
					System.out.print(movie.get(tmp.get(i)).getTime());
				} else {
					System.out.print(movie.get(tmp.get(i)).getTime() + ", ");
				}
			}
			else{
				System.out.print(movie.get(tmp.get(i)).getTime());
			}
		}
	}
	/**
	 * 提供查詢限定區域座位尚有票的電影列表，輸入需求座位數, 指定區域
	 * ，輸出符合條件的所有電影ID、播放場次
	 * @param NumOfseat
	 * @param region
	 * @throws MovieIsNotExist
	 */
	public void GetMovieOfGivenRegion(int NumOfseat, String region) throws MovieIsNotExist{
		ArrayList<Integer> tmp = new ArrayList<Integer>();
//		System.out.println("list size="+tmp.size());
		String s = "";
		int k =0;
		for(int i=0;i<movie.size();i++){
			if(movie.get(i).getHall().getHallName().equals(" 武當 ")||
					movie.get(i).getHall().getHallName().equals(" 少林 ")||
					movie.get(i).getHall().getHallName().equals(" 華山 ")){
//				System.out.println("here");
//				System.out.println("remain seat="+movie.get(i).getMovieRemainSeat());
				if(NumOfseat<=movie.get(i).getMovieRemainSeat()){
					if(NumOfseat<=movie.get(i).getGivenRegionRemainSeat(region)){
						tmp.add(i);
					}
				}
			}
		}
		if(tmp.size()==0){
			throw new MovieIsNotExist("The movie is not exist");
		}
//		System.out.println("tmp="+tmp);
		for (int i = 0; i < tmp.size(); i++) {
			if (!s.equals(movie.get(tmp.get(i)).getId())) {
				if (k == 0) {
					System.out.print(movie.get(tmp.get(i)).getId() + ", ");
					s = movie.get(tmp.get(i)).getId();
					k = 1;
				} else {
					System.out.println();
					System.out.print(movie.get(tmp.get(i)).getId() + ", ");
					s = movie.get(tmp.get(i)).getId();
				}
			}
//			System.out.println(s);
//			System.out.println(movie.get(tmp.get(i+1)).getId());
			if(i!=tmp.size()-1){
				if (!s.equals(movie.get(tmp.get(i+1)).getId())) {
					System.out.print(movie.get(tmp.get(i)).getTime());
				} else {
					System.out.print(movie.get(tmp.get(i)).getTime() + ", ");
				}
			}
			else{
				System.out.print(movie.get(tmp.get(i)).getTime());
			}
		}
		
		
	}
	/**
	 * 提供一個驗證電影是否存在的方法，輸入指定電影ID
	 * ，若存在則return ture，反之。
	 * @param id
	 * @return flag
	 * @throws MovieNotExist
	 */
	public boolean isMovieIdValid(String id) throws MovieNotExist{
		boolean flag = false;
		for(int i=0;i<movielist.size();i++){
			if(movielist.get(i).getId().equals(id)){
				flag = true;
			}
		}
		if(flag==false){
			throw new MovieNotExist("The movie is not exist");
		}
		return flag;
	}
}
