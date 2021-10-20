package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SalaryDefinition_JSONDataReader {

	public String SaudianRequestName,NonSaudianRequestName, Destination, EmployeeName,NonSaudianIDNumber, SaudianIDNumber,
	IDSource, JobName, TotalSalary,Nationality, OTP, InvalidOTP;
	
	public void JsonReader_SalaryDefRequest() throws FileNotFoundException, IOException, ParseException 
	{
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/SalaryDefinition_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			SaudianRequestName = (String) user.get("SaudianRequestName");
			System.out.println(SaudianRequestName);
			
			NonSaudianRequestName = (String) user.get("NonSaudianRequestName");
			System.out.println(NonSaudianRequestName);
			
			Destination = (String) user.get("Destination");
			System.out.println(Destination);

			EmployeeName = (String) user.get("EmployeeName");
			System.out.println(EmployeeName);
			
			SaudianIDNumber = (String) user.get("SaudianIDNumber");
			System.out.println(SaudianIDNumber);
			
			NonSaudianIDNumber = (String) user.get("NonSaudianIDNumber");
			System.out.println(NonSaudianIDNumber);

			IDSource = (String) user.get("IDSource");
			System.out.println(IDSource);
			
			JobName = (String) user.get("JobName");
			System.out.println(JobName);
			
			TotalSalary = (String) user.get("TotalSalary");
			System.out.println(TotalSalary);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			InvalidOTP = (String) user.get("InvalidOTP");
			System.out.println(InvalidOTP);
			
			Nationality = (String) user.get("Nationality");
			System.out.println(Nationality);

		}

	}


	
	

}
