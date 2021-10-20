package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PromotionalOffers_JSONDataReader {

	public String  RequestName, PromoName, Location, Notes, Reason, Year, StartMonth, EndMonth, OTP, InvalidOTP;
	
	public void JsonReader_PromotionalOffersRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/PromotionalOffers_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			PromoName = (String) user.get("PromoName");
			System.out.println(PromoName);
			

			Location = (String) user.get("Location");
			System.out.println(Location);
		
			Notes = (String) user.get("Notes");
			System.out.println(Notes);
			
			Reason = (String) user.get("Reason");
			System.out.println(Reason);

			Year = (String) user.get("Year");
			System.out.println(Year);
		

			StartMonth = (String) user.get("StartMonth");
			System.out.println(StartMonth);
		
			EndMonth = (String) user.get("EndMonth");
			System.out.println(EndMonth);
					
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			
		
		}

	}


	
	

}
