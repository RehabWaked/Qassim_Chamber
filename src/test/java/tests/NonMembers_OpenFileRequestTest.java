package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Pages.AdminPage;
import Pages.LoginPage;
import Pages.NonMembers_OpenFileRequest;
import data.GeneralData_JSONDataReader;
import data.NonMemberServices_JSONDataReader;
import junit.framework.Assert;

public class NonMembers_OpenFileRequestTest extends TestBase{

	
	
	LoginPage loginOBJ;
	GeneralData_JSONDataReader loginReader;
	NonMembers_OpenFileRequest NonMemberOpenFileOBJ;
	NonMemberServices_JSONDataReader NonMember_OpenFileOBJ;
	String AdminURL = "https://www.worldofss.co:7772/#/Login";
	String MemberURL = "https://mbr.rassd.sa/#/Login" ;
	AdminPage adminOBJ;

	
	
	@Test(priority = 1, enabled = false)
	void OpenNonMembers_OpenFileRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		loginOBJ.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);		
		NonMemberOpenFileOBJ = new NonMembers_OpenFileRequest(driver);
		NonMemberOpenFileOBJ.OpenNonMemberCard();
		Assert.assertTrue(NonMemberOpenFileOBJ.OpenRequestsText.getText().toString().contains("طلبات عامة"));
		NonMember_OpenFileOBJ = new NonMemberServices_JSONDataReader();
		NonMember_OpenFileOBJ.jsonReader_NonMemberOpenFile();
		NonMemberOpenFileOBJ.NonMember_OpenFileRequest(NonMember_OpenFileOBJ.PageNumber , NonMember_OpenFileOBJ.RequestReason);
		NonMemberOpenFileOBJ.GetOpenFileRequestCreatedInfo_NonSubscribers();
		
		Thread.sleep(2000);
		driver.navigate().to(AdminURL);
		Thread.sleep(5000);
		adminOBJ = new AdminPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_AdminLogin();
		adminOBJ.AdminLoginPage(loginReader.AdminUsername, loginReader.AdminPassword);
		adminOBJ.AdminRejectOpenFileRequest_NonMemberServices(NonMemberOpenFileOBJ.ID, loginReader.RejectReasonText);
		Thread.sleep(1000);
		Assert.assertTrue(adminOBJ.processDone.getText().toString().contains("تمت العملية بنجاح"));
		Thread.sleep(2000);
		driver.navigate().to(MemberURL);
		Thread.sleep(5000);
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		loginOBJ.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);
		Thread.sleep(2000);
		NonMemberOpenFileOBJ.OpenNonMemberCard();
		NonMemberOpenFileOBJ.CheckRequestStatus();
	}
	
	
	
	@Test(priority = 2, enabled = true)
	void userCancelNewRequestCreatedTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		loginOBJ.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);		
		NonMemberOpenFileOBJ = new NonMembers_OpenFileRequest(driver);
		NonMemberOpenFileOBJ.OpenNonMemberCard();
		Assert.assertTrue(NonMemberOpenFileOBJ.OpenRequestsText.getText().toString().contains("طلبات عامة"));
		NonMember_OpenFileOBJ = new NonMemberServices_JSONDataReader();
		NonMember_OpenFileOBJ.jsonReader_NonMemberOpenFile();
		NonMemberOpenFileOBJ.NonMember_OpenFileRequest(NonMember_OpenFileOBJ.PageNumber , NonMember_OpenFileOBJ.RequestReason);
		NonMemberOpenFileOBJ.GetOpenFileRequestCreatedInfo_NonSubscribers();
		NonMemberOpenFileOBJ.UserCancelRequestCreated();
		Assert.assertTrue(NonMemberOpenFileOBJ.requestAppliedText.getText().toString().contains("تم الإلغاء بنجاح"));
		
	}

}
