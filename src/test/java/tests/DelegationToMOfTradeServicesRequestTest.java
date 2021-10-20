package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import Pages.DelegationToMOfTradeServicesRequestPage;
import data.MOTradeServices_JSONDataReader;



public class DelegationToMOfTradeServicesRequestTest extends TestBase{
	DelegationToMOfTradeServicesRequestPage MOTradeServicesObj;
	MOTradeServices_JSONDataReader MOTradeServicesDataObj;

	private void OpenMOfTradeServicesRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		MOTradeServicesDataObj = new MOTradeServices_JSONDataReader();
		MOTradeServicesDataObj.JsonReader_MOTradeServicesRequest();
		OpenNewRequest(MOTradeServicesDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		MOTradeServicesObj= new DelegationToMOfTradeServicesRequestPage(driver);			
		MOTradeServicesObj.CreateNewMOFTradeServicesRequest(MOTradeServicesDataObj.WorkRegion, MOTradeServicesDataObj.OfficeName, MOTradeServicesDataObj.OfficeServiceNumber, MOTradeServicesDataObj.SirName, MOTradeServicesDataObj.SirIDNumber, MOTradeServicesDataObj.OTP, MOTradeServicesDataObj.InvalidOTP);		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		MOTradeServicesDataObj = new MOTradeServices_JSONDataReader();
		MOTradeServicesDataObj.JsonReader_MOTradeServicesRequest();
		RequestCost = GetRequestCost(MOTradeServicesDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewMOfTradeServicesRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{

		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenMOfTradeServicesRequest();
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
	public void CancelNewMOfTradeRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewMOfTradeServicesRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);
		ReqLogObj.ViewCancelReason(loginReader.CancelReason);
	}


	@Test(priority = 3, enabled = true)
	void StopApprovedMOfTradeRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewMOfTradeServicesRequest ();
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
	void RejectApprovedMOfTradeServicesRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		
		CreateNewMOfTradeServicesRequest ();
		NavigateToAdminPortal ();		
		AdminApprovedNewRequest();
		AdminCheckOptionsForApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();
		MemberCheckOptionsForApprovedRequest();
		NavigateToAdminPortal ();
		AdminRejectApprovedRequest();
		AdminCheckOptionsForRejectedAfterApprovalRequest();
		NavigateToMemberPortal ();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();
	}


	@Test(priority = 5, enabled = true)
	public void RejectNewMOfTradeServicesRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewMOfTradeServicesRequest();
		NavigateToAdminPortal();
		AdminRejectNewRequest();
		AdminCheckOptionsForRejectedRequest();
		NavigateToMemberPortal ();
		OpenRequestsLogPage();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();


	}

	@Test (priority = 6, enabled = true)
	public void CreateEmptyMOfTradeServicesRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenMOfTradeServicesRequest();
			MOTradeServicesObj= new DelegationToMOfTradeServicesRequestPage(driver);			
			MOTradeServicesObj.CreateEmptyMOFTradeServicesRequest(MOTradeServicesDataObj.WorkRegion, MOTradeServicesDataObj.OfficeName, MOTradeServicesDataObj.OfficeServiceNumber);
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
