package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import Pages.ReExportrequestPage;
import Pages.RequestsLogPage;
import data.ReExportRequest_JSONDataReader;

public class ReExportrequestTest extends TestBase{

	
	RequestsLogPage ReqLogObj;
	boolean skipTest;	
	ReExportrequestPage ReExportOBJ;
	ReExportRequest_JSONDataReader ReExportReader;


	public void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		ReExportReader = new ReExportRequest_JSONDataReader();
		ReExportReader.jsonReader_ReExportRequest();
		RequestCost = GetRequestCost(ReExportReader.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}

	public void FillDataAndCreateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		ReExportOBJ= new ReExportrequestPage(driver);
		ReExportReader= new ReExportRequest_JSONDataReader();
		ReExportReader.jsonReader_ReExportRequest();
		OpenNewRequest(  ReExportReader.RequestName);
		ReExportOBJ.CreateNewRequest_ReExportRequest(ReExportReader.ExpoeterPhone, ReExportReader.ExporterZipCode, ReExportReader.ExporterPBox , ReExportReader.ExporterFax, ReExportReader.ImporterName, ReExportReader.ImporterPBox, ReExportReader.ImporterCity, ReExportReader.ImporterFax, ReExportReader.GoodsType, ReExportReader.GoodsOrigin, ReExportReader.GoodsInformation, ReExportReader.GoodsQuantity, ReExportReader.CertificateOriginNumber, ReExportReader.GoodsValue, ReExportReader.GoodsValueDollar, ReExportReader.BillNumber, ReExportReader.OTP);
	}
	
	@Test(alwaysRun = true, enabled = true)
	void loginTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		LoginToMemberPortal();		
	}
	
	@Test(priority = 1, enabled = true)
	void OpenTheRelatedCard_CheckCreatedNewRrequest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{	
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			FillDataAndCreateRequest();
			CheckBalanceAfterSubmission_Cancellation_Rejection();
			SearchForNewRequest(); 
			ReqLogObj = new RequestsLogPage(driver); 
			ReqLogObj.ViewRequestAttachment();
			skipTest = false;
		}
		else {
			Thread.sleep(1000);
			System.out.println("You Don't have available Balance to Submit New Requests");
			Thread.sleep(1000);
			skipTest = true;
		}
	}


	@Test(priority = 2, enabled = true, dependsOnMethods = {"OpenTheRelatedCard_CheckCreatedNewRrequest"})
	void OpenTheRelatedCard_ApprovedNewRrequest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{	
		NavigateToAdminPortal();		
		AdminApprovedNewRequest();
		AdminCheckOptionsForApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();	
		MemberCheckOptionsForApprovedRequest();
	}


	@Test(priority = 3, enabled = true, dependsOnMethods = {"OpenTheRelatedCard_ApprovedNewRrequest"})
	void AdminRejectApprovedRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		if (!skipTest) {
			NavigateToAdminPortal();	
			AdminRejectApprovedRequest();
			NavigateToMemberPortal();
			CheckBalanceAfterSubmission_Cancellation_Rejection();
			OpenRequestsLogPage();
			SearchForRejectedRequest();
			MemberCheckOptionsForRejectedRequest();
		}
		else {
			try 
			{
				skipTest = true;
				throw new SkipException("Skipping this exception");
			}
			catch (Exception e) 
			{
				System.out.println("Test case skipped due to no request created ");		
			}			
		}
	}


	@Test(priority = 4 ,enabled = true)
	void AdminRejectRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		OpenTheRelatedCard_CheckCreatedNewRrequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		NavigateToAdminPortal();	
		AdminRejectNewRequest();
		AdminCheckOptionsForRejectedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();
		}


	@Test(priority = 5 ,enabled = true)
	void CancelRequestCreatedTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		OpenTheRelatedCard_CheckCreatedNewRrequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReExportOBJ.UserCanceledNewRequestCreated(ReExportReader.CancelText);
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		ReqLogObj.ViewCancelReason(ReExportReader.CancelText);
	}


	@Test(priority = 6 ,enabled = true)
	void StopRequestCreatedTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		OpenTheRelatedCard_CheckCreatedNewRrequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		NavigateToAdminPortal();		
		AdminApprovedNewRequest();
		AdminCheckOptionsForApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();	
		StopRequest();
		CheckBalanceAfterApproval_Stopping();
		MemberCheckOptionsForStoppedRequest();
		NavigateToAdminPortal();
		AdminCheckOptionsForStoppedRequest();	
	}


	@Test(priority = 7 ,enabled = true)
	void sendWithoutData() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		NavigateToMemberPortal();
		CheckUserAvailableBalance();
		ReExportOBJ= new ReExportrequestPage(driver);
		ReExportReader= new ReExportRequest_JSONDataReader();
		ReExportReader.jsonReader_ReExportRequest();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			ReExportOBJ.SendEmptyData_ReExportRequest();
			System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}


	@Test(priority = 8 ,enabled = true)
	void TestBillDateValidation() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		NavigateToMemberPortal();
		CheckUserAvailableBalance();
		ReExportOBJ= new ReExportrequestPage(driver);
		ReExportReader= new ReExportRequest_JSONDataReader();
		ReExportReader.jsonReader_ReExportRequest();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			ReExportOBJ.BillDateValidation_ReExportRequest(ReExportReader.ExpoeterPhone, ReExportReader.ExporterZipCode, ReExportReader.ExporterPBox , ReExportReader.ExporterFax, ReExportReader.ImporterName, ReExportReader.ImporterPBox, ReExportReader.ImporterCity, ReExportReader.ImporterFax, ReExportReader.GoodsType, ReExportReader.GoodsOrigin, ReExportReader.GoodsInformation, ReExportReader.GoodsQuantity, ReExportReader.CertificateOriginNumber, ReExportReader.GoodsValue, ReExportReader.GoodsValueDollar, ReExportReader.BillNumber);
			Assert.assertTrue(LandingObj.MessageTextDisplaying.getText().toString().contains("تاريخ الفاتورة أكبر من تاريخ اليوم الحالى ."));
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}


	@Test(priority = 9 ,enabled = true)
	void TestCertificateDateValidation() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		NavigateToMemberPortal();
		CheckUserAvailableBalance();
		ReExportOBJ= new ReExportrequestPage(driver);
		ReExportReader= new ReExportRequest_JSONDataReader();
		ReExportReader.jsonReader_ReExportRequest();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			ReExportOBJ.CertificateDateValidation_ReExportRequest(ReExportReader.ExpoeterPhone, ReExportReader.ExporterZipCode, ReExportReader.ExporterPBox , ReExportReader.ExporterFax, ReExportReader.ImporterName, ReExportReader.ImporterPBox, ReExportReader.ImporterCity, ReExportReader.ImporterFax, ReExportReader.GoodsType, ReExportReader.GoodsOrigin, ReExportReader.GoodsInformation, ReExportReader.GoodsQuantity, ReExportReader.CertificateOriginNumber, ReExportReader.GoodsValue, ReExportReader.GoodsValueDollar, ReExportReader.BillNumber);
			Assert.assertTrue(LandingObj.MessageTextDisplaying.getText().toString().contains("تاريخ شهادة المنشأ أكبر من تاريخ اليوم الحالى ."));
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}


	@Test(priority = 10 ,enabled = true)
	void TestAttachmentsFieldValidation() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		CheckUserAvailableBalance();
		ReExportOBJ= new ReExportrequestPage(driver);
		ReExportReader= new ReExportRequest_JSONDataReader();
		ReExportReader.jsonReader_ReExportRequest();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			ReExportOBJ.AttachmentsField_ReExportRequest(ReExportReader.ExpoeterPhone, ReExportReader.ExporterZipCode, ReExportReader.ExporterPBox , ReExportReader.ExporterFax, ReExportReader.ImporterName, ReExportReader.ImporterPBox, ReExportReader.ImporterCity, ReExportReader.ImporterFax, ReExportReader.GoodsType, ReExportReader.GoodsOrigin, ReExportReader.GoodsInformation, ReExportReader.GoodsQuantity, ReExportReader.CertificateOriginNumber, ReExportReader.GoodsValue, ReExportReader.GoodsValueDollar, ReExportReader.BillNumber, ReExportReader.OTP);
			Assert.assertFalse(LandingObj.MessageTextDisplaying.getText().contains("فضلا يجب اضافة علي الاقل  2 مرفق"));
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}
}

