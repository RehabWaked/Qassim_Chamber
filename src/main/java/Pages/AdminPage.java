package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AdminPage extends PageBase{

	public AdminPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}

	@FindBy(name = "username")
	WebElement adminUserName;

	@FindBy(name = "password")
	WebElement adminPassword;

	@FindBy(css = "[type='submit']")
	WebElement loginBTN;

	@FindBy(css = "[ng-show='CHMS_ManageMemberPortalManageRequests'] > .nav-link > .title")
	WebElement managementRequests;

	@FindBy(css = "[ng-show='CHMS_ManageMemberPortalManageRequests'] > .sub-menu > li:nth-of-type(2) > .nav-link > .title")
	WebElement subscribeManagementRequests;

	@FindBy(css = ".nav-link[ng-href='/#/AllRequests/1'] > .title")
	WebElement newRequests;

	@FindBy(css = "[ng-model='searchModel.RequestId']")
	WebElement AdminRequestNumTXT;
	
	@FindBy(css = "[ng-click='GetAllRequests();']")
	WebElement searchBTN;

	@FindBy(css = "#dataTables-example tr:nth-of-type(1) [ng-click='ApproveRejectRequest(x,true)']")
	WebElement ApprovedRequest;

	@FindBy(css = "#alertify-cancel > .fa")
	WebElement cancelProcess;

	@FindBy(css = ".alertify-button-ok")
	WebElement alertConfirmApprove;

	@FindBy(css = ".fa-eye")
	WebElement eyeIcon;

	@FindBy(css = "[ng-click='buttonAcceptDisabled === false &&  showRejectModal(RequestModel);']")
	WebElement RejectRequest;

	@FindBy(partialLinkText = "رفض")
	WebElement RejectRequest1;

	@FindBy(css = "[visible='modalRejectShow'] .modal-footer > .btn")
	WebElement closeRejectPopup;

	@FindBy(name = "rejectReason")
	WebElement rejectReasonTXT;

	@FindBy(css = "#alertify-ok > .fa")
	WebElement confirmRejection;

	@FindBy(css = ".font-red")
	public WebElement NoDataFound;

	@FindBy(css = "[ng-click='$root.ToggleNav();']")
	WebElement toggleMenu;

	@FindBy(css = "[ng-href='/#/AllRequests'] > .title")
	WebElement ReceivedRequests;

	@FindBy(xpath = "//table[@id='dataTables-example']//tr[1]//a[contains(.,'رفض')]")
	WebElement rejectApprovedRequest;

	@FindBy(css = "[ng-model='RejectModel.RejectReason']")
	WebElement rejectRequestReasonTXT;

	@FindBy(css = "[ng-if='chamberSettings.EnableNonMembers'] > .nav-link > .title")
	WebElement nonSubscribers;

	@FindBy(css = "[ng-href='/#/NonMembers/AllRequests/13'] > .title")
	WebElement nonSubscriber_NewRequests;

	@FindBy(css = ".nav-toggle[ng-href='#/Home'] > .title")
	WebElement HomePage;

	@FindBy(css = "[ng-model='searchModel.RequestId']")
	WebElement nonSubscriber_RequestID;

	@FindBy(css = "[ng-show='x.requestStatus!=5']")
	public WebElement AdminRequestStatus;

	@FindBy(css = "[ng-class='config.title']")
	public WebElement processDone;

	@FindBy (css = "[ng-show='CHMS_ManageMemberPortalManageRequests'] li:nth-of-type(5) > .nav-link")
	WebElement SubscribersSettings;

	@FindBy (css = "[ng-href='/#/RequestsTypeSettings/false']")
	WebElement SubscribersRequestsSettings;



	public void AdminLoginPage(String userName, String Password) throws InterruptedException {

		setTextElementText(adminUserName, userName);
		Thread.sleep(1000);
		setTextElementText(adminPassword, Password);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(1000);
	}

	public void OpenSubscribersRequestsSettingsPage () throws InterruptedException {
		Thread.sleep(1000);
		clickButton(HomePage);
		Thread.sleep(1000);
		clickButton(managementRequests);
		Thread.sleep(1000);
		clickButton(subscribeManagementRequests);
		scrollToTheMiddle();
		Thread.sleep(1000);
		clickButton(SubscribersSettings);
		Thread.sleep(1000);
		clickButton(SubscribersRequestsSettings);
	}

	public void OpenSubscribersNewRequestsPage () throws InterruptedException {
		Thread.sleep(1000);
		clickButton(HomePage);
		Thread.sleep(1000);
		clickButton(managementRequests);
		Thread.sleep(1000);
		clickButton(subscribeManagementRequests);
		Thread.sleep(1000);
		clickButton(newRequests);
	}
	

	public void OpenSubscribersReceivedRequestsPage () throws InterruptedException {

		Thread.sleep(1000);
		clickButton(HomePage);
		Thread.sleep(1000);
		clickButton(managementRequests);
		Thread.sleep(1000);
		clickButton(subscribeManagementRequests);
		Thread.sleep(1000);
		clickButton(ReceivedRequests);
		Thread.sleep(1000);
	}

	public void NavigateToReceivedRequestsPage () throws InterruptedException {
		scrollUp();
		Thread.sleep(1000);
		clickButton(ReceivedRequests);
		Thread.sleep(1000);
	}

	public void AdminSearchforRequest (String RequestID) throws InterruptedException {
		AdminRequestNumTXT.clear();
		Thread.sleep(1000);
		setTextElementText(AdminRequestNumTXT, RequestID);
		Thread.sleep(2000);
		clickButton(searchBTN);
		Thread.sleep(1000);
		scrollToBottom();
	}

	public void CloseToggleMenu () throws InterruptedException {
		
		Thread.sleep(1000);
		clickButton(toggleMenu);
		Thread.sleep(1000);
	}

	public void AdminApprovedRequestCreated(String RequestID) throws InterruptedException {

		AdminSearchforRequest(RequestID);
		clickButton(ApprovedRequest);
		Thread.sleep(1000);
		clickButton(cancelProcess);
		Thread.sleep(1000);
		clickButton(ApprovedRequest);
		Thread.sleep(1000);
		clickButton(alertConfirmApprove);
		Thread.sleep(1000);

	}

	public void AdminRejectedRequestCreated(String RequestID, String RejectionReason) throws InterruptedException {
		
		AdminSearchforRequest(RequestID);
		Thread.sleep(1000);
		clickButton(RejectRequest1);
		Thread.sleep(1000);
		clickButton(closeRejectPopup);
		Thread.sleep(1000);
		clickButton(RejectRequest1);
		Thread.sleep(1000);
		setTextElementText(rejectReasonTXT, RejectionReason);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(1000);
		clickButton(cancelProcess);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(1000);
		clickButton(confirmRejection);
		Thread.sleep(1000);
	}

	public void AdminRejectApprovedRequestCreated(String RequestID, String RejectionReason) throws InterruptedException {

		AdminSearchforRequest(RequestID);
		Thread.sleep(1000);
		clickButton(rejectApprovedRequest);
		Thread.sleep(1000);
		clickButton(closeRejectPopup);
		Thread.sleep(1000);
		clickButton(rejectApprovedRequest);
		Thread.sleep(1000);
		setTextElementText(rejectRequestReasonTXT, RejectionReason);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(1000);
		clickButton(cancelProcess);
		Thread.sleep(1000);
		clickButton(loginBTN);
		Thread.sleep(1000);
		clickButton(confirmRejection);
		Thread.sleep(1000);
	}

	public void AdminRejectOpenFileRequest_NonMemberServices(String RequestID, String rejectReason) throws InterruptedException {

		clickButton(managementRequests);
		Thread.sleep(1000);
		clickButton(nonSubscribers);
		Thread.sleep(1000);
		clickButton(nonSubscriber_NewRequests);
		Thread.sleep(1000);
		setTextElementText(nonSubscriber_RequestID, RequestID);
		Thread.sleep(1000);
		clickButton(searchBTN);
		Thread.sleep(1000);

		if (AdminRequestStatus.getText().contains("تحت الاجراء")) {

			clickButton(eyeIcon);
			Thread.sleep(5000);
			scrollToBottom();
			clickButton(RejectRequest);
			Thread.sleep(1000);
			setTextElementText(rejectRequestReasonTXT, rejectReason);
			Thread.sleep(1000);
			clickButton(loginBTN);
			Thread.sleep(1000);
			clickButton(confirmRejection);
			Thread.sleep(1000);

		}

	}







}
