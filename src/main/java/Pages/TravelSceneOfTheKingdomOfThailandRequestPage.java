package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TravelSceneOfTheKingdomOfThailandRequestPage extends PageBase{

	public TravelSceneOfTheKingdomOfThailandRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver ;
	}
	
	@FindBy(name = "SirName")
	WebElement sirNameTXT;
	
	@FindBy(css = "[ng-model='$select.search']")
	WebElement nationalityDDL;
	
	@FindBy(css = ".ui-select-choices-group > div:nth-of-type(7) [ng-bind-html='c.nameAr']")
	WebElement nationalitySelected;
	
	@FindBy(name = "Passport")
	WebElement passportTXT;
	
	@FindBy(name = "PassportSource")
	WebElement passportSourceTXT;
	
	@FindBy(name = "PassportIssueDate")
	WebElement PassportIssueDate;
	
	@FindBy(name = "PassportExpireDate")
	WebElement PassportExpireDate;
	
	@FindBy(css = "[title='عرض شهر آخر']")
	WebElement MonthClick;
	
	@FindBy(css = "[title='عرض سنة آخرى']")
	WebElement YearClick;
	
	@FindBy(css = "[title='اختر يوم الأربعاء, يونيو 7']")
	WebElement DaySelected;
	
	@FindBy(css = "[title='اختر يوم الخميس, ديسمبر 7']")
	WebElement SelectedDay;
	
	@FindBy(css = "[name='next']")
	WebElement nextBTN;

	@FindBy(css = "[ng-disabled='page.currentStep <= 1']")
	WebElement previousBTN;

	@FindBy(css = "[ng-disabled='page.currentStep != steps.length || saveRequestStart ==true']")
	WebElement ApproveRequest;
	
	@FindBy(name = "codeToMatch")
	WebElement OTPTXT;

	@FindBy(css = ".cancel")
	WebElement closeRequest;

	@FindBy(css = ".confirm")
	WebElement ApproveRequesProcess;

	@FindBy(name = "settings-outline")
	WebElement settingsOption;
	
	@FindBy(xpath = "//a[.='الغاء الطلب']")
	WebElement CancelRequestOption;

	@FindBy(css = "[tabindex='3']")
	WebElement cancelRequestReasonTXT;
	
	@FindBy(css = "[ng-class='config.title']")
	public WebElement RequestCanceledTextMSG;
	
	@FindBy(name = "files")
	WebElement fileUploader;

	String fileName1 = "pdf-test.pdf";
	String uploadPath = System.getProperty("user.dir") + "/uploads/" + fileName1;
	
	
	public void createNewRequest_TravelSceneOfTheKingdomOfThailandRequest(String sirName, String passport, String passportSource, String otp) throws InterruptedException {

		setTextElementText(sirNameTXT, sirName);
		Thread.sleep(1000);
		clickButton(nationalityDDL);
		Thread.sleep(1000);
		clickButton(nationalitySelected);
		Thread.sleep(1000);
		setTextElementText(passportTXT, passport);
		Thread.sleep(1000);
		setTextElementText(passportSourceTXT, passportSource);
		Thread.sleep(1000);
		clickButton(PassportIssueDate);
		Thread.sleep(1000);
		clickButton(MonthClick);
		Thread.sleep(1000);
		Select FromMonthOption = new Select (MonthClick);
		FromMonthOption.selectByVisibleText("يونيو");
		Thread.sleep(1000);
		clickButton(YearClick);
		Thread.sleep(1000);
		Select FromYearOption = new Select (YearClick);
		FromYearOption.selectByVisibleText("2017");
		Thread.sleep(1000);
		clickButton(DaySelected);
		Thread.sleep(1000);
		clickButton(PassportExpireDate);
		Thread.sleep(1000);
		clickButton(MonthClick);
		Thread.sleep(1000);
		Select FromMonthOptions = new Select (MonthClick);
		FromMonthOptions.selectByVisibleText("ديسمبر");
		Thread.sleep(1000);
		clickButton(YearClick);
		Thread.sleep(1000);
		Select FromYearOptions = new Select (YearClick);
		FromYearOptions.selectByVisibleText("2023");
		Thread.sleep(1000);
		clickButton(SelectedDay);
		Thread.sleep(1000);
		fileUploader.sendKeys(uploadPath);
		Thread.sleep(1000);
		clickButton(nextBTN);
		Thread.sleep(1000);
		scrollToBottom();
		clickButton(previousBTN);
		Thread.sleep(1000);
		clickButton(nextBTN);
		Thread.sleep(1000);
		//اعتماد الطلب
		clickButton(ApproveRequest);
		Thread.sleep(1000);
		setTextElementText(OTPTXT, otp);
		Thread.sleep(10000);
		clickButton(ApproveRequesProcess);
		Thread.sleep(10000);
		clickButton(closeRequest);
		Thread.sleep(1000);
		jse.executeScript("window.scrollBy(0,-350)", "");
		Thread.sleep(1000);
	}
	
	public void UserCanceledNewRequestCreated(String CancellationReason) throws InterruptedException {
		clickButton(settingsOption);
		Thread.sleep(1000);
		clickButton(CancelRequestOption);
		Thread.sleep(1000);
		clickButton(ApproveRequesProcess);
		Thread.sleep(1000);
		setTextElementText(cancelRequestReasonTXT, CancellationReason);
		Thread.sleep(1000);
		clickButton(ApproveRequesProcess);
		Thread.sleep(1000);
		Assert.assertFalse(RequestCanceledTextMSG.getText().toString().contains("تم الغاء الطلب بنجاح"));
		Thread.sleep(1000);
	} 
	
	public void createNewRequest_TravelSceneOfTheKingdomOfThailandRequestWithoutData() throws InterruptedException {

		scrollToBottom();
		clickButton(nextBTN);
		Thread.sleep(1000);
	}

}
