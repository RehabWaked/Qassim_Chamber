package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Pages.AdminPage;
import Pages.AdminRequestSettingsPage;
import data.ForeignAffairs_JsonDataReader;

public class AdminRequestSettingsTest extends TestBase{

	AdminPage AdminObj;
	AdminRequestSettingsPage ReqSettObj;
	ForeignAffairs_JsonDataReader FADataObj = new ForeignAffairs_JsonDataReader();


	public void OpenFARequestSettings () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		NavigateToAdminPortal();
		FADataObj.JsonReader_ForeignAffairsRequest();
		OpenSpecificRequestSettingsPage(FADataObj.RequestName);
	
	}
	@Test (priority = 1, enabled = true)
	public void ChangeReqName() throws FileNotFoundException, InterruptedException, IOException, ParseException {	
		OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.ChangeAnyTextBoxinRequestSettings(ReqSettObj.ReqArNameTxt, "طلب وزارة الخارجية جديد");
	}
	
	@Test (priority = 2, enabled = true)
	public void ChangeReqExpiryDate () throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.ChangeAnyTextBoxinRequestSettings(ReqSettObj.ExpireDaysTxt, "90");
	}

	@Test (priority = 3, enabled = true)
	public void ChangeReqAmount () throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.ChangeAnyTextBoxinRequestSettings(ReqSettObj.ReqAmountTxt, "40");
	}
	
	@Test (priority = 4, enabled = true)
	public void ChangeRequestSetting_ActiveStatus() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.CheckCertainRequestOption(ReqSettObj.ActiveLbl,ReqSettObj.ActiveChkBox);
	}
	
	@Test (priority = 5, enabled = true)
	public void ChangeRequestSetting_InActiveStatus() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.UnCheckCertainRequestOption(ReqSettObj.ActiveLbl,ReqSettObj.ActiveChkBox);
	}
	
	@Test (priority = 6, enabled = true)
	public void ChangeRequestSetting_AutoApproval() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.CheckCertainRequestOption(ReqSettObj.AutoLbl, ReqSettObj.AutoChkBox);
	}
	@Test (priority = 7, enabled = true)
	public void ChangeRequestSetting_ManualApproval() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.UnCheckCertainRequestOption(ReqSettObj.AutoLbl, ReqSettObj.AutoChkBox);
	}
	
	@Test (priority = 8, enabled = true)
	public void ChangeRequestSetting_OTPMandatory() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(2000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.CheckCertainRequestOption(ReqSettObj.OTPLbl, ReqSettObj.OTPChkBox );
	}
	@Test (priority = 9, enabled = true)
	public void ChangeRequestSetting_OTPOptional() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.UnCheckCertainRequestOption(ReqSettObj.OTPLbl, ReqSettObj.OTPChkBox );
	}
	
	@Test (priority = 10, enabled = true)
	public void ChangeRequestSetting_AttachMandatory() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.CheckCertainRequestOption(ReqSettObj.AttachmentLbl, ReqSettObj.AttachmentChkBox);
	}
	@Test (priority = 11, enabled = true)
	public void ChangeRequestSetting_AttachOptional() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.UnCheckCertainRequestOption(ReqSettObj.AttachmentLbl, ReqSettObj.AttachmentChkBox);
	}
	
	@Test (priority = 12, enabled = true)
	public void ChangeRequestSetting_DontAllowDownload() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{			
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.CheckCertainRequestOption(ReqSettObj.DownloadLbl, ReqSettObj.DownloadChkBox);
	}
	@Test (priority = 13, enabled = true)
	public void ChangeRequestSetting_AllowDownload() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{	
		//OpenFARequestSettings();
		Thread.sleep(1000);
		ReqSettObj = new AdminRequestSettingsPage (driver);
		ReqSettObj.UnCheckCertainRequestOption(ReqSettObj.DownloadLbl, ReqSettObj.DownloadChkBox);
	}
	
}
 