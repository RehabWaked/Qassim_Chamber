package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class EmbassyVisaExtensionLetterRequestPage extends PageBase {

	public EmbassyVisaExtensionLetterRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}

	@FindBy(name = "CountryName")
	WebElement countryNameTXT;
	
	@FindBy(name = "idNumber")
	WebElement idNumberTXT;
	
	@FindBy(name = "workStartDate")
	WebElement workStartDate;
	
	@FindBy(css = "[title='عرض شهر آخر']")
	WebElement MonthClick;
	
	@FindBy(css = "[title='عرض سنة آخرى']")
	WebElement YearClick;
	
	@FindBy(css = "[title='اختر يوم الثلاثاء, يونيو 22']")
	WebElement DaySelected;
	
	@FindBy(name = "SirName")
	WebElement sirNameTXT;
	
	@FindBy(name = "IdSource")
	WebElement idSourceTXT;
	
	@FindBy(name = "Nationality")
	WebElement nationalityTXT;
	
	@FindBy(name = "VisaTime")
	WebElement VisaTimeTXT;
	
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
	
	
	
public void createNewRequest_EmbassyVisaExtensionLetterRequest(String countryName, String idNumber, String sirName, String idSource, String nationality, String VisaTime, String otp) throws InterruptedException {
		
		setTextElementText(countryNameTXT, countryName);
		Thread.sleep(1000);
		setTextElementText(idNumberTXT, idNumber);
		Thread.sleep(1000);
		clickButton(workStartDate);
		Thread.sleep(1000);
		Select FromMonthOption = new Select (MonthClick);
		FromMonthOption.selectByVisibleText("يونيو");
		Thread.sleep(1000);
		Select FromYearOption= new Select (YearClick);
		FromYearOption.selectByVisibleText("2021");
		Thread.sleep(1000);
		clickButton(DaySelected);
		Thread.sleep(1000);
		setTextElementText(sirNameTXT, sirName);
		Thread.sleep(1000);
		setTextElementText(idSourceTXT, idSource);
		Thread.sleep(1000);
		setTextElementText(nationalityTXT, nationality);
		Thread.sleep(1000);
		setTextElementText(VisaTimeTXT, VisaTime);
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
		setTextElementText(OTPTXT, otp);
		Thread.sleep(10000);
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
	
	public void createNewRequest_EmbassyVisaExtensionLetterRequestWithoutData() throws InterruptedException {

		clickButton(nextBTN);
		Thread.sleep(2000);
		System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");

	}
	
	
}
