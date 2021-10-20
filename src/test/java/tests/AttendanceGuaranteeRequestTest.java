package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.Test;
import Pages.AttendanceGuaranteeRequestPage;
import Pages.RequestsLogPage;
import data.AttendanceGuarantee_JSONDataReader;

public class AttendanceGuaranteeRequestTest extends TestBase{
	
	RequestsLogPage ReqLogObj;
	boolean skipTest;
	AttendanceGuaranteeRequestPage AttendGuaranteeOBJ;
	AttendanceGuarantee_JSONDataReader AttendanceReader;
	
	
	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		AttendanceReader = new AttendanceGuarantee_JSONDataReader();
		AttendanceReader.jsonReader_FillData();
		RequestCost = GetRequestCost(AttendanceReader.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	
	public void FillDataAndCreateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		AttendGuaranteeOBJ= new AttendanceGuaranteeRequestPage(driver);
		AttendanceReader = new AttendanceGuarantee_JSONDataReader();
		AttendanceReader.jsonReader_FillData();
		OpenNewRequest(AttendanceReader.RequestName);
		AttendGuaranteeOBJ.createNewRequest_AttendanceGuaranteeRequest(AttendanceReader.WorkRegion, AttendanceReader.MrName, AttendanceReader.Nationality, AttendanceReader.JobName, AttendanceReader.SirIDNumber);
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
		ReqLogObj.ViewRequestAttachment();
		ReqLogObj.ViewCancelReason(AttendanceReader.CancelText);
		MemberCheckOptionsForRejectedRequest();
	}
	

	@Test(priority = 5 ,enabled = true)
	void CancelRequestCreatedTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		OpenTheRelatedCard_CheckCreatedNewRrequest();
		AttendGuaranteeOBJ.UserCanceledNewRequestCreated(AttendanceReader.CancelText);
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		ReqLogObj.ViewCancelReason(AttendanceReader.CancelText);
	}


	@Test(priority = 6 ,enabled = true)
	void StopRequestCreatedTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		OpenTheRelatedCard_CheckCreatedNewRrequest();
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
	void ServicesCheckBoxesTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		NavigateToMemberPortal();
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			AttendGuaranteeOBJ= new AttendanceGuaranteeRequestPage(driver);
			OpenNewRequest(AttendanceReader.RequestName);
			AttendGuaranteeOBJ.createNewRequest_ServiceCheckBoxesChecker(AttendanceReader.WorkRegion, AttendanceReader.MrName, AttendanceReader.Nationality, AttendanceReader.JobName, AttendanceReader.SirIDNumber);
			skipTest = false;
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}
	
	@Test(priority = 8 ,enabled = true)
	void CreateRequestWithoutData() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			AttendGuaranteeOBJ= new AttendanceGuaranteeRequestPage(driver);
			OpenNewRequest(AttendanceReader.RequestName);
			AttendGuaranteeOBJ.createNewRequest_AttenanceRequestWithoutData();
			skipTest = false;
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}
	

}
