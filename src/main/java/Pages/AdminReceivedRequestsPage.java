package Pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;



public class AdminReceivedRequestsPage extends PageBase{

	public AdminReceivedRequestsPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}


	@FindBy (css = "[ng-if='DirectionId === null || DirectionId===undefined']")
	WebElement RecReqPageTitle;	
	@FindBy (css = "[ng-model='searchModel.RequestId']")
	WebElement ReqIDTxtBox;

	@FindBy (css = "[name='SchoolId'] .ui-select-search")
	WebElement StatusDDL;

	@FindBy (css = "[ng-click='GetAllRequests();']")
	WebElement SearchBtn;

	@FindBy (xpath = "//span[.='موافقة']")
	WebElement ApprovedStatusOption;

	@FindBy (xpath = "//span[.='مرفوض']")
	WebElement RejectedStatusOption;

	@FindBy (xpath = "//span[.='ملغى']")
	WebElement	CancelledStatusOption;
	@FindBy (xpath = "//div[@class='ui-select-choices ui-select-dropdown selectize-dropdown single ng-scope']//div[@class='ui-select-choices-row ng-scope']/div[contains(.,'موقوف')]")
	WebElement	StoppedStatusOption;

	@FindBy (css = "[data-balloon='التفاصيل']")
	WebElement	DetailsBtn;


	@FindBy (css = ".box .caption")
	WebElement	DetailsPageTitle;

	@FindBy (css = ".yellow")
	WebElement	DetailsPage_ViewAttachmentBtn;

	@FindBy (css = ".btn-icon-only > .fa")
	WebElement	DetailsPage_ViewAttachmentIcon;

	@FindBy (css = "[ng-if='RequestModel.RequestStatus==5'] > .col-md-10")
	WebElement	DetailsPage_RejectionReasonTxt;

	@FindBy (css = "site-header > .btn")
	WebElement	DetailsPage_BackBtn;

	@FindBy (css = "#dataTables-example tr:nth-of-type(1) .red")
	WebElement	RejectBtn;
	@FindBy (css = "[visible='modalRejectShow'] .modal-title")
	WebElement	Reject_PopUpTitle;
	@FindBy (css = "[ng-model='RejectModel.RejectReason']")
	WebElement	RejectReasonTxtBox;
	@FindBy (css = "[type='submit']")
	WebElement	Reject_PopUpSaveBtn;

	@FindBy (css = "#alertify-ok > .fa")
	WebElement	ConfirmRejection;

	@FindBy (css = "#alertify-cancel > .fa")
	WebElement	CancelRejection;

	@FindBy (css = "[visible='modalRejectShow'] .modal-footer > .btn")
	WebElement	Reject_PopUpCloseBtn;
	@FindBy (css = "[visible='showRejectReasonModal'] .btn")
	WebElement	Stop_PopUpCloseBtn;


	@FindBy (css = "#dataTables-example tr:nth-of-type(1) .RequestAccept")
	WebElement	ApprovedStatus;


	@FindBy (css = "tr:nth-of-type(4) .RequestRefused")
	WebElement	RejectedStatus;

	@FindBy (css = ".fa-download")
	WebElement	DownloadBtn;
	@FindBy (css = "[data-balloon='المرفقات']")
	WebElement	ViewAttachmentBtn;

	@FindBy (id="code")
	WebElement	Attach_DownloadBtn;


	@FindBy (css = "[visible='showAttachesModal'] .modal-title")
	WebElement	Attach_PopUpTitle;

	@FindBy (css = "[visible='showAttachesModal'] .modal-footer > .btn")
	WebElement	Attach_ClosePopUpBtn;


	@FindBy (css = "[ng-class='config.title']")
	WebElement	SuccessMsg;

	@FindBy (xpath = "//i[@class='fa fa-list']")
	WebElement VerificationLogBtn;

	@FindBy (css = "[visible='showVerifyResultModal'] .modal-title")
	WebElement	Verify_PopUpTitle;

	@FindBy (css = "[visible='showVerifyResultModal'] .btn")
	WebElement	Verify_ClosePopUpBtn;

	@FindBy(xpath = "//table[@id='dataTables-example']//tr[1]//i[@class='fa fa-pause']")
	WebElement ViewStopReasonBtn;

	@FindBy(css = "[visible='showRejectReasonModal'] .modal-title")
	WebElement StopReasonTitle;

	@FindBy(css = ".form-md-line-input > div:nth-of-type(1) > .CustomTextShow")
	WebElement StopReasonTxt;

	@FindBy(css = "[ng-if='isPausedRequest'] > .CustomTextShow")
	WebElement StoppedByTxt;
	@FindBy(css = "[ng-click='$root.ToggleNav();']")
	WebElement toggleMenu;


	public void ViewStopReason(String StopReason) throws InterruptedException {
		if (ViewStopReasonBtn.isEnabled()) {
			Thread.sleep(1000);
			clickButton(ViewStopReasonBtn);
			Thread.sleep(1000);
			//Assert.assertTrue(StopReasonTitle.getText().contains("عرض السبب"));
			Thread.sleep(1000);
			Assert.assertTrue(StopReasonTxt.getText().contains(StopReason));
			Assert.assertTrue(StoppedByTxt.isDisplayed());
			Thread.sleep(1000);
			System.out.println("Request stopped by " + StoppedByTxt.getText().toString());
			Thread.sleep(1000);
			clickButton(Stop_PopUpCloseBtn);
			Thread.sleep(2000);	
		}
		else {
			System.out.println("Stop reason displaying icon isn't displayed ... ");
		}
	}

	public void OpenRequestDetails() throws InterruptedException {
		Thread.sleep(1000);
		clickButton(DetailsBtn);
		Thread.sleep(5000);
		//Assert.assertTrue(DetailsPageTitle.getText().contains("تفاصيل الطلب"));
		Thread.sleep(1000);
		//click on ViewAttachmentButton in the bottom of Details Page
		scrollToBottom();
		Thread.sleep(1000);
		clickButton(DetailsPage_ViewAttachmentBtn);
		Thread.sleep(1000);
		clickButton(Attach_DownloadBtn);
		Thread.sleep(1000);	

		clickButton(Attach_ClosePopUpBtn);
		Thread.sleep(1000);
		scrollUp();
		clickButton(DetailsPage_ViewAttachmentIcon);
		Thread.sleep(3000);
		//Assert.assertTrue(Attach_PopUpTitle.getText().contains("عرض المرفقات"));
		Thread.sleep(1000);
		clickButton(Attach_DownloadBtn);
		Thread.sleep(1000);

		clickButton(Attach_ClosePopUpBtn);
		Thread.sleep(1000);
		clickButton(DetailsPage_BackBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RecReqPageTitle.getText().contains("طلبات التصديق المستلمة"));
	}
	public void OpenRequestDetailsForRejectedRequests(String RejectReason) throws InterruptedException {
		Thread.sleep(1000);
		clickButton(DetailsBtn);
		Thread.sleep(5000);
		Assert.assertTrue(DetailsPageTitle.getText().contains("تفاصيل الطلب"));
		Thread.sleep(1000);
		//click on ViewAttachmentButton in the bottom of Details Page
		scrollToBottom();
		//Assert.assertTrue(DetailsPage_RejectionReasonTxt.getText().contains(RejectReason));
		//Thread.sleep(1000);
		clickButton(DetailsPage_ViewAttachmentBtn);
		Thread.sleep(1000);
		clickButton(Attach_DownloadBtn);
		Thread.sleep(1000);	

		clickButton(Attach_ClosePopUpBtn);
		Thread.sleep(1000);
		scrollUp();
		clickButton(DetailsPage_ViewAttachmentIcon);
		Thread.sleep(3000);
		Assert.assertTrue(Attach_PopUpTitle.getText().contains("عرض المرفقات"));
		Thread.sleep(1000);
		clickButton(Attach_DownloadBtn);
		Thread.sleep(1000);

		clickButton(Attach_ClosePopUpBtn);
		Thread.sleep(1000);
		clickButton(DetailsPage_BackBtn);
		Thread.sleep(1000);
		Assert.assertTrue(RecReqPageTitle.getText().contains("طلبات التصديق المستلمة"));
	}
	public void ViewRequestAttachment() throws InterruptedException {
		Thread.sleep(3000);
		clickButton(ViewAttachmentBtn);
		Thread.sleep(3000);
		if (Attach_DownloadBtn.isEnabled()) {
			clickButton(Attach_DownloadBtn);
			Thread.sleep(1000);
		}
		else {
			System.out.println("No attachments displayed to download ... ");
		}
		clickButton(Attach_ClosePopUpBtn);
		Thread.sleep(1000);
	}

	public void ViewVerificationLog() throws InterruptedException {
		Thread.sleep(1000);
		clickButton(VerificationLogBtn);
		Thread.sleep(1000);
		//Assert.assertFalse(Verify_PopUpTitle.getText().contains("سجلات التحقق من الطلب"));
		Thread.sleep(1000);
		clickButton(Verify_ClosePopUpBtn);

	}
	public void RejectApprovedRequest(String RejectionReason) throws InterruptedException {
		Thread.sleep(1000);
		scrollLeft();
		Thread.sleep(1000);
		//Close PopUp without Save
		clickButton(RejectBtn);
		Thread.sleep(1000);
		setTextElementText(RejectReasonTxtBox, RejectionReason);
		Thread.sleep(1000);
		clickButton(Reject_PopUpCloseBtn);
		Thread.sleep(3000);
		Assert.assertTrue(ApprovedStatus.getText().contains("موافقة"));

		//Cancel Rejection After Click on Save
		clickButton(RejectBtn);
		Thread.sleep(1000);
		setTextElementText(RejectReasonTxtBox, RejectionReason);
		Thread.sleep(1000);
		clickButton(Reject_PopUpSaveBtn);
		Thread.sleep(1000);
		clickButton(CancelRejection);
		Thread.sleep(3000);
		Assert.assertTrue(ApprovedStatus.getText().contains("موافقة"));

		//Complete Rejection Process
		clickButton(RejectBtn);
		Thread.sleep(1000);
		setTextElementText(RejectReasonTxtBox, RejectionReason);
		Thread.sleep(1000);
		clickButton(Reject_PopUpSaveBtn);
		Thread.sleep(1000);
		clickButton(ConfirmRejection);
		Thread.sleep(3000);
		Assert.assertTrue(RejectedStatus.getText().contains("مرفوض"));

	}

	public void DownloadRequest() throws InterruptedException {
		Thread.sleep(1000);
		scrollLeft();
		Thread.sleep(1000);
		if (DownloadBtn.isEnabled()) {

			clickButton(DownloadBtn);
			Thread.sleep(5000);
			//Assert.assertTrue(SuccessMsg.getText().contains("تم تحميل الطلب بنجاح"));
			Thread.sleep(2000);
		}
		else {
			System.out.println("No attachments displayed to download ... ");
		}


	}
}

