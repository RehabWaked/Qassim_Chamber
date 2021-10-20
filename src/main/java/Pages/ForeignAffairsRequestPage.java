package Pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ForeignAffairsRequestPage extends PageBase{

	public ForeignAffairsRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}


	// 	Foreign Affairs Request Data Required to complete the cycle


	@FindBy (css = "[src='templateUrl()'] h2")
	WebElement RequestTitle;
	@FindBy (css = "[ng-model='$select.search']")
	WebElement VisaDDL;

	@FindBy (css = ".ui-select-choices-group > div:nth-of-type(4) > .option")
	WebElement VisaType;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(1) > p")
	WebElement VisaTypePreview;
	String VisaStr;


	@FindBy (name = "authorizationNumber")
	WebElement RequestNoTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(2) > p")
	WebElement RequestNoPreviewTxt;

	@FindBy (name = "notes")
	WebElement NotesTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(4) > p")
	WebElement NotesPreviewTxt;

	@FindBy (name = "requestReason")
	WebElement ReasonTxt;
	@FindBy (css = ".custom-center-ul > li:nth-of-type(3)")
	WebElement ReasonPreviewTxt;

	// for Attachment
	String fileName1 = "pdf-test.pdf";
	String uploadPath = System.getProperty("user.dir") + "/uploads/" + fileName1;

	@FindBy (name = "files")
	WebElement AddAttachmentBtn;



	@FindBy(name = "next")
	WebElement NextBTN;

	@FindBy(css = "[ng-disabled='page.currentStep <= 1']")
	WebElement BackBTN;


	@FindBy (css = "h3.text-center")
	public WebElement PreviewTitle;


	@FindBy(css = "[ng-disabled='page.currentStep != steps.length || saveRequestStart ==true']")
	WebElement SubmitRequest;


	@FindBy(name = "codeToMatch")
	WebElement OTPTXT;


	@FindBy(css = "ul.text-center.list-unstyled.custom-center-ul")
	List<WebElement> RequestDetailsComponent;

	@FindBy(css = ".confirm")
	WebElement SubmitRequestAndConductPrice;

	@FindBy(css = ".cancel")
	WebElement closeRequest;


	@FindBy (css = "tr:nth-of-type(2) .RequestPending")
	WebElement RequestStatus;
	@FindBy (css = "[ng-class='config.title']")
	WebElement AttachmentMsg;
	
	//Error Messages for Mandatory Fields
	@FindBy (css = "[ng-messages='requestForm.visaType.$error'] > .text-danger")
	WebElement VisaTypeMsg;

	@FindBy (css = "[ng-messages='requestForm.authorizationNumber.$error'] > .text-danger")
	WebElement ReqNoMsg;

	public void CreateNewForeignAffairsRequest (String RequestNo, String Notes, String Reason, String OTP) throws InterruptedException
	{
		//Enter Required Fields
		Thread.sleep(500);
		Assert.assertTrue(RequestTitle.getText().contains("طلب وزارة الخارجية"));
		clickButton(VisaDDL);
		Thread.sleep(500);
		clickButton(VisaType);
		VisaStr = VisaDDL.getAttribute("value");
		setTextElementText(RequestNoTxt, RequestNo);
		setTextElementText(NotesTxt, Notes);
		setTextElementText(ReasonTxt, Reason);
		jse.executeScript("scrollBy(0,250)");
		Thread.sleep(1000);
		AddAttachmentBtn.sendKeys(uploadPath);
		//Next
		Thread.sleep(1000);
		clickButton(NextBTN);
		Thread.sleep(1000);
		Assert.assertTrue(PreviewTitle.getText().contains("تفاصيل طلب وزارة الخارجية"));
		Assert.assertTrue(VisaTypePreview.getText().contains(VisaStr));
		Assert.assertTrue(RequestNoPreviewTxt.getText().contains(RequestNo));
		Assert.assertTrue(NotesPreviewTxt.getText().contains(Notes));
		Assert.assertTrue(ReasonPreviewTxt.getText().contains(Reason));
		Thread.sleep(1000);
		//Back
		clickButton(BackBTN);
		jse.executeScript("scrollBy(0,-300)");
		Assert.assertTrue(RequestTitle.getText().contains("طلب وزارة الخارجية"));
		//	Assert.assertTrue(VisaType.getAttribute("value").contains(VisaStr));
		Assert.assertTrue(RequestNoTxt.getAttribute("value").contains(RequestNo));
		Assert.assertTrue(NotesTxt.getAttribute("value").contains(Notes));
		Assert.assertTrue(ReasonTxt.getAttribute("value").contains(Reason));
		Thread.sleep(1000);


		//Next again
		clickButton(NextBTN);
		Thread.sleep(1000);
		assertTrue(PreviewTitle.getText().contains("تفاصيل طلب وزارة الخارجية"));

		//Submit Request
		clickButton(SubmitRequest);
		Thread.sleep(3000);
		clickButton(SubmitRequestAndConductPrice);
		Thread.sleep(4000);
		clickButton(closeRequest);
		Thread.sleep(2000);
	}

	public void CreateEmptyForeignAffairsRequest () throws InterruptedException 
	{
		//Click on Next without entering data
		clickButton(NextBTN);
		Thread.sleep(1000);
		Assert.assertTrue(VisaTypeMsg.getText().contains("من فضلك ادخل نوع التاشيرة"));
		Thread.sleep(1000);
		System.out.println(VisaTypeMsg.getText());
		Assert.assertTrue(ReqNoMsg.getText().contains("من فضلك ادخل رقم الطلب"));
		Thread.sleep(1000);
		System.out.println(ReqNoMsg.getText());

	}



	public void CreateRequestWithoutAttachment (String RequestNo, String Notes, String Reason, String OTP) throws InterruptedException
	{
		//Enter Required Fields
		Thread.sleep(1000);
		clickButton(VisaDDL);
		Thread.sleep(1000);
		clickButton(VisaType);
		Thread.sleep(1000);
		setTextElementText(RequestNoTxt, RequestNo);
		Thread.sleep(1000);
		setTextElementText(NotesTxt, Notes);
		Thread.sleep(1000);
		setTextElementText(ReasonTxt, Reason);
		clickButton(NextBTN);
		Thread.sleep(1000);
		Assert.assertTrue(AttachmentMsg.getText().contains("فضلا يجب اضافة علي الاقل 1 مرفق"));
		Thread.sleep(1000);
		System.out.println(AttachmentMsg.getText());
	}
}

