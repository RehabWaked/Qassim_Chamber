package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AuthorizePassportServicesPage extends PageBase{

	public AuthorizePassportServicesPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}

	@FindBy(name = "WorkRegion")
	WebElement workRegionTXT;
	
	@FindBy(name = "OfficeName")
	WebElement officeNameTXT;
	
	@FindBy(name = "OfficeServiceNumber")
	WebElement officeServiceNumTXT;
	
	@FindBy(name = "name_0")
	WebElement beneficiaryNameTXT;
	
	@FindBy(name = "id_0")
	WebElement beneficiaryIdTXT;
	
	@FindBy(id = "chex1")
	WebElement checkBox1;
	
	@FindBy(id = "chex2")
	WebElement checkBox2;
	
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
	
	
	public void createNewRequest_AuthorizeServiceRequest(String workRegion, String officeName, String officeServiceNumber, String beneficiaryName, String benificiaryIdNumber, String otp) throws InterruptedException {

		setTextElementText(workRegionTXT, workRegion);
		Thread.sleep(1000);
		setTextElementText(officeNameTXT, officeName);
		Thread.sleep(1000);
		setTextElementText(officeServiceNumTXT, officeServiceNumber);
		Thread.sleep(1000);
		setTextElementText(beneficiaryNameTXT, beneficiaryName);
		Thread.sleep(1000);
		setTextElementText(beneficiaryIdTXT, benificiaryIdNumber);
		Thread.sleep(1000);
		clickButton(checkBox1);
		Thread.sleep(1000);
		clickButton(checkBox2);
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
	
	public void createNewRequest_AuthorizedServiceWithoutData() throws InterruptedException {

		clickButton(nextBTN);
		Thread.sleep(2000);
		System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");
	}
}
