package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class NICScopeModelRequestPage extends PageBase{

	public NICScopeModelRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	

	//Definition 
	
		@FindBy (css = "[src='templateUrl()'] h2")
		WebElement RequestTitle;
		
		@FindBy (css = "[ng-model='RequestModel.Band']")
		WebElement BandNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(2) > p")
		WebElement BandNamePreviewTxt;
		//من فضلك ادخل إسم النطاق
		@FindBy (css = "[ng-messages='requestForm.Band.$error'] > .text-danger")
		WebElement BandNameErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.AdministrativeCoordinator']")
		WebElement AdminNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(3) > p")
		WebElement AdminNamePreviewTxt;
		//أدخل المنسق الإدارى
		@FindBy (css= "[ng-messages='requestForm.AdministrativeCoordinator.$error'] > .text-danger")
		WebElement AdminNameErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.CoordinatorMobile']")
		WebElement AdminMobileTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(4) > p")
		WebElement AdminMobilePreviewTxt;
		//من فضلك ادخل رقم جوال
		@FindBy (css = "[ng-messages='requestForm.CoordinatorMobile.$error'] > .text-danger")
		WebElement AdminMobileErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.CoordinatorEmail']")
		WebElement AdminMailTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(5) > p")
		WebElement AdminMailPreviewTxt;
		//من فضلك ادخل البريد الالكتروني
		@FindBy (css = "[ng-messages='requestForm.CoordinatorEmail.$error'] > .text-danger")
		WebElement AdminMailErrorMsg;
		
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
		
		public void CreateNewNICScopeModelRequest(String BandName, String AdminName, String AdminMobile, String AdminMail, String OTP, String InvalidOTP) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("نموذج نطاق NIC"));
			//fill Data
			setTextElementText(BandNameTxt, BandName);
			setTextElementText(AdminNameTxt, AdminName);
			setTextElementText(AdminMobileTxt, AdminMobile);
			setTextElementText(AdminMailTxt, AdminMail);

			//upload attachment
			AddAttachmentBtn.sendKeys(uploadPath);
			Thread.sleep(1000);
			
			//click on Next Button
			clickButton(NextBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			// Assert on Data Entered are displayed correctly on the preview page
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تسجيل اسم نطاق سعودي"));
			Assert.assertTrue(BandNamePreviewTxt.getText().contains(BandName));
			Assert.assertTrue(AdminNamePreviewTxt.getText().contains(AdminName));
			Assert.assertTrue(AdminMobilePreviewTxt.getText().contains(AdminMobile));
			Assert.assertTrue(AdminMailPreviewTxt.getText().contains(AdminMail));

			
			//Return Back to First Page and Assert that data still exist
			clickButton(PreviousBtn);
			//jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("نموذج نطاق NIC"));
			Assert.assertTrue(BandNameTxt.getAttribute("value").contains(BandName));
			Assert.assertTrue(AdminNameTxt.getAttribute("value").contains(AdminName));
			Assert.assertTrue(AdminMobileTxt.getAttribute("value").contains(AdminMobile));
			Assert.assertTrue(AdminMailTxt.getAttribute("value").contains(AdminMail));

			Thread.sleep(1000);
			
			//click on Next Again
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تفاصيل تسجيل اسم نطاق سعودي"));
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
			Thread.sleep(1000);
			
		}
		
		public void CreateEmptyNICScopeModelRequest () throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("نموذج نطاق NIC"));
			Thread.sleep(1000);
			//Click on Next without entering data
			clickButton(NextBtn);
			System.out.println(BandNameErrorMsg.getText());
			Assert.assertTrue(BandNameErrorMsg.getText().contains("من فضلك ادخل إسم النطاق"));
			System.out.println(AdminNameErrorMsg.getText());
			Assert.assertTrue(AdminNameErrorMsg.getText().contains("أدخل المنسق الإدارى"));
			System.out.println(AdminMobileErrorMsg.getText());
			Assert.assertTrue(AdminMobileErrorMsg.getText().contains("من فضلك ادخل رقم جوال"));
			System.out.println(AdminMailErrorMsg.getText());
			Assert.assertTrue(AdminMailErrorMsg.getText().contains("من فضلك ادخل البريد الالكتروني"));
			Thread.sleep(1000);
		}
		

}
