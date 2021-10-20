package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.Test;
import Pages.RegisterToUseElectronicLicensingServicesFromAutorityPage;
import Pages.RequestsLogPage;
import data.RegisterToUseElectronicLicensingServicesFromAutority_JSONDataReader;

public class RegisterToUseElectronicLicensingServicesFromAutorityTest extends TestBase {

	
	RequestsLogPage ReqLogObj;
	boolean skipTest;	
	RegisterToUseElectronicLicensingServicesFromAutorityPage RegisterLicenseOBJ;
	RegisterToUseElectronicLicensingServicesFromAutority_JSONDataReader RegisterUsingElectronicReader;
	
	
	public void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		RegisterUsingElectronicReader = new RegisterToUseElectronicLicensingServicesFromAutority_JSONDataReader();
		RegisterUsingElectronicReader.jsonReader_FillData();
		RequestCost = GetRequestCost(RegisterUsingElectronicReader.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}


	public void FillDataAndCreateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		RegisterLicenseOBJ= new RegisterToUseElectronicLicensingServicesFromAutorityPage(driver);
		RegisterUsingElectronicReader = new RegisterToUseElectronicLicensingServicesFromAutority_JSONDataReader();
		RegisterUsingElectronicReader.jsonReader_FillData();
		OpenNewRequest( RegisterUsingElectronicReader.RequestName);
		RegisterLicenseOBJ.createNewRequest_RegisterUsingElecronicLicensingServicesAuthorityRequest(RegisterUsingElectronicReader.SirName, RegisterUsingElectronicReader.IdNumber, RegisterUsingElectronicReader.JobTitle, RegisterUsingElectronicReader.IdentityIssuePlace, RegisterUsingElectronicReader.PhoneNumber, RegisterUsingElectronicReader.Email, RegisterUsingElectronicReader.PoBox, RegisterUsingElectronicReader.PostalCode, RegisterUsingElectronicReader.City, RegisterUsingElectronicReader.Street, RegisterUsingElectronicReader.District, RegisterUsingElectronicReader.BuildingNumber, RegisterUsingElectronicReader.ApartmentNumber);
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
		RegisterLicenseOBJ.UserCanceledNewRequestCreated(RegisterUsingElectronicReader.CancelText);
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		ReqLogObj.ViewCancelReason(RegisterUsingElectronicReader.CancelText);
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
			RegisterLicenseOBJ= new RegisterToUseElectronicLicensingServicesFromAutorityPage(driver);
			OpenNewRequest( RegisterUsingElectronicReader.RequestName);
			RegisterLicenseOBJ.createNewRequest_RegisterUsingElecronicLicensingServicesAuthorityRequestWithoutData();
			skipTest = false;
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}
	
	
}
