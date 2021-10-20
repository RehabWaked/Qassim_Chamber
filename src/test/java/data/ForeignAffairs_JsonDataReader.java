package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ForeignAffairs_JsonDataReader {

	public String RequestName, RequestNumber, Notes, Reason, OTP, OTPInValid;
	
	public void JsonReader_ForeignAffairsRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/ForeignAffairs_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			
			RequestNumber = (String) user.get("RequestNumber");
			System.out.println(RequestNumber);

			Notes = (String) user.get("Notes");
			System.out.println(Notes);
			
			Reason = (String) user.get("Reason");
			System.out.println(Reason);
			

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			

		}

	}


	
	

}
