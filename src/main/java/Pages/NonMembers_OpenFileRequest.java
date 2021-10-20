package Pages;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class NonMembers_OpenFileRequest extends PageBase{

	public NonMembers_OpenFileRequest(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}



	@FindBy
	(css = "[ng-click='RedirectNonMembers();']")
	WebElement NonMemberCard;
	
	@FindBy(css = ".sp-box-title h2")
	public WebElement Main;

	@FindBy(css = "#nav-requests h2")
	public WebElement OpenRequestsText;

	@FindBy(id = "nav-tab")
	List<WebElement> HeaderList;

	@FindBy(id = "nav-tabContent")
	List<WebElement> RequestsList;

	@FindBy(css = "[href='/#/Non-Members/Requests/New/59'] > .main-list-item-name")
	WebElement openFileRequest;

	@FindBy(css = "body > div.sweet-alert.showSweetAlert.visible")
	WebElement popupOpenFileText;
	
	@FindBy(css = ".sweet-alert > h2")
	WebElement confirmText;
	
	@FindBy(css = ".confirm")
	WebElement confirmPOpup;

	@FindBy(name = "RequiredPagesToAttestation")
	WebElement PageNums;

	@FindBy(name = "RequestReason")
	WebElement requestReasonText;

	@FindBy(css = "[ng-if='RequestModel.requestType != 56 && (RequestModel.AttachmentsNumber == 0 && RequestModel.AddAttachment) || (RequestModel.AttachmentsNumber == 1 || RequestModel.AttachmentsNumber == 0 || RequestModel.AttachmentsNumber == null)']")
	WebElement fileUpload;	

	@FindBy(name = "AcceptTerms")
	WebElement AcceptTermsCheckbox;

	String fileName = "pdf-test.pdf";
	String FileUploader = System.getProperty("user.dir") + "/uploads/" + fileName;

	@FindBy(name = "next")
	WebElement nextBTN;
	
	@FindBy(css = "[ng-click='RequestModel.ConfirmAdd = true;SwitchSteps(page.currentStep + 1,mainForm,mainFormEvent,mainFormGrid);']")
	WebElement confirmAddBTN;
	
	@FindBy(css = "body > div.page-wrap.ng-isolate-scope > div.dashboard-content > div > div.sp-main-modal.modal.fade.ng-scope.in > div > div")
	WebElement PopupContent;
	
	@FindBy(css = "[name='termsForm'] .main-modal-title")
	WebElement popupTitle;
	
	@FindBy(css = "[ng-class='{\\'fileDownloading\\':IsDownloading==true}'] tr:nth-of-type(2) > td:nth-of-type(2)")
	WebElement requestID;
	public String ID ;

	@FindBy(css = "[ng-class='{\\'fileDownloading\\':IsDownloading==true}'] tr:nth-of-type(2) > td:nth-of-type(3)")
	WebElement requestDate;
	public String Date ;

	@FindBy(css = "[ng-class='{\\'fileDownloading\\':IsDownloading==true}'] tr:nth-of-type(2) > td:nth-of-type(4)")
	WebElement requestType;
	public String Type ;
	
	@FindBy(css = "[ng-class='{\\'fileDownloading\\':IsDownloading==true}'] tr:nth-of-type(2) .RequestPending")
	WebElement requestStatus;
	public String status ;
	
	@FindBy(css = "[ng-class='config.title']")
	public WebElement requestAppliedText;
	
	
	
	public void OpenNonMemberCard() throws InterruptedException {


		if (NonMemberCard.isDisplayed()) {

			NonMemberCard.click();
		}
		
		else {
			
			System.out.println("You redirected already into the NonSubscribers page ... ");
		}
	}


	public void NonMember_OpenFileRequest(String PagesNumber, String requestReason) throws InterruptedException {

		System.out.println(Main.getText().toString());
		Assert.assertTrue(Main.getText().toString().contains("الرئيسية"));
		Thread.sleep(1000);
		for (WebElement TabContent : HeaderList) {

			System.out.println(TabContent.getText().toString());
		}
		Thread.sleep(500);

		for (WebElement Requests : RequestsList) {

			System.out.println(Requests.getText().toString());
			Thread.sleep(500);

			if (openFileRequest.isEnabled()) {
				openFileRequest.click();
				Thread.sleep(500);
			}
		}
		Thread.sleep(1000);
		System.out.println(popupOpenFileText.getText().toString());
		Thread.sleep(1000);
		Assert.assertTrue(confirmText.getText().toString().contains("هذا الطلب موجه فقط للأفراد الذين لا يتبعون لمنشأة خاصة"));
		Thread.sleep(1000);
		clickButton(confirmPOpup);
		Thread.sleep(3000);
		setTextElementText(PageNums, PagesNumber);
		Thread.sleep(1000);
		setTextElementText(requestReasonText, requestReason);
		Thread.sleep(1000);
		scrollToTheMiddle();
		Thread.sleep(1000);
		fileUpload.sendKeys(FileUploader);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,450)");
		Thread.sleep(2000);
		clickButton(AcceptTermsCheckbox);
		Thread.sleep(2000);
		clickButton(nextBTN);
		Thread.sleep(1000);
		Assert.assertTrue(popupTitle.getText().toString().contains("لقد تمت الموافقة على الشروط والسياسات للطلبات و إدخال البيانات الصحيحة للطلب"));
		Thread.sleep(1000);
		System.out.println(PopupContent.getText().toString());
		Thread.sleep(1000);
		clickButton(confirmAddBTN);
		Thread.sleep(1000);
		scrollToBottom();
		Thread.sleep(1000);
		clickButton(nextBTN);
		Thread.sleep(6000);
		Assert.assertTrue(requestAppliedText.getText().toString().contains("تم تقديم الطلب بنجاح"));
		Thread.sleep(1000);
		jse.executeScript("window.scrollBy(0,-450)", "");
		Thread.sleep(1000);


	}
	
	public void GetOpenFileRequestCreatedInfo_NonSubscribers() throws InterruptedException {

		// رقم الطلب - نوع الطلب - حالة الطلب
		ID= new BigDecimal(requestID.getText()).toString();
		System.out.println(ID);
		Thread.sleep(1000);

		Type = requestType.getText();
		System.out.println(Type);
		Thread.sleep(1000);

		status = requestStatus.getText();
		System.out.println(status);
		Thread.sleep(1000);	
		
		Date = requestDate.getText();
		System.out.println(Date);
		Thread.sleep(2000);	
	}

	
	@FindBy(css = "[ng-click='pageIndex  =2;getNonMemberRequests();searchModel.currentPage = 1;']")
	WebElement nonSubscribers_RequestsLog;
	
	@FindBy(css = "#nav-all-requests > div > div.sp-main-container > div > table > tbody > tr:nth-child(2)")
	List<WebElement> Rows;
	
	int rowCount = Rows.size();
	
	public void CheckRequestStatus() throws InterruptedException {
		
		if (nonSubscribers_RequestsLog.isEnabled()) {
			
			clickButton(nonSubscribers_RequestsLog);
			Thread.sleep(1000);				
			for (WebElement rowNum : Rows) {
			
				System.out.println(rowNum.getText().toString());
				Thread.sleep(1000);	

			}

		}
	}
	
	
	@FindBy(name = "settings-outline")
	WebElement options;
	
	@FindBy(css = "#nav-all-requests > div > div.sp-main-container > div > table > tbody > tr:nth-child(10) > td:nth-child(8) > div > ul")
	List<WebElement> OptionsList;
	
	@FindBy(css = "[ng-class='{\\'fileDownloading\\':IsDownloading==true}'] tr:nth-of-type(2) [ng-if='x.requestStatus==12 || x.requestStatus==13 || x.requestStatus==1'] > .customized-btn")
	WebElement CancelRequestOption;
	
	
	
	public void UserCancelRequestCreated() throws InterruptedException {
		
		if (requestID.isDisplayed()) {
			clickButton(options);
			
			try {
				
				for (WebElement option : OptionsList) {
					
					System.out.println(option.getText().toString());
					
					if (CancelRequestOption.isDisplayed()) {
						clickButton(CancelRequestOption);
					}
				}		
			} 
			
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
			
		}
		Thread.sleep(1000);	
		

	}

}
