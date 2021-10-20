package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DelegationToTrafficServicesRequestPage extends PageBase{

	public DelegationToTrafficServicesRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	@FindBy (css = "[src='templateUrl()'] h2")
	WebElement RequestTitle;

	@FindBy (css = "[ng-model='RequestModel.WorkRegion']")
	WebElement WorkRegionTxt;
	@FindBy (css = ".CustomTextShow")
	WebElement WorkRegionPreviewTxt;
	@FindBy (css = "[ng-messages='requestForm.workRegion.$error'] > .text-danger")
	WebElement WorkRegionErrorMsg;

	@FindBy (css = ".fa-toggle-on")
	WebElement ToggleBtn;
	@FindBy (css = "[ng-model='RequestModel.OfficeName']")
	WebElement OfficeNameTxt;
	@FindBy (css = "[ng-if='RequestModel.IsOffice']")
	WebElement OfficPreviewTxt;
	@FindBy (css = "[ng-messages='requestForm.OfficeName.$error'] > .text-danger")
	WebElement OfficeNameErrorMsg;
	@FindBy (css = "[ng-if='!RequestModel.IsOffice']")
	WebElement SirDataPreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.OfficeServiceNumber']")
	WebElement OfficeServiceNumberTxt;
	@FindBy (css = "[ng-messages='requestForm.OfficeServiceNumber.$error'] > .text-danger")
	WebElement OfficeServiceNumberErrorMsg;

	@FindBy (css = "[ng-model='RequestModel.SirName']")
	WebElement SirNameTxt;
	@FindBy (css = "[ng-messages='requestForm.SirName.$error'] > .text-danger")
	WebElement SirNameErrorMsg;

	@FindBy (css = "[ng-model='RequestModel.SirIdNumber']")
	WebElement SirIdNumberTxt;
	@FindBy (css = "[ng-messages='requestForm.SirIdNumber.$error'] > .text-danger")
	WebElement SirIdNumberErrorMsg;


	@FindBy (css = "[ng-model='RequestModel.Nationality']")
	WebElement NationalityTxt;
	@FindBy (css = "[ng-messages='requestForm.Nationality.$error'] > .text-danger")
	WebElement NationalityErrorMsg;


	@FindBy (id="chex1")
	WebElement Service1ChkBox;
	@FindBy (id="chex2")
	WebElement Service2ChkBox;
	@FindBy (id="chex3")
	WebElement Service3ChkBox;
	@FindBy (id="chex4")
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

	public void CreateNewTraffiCServicesRequest(String WorkRegion, String OfficeName, String OfficeServiceNumber, String SirName, String SirIDNumber , String Nationality, String OTP, String InvalidOTP) throws InterruptedException {

		scrollToTheMiddle();
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض لخدمات المرور"));

		//fill Data
		setTextElementText(WorkRegionTxt, WorkRegion);
		Thread.sleep(1000);
		setTextElementText(OfficeNameTxt, OfficeName);
		Thread.sleep(1000);
		setTextElementText(OfficeServiceNumberTxt, OfficeServiceNumber);
		scrollToTheMiddle();
		clickButton(Service1ChkBox);
		Thread.sleep(1000);
		scrollToBottom();

		//click on Next Button
		clickButton(NextBtn);
		jse.executeScript("scrollBy(0,-500)");
		Thread.sleep(2000);
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تفويض لخدمات المرور"));
		Assert.assertTrue(WorkRegionPreviewTxt.getText().contains(WorkRegion));
		Assert.assertTrue(OfficPreviewTxt.getText().contains(OfficeName));
		Assert.assertTrue(OfficPreviewTxt.getText().contains(OfficeServiceNumber));

		//Return Back to First Page
		clickButton(PreviousBtn);
		jse.executeScript("scrollBy(0,-500)");
		Thread.sleep(2000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض لخدمات المرور"));
		Assert.assertTrue(WorkRegionTxt.getAttribute("value").contains(WorkRegion));
		Assert.assertTrue(OfficeNameTxt.getAttribute("value").contains(OfficeName));
		Assert.assertTrue(OfficeServiceNumberTxt.getAttribute("value").contains(OfficeServiceNumber));

		clickButton(ToggleBtn);
		Thread.sleep(3000);
		setTextElementText(SirNameTxt, SirName);
		setTextElementText(SirIdNumberTxt, SirIDNumber);
		NationalityTxt.clear();
		setTextElementText(NationalityTxt, Nationality);
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
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تفويض لخدمات المرور"));
		Assert.assertTrue(WorkRegionPreviewTxt.getText().contains(WorkRegion));
		Assert.assertTrue(SirDataPreviewTxt.getText().contains(SirName));
		Assert.assertTrue(SirDataPreviewTxt.getText().contains(SirIDNumber));
		Assert.assertTrue(SirDataPreviewTxt.getText().contains(Nationality));

		//Return Back to First Page
		clickButton(PreviousBtn);
		jse.executeScript("scrollBy(0,-500)");
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض لخدمات المرور"));
		Assert.assertTrue(WorkRegionTxt.getAttribute("value").contains(WorkRegion));
		Assert.assertTrue(SirNameTxt.getAttribute("value").contains(SirName));
		Assert.assertTrue(SirIdNumberTxt.getAttribute("value").contains(SirIDNumber));
		Assert.assertTrue(NationalityTxt.getAttribute("value").contains(Nationality));
		Thread.sleep(1000);
		
		//click on Next Again
		clickButton(NextBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تفويض لخدمات المرور"));
		Thread.sleep(1000);

		//Submit Request
		clickButton(SubmitBtn);
		Thread.sleep(1000);
		Assert.assertTrue(OTPMsg.getText().contains("أدخل كود التحقق الذي تم إرساله على الهاتف الجوال الخاص بك"));
		Thread.sleep(1000);

		//Enter Invalid OTP
		setTextElementText(OTPTxt, InvalidOTP);
		Thread.sleep(4000);
		Assert.assertTrue(WarningMsg.getText().contains("كود التحقق غير صحيح"));
		Thread.sleep(1000);

		//Enter Valid OTP
		OTPTxt.clear();
		Thread.sleep(1000);
		setTextElementText(OTPTxt, OTP);

		Thread.sleep(1000);
		Assert.assertTrue(ConfirmTitle.getText().contains("تأكيد اضافة الطلب"));
		Thread.sleep(1000);
		clickButton(ConfirmBtn);
		Thread.sleep(5000);
		Assert.assertTrue(ConfirmTitle.getText().contains("تمت العملية بنجاح"));
		Thread.sleep(1000);
		clickButton(CloseQuestionnaire);
		Thread.sleep(1000);

	}

	public void CreateEmptyTrafficServicesRequest (String WorkRegion, String OfficeName, String OfficeServiceNumber) throws InterruptedException {
		scrollUp();
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض لخدمات المرور"));
		Thread.sleep(1000);
		scrollToBottom();
		//Click on Next without entering data
		clickButton(NextBtn);
		Thread.sleep(500);
		jse.executeScript("scrollBy(0,-500)");
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
		System.out.println(NationalityErrorMsg.getText());
		Assert.assertTrue(NationalityErrorMsg.getText().contains("من فضلك ادخل الجنسية"));
		Thread.sleep(500);
	}


}
