package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.MunicipalityServicesRequestPage;
import data.MunicipalityServices_JSONDataReader;


public class MunicipalityServicesRequestTest extends TestBase{
	MunicipalityServicesRequestPage MunicipalityObj;
	MunicipalityServices_JSONDataReader MunicipalityDataObj;
	private void OpenMunicipalityServicesRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		MunicipalityDataObj = new MunicipalityServices_JSONDataReader();
		MunicipalityDataObj.JsonReader_MunicipalityServicesRequest();
		OpenNewRequest(MunicipalityDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		MunicipalityObj= new MunicipalityServicesRequestPage(driver);			
		MunicipalityObj.CreateNewMunicipalityServicesRequest(MunicipalityDataObj.AuthName, MunicipalityDataObj.IDNumber, MunicipalityDataObj.RegionName,  MunicipalityDataObj.OTP, MunicipalityDataObj.InvalidOTP );		
	}
	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		MunicipalityDataObj = new MunicipalityServices_JSONDataReader();
		MunicipalityDataObj.JsonReader_MunicipalityServicesRequest();
		RequestCost = GetRequestCost(MunicipalityDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}

	public void CreateNewMunicipalityServicesRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenMunicipalityServicesRequest();
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
	public void CancelNewMunicipalityServicesRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewMunicipalityServicesRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);

	}

	@Test(priority = 3, enabled = true)
	void StopApprovedMunicipalityServicesRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewMunicipalityServicesRequest();
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
	void RejectApprovedMunicipalityServicesRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewMunicipalityServicesRequest();
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
	public void RejectNewMunicipalityServicesRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewMunicipalityServicesRequest();
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
	public void CreateEmptyMunicipalityServicesRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenMunicipalityServicesRequest();
			MunicipalityObj= new MunicipalityServicesRequestPage(driver);			
			MunicipalityObj.CreateEmptyMunicipalityServicesRequest(MunicipalityDataObj.AuthName, MunicipalityDataObj.IDNumber, MunicipalityDataObj.RegionName);
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
