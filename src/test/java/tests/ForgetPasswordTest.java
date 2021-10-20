package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.ForgetPasswordPage;
import Pages.LoginPage;
import data.GeneralData_JSONDataReader;

public class ForgetPasswordTest extends TestBase{

	ForgetPasswordPage forgetOBJ;
	GeneralData_JSONDataReader forgetPasswordReader;
	LoginPage loginOBJ;



	@Test(priority = 1, alwaysRun = true)
	void ForgetPasswordSuccessTest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		forgetPasswordReader = new GeneralData_JSONDataReader();
		forgetPasswordReader.jsonReader_Login();
		forgetOBJ = new ForgetPasswordPage(driver);
		Assert.assertTrue(forgetOBJ.ForgetPasswordText.getText().toString().contains("نسيت كلمة المرور؟"));
		forgetOBJ.ForgetPasswordSuccessRestoring(forgetPasswordReader.userID, forgetPasswordReader.ForgetPassOTP, forgetPasswordReader.password);
		Assert.assertTrue(forgetOBJ.MessageTextDisplaying.getText().toString().contains("تم تغيير كلمة المرور بنجاح"));
		loginOBJ = new LoginPage(driver);
		loginOBJ.loginSuccess(forgetPasswordReader.userID, forgetPasswordReader.password, forgetPasswordReader.OTP);
		loginOBJ.LogOut();
	}

	@Test(priority = 2, dependsOnMethods = {"ForgetPasswordSuccessTest"}, enabled = true)
	void forgetPassword_IDErrorRestoring() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{

		forgetPasswordReader = new GeneralData_JSONDataReader();
		forgetPasswordReader.jsonReader_ErrorLogin();

		forgetOBJ = new ForgetPasswordPage(driver);
		forgetOBJ.ForgetPassword_IDErrorRestoring(forgetPasswordReader.wrongUserID);
	}


	@Test(priority = 3, dependsOnMethods = {"forgetPassword_IDErrorRestoring"}, enabled = true)
	void forgetPassword_OTPErrorRestoring() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{

		forgetPasswordReader = new GeneralData_JSONDataReader();
		forgetPasswordReader.jsonReader_ErrorLogin();

		forgetOBJ = new ForgetPasswordPage(driver);
		forgetOBJ.ForgetPassword_OTPErrorRestoring(forgetPasswordReader.userID, forgetPasswordReader.ForgetPassWrongOTP);
		
	}

	@Test(priority = 4, dependsOnMethods = {"forgetPassword_OTPErrorRestoring"}, enabled = true)
	void forgetPassword_PasswordErrorRestoring() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{

		forgetPasswordReader = new GeneralData_JSONDataReader();
		forgetPasswordReader.jsonReader_ErrorLogin();

		forgetOBJ = new ForgetPasswordPage(driver);
		forgetOBJ.ForgetPassword_PasswordErrorRestoring(forgetPasswordReader.ForgetPassOTP, forgetPasswordReader.wrongPassword);
	}
}
