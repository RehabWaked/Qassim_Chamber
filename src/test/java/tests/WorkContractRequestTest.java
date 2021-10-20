package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.WorkContractRequestPage;
import data.WorkContract_JSONDataReader;


public class WorkContractRequestTest extends TestBase{
	WorkContractRequestPage WorkContractObj;
	WorkContract_JSONDataReader WorkContractDataObj;
	private void OpenWorkContractRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		WorkContractDataObj = new WorkContract_JSONDataReader();
		WorkContractDataObj.JsonReader_WorkContractRequest();
		OpenNewRequest(WorkContractDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		WorkContractObj= new WorkContractRequestPage(driver);			
		WorkContractObj.CreateNewWorkContractRequest(WorkContractDataObj.ContractDuration, WorkContractDataObj.SirName, WorkContractDataObj.SirIDNumber, WorkContractDataObj.EmployeeName, WorkContractDataObj.PassportNumber, WorkContractDataObj.Nationality, WorkContractDataObj.JobName, WorkContractDataObj.Salary, WorkContractDataObj.VacationDays, WorkContractDataObj.OTP, WorkContractDataObj.InvalidOTP );		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		WorkContractDataObj = new WorkContract_JSONDataReader();
		WorkContractDataObj.JsonReader_WorkContractRequest();
		RequestCost = GetRequestCost(WorkContractDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewWorkContractRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{

		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenWorkContractRequest();
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
	public void CancelNewWorkContractRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewWorkContractRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);

	}

	@Test(priority = 3, enabled = true)
	void StopApprovedWorkContractRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewWorkContractRequest ();
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
	void RejectApprovedWorkContractRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewWorkContractRequest ();
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
	public void RejectNewWorkContractRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewWorkContractRequest();
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
	public void CreateEmptyWorkContractRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenWorkContractRequest();
			WorkContractObj= new WorkContractRequestPage(driver);			
			WorkContractObj.CreateEmptyWorkContractRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
