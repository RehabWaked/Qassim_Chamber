package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReExportRequest_JSONDataReader {


	public String ExpoeterPhone , ExporterZipCode, ExporterFax, ExporterPBox, ImporterName, ImporterPBox, ImporterCity,
	ImporterFax,GoodsType, GoodsOrigin, GoodsInformation, GoodsQuantity, CertificateOriginNumber, GoodsValue,
	GoodsValueDollar, BillNumber, OTP, CancelText , RequestName;

	
	public void jsonReader_ReExportRequest() throws FileNotFoundException, IOException, ParseException {

		String filePath = System.getProperty("user.dir")+"/src/test/java/data/ReExportRequest_UserData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray) parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {

			JSONObject user =  (JSONObject) jsonObj;


			ExpoeterPhone = (String) user.get("ExpoeterPhone");
			System.out.println(ExpoeterPhone);

			ExporterZipCode = (String) user.get("ExporterZipCode");
			System.out.println(ExporterZipCode);
			
			ExporterFax = (String) user.get("ExporterFax");
			System.out.println(ExporterFax);

			ExporterPBox = (String) user.get("ExporterPBox");
			System.out.println(ExporterPBox);
			
			ImporterName = (String) user.get("ImporterName");
			System.out.println(ImporterName);

			ImporterPBox = (String) user.get("ImporterPBox");
			System.out.println(ImporterPBox);

			ImporterCity = (String) user.get("ImporterCity");
			System.out.println(ImporterCity);

			ImporterFax = (String) user.get("ImporterFax");
			System.out.println(ImporterFax);
			
			GoodsType = (String) user.get("GoodsType");
			System.out.println(GoodsType);
			
			GoodsOrigin = (String) user.get("GoodsOrigin");
			System.out.println(GoodsOrigin);

			GoodsInformation = (String) user.get("GoodsInformation");
			System.out.println(GoodsInformation);

			GoodsQuantity = (String) user.get("GoodsQuantity");
			System.out.println(GoodsQuantity);

			CertificateOriginNumber = (String) user.get("CertificateOriginNumber");
			System.out.println(CertificateOriginNumber);

			GoodsValue = (String) user.get("GoodsValue");
			System.out.println(GoodsValue);

			GoodsValueDollar = (String) user.get("GoodsValueDollar");
			System.out.println(GoodsValueDollar);

			BillNumber = (String) user.get("BillNumber");
			System.out.println(BillNumber);

			OTP = (String) user.get("OTP");
			System.out.println(OTP);
			
			CancelText = (String) user.get("CancelText");
			System.out.println(CancelText);
			
			RequestName = (String) user.get("RequestName");
			System.out.println(RequestName);
		}

	}

}
