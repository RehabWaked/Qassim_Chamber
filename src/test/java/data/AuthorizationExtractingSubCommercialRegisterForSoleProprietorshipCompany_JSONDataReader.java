package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AuthorizationExtractingSubCommercialRegisterForSoleProprietorshipCompany_JSONDataReader {


	public String WorkRegion, OfficeName, OfficeServiceNumber, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/AuthorizationExtractingSubCommercialRegisterForSoleProprietorshipCompany_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			WorkRegion = (String) user.get("WorkRegion");
			System.out.println(WorkRegion);
					
			OfficeName = (String) user.get("OfficeName");
			System.out.println(OfficeName);
			
			OfficeServiceNumber = (String) user.get("OfficeServiceNumber");
			System.out.println(OfficeServiceNumber);
			
			OfficeServiceNumber = (String) user.get("OfficeServiceNumber");
			System.out.println(OfficeServiceNumber);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
