package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ExtendVisaFromPassportsRequestRequest extends PageBase{

	public ExtendVisaFromPassportsRequestRequest(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}

	@FindBy(name = "WorkRegion")
	WebElement workRegionTXT;
	
	@FindBy(name = "idNumber")
	WebElement idNumberTXT;
	
	@FindBy(name = "IdStartDate")
	WebElement IdStartDate;
	
	@FindBy(name = "IdExpireDate")
	WebElement IdExpireDate;
	
	@FindBy(css = "[title='عرض شهر آخر']")
	WebElement MonthClick;
	
	@FindBy(css = "[title='عرض سنة آخرى']")
	WebElement YearClick;
	
	@FindBy(css = "[title='اختر يوم الجمعة, يونيو 2']")
	WebElement DaySelected;
	
	@FindBy(css = "[title='اختر يوم الخميس, يونيو 1']")
	WebElement DaySelect;
	
	@FindBy(name = "SirName")
	WebElement sirNameTXT;
	
	@FindBy(name = "Nationality")
	WebElement nationalityTXT;
	
	@FindBy(name = "IdSource")
	WebElement idSourceTXT;
	
	@FindBy(name = "JobName")
	WebElement jobNameTXT;
	
	@FindBy(name = "VisaNo")
	WebElement visaNoTXT;

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
	String fileName2 = "pdf-test1.pdf";

	String uploadPath = System.getProperty("user.dir") + "/uploads/" + fileName1;
	String uploadPath1 = System.getProperty("user.dir") + "/uploads/" + fileName2;
	
	
	//, String otp
public void createNewRequest_ExtendVisaFromPassportsRequestRequest(String workRegion, String idNumber, String sirName, String nationality, String idSource, String jobName, String visaNo) throws InterruptedException {
		
		setTextElementText(workRegionTXT, workRegion);
		Thread.sleep(1000);
		setTextElementText(idNumberTXT, idNumber);
		Thread.sleep(1000);
		clickButton(IdStartDate);
		Thread.sleep(1000);
		Select FromMonthOptionForBilling = new Select (MonthClick);
		FromMonthOptionForBilling.selectByVisibleText("يونيو");
		Thread.sleep(1000);
		clickButton(YearClick);
		Thread.sleep(1000);
		Select FromYearOption = new Select (YearClick);
		FromYearOption.selectByVisibleText("2017");
		Thread.sleep(1000);
		clickButton(DaySelected);
		Thread.sleep(1000);
		
		clickButton(IdExpireDate);
		Thread.sleep(1000);
		clickButton(MonthClick);
		Thread.sleep(1000);
		Select FromMonthOptions = new Select (MonthClick);
		FromMonthOptions.selectByVisibleText("يونيو");
		Thread.sleep(1000);
		clickButton(YearClick);
		Thread.sleep(1000);
		Select FromYearOptions = new Select (YearClick);
		FromYearOptions.selectByVisibleText("2023");
		Thread.sleep(1000);
		clickButton(DaySelect);
		Thread.sleep(1000);
		setTextElementText(sirNameTXT, sirName);
		Thread.sleep(1000);
		setTextElementText(nationalityTXT, nationality);
		Thread.sleep(1000);
		setTextElementText(idSourceTXT, idSource);
		Thread.sleep(1000);
		setTextElementText(jobNameTXT, jobName);
		Thread.sleep(1000);
		setTextElementText(visaNoTXT, visaNo);
		Thread.sleep(1000);
		scrollToTheMiddle();
		fileUploader.sendKeys(uploadPath);
		Thread.sleep(1000);
		clickButton(nextBTN);
		Thread.sleep(1000);
		scrollToBottom();
		clickButton(previousBTN);
		Thread.sleep(1000);
		clickButton(nextBTN);
		Thread.sleep(3000);
		//اعتماد الطلب
		clickButton(ApproveRequest);
		Thread.sleep(3000);
//		setTextElementText(OTPTXT, otp);
//		Thread.sleep(10000);
		//تاكيد خصم المبلغ
		clickButton(ApproveRequesProcess);
		Thread.sleep(10000);
		// اغلاق الاستبيان
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
	
	public void createNewRequest_ExtendVisaFromPassportsRequestRequestWithoutData() throws InterruptedException {

		clickButton(nextBTN);
		Thread.sleep(2000);
		System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");

	}
}
