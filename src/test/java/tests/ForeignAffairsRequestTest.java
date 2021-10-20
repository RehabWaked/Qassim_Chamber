package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import Pages.ForeignAffairsRequestPage;
import data.ForeignAffairs_JsonDataReader;


public class ForeignAffairsRequestTest extends TestBase{
	ForeignAffairsRequestPage ForeignAffairsObj;
	ForeignAffairs_JsonDataReader ForeignAffairsDataObj;

	public void OpenFARequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		ForeignAffairsDataObj = new ForeignAffairs_JsonDataReader();
		ForeignAffairsDataObj.JsonReader_ForeignAffairsRequest();
		OpenNewRequest(ForeignAffairsDataObj.RequestName);

	}
	public void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		ForeignAffairsObj= new ForeignAffairsRequestPage(driver);			
		ForeignAffairsObj.CreateNewForeignAffairsRequest(ForeignAffairsDataObj.RequestNumber, ForeignAffairsDataObj.Notes, ForeignAffairsDataObj.Reason,ForeignAffairsDataObj.OTP);		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		ForeignAffairsDataObj = new ForeignAffairs_JsonDataReader();
		ForeignAffairsDataObj.JsonReader_ForeignAffairsRequest();
		RequestCost = GetRequestCost(ForeignAffairsDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewForeignAffairsRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenFARequest();
			FillRequestData();
			CheckBalanceAfterSubmission_Cancellation_Rejection();
			SearchForNewRequest();
			ReqLogObj.ViewRequestAttachment();
			Thread.sleep(1000);
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}
	//-------------------------Test Cases-------------------------------\\

	@Test(priority = 1, enabled = true)
	public void Login() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		LoginToMemberPortal();		
	}
	@Test (priority = 2, enabled = true)
	public void CancelNewForeignAffairsRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewForeignAffairsRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);
		ReqLogObj.ViewCancelReason(loginReader.CancelReason);
	}

	

	@Test(priority = 3, enabled = true)
	void StopApprovedForeignAffairsRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewForeignAffairsRequest ();
		NavigateToAdminPortal ();		
		AdminApprovedNewRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();
		StopRequest();
		CheckBalanceAfterApproval_Stopping();
		MemberCheckOptionsForStoppedRequest();
		NavigateToAdminPortal();
		AdminCheckOptionsForStoppedRequest();	
		NavigateToMemberPortal();
	}


	@Test(priority = 4, enabled = true)
	void RejectApprovedForeignAffairsRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewForeignAffairsRequest ();
		NavigateToAdminPortal ();		
		AdminApprovedNewRequest();
		AdminCheckOptionsForApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();
		MemberCheckOptionsForApprovedRequest();
		NavigateToAdminPortal();	
		AdminRejectApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
	}


	@Test(priority = 5, enabled = true)
	public void RejectNewForeignAffairsRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewForeignAffairsRequest();
		NavigateToAdminPortal();
		AdminRejectNewRequest();
		AdminCheckOptionsForRejectedRequest();
		NavigateToMemberPortal ();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();

	}

	@Test (priority = 6, enabled = true)
	public void CreateEmptyForeignAffairsRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenFARequest();
			ForeignAffairsObj= new ForeignAffairsRequestPage(driver);			
			ForeignAffairsObj.CreateEmptyForeignAffairsRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}

	@Test (priority = 7, enabled = true)
	public void CreateForeignAffairsRequestWithNoAttachment() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenFARequest();
			ForeignAffairsObj= new ForeignAffairsRequestPage(driver);			
			ForeignAffairsObj.CreateRequestWithoutAttachment(ForeignAffairsDataObj.RequestNumber, ForeignAffairsDataObj.Notes, ForeignAffairsDataObj.Reason,ForeignAffairsDataObj.OTP);		;

		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}
}
