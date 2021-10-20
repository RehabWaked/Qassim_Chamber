package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class LoginPage extends PageBase{

	public LoginPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}


	@FindBy(name = "identity")
	WebElement ID;

	@FindBy(name = "password")
	WebElement passwordTXT;

	@FindBy(css = "#loginForm > div > div.form-buttons > button")
	WebElement loginBTN;

	@FindBy(css = "[src='../../../Content/Theme/assets/img/eye.svg']")
	WebElement eyeIcon;

	@FindBy(css = "input.form-control.ng-pristine.ng-untouched.ng-valid.ng-scope.ng-empty.ng-valid-maxlength")
	List<WebElement> otpLoop;

	@FindBy(css = ".chamber-name")
	public WebElement homePageChamberName;

	@FindBy(css = ".title")
	WebElement settings;

	@FindBy(partialLinkText = "تسجيل الخروج")
	WebElement logOut;

	@FindBy(css = "[ng-messages='loginForm.password.$error'] > .errors")
	public WebElement passwordRequired;

	@FindBy(css = "[ng-message='pattern']")
	WebElement wrongIDInsertedText;

	@FindBy(css = "[ng-message='required']")
	public WebElement IDRequired;

	@FindBy(css = "[ng-bind-html='Message']")
	public WebElement invalidLoginText;

	@FindBy(css = "div.sp-main-box.sp-membership-box.margin-bottom-30")
	List<WebElement> MembershipOwner;

	@FindBy(css = "div.sp-main-title")
	WebElement MembershipTitle;



	public void loginSuccess(String id, String password, String otp) throws InterruptedException {
		setTextElementText(ID, id);
		setTextElementText(passwordTXT, password);
		clickButton(eyeIcon);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(1000);
		System.out.println("The OTP index number is = " + otpLoop.size() + " " + "index");
		scrollUp();
		for (WebElement otpValue : otpLoop) {
			setTextElementText(otpValue, otp);
			Thread.sleep(200);
		}

	
		for (WebElement membership : MembershipOwner) {

			System.out.println(MembershipTitle.getText().toString());
			System.out.println(membership.getText().toString());
		}

		System.out.println("Chamber name is " + homePageChamberName.getText().toString());

	}



	public void loginError_InValidID(String id) throws InterruptedException {

		clickButton(loginBTN);
		Thread.sleep(1000);
		Assert.assertTrue(passwordRequired.getText().toString().contains("من فضلك ادخل كلمة المرور"));
		Thread.sleep(1000);
		setTextElementText(ID, id);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(2000);
		Assert.assertTrue(wrongIDInsertedText.getText().toString().contains("من فضلك ادخل رقم الهوية أرقام وتبدأ ب 1 أو 2"));
	}


	public void loginError_InValidPassword(String id, String password) throws InterruptedException {

		ID.clear();
		Thread.sleep(1000);
		setTextElementText(passwordTXT, password);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(2000);
		Assert.assertTrue(IDRequired.getText().toString().contains("من فضلك ادخل رقم الهوية / الإقامة"));
		Thread.sleep(1000);
		setTextElementText(ID, id);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(2000);
		Assert.assertFalse(invalidLoginText.getText().toString().contains("تسجيل دخول خاطئ ,  عدد المحاولات المتبقة"));
	}


	public void LogOut() throws InterruptedException {

		Thread.sleep(1000);
		clickButton(settings);
		Thread.sleep(2000);
		clickButton(logOut);
		Thread.sleep(1000);
	}
}
