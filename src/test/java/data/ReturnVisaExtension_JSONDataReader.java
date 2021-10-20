package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReturnVisaExtension_JSONDataReader {

	public String  RequestName, ConsulateName, GuaranteedName, EndYear, EndMonth, OTP, InvalidOTP;
	
	public void JsonReader_ReturnVisaExtension() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/ReturnVisaExtension_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
				
			ConsulateName = (String) user.get("ConsulateName");
			System.out.println(ConsulateName);

			GuaranteedName = (String) user.get("GuaranteedName");
			System.out.println(GuaranteedName);
				
			EndYear = (String) user.get("EndYear");
			System.out.println(EndYear);
			
			EndMonth = (String) user.get("EndMonth");
			System.out.println(EndMonth);
				
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
		}

	}


	
	

}
