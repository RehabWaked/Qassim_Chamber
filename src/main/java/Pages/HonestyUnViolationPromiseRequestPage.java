package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HonestyUnViolationPromiseRequestPage extends PageBase{

	public HonestyUnViolationPromiseRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}
	

	//Definition 
		@FindBy (css = "[src='templateUrl()'] h2")
		WebElement RequestTitle;
		
		@FindBy (css = "[ng-model='RequestModel.AuthorizedName']")
		WebElement AuthorizedNameTxt;
		
		@FindBy (css = ".custom-center-ul > li:nth-of-type(2) > p")
		WebElement AuthorizedNamePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.AuthorizedName.$error'] > .text-danger")
		WebElement AuthorizedNameErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.IdNumber']")
		WebElement IDNumberTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(4) > p")
		WebElement IDNumberPreviewTxt;
		//من فضلك ادخل رقم بطاقة احوال المفوض بالتوقيع
		@FindBy (css= "[ng-messages='requestForm.IdNumber.$error'] > .text-danger")
		WebElement IDNumErrorMsg;
		
		@FindBy (css = "[ng-model='RequestModel.Municipality']")
		WebElement RegionNameTxt;
		@FindBy (css = ".custom-center-ul > li:nth-of-type(1) > p")
		WebElement RegionNamePreviewTxt;
		@FindBy (css = "[ng-messages='requestForm.Municipality.$error'] > .text-danger")
		WebElement RegionNameErrorMsg;
		
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
		
		public void CreateNewHonestyUnViolationRequest(String AuthorizedName, String IDNumber, String RegionName, String OTP, String InvalidOTP) throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("تعهد بعدم مخالفة الأمانة"));
			//fill Data
			setTextElementText(RegionNameTxt, RegionName);
			setTextElementText(AuthorizedNameTxt, AuthorizedName);
			setTextElementText(IDNumberTxt, IDNumber);
			
			//upload attachment
			AddAttachmentBtn.sendKeys(uploadPath);
			Thread.sleep(1000);
			
			//click on Next Button
			clickButton(NextBtn);
			jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			// Assert on Data Entered are displayed correctly on the preview page
			Assert.assertTrue(RequestTitle.getText().contains("طلب تعهد بعدم مخالفة الامانة"));
			Assert.assertTrue(AuthorizedNamePreviewTxt.getText().contains(AuthorizedName));
			Assert.assertTrue(IDNumberPreviewTxt.getText().contains(IDNumber));
			Assert.assertTrue(RegionNamePreviewTxt.getText().contains(RegionName));
			
			//Return Back to First Page and Assert that data still exist
			clickButton(PreviousBtn);
			//jse.executeScript("scrollBy(0,-500)");
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("تعهد بعدم مخالفة الأمانة"));
			Assert.assertTrue(AuthorizedNameTxt.getAttribute("value").contains(AuthorizedName));
			Assert.assertTrue(IDNumberTxt.getAttribute("value").contains(IDNumber));
			Assert.assertTrue(RegionNameTxt.getAttribute("value").contains(RegionName));
			Thread.sleep(1000);
			
			//click on Next Again
			clickButton(NextBtn);
			Thread.sleep(1000);
			Assert.assertTrue(RequestTitle.getText().contains("طلب تعهد بعدم مخالفة الامانة"));
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
		
		public void CreateEmptyHonestyUnViolationRequest () throws InterruptedException {
			Assert.assertTrue(RequestTitle.getText().contains("تعهد بعدم مخالفة الأمانة"));
			Thread.sleep(1000);
			//Click on Next without entering data
			clickButton(NextBtn);
			System.out.println(AuthorizedNameErrorMsg.getText());
			Assert.assertTrue(AuthorizedNameErrorMsg.getText().contains("من فضلك ادخل اسم المندوب"));
			System.out.println(IDNumErrorMsg.getText());
			Assert.assertTrue(IDNumErrorMsg.getText().contains("من فضلك ادخل بطاقة الأحوال"));
			System.out.println(RegionNameErrorMsg.getText());
			Assert.assertTrue(RegionNameErrorMsg.getText().contains("من فضلك ادخل اسم المنطقة"));
			Thread.sleep(1000);
		}
		

}
