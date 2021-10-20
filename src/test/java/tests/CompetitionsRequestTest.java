package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.Test;
import Pages.CompetitionsRequestPage;
import Pages.RequestsLogPage;
import data.CompetitionsRequest_JSONDataReader;

public class CompetitionsRequestTest extends TestBase{
	
	
	
	RequestsLogPage ReqLogObj;
	boolean skipTest;	
	CompetitionsRequestPage CompetitionsOBJ;
	CompetitionsRequest_JSONDataReader CopetitionReader;

	
	
	public void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		CopetitionReader = new CompetitionsRequest_JSONDataReader();
		CopetitionReader.jsonReader_FillData();
		RequestCost = GetRequestCost(CopetitionReader.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}


	public void FillDataAndCreateRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		CompetitionsOBJ = new CompetitionsRequestPage(driver);
		CopetitionReader = new CompetitionsRequest_JSONDataReader();
		CopetitionReader.jsonReader_FillData();
		OpenNewRequest(CopetitionReader.RequestName);
		CompetitionsOBJ.createNewRequest_CompetitionsRequest(CopetitionReader.ContestName, CopetitionReader.ContestPlace, CopetitionReader.DistributionMethod, CopetitionReader.RecieveCouponsPlace, CopetitionReader.SortingPlace, CopetitionReader.AwardsPlace, CopetitionReader.ContestMethod, CopetitionReader.ContestCouponsNumbers, CopetitionReader.ContestCouponsFrom, CopetitionReader.ContestCouponsTo, CopetitionReader.ContestGoal, CopetitionReader.Question, CopetitionReader.Answer, CopetitionReader.OTP);
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
		CompetitionsOBJ.UserCanceledNewRequestCreated(CopetitionReader.CancelText);
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		ReqLogObj.ViewCancelReason(CopetitionReader.CancelText);
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
			CompetitionsOBJ = new CompetitionsRequestPage(driver);
			OpenNewRequest(CopetitionReader.RequestName);
			CompetitionsOBJ.createNewRequest_CompetitionsRequestWithoutData();
			skipTest = false;
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
			skipTest = true;
		}
	}
	
}
