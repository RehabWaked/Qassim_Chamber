package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Pages.AdminPage;
import Pages.AdminReceivedRequestsPage;
import Pages.AdminRequestSettingsPage;
import Pages.HomePage;
import Pages.LandingPage;
import Pages.LoginPage;
import Pages.ReExportrequestPage;
import Pages.RequestsLogPage;
import Pages.RequestsPage;
import Pages.WalletTransactionsPage;
import data.GeneralData_JSONDataReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase 
{
	public static WebDriver driver ; 

	public static String downloadPath = System.getProperty("user.dir") + "\\downloads";
	public static String uploadPath = System.getProperty("user.dir") + "\\uploads";


	public static FirefoxOptions firefoxOption()
	{
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}

	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		HashMap<String, Object	> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}
	


	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) throws UnsupportedEncodingException, MalformedURLException 
	{

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver = new ChromeDriver(chromeOption()); 
		}

		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			driver = new FirefoxDriver(firefoxOption()); 
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.navigate().to("https://mbr.rassd.sa/#/Login");
		

	} 


	@AfterSuite
	public void stopDriver() 
	{
		driver.quit();
	}

	// take screenshot when test case fail and add it in the Screenshot folder

	//	@AfterMethod
	//	public void screenshotOnFailure(ITestResult result) throws IOException 
	//	{
	//		if (result.getStatus() == ITestResult.FAILURE)
	//		{
	//			try {
	//				System.out.println("Failed!");
	//				System.out.println("Taking Screenshot....");
	//				Helper.captureScreenshot(driver, result.getName());
	//			}
	//			catch (Exception e) {
	//
	//				System.out.println(result.getStatus() == ITestResult.SUCCESS);
	//				System.out.println(result.getStatus() == ITestResult.SKIP);
	//			}
	//		} 
	//	}




	//Main Functions to inherit in all classes
	LoginPage LoginObj;
	LandingPage LandingObj;
	HomePage HomeObj;
	RequestsPage ReqObj;
	RequestsLogPage ReqLogObj;
	AdminPage AdminObj;
	AdminRequestSettingsPage ReqSettObj;
	GeneralData_JSONDataReader loginReader;
	String AdminURL = "https://www.worldofss.co:7772/#/Login";
	String MemberURL = "https://mbr.rassd.sa/#/Login" ;
	ReExportrequestPage ReExportObj;
	WalletTransactionsPage WallTransObj;
	public int CurrentAvailableBalance;
	public int RequestCost;
	AdminReceivedRequestsPage AdminRecReqObj;
	public String RequestID ;
	public boolean HasBalance;
	public String RejectReason, StopReason; 
	public int FirstBalance, NewBalance;

	//تحت الاجراء - رفض - الغاء
	public void CheckBalanceAfterSubmission_Cancellation_Rejection() throws InterruptedException {
		//Check Balance After Request Submission
		NewBalance = CheckCurrentWalletBalance();
		System.out.println("Your Fisrt Balance is: " + FirstBalance);
		System.out.println("Request cost is: " + RequestCost);
		System.out.println("Your New Balance is: " + NewBalance);
		Assert.assertEquals(NewBalance, FirstBalance); 
	}

	
	// رصيد المحفظة فى حالة الموافقة - ايقاف الطلب
	public void CheckBalanceAfterApproval_Stopping() throws InterruptedException {
		//Check Balance After Request Submission
		NewBalance = CheckCurrentWalletBalance();
		System.out.println("Your Current Balance is: " + FirstBalance);
		System.out.println("Request cost is: " + RequestCost);
		System.out.println("Your New Balance is: " + NewBalance);
		Assert.assertEquals(NewBalance, FirstBalance-RequestCost); 
	}
	public void AdminCheckOptionsForApprovedRequest () throws InterruptedException {
		AdminObj.NavigateToReceivedRequestsPage();
		AdminObj.AdminSearchforRequest(RequestID);
		AdminObj.CloseToggleMenu();
		AdminRecReqObj = new AdminReceivedRequestsPage(driver);
		AdminRecReqObj.ViewRequestAttachment();
		AdminRecReqObj.DownloadRequest();
		AdminRecReqObj.ViewVerificationLog();
		AdminRecReqObj.OpenRequestDetails();

	}
	public void AdminCheckOptionsForRejectedAfterApprovalRequest () throws InterruptedException {
		AdminRecReqObj = new AdminReceivedRequestsPage(driver);
		AdminRecReqObj.ViewRequestAttachment();
		AdminRecReqObj.ViewVerificationLog();
		AdminRecReqObj.OpenRequestDetailsForRejectedRequests(RejectReason);

	}	
	public void AdminCheckOptionsForRejectedRequest () throws InterruptedException {
		AdminObj.NavigateToReceivedRequestsPage();
		AdminObj.AdminSearchforRequest(RequestID);
		AdminObj.CloseToggleMenu();
		AdminRecReqObj = new AdminReceivedRequestsPage(driver);
		AdminRecReqObj.ViewRequestAttachment();
		AdminRecReqObj.ViewVerificationLog();
		AdminRecReqObj.OpenRequestDetailsForRejectedRequests(RejectReason);
	}
	
	public void AdminCheckOptionsForStoppedRequest () throws InterruptedException {
		AdminObj.OpenSubscribersReceivedRequestsPage();
		AdminObj.AdminSearchforRequest(RequestID);
		AdminObj.CloseToggleMenu();
		AdminRecReqObj = new AdminReceivedRequestsPage(driver);
		AdminRecReqObj.ViewRequestAttachment();
		AdminRecReqObj.DownloadRequest();
		AdminRecReqObj.ViewVerificationLog();
		AdminRecReqObj.ViewStopReason(StopReason);
		AdminRecReqObj.OpenRequestDetails();
	}

	public void MemberCheckOptionsForStoppedRequest () throws InterruptedException, FileNotFoundException, IOException, ParseException {

		Thread.sleep(2000);
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(2000);
		
	}
	public void MemberCheckOptionsForApprovedRequest () throws InterruptedException, FileNotFoundException, IOException, ParseException {
		Thread.sleep(2000);
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(2000);
		ReqLogObj.DownloadRequest();
		Thread.sleep(10000);
		SendRequestToMail();
		ReqLogObj.ViewRequestDetails();


	}
	public void MemberCheckOptionsForRejectedRequest () throws InterruptedException, FileNotFoundException, IOException, ParseException {
		Thread.sleep(2000);
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(2000);
		ReqLogObj.ViewRejectionReason(RejectReason);

	}

	// Check the available balance only
	public int GetAvailableBalance() throws InterruptedException {
		HomeObj = new HomePage(driver);
		HomeObj.OpenWalletTransactions();
		WallTransObj = new WalletTransactionsPage (driver);
		int x = WallTransObj.CalculateAvailableBalance();
		return x;

	}

	//Get the request cost for طلب وزارة الخارجية
	public int GetRequestCost(String RequestName) throws InterruptedException {
		HomeObj = new HomePage(driver);
		HomeObj.OpenRequestsPage();
		ReqObj = new RequestsPage(driver);
		int x = ReqObj.GetRequestPrice(RequestName);
		return x;

	}

	public void LoginToMemberPortal () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		LoginObj = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		LoginObj.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);
		Thread.sleep(2000);
		LandingObj= new LandingPage(driver);
		LandingObj.OpenTheCurrentCard();
		ClosePopupBanner();
		
	}

	//Open Member portal - Chamber
	public void NavigateToMemberPortal () throws InterruptedException, FileNotFoundException, IOException, ParseException {
		driver.navigate().to(MemberURL);
		Thread.sleep(2000);
		LoginObj = new LoginPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_Login();
		LoginObj.loginSuccess(loginReader.userID, loginReader.password, loginReader.OTP);
		Thread.sleep(2000);
		LandingObj = new LandingPage (driver);
		LandingObj.OpenTheCurrentCard();
		Thread.sleep(1000);
		ClosePopupBanner();

	}

	//open admin portal - Management
	public void NavigateToAdminPortal () throws InterruptedException, FileNotFoundException, IOException, ParseException {

		driver.navigate().to(AdminURL);
		Thread.sleep(2000);
		AdminObj = new AdminPage(driver);
		loginReader = new GeneralData_JSONDataReader();
		loginReader.jsonReader_AdminLogin();
		AdminObj.AdminLoginPage(loginReader.AdminUsername, loginReader.AdminPassword);
		RejectReason = loginReader.RejectReasonText;
	}


	public void AdminRejectNewRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {	
		AdminObj.OpenSubscribersNewRequestsPage();
		AdminObj.AdminRejectedRequestCreated(RequestID, RejectReason);
		Thread.sleep(1000);

	}
	
	public void AdminApprovedNewRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {
		AdminObj.OpenSubscribersNewRequestsPage();
		AdminObj.AdminApprovedRequestCreated(ReqLogObj.ID);
		Thread.sleep(1000);
		//Assert.assertTrue(AdminObj.NoDataFound.getText().contains("عفواً , لا توجد بيانات للعرض"));
		Thread.sleep(1000);
	}
	
	public void AdminRejectApprovedRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {
		AdminObj.OpenSubscribersReceivedRequestsPage();
		AdminObj.CloseToggleMenu();
		AdminObj.AdminRejectApprovedRequestCreated(RequestID, RejectReason);
		Thread.sleep(1000);
	}
	
	
	// Open only the new request page with the different requests
	public void OpenRequestsPage() throws InterruptedException {
		HomeObj = new HomePage (driver);
		HomeObj.OpenRequestsPage();
	}

	public void ClosePopupBanner() throws InterruptedException {
		HomeObj = new HomePage (driver);
		HomeObj.CloseBanner();
	}

	public int CheckCurrentWalletBalance() throws InterruptedException {
		HomeObj = new HomePage (driver);
		int x = HomeObj.SetBalance();
		return x;
	}

	//Open only Requests log page
	public void OpenRequestsLogPage() throws InterruptedException {
		HomeObj = new HomePage (driver);
		HomeObj.OpenRequestsLogPage();
	}

	//To create new request with a specific type
	public void OpenNewRequest (String RequestName) throws InterruptedException {
		ReqObj = new RequestsPage(driver);		
		ReqObj.OpenNewRequestPage(RequestName);
		Thread.sleep(1000);

	}

	public void SearchByRequestStatus(String Status) throws InterruptedException{
		ReqLogObj = new RequestsLogPage(driver);
		ReqLogObj.SearchByStatus(Status);
	}

	//search by request Under Procedure 
	public void SearchForNewRequest() throws InterruptedException {
		ReqLogObj = new RequestsLogPage(driver);
		ReqLogObj.GetRequestInfo("تحت الاجراء");
		RequestID = ReqLogObj.ID;
		ReqLogObj.MemberCheckRequestStatus(RequestID);
		Thread.sleep(1000);
		Assert.assertTrue(ReqLogObj.Status.contains("تحت الاجراء"));		
	}
	public void SearchForApprovedRequest() throws InterruptedException {
		ReqLogObj = new RequestsLogPage (driver);
		ReqLogObj.MemberCheckRequestStatus(RequestID);
		Thread.sleep(1000);
		ReqLogObj.GetRequestInfo("موافقة");
		Thread.sleep(1000);
		System.out.println("RequestNo in Member Portal - Requests log page is: " + RequestID);
		System.out.println("RequestStatus in Member Portal - Requests log page is: " + ReqLogObj.Status);
		Assert.assertTrue(ReqLogObj.Status.contains("موافقة"));
	}
	
	public void SearchForRejectedRequest() throws InterruptedException {

		ReqLogObj = new RequestsLogPage (driver);
		ReqLogObj.MemberCheckRequestStatus(RequestID);
		Thread.sleep(1000);
		ReqLogObj.GetRequestInfo("مرفوض");
		System.out.println("Current Request Status after rejection: "+ ReqLogObj.Status);
		Assert.assertTrue(ReqLogObj.Status.contains("مرفوض"));
		Thread.sleep(1000);
	}
	
	public void SearchForNewRequest_ApprovedAutomatically() throws InterruptedException {
		ReqLogObj = new RequestsLogPage(driver);
		ReqLogObj.GetRequestInfo("موافقة");
		RequestID = ReqLogObj.ID;
		Thread.sleep(1000);
		ReqLogObj.MemberCheckRequestStatus(RequestID);
		Assert.assertTrue(ReqLogObj.Status.contains("موافقة"));
		Thread.sleep(1000);
	}
	public void CancelRequest() throws InterruptedException {
		ReqLogObj.CancelRequest(loginReader.CancelReason);
		ReqLogObj = new RequestsLogPage (driver);
		ReqLogObj.GetRequestInfo("ملغى");
		Thread.sleep(1000);
		Assert.assertTrue(ReqLogObj.Status.contains("ملغى"));
		Thread.sleep(1000);
		ReqLogObj.ViewCancelReason(loginReader.CancelReason);
	}
	
	public void StopRequest() throws InterruptedException {
		ReqLogObj.StopRequest(loginReader.StopReason);
		StopReason = loginReader.StopReason;
		Thread.sleep(1000);
		ReqLogObj = new RequestsLogPage (driver);
		ReqLogObj.GetRequestInfo("موقوف");
		Thread.sleep(1000);
		Assert.assertTrue(ReqLogObj.Status.contains("موقوف"));
		Thread.sleep(1000);
		ReqLogObj.ViewStopReason(StopReason);
	}
	public void SendRequestToMail() throws InterruptedException{
		ReqLogObj.SendRequestToMail(loginReader.ValidMail, loginReader.InValidMail);
	}

	public void OpenSpecificRequestSettingsPage (String RequestName) throws InterruptedException {
		AdminObj = new AdminPage(driver);
		AdminObj.OpenSubscribersRequestsSettingsPage();
		ReqSettObj = new AdminRequestSettingsPage(driver);
		ReqSettObj.OpenRequestSettings(RequestName);
	}

}
