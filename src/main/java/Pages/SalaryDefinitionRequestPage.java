package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SalaryDefinitionRequestPage extends PageBase{

	public SalaryDefinitionRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}

//Definition 
	@FindBy (css = "[src='templateUrl()'] h2")
	WebElement RequestTitle;


	@FindBy (css = "[ng-model='RequestModel.Destination']")
	WebElement DestinationTxt;	
	@FindBy (css = ".sp-details-page > .row > div:nth-of-type(1) li:nth-of-type(1) > p")
	WebElement DestinationPreviewTxt;
	
	@FindBy (css = "[ng-model='RequestModel.EmployeeName']")
	WebElement EmpNameTxt;
	@FindBy (css = ".sp-details-page li:nth-of-type(1) > .CustomTextShow")
	WebElement EmpNamePreviewTxt;
	
	@FindBy (css = ".sp-details-page div:nth-of-type(2) li:nth-of-type(1) > .CustomTextShow")
	WebElement EmpNamePreviewTxt2;
	@FindBy (css = ".sp-details-page div:nth-of-type(2) li:nth-of-type(2) > .CustomTextShow")
	WebElement JobNamePreviewTxt2;
	
	@FindBy (css = "[ng-model='RequestModel.IdNumber']")
	WebElement IDNumberTxt;
	@FindBy (css = ".sp-details-page div:nth-of-type(2) li:nth-of-type(3) > p")
	WebElement IDNumberPreviewTxt;
	
	@FindBy (css = "[ng-model='RequestModel.IdSource']")
	WebElement IDSourceTxt;
	@FindBy (css = ".sp-details-page > .row > div:nth-of-type(1) li:nth-of-type(3)")
	WebElement IDSourcePreviewTxt;

	@FindBy (css = "[ng-model='mydate']")
	WebElement StartDate;
	@FindBy (css = ".sp-details-page > .row > div:nth-of-type(1) li:nth-of-type(4)")
	WebElement StartDatePreviewTxt;
	String DateStr;
	@FindBy (css = "[title='عرض سنة آخرى']")
	WebElement YearPicker;

	@FindBy (css = "[title='عرض شهر آخر']")
	WebElement MonthPicker;
					
	@FindBy (css = ".calendars-today")
	WebElement TodayPicker;

	@FindBy (css = "[ng-model='RequestModel.JobName']")
	WebElement JobNameTxt;
	@FindBy (css = ".sp-details-page li:nth-of-type(2) > .CustomTextShow")
	WebElement JobNamePreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.TotalSalary']")
	WebElement TotalSalaryTxt;
	@FindBy (css = "[ng-if='RequestModel.IsTotalSalary'] > p")
	WebElement TotalSalaryPreviewTxt;
	
	
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
	
	@FindBy (css= "[ng-if='!RequestDataModel.isAttachment'] h2")
	WebElement PreviewTitleAfterSubmission;
	
	
	//Error Messages Validations
	@FindBy (css= "[ng-messages='requestForm.Destination.$error'] > .text-danger")
	WebElement DestinationErrorMsg;
	@FindBy (css= "[ng-messages='requestForm.EmployeeName.$error'] > .text-danger")
	WebElement EmpNameErrorMsg;
	@FindBy (css= "[ng-messages='requestForm.IdNumber.$error'] > .text-danger")
	WebElement IDNumErrorMsg;
	@FindBy (css= "[ng-messages='requestForm.IdSource.$error'] > .text-danger")
	WebElement IDSourceErrorMsg;
	@FindBy (css= "[ng-messages='requestForm.WorkStartDate.$error'] > .text-danger")
	WebElement DateErrorMsg;
	@FindBy (css= "[ng-messages='requestForm.TotalSalary.$error'] > .text-danger")
	WebElement SalaryErrorMsg;
	
	@FindBy (css= "[ng-messages='requestForm.JobName.$error'] > .text-danger")
	WebElement JobErrorMsg;
	
	@FindBy (css= "[ng-messages='requestForm.Nationality.$error'] > .text-danger")
	WebElement NationErrorMsg;

	// Fields of NonSaudianSalaryRequest
	@FindBy (css= "[ng-model='RequestModel.Nationality']")
	WebElement NationalityTxt;
	@FindBy (css= ".sp-details-page li:nth-of-type(5) > p")
	WebElement NationalityPreviewTxt;
	
	 
	public void CreateNewSaudianSalaryRequestWithTotalSalary (String Destination, String EmpName, String IDNumber, String IDSource, String JobName, String TotalSalary, String OTP, String InvalidOTP) throws InterruptedException {
		Assert.assertTrue(RequestTitle.getText().contains("طلب تعريف راتب سعودى"));
		scrollToTheMiddle();
		Thread.sleep(1000);
	
		//fill Data
		setTextElementText(DestinationTxt, Destination);
		Thread.sleep(500);
		setTextElementText(EmpNameTxt, EmpName);
		Thread.sleep(500);
		setTextElementText(IDNumberTxt, IDNumber);
		Thread.sleep(500);
		setTextElementText(IDSourceTxt, IDSource);
		Thread.sleep(500);
		setTextElementText(JobNameTxt, JobName);
		Thread.sleep(500);
		setTextElementText(TotalSalaryTxt, TotalSalary);
		Thread.sleep(500);
		clickButton(StartDate);
		Thread.sleep(1000);
		clickButton(TodayPicker);
		DateStr = StartDate.getAttribute("value");
		scrollToBottom();
		Thread.sleep(500);
		
		//upload attachment
		AddAttachmentBtn.sendKeys(uploadPath);
		Thread.sleep(1000);
		
		//click on Next Button
		clickButton(NextBtn);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,-500)");
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل طلب تعريف راتب سعودى"));
		Assert.assertTrue(DestinationPreviewTxt.getText().contains(Destination));
		Assert.assertTrue(EmpNamePreviewTxt.getText().contains(EmpName));
		Assert.assertTrue(JobNamePreviewTxt.getText().contains(JobName));
		Assert.assertTrue(IDSourcePreviewTxt.getText().contains(IDSource));
		Assert.assertTrue(IDNumberPreviewTxt.getText().contains(IDNumber));
		Assert.assertTrue(StartDatePreviewTxt.getText().contains(DateStr));
		Assert.assertTrue(TotalSalaryPreviewTxt.getText().contains(TotalSalary));
		Thread.sleep(1000);
		
		//Return Back to First Page
		clickButton(PreviousBtn);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,-200)");
		Assert.assertTrue(RequestTitle.getText().contains("طلب تعريف راتب سعودى"));
		Assert.assertTrue(DestinationTxt.getAttribute("value").contains(Destination));
		Assert.assertTrue(EmpNameTxt.getAttribute("value").contains(EmpName));
		Assert.assertTrue(JobNameTxt.getAttribute("value").contains(JobName));
		Assert.assertTrue(IDSourceTxt.getAttribute("value").contains(IDSource));
		Assert.assertTrue(IDNumberTxt.getAttribute("value").contains(IDNumber));
		Assert.assertTrue(StartDate.getAttribute("value").contains(DateStr));
		Assert.assertTrue(TotalSalaryTxt.getAttribute("value").contains(TotalSalary));
		
		//click on Next Again
		clickButton(NextBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل طلب تعريف راتب سعودى"));
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
	
		Thread.sleep(2000);
		Assert.assertTrue(ConfirmTitle.getText().contains("تأكيد اضافة الطلب"));
		Thread.sleep(1000);
		clickButton(ConfirmBtn);
		Thread.sleep(5000);
		Assert.assertTrue(ConfirmTitle.getText().contains("تمت العملية بنجاح"));
		Thread.sleep(1000);
		clickButton(CloseQuestionnaire);
		Thread.sleep(1000);
		Assert.assertTrue(PreviewTitleAfterSubmission.getText().contains("تفاصيل طلب تعريف راتب سعودى"));
		Assert.assertTrue(DestinationPreviewTxt.getText().contains(Destination));
		Assert.assertTrue(EmpNamePreviewTxt.getText().contains(EmpName));
		Assert.assertTrue(JobNamePreviewTxt.getText().contains(JobName));
		Assert.assertTrue(IDSourcePreviewTxt.getText().contains(IDSource));
		Assert.assertTrue(IDNumberPreviewTxt.getText().contains(IDNumber));
		Assert.assertTrue(StartDatePreviewTxt.getText().contains(DateStr));
		Assert.assertTrue(TotalSalaryPreviewTxt.getText().contains(TotalSalary));
		Thread.sleep(1000);
		clickButton(DownloadBtn);
		Thread.sleep(5000);
		scrollUp();
	}
	
	public void CreateEmptySaudianSalaryRequest () throws InterruptedException {
		Assert.assertTrue(RequestTitle.getText().contains("طلب تعريف راتب سعودى"));
		scrollToBottom();
		//Click on Next without entering data
		clickButton(NextBtn);
		Thread.sleep(1000);
		System.out.println(DestinationErrorMsg.getText());
		Assert.assertTrue(DestinationErrorMsg.getText().contains("من فضلك ادخل السادة"));
		Thread.sleep(1000);
		System.out.println(EmpNameErrorMsg.getText());
		Assert.assertTrue(EmpNameErrorMsg.getText().contains("من فضلك ادخل اسم الموظف"));
		Thread.sleep(1000);
		System.out.println(IDNumErrorMsg.getText());
		Assert.assertTrue(IDNumErrorMsg.getText().contains("من فضلك ادخل رقم الهوية"));
		Thread.sleep(1000);
		System.out.println(IDSourceErrorMsg.getText());
		Assert.assertTrue(IDSourceErrorMsg.getText().contains("من فضلك ادخل مصدر الهوية"));
		Thread.sleep(1000);
		System.out.println(DateErrorMsg.getText());
		Assert.assertTrue(DateErrorMsg.getText().contains("تاريخ بداية العمل مطلوب"));
		Thread.sleep(1000);
		System.out.println(SalaryErrorMsg.getText());
		Assert.assertTrue(SalaryErrorMsg.getText().contains("ادخل قيمة الراتب الشهرى"));
		Thread.sleep(1000);
	}
	
	public void CreateNewNonSaudianSalaryRequestWithTotalSalary (String Destination, String EmpName, String IDNumber, String IDSource, String JobName, String TotalSalary, String Nationality, String OTP, String InvalidOTP) throws InterruptedException {
		Assert.assertTrue(RequestTitle.getText().contains("طلب تعريف راتب غير سعودي"));
		scrollToTheMiddle();
		Thread.sleep(1000);
		
		//fill Data
		setTextElementText(DestinationTxt, Destination);
		Thread.sleep(500);
		setTextElementText(EmpNameTxt, EmpName);
		Thread.sleep(500);
		setTextElementText(IDNumberTxt, IDNumber);
		Thread.sleep(500);
		setTextElementText(IDSourceTxt, IDSource);
		Thread.sleep(500);
		setTextElementText(JobNameTxt, JobName);
		Thread.sleep(500);
		setTextElementText(TotalSalaryTxt, TotalSalary);
		Thread.sleep(500);
		setTextElementText(NationalityTxt, Nationality);
		Thread.sleep(500);
		clickButton(StartDate);
		clickButton(TodayPicker);
		DateStr = StartDate.getAttribute("value");
		scrollToBottom();
		Thread.sleep(1000);
		
		//upload attachment
		AddAttachmentBtn.sendKeys(uploadPath);
		Thread.sleep(1000);
		
		//click on Next Button
				clickButton(NextBtn);
				Thread.sleep(1000);
				jse.executeScript("scrollBy(0,-500)");
				Assert.assertTrue(RequestTitle.getText().contains("تفاصيل طلب تعريف راتب غير سعودى"));
				Assert.assertTrue(DestinationPreviewTxt.getText().contains(Destination));
				Assert.assertTrue(EmpNamePreviewTxt2.getText().contains(EmpName));
				Assert.assertTrue(JobNamePreviewTxt2.getText().contains(JobName));
				Assert.assertTrue(IDSourcePreviewTxt.getText().contains(IDSource));
				Assert.assertTrue(IDNumberPreviewTxt.getText().contains(IDNumber));
				Assert.assertTrue(StartDatePreviewTxt.getText().contains(DateStr));
				Assert.assertTrue(TotalSalaryPreviewTxt.getText().contains(TotalSalary));
				Assert.assertTrue(NationalityPreviewTxt.getText().contains(Nationality));
				Thread.sleep(1000);
				
				//Return Back to First Page
				clickButton(PreviousBtn);
				Thread.sleep(1000);
				jse.executeScript("scrollBy(0,-200)");
				Assert.assertTrue(RequestTitle.getText().contains("طلب تعريف راتب غير سعودي"));
				Assert.assertTrue(DestinationTxt.getAttribute("value").contains(Destination));
				Assert.assertTrue(EmpNameTxt.getAttribute("value").contains(EmpName));
				Assert.assertTrue(JobNameTxt.getAttribute("value").contains(JobName));
				Assert.assertTrue(IDSourceTxt.getAttribute("value").contains(IDSource));
				Assert.assertTrue(IDNumberTxt.getAttribute("value").contains(IDNumber));
				Assert.assertTrue(StartDate.getAttribute("value").contains(DateStr));
				Assert.assertTrue(TotalSalaryTxt.getAttribute("value").contains(TotalSalary));
				Assert.assertTrue(NationalityTxt.getAttribute("value").contains(Nationality));
				Thread.sleep(2000);		

		
		//click on Next Again
		clickButton(NextBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفاصيل طلب تعريف راتب غير سعودى"));
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
		Thread.sleep(5000);
		Assert.assertTrue(ConfirmTitle.getText().contains("تمت العملية بنجاح"));
		Thread.sleep(1000);
		clickButton(CloseQuestionnaire);
		Thread.sleep(1000);
		Assert.assertTrue(PreviewTitleAfterSubmission.getText().contains("تفاصيل طلب تعريف راتب غير سعودى"));
		Thread.sleep(1000);
		Assert.assertTrue(DestinationPreviewTxt.getText().contains(Destination));
		Assert.assertTrue(EmpNamePreviewTxt2.getText().contains(EmpName));
		Assert.assertTrue(JobNamePreviewTxt2.getText().contains(JobName));
		Assert.assertTrue(IDSourcePreviewTxt.getText().contains(IDSource));
		Assert.assertTrue(IDNumberPreviewTxt.getText().contains(IDNumber));
		Assert.assertTrue(StartDatePreviewTxt.getText().contains(DateStr));
		Assert.assertTrue(TotalSalaryPreviewTxt.getText().contains(TotalSalary));
		Assert.assertTrue(NationalityPreviewTxt.getText().contains(Nationality));
		Thread.sleep(1000);
		clickButton(DownloadBtn);
		Thread.sleep(5000);
		scrollUp();
	}
	
	public void CreateEmptyNonSaudianSalaryRequest () throws InterruptedException {
		scrollToBottom();
		//Click on Next without entering data
		clickButton(NextBtn);
		Thread.sleep(1000);
		System.out.println(DestinationErrorMsg.getText());
		Assert.assertTrue(DestinationErrorMsg.getText().contains("من فضلك ادخل السادة"));
		Thread.sleep(1000);
		System.out.println(EmpNameErrorMsg.getText());
		Assert.assertTrue(EmpNameErrorMsg.getText().contains("من فضلك ادخل اسم الموظف"));
		Thread.sleep(1000);
		System.out.println(IDNumErrorMsg.getText());
		Assert.assertTrue(IDNumErrorMsg.getText().contains("من فضلك ادخل رقم الهوية"));
		Thread.sleep(1000);
		System.out.println(IDSourceErrorMsg.getText());
		Assert.assertTrue(IDSourceErrorMsg.getText().contains("من فضلك ادخل مصدر الهوية"));
		Thread.sleep(1000);
		System.out.println(DateErrorMsg.getText());
		Assert.assertTrue(DateErrorMsg.getText().contains("تاريخ بداية العمل مطلوب"));
		Thread.sleep(1000);
		System.out.println(SalaryErrorMsg.getText());
		Assert.assertTrue(SalaryErrorMsg.getText().contains("ادخل قيمة الراتب الشهرى"));
		Thread.sleep(1000);
		System.out.println(JobErrorMsg.getText());
		Assert.assertTrue(JobErrorMsg.getText().contains("من فضلك ادخل المسمى الوظيفى"));
		Thread.sleep(1000);		
		System.out.println(NationErrorMsg.getText());
		Assert.assertTrue(NationErrorMsg.getText().contains("من فضلك ادخل الجنسية"));
		Thread.sleep(1000);
	}

}
