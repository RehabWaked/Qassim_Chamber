package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DelegatingRepresentativeToSecretariat_JSONDataReader {


	public String Municipality, IdNumber, AuthorizedName, ReasonFor, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/DelegatingRepresentativeToSecretariat_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			Municipality = (String) user.get("Municipality");
			System.out.println(Municipality);
			
			IdNumber = (String) user.get("IdNumber");
			System.out.println(IdNumber);
			
			AuthorizedName = (String) user.get("AuthorizedName");
			System.out.println(AuthorizedName);
			
			ReasonFor = (String) user.get("ReasonFor");
			System.out.println(ReasonFor);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
