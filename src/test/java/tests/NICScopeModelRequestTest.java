package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.NICScopeModelRequestPage;
import data.NICScopeModel_JSONDataReader;


public class NICScopeModelRequestTest extends TestBase{
	NICScopeModelRequestPage NICModelObj;
	NICScopeModel_JSONDataReader NICModelDataObj;
	private void OpenNICScopeModelRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		NICModelDataObj = new NICScopeModel_JSONDataReader();
		NICModelDataObj.JsonReader_NICScopeModelRequest();
		OpenNewRequest(NICModelDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		NICModelObj= new NICScopeModelRequestPage(driver);			
		NICModelObj.CreateNewNICScopeModelRequest(NICModelDataObj.BandName, NICModelDataObj.AdminName, NICModelDataObj.AdminMobile, NICModelDataObj.AdminMail, NICModelDataObj.OTP, NICModelDataObj.InvalidOTP );		
	}
	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		NICModelDataObj = new NICScopeModel_JSONDataReader();
		NICModelDataObj.JsonReader_NICScopeModelRequest();
		RequestCost = GetRequestCost(NICModelDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewNICModelRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenNICScopeModelRequest();
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
	public void CancelNewNICModelRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewNICModelRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);

	}

	@Test(priority = 3, enabled = true)
	void StopApprovedNICModelRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewNICModelRequest();
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
	void RejectApprovedNICModelRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewNICModelRequest();
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
	public void RejectNewNICModelRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewNICModelRequest();
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
	public void CreateEmptyNICModelRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenNICScopeModelRequest();
			NICModelObj= new NICScopeModelRequestPage(driver);			
			NICModelObj.CreateEmptyNICScopeModelRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
