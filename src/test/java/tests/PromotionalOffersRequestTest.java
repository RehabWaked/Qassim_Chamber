package tests;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Pages.PromotionalOffersRequestPage;
import data.PromotionalOffers_JSONDataReader;


public class PromotionalOffersRequestTest extends TestBase{
	PromotionalOffersRequestPage PromotionalOffersObj;
	PromotionalOffers_JSONDataReader PromotionalOffersDataObj;

	private void OpenPromotionalOffersRequest () throws FileNotFoundException, IOException, ParseException, InterruptedException {
		PromotionalOffersDataObj = new PromotionalOffers_JSONDataReader();
		PromotionalOffersDataObj.JsonReader_PromotionalOffersRequest();
		OpenNewRequest(PromotionalOffersDataObj.RequestName);

	}
	private void FillRequestData() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		PromotionalOffersObj= new PromotionalOffersRequestPage(driver);			
		PromotionalOffersObj.CreateNewPromotionalOffersRequest(PromotionalOffersDataObj.PromoName, PromotionalOffersDataObj.Location, PromotionalOffersDataObj.Notes, PromotionalOffersDataObj.Reason, PromotionalOffersDataObj.Year, PromotionalOffersDataObj.StartMonth, PromotionalOffersDataObj.EndMonth, PromotionalOffersDataObj.OTP, PromotionalOffersDataObj.InvalidOTP );		
	}
	private void CheckUserAvailableBalance() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{

		FirstBalance = CheckCurrentWalletBalance();
		CurrentAvailableBalance = GetAvailableBalance();
		PromotionalOffersDataObj = new PromotionalOffers_JSONDataReader();
		PromotionalOffersDataObj.JsonReader_PromotionalOffersRequest();
		RequestCost = GetRequestCost(PromotionalOffersDataObj.RequestName);
		if (CurrentAvailableBalance >= RequestCost) {
			HasBalance = true;			
		}
		else {
			HasBalance = false;
		}
	}
	private void CreateNewPromotionalOffersRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenPromotionalOffersRequest();
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
	public void CancelNewPromotionalOffersRequestCreated () throws FileNotFoundException, InterruptedException, IOException, ParseException {
		CreateNewPromotionalOffersRequest();
		CancelRequest();
		CheckBalanceAfterSubmission_Cancellation_Rejection();
		ReqLogObj.ViewRequestAttachment();
		Thread.sleep(1000);

	}

	@Test(priority = 3, enabled = true)
	void StopApprovedPromotionalOffersRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewPromotionalOffersRequest ();
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
	void RejectApprovedPromotionalOffersRequest() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		CreateNewPromotionalOffersRequest ();
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
	public void RejectNewPromotionalOffersRequestCreated() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{	
		CreateNewPromotionalOffersRequest();
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
	public void CreateEmptyPromotionalOffersRequest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		CheckUserAvailableBalance();
		if (HasBalance) {
			System.out.println("You have available Balance to Submit New Requests");
			OpenPromotionalOffersRequest();
			PromotionalOffersObj= new PromotionalOffersRequestPage(driver);			
			PromotionalOffersObj.CreateEmptyPromotionalOffersRequest(PromotionalOffersDataObj.PromoName,PromotionalOffersDataObj.Location);
		}
		else {
			System.out.println("You Don't have available Balance to Submit New Requests");
		}
	}
}
