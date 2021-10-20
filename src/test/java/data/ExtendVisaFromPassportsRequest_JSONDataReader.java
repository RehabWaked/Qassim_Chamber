package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExtendVisaFromPassportsRequest_JSONDataReader {


	public String  WorkRegion, IdNumber, SirName, Nationality, IdSource, JobName, VisaNo, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/ExtendVisaFromPassportsRequest_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			WorkRegion = (String) user.get("WorkRegion");
			System.out.println(WorkRegion);
			
			IdNumber = (String) user.get("IdNumber");
			System.out.println(IdNumber);
			
			SirName = (String) user.get("SirName");
			System.out.println(SirName);
			
			Nationality = (String) user.get("Nationality");
			System.out.println(Nationality);
			
			IdSource = (String) user.get("IdSource");
			System.out.println(IdSource);
			
			JobName = (String) user.get("JobName");
			System.out.println(JobName);
			
			VisaNo = (String) user.get("VisaNo");
			System.out.println(VisaNo);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
