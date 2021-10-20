package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PledgeOfConductAndMoralsRequest_JSONDataReader {


	public String  NameAr, NameEn, PositionAr, PositionEn, VendorNo, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/PledgeOfConductAndMoralsRequest_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			NameAr = (String) user.get("NameAr");
			System.out.println(NameAr);
					
			NameEn = (String) user.get("NameEn");
			System.out.println(NameEn);
			
			PositionAr = (String) user.get("PositionAr");
			System.out.println(PositionAr);
			
			PositionEn = (String) user.get("PositionEn");
			System.out.println(PositionEn);
			
			VendorNo = (String) user.get("VendorNo");
			System.out.println(VendorNo);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
