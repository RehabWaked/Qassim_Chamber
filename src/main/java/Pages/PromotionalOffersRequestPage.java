package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class PromotionalOffersRequestPage extends PageBase{

	public PromotionalOffersRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	

	//Definition 
		@FindBy (css = "[src='templateUrl()'] h2")
		WebElement RequestTitle;
		
		@FindBy (css = "[ng-model='RequestModel.PromotionName']")
		WebElement PromoNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(1) > p")
		WebElement PromoNamePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.PromotionName.$error'] > .text-danger")
		WebElement PromoNameErrorMsg;
	
		@FindBy (css = "[ng-model='RequestModel.Place']")
		WebElement LocationTxt;
		
		@FindBy (css = ".custom-center-ul > li:nth-of-type(4) > p")
		WebElement LocationPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.Place.$error'] > .text-danger")
		WebElement LocationErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.Notes']")
		WebElement NotesTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(6)")
		WebElement NotesPreviewTxt;
		
		@FindBy (css = "[ng-model='RequestModel.RequestReason']")
		WebElement ReasonTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(5)")
		WebElement ReasonPreviewTxt;	
		
	
		//Start Date Picker
		@FindBy (css = ".WSSCalender[name='StartDate']")
		WebElement StartDate;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(2)")
		WebElement StartDatePreview;
		@FindBy (css = ".WSSCalender[name='EndDate']")
		WebElement EndDate;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(3)")
		WebElement EndDatePreview;		
		String StartDateStr, EndDateStr;
		
		@FindBy (css = "[title='عرض سنة آخرى']")
		WebElement YearPicker;

		@FindBy (css = "[title='عرض شهر آخر']")
		WebElement MonthPicker;

		@FindBy (css = "[title='اختر يوم السبت, يناير 1']")
		WebElement StartDay;
		@FindBy (css = "[title='اختر يوم الخميس, مارس 31']")
		WebElement EndDay;
		@FindBy (css = ".calendars-today")
		WebElement ToDay;
		
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
		
		
		public void CreateNewPromotionalOffersRequest(String PromoName, String Location, String Notes, String Reason, String Year, String StartMonth,String EndMonth,  String OTP, String InvalidOTP) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("طلب تصريح عروض ترويجية"));
	
			//fill Data
			Thread.sleep(1000);
			setTextElementText(PromoNameTxt, PromoName);
			setTextElementText(LocationTxt, Location);
			setTextElementText(NotesTxt, Notes);
			setTextElementText(ReasonTxt, Reason);
			Thread.sleep(1000);

			clickButton(StartDate);
			Thread.sleep(1000);
			clickButton(YearPicker);
			Thread.sleep(1000);
			Select FromYearOptionSelected = new Select (YearPicker);
			FromYearOptionSelected.selectByVisibleText(Year);
			clickButton(MonthPicker);
			Thread.sleep(1000);
			Select FromMonthOptionSelected = new Select (MonthPicker);
			FromMonthOptionSelected.selectByVisibleText(StartMonth);
			clickButton(StartDay);
			StartDateStr = StartDate.getAttribute("value");
			
			clickButton(EndDate);
			Thread.sleep(1000);
			clickButton(YearPicker);
			Thread.sleep(1000);
			FromYearOptionSelected.selectByVisibleText(Year);
			clickButton(MonthPicker);
			Thread.sleep(1000);
			FromMonthOptionSelected.selectByVisibleText(EndMonth);
			clickButton(EndDay);
			EndDateStr = EndDate.getAttribute("value");
		
			//upload attachment
			AddAttachmentBtn.sendKeys(uploadPath);
			Thread.sleep(1000);

			//click on Next Button
			clickButton(NextBtn);
			Thread.sleep(1000);

			// Assert on Data Entered are displayed correctly on the preview page
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل طلب عروض ترويجية"));
			Assert.assertTrue(PromoNamePreviewTxt.getText().contains(PromoName));
			Assert.assertTrue(StartDatePreview.getText().contains(StartDateStr));
			Assert.assertTrue(EndDatePreview.getText().contains(EndDateStr));
			Assert.assertTrue(LocationPreviewTxt.getText().contains(Location));
			Assert.assertTrue(ReasonPreviewTxt.getText().contains(Reason));
			Assert.assertTrue(NotesPreviewTxt.getText().contains(Notes));
			Thread.sleep(1000);
			
			//Return Back to First Page and Assert that data still exist
			scrollToBottom();
			clickButton(PreviousBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("طلب تصريح عروض ترويجية"));
			Assert.assertTrue(PromoNameTxt.getAttribute("value").contains(PromoName));
			Assert.assertTrue(LocationTxt.getAttribute("value").contains(Location));
			Assert.assertTrue(StartDate.getAttribute("value").contains(StartDateStr));
			Assert.assertTrue(EndDate.getAttribute("value").contains(EndDateStr));
			Assert.assertTrue(NotesTxt.getAttribute("value").contains(Notes));
			Assert.assertTrue(ReasonTxt.getAttribute("value").contains(Reason));
			Thread.sleep(1000);
			
			//click on Next Again
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل طلب عروض ترويجية"));
			Thread.sleep(1000);
			
			//Submit Request
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
		
			Thread.sleep(2000);
			Assert.assertTrue(ConfirmTitle.getText().contains("تأكيد اضافة الطلب"));
			Thread.sleep(1000);
			clickButton(ConfirmBtn);
			Thread.sleep(3000);
			Assert.assertTrue(ConfirmTitle.getText().contains("تمت العملية بنجاح"));
			Thread.sleep(1000);
			clickButton(CloseQuestionnaire);

		}
		
		public void CreateEmptyPromotionalOffersRequest (String PromoName, String Location) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("طلب تصريح عروض ترويجية"));
			Thread.sleep(1000);
			//Click on Next without entering data
			clickButton(NextBtn);
			jse.executeScript("scrollBy(0,-800)");
			System.out.println(PromoNameErrorMsg.getText());
			Assert.assertTrue(PromoNameErrorMsg.getText().contains("من فضلك ادخل اسم العرض الترويجى"));
			System.out.println(LocationErrorMsg.getText());
			Assert.assertTrue(LocationErrorMsg.getText().contains("من فضلك ادخل الموقع"));
			Thread.sleep(1000);
			setTextElementText(PromoNameTxt, PromoName);
			setTextElementText(LocationTxt, Location);
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(WarningMsg.getText().contains("فضلاً . أختر تاريخ البداية ."));
			Thread.sleep(1000);
			clickButton(StartDate);
			clickButton(ToDay);
			clickButton(NextBtn);
			Assert.assertTrue(WarningMsg.getText().contains("فضلاً . أختر تاريخ النهاية ."));


		}
		

}
