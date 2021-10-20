package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DelegationToMOfTradeServicesRequestPage extends PageBase{

	public DelegationToMOfTradeServicesRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	@FindBy (css = "[src='templateUrl()'] h2")
	WebElement RequestTitle;

	@FindBy (css = "[ng-model='RequestModel.WorkRegion']")
	WebElement WorkRegionTxt;	
	@FindBy (css = ".CustomTextShow")
	WebElement WorkRegionPreviewTxt;
	@FindBy (xpath = "//p[.='من فضلك ادخل المنطقة']")
	WebElement WorkRegionErrorMsg;

	@FindBy (css = ".fa-toggle-on")
	WebElement ToggleBtn;
	@FindBy (css = "[ng-model='RequestModel.OfficeName']")
	WebElement OfficeNameTxt;
	@FindBy (css = "[ng-if='RequestModel.IsOffice']")
	WebElement OfficPreviewTxt;
	@FindBy (xpath = "//p[.='من فضلك ادخل اسم المكتب']")
	WebElement OfficeNameErrorMsg;
	@FindBy (css = "[ng-if='!RequestModel.IsOffice']")
	WebElement SirDataPreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.OfficeServiceNumber']")
	WebElement OfficeServiceNumberTxt;
	@FindBy (xpath = "//p[.='من فضلك ادخل رقم الترخيص']")
	WebElement OfficeServiceNumberErrorMsg;

	@FindBy (css = "[ng-model='RequestModel.SirName']")
	WebElement SirNameTxt;
	@FindBy (xpath = "//p[.='من فضلك ادخل اسم السيد']")
	WebElement SirNameErrorMsg;

	@FindBy (css = "[ng-model='RequestModel.SirIdNumber']")
	WebElement SirIdNumberTxt;
	@FindBy (xpath = "//p[.='من فضلك ادخل رقم بطاقة الهوية الوطنية']")
	WebElement SirIdNumberErrorMsg;

	@FindBy (id="chex4")
	WebElement Service1ChkBox;
	@FindBy (id="chex5")
	WebElement Service2ChkBox;
	@FindBy (id="chex6")
	WebElement Service3ChkBox;
	@FindBy (id="chex7")
	WebElement Service4ChkBox;
	@FindBy (css = "[ng-class='config.title']")
	WebElement ServicesErrorMsg;

	// for Attachment
	String fileName1 = "pdf-test.pdf";
	String uploadPath = System.getProperty("user.dir") + "/uploads/" + fileName1;
	@FindBy (id="files")
	WebElement AddAttachmentBtn;

	@FindBy (name="next")
	WebElement NextBtn;


	@FindBy (css="[ng-disabled='page.currentStep != steps.length || saveRequestStart ==true']")
	WebElement SubmitBtn;

	@FindBy (name= "previous")
	WebElement PreviousBtn;

	@FindBy (css= "label")
	WebElement OTPMsg;

	@FindBy (name= "codeToMatch")
	WebElement OTPTxt;

	@FindBy (css= ".confirm")
	WebElement ConfirmBtn;

	@FindBy (css= ".sweet-alert > h2")
	WebElement ConfirmTitle;

	@FindBy (css= ".cancel")
	WebElement CloseQuestionnaire;

	@FindBy (css= "[ng-if='RequestModel.RequestStatus === 4']")
	WebElement DownloadBtn;

	@FindBy (css= ".m-top-md > [back='عوده'] > .sp-main-button")
	WebElement BackBtn;

	@FindBy (css= "[ng-class='config.title']")
	WebElement WarningMsg;

	public void CreateNewMOFTradeServicesRequest(String WorkRegion, String OfficeName, String OfficeServiceNumber, String SirName, String SirIDNumber, String OTP, String InvalidOTP) throws InterruptedException {

		scrollToTheMiddle();
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض لخدمات وزارة التجارة"));

		//fill Data
		setTextElementText(WorkRegionTxt, WorkRegion);
		Thread.sleep(300);
		setTextElementText(OfficeNameTxt, OfficeName);
		Thread.sleep(300);
		setTextElementText(OfficeServiceNumberTxt, OfficeServiceNumber);
		clickButton(Service1ChkBox);
		Thread.sleep(1000);
		scrollToBottom();

		//click on Next Button
		clickButton(NextBtn);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,-500)");
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تفويض لخدمات وزارة التجارة"));
		Assert.assertTrue(WorkRegionPreviewTxt.getText().contains(WorkRegion));
		Assert.assertTrue(OfficPreviewTxt.getText().contains(OfficeName));
		Assert.assertTrue(OfficPreviewTxt.getText().contains(OfficeServiceNumber));

		//Return Back to First Page
		clickButton(PreviousBtn);
		jse.executeScript("scrollBy(0,-500)");
		Thread.sleep(2000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض لخدمات وزارة التجارة"));
		Assert.assertTrue(WorkRegionTxt.getAttribute("value").contains(WorkRegion));
		Assert.assertTrue(OfficeNameTxt.getAttribute("value").contains(OfficeName));
		Assert.assertTrue(OfficeServiceNumberTxt.getAttribute("value").contains(OfficeServiceNumber));

		clickButton(ToggleBtn);
		setTextElementText(SirNameTxt, SirName);
		setTextElementText(SirIdNumberTxt, SirIDNumber);
		jse.executeScript("scrollBy(0,300)");
		clickButton(Service2ChkBox);
		clickButton(Service3ChkBox);
		
		//upload attachment
		AddAttachmentBtn.sendKeys(uploadPath);
		Thread.sleep(1000);
		scrollToBottom();
		//click on Next Again
		clickButton(NextBtn);
		jse.executeScript("scrollBy(0,-500)");
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تفويض لخدمات وزارة التجارة"));
		Assert.assertTrue(WorkRegionPreviewTxt.getText().contains(WorkRegion));
		Assert.assertTrue(SirDataPreviewTxt.getText().contains(SirName));
		Assert.assertTrue(SirDataPreviewTxt.getText().contains(SirIDNumber));
	
		//Return Back to First Page
		clickButton(PreviousBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض لخدمات وزارة التجارة"));
		Assert.assertTrue(WorkRegionTxt.getAttribute("value").contains(WorkRegion));
		Assert.assertTrue(SirNameTxt.getAttribute("value").contains(SirName));
		Assert.assertTrue(SirIdNumberTxt.getAttribute("value").contains(SirIDNumber));
		Thread.sleep(1000);
		
		//click on Next Again
		clickButton(NextBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تفويض لخدمات وزارة التجارة"));
	
		//Submit Request
		clickButton(SubmitBtn);
		Thread.sleep(1000);
//		// if OTP is Required
//		Assert.assertTrue(OTPMsg.getText().contains("أدخل كود التحقق الذي تم إرساله على الهاتف الجوال الخاص بك"));
//		Thread.sleep(1000);
//
//		//Enter Invalid OTP
//		setTextElementText(OTPTxt, InvalidOTP);
//		Thread.sleep(4000);
//		Assert.assertTrue(WarningMsg.getText().contains("كود التحقق غير صحيح"));
//		Thread.sleep(1000);
//
//		//Enter Valid OTP
//		OTPTxt.clear();
//		Thread.sleep(1000);
//		setTextElementText(OTPTxt, OTP);

		Thread.sleep(1000);
		Assert.assertTrue(ConfirmTitle.getText().contains("تأكيد اضافة الطلب"));
		Thread.sleep(1000);
		clickButton(ConfirmBtn);
		Thread.sleep(3000);
		Assert.assertTrue(ConfirmTitle.getText().contains("تمت العملية بنجاح"));
		Thread.sleep(1000);
		clickButton(CloseQuestionnaire);
		Thread.sleep(1000);

	}

	public void CreateEmptyMOFTradeServicesRequest (String WorkRegion, String OfficeName, String OfficeServiceNumber) throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض لخدمات وزارة التجارة"));
		//Click on Next without entering data
		clickButton(NextBtn);
		Thread.sleep(500);
		jse.executeScript("scrollBy(0,-800)");
		Thread.sleep(1000);
		System.out.println(WorkRegionErrorMsg.getText());
		Assert.assertTrue(WorkRegionErrorMsg.getText().contains("من فضلك ادخل المنطقة"));
		Thread.sleep(500);
		System.out.println(OfficeNameErrorMsg.getText());
		Assert.assertTrue(OfficeNameErrorMsg.getText().contains("من فضلك ادخل اسم المكتب"));
		Thread.sleep(500);
		System.out.println(OfficeServiceNumberErrorMsg.getText());
		Assert.assertTrue(OfficeServiceNumberErrorMsg.getText().contains("من فضلك ادخل رقم الترخيص"));
		Thread.sleep(500);
		
		clickButton(ToggleBtn);
		System.out.println(SirNameErrorMsg.getText());
		Assert.assertTrue(SirNameErrorMsg.getText().contains("من فضلك ادخل اسم السيد"));
		Thread.sleep(500);
		System.out.println(SirIdNumberErrorMsg.getText());
		Assert.assertTrue(SirIdNumberErrorMsg.getText().contains("من فضلك ادخل رقم بطاقة الهوية الوطنية"));
		Thread.sleep(500);
		
		clickButton(ToggleBtn);
		setTextElementText(WorkRegionTxt, WorkRegion);
		setTextElementText(OfficeNameTxt, OfficeName);
		setTextElementText(OfficeServiceNumberTxt, OfficeServiceNumber);
		clickButton(NextBtn);
		System.out.println(ServicesErrorMsg.getText());
		Assert.assertTrue(ServicesErrorMsg.getText().contains("فضلاً , اختر سبب طلب التفويض ."));
		Thread.sleep(1000);

		//Check Validation if user selected more than 3 services
		Thread.sleep(3000);
		clickButton(Service2ChkBox);	
		Thread.sleep(1000);
		clickButton(Service3ChkBox);
		Thread.sleep(1000);
		clickButton(Service4ChkBox);
		Thread.sleep(1000);
		clickButton(Service1ChkBox);
		Thread.sleep(1000);
		clickButton(NextBtn);	
		Assert.assertTrue(WarningMsg.getText().contains("لايمكن تحديد اكثر من 3"));
		
	}


}
