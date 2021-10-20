package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FoodAndDrugsOrganizationRequestPage extends PageBase{

	public FoodAndDrugsOrganizationRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;

	}


	//Definition 
	@FindBy (css = "[src='templateUrl()'] h2")
	WebElement RequestTitle;

	@FindBy (css = "[ng-model='RequestModel.Name']")
	WebElement NameTxt;	
	@FindBy (css = ".custom-center-ul > li:nth-of-type(1) > p")
	WebElement NamePreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.JobTitle']")
	WebElement JobTitleTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(2)")
	WebElement JobTitlePreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.Email']")
	WebElement EmailTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(3) > p")
	WebElement EmailPreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.IdNumber']")
	WebElement IdNumberTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(4) > p")
	WebElement IdNumberPreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.PhoneNumber']")
	WebElement TelNumberTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(5) > p")
	WebElement TelNumberPreviewTxt;

	@FindBy (css = "[ng-model='RequestModel.Mobile']")
	WebElement MobNumberTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(6) > p")
	WebElement MobNumberPreviewTxt;
	
	@FindBy (css = "[ng-model='RequestModel.NationalAddress']")
	WebElement AddressTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(7) > p")
	WebElement AddressPreviewTxt;
	
	@FindBy (css = "[ng-model='RequestModel.BuildingNumber']")
	WebElement BuildNumberTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(8) > p")
	WebElement BuildNumberPreviewTxt;
		
	@FindBy (css = "[ng-model='RequestModel.DistrictName']")
	WebElement DistrictNameTxt;
	
	@FindBy (css = ".custom-center-ul > li:nth-of-type(10) > p")
	WebElement DistrictNamePreviewTxt;
	
	@FindBy (css = "[ng-model='RequestModel.StreetName']")
	WebElement StreetNameTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(9) > p")
	WebElement StreetNamePreviewTxt;
	
	@FindBy (css = "[ng-model='RequestModel.CityName']")
	WebElement CityNameTxt;
	@FindBy (css = "li:nth-of-type(11) > p")
	WebElement CityNamePreviewTxt;

	
	@FindBy (css = "[ng-model='RequestModel.ZipCode']")
	WebElement PostalCodeTxt;
	@FindBy (css = "li:nth-of-type(12) > p")
	WebElement PostalCodePreviewTxt;	
	
	@FindBy (css = "[ng-model='RequestModel.UnitNumber']")
	WebElement UnitNumberTxt;
	@FindBy (css = "li:nth-of-type(14) > p")
	WebElement UnitNumberPreviewTxt;
	
	@FindBy (css = "[ng-model='RequestModel.AdditionalMobile']")
	WebElement AdditionMobNumberTxt;
	@FindBy (css = "li:nth-of-type(13) > p")
	WebElement AdditionMobNumberPreviewTxt;
	

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
	//الاسم مطلوب
	@FindBy (css = "[ng-messages='requestForm.Name.$error'] > .text-danger")
	WebElement NameErrorMsg;	
	
	//المسمي الوظيفي مطلوب
	@FindBy (css = "[ng-messages='requestForm.JobTitle.$error'] > .text-danger")
	WebElement JobTitleErrorMsg;

	//البريد الالكتروني مطلوب
	@FindBy (css = "[ng-messages='requestForm.Email.$error'] > .text-danger")
	WebElement EmailErrorMsg;
	
	//من فضلك ادخل رقم الهوية
	@FindBy (css = ".validation-alert > .text-danger")
	WebElement IdNumberErrorMsg;

	//رقم الهاتف مطلوب
	@FindBy (css = "[ng-messages='requestForm.PhoneNumber.$error'] > .text-danger")
	WebElement TelNumberErrorMsg;

	//رقم الجوال مطلوب
	@FindBy (css = "[ng-messages='requestForm.Mobile.$error'] > .text-danger")
	WebElement MobNumberErrorMsg;
	
	//من فضلك ادخل العنوان الوطني
	@FindBy (css = "[ng-messages='requestForm.NationalAddress.$error'] > .text-danger")
	WebElement AddressErrorMsg;

	//من فضلك ادخل رقم المبني
	@FindBy (css = "[ng-messages='requestForm.BuildingNumber.$error'] > .text-danger")
	WebElement BuildNumberErrorMsg;
	
	//من فضلك ادخل اسم الحى
	@FindBy (css = "[ng-messages='requestForm.DistrictName.$error'] > .text-danger")
	WebElement DistrictNameErrorMsg;
	
	//من فضلك ادخل اسم الشارع
	@FindBy (css = "[ng-messages='requestForm.StreetName.$error'] > .text-danger")
	WebElement StreetNameErrorMsg;
	
	//من فضلك ادخل اسم المدينة
	@FindBy (css = "[ng-messages='requestForm.CityName.$error'] > .text-danger")
	WebElement CityNameErrorMsg;

	//من فضلك ادخل الرمز البريدى
	@FindBy (css = "[ng-messages='requestForm.ZipCode.$error'] > .text-danger")
	WebElement PostalCodeErrorMsg;
	
	//من فضلك ادخل رقم الوحده
	@FindBy (css = "[ng-messages='requestForm.UnitNumber.$error'] > .text-danger")
	WebElement UnitNumberErrorMsg;	
	
	//رقم رقم الجوال الإضافي مطلوب
	@FindBy (css = "[ng-messages='requestForm.AdditionalMobile.$error'] > .text-danger")
	WebElement AdditionMobNumberErrorMsg;
	


	public void CreateNewFoodAndDrugsOrgRequest(String Name, String JobTitle, String Email, String IDNumber, String TelNumber, String MobNumber, String Address, String BuildNumber, String DistrictName, String StreetName, String CityName, String PostalCode, String UnitNumber, String AddMobNumber, String OTP, String InvalidOTP) throws InterruptedException {
		Assert.assertTrue(RequestTitle.getText().contains("تفويض للهيئة العامة للغذاء و الدواء"));
		scrollToTheMiddle();
		Thread.sleep(1000);

		//fill Data
		setTextElementText(NameTxt, Name);
		Thread.sleep(500);
		setTextElementText(JobTitleTxt, JobTitle);
		Thread.sleep(500);
		setTextElementText(EmailTxt, Email);
		Thread.sleep(500);
		setTextElementText(IdNumberTxt, IDNumber);
		Thread.sleep(500);
		setTextElementText(TelNumberTxt, TelNumber);
		Thread.sleep(500);
		setTextElementText(MobNumberTxt, MobNumber);
		Thread.sleep(500);
		setTextElementText(AddressTxt, Address);
		Thread.sleep(500);
		setTextElementText(BuildNumberTxt, BuildNumber);
		Thread.sleep(500);
		setTextElementText(DistrictNameTxt, DistrictName);
		Thread.sleep(500);
		setTextElementText(StreetNameTxt, StreetName);
		Thread.sleep(500);
		CityNameTxt.clear();
		setTextElementText(CityNameTxt, CityName);
		Thread.sleep(500);	
		setTextElementText(PostalCodeTxt, PostalCode);
		Thread.sleep(500);
		setTextElementText(UnitNumberTxt, UnitNumber);
		Thread.sleep(500);
		setTextElementText(AdditionMobNumberTxt, AddMobNumber);
		Thread.sleep(500);
		scrollToBottom();
		

		//upload attachment
		AddAttachmentBtn.sendKeys(uploadPath);
		Thread.sleep(1000);

		//click on Next Button
		clickButton(NextBtn);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,-600)");
		Assert.assertTrue(RequestTitle.getText().contains("تفويض للهيئة العامة للغذاء و الدواء"));
		Assert.assertTrue(NamePreviewTxt.getText().contains(Name));
		Assert.assertTrue(JobTitlePreviewTxt.getText().contains(JobTitle));
		Assert.assertTrue(EmailPreviewTxt.getText().contains(Email));
		Assert.assertTrue(IdNumberPreviewTxt.getText().contains(IDNumber));
		Assert.assertTrue(TelNumberPreviewTxt.getText().contains(TelNumber));
		Assert.assertTrue(MobNumberPreviewTxt.getText().contains(MobNumber));
		Assert.assertTrue(AddressPreviewTxt.getText().contains(Address));
		Assert.assertTrue(BuildNumberPreviewTxt.getText().contains(BuildNumber));
		Assert.assertTrue(StreetNamePreviewTxt.getText().contains(StreetName));
		Assert.assertTrue(DistrictNamePreviewTxt.getText().contains(DistrictName));
		Assert.assertTrue(CityNamePreviewTxt.getText().contains(CityName));
		Assert.assertTrue(PostalCodePreviewTxt.getText().contains(PostalCode));
		Assert.assertTrue(AdditionMobNumberPreviewTxt.getText().contains(AddMobNumber));
		Assert.assertTrue(UnitNumberPreviewTxt.getText().contains(UnitNumber));	
		Thread.sleep(1000);

		//Return Back to First Page
		clickButton(PreviousBtn);
		jse.executeScript("scrollBy(0,-700)");
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض للهيئة العامة للغذاء و الدواء"));
		Assert.assertTrue(NameTxt.getAttribute("value").contains(Name));
		Assert.assertTrue(JobTitleTxt.getAttribute("value").contains(JobTitle));
		Assert.assertTrue(EmailTxt.getAttribute("value").contains(Email));
		Assert.assertTrue(IdNumberTxt.getAttribute("value").contains(IDNumber));
		Assert.assertTrue(TelNumberTxt.getAttribute("value").contains(TelNumber));
		Assert.assertTrue(MobNumberTxt.getAttribute("value").contains(MobNumber));
		Assert.assertTrue(AddressTxt.getAttribute("value").contains(Address));
		Assert.assertTrue(BuildNumberTxt.getAttribute("value").contains(BuildNumber));
		Assert.assertTrue(DistrictNameTxt.getAttribute("value").contains(DistrictName));
		Assert.assertTrue(StreetNameTxt.getAttribute("value").contains(StreetName));
		Assert.assertTrue(CityNameTxt.getAttribute("value").contains(CityName));
		Assert.assertTrue(PostalCodeTxt.getAttribute("value").contains(PostalCode));
		Assert.assertTrue(UnitNumberTxt.getAttribute("value").contains(UnitNumber));
		Assert.assertTrue(AdditionMobNumberTxt.getAttribute("value").contains(AddMobNumber));
		
		Thread.sleep(1000);
		scrollToBottom();
		//click on Next Again
		clickButton(NextBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض للهيئة العامة للغذاء و الدواء"));
		Thread.sleep(1000);

		//Submit Request
		clickButton(SubmitBtn);
		Thread.sleep(1000);
		Assert.assertTrue(OTPMsg.getText().contains("أدخل كود التحقق الذي تم إرساله على الهاتف الجوال الخاص بك"));
		Thread.sleep(1000);

		//Enter Invalid OTP
		setTextElementText(OTPTxt, InvalidOTP);
		Thread.sleep(4000);
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
		Thread.sleep(2000);
		clickButton(CloseQuestionnaire);
		Thread.sleep(1000);

	}

	public void CreateEmptyFoodAndDrugsRequest () throws InterruptedException {
		jse.executeScript("scrollBy(0,-600)");
		Thread.sleep(1000);
		Assert.assertTrue(RequestTitle.getText().contains("تفويض للهيئة العامة للغذاء و الدواء"));
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,600)");
		//Click on Next without entering data
		clickButton(NextBtn);
		Thread.sleep(1000);
		scrollToTheMiddle();
		System.out.println(NameErrorMsg.getText());
		Assert.assertTrue(NameErrorMsg.getText().contains("الاسم مطلوب"));
		System.out.println(JobTitleErrorMsg.getText());
		Assert.assertTrue(JobTitleErrorMsg.getText().contains("المسمي الوظيفي مطلوب"));
		System.out.println(EmailErrorMsg.getText());
		Assert.assertTrue(EmailErrorMsg.getText().contains("البريد الالكتروني مطلوب"));
		System.out.println(IdNumberErrorMsg.getText());
		Assert.assertTrue(IdNumberErrorMsg.getText().contains("من فضلك ادخل رقم الهوية"));
		System.out.println(TelNumberErrorMsg.getText());
		Assert.assertTrue(TelNumberErrorMsg.getText().contains("رقم الهاتف مطلوب"));
		System.out.println(MobNumberErrorMsg.getText());
		Assert.assertTrue(MobNumberErrorMsg.getText().contains("رقم الجوال مطلوب"));
		System.out.println(AddressErrorMsg.getText());
		Assert.assertTrue(AddressErrorMsg.getText().contains("من فضلك ادخل العنوان الوطني"));
		System.out.println(BuildNumberErrorMsg.getText());
		Assert.assertTrue(BuildNumberErrorMsg.getText().contains("من فضلك ادخل رقم المبني"));
		System.out.println(DistrictNameErrorMsg.getText());
		Assert.assertTrue(DistrictNameErrorMsg.getText().contains("من فضلك ادخل اسم الحى"));
		System.out.println(StreetNameErrorMsg.getText());
		Assert.assertTrue(StreetNameErrorMsg.getText().contains("من فضلك ادخل اسم الشارع"));
		System.out.println(CityNameErrorMsg.getText());
		Assert.assertTrue(CityNameErrorMsg.getText().contains("ادخل اسم المدينة بشكل صحيح"));	
		System.out.println(PostalCodeErrorMsg.getText());
		Assert.assertTrue(PostalCodeErrorMsg.getText().contains("من فضلك ادخل الرمز البريدى"));
		System.out.println(UnitNumberErrorMsg.getText());
		Assert.assertTrue(UnitNumberErrorMsg.getText().contains("من فضلك ادخل رقم الوحده"));
		System.out.println(AdditionMobNumberErrorMsg.getText());
		Assert.assertTrue(AdditionMobNumberErrorMsg.getText().contains("رقم رقم الجوال الإضافي مطلوب"));
		Thread.sleep(500);

	}


}
