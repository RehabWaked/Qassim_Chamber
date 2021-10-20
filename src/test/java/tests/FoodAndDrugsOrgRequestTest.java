package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Pages.FoodAndDrugsOrganizationRequestPage;
import data.FoodAndDrugs_JSONDataReader;

public class FoodAndDrugsOrgRequestTest extends TestBase{
	FoodAndDrugsOrganizationRequestPage FoodAndDrugsObj;
	FoodAndDrugs_JSONDataReader FoodAndDrugsDataObj;


	private void OpenFoodAndDrugsRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		FoodAndDrugsDataObj = new FoodAndDrugs_JSONDataReader();
		FoodAndDrugsDataObj.JsonReader_FoodAndDrugsOrg();
		OpenNewRequest(FoodAndDrugsDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		FoodAndDrugsObj= new FoodAndDrugsOrganizationRequestPage(driver);			
		FoodAndDrugsObj.CreateNewFoodAndDrugsOrgRequest(FoodAndDrugsDataObj.Name, FoodAndDrugsDataObj.JobTitle, FoodAndDrugsDataObj.Email, FoodAndDrugsDataObj.IDNumber, FoodAndDrugsDataObj.TelNo, FoodAndDrugsDataObj.MobNo, FoodAndDrugsDataObj.Address, FoodAndDrugsDataObj.BuildNo, FoodAndDrugsDataObj.DistrictName, FoodAndDrugsDataObj.StreetName, FoodAndDrugsDataObj.CityName, FoodAndDrugsDataObj.PostalCode, FoodAndDrugsDataObj.UnitNo, FoodAndDrugsDataObj.AdditionalMobNo, FoodAndDrugsDataObj.OTP, FoodAndDrugsDataObj.InvalidOTP);		
	}

	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		FoodAndDrugsDataObj = new FoodAndDrugs_JSONDataReader();
		FoodAndDrugsDataObj.JsonReader_FoodAndDrugsOrg();
		RequestCost = GetRequestCost(FoodAndDrugsDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	public void CreateNewFoodAndDrugsRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenFoodAndDrugsRequest();
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
	public void CancelNewFoodAndDrugsRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewFoodAndDrugsRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);
		ReqLogObj.ViewCancelReason(loginReader.CancelReason);
	}

	@Test(priority = 3, enabled = true)
	void StopApprovedFoodAndDrugsRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewFoodAndDrugsRequest ();
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
	void RejectApprovedFoodAndDrugsRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewFoodAndDrugsRequest ();
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
	public void RejectNewFoodAndDrugsRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewFoodAndDrugsRequest();
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
	public void CreateEmptyFoodAndDrugsRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenFoodAndDrugsRequest();
			FoodAndDrugsObj= new FoodAndDrugsOrganizationRequestPage(driver);			
			FoodAndDrugsObj.CreateEmptyFoodAndDrugsRequest();
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}


}
