package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CompanyRepresentativeDataRegisterOnSaudiElectricSite_JSONDataReader {


	public String  BusinessPartnerNumber, Title, FirstName, LastName, Position, Language, PhoneNumber,
	MobileNumber,Fax, Email, Country, AuthorizedName, AuthorizedPosition, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/CompanyRepresentativeDataRegisterOnSaudiElectricSite_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			BusinessPartnerNumber = (String) user.get("BusinessPartnerNumber");
			System.out.println(BusinessPartnerNumber);
			
			Title = (String) user.get("Title");
			System.out.println(Title);
			
			FirstName = (String) user.get("FirstName");
			System.out.println(FirstName);
			
			LastName = (String) user.get("LastName");
			System.out.println(LastName);
			
			Position = (String) user.get("Position");
			System.out.println(Position);
			
			Language = (String) user.get("Language");
			System.out.println(Language);
			
			PhoneNumber = (String) user.get("PhoneNumber");
			System.out.println(PhoneNumber);
			
			MobileNumber = (String) user.get("MobileNumber");
			System.out.println(MobileNumber);
			
			Fax = (String) user.get("Fax");
			System.out.println(Fax);

			Email = (String) user.get("Email");
			System.out.println(Email);
			
			Country = (String) user.get("Country");
			System.out.println(Country);
			
			AuthorizedName = (String) user.get("AuthorizedName");
			System.out.println(AuthorizedName);
			
			AuthorizedPosition = (String) user.get("AuthorizedPosition");
			System.out.println(AuthorizedPosition);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
