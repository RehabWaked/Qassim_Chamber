package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TrafficServices_JSONDataReader {

	public String RequestName, WorkRegion, OfficeName, OfficeServiceNumber, SirName, SirIDNumber, Nationality, OTP,
	InvalidOTP;
	
	public void JsonReader_TrafficServicesRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/TrafficServices_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			WorkRegion = (String) user.get("WorkRegion");
			System.out.println(WorkRegion);
			
			SirName = (String) user.get("SirName");
			System.out.println(SirName);

			OfficeName = (String) user.get("OfficeName");
			System.out.println(OfficeName);
			
			OfficeServiceNumber = (String) user.get("OfficeServiceNumber");
			System.out.println(OfficeServiceNumber);
			
			SirIDNumber = (String) user.get("SirIDNumber");
			System.out.println(SirIDNumber);
			
			Nationality = (String) user.get("Nationality");
			System.out.println(Nationality);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
		}

	}
}
