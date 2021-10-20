package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DelegatingRepresentativeToSecretariatPage extends PageBase {

	public DelegatingRepresentativeToSecretariatPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}
	
	//المنطقة
	@FindBy(name = "Municipality")
	WebElement municipalityTXT;
	
	@FindBy(name = "IdNumber")
	WebElement idNumberTXT;
	
	@FindBy(name = "AuthorizedName")
	WebElement authorizedNameTXT;
	
	@FindBy(name = "BirthDate")
	WebElement BirthDate;
	
	@FindBy(css = "[title='عرض شهر آخر']")
	WebElement MonthClick;
	
	@FindBy(css = "[title='عرض سنة آخرى']")
	WebElement YearClick;
	
	@FindBy(css = "[title='اختر يوم الأربعاء, سبتمبر 2']")
	WebElement daySelected;
	
	@FindBy(name = "ReasonsFor")
	WebElement reasonForTXT;
	
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


	public void createNewRequest_DelegatingRepresentativeToSecretariatRequest(String municipality, String idNumber, String authorizedName, String reasonFor) throws InterruptedException {

		setTextElementText(municipalityTXT, municipality);
		Thread.sleep(1000);
		setTextElementText(idNumberTXT, idNumber);
		Thread.sleep(1000);
		setTextElementText(authorizedNameTXT, authorizedName);
		Thread.sleep(1000);
		clickButton(BirthDate);
		Thread.sleep(1000);
		Select FromMonthOptionSelected = new Select (MonthClick);
		FromMonthOptionSelected.selectByVisibleText("سبتمبر");
		Thread.sleep(1000);
		clickButton(YearClick);
		Thread.sleep(1000);
		Select FromYearOptionSelected = new Select (YearClick);
		FromYearOptionSelected.selectByVisibleText("2015");
		Thread.sleep(1000);
		clickButton(daySelected);
		Thread.sleep(1000);
		setTextElementText(reasonForTXT, reasonFor);
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
		//		Thread.sleep(1000);
		//تاكيد خصم المبلغ
		clickButton(ApproveRequesProcess);
		Thread.sleep(10000);
		// اغلاق الاستبيان
		clickButton(closeRequest);
		Thread.sleep(1000);
		jse.executeScript("window.scrollBy(0,-350)", "");
		Thread.sleep(1000);
	}


	@FindBy(css = "[ng-class='config.title']")
	public WebElement CheckboxAlert;
	
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
	
	public void createNewRequest_DelegatingRepresentativeToSecretariatRequestWithoutData() throws InterruptedException {

		scrollToBottom();
		clickButton(nextBTN);
		Thread.sleep(2000);
		System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");

	}


}
