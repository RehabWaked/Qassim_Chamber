package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class VehicleDrivingAuthorizationRequestPage extends PageBase{

	public VehicleDrivingAuthorizationRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}

	@FindBy(name = "ManagerName")
	WebElement managerNameTXT;
	
	@FindBy(name = "SirNameCar")
	WebElement sirNameCarTXT;
	
	@FindBy(name = "IdNumber")
	WebElement idNumberTXT;
	
	@FindBy(name = "CarType")
	WebElement carTypeTXT;
	
	@FindBy(name = "CarNumber")
	WebElement carNumberTXT;
	
	@FindBy(name = "AuthorizationPeriod")
	WebElement authorizationPeriodTXT;
	
	@FindBy(name = "CarModel")
	WebElement carModelTXT;
	
	@FindBy(name = "CarColor")
	WebElement carColorTXT;
	
	@FindBy(name = "ComputerNumber")
	WebElement computerNumberTXT;
	
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


	public void createNewRequest_VehicleDrivingAuthorizationRequest(String managerName, String sirNameCar, String idNumber,String carType, String carNumber, String authorizationPeriod, String carModel, String carColor, String computerNumber) throws InterruptedException {

		setTextElementText(managerNameTXT, managerName);
		Thread.sleep(1000);
		setTextElementText(sirNameCarTXT, sirNameCar);
		Thread.sleep(1000);
		setTextElementText(idNumberTXT, idNumber);
		Thread.sleep(1000);
		setTextElementText(carTypeTXT, carType);
		Thread.sleep(1000);
		setTextElementText(carNumberTXT, carNumber);
		Thread.sleep(1000);
		setTextElementText(authorizationPeriodTXT, authorizationPeriod);
		Thread.sleep(1000);
		setTextElementText(carModelTXT, carModel);
		Thread.sleep(1000);
		setTextElementText(carColorTXT, carColor);
		Thread.sleep(1000);
		setTextElementText(computerNumberTXT, computerNumber);
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
	
	public void createNewRequest_VehicleDrivingAuthorizationRequestWithoutData() throws InterruptedException {

		scrollToBottom();
		clickButton(nextBTN);
		Thread.sleep(2000);
		System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");

	}

}
