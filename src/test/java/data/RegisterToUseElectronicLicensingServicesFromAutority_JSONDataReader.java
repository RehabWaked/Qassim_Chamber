package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RegisterToUseElectronicLicensingServicesFromAutority_JSONDataReader {


	public String SirName, IdNumber, JobTitle, IdentityIssuePlace, PhoneNumber, Email, PoBox, PostalCode, City,
	Street, District, BuildingNumber, ApartmentNumber, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/RegisterToUseElectronicLicensingServicesFromAutority_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			
			SirName = (String) user.get("SirName");
			System.out.println(SirName);
			
			IdNumber = (String) user.get("IdNumber");
			System.out.println(IdNumber);
			
			JobTitle = (String) user.get("JobTitle");
			System.out.println(JobTitle);
			
			IdentityIssuePlace = (String) user.get("IdentityIssuePlace");
			System.out.println(IdentityIssuePlace);
			
			PhoneNumber = (String) user.get("PhoneNumber");
			System.out.println(PhoneNumber);
			
			Email = (String) user.get("Email");
			System.out.println(Email);
			
			PoBox = (String) user.get("PoBox");
			System.out.println(PoBox);
			
			PostalCode = (String) user.get("PostalCode");
			System.out.println(PostalCode);
			
			City = (String) user.get("City");
			System.out.println(City);
			
			Street = (String) user.get("Street");
			System.out.println(Street);
			
			District = (String) user.get("District");
			System.out.println(District);
			
			BuildingNumber = (String) user.get("BuildingNumber");
			System.out.println(BuildingNumber);
			
			ApartmentNumber = (String) user.get("ApartmentNumber");
			System.out.println(ApartmentNumber);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
