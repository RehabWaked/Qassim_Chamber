package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CustomsCommitmentRequestPage extends PageBase{

	public CustomsCommitmentRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	

	//Definition 
		@FindBy (css = "[src='templateUrl()'] h2")
		WebElement RequestTitle;
		
		@FindBy (css = "[ng-model='RequestModel.MM_AdrPOBox']")
		WebElement POBoxTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(8) > p")
		WebElement POBoxPreviewTxt;
	
		@FindBy (css = "[ng-model='RequestModel.EstPhone']")
		WebElement PhoneNoTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(3) > p")
		WebElement PhoneNoPreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.IbanNo']")
		WebElement IBANNoTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(9) > p")
		WebElement IBANNoPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.IbanNo.$error'] > .text-danger")
		WebElement IBANNoErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.EstFax']")
		WebElement FaxNoTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(4)")
		WebElement FaxNoPreviewTxt;
			
		@FindBy (css = "[ng-model='RequestModel.ImportNo']")
		WebElement ImportNoTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(10) > p")
		WebElement ImportNoPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.ImportNo.$error'] > .text-danger")
		WebElement ImportNoErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.CustomsName']")
		WebElement CustomsNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(5) > p")
		WebElement CustomsNamePreviewTxt;
		@FindBy (css = ".text-danger")
		WebElement CustomsNameErrorMsg;
		
		
	
		//Receive Date Picker
		
		@FindBy (css = "[ng-model='mydate']")
		WebElement ReceiveDate;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(6) > p")
		WebElement ReceiveDatePreview;
		@FindBy (css = "[ng-messages='requestForm.ReceiveDate.$error'] > .text-danger")
		WebElement ReceiveDateErrorMsg;
		String DateStr;
		
		@FindBy (css = "[title='عرض سنة آخرى']")
		WebElement YearPicker;

		@FindBy (css = "[title='عرض شهر آخر']")
		WebElement MonthPicker;

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

		@FindBy (css= ".m-top-md > [back='عوده'] > .sp-main-button")
		WebElement BackBtn;
		
		@FindBy (css= "[ng-if='RequestModel.RequestStatus === 4']")
		WebElement DownloadBtn;
		@FindBy (css= "[ng-class='config.title']")
		WebElement WarningMsg;
		
		
		public void CreateNewCustomsCommitmentRequest(String POBox, String PhoneNo, String FaxNo, String IBANNo, String ImportNo, String CustomsName, String OTP, String InvalidOTP) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("تعهد جمركى"));
			scrollToTheMiddle();
			Thread.sleep(500);
		
			//fill Data
			Thread.sleep(1000);

			setTextElementText(POBoxTxt, POBox);
			PhoneNoTxt.clear();
			setTextElementText(PhoneNoTxt, PhoneNo);
			setTextElementText(FaxNoTxt, FaxNo);
			setTextElementText(IBANNoTxt, IBANNo);
			setTextElementText(ImportNoTxt, ImportNo);
			setTextElementText(CustomsNameTxt, CustomsName);
			Thread.sleep(1000);

			clickButton(ReceiveDate);
			Thread.sleep(1000);
			clickButton(TodayPicker);
			DateStr = ReceiveDate.getAttribute("value");
			
			//upload attachment
			AddAttachmentBtn.sendKeys(uploadPath);
			Thread.sleep(1000);

			//click on Next Button
			clickButton(NextBtn);
			Thread.sleep(1000);

			// Assert on Data Entered are displayed correctly on the preview page
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تعهد جمركى"));
			Assert.assertTrue(POBoxPreviewTxt.getText().contains(POBox));
			Assert.assertTrue(PhoneNoPreviewTxt.getText().contains(PhoneNo));
			Assert.assertTrue(IBANNoPreviewTxt.getText().contains(IBANNo));
			Assert.assertTrue(FaxNoPreviewTxt.getText().contains(FaxNo));
			Assert.assertTrue(ImportNoPreviewTxt.getText().contains(ImportNo));
			Assert.assertTrue(CustomsNamePreviewTxt.getText().contains(CustomsName));
			Thread.sleep(1000);
			
			//Return Back to First Page and Assert that data still exist
			scrollToBottom();
			clickButton(PreviousBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تعهد جمركى"));
			Assert.assertTrue(POBoxTxt.getAttribute("value").contains(POBox));
			Assert.assertTrue(PhoneNoTxt.getAttribute("value").contains(PhoneNo));
			Assert.assertTrue(FaxNoTxt.getAttribute("value").contains(FaxNo));
			Assert.assertTrue(IBANNoTxt.getAttribute("value").contains(IBANNo));
			Assert.assertTrue(ImportNoTxt.getAttribute("value").contains(ImportNo));
			Assert.assertTrue(CustomsNameTxt.getAttribute("value").contains(CustomsName));
			Thread.sleep(1000);
			
			//click on Next Again
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تعهد جمركى"));
			Thread.sleep(1000);
			
			//Submit Request
			scrollToBottom();
			Thread.sleep(1000);
			clickButton(SubmitBtn);
			Thread.sleep(1000);
			
//If Request Required OTP		
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
			Thread.sleep(5000);
			clickButton(DownloadBtn);
			Thread.sleep(5000);


		}
		
		public void CreateEmptyCustomsCommitmentRequest () throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("تعهد جمركى"));
			Thread.sleep(1000);
			//Click on Next without entering data
			clickButton(NextBtn);
			jse.executeScript("scrollBy(0,-800)");
			System.out.println(ReceiveDateErrorMsg.getText());
			Assert.assertTrue(ReceiveDateErrorMsg.getText().contains("تاريخ الاستلام مطلوب"));
			System.out.println(IBANNoErrorMsg.getText());
			Assert.assertTrue(IBANNoErrorMsg.getText().contains("من فضلك ادخل رقم بيان استيراد"));
			System.out.println(ImportNoErrorMsg.getText());
			Assert.assertTrue(ImportNoErrorMsg.getText().contains("من فضلك ادخل رقم بيان استيراد"));
		//	Assert.assertTrue(CustomsNameErrorMsg.getText().contains("من فضلك ادخل اسم الجمرك"));
			Thread.sleep(1000);

		}
		

}
