package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.Test;
import Pages.RequestsLogPage;
import Pages.VehicleDrivingAuthorizationRequestPage;
import data.VehicleDrivingAuthorization_JSONDataReader;

public class VehicleDrivingAuthorizationRequestTest extends TestBase{
	

	RequestsLogPage ReqLogObj;
	boolean skipTest;	
	VehicleDrivingAuthorizationRequestPage VehicleDrivingOBJ;
	VehicleDrivingAuthorization_JSONDataReader VehicleDrivingReader;
	
	
	public void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		VehicleDrivingReader = new VehicleDrivingAuthorization_JSONDataReader();
		VehicleDrivingReader.jsonReader_FillData();
		RequestCost = GetRequestCost(VehicleDrivingReader.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	
	public void FillDataAndCreateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		VehicleDrivingOBJ = new VehicleDrivingAuthorizationRequestPage(driver);
		VehicleDrivingReader = new VehicleDrivingAuthorization_JSONDataReader();
		VehicleDrivingReader.jsonReader_FillData();
		OpenNewRequest( VehicleDrivingReader.RequestName);
		VehicleDrivingOBJ.createNewRequest_VehicleDrivingAuthorizationRequest(VehicleDrivingReader.ManagerName, VehicleDrivingReader.SirNameCar, VehicleDrivingReader.IdNumber,VehicleDrivingReader.CarType ,VehicleDrivingReader.CarNumber, VehicleDrivingReader.AuthorizationPeriod, VehicleDrivingReader.CarModel,VehicleDrivingReader.CarColor ,VehicleDrivingReader.ComputerNumber);
	}
	
	@Test(alwaysRun = true, enabled = true)
	void loginTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		LoginToMemberPortal();		
	}
	
	@Test(priority = 1, enabled = true)
	void OpenTheRelatedCard_CheckCreatedNewRrequest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{	
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			FillDataAndCreateRequest();
			CheckBalanceAfterSubmission_Cancellation_Rejection();
			SearchForNewRequest(); 
			ReqLogObj = new RequestsLogPage(driver); 
			ReqLogObj.ViewRequestAttachment();
			skipTest = false;
		}
		else {
			Thread.sleep(1000);
			System.out.println("You Don't have available Balance to Submit New Requests");
			Thread.sleep(1000);
			skipTest = true;
		}
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = {"OpenTheRelatedCard_CheckCreatedNewRrequest"})
	void OpenTheRelatedCard_ApprovedNewRrequest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{	
		NavigateToAdminPortal();		
		AdminApprovedNewRequest();
		AdminCheckOptionsForApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();	
		MemberCheckOptionsForApprovedRequest();
	}
	
	@Test(priority = 3, enabled = true, dependsOnMethods = {"OpenTheRelatedCard_ApprovedNewRrequest"})
	void AdminRejectApprovedRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		if (!skipTest) {
			NavigateToAdminPortal();	
			AdminRejectApprovedRequest();
			NavigateToMemberPortal();
			CheckBalanceAfterSubmission_Cancellation_Rejection();
			OpenRequestsLogPage();
			SearchForRejectedRequest();
			MemberCheckOptionsForRejectedRequest();
		}
		else {
			try 
			{
				skipTest = true;
				throw new SkipException("Skipping this exception");
			}
			catch (Exception e) 
			{
				System.out.println("Test case skipped due to no request created ");		
			}			
		}
	}


	@Test(priority = 4 ,enabled = true)
	void AdminRejectRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		OpenTheRelatedCard_CheckCreatedNewRrequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		NavigateToAdminPortal();	
		AdminRejectNewRequest();
		AdminCheckOptionsForRejectedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		OpenRequestsLogPage();
		SearchForRejectedRequest();
		MemberCheckOptionsForRejectedRequest();
	}


	@Test(priority = 5 ,enabled = true)
	void CancelRequestCreatedTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		OpenTheRelatedCard_CheckCreatedNewRrequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		VehicleDrivingOBJ.UserCanceledNewRequestCreated(VehicleDrivingReader.CancelText);
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		ReqLogObj.ViewCancelReason(VehicleDrivingReader.CancelText);
	}


	@Test(priority = 6 ,enabled = true)
	void StopRequestCreatedTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		OpenTheRelatedCard_CheckCreatedNewRrequest();
		NavigateToAdminPortal();		
		AdminApprovedNewRequest();
		AdminCheckOptionsForApprovedRequest();
		NavigateToMemberPortal();
		CheckBalanceAfterApproval_Stopping();
		OpenRequestsLogPage();
		SearchForApprovedRequest();	
		StopRequest();
		CheckBalanceAfterApproval_Stopping();
		MemberCheckOptionsForStoppedRequest();
		NavigateToAdminPortal();
		AdminCheckOptionsForStoppedRequest();	
	}
	

	
	@Test(priority = 7 ,enabled = true)
	void CreateRequestWithoutData() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			VehicleDrivingOBJ= new VehicleDrivingAuthorizationRequestPage(driver); 
			OpenNewRequest( VehicleDrivingReader.RequestName);
			VehicleDrivingOBJ.createNewRequest_VehicleDrivingAuthorizationRequestWithoutData();
			skipTest = false;
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}
	
}
