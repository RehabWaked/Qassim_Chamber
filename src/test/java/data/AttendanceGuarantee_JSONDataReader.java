package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AttendanceGuarantee_JSONDataReader {


	public String WorkRegion, MrName, Nationality, JobName, SirIDNumber, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/AttendanceGuarantee_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			WorkRegion = (String) user.get("WorkRegion");
			System.out.println(WorkRegion);
			
			MrName = (String) user.get("MrName");
			System.out.println(MrName);
			
			Nationality = (String) user.get("Nationality");
			System.out.println(Nationality);
			
			JobName = (String) user.get("JobName");
			System.out.println(JobName);
			
			SirIDNumber = (String) user.get("SirIDNumber");
			System.out.println(SirIDNumber);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
