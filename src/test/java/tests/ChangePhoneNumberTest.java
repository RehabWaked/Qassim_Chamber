package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.ChangePhoneNumberPage;
import Pages.LoginPage;
import data.GeneralData_JSONDataReader;

public class ChangePhoneNumberTest extends TestBase{

	ChangePhoneNumberPage changePhoneOBJ;
	GeneralData_JSONDataReader changePhoneReader;
	LoginPage loginOBJ;
	GeneralData_JSONDataReader loginReader;



	@Test(priority = 1, alwaysRun = true)
	void changePhoneSuccess() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneReader = new GeneralData_JSONDataReader();
		changePhoneReader.jsonReader_Login();
		Assert.assertTrue(changePhoneOBJ.changePhoneNum.getText().toString().contains("تغيير رقم الجوال؟"));
		changePhoneOBJ.changePhoneNumberOption_SuccessStep(changePhoneReader.userID, changePhoneReader.OTP, changePhoneReader.phoneNumber);
		Assert.assertTrue(changePhoneOBJ.MessageTextDisplaying.getText().toString().contains("تمت العملية بنجاح"));
		Thread.sleep(1000);
		loginOBJ = new LoginPage(driver);
		loginReader= new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		loginOBJ.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);
		Thread.sleep(1000);
		changePhoneOBJ.CheckPhoneChanged();
		Thread.sleep(2000);
		loginOBJ.LogOut();
		Thread.sleep(1000);
	}


	@Test(priority = 2, dependsOnMethods = {"changePhoneSuccess"}, enabled = true)
	void changePhone_IDErrorMembershipNotExist() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneReader = new GeneralData_JSONDataReader();
		changePhoneReader.jsonReader_ErrorLogin();
		changePhoneOBJ.changePhoneNumberOption_IDError1(changePhoneReader.wrongUserID);
	}


	@Test(priority = 3, dependsOnMethods = {"changePhone_IDErrorMembershipNotExist"}, enabled = true)
	void changePhone_IDErrorMaxAndMinLength() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneReader = new GeneralData_JSONDataReader();
		changePhoneReader.jsonReader_ErrorLogin();
		changePhoneOBJ.changePhoneNumberOption_IDError2(changePhoneReader.WrongUserID1);
	}


	@Test(priority = 4, dependsOnMethods = {"changePhone_IDErrorMaxAndMinLength"}, enabled = true)
	void changePhone_IDErrorValidation() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneReader = new GeneralData_JSONDataReader();
		changePhoneReader.jsonReader_ErrorLogin();
		changePhoneOBJ.changePhoneNumberOption_IDError3(changePhoneReader.WrongUserID2);
	}


	@Test(priority = 5, dependsOnMethods = {"changePhone_IDErrorValidation"}, enabled = true)
	void changePhone_ErrorOTP() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneReader = new GeneralData_JSONDataReader();
		changePhoneReader.jsonReader_ErrorLogin();
		changePhoneOBJ.changePhoneNumberOption_OTPError(changePhoneReader.userID, changePhoneReader.wrongOTP);
		Assert.assertTrue(changePhoneOBJ.MessageTextDisplaying.getText().toString().contains("كود التحقق غير صحيح"));
		Thread.sleep(1000);
	}


	@Test(priority = 6, dependsOnMethods = {"changePhone_ErrorOTP"}, enabled = true)
	void changePhone_NewPhoneNumberError() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneReader = new GeneralData_JSONDataReader();
		changePhoneReader.jsonReader_ErrorLogin();
		driver.navigate().refresh();
		changePhoneOBJ.changePhoneNumberOption_OTPError(changePhoneReader.userID, changePhoneReader.OTP);
	}
	

	@Test(priority = 7, dependsOnMethods = {"changePhone_NewPhoneNumberError"}, enabled = true)
	void changePhone_NewPhoneNumberNullError() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneOBJ.changePhoneNumberOption_NewPhoneNumberNullError();
	}
	

	@Test(priority = 8, dependsOnMethods = {"changePhone_NewPhoneNumberNullError"}, enabled = true)
	void changePhone_NewPhoneNumberMinError() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneReader = new GeneralData_JSONDataReader();
		changePhoneReader.jsonReader_ErrorLogin();
		changePhoneOBJ.changePhoneNumberOption_NewPhoneNumberMinError(changePhoneReader.WrongUserID1);
	}
	

	@Test(priority = 9, dependsOnMethods = {"changePhone_NewPhoneNumberMinError"}, enabled = true)
	void changePhone_NewPhoneNumberMaxError() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		changePhoneOBJ = new ChangePhoneNumberPage(driver);
		changePhoneReader = new GeneralData_JSONDataReader();
		changePhoneReader.jsonReader_ErrorLogin();
		changePhoneOBJ.changePhoneNumberOption_NewPhoneNumberMaxError(changePhoneReader.WrongUserID2);
	}
	
}
