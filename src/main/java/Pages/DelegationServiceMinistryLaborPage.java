package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DelegationServiceMinistryLaborPage extends PageBase{

	public DelegationServiceMinistryLaborPage(WebDriver driver) {
		super(driver);
		jse= (JavascriptExecutor) driver;
	}
	
	@FindBy(name = "WorkRegion")
	WebElement workRegionTXT;
	
	@FindBy(name = "OfficeName")
	WebElement officeNameTXT;
	
	@FindBy(name = "OfficeServiceNumber")
	WebElement officeServiceNumTXT;
	
	@FindBy(id = "chex4")
	WebElement checkbox4;
	
	@FindBy(id = "chex5")
	WebElement checkbox5;
	
	@FindBy(id = "chex6")
	WebElement checkbox6;
	
	@FindBy(id = "chex7")
	WebElement checkbox7;
	
	@FindBy(id = "chex8")
	WebElement checkbox8;
	
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
	
	
	
	
	public void createNewRequest_DelegationServiceLaborRequest(String workRegion, String officeName, String officeServiceNumber, String otp) throws InterruptedException {
		
		setTextElementText(workRegionTXT, workRegion);
		Thread.sleep(1000);
		setTextElementText(officeNameTXT, officeName);
		Thread.sleep(1000);
		setTextElementText(officeServiceNumTXT, officeServiceNumber);
		Thread.sleep(1000);
		clickButton(checkbox4);
		Thread.sleep(1000);
		clickButton(checkbox5);
		Thread.sleep(1000);
		clickButton(checkbox6);
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
	
	@FindBy(css = "[ng-class='config.title']")
	public WebElement CheckboxAlert;
	
	public void createNewRequest_ServiceCheckBoxesChecker(String workRegion, String officeName, String officeServiceNumber) throws InterruptedException {

		setTextElementText(workRegionTXT, workRegion);
		Thread.sleep(1000);
		setTextElementText(officeNameTXT, officeName);
		Thread.sleep(1000);
		setTextElementText(officeServiceNumTXT, officeServiceNumber);
		Thread.sleep(1000);
		clickButton(checkbox4);
		Thread.sleep(1000);
		clickButton(checkbox5);
		Thread.sleep(1000);
		clickButton(checkbox6);
		Thread.sleep(1000);
		clickButton(checkbox7);
		Thread.sleep(1000);
		clickButton(checkbox8);
		Thread.sleep(1000);
		scrollToTheMiddle();
		fileUploader.sendKeys(uploadPath);
		Thread.sleep(1000);
		clickButton(nextBTN);
		Thread.sleep(1000);
		Assert.assertTrue(CheckboxAlert.getText().toString().contains("لايمكن تحديد اكثر من 1"));
		Thread.sleep(1000);		
		System.out.println(CheckboxAlert.getText().toString());
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
	
	public void createNewRequest_DelegateServiceRequestWithoutData() throws InterruptedException {

		clickButton(nextBTN);
		Thread.sleep(2000);
		System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");

	}

}
