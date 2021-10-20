package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import Pages.DelegationToTrafficServicesRequestPage;
import data.TrafficServices_JSONDataReader;


public class DelegationToTrafficServicesRequestTest extends TestBase{
	DelegationToTrafficServicesRequestPage TrafficServicesObj;
	TrafficServices_JSONDataReader TrafficServicesDataObj;

	private void OpenTrafficServicesRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		TrafficServicesDataObj = new TrafficServices_JSONDataReader();
		TrafficServicesDataObj.JsonReader_TrafficServicesRequest();
		OpenNewRequest( TrafficServicesDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		TrafficServicesObj= new DelegationToTrafficServicesRequestPage(driver);			
		TrafficServicesObj.CreateNewTraffiCServicesRequest(TrafficServicesDataObj.WorkRegion, TrafficServicesDataObj.OfficeName, TrafficServicesDataObj.OfficeServiceNumber, TrafficServicesDataObj.SirName, TrafficServicesDataObj.SirIDNumber, TrafficServicesDataObj.Nationality, TrafficServicesDataObj.OTP, TrafficServicesDataObj.InvalidOTP);		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		TrafficServicesDataObj = new TrafficServices_JSONDataReader();
		TrafficServicesDataObj.JsonReader_TrafficServicesRequest();
		RequestCost = GetRequestCost(TrafficServicesDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}


	public void CreateNewTrafficServicesRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{

		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenTrafficServicesRequest();
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
	public void CancelNewTrafficServicesRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewTrafficServicesRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);
		ReqLogObj.ViewCancelReason(loginReader.CancelReason);
	}

	@Test(priority = 3, enabled = true)
	void StopApprovedTrafficServicesRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewTrafficServicesRequest ();
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
	void RejectApprovedTrafficServicesRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewTrafficServicesRequest ();
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
		NavigateToMemberPortal ();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
	}

	@Test(priority = 5, enabled = true)
	public void RejectNewTrafficServicesRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewTrafficServicesRequest();
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
	public void CreateEmptyTrafficServicesRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenTrafficServicesRequest();
			TrafficServicesObj= new DelegationToTrafficServicesRequestPage(driver);			
			TrafficServicesObj.CreateEmptyTrafficServicesRequest(TrafficServicesDataObj.WorkRegion, TrafficServicesDataObj.OfficeName, TrafficServicesDataObj.OfficeServiceNumber);
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
