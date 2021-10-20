package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import Pages.ExperienceCertificateRequestPage;
import data.ExperienceCertificate_JSONDataReader;

public class ExperienceCertificateRequestTest extends TestBase{
	ExperienceCertificateRequestPage ExpCertObj;
	ExperienceCertificate_JSONDataReader ExpCertDataObj;

	private void OpenExperienceCertificateRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		ExpCertDataObj = new ExperienceCertificate_JSONDataReader();
		ExpCertDataObj.JsonReader_ExperienceCertificate();
		OpenNewRequest(ExpCertDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		ExpCertObj= new ExperienceCertificateRequestPage(driver);			
		ExpCertObj.CreateNewExperienceCertificateRequest(ExpCertDataObj.JobName, ExpCertDataObj.SirName, ExpCertDataObj.SirIDNumber, ExpCertDataObj.StartYear, ExpCertDataObj.StartMonth, ExpCertDataObj.OTP, ExpCertDataObj.InvalidOTP );		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		ExpCertDataObj = new ExperienceCertificate_JSONDataReader();
		ExpCertDataObj.JsonReader_ExperienceCertificate();
		RequestCost = GetRequestCost(ExpCertDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewExperienceCertificateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenExperienceCertificateRequest();
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
	public void CancelNewExperienceCertificateRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewExperienceCertificateRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);

	}

	@Test(priority = 3, enabled = true)
	void StopApprovedExperienceCertificateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewExperienceCertificateRequest ();
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
	void RejectApprovedExperienceCertificateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewExperienceCertificateRequest ();
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
		NavigateToMemberPortal();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();


	}


	@Test(priority = 5, enabled = true)
	public void RejectNewExperienceCertificateRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewExperienceCertificateRequest();
		NavigateToAdminPortal();
		AdminRejectNewRequest();
		AdminCheckOptionsForRejectedRequest();
		NavigateToMemberPortal ();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();

	}

	@Test (priority = 6, enabled = true)
	public void CreateEmptyExperienceCertificateRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenExperienceCertificateRequest();
			ExpCertObj= new ExperienceCertificateRequestPage(driver);			
			ExpCertObj.CreateEmptyExperienceCertificateRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}