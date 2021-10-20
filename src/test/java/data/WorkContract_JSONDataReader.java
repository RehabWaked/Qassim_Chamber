package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WorkContract_JSONDataReader {

	public String RequestName, ContractDuration, SirName, EmployeeName, SirIDNumber, PassportNumber, JobName, Salary, Nationality, VacationDays, OTP, InvalidOTP;
	
	public void JsonReader_WorkContractRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/WorkContract_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			ContractDuration = (String) user.get("ContractDuration");
			System.out.println(ContractDuration);
			
			SirName = (String) user.get("SirName");
			System.out.println(SirName);

			EmployeeName = (String) user.get("EmployeeName");
			System.out.println(EmployeeName);
			
			SirIDNumber = (String) user.get("SirIDNumber");
			System.out.println(SirIDNumber);
			
			PassportNumber = (String) user.get("PassportNumber");
			System.out.println(PassportNumber);

			VacationDays = (String) user.get("VacationDays");
			System.out.println(VacationDays);
			
			JobName = (String) user.get("JobName");
			System.out.println(JobName);
			
			Salary = (String) user.get("Salary");
			System.out.println(Salary);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			
			Nationality = (String) user.get("Nationality");
			System.out.println(Nationality);

		}

	}


	
	

}
