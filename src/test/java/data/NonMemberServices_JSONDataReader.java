package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NonMemberServices_JSONDataReader {


	public String PageNumber , RequestReason;;

	
	public void jsonReader_NonMemberOpenFile() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/NonMemberOpenFileData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			PageNumber = (String) user.get("PageNumber");
			System.out.println(PageNumber);
			
			RequestReason = (String) user.get("RequestReason");
			System.out.println(RequestReason);
		}

	}
}
