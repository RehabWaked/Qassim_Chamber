package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.AdmissionLetterRequestPage;
import data.AdmissionLetter_JSONDataReader;


public class AdmissionLetterRequestTest extends TestBase{
	AdmissionLetterRequestPage AdmissionLetterObj;
	AdmissionLetter_JSONDataReader AdmissionLetterDataObj;
	private void OpenAdmissionLetterRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		AdmissionLetterDataObj = new AdmissionLetter_JSONDataReader();
		AdmissionLetterDataObj.JsonReader_AdmissionLetterRequest();
		OpenNewRequest(AdmissionLetterDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		AdmissionLetterObj= new AdmissionLetterRequestPage(driver);			
		AdmissionLetterObj.CreateNewAdmissionLetterRequest( AdmissionLetterDataObj.SirName, AdmissionLetterDataObj.SirIDNumber, AdmissionLetterDataObj.Nationality, AdmissionLetterDataObj.JobName, AdmissionLetterDataObj.Salary, AdmissionLetterDataObj.OTP, AdmissionLetterDataObj.InvalidOTP );		
	}
	
	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		AdmissionLetterDataObj = new AdmissionLetter_JSONDataReader();
		AdmissionLetterDataObj.JsonReader_AdmissionLetterRequest();
		RequestCost = GetRequestCost(AdmissionLetterDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	
	public void CreateNewAdmissionLetterRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenAdmissionLetterRequest();
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
	public void CancelNewAdmissionLetterRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewAdmissionLetterRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);
	}

	@Test(priority = 3, enabled = true)
	void StopApprovedAdmissionLetterRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewAdmissionLetterRequest ();
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
	void RejectApprovedAdmissionLetterRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewAdmissionLetterRequest ();
		NavigateToAdminPortal ();		
		AdminApprovedNewRequest();
		AdminCheckOptionsForApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();
		MemberCheckOptionsForApprovedRequest();
		NavigateToAdminPortal();	
		AdminObj.OpenSubscribersReceivedRequestsPage();
		AdminRejectApprovedRequest();
		AdminCheckOptionsForRejectedAfterApprovalRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();
	}


	@Test(priority = 5, enabled = true)
	public void RejectNewAdmissionLetterRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewAdmissionLetterRequest();
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
	public void CreateEmptyAdmissionLetterRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenAdmissionLetterRequest();
			AdmissionLetterObj= new AdmissionLetterRequestPage(driver);			
			AdmissionLetterObj.CreateEmptyAdmissionLetterRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
