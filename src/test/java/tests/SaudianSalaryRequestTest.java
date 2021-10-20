package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import Pages.SalaryDefinitionRequestPage;
import data.SalaryDefinition_JSONDataReader;


public class SaudianSalaryRequestTest extends TestBase{
	SalaryDefinitionRequestPage SalaryDefObj;
	SalaryDefinition_JSONDataReader SalaryDefDataObj;

	public void OpenSaudianSalaryRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		SalaryDefDataObj = new SalaryDefinition_JSONDataReader();
		SalaryDefDataObj.JsonReader_SalaryDefRequest();
		OpenNewRequest(SalaryDefDataObj.SaudianRequestName);

	}
	public void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		SalaryDefObj= new SalaryDefinitionRequestPage(driver);			
		SalaryDefObj.CreateNewSaudianSalaryRequestWithTotalSalary(SalaryDefDataObj.Destination, SalaryDefDataObj.EmployeeName, SalaryDefDataObj.SaudianIDNumber,SalaryDefDataObj.IDSource, SalaryDefDataObj.JobName, SalaryDefDataObj.TotalSalary, SalaryDefDataObj.OTP, SalaryDefDataObj.InvalidOTP);		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		SalaryDefDataObj = new SalaryDefinition_JSONDataReader();
		SalaryDefDataObj.JsonReader_SalaryDefRequest();
		RequestCost = GetRequestCost(SalaryDefDataObj.SaudianRequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewSaudianSalaryRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenSaudianSalaryRequest();
			FillRequestData();
			OpenRequestsLogPage();
			CheckBalanceAfterApproval_Stopping();
			SearchForNewRequest_ApprovedAutomatically();
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
	@Test(priority = 2, enabled = true)
	void StopApprovedSaudianSalaryRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewSaudianSalaryRequest ();
		CheckBalanceAfterApproval_Stopping();
		StopRequest();
		CheckBalanceAfterApproval_Stopping();
		MemberCheckOptionsForStoppedRequest();
		NavigateToAdminPortal();
		AdminCheckOptionsForStoppedRequest();		
		NavigateToMemberPortal();
	}


	@Test(priority = 3, enabled = true)
	void RejectApprovedSaudianSalaryRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewSaudianSalaryRequest ();
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
	
	@Test(priority = 4, enabled = true)
	public void CreateEmptySaudianSalaryRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenSaudianSalaryRequest();
			SalaryDefObj = new SalaryDefinitionRequestPage(driver);			
			SalaryDefObj.CreateEmptySaudianSalaryRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}

}
