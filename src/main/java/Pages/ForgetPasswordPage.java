package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ForgetPasswordPage extends PageBase {

	public ForgetPasswordPage(WebDriver driver) {
		super(driver);

		jse = (JavascriptExecutor) driver;
	}


	@FindBy(partialLinkText = "نسيت كلمة المرور؟")
	public WebElement ForgetPasswordText;

	@FindBy(partialLinkText = "نسيت كلمة المرور؟")
	WebElement ForgetPassword;

	@FindBy(name = "IdentityNo")
	WebElement IDTXT;

	@FindBy(css = "[for='MemberID']")
	WebElement IDTxt;

	@FindBy(name = "code")
	WebElement otpTXT;

	@FindBy(css = "[name='forgetPasswordForm'] > p:nth-of-type(1)")
	WebElement OTPTxt;

	@FindBy(css = ".switch-control > div:nth-of-type(1) > label")
	WebElement phoneNumber;

	@FindBy(css = "[type='submit']")
	WebElement restoreBTN;

	@FindBy(name = "newPassword")
	WebElement newPassTXT;

	@FindBy(css = ".sp-main-size")
	WebElement newPasswordTXT;

	@FindBy(name = "confirmNewPassword")
	WebElement confirmNewPassTXT;

	@FindBy(css = "[ng-class='config.title']")
	public WebElement MessageTextDisplaying;

	@FindBy(css = "[ng-message='required']")
	WebElement IDWarningText;

	@FindBy(css = "[ng-if='!ForgetModel.ForgetWay  && isFormSubmitted'] > .errors")
	WebElement phonewarningText;

	@FindBy(css = "[ng-messages='changePass.newPassword.$error'] > .text-danger")
	WebElement newPassTextMSG; // Password Field Is Required

	@FindBy(css = "[ng-messages='changePass.confirmNewPassword.$error'] > .text-danger")
	WebElement confirmNewPassMSG;  // Confirm Password Field Is Required

	@FindBy(css = "[ng-message='pattern']")
	WebElement wrongPasswordText;

	@FindBy(css = "[ng-message='noMatch']")
	WebElement passwordsNotMatch;

	public void ForgetPasswordSuccessRestoring(String id, String otp , String newPassword) throws InterruptedException {

		clickButton(ForgetPassword);
		Thread.sleep(1000);
		Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		clickButton(phoneNumber);
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(2000);
		Assert.assertFalse(otpTXT.getText().toString().contains("الرجاء إدخال رقم التحقق الذي تم إرساله"));
		Thread.sleep(1000);
		Assert.assertTrue(MessageTextDisplaying.getText().toString().contains("تم إرسال كود التحقق بنجاح"));
		Thread.sleep(1000);
		setTextElementText(otpTXT, otp);
		Thread.sleep(2000);
		setTextElementText(newPassTXT, newPassword);
		Thread.sleep(1000);
		Assert.assertTrue(newPasswordTXT.getText().toString().contains("الرجاء إدخال كلمة المرور الجديدة"));
		Thread.sleep(1000);
		setTextElementText(confirmNewPassTXT, newPassword);
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(2000);

	}



	public void ForgetPassword_IDErrorRestoring(String id) throws InterruptedException {

		{
			clickButton(ForgetPassword);
			Thread.sleep(1000);
			Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
			Thread.sleep(1000);
			clickButton(restoreBTN);
			Thread.sleep(2000);
			Assert.assertTrue(IDWarningText.getText().toString().contains("من فضلك ادخل رقم الهوية / الإقامة"));
			Thread.sleep(1000);  
			Assert.assertTrue(phonewarningText.getText().toString().contains("من فضلك أختر طريقة الإرسال"));
			Thread.sleep(2000);
			setTextElementText(IDTXT, id);
			Thread.sleep(1000);
			clickButton(phoneNumber);
			Thread.sleep(1000);
			clickButton(restoreBTN);
			Thread.sleep(2000);
			Assert.assertTrue(MessageTextDisplaying.getText().toString().contains("هذه الهوية ليس لديها حساب , يرجى التسجيل اولا"));
			Thread.sleep(1000);
		}
	}


	public void ForgetPassword_OTPErrorRestoring(String id, String otp) throws InterruptedException
	{
		IDTXT.clear();
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		clickButton(phoneNumber);
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(2000);
		Assert.assertFalse(otpTXT.getText().toString().contains("الرجاء إدخال رقم التحقق الذي تم إرساله"));
		Thread.sleep(1000);
		setTextElementText(otpTXT, otp);
		Thread.sleep(1000);
	//	Assert.assertTrue(MessageTextDisplaying.getText().toString().contains("كود التحقق غير صحيح"));
		Thread.sleep(2000);


	}

	public void ForgetPassword_PasswordErrorRestoring(String otp, String newPassword) throws InterruptedException

	{
		otpTXT.clear();
		Thread.sleep(1000);
		setTextElementText(otpTXT, otp);
		Thread.sleep(2000);
		clickButton(restoreBTN);
		Thread.sleep(2000);
		Assert.assertTrue(newPassTextMSG.getText().toString().contains("من فضلك ادخل كلمة المرور"));
		Thread.sleep(1000);
		Assert.assertTrue(confirmNewPassMSG.getText().toString().contains("من فضلك ادخل كلمة المرور الجديدة"));
		Thread.sleep(1000);

		setTextElementText(newPassTXT, newPassword);
		Thread.sleep(1000);
		setTextElementText(confirmNewPassTXT, newPassword);
		Thread.sleep(1000);
		Assert.assertTrue(wrongPasswordText.getText().toString().contains("يجب ألا تقل كلمة المرور عن 8 خانات تحتوى على الاقل على رقم وحرف"));
		Thread.sleep(1000);
		Assert.assertTrue(passwordsNotMatch.getText().toString().contains("كلمات المرور غير متشابه"));
		Thread.sleep(1000);  
	}

}
