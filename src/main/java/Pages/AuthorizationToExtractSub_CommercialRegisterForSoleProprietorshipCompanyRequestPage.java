package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AuthorizationToExtractSub_CommercialRegisterForSoleProprietorshipCompanyRequestPage extends PageBase{

	public AuthorizationToExtractSub_CommercialRegisterForSoleProprietorshipCompanyRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}
	
	@FindBy(name = "WorkRegion")
	WebElement workRegionTXT;
	
	@FindBy(name = "officeName")
	WebElement officeNameTXT;
	
	@FindBy(name = "OfficeServiceNumber")
	WebElement officeServiceNumberTXT;
	
	@FindBy(css = "[for='chex1']")
	WebElement chexkBox;

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
	
	
	public void createNewRequest_AuthorizationToExtractSub_CommercialRegisterForSoleProprietorshipCompanyRequest(String workRegion, String officeName, String officeServiceNumber, String otp) throws InterruptedException {

		setTextElementText(workRegionTXT, workRegion);
		Thread.sleep(1000);
		setTextElementText(officeNameTXT, officeName);
		Thread.sleep(1000);
		setTextElementText(officeServiceNumberTXT, officeServiceNumber);
		Thread.sleep(1000);
		clickButton(chexkBox);
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
	
	public void createNewRequest_AuthorizationToExtractSub_CommercialRegisterForSoleProprietorshipCompanyRequestWithoutData() throws InterruptedException {

		scrollToBottom();
		clickButton(nextBTN);
		Thread.sleep(1000);
	}
	
}
