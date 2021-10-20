package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class WorkContractRequestPage extends PageBase{

	public WorkContractRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	

	//Definition 
		@FindBy (css = "[src='templateUrl()'] h2")
		WebElement RequestTitle;
		
		@FindBy (css = "[ng-model='RequestModel.ContractDuration']")
		WebElement ContractDurationTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(2) > p")
		WebElement ContractDurationPreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.SirName']")
		WebElement SirNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(3) > p")
		WebElement SirNamePreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.SirIdNumber']")
		WebElement SirIDNumberTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(4) > p")
		WebElement SirIDNumberPreviewTxt;

		@FindBy (css = "[ng-model='RequestModel.EmployeeName']")
		WebElement EmpNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(5) > p")
		WebElement EmpNamePreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.Passport']")
		WebElement PassportNumberTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(7) > p")
		WebElement PassportNumberPreviewTxt;

		@FindBy (css = "[ng-model='mydate']")
		WebElement StartDate;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(1) > p")
		WebElement StartDatePreview;
		String DateStr;
		
		@FindBy (css = "[title='عرض سنة آخرى']")
		WebElement YearPicker;

		@FindBy (css = "[title='عرض شهر آخر']")
		WebElement MonthPicker;

		@FindBy (css = ".calendars-today")
		WebElement TodayPicker;

		@FindBy (css= "[ng-model='RequestModel.Nationality']")
		WebElement NationalityTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(6) > p")
		WebElement NationalityPreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.JobName']")
		WebElement JobNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(8) > p")
		WebElement JobNamePreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.Salary']")
		WebElement SalaryTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(9) > p")
		WebElement SalaryPreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.VacationDays']")
		WebElement	VacationDaysTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(10) > p")
		WebElement VacationDaysPreviewTxt;
		
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
		//من فضلك ادخل مدة العقد بالسنوات
		@FindBy (css= "[ng-messages='requestForm.ContractDuration.$error'] > .text-danger")
		WebElement ContractDurationErrorMsg;
		
		//من فضلك ادخل اسم المسؤول
		@FindBy (css= "[ng-messages='requestForm.SirName.$error'] > .text-danger")
		WebElement SirNameErrorMsg;
		
		//من فضلك ادخل رقم بطاقة احوال المفوض بالتوقيع
		@FindBy (css= "[ng-messages='requestForm.SirIdNumber.$error'] > .text-danger")
		WebElement SirIDNumErrorMsg;
		
		//من فضلك ادخل إسم الموظف
		@FindBy (css= "[ng-messages='requestForm.employeeName.$error'] > .text-danger")
		WebElement EmpNameErrorMsg;
		
		//من فضلك ادخل رقم جواز السفر
		@FindBy (css= "[ng-messages='requestForm.Passport.$error'] > .text-danger")
		WebElement PassNumErrorMsg;
		
		//من فضلك ادخل عدد أيام الأجازة
		@FindBy (css= "[ng-messages='requestForm.VacationDays.$error'] > .text-danger")
		WebElement VacationDaysErrorMsg;
		
		//من فضلك ادخل الراتب الشهري
		@FindBy (css= "[ng-messages='requestForm.Salary.$error'] > .text-danger")
		WebElement SalaryErrorMsg;
		
		//من فضلك ادخل المهنة
		@FindBy (css= "[ng-messages='requestForm.JobName.$error'] > .text-danger")
		WebElement JobErrorMsg;
		
		//من فضلك ادخل الجنسية
		@FindBy (css= "[ng-messages='requestForm.Nationality.$error'] > .text-danger")
		WebElement NationErrorMsg;

		public void CreateNewWorkContractRequest(String ContractDuration, String SirName, String SirIDNumber, String EmpName, String PassNumber, String Nationality, String JobName, String Salary, String VacationDays, String OTP, String InvalidOTP) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("عقد العمل"));
			scrollToTheMiddle();
			Thread.sleep(500);
		
			//fill Data
			clickButton(StartDate);
			Thread.sleep(500);
			clickButton(TodayPicker);
			DateStr = StartDate.getAttribute("value");
			Thread.sleep(500);
			setTextElementText(ContractDurationTxt, ContractDuration);
			Thread.sleep(500);
			setTextElementText(SirNameTxt, SirName);
			Thread.sleep(500);
			setTextElementText(SirIDNumberTxt, SirIDNumber);
			Thread.sleep(500);
			setTextElementText(EmpNameTxt, EmpName);
			Thread.sleep(500);
			setTextElementText(PassportNumberTxt, PassNumber);
			Thread.sleep(500);
			setTextElementText(NationalityTxt, Nationality);
			Thread.sleep(500);
			setTextElementText(JobNameTxt, JobName);
			Thread.sleep(500);
			setTextElementText(SalaryTxt, Salary);
			Thread.sleep(500);
			setTextElementText(VacationDaysTxt, VacationDays);
			Thread.sleep(500);
			scrollToBottom();
			Thread.sleep(500);
			
			//upload attachment
			AddAttachmentBtn.sendKeys(uploadPath);
			Thread.sleep(1000);
			
			//click on Next Button
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("طلب تصديق عقد عمل"));
			Assert.assertTrue(StartDatePreview.getText().contains(DateStr));
			Assert.assertTrue(ContractDurationPreviewTxt.getText().contains(ContractDuration));
			Assert.assertTrue(SirNamePreviewTxt.getText().contains(SirName));
			Assert.assertTrue(SirIDNumberPreviewTxt.getText().contains(SirIDNumber));
			Assert.assertTrue(EmpNamePreviewTxt.getText().contains(EmpName));
			Assert.assertTrue(NationalityPreviewTxt.getText().contains(Nationality));
			Assert.assertTrue(PassportNumberPreviewTxt.getText().contains(PassNumber));
			Assert.assertTrue(JobNamePreviewTxt.getText().contains(JobName));
			Assert.assertTrue(SalaryPreviewTxt.getText().contains(Salary));
			Assert.assertTrue(VacationDaysPreviewTxt.getText().contains(VacationDays));
			Thread.sleep(1000);
			
			//Return Back to First Page
			clickButton(PreviousBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("عقد العمل"));
			Assert.assertTrue(StartDate.getAttribute("value").contains(DateStr));
			Assert.assertTrue(ContractDurationTxt.getAttribute("value").contains(ContractDuration));
			Assert.assertTrue(SirNameTxt.getAttribute("value").contains(SirName));
			Assert.assertTrue(SirIDNumberTxt.getAttribute("value").contains(SirIDNumber));
			Assert.assertTrue(EmpNameTxt.getAttribute("value").contains(EmpName));
			Assert.assertTrue(NationalityTxt.getAttribute("value").contains(Nationality));
			Assert.assertTrue(PassportNumberTxt.getAttribute("value").contains(PassNumber));
			Assert.assertTrue(JobNameTxt.getAttribute("value").contains(JobName));
			Assert.assertTrue(SalaryTxt.getAttribute("value").contains(Salary));
			Assert.assertTrue(VacationDaysTxt.getAttribute("value").contains(VacationDays));
			Thread.sleep(1000);
			Thread.sleep(1000);
			
			//click on Next Again
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("طلب تصديق عقد عمل"));
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
		
		public void CreateEmptyWorkContractRequest () throws InterruptedException {
			scrollUp();
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("عقد العمل"));
			Thread.sleep(1000);
			scrollToBottom();
			//Click on Next without entering data
			clickButton(NextBtn);
			Thread.sleep(500);
			scrollToTheMiddle();
			Thread.sleep(500);
			System.out.println(ContractDurationErrorMsg.getText());
			Assert.assertTrue(ContractDurationErrorMsg.getText().contains("من فضلك ادخل مدة العقد بالسنوات"));
			Thread.sleep(500);
			System.out.println(EmpNameErrorMsg.getText());
			Assert.assertTrue(EmpNameErrorMsg.getText().contains("من فضلك ادخل إسم الموظف"));
			Thread.sleep(500);
			System.out.println(SirIDNumErrorMsg.getText());
			Assert.assertTrue(SirIDNumErrorMsg.getText().contains("من فضلك ادخل رقم بطاقة احوال المفوض بالتوقيع"));
			Thread.sleep(500);
			System.out.println(SirNameErrorMsg.getText());
			Assert.assertTrue(SirNameErrorMsg.getText().contains("من فضلك ادخل اسم المسؤول"));
			Thread.sleep(500);
			System.out.println(PassNumErrorMsg.getText());
			Assert.assertTrue(PassNumErrorMsg.getText().contains("من فضلك ادخل رقم جواز السفر"));
			Thread.sleep(500);
			System.out.println(SalaryErrorMsg.getText());
			Assert.assertTrue(SalaryErrorMsg.getText().contains("من فضلك ادخل الراتب الشهري"));
			Thread.sleep(500);
			
			System.out.println(VacationDaysErrorMsg.getText());
			Assert.assertTrue(VacationDaysErrorMsg.getText().contains("من فضلك ادخل عدد أيام الأجازة"));
			Thread.sleep(500);
		
			System.out.println(NationErrorMsg.getText());
			Assert.assertTrue(NationErrorMsg.getText().contains("من فضلك ادخل الجنسية"));
			Thread.sleep(500);
		
			System.out.println(JobErrorMsg.getText());
			Assert.assertTrue(JobErrorMsg.getText().contains("من فضلك ادخل المهنة"));
			Thread.sleep(500);
			
		
		}
		

}
