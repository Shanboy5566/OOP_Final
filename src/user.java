import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;
import org.apache.commons.io.IOUtils;
public class user {
	public static String readJsonFile(String filename) throws IOException {
	    try (InputStream is = new FileInputStream(filename)) {
	        return IOUtils.toString(is, StandardCharsets.UTF_8);
	    }    
	}
	public static void main(String[] args) throws JSONException, IOException {
		JSONArray User = new JSONArray(readJsonFile("/Users/bruce0621/Documents/workspace/Final/src/user.json"));
		for(int i=0;i<User.length();i++){
			System.out.println(User.getJSONObject(i).get("index"));
			System.out.println(User.getJSONObject(i).get("name"));
			System.out.println(User.getJSONObject(i).get("age"));
		}

	}

}
