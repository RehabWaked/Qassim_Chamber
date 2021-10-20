package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EmbassyVisaExtensionLetterRequest_JSONDataReader {


	public String  CountryName, IdNumber, SirName, IdSource, Nationality, VisaTime, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/EmbassyVisaExtensionLetterRequest_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			CountryName = (String) user.get("CountryName");
			System.out.println(CountryName);
			
			IdNumber = (String) user.get("IdNumber");
			System.out.println(IdNumber);
			
			SirName = (String) user.get("SirName");
			System.out.println(SirName);
			
			IdSource = (String) user.get("IdSource");
			System.out.println(IdSource);
			
			Nationality = (String) user.get("Nationality");
			System.out.println(Nationality);
			
			VisaTime = (String) user.get("VisaTime");
			System.out.println(VisaTime);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
