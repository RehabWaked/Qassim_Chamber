package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ChangePasswordPage extends PageBase{

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
	}


	@FindBy(name = "identity")
	WebElement ID;

	@FindBy(name = "password")
	WebElement passwordTXT;
	
	@FindBy(css = "[src='../../../Content/Theme/assets/img/eye.svg']")
	WebElement eyeIcon;
	
	@FindBy(css = ".form-buttons > [type='submit']")
	WebElement loginBTN;
	
	@FindBy(css = "span.title.ng-binding")
	WebElement settings;

	@FindBy(css = ".user-menu-list [href='/#/Profile/ChangePassword']")
	WebElement changePassword;

	@FindBy(css = ".sp-box-title h2")
	WebElement changePasswordText;

	@FindBy(name = "currentPassword")
	WebElement currentPassword;

	@FindBy(css = "[for='currentPassword']")
	WebElement currentPasswordText;

	@FindBy(name = "newPassword")
	WebElement NewPassword;

	@FindBy(css = "[for='newPassword']")
	WebElement newPasswordText;

	@FindBy(name = "confirmNewPassword")
	WebElement confirmPassword;

	@FindBy(css = "[for='confirmNewPassword']")
	WebElement confirmPasswordText;

	@FindBy(css = "[type='submit']")
	WebElement saveBTN;
	
	@FindBy(css = "[ng-class='config.title']")
	public WebElement MessageTextDisplaying;

	@FindBy(css = "[ng-message='pattern']")
	WebElement wrongPasswordText;

	@FindBy(css = "[ng-message='noMatch']")
	WebElement passwordsNotMatch;
	
	@FindBy(css = "[ng-bind-html='Message']")
	WebElement ErrorLoginText;
	
	@FindBy(css = "p.errors.ng-binding")
	WebElement errorLoginText;
	
	public void ChangePasswordOption(String password, String newPassword) throws InterruptedException {

		clickButton(settings);
		Thread.sleep(1000);
		clickButton(changePassword);
		Thread.sleep(5000);
		System.out.println("The page title is " + changePasswordText.getText().toString());
		Thread.sleep(1000);
		Assert.assertTrue(changePasswordText.getText().toString().contains("تغيير كلمة السر"));
		Thread.sleep(1000);
		System.out.println(currentPasswordText.getText().toString());
		Thread.sleep(1000);
		setTextElementText(currentPassword, password);
		Thread.sleep(1000);
		System.out.println(newPasswordText.getText().toString());
		Thread.sleep(1000);
		setTextElementText(NewPassword, newPassword);
		Thread.sleep(1000);
		System.out.println(confirmPasswordText.getText().toString());
		Thread.sleep(1000);
		setTextElementText(confirmPassword, newPassword);
		Thread.sleep(1000);
		clickButton(saveBTN);
		Thread.sleep(1000);
	}


	
	public void ChangePasswordOption_PassLengthError(String password, String newPassword) throws InterruptedException {

		clickButton(settings);
		Thread.sleep(1000);
		clickButton(changePassword);
		Thread.sleep(5000);
		System.out.println("The page title is " + changePasswordText.getText().toString());
		Thread.sleep(1000);
		Assert.assertTrue(changePasswordText.getText().toString().contains("تغيير كلمة السر"));
		Thread.sleep(1000);
		System.out.println(currentPasswordText.getText().toString());
		Thread.sleep(1000);
		setTextElementText(currentPassword, password);
		Thread.sleep(1000);
		System.out.println(newPasswordText.getText().toString());
		Thread.sleep(1000);
		setTextElementText(NewPassword, newPassword);
		Thread.sleep(1000);
		System.out.println(confirmPasswordText.getText().toString());
		Thread.sleep(1000);
		setTextElementText(confirmPassword, newPassword);
		Thread.sleep(1000);
		Assert.assertTrue(wrongPasswordText.getText().toString().contains("يجب ألا تقل كلمة المرور عن 8 خانات تحتوى على الاقل على رقم وحرف"));
		Thread.sleep(1000);
	}
	
	public void LoginWithForgetPass(String id, String password) throws InterruptedException {
		
		
		setTextElementText(ID, id);
		Thread.sleep(1000);
		setTextElementText(passwordTXT, password);
		Thread.sleep(1000);
		clickButton(eyeIcon);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(2000);
		System.out.println(errorLoginText.getText().toString());
		Thread.sleep(1000);
		Assert.assertFalse(ErrorLoginText.getText().contains("تسجيل دخول خاطئ ,  عدد المحاولات المتبقة 4"));
		Thread.sleep(1000);
	}

}
