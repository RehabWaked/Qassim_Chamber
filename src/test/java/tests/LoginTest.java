package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Pages.LoginPage;
import data.GeneralData_JSONDataReader;

public class LoginTest extends TestBase {

	
	LoginPage loginOBJ;
	GeneralData_JSONDataReader loginReader;

	
	
	@Test(priority = 1, alwaysRun = true)
	void loginSuccess() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		loginOBJ.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);
		Thread.sleep(1000);
		loginOBJ.LogOut();
	}
	
	
	@Test(priority = 2, dependsOnMethods = {"loginSuccess"}, enabled = true)
	void ErrorLogin_IDInvalid() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_ErrorLogin();
		loginOBJ.loginError_InValidID(loginReader.WrongUserID1);
	}
	
	@Test(priority = 3, dependsOnMethods = {"ErrorLogin_IDInvalid"}, enabled = true)
	void ErrorLogin_InValidPassword() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_ErrorLogin();
		loginOBJ.loginError_InValidPassword(loginReader.wrongUserID, loginReader.wrongPassword);
	}
}
