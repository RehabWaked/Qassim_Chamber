package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VehicleDrivingAuthorization_JSONDataReader {


	public String  ManagerName, SirNameCar, IdNumber, CarNumber, CarType, AuthorizationPeriod, CarModel, 
	CarColor, ComputerNumber, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/VehicleDrivingAuthorization_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			ManagerName = (String) user.get("ManagerName");
			System.out.println(ManagerName);
			
			SirNameCar = (String) user.get("SirNameCar");
			System.out.println(SirNameCar);
			
			IdNumber = (String) user.get("IdNumber");
			System.out.println(IdNumber);
			
			CarNumber = (String) user.get("CarNumber");
			System.out.println(CarNumber);
			
			CarType = (String) user.get("CarType");
			System.out.println(CarType);
			
			AuthorizationPeriod = (String) user.get("AuthorizationPeriod");
			System.out.println(AuthorizationPeriod);
			
			CarModel = (String) user.get("CarModel");
			System.out.println(CarModel);
			
			CarColor = (String) user.get("CarColor");
			System.out.println(CarColor);
			
			ComputerNumber = (String) user.get("ComputerNumber");
			System.out.println(ComputerNumber);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}
}
