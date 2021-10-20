package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GeneralData_JSONDataReader {


	public String userID, password, newPass, ForgetPassOTP, ForgetPassWrongOTP,wrongUserID , WrongUserID1, WrongUserID2,
	wrongPassword, phoneNumber, OTP, wrongOTP, OpenRequestText ,AdminUsername , AdminPassword, RejectReasonText, ValidMail,
	InValidMail, StopReason, CancelReason;

	public void jsonReader_Login() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;


			userID = (String) user.get("userID");
			System.out.println(userID);

			password = (String) user.get("password");
			System.out.println(password);
			
			newPass = (String) user.get("newPass");
			System.out.println(newPass);

			ForgetPassOTP = (String) user.get("ForgetPassOTP");
			System.out.println(ForgetPassOTP);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);

			phoneNumber = (String) user.get("phoneNumber");
			System.out.println(phoneNumber);

			OpenRequestText = (String) user.get("OpenRequestText");
			System.out.println(OpenRequestText);
		
			ValidMail = (String) user.get("ValidMail");
			System.out.println(ValidMail);
			
			InValidMail = (String) user.get("InValidMail");
			System.out.println(InValidMail);
			
			StopReason = (String) user.get("StopRequestReason");
			System.out.println(StopReason);
			
			CancelReason = (String) user.get("CancelReason");
			System.out.println(CancelReason);
		}

	}


	public void jsonReader_ErrorLogin() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;

			userID = (String) user.get("userID");
			System.out.println(userID);

			wrongUserID = (String) user.get("wrongUserID");
			System.out.println(wrongUserID);

			WrongUserID1 = (String) user.get("WrongUserID1");
			System.out.println(WrongUserID1);

			WrongUserID2 = (String) user.get("WrongUserID2");
			System.out.println(WrongUserID2);

			wrongPassword = (String) user.get("wrongPassword");
			System.out.println(wrongPassword);

			ForgetPassOTP = (String) user.get("ForgetPassOTP");
			System.out.println(ForgetPassOTP);

			ForgetPassWrongOTP = (String) user.get("ForgetPassWrongOTP");
			System.out.println(ForgetPassWrongOTP);

			wrongOTP = (String) user.get("wrongOTP");
			System.out.println(wrongOTP);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
	
		}

	}

	public void jsonReader_AdminLogin() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;


			AdminUsername = (String) user.get("AdminUsername");
			System.out.println(AdminUsername);

			AdminPassword = (String) user.get("AdminPassword");
			System.out.println(AdminPassword);
			
			RejectReasonText = (String) user.get("RejectReasonText");
			System.out.println(RejectReasonText);
		}

	}


}
