package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AdmissionLetterRequestPage extends PageBase{

	public AdmissionLetterRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	

	//Definition 
		@FindBy (css = "[src='templateUrl()'] h2")
		WebElement RequestTitle;
		
		@FindBy (css = "[src='templateUrl()'] .sp-main-form > div:nth-of-type(2) > div:nth-of-type(1)")
		WebElement EnterpriseNameTxt;
		@FindBy (css = "[ng-model='RequestModel.SirName']")
		WebElement SirNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(1) > p")
		WebElement SirNamePreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.SirIdNumber']")
		WebElement IDNumberTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(2) > p")
		WebElement IDNumberPreviewTxt;
	
		@FindBy (css = "[ng-model='mydate']")
		WebElement StartDate;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(4)")
		WebElement StartDatePreview;
		String DateStr, EnterpriseNameStr;
		
		@FindBy (css = "[title='عرض سنة آخرى']")
		WebElement YearPicker;

		@FindBy (css = "[title='عرض شهر آخر']")
		WebElement MonthPicker;

		@FindBy (css = ".calendars-today")
		WebElement TodayPicker;

		@FindBy (css= "[ng-model='RequestModel.Nationality']")
		WebElement NationalityTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(3) > p")
		WebElement NationalityPreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.JobName']")
		WebElement JobNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(5) > p")
		WebElement JobNamePreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.Salary']")
		WebElement SalaryTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(6)")
		WebElement SalaryPreviewTxt;
		

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
		WebElement IDNumErrorMsg;

		//من فضلك ادخل الراتب الشهري
		@FindBy (css= "[ng-messages='requestForm.Salary.$error'] > .text-danger")
		WebElement SalaryErrorMsg;
		
		//من فضلك ادخل المهنة
		@FindBy (css= "[ng-messages='requestForm.JobName.$error'] > .text-danger")
		WebElement JobErrorMsg;
		
		//من فضلك ادخل الجنسية
		@FindBy (css= "[ng-messages='requestForm.Nationality.$error'] > .text-danger")
		WebElement NationErrorMsg;
		@FindBy (css= "[ng-messages='requestForm.WorkStartDate.$error'] > .text-danger")
		WebElement DateErrorMsg;

		public void CreateNewAdmissionLetterRequest(String SirName, String IDNumber, String Nationality, String JobName, String Salary, String OTP, String InvalidOTP) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("خطاب تعريف لطلب استقدام"));
			scrollToTheMiddle();
			Thread.sleep(500);
		
			//fill Data
			setTextElementText(IDNumberTxt, IDNumber);
			setTextElementText(SirNameTxt, SirName);
			setTextElementText(NationalityTxt, Nationality);
			setTextElementText(JobNameTxt, JobName);
			setTextElementText(SalaryTxt, Salary);
			
			clickButton(StartDate);
			Thread.sleep(500);
			clickButton(TodayPicker);
			DateStr = StartDate.getAttribute("value");
			EnterpriseNameStr = EnterpriseNameTxt.getAttribute("value");

			Thread.sleep(500);
			scrollToBottom();
			Thread.sleep(500);
			
			//upload attachment
			AddAttachmentBtn.sendKeys(uploadPath);
			Thread.sleep(1000);
			
			//click on Next Button
			clickButton(NextBtn);
			Thread.sleep(1000);
			// Assert on Data Entered are displayed correctly on the preview page
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل خطاب تعريف لطلب استقدام"));
			Assert.assertTrue(StartDatePreview.getText().contains(DateStr));
			Assert.assertTrue(SirNamePreviewTxt.getText().contains(SirName));
			Assert.assertTrue(IDNumberPreviewTxt.getText().contains(IDNumber));
			Assert.assertTrue(NationalityPreviewTxt.getText().contains(Nationality));
			Assert.assertTrue(JobNamePreviewTxt.getText().contains(JobName));
			Assert.assertTrue(SalaryPreviewTxt.getText().contains(Salary));
			Thread.sleep(1000);
			
			//Return Back to First Page and Assert that data still exist
			clickButton(PreviousBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("خطاب تعريف لطلب استقدام"));
			Assert.assertTrue(StartDate.getAttribute("value").contains(DateStr));
			Assert.assertTrue(SirNameTxt.getAttribute("value").contains(SirName));
			Assert.assertTrue(IDNumberTxt.getAttribute("value").contains(IDNumber));
			Assert.assertTrue(NationalityTxt.getAttribute("value").contains(Nationality));
			Assert.assertTrue(JobNameTxt.getAttribute("value").contains(JobName));
			Assert.assertTrue(SalaryTxt.getAttribute("value").contains(Salary));
			Thread.sleep(1000);
			
			//click on Next Again
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل خطاب تعريف لطلب استقدام"));
			Thread.sleep(1000);
			
			//Submit Request
			clickButton(SubmitBtn);
			Thread.sleep(1000);
			
//If Request Required OTP		
//			Assert.assertTrue(OTPMsg.getText().contains("أدخل كود التحقق الذي تم إرساله على الهاتف الجوال الخاص بك"));
//			Thread.sleep(1000);
//			
//			//Enter Invalid OTP
//			setTextElementText(OTPTxt, InvalidOTP);
//			Thread.sleep(1000);
//			Assert.assertTrue(WarningMsg.getText().contains("كود التحقق غير صحيح"));
//			Thread.sleep(1000);
//			
//			//Enter Valid OTP
//			OTPTxt.clear();
//			Thread.sleep(1000);
//			setTextElementText(OTPTxt, OTP);
//		
//			Thread.sleep(1000);
			Assert.assertTrue(ConfirmTitle.getText().contains("تأكيد اضافة الطلب"));
			Thread.sleep(1000);
			clickButton(ConfirmBtn);
			Thread.sleep(3000);
			Assert.assertTrue(ConfirmTitle.getText().contains("تمت العملية بنجاح"));
			Thread.sleep(1000);
			clickButton(CloseQuestionnaire);
			Thread.sleep(1000);
			
		}
		
		public void CreateEmptyAdmissionLetterRequest () throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("خطاب تعريف لطلب استقدام"));
			Thread.sleep(1000);
			//Click on Next without entering data
			clickButton(NextBtn);
			jse.executeScript("scrollBy(0,-500)");
			System.out.println(DateErrorMsg.getText());
			Assert.assertTrue(DateErrorMsg.getText().contains("تاريخ بدء العمل مطلوب"));
			System.out.println(IDNumErrorMsg.getText());
			Assert.assertTrue(IDNumErrorMsg.getText().contains("رقم الهوية مطلوب"));
			System.out.println(SirNameErrorMsg.getText());
			Assert.assertTrue(SirNameErrorMsg.getText().contains("إسم السيد مطلوب"));
			System.out.println(SalaryErrorMsg.getText());
			Assert.assertTrue(SalaryErrorMsg.getText().contains("الراتب الشهرى مطلوب"));
			System.out.println(NationErrorMsg.getText());
			Assert.assertTrue(NationErrorMsg.getText().contains("الجنسية مطلوبة"));
			System.out.println(JobErrorMsg.getText());
			Assert.assertTrue(JobErrorMsg.getText().contains("المهنة مطلوبة"));
			Thread.sleep(1000);

		}
		

}
