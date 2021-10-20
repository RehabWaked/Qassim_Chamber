package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MunicipalityServices_JSONDataReader {

	public String  RequestName, AuthName, IDNumber, RegionName, OTP, InvalidOTP;
	
	public void JsonReader_MunicipalityServicesRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/MunicipalityServices_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			IDNumber = (String) user.get("IDNumber");
			System.out.println(IDNumber);
				
			AuthName = (String) user.get("AuthName");
			System.out.println(AuthName);

			RegionName = (String) user.get("RegionName");
			System.out.println(RegionName);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			
		
		}

	}


	
	

}
