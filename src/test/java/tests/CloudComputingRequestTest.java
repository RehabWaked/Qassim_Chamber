package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.CloudComputingRequestPage;
import data.CloudComputing_JSONDataReader;


public class CloudComputingRequestTest extends TestBase{
	CloudComputingRequestPage CloudCompObj;
	CloudComputing_JSONDataReader CloudCompDataObj;

	private void OpenCloudComputingRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CloudCompDataObj = new CloudComputing_JSONDataReader();
		CloudCompDataObj.JsonReader_CloudComputingRequest();
		OpenNewRequest(CloudCompDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CloudCompObj= new CloudComputingRequestPage(driver);			
		CloudCompObj.CreateNewCloudComputingRequest( CloudCompDataObj.SirName, CloudCompDataObj.IDNumber, CloudCompDataObj.JobTitle, CloudCompDataObj.IDIssuePlace, CloudCompDataObj.MobileNo, CloudCompDataObj.Email, CloudCompDataObj.POBox, CloudCompDataObj.PostalCode, CloudCompDataObj.City, CloudCompDataObj.Street, CloudCompDataObj.District, CloudCompDataObj.BuildingNo, CloudCompDataObj.AppartNo, CloudCompDataObj.OTP, CloudCompDataObj.InvalidOTP );		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		CloudCompDataObj = new CloudComputing_JSONDataReader();
		CloudCompDataObj.JsonReader_CloudComputingRequest();
		RequestCost = GetRequestCost(CloudCompDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}

	public void CreateNewCloudComputingRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenCloudComputingRequest();
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
	public void CancelNewCloudComputingRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewCloudComputingRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);

	}

	@Test(priority = 3, enabled = true)
	void StopApprovedCloudComputingRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewCloudComputingRequest ();
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
	void RejectApprovedCloudComputingRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewCloudComputingRequest ();
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
		AdminCheckOptionsForRejectedAfterApprovalRequest();
		NavigateToMemberPortal ();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();
	}


	@Test(priority = 5, enabled = true)
	public void RejectNewCloudComputingCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewCloudComputingRequest();
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
	public void CreateEmptyCloudComputingRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenCloudComputingRequest();
			CloudCompObj= new CloudComputingRequestPage(driver);			
			CloudCompObj.CreateEmptyCloudComputingRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
