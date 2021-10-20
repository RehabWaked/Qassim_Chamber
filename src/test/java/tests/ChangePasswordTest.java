package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.ChangePasswordPage;
import Pages.ForgetPasswordPage;
import Pages.LoginPage;
import data.GeneralData_JSONDataReader;

public class ChangePasswordTest extends TestBase{

	
	
	LoginPage loginOBJ;
	GeneralData_JSONDataReader loginReader;
	ChangePasswordPage passwordOBJ;
	ForgetPasswordPage forgetOBJ;
	GeneralData_JSONDataReader forgetPasswordReader;
	
	
	@Test(priority = 1, alwaysRun = true)
	void ChangePasswordSuccess() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		passwordOBJ = new ChangePasswordPage(driver);
		loginOBJ.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);
		Thread.sleep(1000);
		passwordOBJ.ChangePasswordOption(loginReader.password, loginReader.newPass);
		Thread.sleep(1000);
		Assert.assertTrue(passwordOBJ.MessageTextDisplaying.getText().toString().contains("تم تغيير كلمة المرور بنجاح"));
		Thread.sleep(1000);
		loginOBJ.loginSuccess(loginReader.userID, loginReader.newPass, loginReader.OTP);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getCurrentUrl().contains("https://mbr.rassd.sa/#/Landing"));
		Thread.sleep(1000);
	}
	
	
	
	@Test(priority = 2, dependsOnMethods = {"ChangePasswordSuccess"}, enabled = true)
	void ChangePassword_ErrorPassLength() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_ErrorLogin();
		passwordOBJ = new ChangePasswordPage(driver);
		passwordOBJ.ChangePasswordOption_PassLengthError(loginReader.wrongPassword, loginReader.wrongPassword);
		Thread.sleep(1000);
		loginOBJ.LogOut();
		Thread.sleep(1000);
	}
	
	
	@Test(priority = 3, dependsOnMethods = {"ChangePassword_ErrorPassLength"}, enabled = true)
	void ChangePassword_LoginWithForgetPassword() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		forgetPasswordReader = new GeneralData_JSONDataReader();
		forgetPasswordReader.jsonReader_Login();
		forgetPasswordReader.jsonReader_ErrorLogin();
		forgetOBJ = new ForgetPasswordPage(driver);
		passwordOBJ = new ChangePasswordPage(driver);
		passwordOBJ.LoginWithForgetPass(forgetPasswordReader.userID, forgetPasswordReader.wrongPassword);
		Thread.sleep(1000);
		Assert.assertTrue(forgetOBJ.ForgetPasswordText.getText().toString().contains("نسيت كلمة المرور؟"));
		forgetOBJ.ForgetPasswordSuccessRestoring(forgetPasswordReader.userID, forgetPasswordReader.ForgetPassOTP, forgetPasswordReader.password);
		Assert.assertTrue(forgetOBJ.MessageTextDisplaying.getText().toString().contains("تم تغيير كلمة المرور بنجاح"));
		loginOBJ = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		loginOBJ.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);
		loginOBJ.LogOut();
	}
}
