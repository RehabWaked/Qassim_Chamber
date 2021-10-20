package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.CustomsCommitmentRequestPage;
import data.CustomsCommitment_JSONDataReader;


public class CustomsCommitmentRequestTest extends TestBase{
	CustomsCommitmentRequestPage CustomsCommObj;
	CustomsCommitment_JSONDataReader CustomsCommDataObj;
	
	private void OpenCustomsCommitmentRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CustomsCommDataObj = new CustomsCommitment_JSONDataReader();
		CustomsCommDataObj.JsonReader_CustomsCommitmentRequest();
		OpenNewRequest(CustomsCommDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CustomsCommObj= new CustomsCommitmentRequestPage(driver);			
		CustomsCommObj.CreateNewCustomsCommitmentRequest( CustomsCommDataObj.POBox, CustomsCommDataObj.PhoneNo, CustomsCommDataObj.FaxNo, CustomsCommDataObj.IBANNo, CustomsCommDataObj.ImportNo, CustomsCommDataObj.CustomsName, CustomsCommDataObj.OTP, CustomsCommDataObj.InvalidOTP );		
	}
	
	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		CustomsCommDataObj = new CustomsCommitment_JSONDataReader();
		CustomsCommDataObj.JsonReader_CustomsCommitmentRequest();
		RequestCost = GetRequestCost(CustomsCommDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewCustomsCommitmentRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenCustomsCommitmentRequest();
			FillRequestData();
			OpenRequestsLogPage();
			CheckBalanceAfterApproval_Stopping();
			SearchForNewRequest_ApprovedAutomatically();

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
	

	@Test(priority = 2, enabled = true)
	void StopApprovedCustomsCommitmentRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewCustomsCommitmentRequest ();
		StopRequest();
		CheckBalanceAfterApproval_Stopping();
		MemberCheckOptionsForStoppedRequest();
		NavigateToAdminPortal();
		AdminCheckOptionsForStoppedRequest();	
		NavigateToMemberPortal();
	}


	@Test(priority = 3, enabled = true)
	void RejectApprovedCustomsCommitmentRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewCustomsCommitmentRequest ();
		MemberCheckOptionsForApprovedRequest();
		NavigateToAdminPortal();
		AdminObj.OpenSubscribersReceivedRequestsPage();
		AdminCheckOptionsForApprovedRequest();
		AdminObj.AdminRejectApprovedRequestCreated(RequestID, RejectReason);
		AdminCheckOptionsForRejectedAfterApprovalRequest();
		NavigateToMemberPortal ();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();
	}


	@Test (priority = 4, enabled = true)
	public void CreateEmptyCustomsCommitmentRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenCustomsCommitmentRequest();
			CustomsCommObj= new CustomsCommitmentRequestPage(driver);			
			CustomsCommObj.CreateEmptyCustomsCommitmentRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
