package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.ReturnVisaExtensionRequestPage;
import data.ReturnVisaExtension_JSONDataReader;

public class ReturnVisaExtensionRequestTest extends TestBase{
	ReturnVisaExtensionRequestPage ReturnVisaObj;
	ReturnVisaExtension_JSONDataReader ReturnVisaDataObj;
	private void OpenReturnVisaExtensionRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		ReturnVisaDataObj = new ReturnVisaExtension_JSONDataReader();
		ReturnVisaDataObj.JsonReader_ReturnVisaExtension();
		OpenNewRequest(ReturnVisaDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		ReturnVisaObj= new ReturnVisaExtensionRequestPage(driver);			
		ReturnVisaObj.CreateNewReturnVisaExtRequest(ReturnVisaDataObj.ConsulateName, ReturnVisaDataObj.GuaranteedName, ReturnVisaDataObj.EndYear, ReturnVisaDataObj.EndMonth, ReturnVisaDataObj.OTP, ReturnVisaDataObj.InvalidOTP );		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		ReturnVisaDataObj = new ReturnVisaExtension_JSONDataReader();
		ReturnVisaDataObj.JsonReader_ReturnVisaExtension();
		RequestCost = GetRequestCost(ReturnVisaDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewReturnVisaRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{

		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenReturnVisaExtensionRequest();
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
	public void CancelNewReturnVisaRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewReturnVisaRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);

	}

	@Test(priority = 3, enabled = true)
	void StopApprovedReturnVisaRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewReturnVisaRequest ();
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
	void RejectApprovedReturnVisaRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewReturnVisaRequest ();
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
	public void RejectNewReturnVisaRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewReturnVisaRequest();
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
	public void CreateEmptyReturnVisaRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenReturnVisaExtensionRequest();
			ReturnVisaObj= new ReturnVisaExtensionRequestPage(driver);			
			ReturnVisaObj.CreateEmptyReturnVisaExtRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
