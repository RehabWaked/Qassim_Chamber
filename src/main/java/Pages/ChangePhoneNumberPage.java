package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ChangePhoneNumberPage extends PageBase{

	public ChangePhoneNumberPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}


	@FindBy(css = ".change-phone")
	public WebElement changePhoneNum;

	@FindBy(css = "[for='MemberID']")
	public WebElement IDTxt;

	@FindBy(name = "IdentityNo")
	WebElement IDTXT;

	@FindBy(css = "[type='submit']")
	WebElement restoreBTN;

	@FindBy(css = "input.form-control.ng-pristine.ng-untouched.ng-valid.ng-scope.ng-empty.ng-valid-maxlength")
	List<WebElement> otpLoop;

	@FindBy(css = ".sp-main-sm-button")
	WebElement backBTN;
	
	@FindBy(name = "PhoneNumber")
	WebElement phoneNum;

	@FindBy(css = "[name='ResetPhoneForm'] > p:nth-of-type(1)")
	public WebElement optSentToPhoneNumText;

	@FindBy(css = "[ng-class='config.title']")
	public WebElement MessageTextDisplaying;

	@FindBy(css = ".errors")
	public WebElement PhoneNumeErrorText;

	@FindBy(css = "[name='changePhoneNumber'] > div:nth-of-type(2) > [for='newPassword']")
	public WebElement newPhoneText;
	
	@FindBy(css = "[name='changePhoneNumber'] > div:nth-of-type(1) > [for='newPassword']")
	WebElement OldPhoneText;
	
	@FindBy(css = "[name='changePhoneNumber'] > div:nth-of-type(1) > .input-icon")
	WebElement oldPhoneValue;
	
	@FindBy(css = ".text-danger")
	public WebElement nullPhoneText;
	
	@FindBy(css = "span.title.ng-binding")
	WebElement settings;
	
	@FindBy(css = ".user-menu-list [href='/#/Profile/AccountInfo']")
	WebElement profilePage;
	
	@FindBy(css = "input.form-control.ng-pristine.ng-valid.ng-not-empty.ng-valid-required.ng-valid-pattern.ng-valid-minlength.ng-valid-maxlength.ng-touched")
	WebElement phoneNumberValueField;
	
	@FindBy(name = "changeData")
	List<WebElement> profileMainContainer;
	
	@FindBy(css = "div.form-body.row-model")
	List<WebElement> profileDataChanged;
	
	@FindBy(css = "div.form-group.form-md-line-input.has-success.form-md-floating-label")
	List<WebElement> ProfileRows;

	@FindBy(css = "[for='phoneNumber']")
	WebElement phoneNumberTextField;
	
	public void changePhoneNumberOption_SuccessStep(String id, String otp, String phone) throws InterruptedException {

		clickButton(changePhoneNum);
		Thread.sleep(1000);
		Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(2000);
		System.out.println("The OTP index number is = " + otpLoop.size() + " " + "index");
		Assert.assertTrue(optSentToPhoneNumText.getText().toString().contains("الرجاء إدخال رقم التحقق الذي تم إرساله"));
		Thread.sleep(1000);
		clickButton(backBTN);
		Thread.sleep(1000);
		clickButton(changePhoneNum);
		Thread.sleep(1000);
		Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(2000);
		
		for (WebElement otpValue : otpLoop) {
			setTextElementText(otpValue, otp);
			Thread.sleep(1000);
		}

		Assert.assertTrue(OldPhoneText.getText().toString().contains("رقم الجوال السابق"));
		Thread.sleep(1000);
		Assert.assertFalse(oldPhoneValue.getText().toString().contains("xxxxxx5555"));
		Thread.sleep(1000);
		System.out.println(OldPhoneText.getText().toString() + " " + oldPhoneValue.getText().toString());
		Thread.sleep(1000);
		Assert.assertTrue(newPhoneText.getText().toString().contains("رقم الجوال الجديد"));
		Thread.sleep(1000);
		clickButton(backBTN);
		Thread.sleep(1000);
		clickButton(changePhoneNum);
		Thread.sleep(1000);
		Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(2000);
		
		for (WebElement otpValue : otpLoop) {
			setTextElementText(otpValue, otp);
			Thread.sleep(1000);
		}

		setTextElementText(phoneNum, phone);
		Thread.sleep(2000);
		clickButton(restoreBTN);
		Thread.sleep(2000);
		
	}


	public void CheckPhoneChanged() throws InterruptedException {
		
		clickButton(settings);
		Thread.sleep(5000);
		clickButton(profilePage);
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView();", phoneNumberTextField );
		Thread.sleep(1000);
		System.out.println(phoneNumberTextField.getText().toString());
		Thread.sleep(1000);
		
		System.out.println(profileMainContainer.size());
		
		for (WebElement profileContainer : profileMainContainer) {
			
			for (WebElement profileData : profileDataChanged) {
				
				System.out.println(profileData.getText().toString());
				
				}
			}
		jse.executeScript("arguments[0].scrollIntoView();", settings );
		Thread.sleep(1000);
		}
	
	
	
	public void changePhoneNumberOption_IDError1(String id) throws InterruptedException {

		clickButton(changePhoneNum);
		Thread.sleep(1000);
		Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(1000);
		Assert.assertFalse(MessageTextDisplaying.getText().toString().contains("هذه الهوية ليس لديها حساب , يرجى التسجيل اولا"));
		Thread.sleep(1000);

	}


	public void changePhoneNumberOption_IDError2(String id) throws InterruptedException {

		IDTXT.clear();
		Thread.sleep(1000);
		Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		Assert.assertTrue(PhoneNumeErrorText.getText().toString().contains("ادخل رقم الهوية / الإقامة 10 أرقام"));
		Thread.sleep(1000);

	}

	public void changePhoneNumberOption_IDError3(String id) throws InterruptedException {

		IDTXT.clear();
		Thread.sleep(1000);
		Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		Assert.assertTrue(PhoneNumeErrorText.getText().toString().contains("من فضلك ادخل رقم الهوية أرقام وتبدأ ب 1 أو 2"));
		Thread.sleep(1000);

	}

	
	public void changePhoneNumberOption_OTPError(String id, String otp) throws InterruptedException {

		IDTXT.clear();
		Thread.sleep(1000);
		Assert.assertTrue(IDTxt.getText().toString().contains("رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(IDTXT, id);
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(2000);
		Assert.assertTrue(optSentToPhoneNumText.getText().toString().contains("الرجاء إدخال رقم التحقق الذي تم إرساله"));

		for (WebElement otpValue : otpLoop) {
			setTextElementText(otpValue, otp);
			Thread.sleep(1000);
		}
	}
	
	
	public void changePhoneNumberOption_NewPhoneNumberNullError() throws InterruptedException {

		Assert.assertTrue(newPhoneText.getText().toString().contains("رقم الجوال الجديد"));
		Thread.sleep(1000);
		clickButton(restoreBTN);
		Thread.sleep(1000);
		Assert.assertTrue(nullPhoneText.getText().toString().contains("من فضلك ادخل رقم الجوال"));
		Thread.sleep(1000);
	}

	
	public void changePhoneNumberOption_NewPhoneNumberMinError(String phone) throws InterruptedException {

		setTextElementText(phoneNum, phone);
		Thread.sleep(1000);
		Assert.assertTrue(nullPhoneText.getText().toString().contains("من فضلك ادخل الجوال بشكل صحيح لا يقل عن 10 أرقام يبدأ ب 05"));
		Thread.sleep(1000);
	}
	
	
	public void changePhoneNumberOption_NewPhoneNumberMaxError(String phone) throws InterruptedException {

		phoneNum.clear();
		Thread.sleep(250);
		setTextElementText(phoneNum, phone);
		Thread.sleep(1000);
		Assert.assertTrue(nullPhoneText.getText().toString().contains("من فضلك ادخل الجوال بشكل صحيح لا يقل عن 10 أرقام يبدأ ب 05"));
		Thread.sleep(1000);
	}
	
}
