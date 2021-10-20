package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.CivilDefenseServicesRequestPage;
import data.CivilDefense_JSONDataReader;


public class CivilDefenseServicesRequestTest extends TestBase{
	CivilDefenseServicesRequestPage CivilDefObj;
	CivilDefense_JSONDataReader CivilDefDataObj;
	private void OpenCivilDefenseRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CivilDefDataObj = new CivilDefense_JSONDataReader();
		CivilDefDataObj.JsonReader_CivilDefenseRequest();
		OpenNewRequest(CivilDefDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CivilDefObj= new CivilDefenseServicesRequestPage(driver);			
		CivilDefObj.CreateNewCivilDefenseServicesRequest( CivilDefDataObj.AuthName, CivilDefDataObj.IDNumber, CivilDefDataObj.LicenseName, CivilDefDataObj.RegionName,  CivilDefDataObj.OTP, CivilDefDataObj.InvalidOTP );		
	}
		private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		CivilDefDataObj = new CivilDefense_JSONDataReader();
		CivilDefDataObj.JsonReader_CivilDefenseRequest();
		RequestCost = GetRequestCost(CivilDefDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewCivilDefenseRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenCivilDefenseRequest();
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
	//----------------------------------Test Cases-----------------------------------------------\\

	@Test(priority = 1, enabled = true)
	public void Login() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		LoginToMemberPortal();		
	}
	
	
	@Test (priority = 2, enabled = true)
	public void CancelNewCivilDefenseRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewCivilDefenseRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);
	}


	@Test(priority = 3, enabled = true)
	void StopApprovedCivilDefenseRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewCivilDefenseRequest();
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
	void RejectApprovedCivilDefenseRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewCivilDefenseRequest();
		NavigateToAdminPortal ();		
		AdminApprovedNewRequest();
		AdminCheckOptionsForApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();
		MemberCheckOptionsForApprovedRequest();
		NavigateToAdminPortal ();	
		AdminObj.OpenSubscribersReceivedRequestsPage();
		AdminRejectApprovedRequest();
		AdminCheckOptionsForRejectedAfterApprovalRequest();
		NavigateToMemberPortal ();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();
	}

	@Test(priority = 5, enabled = true)
	public void RejectNewCivilDefenseRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewCivilDefenseRequest();
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
	public void CreateEmptyCivilDefenseRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenCivilDefenseRequest();
			CivilDefObj= new CivilDefenseServicesRequestPage(driver);			
			CivilDefObj.CreateEmptyCivilDefenseServicesRequest(CivilDefDataObj.AuthName, CivilDefDataObj.IDNumber, CivilDefDataObj.LicenseName, CivilDefDataObj.RegionName);
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
