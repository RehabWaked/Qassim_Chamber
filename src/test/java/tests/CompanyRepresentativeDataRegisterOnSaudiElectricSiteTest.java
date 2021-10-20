package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.Test;
import Pages.CompanyRepresentativeDataRegisterOnSaudiElectricSitePage;
import Pages.RequestsLogPage;
import data.CompanyRepresentativeDataRegisterOnSaudiElectricSite_JSONDataReader;

public class CompanyRepresentativeDataRegisterOnSaudiElectricSiteTest extends TestBase{

	
	RequestsLogPage ReqLogObj;
	boolean skipTest;
	CompanyRepresentativeDataRegisterOnSaudiElectricSitePage CompRepresentativeOBJ;
	CompanyRepresentativeDataRegisterOnSaudiElectricSite_JSONDataReader CompRepresentativeReader;
	
	
	
	public void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		CompRepresentativeReader = new CompanyRepresentativeDataRegisterOnSaudiElectricSite_JSONDataReader();
		CompRepresentativeReader.jsonReader_FillData();
		RequestCost = GetRequestCost(CompRepresentativeReader.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}


	public void FillDataAndCreateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		CompRepresentativeOBJ = new CompanyRepresentativeDataRegisterOnSaudiElectricSitePage(driver);
		CompRepresentativeReader = new CompanyRepresentativeDataRegisterOnSaudiElectricSite_JSONDataReader();
		CompRepresentativeReader.jsonReader_FillData();
		OpenNewRequest(CompRepresentativeReader.RequestName);
		CompRepresentativeOBJ.createNewRequest_CompanyRepresentativeDataRegisterOnSaudiElectricSiteRequest(CompRepresentativeReader.BusinessPartnerNumber, CompRepresentativeReader.Title, CompRepresentativeReader.FirstName, CompRepresentativeReader.LastName, CompRepresentativeReader.Position, CompRepresentativeReader.Language, CompRepresentativeReader.PhoneNumber, CompRepresentativeReader.MobileNumber, CompRepresentativeReader.Fax, CompRepresentativeReader.Email, CompRepresentativeReader.AuthorizedName, CompRepresentativeReader.AuthorizedPosition, CompRepresentativeReader.OTP);
	}


	@Test(alwaysRun = true, enabled = true)
	void loginTest() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		LoginToMemberPortal();		
	}
		
	//Create new request with it's status is under procedure 
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
		CompRepresentativeOBJ.UserCanceledNewRequestCreated(CompRepresentativeReader.CancelText);
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		ReqLogObj.ViewCancelReason(CompRepresentativeReader.CancelText);
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
		CheckBalanceAfterApproval_Stopping();
		NavigateToAdminPortal();
		AdminCheckOptionsForStoppedRequest();	
	}

	@Test(priority = 7 ,enabled = true)
	void CreateRequestWithoutData() throws FileNotFoundException, IOException, ParseException, InterruptedException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			CompRepresentativeOBJ = new CompanyRepresentativeDataRegisterOnSaudiElectricSitePage(driver);
			OpenNewRequest(CompRepresentativeReader.RequestName);
			CompRepresentativeOBJ.createNewRequest_CompanyRepresentativeDataRegisterOnSaudiElectricSiteRequestWithoutData();
			skipTest = false;
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}
	
	
}
