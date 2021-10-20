package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CivilDefenseServicesRequestPage extends PageBase{

	public CivilDefenseServicesRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	

	//Definition 
		@FindBy (css = "[src='templateUrl()'] h2")
		WebElement RequestTitle;
		
		@FindBy (css = "[ng-model='RequestModel.AuthorizedName']")
		WebElement AuthorizedNameTxt;
		@FindBy (css = ".sp-details-page div:nth-of-type(2) li:nth-of-type(1) > .CustomTextShow")
		WebElement AuthorizedNamePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.AuthorizedName.$error'] > .text-danger")
		WebElement AuthorizedNameErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.IdNumber']")
		WebElement IDNumberTxt;
		@FindBy (css = ".sp-details-page li:nth-of-type(2) > .CustomTextShow")
		WebElement IDNumberPreviewTxt;
		//من فضلك ادخل رقم بطاقة احوال المفوض بالتوقيع
		@FindBy (css= "[ng-messages='requestForm.IdNumber.$error'] > .text-danger")
		WebElement IDNumErrorMsg;
		
		@FindBy (css = "[ng-model='mydate']")
		WebElement AuthEndDate;
		@FindBy (css = ".sp-details-page div:nth-of-type(2) li:nth-of-type(3) > .CustomTextShow")
		WebElement AuthEndDatePreview;
		@FindBy (css = "[ng-messages='requestForm.AuthorizedExpireDate.$error'] > .text-danger")
		WebElement AuthEndDateErrorMsg;
		String DateStr;
		
		@FindBy (css = "[title='عرض سنة آخرى']")
		WebElement YearPicker;

		@FindBy (css = "[title='عرض شهر آخر']")
		WebElement MonthPicker;

		@FindBy (css = ".calendars-today")
		WebElement TodayPicker;

		@FindBy (css= "[ng-model='RequestModel.LicenseName']")
		WebElement LicenseNameTxt;
		@FindBy (css = ".sp-details-page > .row > div:nth-of-type(1) li:nth-of-type(3) > .CustomTextShow")
		WebElement LicenseNamePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.LicenseName.$error'] > .text-danger")
		WebElement LicenseNameErrorMsg;
		
		
		@FindBy (css = "[ng-model='RequestModel.RegionName']")
		WebElement RegionNameTxt;
		@FindBy (css = ".sp-details-page li:nth-of-type(4) > .CustomTextShow")
		WebElement RegionNamePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.RegionName.$error'] > .text-danger")
		WebElement RegionNameErrorMsg;
		
		

		@FindBy (id="chex1")
		WebElement Service1CheckBox;
		@FindBy (id="chex2")
		WebElement Service2CheckBox;
		@FindBy (id="chex3")
		WebElement Service3CheckBox;
		@FindBy (id="chex4")
		WebElement Service4CheckBox;
		@FindBy (css=".sp-details-page div:nth-of-type(2) li:nth-of-type(4) > p")
		WebElement ServicesPreview;
		String Service1, Service2, Service3; 
		

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
		
		
		public void CreateNewCivilDefenseServicesRequest(String AuthorizedName, String IDNumber, String LicenseName, String RegionName, String OTP, String InvalidOTP) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("طلب خدمات الدفاع المدنى"));
			//fill Data
			setTextElementText(AuthorizedNameTxt, AuthorizedName);
			setTextElementText(IDNumberTxt, IDNumber);
			setTextElementText(LicenseNameTxt, LicenseName);
			setTextElementText(RegionNameTxt, RegionName);
			
			clickButton(AuthEndDate);
			Thread.sleep(500);
			clickButton(TodayPicker);
			Thread.sleep(500);
			DateStr = AuthEndDate.getAttribute("value");
			Thread.sleep(500);
			jse.executeScript("scrollBy(0,500)");
			Thread.sleep(500);
			clickButton(Service1CheckBox);
			Service1 = Service1CheckBox.getAttribute("value");
			clickButton(Service2CheckBox);
			Service2 = Service2CheckBox.getAttribute("value");
			clickButton(Service3CheckBox);
			Service3 = Service3CheckBox.getAttribute("value");
			
			//upload attachment
			AddAttachmentBtn.sendKeys(uploadPath);
			Thread.sleep(1000);
			
			//click on Next Button
			clickButton(NextBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			// Assert on Data Entered are displayed correctly on the preview page
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تفويض خدمات الدفاع المدني"));
			Assert.assertTrue(AuthorizedNamePreviewTxt.getText().contains(AuthorizedName));
			Assert.assertTrue(IDNumberPreviewTxt.getText().contains(IDNumber));
			Assert.assertTrue(LicenseNamePreviewTxt.getText().contains(LicenseName));
			Assert.assertTrue(RegionNamePreviewTxt.getText().contains(RegionName));
			Assert.assertTrue(AuthEndDatePreview.getText().contains(DateStr));
			Assert.assertTrue(ServicesPreview.getText().contains(Service1));
			Assert.assertTrue(ServicesPreview.getText().contains(Service2));
			Assert.assertTrue(ServicesPreview.getText().contains(Service3));
			Thread.sleep(1000);
			
			//Return Back to First Page and Assert that data still exist
			clickButton(PreviousBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("طلب خدمات الدفاع المدنى"));
			Assert.assertTrue(AuthorizedNameTxt.getAttribute("value").contains(AuthorizedName));
			Assert.assertTrue(LicenseNameTxt.getAttribute("value").contains(LicenseName));
			Assert.assertTrue(IDNumberTxt.getAttribute("value").contains(IDNumber));
			Assert.assertTrue(RegionNameTxt.getAttribute("value").contains(RegionName));
			Assert.assertTrue(AuthEndDate.getAttribute("value").contains(DateStr));
			Thread.sleep(1000);
			
			//click on Next Again
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تفويض خدمات الدفاع المدني"));
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
		
		public void CreateEmptyCivilDefenseServicesRequest (String AuthorizedName, String IDNumber, String LicenseName, String RegionName) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("طلب خدمات الدفاع المدنى"));
			Thread.sleep(1000);
			//Click on Next without entering data
			clickButton(NextBtn);
			jse.executeScript("scrollBy(0,-500)");
			System.out.println(AuthorizedNameErrorMsg.getText());
			Assert.assertTrue(AuthorizedNameErrorMsg.getText().contains("من فضلك ادخل اسم المفوض"));
			System.out.println(IDNumErrorMsg.getText());
			Assert.assertTrue(IDNumErrorMsg.getText().contains("من فضلك ادخل رقم الهوية الوطنية"));
			System.out.println(LicenseNameErrorMsg.getText());
			Assert.assertTrue(LicenseNameErrorMsg.getText().contains("ادخل اسم الرخصة"));
			System.out.println(RegionNameErrorMsg.getText());
			Assert.assertTrue(RegionNameErrorMsg.getText().contains("ادخل اسم المنطقة"));
			System.out.println(AuthEndDateErrorMsg.getText());
			Assert.assertTrue(AuthEndDateErrorMsg.getText().contains("من فضلك ادخل تاريخ إنتهاء التفويض"));
			Thread.sleep(1000);
			//fill Data
			setTextElementText(AuthorizedNameTxt, AuthorizedName);
			setTextElementText(IDNumberTxt, IDNumber);
			setTextElementText(LicenseNameTxt, LicenseName);
			setTextElementText(RegionNameTxt, RegionName);
			
			clickButton(AuthEndDate);
			Thread.sleep(500);
			clickButton(TodayPicker);
			clickButton(NextBtn);
			Thread.sleep(500);
			Assert.assertTrue(WarningMsg.getText().contains("فضلاً , اختر سبب طلب التفويض ."));
			clickButton(Service1CheckBox);
			clickButton(Service2CheckBox);
			clickButton(Service3CheckBox);
			clickButton(Service4CheckBox);
			Thread.sleep(500);
			clickButton(NextBtn);
			Thread.sleep(500);
			Assert.assertTrue(WarningMsg.getText().contains("لايمكن تحديد اكثر من 3"));
			

			
		}
		

}
