package Pages;

import java.math.BigDecimal;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class RequestsLogPage extends PageBase{

	public RequestsLogPage(WebDriver driver) {
		super(driver);

		jse = (JavascriptExecutor) driver;
	}


	//Search Criteria

	@FindBy(css = "[ng-model='searchModel.requestStatus'] [placeholder]")
	WebElement RequestStatusDDL;

	@FindBy(css = "[repeat='c.id as c in RequestStatuses | filter: $select.search'] div:nth-of-type(5) > .option")
	WebElement ApprovedStatusOption;

	@FindBy(css = "[repeat='c.id as c in RequestStatuses | filter: $select.search'] div:nth-of-type(6) > .option")
	WebElement RejectedStatusOption;

	@FindBy(css = "div:nth-of-type(16) > .option")
	WebElement StoppedStatusOption;


	//Get Request Created Info.....
	public String ID ;
	@FindBy(css = "tr:nth-of-type(2) > td:nth-of-type(3)")
	WebElement RequestID;

	public String Type;
	@FindBy(css = "tr:nth-of-type(2) > td:nth-of-type(4)")
	WebElement RequestType;


	public String Status;
	@FindBy(css = "tr:nth-of-type(2) .RequestPending")
	WebElement RequestPendingStatus;


	@FindBy(css = "[data-balloon='موافقة']")
	WebElement RequestApprovedStatus;

	@FindBy(css = "[data-balloon='موقوف']")
	WebElement RequestStoppedStatus;

	@FindBy(css = ".RequestRefused")
	WebElement RequestRejectedStatus;


	@FindBy(css = "[data-balloon='ملغى']")
	WebElement RequestCancelledStatus;

	@FindBy(css = "[ng-model='searchModel.requestId']")
	WebElement requestIDTXT;

	@FindBy(css = ".note")
	WebElement DetailsNote;

	@FindBy(css = "[ng-click='SearchAllRequest();']") 
	WebElement searchBTN;
	@FindBy(css = ".wallet > .title") 
	WebElement Balance;
	public String CurrentBalance;
	//Settings Icon
	@FindBy (name = "settings-outline")
	WebElement SettingIcon;


	//View Attachments Option
	@FindBy (xpath = "//div[@class='btn-group ng-scope open']//a[.='عرض المرفقات']")
	WebElement ViewAttach;

	@FindBy (xpath = "//h4[.='عرض المرفقات']")
	WebElement AttachTitle;

	@FindBy (css = "[ng-click='AttachDownload(x)']")
	WebElement DownloadAttach;

	@FindBy (css = "[visible='page.showAttachmentsModal'] .modal-footer > .btn")
	WebElement CloseAttach;


	//Cancel Request Option
	@FindBy (css = "tr:nth-of-type(2) [ng-click='DeleteRequest(x.requestId)']")
	WebElement CancelReq;

	@FindBy (css = ".sweet-alert > h2")
	WebElement PopUpTitle;

	@FindBy (css = ".cancel")
	WebElement PopUpBackBtn;

	@FindBy (css = ".confirm")
	WebElement PopUpSubmitBtn;

	@FindBy (css = ".sa-help-text")
	WebElement PopUpWarningMsg;

	@FindBy (css = "[tabindex='3']")
	WebElement PopUpTxtBox;

	@FindBy (css = "[ng-class='config.title']")
	WebElement SuccessMsg;

	//View Cancel Reason Option
	@FindBy (xpath = "//div[@class='btn-group ng-scope open']//li[2]/a[1]")
	WebElement ViewOfReasonBtn;

	@FindBy (css = "[visible='showRejectReasonModal'] .modal-title")
	WebElement ViewOfReasonTitle;
	@FindBy (css = ".portlet .btn")
	WebElement CloseOfViewReason;

	@FindBy (css = ".CustomTextShow")
	WebElement ViewOfReasonTxt;
	@FindBy (css = ".col-sm-11 div:nth-of-type(1) > .CustomTextShow")
	WebElement StopReasonTxt;
	@FindBy (css = "[ng-if='isPausedRequest'] > .CustomTextShow")
	WebElement StoppedBy;


	//Options of Approved Request - Request Details 
	@FindBy(name="requestDetails")
	WebElement DetailsBtn;

	@FindBy (css = ".sp-box-title h2")
	WebElement DetailsTitle;

	@FindBy (css = "[ng-if='RequestModel.RequestStatus === 4']")
	WebElement DetailsDownloadBtn;

	@FindBy (className = "button.sp-main-button.sp-transparent-button.ng-binding")
	WebElement DetailsBackBtn;

	//Download Option
	@FindBy (xpath = "//div[@class='btn-group ng-scope open']//a[contains(.,'تحميل')]")
	WebElement DownloadBtn;

	//Send to Mail Option
	@FindBy (xpath = "//div[@class='btn-group ng-scope open']//a[contains(.,'إرسال لبريد إلكتروني')]")
	WebElement SendToMailBtn;


	//Stop Request
	@FindBy (xpath = "//div[@class='btn-group ng-scope open']//a[.='إيقاف الطلب']")
	WebElement StopReqBtn;

	@FindBy (xpath = "//p[@class='lead text-muted ']/div[contains(.,'علماً بأن مبلغ الطلب لن تُسترد قيمته')]")
	WebElement StopReqWarningMsg;



	public void SearchByStatus (String Status) throws InterruptedException {
		clickButton(RequestStatusDDL);
		if (Status == "Approved") {
			clickButton(ApprovedStatusOption);
		}
		else if (Status == "Rejected") {
			clickButton(RejectedStatusOption);
		}
		else if (Status == "UnderProcedure") {}
		else if (Status == "Expired") {}
		else if (Status == "Stopped") {
			clickButton(StoppedStatusOption);
		}
		Thread.sleep(2000);
		scrollToTheMiddle();
		clickButton(searchBTN);
		Thread.sleep(1000); 
	}

	public void ViewRequestDetails () throws InterruptedException {
		Thread.sleep(1000);
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(DetailsBtn);
		Thread.sleep(5000);
		scrollUp();
		Assert.assertTrue(DetailsTitle.getText().toString().contains("تفاصيل الطلب"));
		Thread.sleep(1000);
		scrollToTheMiddle();

		if (DetailsNote.getText().contains("عفواً . لا توجـد بيانات.")) {
			System.out.println("This Request Details are not displyed ... ");
			clickButton(DetailsBackBtn);
			Thread.sleep(1000);
		}

		else {
			scrollToBottom();
			if (DetailsDownloadBtn.isEnabled())
			{
				clickButton(DetailsDownloadBtn);
				Thread.sleep(8000);
			}
			else
			{ 
				System.out.println("Download option is't displayed ... ");
			}
		}
	}

	public void ViewRequestAttachment () throws InterruptedException {
		Thread.sleep(1000);
		clickButton(SettingIcon);
		Thread.sleep(1000);
		if (ViewAttach.isEnabled()) {
			clickButton(ViewAttach);
			Thread.sleep(2000);
			clickButton(DownloadAttach);
			Thread.sleep(3000);
			clickButton(CloseAttach);
			Thread.sleep(1000);
		}
		else {
			System.out.println("View attachments option isn't displyed ... ");
		}
	}

	public void DownloadRequest () throws InterruptedException {

		Thread.sleep(1000);
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(DownloadBtn);
		Thread.sleep(5000);
	}

	public void SendRequestToMail (String ValidMail, String InvalidMail) throws InterruptedException {
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(SendToMailBtn);
		Thread.sleep(2000);
		Assert.assertTrue(PopUpTitle.getText().contains("فضلاً , أدخل البريد المراد إرسال النموذج إليه"));
		Thread.sleep(1000);
		clickButton(PopUpBackBtn);
		Thread.sleep(1000);
		//Try to Click on CancelRequest Then Press Back Btn on the final step
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(SendToMailBtn);
		Thread.sleep(2000);
		//Assert.assertTrue(PopUpTitle.getText().contains("فضلاً , أدخل البريد المراد إرسال النموذج إليه"));
		Thread.sleep(1000);
		clickButton(PopUpSubmitBtn);
		Thread.sleep(2000);
		//Assert.assertTrue(PopUpWarningMsg.getText().contains("فضلاً , أدخل البريد المراد إرسال النموذج إليه"));
		Thread.sleep(1000);
		setTextElementText(PopUpTxtBox, InvalidMail);
		Thread.sleep(1000);
		clickButton(PopUpSubmitBtn);
		Thread.sleep(1000);
		Assert.assertTrue(SuccessMsg.getText().contains("أدخل البريد بشكل صحيح"));
		Thread.sleep(1000);
		PopUpTxtBox.clear();
		Thread.sleep(1000);
		setTextElementText(PopUpTxtBox, ValidMail);
		Thread.sleep(1000);
		clickButton(PopUpSubmitBtn);
		Thread.sleep(3000);
		//Assert.assertTrue(SuccessMsg.getText().contains("تم إرسال الوثيقة بنجاح"));
		Thread.sleep(1000);
	}

	public void StopRequest (String StopReason) throws InterruptedException {
		clickButton(SettingIcon);
		Thread.sleep(1000);
		if (StopReqBtn.isEnabled()) {
			clickButton(StopReqBtn);
			Thread.sleep(1000);
			Assert.assertTrue(StopReqWarningMsg.getText().contains("علماً بأن مبلغ الطلب لن تُسترد قيمته"));
			Thread.sleep(1000);
			clickButton(PopUpBackBtn);
			Thread.sleep(1000);
			//Try to Click on Stop Request Then Press Back Btn on the final step
			clickButton(SettingIcon);
			Thread.sleep(1000);
			clickButton(StopReqBtn);
			Thread.sleep(1000);
			Assert.assertTrue(StopReqWarningMsg.getText().contains("علماً بأن مبلغ الطلب لن تُسترد قيمته"));
			Thread.sleep(1000);
			clickButton(PopUpSubmitBtn);
			Thread.sleep(1000);
			clickButton(PopUpBackBtn);
			Thread.sleep(1000);
			//stop request Complete cycle
			clickButton(SettingIcon);
			Thread.sleep(1000);
			clickButton(StopReqBtn);
			Thread.sleep(1000);
			Assert.assertTrue(StopReqWarningMsg.getText().contains("علماً بأن مبلغ الطلب لن تُسترد قيمته"));
			Thread.sleep(1000);
			clickButton(PopUpSubmitBtn);
			Thread.sleep(1000);
			Assert.assertTrue(PopUpTitle.getText().contains("أدخل سبب إيقاف الطلب"));
			Thread.sleep(1000);
			clickButton(PopUpSubmitBtn);
			Thread.sleep(1000);
			Assert.assertTrue(PopUpWarningMsg.getText().contains("أدخل سبب إيقاف الطلب"));
			Thread.sleep(1000);
			setTextElementText(PopUpTxtBox, StopReason);
			Thread.sleep(1000);
			clickButton(PopUpSubmitBtn);
			Thread.sleep(1000);
			Assert.assertTrue(SuccessMsg.getText().contains("تم إيقاف الطلب بنجاح"));
			Thread.sleep(1000);
			scrollToBottom();
			Thread.sleep(1000);
			scrollToTheMiddle();
		}
		else {
			System.out.println("Stop request option is not displayed ... ");
		}
	}

	public void CancelRequest (String CancelReason) throws InterruptedException {
		//Try to Click on CancelRequest Then Press Back Btn
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(CancelReq);
		Thread.sleep(1000);
		Assert.assertTrue(PopUpTitle.getText().contains("هل أنت متأكد من إلغاء هذا الطلب؟"));
		Thread.sleep(1000);
		clickButton(PopUpBackBtn);
		Thread.sleep(1000);
		//Try to Click on CancelRequest Then Press Back Btn on the final step
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(CancelReq);
		Assert.assertTrue(PopUpTitle.getText().contains("هل أنت متأكد من إلغاء هذا الطلب؟"));
		Thread.sleep(1000);
		clickButton(PopUpSubmitBtn);
		Thread.sleep(1000);
		Assert.assertTrue(PopUpTitle.getText().contains("فضلا أدخل سبب الإلغاء"));
		Thread.sleep(1000);
		clickButton(PopUpSubmitBtn);
		Thread.sleep(1000);
		Assert.assertTrue(PopUpWarningMsg.getText().contains("سبب الرفض مطلوب"));
		Thread.sleep(1000);
		clickButton(PopUpBackBtn);
		Thread.sleep(1000);
		//Complete Cancellation
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(CancelReq);
		Thread.sleep(1000);
		Assert.assertTrue(PopUpTitle.getText().contains("هل أنت متأكد من إلغاء هذا الطلب؟"));
		Thread.sleep(1000);
		clickButton(PopUpSubmitBtn);
		Thread.sleep(1000);
		Assert.assertTrue(PopUpTitle.getText().contains("فضلا أدخل سبب الإلغاء"));
		Thread.sleep(1000);
		setTextElementText(PopUpTxtBox,CancelReason);
		Thread.sleep(1000);
		clickButton(PopUpSubmitBtn);
		Thread.sleep(1000);
		Assert.assertTrue(SuccessMsg.getText().contains("تم إلغاء الطلب بنجاح"));
		Thread.sleep(1000);	

	}

	public void ViewCancelReason (String CancelReason) throws InterruptedException {
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(ViewOfReasonBtn);
		Thread.sleep(1000);
		Assert.assertTrue(ViewOfReasonTitle.getText().contains("عرض السبب"));
		Thread.sleep(1000);
		System.out.println(ViewOfReasonTxt.getText().toString());
		//Assert.assertTrue(ViewOfReasonTxt.getText().contains(CancelReason));	
		Thread.sleep(1000);
		clickButton(CloseOfViewReason);
		Thread.sleep(1000);

	}


	public void ViewRejectionReason(String RejectReason) throws InterruptedException {

		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(ViewOfReasonBtn);
		Thread.sleep(1000);
		Assert.assertTrue(ViewOfReasonTitle.getText().contains("عرض السبب"));
		Thread.sleep(1000);
		System.out.println(ViewOfReasonTxt.getText());
		Thread.sleep(2000);
		Assert.assertTrue(ViewOfReasonTxt.getText().contains(RejectReason));
		Thread.sleep(1000);
		clickButton(CloseOfViewReason);
		scrollUp();
		Thread.sleep(1000);

	}
	
	public void DetailsPage() throws InterruptedException {
		
		Thread.sleep(1000);
		clickButton(DetailsDownloadBtn);
		Thread.sleep(10000);
	}

	public void ViewStopReason(String StopReason) throws InterruptedException {
		clickButton(SettingIcon);
		Thread.sleep(1000);
		clickButton(ViewOfReasonBtn);
		Thread.sleep(1000);
		Assert.assertTrue(ViewOfReasonTitle.getText().contains("عرض السبب"));
		Thread.sleep(1000);
		Assert.assertTrue(StopReasonTxt.isDisplayed());
		Thread.sleep(1000);
		Assert.assertTrue(StopReasonTxt.getText().contains(StopReason));
		Thread.sleep(1000);
		Assert.assertTrue(StoppedBy.isDisplayed());
		Thread.sleep(1000);
		clickButton(CloseOfViewReason);
		Thread.sleep(1000);
	}

	//Request Under Procedure 
	public void GetRequestInfo(String RequestStatus) throws InterruptedException {

		// رقم الطلب - نوع الطلب - حالة الطلب
		ID= new BigDecimal(RequestID.getText()).toString();
		System.out.println(ID);
		Type = RequestType.getText();
		System.out.println(Type);

		if (RequestStatus == "تحت الاجراء") {
			Status = RequestPendingStatus.getText();
			System.out.println(Status);
		}
		else if (RequestStatus == "موافقة") {
			Status = RequestApprovedStatus.getText();
			System.out.println(Status);
		}
		else if (RequestStatus == "مرفوض") {
			Status = RequestRejectedStatus.getText();
			System.out.println(Status);
		}
		else if (RequestStatus == "موقوف") {
			Status = RequestStoppedStatus.getText();
			System.out.println(Status);
		}
		else if (RequestStatus == "ملغى") {
			Status = RequestCancelledStatus.getText();
			System.out.println(Status);
		}
		//Set Current Balance
		CurrentBalance = Balance.getText();
		System.out.println(CurrentBalance);
	}

	public void MemberCheckRequestStatus(String requestID) throws InterruptedException {
		Thread.sleep(2000);
		clearWebElement(requestIDTXT);
		Thread.sleep(1000);
		setTextElementText(requestIDTXT,requestID);
		Thread.sleep(2000);
		clickButton(searchBTN);
		Thread.sleep(1000);
		scrollToBottom();		
	}
}
