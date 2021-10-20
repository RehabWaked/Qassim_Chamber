package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegisterToUseElectronicLicensingServicesFromAutorityPage extends PageBase{

	public RegisterToUseElectronicLicensingServicesFromAutorityPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}


	@FindBy(name = "SirName")
	WebElement sirNameTXT;

	@FindBy(name = "IdNumber")
	WebElement idNumberTXT;
	
	@FindBy(name = "JobTitle")
	WebElement jobTitleTXT;
	
	@FindBy(name = "IdentityIssuePlace")
	WebElement identityIssuePlaceTXT;
	
	@FindBy(name = "PhoneNumber")
	WebElement phoneNumberTXT;
	
	@FindBy(name = "IdentityIssueDate")
	WebElement identityIssueDate;
	
	@FindBy(css = ".calendars-today")
	WebElement todaySelect;
	
	@FindBy(name = "Email")
	WebElement emailTXT;
	
	//صندوق البريد
	@FindBy(name = "PoBox")
	WebElement poBoxTXT;
	
	@FindBy(name = "PostalCode")
	WebElement postalCodeTXT;
	
	@FindBy(name = "City")
	WebElement cityTXT;
	
	@FindBy(name = "Street")
	WebElement streetTXT;
	
	@FindBy(name = "District")
	WebElement districtTXT;
	
	@FindBy(name = "BuildingNo")
	WebElement buildingNumberTXT;
	
	@FindBy(name = "ApartmentNo")
	WebElement apartmentNumberTXT;
	
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


	public void createNewRequest_RegisterUsingElecronicLicensingServicesAuthorityRequest(String sirName, String idNumber, String jobTitle, String identityIssuePlace, String phoneNumber, String email, String poBox, String postalCode, String city, String street, String district, String buildingNumber, String apartmentNumber) throws InterruptedException {

		setTextElementText(sirNameTXT, sirName);
		Thread.sleep(1000);
		setTextElementText(idNumberTXT, idNumber);
		Thread.sleep(1000);
		setTextElementText(jobTitleTXT, jobTitle);
		Thread.sleep(1000);
		setTextElementText(identityIssuePlaceTXT, identityIssuePlace);
		Thread.sleep(1000);
		setTextElementText(phoneNumberTXT, phoneNumber);
		Thread.sleep(1000);
		clickButton(identityIssueDate);
		Thread.sleep(1000);
		clickButton(todaySelect);
		Thread.sleep(1000);
		setTextElementText(emailTXT, email);
		Thread.sleep(1000);
		setTextElementText(poBoxTXT, poBox);
		Thread.sleep(1000);
		setTextElementText(postalCodeTXT, postalCode);
		Thread.sleep(1000);
		setTextElementText(cityTXT, city);
		Thread.sleep(1000);
		setTextElementText(streetTXT, street);
		Thread.sleep(1000);
		setTextElementText(districtTXT, district);
		Thread.sleep(1000);
		setTextElementText(buildingNumberTXT, buildingNumber);
		Thread.sleep(1000);
		setTextElementText(apartmentNumberTXT, apartmentNumber);
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
	
	public void createNewRequest_RegisterUsingElecronicLicensingServicesAuthorityRequestWithoutData() throws InterruptedException {

		scrollToBottom();
		clickButton(nextBTN);
		Thread.sleep(2000);
		System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");

	}

}
