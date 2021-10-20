package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NICScopeModel_JSONDataReader {

	public String RequestName, BandName, AdminName, AdminMobile, AdminMail, OTP, InvalidOTP;
	
	public void JsonReader_NICScopeModelRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/NICScopeModel_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			BandName = (String) user.get("BandName");
			System.out.println(BandName);
				
			AdminName = (String) user.get("AdminName");
			System.out.println(AdminName);

			AdminMobile = (String) user.get("AdminMobile");
			System.out.println(AdminMobile);
			
			AdminMail = (String) user.get("AdminMail");
			System.out.println(AdminMail);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			
		
		}

	}


	
	

}
