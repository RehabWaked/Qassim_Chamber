package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FoodAndDrugs_JSONDataReader {

	public String RequestName, Name, JobTitle, Email, IDNumber, TelNo, MobNo, Address, BuildNo, DistrictName, CityName,
	StreetName, PostalCode, UnitNo, AdditionalMobNo, OTP, InvalidOTP;
	
	public void JsonReader_FoodAndDrugsOrg() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/FoodAndDrugs_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			Name = (String) user.get("Name");
			System.out.println(Name);
			
			JobTitle = (String) user.get("JobTitle");
			System.out.println(JobTitle);

			Email = (String) user.get("Email");
			System.out.println(Email);
			
			IDNumber = (String) user.get("IDNumber");
			System.out.println(IDNumber);
			
			TelNo = (String) user.get("TelNo");
			System.out.println(TelNo);

			MobNo = (String) user.get("MobNo");
			System.out.println(MobNo);
			
			Address = (String) user.get("Address");
			System.out.println(Address);
			
			BuildNo = (String) user.get("BuildNo");
			System.out.println(BuildNo);
			
			DistrictName = (String) user.get("DistrictName");
			System.out.println(DistrictName);
			
			StreetName = (String) user.get("StreetName");
			System.out.println(StreetName);
			CityName = (String) user.get("CityName");
			System.out.println(CityName);
			PostalCode = (String) user.get("PostalCode");
			System.out.println(PostalCode);
			
			UnitNo = (String) user.get("UnitNo");
			System.out.println(UnitNo);
			
			AdditionalMobNo = (String) user.get("AdditionalMobNo");
			System.out.println(AdditionalMobNo);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			


		}

	}


	
	

}
