package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.HonestyUnViolationPromiseRequestPage;
import data.HonestyUnViolation_JSONDataReader;


public class HonestyUnViolationPromiseRequestTest extends TestBase{
	HonestyUnViolationPromiseRequestPage HonestyUnViolationObj;
	HonestyUnViolation_JSONDataReader HonestyUnViolationDataObj;

	private void OpenHonestyUnViolationPromiseRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		HonestyUnViolationDataObj = new HonestyUnViolation_JSONDataReader();
		HonestyUnViolationDataObj.JsonReader_HonestyUnViolationRequest();
		OpenNewRequest(HonestyUnViolationDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		HonestyUnViolationObj= new HonestyUnViolationPromiseRequestPage(driver);			
		HonestyUnViolationObj.CreateNewHonestyUnViolationRequest(HonestyUnViolationDataObj.AuthName, HonestyUnViolationDataObj.IDNumber, HonestyUnViolationDataObj.RegionName,  HonestyUnViolationDataObj.OTP, HonestyUnViolationDataObj.InvalidOTP );		
	}
	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		HonestyUnViolationDataObj = new HonestyUnViolation_JSONDataReader();
		HonestyUnViolationDataObj.JsonReader_HonestyUnViolationRequest();
		RequestCost = GetRequestCost(HonestyUnViolationDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewHonestyUnViolationRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenHonestyUnViolationPromiseRequest();
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
	public void CancelNewHonestyUnViolationRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewHonestyUnViolationRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);

	}

	
	@Test(priority = 3, enabled = true)
	void StopApprovedHonestyUnViolationRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewHonestyUnViolationRequest();
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
	void RejectApprovedHonestyUnViolationRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewHonestyUnViolationRequest();
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
	public void RejectNewHonestyUnViolationRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewHonestyUnViolationRequest();
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
			OpenHonestyUnViolationPromiseRequest();
			HonestyUnViolationObj= new HonestyUnViolationPromiseRequestPage(driver);			
			HonestyUnViolationObj.CreateEmptyHonestyUnViolationRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
