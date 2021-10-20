package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AdmissionLetter_JSONDataReader {

	public String RequestName, SirName, SirIDNumber, JobName, Salary, Nationality,StartYear ,StartMonth, OTP, InvalidOTP;
	
	public void JsonReader_AdmissionLetterRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/AdmissionLetter_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			SirIDNumber = (String) user.get("SirIDNumber");
			System.out.println(SirIDNumber);
				
			SirName = (String) user.get("SirName");
			System.out.println(SirName);
			
			StartYear = (String) user.get("StartYear");
			System.out.println(StartYear);
		
			StartMonth = (String) user.get("StartMonth");
			System.out.println(StartMonth);
		
			JobName = (String) user.get("JobName");
			System.out.println(JobName);
			
			Nationality = (String) user.get("Nationality");
			System.out.println(Nationality);

			Salary = (String) user.get("Salary");
			System.out.println(Salary);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			
		
		}

	}


	
	

}
