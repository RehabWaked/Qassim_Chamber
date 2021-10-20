package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ExperienceCertificateRequestPage extends PageBase{

	public ExperienceCertificateRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}


	//Definition 
	@FindBy (css = "[src='templateUrl()'] h2")
	WebElement RequestTitle;

	@FindBy (css = "[ng-model='RequestModel.JobName']")
	WebElement JobNameTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(5) > p")
	WebElement JobNamePreviewTxt;


	@FindBy (css = "[ng-model='RequestModel.SirName']")
	WebElement SirNameTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(1) > p")
	WebElement SirNamePreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.SirIdNumber']")
	WebElement SirIDNumberTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(2) > p")
	WebElement SirIDNumberPreviewTxt;


	@FindBy (css = ".WSSCalender[name='workStartDate']")
	WebElement WorkStartDate;
	@FindBy (css = ".WSSCalender[name='workEndDate']")
	WebElement WorkEndDate;
	String StartDateStr, EndDateStr;
	
	@FindBy (css = ".custom-center-ul > li:nth-of-type(3) > p")
	WebElement WorkStartDatePreviewTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(4) > p")
	WebElement WorkEndDatePreviewTxt;

	@FindBy (css = "[title='عرض سنة آخرى']")
	WebElement YearPicker;

	@FindBy (css = "[title='عرض شهر آخر']")
	WebElement MonthPicker;

	@FindBy (css = "[title='اختر يوم الخميس, يناير 1']")
	WebElement StartDay;
	
	@FindBy (css = ".calendars-today")
	WebElement TodayPicker;


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

	//Error Messages Validations

	//من فضلك ادخل اسم المسؤول
	@FindBy (css= "[ng-messages='requestForm.SirName.$error'] > .text-danger")
	WebElement SirNameErrorMsg;

	//من فضلك ادخل رقم بطاقة احوال المفوض بالتوقيع
	@FindBy (css= "[ng-messages='requestForm.idNumber.$error'] > .text-danger")
	WebElement SirIDNumErrorMsg;

	//من فضلك ادخل المهنة
	
	@FindBy (css= "[ng-messages='requestForm.JobName.$error'] > .text-danger")
	WebElement JobErrorMsg;
	
	
	@FindBy (css= "[ng-messages='requestForm.workStartDate.$error'] > .text-danger")
	WebElement StartDateErrorMsg;
	@FindBy (css= "[ng-messages='requestForm.workEndDate.$error'] > .text-danger")
	WebElement EndDateErrorMsg;

	public void CreateNewExperienceCertificateRequest(String JobName, String SirName, String SirIDNumber, String StartYear, String StartMonth, String OTP, String InvalidOTP) throws InterruptedException {
		Assert.assertTrue(RequestTitle.getText().contains("شهادة خبرة"));
		Thread.sleep(500);

		//fill Data
		setTextElementText(JobNameTxt, JobName);
		Thread.sleep(500);
		setTextElementText(SirNameTxt, SirName);
		Thread.sleep(500);
		setTextElementText(SirIDNumberTxt, SirIDNumber);
		
		//Pick Start Date
		clickButton(WorkStartDate);
		Thread.sleep(500);
		clickButton(YearPicker);
		Thread.sleep(500);
		Select FromYearOptionSelected = new Select (YearPicker);
		FromYearOptionSelected.selectByVisibleText(StartYear);
		clickButton(MonthPicker);
		Thread.sleep(500);
		Select FromMonthOptionSelected = new Select (MonthPicker);
		FromMonthOptionSelected.selectByVisibleText(StartMonth);
		clickButton(StartDay);
		StartDateStr = WorkStartDate.getAttribute("value");
	
		//Pick End Date
		clickButton(WorkEndDate);
		clickButton(TodayPicker);
		EndDateStr = WorkEndDate.getAttribute("value");
		Thread.sleep(500);

		//upload attachment
		AddAttachmentBtn.sendKeys(uploadPath);
		Thread.sleep(1000);

		//click on Next Button
		clickButton(NextBtn);
		Thread.sleep(1000);
	//	Assert.assertTrue(RequestTitle.getText().contains("تفاصيل شهادة خبرة"));
		Assert.assertTrue(SirNamePreviewTxt.getText().contains(SirName));
		Assert.assertTrue(SirIDNumberPreviewTxt.getText().contains(SirIDNumber));
		Assert.assertTrue(JobNamePreviewTxt.getText().contains(JobName));
		Assert.assertTrue(WorkStartDatePreviewTxt.getText().contains(StartDateStr));
		jse.executeScript("scrollBy(0,200)");
		Assert.assertTrue(WorkEndDatePreviewTxt.getText().contains(EndDateStr));
		Thread.sleep(1000);

		//Return Back to First Page
		clickButton(PreviousBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("شهادة خبرة"));
		Assert.assertTrue(SirNameTxt.getAttribute("value").contains(SirName));
		Assert.assertTrue(SirIDNumberTxt.getAttribute("value").contains(SirIDNumber));
		Assert.assertTrue(WorkStartDate.getAttribute("value").contains(StartDateStr));
		Assert.assertTrue(WorkEndDate.getAttribute("value").contains(EndDateStr));
		Assert.assertTrue(JobNameTxt.getAttribute("value").contains(JobName));
		Thread.sleep(1000);

		//click on Next Again
		clickButton(NextBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل شهادة خبرة"));
		Thread.sleep(1000);

		//Submit Request
		clickButton(SubmitBtn);
		Thread.sleep(1000);
		Assert.assertTrue(OTPMsg.getText().contains("أدخل كود التحقق الذي تم إرساله على الهاتف الجوال الخاص بك"));
		Thread.sleep(1000);

		//Enter Invalid OTP
		setTextElementText(OTPTxt, InvalidOTP);
		Thread.sleep(1000);
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
		Thread.sleep(3000);
		Assert.assertTrue(ConfirmTitle.getText().contains("تمت العملية بنجاح"));
		Thread.sleep(1000);
		clickButton(CloseQuestionnaire);
		Thread.sleep(1000);

	}

	public void CreateEmptyExperienceCertificateRequest () throws InterruptedException {
		Thread.sleep(500);
		Assert.assertTrue(RequestTitle.getText().contains("شهادة خبرة"));
		//Click on Next without entering data
		clickButton(NextBtn);
		Thread.sleep(500);
//		System.out.println(SirIDNumErrorMsg.getText());
		Assert.assertTrue(SirIDNumErrorMsg.getText().contains("رقم الهوية مطلوب"));
		Thread.sleep(500);
		System.out.println(SirNameErrorMsg.getText());
		Assert.assertTrue(SirNameErrorMsg.getText().contains("إسم السيد مطلوب"));
		Thread.sleep(500);
		System.out.println(StartDateErrorMsg.getText());
		Assert.assertTrue(StartDateErrorMsg.getText().contains("تاريخ بداية العمل مطلوب"));
		Thread.sleep(500);
		System.out.println(EndDateErrorMsg.getText());
		Assert.assertTrue(EndDateErrorMsg.getText().contains("تاريخ نهاية العمل مطلوب"));
		Thread.sleep(500);

		System.out.println(JobErrorMsg.getText());
		Assert.assertTrue(JobErrorMsg.getText().contains("المهنة مطلوبة"));
		Thread.sleep(500);


	}


}
