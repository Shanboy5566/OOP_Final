import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 這個Movie class描述了一部電影應該要有的屬性如下：
 * (1)電影id(String)
 * (2)電影名稱(String)
 * (3)電影連結(String)
 * (4)電影分級(Classification,自定義的class)
 * (5)電影簡介(String)
 * (6)電影片長(String)
 * (7)電影評價(String)
 * (8)電影播放時間(String)
 * (9)電影播放廳位(Hall.自訂的class)
 * 
 * PS.另準備 SmallHall以及BigHall兩個繼承Hall的class
 * 
 * @author bruce0621
 *
 */
public class Movie {
	
	
	private String Id;
	private String MovieName;
	private String Url;
	private Classification Classification;
	private String Descri;
	private String Infor;
	private String Score;
	private String time;
	private Hall Hall;
	private SmallHall SHall;
	private BigHall BHall;
	
//	public Movie(String MovieName) throws JSONException, IOException{
//		MovieInfo info = new MovieInfo();
//		ArrayList<Movie> movielist = info.movielist;
//		for(int i=0;i<movielist.size();i++){
//			if(movielist.get(i).getId().equals(MovieName)){
//				this.setId(movielist.get(i).getId());
//				this.setMovieName(movielist.get(i).getMovieName());
//				this.setUrl(movielist.get(i).getUrl());
//				this.setClassification(movielist.get(i).getClassification());
//				this.setDescri(movielist.get(i).getDescri());
//				this.setInfor(movielist.get(i).getInfor());
//				this.setScore(movielist.get(i).getScore());
//				this.setTime(movielist.get(i).getTime());
//			}
//		}
//	}
	/**
	 * 這是Movie這個class的建構子(constructor)
	 * 根據輸入的JSONObject，將其各個屬性變數塞入Movie這個class下的variable
	 * 需要注意的是第81行，會判斷爬出來的廳位是大廳或小廳
	 * 如果是大廳，則是更新BigHall;小廳的話則是更新SmallHall
	 * 
	 * @param home
	 * @throws JSONException
	 * @throws IOException
	 */
	public Movie(JSONObject home,String time) throws JSONException, IOException{
		this.setId(home.getString("id"));
		this.setMovieName(home.getString("movie"));
		this.setUrl(home.getString("url"));
		this.setClassification(new Classification(home.getString("classification")));
		this.setDescri(home.getString("descri"));
		this.setInfor(home.getString("infor"));
		this.setScore(home.getString("score"));
		this.setTime(time);
//		this.setTime(home.getString("time"));
		
//		if(home.getString("hall").equals(" 峨嵋 ")||home.getString("hall").equals(" 崆峒 ")){
//			this.setSHall(new SmallHall(home.getString("hall")));
//		}
//		else{
//			this.setBHall(new BigHall(home.getString("hall")));
//		}
		if(home.getString("hall").equals(" 峨嵋 ")||home.getString("hall").equals(" 崆峒 ")){
			this.setHall(new SmallHall(home.getString("hall")));
		}
		else{
			this.setHall(new BigHall(home.getString("hall")));
		}
		
	}
	public Movie(JSONObject home) throws JSONException, IOException{
		this.setId(home.getString("id"));
		this.setMovieName(home.getString("movie"));
		this.setUrl(home.getString("url"));
		this.setClassification(new Classification(home.getString("classification")));
		this.setDescri(home.getString("descri"));
		this.setInfor(home.getString("infor"));
		this.setScore(home.getString("score"));
//		this.setTime(time);
		this.setTime(home.getString("time"));
		
//		if(home.getString("hall").equals(" 峨嵋 ")||home.getString("hall").equals(" 崆峒 ")){
//			this.setSHall(new SmallHall(home.getString("hall")));
//		}
//		else{
//			this.setBHall(new BigHall(home.getString("hall")));
//		}
		if(home.getString("hall").equals(" 峨嵋 ")||home.getString("hall").equals(" 崆峒 ")){
			this.setHall(new SmallHall(home.getString("hall")));
		}
		else{
			this.setHall(new BigHall(home.getString("hall")));
		}
		
	}
	public ArrayList<String> setSeat(int num){
		return this.getHall().SetSeat(num);
	}
	public ArrayList<String> setSeat(String region , int num , boolean flag) throws RegionSeatNotExist, NoContinuousSeat{
		return this.getHall().SetSeat(region, num , flag);
	}
	public ArrayList<String> setSeat(char row , int num , boolean flag) throws RegionSeatNotExist, ConSeqOfRowSeatNotExist, NoContinuousSeat{
		return this.getHall().SetSeat(row, num , flag);
	}
	public boolean ResetSeatOccupied(ArrayList<String> seat){
		boolean flag = true;
//		System.out.println("seat="+seat);
//		System.out.println("seat size="+seat.size());
		for(int i=0;i<seat.size();i++){
			String s = seat.get(i);
			String[] tmp = s.split("_");
			char row_c = tmp[0].charAt(0);
			int row = row_c - 'A';
			int col = Integer.parseInt(tmp[1])-1;
			this.getHall().getSeat()[row][col].setOccupied(false);;
			int num = this.getHall().getSeatNum();
			int newnum = num + 1;
			this.getHall().setSeatNum(newnum);
//			System.out.println("row="+row_c+"col"+col);
//			System.out.println("i="+i);
//			System.out.println("region="+this.getHall().getSeat()[row][col].getRegoin());
			this.getHall().resetRegionNum(this.getHall().getSeat()[row][col].getRegoin());
		}
		return flag;
	}
	public int getMovieRemainSeat(){
		return this.getHall().getSeatNum();
	}
	public void getMovieInfo() {
		System.out.println("電影名稱:" + getMovieName());
		System.out.println("分級:" + getClassification());
		System.out.println("播映時間:" + getTime());
		System.out.println("廳位:" + getHallName());
	}
	private String getHallName() {
		return this.getHall().getHallName();
	}
	/**
	 * 提供外界取得電影id
	 * @return Id
	 */
	public String getId() {
		return Id;
	}
	/**
	 * 提供外界設定電影id
	 * @param id
	 */
	public void setId(String id) {
		Id = id;
	}
	/**
	 * 提供外界取得電影名稱
	 * @return MovieName
	 */
	public String getMovieName() {
		return MovieName;
	}
	/**
	 * 提供外界設定電影名稱
	 * @param movieName
	 */
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}
	/**
	 * 提供外界取得電影連結
	 * @return Url
	 */
	public String getUrl() {
		return Url;
	}
	/**
	 * 提供外界設定電影連結
	 * @param url
	 */
	public void setUrl(String url) {
		Url = url;
	}
	/**
	 * 提供外界取得電影簡介
	 * @return Descri
	 */
	public String getDescri() {
		return Descri;
	}
	/**
	 * 提供外界設定電影簡介
	 * @param descri
	 */
	public void setDescri(String descri) {
		Descri = descri;
	}
	/**
	 * 提供外界取得電影片長
	 * @return Infor
	 */
	public String getInfor() {
		return Infor;
	}
	/**
	 * 提供外界設定電影片長
	 * @param infor
	 */
	public void setInfor(String infor) {
		Infor = infor;
	}
	/**
	 * 提供外界取得電影評價
	 * @return Score
	 */
	public String getScore() {
		return Score;
	}
	/**
	 * 提供外界設定電影評價
	 * @param score
	 */
	public void setScore(String score) {
		Score = score;
	}
	/**
	 * 提供外界取得電影播放時間
	 * @return time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 提供外界設定電影播放時間
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * 提供外界取得電影播放廳位
	 * @return Hall
	 */
	public Hall getHall() {
		return Hall;
	}
	/**
	 * 提供外界設定電影播放廳位
	 * @param hall
	 */
	public void setHall(Hall hall) {
		Hall = hall;
	}
	/**
	 * 提供外界取得電影播放廳位
	 * @return SHall
	 */
	public SmallHall getSHall() {
		return SHall;
	}
	/**
	 * 提供外界設定電影播放廳位
	 * @param sHall
	 */
	public void setSHall(SmallHall sHall) {
		SHall = sHall;
	}
	/**
	 * 提供外界取得電影播放廳位
	 * @return BHall
	 */
	public BigHall getBHall() {
		return BHall;
	}
	/**
	 * 提供外界設定電影播放廳位
	 * @param bHall
	 */
	public void setBHall(BigHall bHall) {
		BHall = bHall;
	}
	/**
	 * 提供外界取得電影分級
	 * @return Classification
	 */
	public Classification getClassification() {
		return Classification;
	}
	/**
	 * 提供外界設定電影分級
	 * @param classification
	 */
	public void setClassification(Classification classification) {
		Classification = classification;
	}
	public int getGivenRegionRemainSeat(String region) {
//		System.out.println("region="+region);
//		System.out.println(this.getBHall());
		switch(region){
		case "gray":
			return this.getHall().getNumOfGray();
		case "blue":
			return this.getHall().getNumOfBlue();
		case "yellow":
			return this.getHall().getNumOfYellow();
		case "red":
			return this.getHall().getNumOfRed();
		}
		return 0;
	}
	
	
	
}
