package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MinistryOfIntriorAffairs_JSONDataReader {


	public String ApplicantName, RequestNumber, ApplicantIDNumber, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/MinistryOfIntriorAffairs_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			ApplicantName = (String) user.get("ApplicantName");
			System.out.println(ApplicantName);
			
			RequestNumber = (String) user.get("RequestNumber");
			System.out.println(RequestNumber);
			
			ApplicantIDNumber = (String) user.get("ApplicantIDNumber");
			System.out.println(ApplicantIDNumber);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
