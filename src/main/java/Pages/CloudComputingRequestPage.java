package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CloudComputingRequestPage extends PageBase{

	public CloudComputingRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	

	//Definition 
		@FindBy (css = "[src='templateUrl()'] h2")
		WebElement RequestTitle;
		
		@FindBy (css = "[ng-model='RequestModel.SirName']")
		WebElement SirNameTxt;
		@FindBy (css = ".CustomTextShow")
		WebElement SirDataPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.SirName.$error'] > .text-danger")
		WebElement SirNameErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.IdNumber']")
		WebElement IDNumberTxt;
//		@FindBy (css = ".custom-center-ul > li:nth-of-type(2) > p")
//		WebElement IDNumberPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.IdNumber.$error'] > .text-danger")
		WebElement IDNumberErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.JobTitle']")
		WebElement JobTitleTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(2)")
		WebElement JobTitlePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.JobTitle.$error'] > .text-danger")
		WebElement JobTitleErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.IdentityIssuePlace']")
		WebElement IDIssuePlaceTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(3)")
		WebElement IDIssuePlacePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.IdentityIssuePlace.$error'] > .text-danger")
		WebElement IDIssuePlaceErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.PhoneNumber']")
		WebElement MobileNoTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(5) > p")
		WebElement MobileNoPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.PhoneNumber.$error'] > .text-danger")
		WebElement MobileNoErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.Email']")
		WebElement EmailTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(4) > p")
		WebElement EmailPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.Email.$error'] > .text-danger")
		WebElement EmailErrorMsg;
		
		
		@FindBy (css = "[ng-model='RequestModel.PoBox']")
		WebElement POBoxTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(7) > p")
		WebElement POBoxPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.PoBox.$error'] > .text-danger")
		WebElement POBoxErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.PostalCode']")
		WebElement PostalCodeTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(8)")
		WebElement PostalCodePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.PostalCode.$error'] > .text-danger")
		WebElement PostalCodeErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.City']")
		WebElement CityTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(9) > p")
		WebElement CityPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.City.$error'] > .text-danger")
		WebElement CityErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.Street']")
		WebElement StreetTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(10) > p")
		WebElement StreetPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.Street.$error'] > .text-danger")
		WebElement StreetErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.District']")
		WebElement DistrictTxt;
		@FindBy (css = "li:nth-of-type(11) > p")
		WebElement DistrictPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.District.$error'] > .text-danger")
		WebElement DistrictErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.BuildingNo']")
		WebElement BuildingNoTxt;
		@FindBy (css = "li:nth-of-type(12)")
		WebElement BuildingNoPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.BuildingNo.$error'] > .text-danger")
		WebElement BuildingNoErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.ApartmentNo']")
		WebElement AppartmentNoTxt;
		@FindBy (css = "li:nth-of-type(13) > p")
		WebElement AppartmentNoPreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.ApartmentNo.$error'] > .text-danger")
		WebElement AppartmentNoErrorMsg;
	
		//ID Issue Date Picker
		
		@FindBy (css = "[ng-model='mydate']")
		WebElement IDIssueDate;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(6)")
		WebElement IDIssueDatePreview;
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

		@FindBy (css= "[ng-if='RequestModel.RequestStatus === 4']")
		WebElement DownloadBtn;

		@FindBy (css= ".m-top-md > [back='عوده'] > .sp-main-button")
		WebElement BackBtn;

		@FindBy (css= "[ng-class='config.title']")
		WebElement WarningMsg;
		
		
		public void CreateNewCloudComputingRequest(String SirName, String IDNumber, String JobTitle, String IDIssuePlace, String MobileNo, String Email, String POBox, String PostalCode, String City, String Street, String District, String BuildingNo, String AppartNo, String OTP, String InvalidOTP) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("تسجيل لتقديم خدمات الحوسبة السحابية"));
			scrollToTheMiddle();
			Thread.sleep(500);
		
			//fill Data
			Thread.sleep(1000);

			setTextElementText(SirNameTxt, SirName);
			setTextElementText(IDNumberTxt, IDNumber);
			setTextElementText(JobTitleTxt, JobTitle);
			setTextElementText(IDIssuePlaceTxt, IDIssuePlace);
			setTextElementText(MobileNoTxt, MobileNo);
			setTextElementText(EmailTxt, Email);
			Thread.sleep(1000);

			clickButton(IDIssueDate);
			Thread.sleep(1000);
			clickButton(TodayPicker);
			DateStr = IDIssueDate.getAttribute("value");
			setTextElementText(POBoxTxt, POBox);
			setTextElementText(PostalCodeTxt, PostalCode);
			setTextElementText(CityTxt, City);
			setTextElementText(StreetTxt, Street);
			setTextElementText(DistrictTxt, District);
			setTextElementText(BuildingNoTxt, BuildingNo);
			setTextElementText(AppartmentNoTxt, AppartNo);
			Thread.sleep(1000);

			//upload attachment
			AddAttachmentBtn.sendKeys(uploadPath);
			Thread.sleep(1000);
			scrollToBottom();
			Thread.sleep(1000);

			//click on Next Button
			clickButton(NextBtn);
			Thread.sleep(1000);
			scrollUp();
			Thread.sleep(1000);

			// Assert on Data Entered are displayed correctly on the preview page
			Assert.assertTrue(RequestTitle.getText().contains("تسجيل لتقديم خدمات الحوسبة السحابية"));
			Assert.assertTrue(SirDataPreviewTxt.getText().contains(SirName));
			Assert.assertTrue(SirDataPreviewTxt.getText().contains(IDNumber));
			Assert.assertTrue(JobTitlePreviewTxt.getText().contains(JobTitle));
			Assert.assertTrue(IDIssuePlacePreviewTxt.getText().contains(IDIssuePlace));
			Assert.assertTrue(MobileNoPreviewTxt.getText().contains(MobileNo));
			Assert.assertTrue(EmailPreviewTxt.getText().contains(Email));
			Assert.assertTrue(IDIssueDatePreview.getText().contains(DateStr));
			Assert.assertTrue(POBoxPreviewTxt.getText().contains(POBox));
			Assert.assertTrue(PostalCodePreviewTxt.getText().contains(PostalCode));
			Assert.assertTrue(CityPreviewTxt.getText().contains(City));
			Assert.assertTrue(StreetPreviewTxt.getText().contains(Street));
			Assert.assertTrue(DistrictPreviewTxt.getText().contains(District));
			Assert.assertTrue(BuildingNoPreviewTxt.getText().contains(BuildingNo));
			Assert.assertTrue(AppartmentNoPreviewTxt.getText().contains(AppartNo));
			Thread.sleep(1000);
			
			//Return Back to First Page and Assert that data still exist
			clickButton(PreviousBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تسجيل لتقديم خدمات الحوسبة السحابية"));
			Assert.assertTrue(SirNameTxt.getAttribute("value").contains(SirName));
			Assert.assertTrue(IDNumberTxt.getAttribute("value").contains(IDNumber));
			Assert.assertTrue(JobTitleTxt.getAttribute("value").contains(JobTitle));
			Assert.assertTrue(IDIssueDate.getAttribute("value").contains(DateStr));
			Assert.assertTrue(MobileNoTxt.getAttribute("value").contains(MobileNo));
			Assert.assertTrue(EmailTxt.getAttribute("value").contains(Email));
			Assert.assertTrue(POBoxTxt.getAttribute("value").contains(POBox));
			Assert.assertTrue(PostalCodeTxt.getAttribute("value").contains(PostalCode));
			Assert.assertTrue(CityTxt.getAttribute("value").contains(City));
			Assert.assertTrue(StreetTxt.getAttribute("value").contains(Street));
			Assert.assertTrue(DistrictTxt.getAttribute("value").contains(District));
			Assert.assertTrue(BuildingNoTxt.getAttribute("value").contains(BuildingNo));
			Assert.assertTrue(AppartmentNoTxt.getAttribute("value").contains(AppartNo));

			Thread.sleep(1000);
			
			//click on Next Again
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تسجيل لتقديم خدمات الحوسبة السحابية"));
			Thread.sleep(1000);
			
			//Submit Request
			clickButton(SubmitBtn);
			Thread.sleep(2000);
			
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
		
		public void CreateEmptyCloudComputingRequest () throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("تسجيل لتقديم خدمات الحوسبة السحابية"));
			Thread.sleep(1000);
			//Click on Next without entering data
			clickButton(NextBtn);
			jse.executeScript("scrollBy(0,-800)");
			System.out.println(SirNameErrorMsg.getText());
			Assert.assertTrue(SirNameErrorMsg.getText().contains("من فضلك ادخل اسم السيد"));
			System.out.println(IDNumberErrorMsg.getText());
			Assert.assertTrue(IDNumberErrorMsg.getText().contains("من فضلك ادخل رقم الهوية"));
			System.out.println(JobTitleErrorMsg.getText());
			Assert.assertTrue(JobTitleErrorMsg.getText().contains("المسمي الوظيفي مطلوب"));
			System.out.println(IDIssuePlaceErrorMsg.getText());
			Assert.assertTrue(IDIssuePlaceErrorMsg.getText().contains("مكان اصدار الهوية مطلوب"));
			System.out.println(MobileNoErrorMsg.getText());
			Assert.assertTrue(MobileNoErrorMsg.getText().contains("رقم الجوال مطلوب"));
			System.out.println(EmailErrorMsg.getText());
			Assert.assertTrue(EmailErrorMsg.getText().contains("البريد الالكتروني مطلوب"));
			
			System.out.println(POBoxErrorMsg.getText());
			Assert.assertTrue(POBoxErrorMsg.getText().contains("صندوق البريد مطلوب"));
			System.out.println(PostalCodeErrorMsg.getText());
			Assert.assertTrue(PostalCodeErrorMsg.getText().contains("الرمز البريدى مطلوب"));
			System.out.println(CityErrorMsg.getText());
			Assert.assertTrue(CityErrorMsg.getText().contains("المدينة مطلوبة"));
			System.out.println(StreetErrorMsg.getText());
			Assert.assertTrue(StreetErrorMsg.getText().contains("الشارع مطلوب"));
			System.out.println(DistrictErrorMsg.getText());
			Assert.assertTrue(DistrictErrorMsg.getText().contains("الحي مطلوب"));
			System.out.println(BuildingNoErrorMsg.getText());
			Assert.assertTrue(BuildingNoErrorMsg.getText().contains("رقم المبني مطلوب"));
			System.out.println(AppartmentNoErrorMsg.getText());
			Assert.assertTrue(AppartmentNoErrorMsg.getText().contains("رقم الوحدة مطلوب"));
			
			Thread.sleep(1000);

		}
		

}
