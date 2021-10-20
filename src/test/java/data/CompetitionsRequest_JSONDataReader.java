package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CompetitionsRequest_JSONDataReader {


	public String  ContestName, ContestPlace, DistributionMethod, RecieveCouponsPlace, SortingPlace, AwardsPlace, ContestMethod, ContestCouponsNumbers,
	ContestCouponsFrom, ContestCouponsTo, ContestGoal, Question, Answer, OTP, RequestName, CancelText;

	public void jsonReader_FillData() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/CompetitionsRequest_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;
			
			ContestName = (String) user.get("ContestName");
			System.out.println(ContestName);
			
			ContestPlace = (String) user.get("ContestPlace");
			System.out.println(ContestPlace);
			
			DistributionMethod = (String) user.get("DistributionMethod");
			System.out.println(DistributionMethod);
			
			RecieveCouponsPlace = (String) user.get("RecieveCouponsPlace");
			System.out.println(RecieveCouponsPlace);
			
			SortingPlace = (String) user.get("SortingPlace");
			System.out.println(SortingPlace);
			
			AwardsPlace = (String) user.get("AwardsPlace");
			System.out.println(AwardsPlace);
			
			ContestMethod = (String) user.get("ContestMethod");
			System.out.println(ContestMethod);
			
			ContestCouponsNumbers = (String) user.get("ContestCouponsNumbers");
			System.out.println(ContestCouponsNumbers);
			
			ContestCouponsFrom = (String) user.get("ContestCouponsFrom");
			System.out.println(ContestCouponsFrom);
			
			ContestCouponsTo = (String) user.get("ContestCouponsTo");
			System.out.println(ContestCouponsTo);

			ContestGoal = (String) user.get("ContestGoal");
			System.out.println(ContestGoal);
			
			Question = (String) user.get("Question");
			System.out.println(Question);
			
			Answer = (String) user.get("Answer");
			System.out.println(Answer);
			
			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
		}

	}





}
