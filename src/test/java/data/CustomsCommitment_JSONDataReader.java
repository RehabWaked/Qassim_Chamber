package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CustomsCommitment_JSONDataReader {

	public String RequestName, POBox, PhoneNo, FaxNo, IBANNo, ImportNo, CustomsName, OTP, InvalidOTP;
	
	public void JsonReader_CustomsCommitmentRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/CustomsCommitment_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			POBox = (String) user.get("POBox");
			System.out.println(POBox);
			

			PhoneNo = (String) user.get("PhoneNo");
			System.out.println(PhoneNo);
		
			FaxNo = (String) user.get("FaxNo");
			System.out.println(FaxNo);
			
			ImportNo = (String) user.get("ImportNo");
			System.out.println(ImportNo);

			CustomsName = (String) user.get("CustomsName");
			System.out.println(CustomsName);
		

			IBANNo = (String) user.get("IBANNo");
			System.out.println(IBANNo);
		
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			
		
		}

	}


	
	

}
