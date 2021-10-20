package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExperienceCertificate_JSONDataReader {

	public String  RequestName, SirName, SirIDNumber, JobName, StartYear, StartMonth, OTP, InvalidOTP;
	
	public void JsonReader_ExperienceCertificate() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/ExperienceCertificate_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
				
			SirName = (String) user.get("SirName");
			System.out.println(SirName);

			SirIDNumber = (String) user.get("SirIDNumber");
			System.out.println(SirIDNumber);
				
			JobName = (String) user.get("JobName");
			System.out.println(JobName);
			
			StartYear = (String) user.get("StartYear");
			System.out.println(StartYear);
			
			StartMonth = (String) user.get("StartMonth");
			System.out.println(StartMonth);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
		}

	}


	
	

}
