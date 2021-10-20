package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CloudComputing_JSONDataReader {

	public String RequestName, SirName, IDNumber, JobTitle, IDIssuePlace, MobileNo, Email, POBox, PostalCode, City, Street, District, BuildingNo, AppartNo, OTP, InvalidOTP;
	
	public void JsonReader_CloudComputingRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/CloudComputing_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			IDNumber = (String) user.get("IDNumber");
			System.out.println(IDNumber);
				
			SirName = (String) user.get("SirName");
			System.out.println(SirName);
			
			JobTitle = (String) user.get("JobTitle");
			System.out.println(JobTitle);
			
		
			IDIssuePlace = (String) user.get("IDIssuePlace");
			System.out.println(IDIssuePlace);
		
			MobileNo = (String) user.get("MobileNo");
			System.out.println(MobileNo);
			
			Email = (String) user.get("Email");
			System.out.println(Email);

			POBox = (String) user.get("POBox");
			System.out.println(POBox);
			

			PostalCode = (String) user.get("PostalCode");
			System.out.println(PostalCode);
		
			City = (String) user.get("City");
			System.out.println(City);
			
			Street = (String) user.get("Street");
			System.out.println(Street);

			District = (String) user.get("District");
			System.out.println(District);
		

			BuildingNo = (String) user.get("BuildingNo");
			System.out.println(BuildingNo);
		
			AppartNo = (String) user.get("AppartNo");
			System.out.println(AppartNo);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			
		
		}

	}


	
	

}
