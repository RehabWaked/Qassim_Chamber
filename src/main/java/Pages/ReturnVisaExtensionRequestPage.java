package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ReturnVisaExtensionRequestPage extends PageBase{

	public ReturnVisaExtensionRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	@FindBy (css = "[src='templateUrl()'] h2")
	WebElement RequestTitle;

	@FindBy (css = "[ng-model='RequestModel.ConsulateName']")
	WebElement ConsulateNameTxt;	
	@FindBy (css = ".custom-center-ul > li:nth-of-type(1) > p")
	WebElement ConsulateNamePreviewTxt;
	@FindBy (xpath = "//p[.='من فضلك ادخل اسم القنصلية']")
	WebElement ConsulateNameErrorMsg;

	@FindBy (css = "[ng-model='RequestModel.Name']")
	WebElement GuaranteedNameTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(2) > p")
	WebElement GuaranteedNamePreviewTxt;
	@FindBy (xpath = "//p[contains(.,'من فضلك ادخل اسم المكفول')]")
	WebElement GuaranteedNameErrorMsg;


	@FindBy (css = "[ng-model='$select.search']")
	WebElement NationalityDDL;
	@FindBy (css = ".ui-select-choices-group > div:nth-of-type(5) [ng-bind-html='c.nameAr']")
	WebElement NationalityOption;
	
	@FindBy (css = ".custom-center-ul > li:nth-of-type(3) > p")
	WebElement NationalityPreviewTxt;
	@FindBy (xpath = "//p[.='من فضلك اختر الجنسية']")
	WebElement NationalityErrorMsg;
	String NationalityStr;

	@FindBy (css = "[ng-model='mydate']")
	WebElement ResidenceEndDate;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(4)")
	WebElement ResidenceEndDatePreview;
	@FindBy (xpath = "//p[@class='text-danger']")
	WebElement ResidenceEndDateErrorMsg;
	String ResidenceEndDateStr;
	
	@FindBy (css = "[title='عرض سنة آخرى']")
	WebElement YearPicker;

	@FindBy (css = "[title='عرض شهر آخر']")
	WebElement MonthPicker;

	@FindBy (css = "[title='اختر يوم السبت, يناير 1']")
	WebElement EndDay;
	

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

	public void CreateNewReturnVisaExtRequest(String ConsulateName, String GuaranteedName, String EndYear, String EndMonth, String OTP, String InvalidOTP) throws InterruptedException {

		Assert.assertTrue(RequestTitle.getText().contains("تمديد تأشيرة عودة"));

		//fill Data
		setTextElementText(ConsulateNameTxt, ConsulateName);
		Thread.sleep(300);
		setTextElementText(GuaranteedNameTxt, GuaranteedName);
		Thread.sleep(300);
		clickButton(NationalityDDL);
		Thread.sleep(500);

		clickButton(NationalityOption);
		Thread.sleep(1000);
		NationalityStr = NationalityDDL.getAttribute("value");

		//Pick End Date
		clickButton(ResidenceEndDate);
		clickButton(YearPicker);
		Thread.sleep(500);
		Select FromYearOptionSelected = new Select (YearPicker);
		FromYearOptionSelected.selectByVisibleText(EndYear);
		clickButton(MonthPicker);
		Thread.sleep(500);
		Select FromMonthOptionSelected = new Select (MonthPicker);
		FromMonthOptionSelected.selectByVisibleText(EndMonth);
		clickButton(EndDay);
		ResidenceEndDateStr = ResidenceEndDate.getAttribute("value");
		//upload attachment
		AddAttachmentBtn.sendKeys(uploadPath);
		Thread.sleep(1000);

		//click on Next Button
		clickButton(NextBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("طلب تمديد تأشيرة عودة"));
		Assert.assertTrue(ConsulateNamePreviewTxt.getText().contains(ConsulateName));
		Assert.assertTrue(GuaranteedNamePreviewTxt.getText().contains(GuaranteedName));
		Assert.assertTrue(ResidenceEndDatePreview.getText().contains(ResidenceEndDateStr));
		Assert.assertTrue(NationalityPreviewTxt.getText().contains(NationalityStr));

		//Return Back to First Page
		clickButton(PreviousBtn);
		jse.executeScript("scrollBy(0,-500)");
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تمديد تأشيرة عودة"));
		Assert.assertTrue(ConsulateNameTxt.getAttribute("value").contains(ConsulateName));
		Assert.assertTrue(GuaranteedNameTxt.getAttribute("value").contains(GuaranteedName));
		Assert.assertTrue(NationalityDDL.getAttribute("value").contains(NationalityStr));
		Assert.assertTrue(ResidenceEndDate.getAttribute("value").contains(ResidenceEndDateStr));

		//click on Next Again
		clickButton(NextBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("طلب تمديد تأشيرة عودة"));
	
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

	public void CreateEmptyReturnVisaExtRequest() throws InterruptedException {
		Assert.assertTrue(RequestTitle.getText().contains("تمديد تأشيرة عودة"));
		//Click on Next without entering data
		clickButton(NextBtn);
		Thread.sleep(1000);
		System.out.println(ConsulateNameErrorMsg.getText());
		Assert.assertTrue(ConsulateNameErrorMsg.getText().contains("من فضلك ادخل اسم القنصلية"));
		Thread.sleep(500);
		System.out.println(GuaranteedNameErrorMsg.getText());
		Assert.assertTrue(GuaranteedNameErrorMsg.getText().contains("من فضلك ادخل اسم المكفول"));
		Thread.sleep(500);
		System.out.println(NationalityErrorMsg.getText());
		Assert.assertTrue(NationalityErrorMsg.getText().contains("من فضلك اختر الجنسية"));
		Thread.sleep(500);
		System.out.println(ResidenceEndDateErrorMsg.getText());
		Assert.assertTrue(ResidenceEndDateErrorMsg.getText().contains("من فضلك ادخل تاريخ انتهاء الإقامه"));
	
	}


}
