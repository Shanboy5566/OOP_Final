import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;
import org.apache.commons.io.IOUtils;
public class User {
	public  int user_ID;
	public  String user_name;
	public  int user_age;
	public ArrayList<User> userlist = new ArrayList<User>();
	public static String readJsonFile(String filename) throws IOException {
	    try (InputStream is = new FileInputStream(filename)) {
	        return IOUtils.toString(is, StandardCharsets.UTF_8);
	    }    
	}
	
	public User() throws JSONException, IOException{
		JSONArray user = new JSONArray(readJsonFile("/Users/bruce0621/Documents/workspace/Final/src/user.json"));
		for(int i = 0;i<user.length();i++){
			user_ID=user.getJSONObject(i).getInt("index");
			user_name=user.getJSONObject(i).getString("name");
			user_age=user.getJSONObject(i).getInt("age");
			userlist.add(new User(user_ID,user_name,user_age));
		}
	}
	
	public User(int user_ID, String user_name, int user_age) {
		this.user_ID = user_ID;
		this.user_name = user_name;
		this.user_age = user_age;
	}

	public User GetUser(int userId) throws UserNotExist{
		int key = -1;
		for(int i = 0;i<userlist.size();i++){
			if(userlist.get(i).user_ID == userId){
				key = i;
			}
		}
		if(key==-1){
			throw new UserNotExist("User is not exist");
		}
		return userlist.get(key);
	}
	
//	public static void main(String[] args) throws JSONException, IOException {
//		JSONArray User = new JSONArray(readJsonFile("/Users/bruce0621/Documents/workspace/Final/src/user.json"));
//		for(int i=0;i<User.length();i++){
//			System.out.println(User.getJSONObject(i).get("index"));
//			System.out.println(User.getJSONObject(i).get("name"));
//			System.out.println(User.getJSONObject(i).get("age"));
//		}
//
//	}

}
