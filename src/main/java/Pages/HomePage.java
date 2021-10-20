package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends PageBase{

	public HomePage(WebDriver driver) {
		super(driver);

		jse = (JavascriptExecutor) driver;
	}


	@FindBy(css = "a.dropdown-toggle")
	List<WebElement> DDLOptions;
	@FindBy(css = ".navigation > li:nth-of-type(5) > .dropdown-toggle")
	WebElement WalletDDL;
	@FindBy(css = ".navigation [href='/#/WalletsTrans']")
	WebElement WalletTrx;
	
	@FindBy(css = ".navigation > li:nth-of-type(3) > .dropdown-toggle")
	WebElement RequestsDDL;

	@FindBy(css = ".navigation [ng-click='$root.redirectNewRequest(\\'/NewRequest\\')']")
	WebElement newRequest;

	@FindBy(css = ".navigation [href='/#/AllRequests']")
	WebElement RequestsLog;

	@FindBy(css = "div.req-list-item.ng-scope")
	List<WebElement> RequestsType;

	@FindBy(css = "div.request-app.col-md-4.col-sm-12.ng-scope")
	List<WebElement> RequestsList;

	@FindBy(css = ".wallet > .title")
	WebElement Balance;
	public String BalanceStr;

	@FindBy(css = "[title='اغلاق']")
	WebElement CloseBanner;

	
	public int SetBalance () throws InterruptedException {
		//set First Balance
		BalanceStr = Balance.getText();
		System.out.println(BalanceStr);
		BalanceStr = convertNumbersToEnglish(BalanceStr);
		System.out.println(BalanceStr);
		int x = Integer.parseInt(BalanceStr);
		Thread.sleep(1000);
		return x;
	}	
	
	
	public void CloseBanner () throws InterruptedException {

		clickButton(CloseBanner);
	}

	public void OpenRequestsPage () {
		try {
			// الطلبات
			clickButton(RequestsDDL);
			Thread.sleep(1000);
			// طلب جديد
			clickButton(newRequest);
			Thread.sleep(1000);
			System.out.println("Side menu elements size " + RequestsType.size());
			System.out.println("Requests elements size " + RequestsList.size());
			scrollToTheMiddle();
		}


		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void OpenRequestsLogPage () throws InterruptedException {
		Thread.sleep(1000);
		clickButton(RequestsDDL);
		// طلب جديد
		clickButton(RequestsLog);
		Thread.sleep(1000);
		scrollToTheMiddle();
	}

	@FindBy(xpath = "//span[.='كشف الحساب استعراض كشف حسابك']")
	WebElement WalletTransBtn;
	
	public void OpenWalletTransactions() throws InterruptedException {
		Thread.sleep(1000);
		scrollUp();
		Thread.sleep(1000);
		clickButton(WalletDDL);
		Thread.sleep(1000);
		clickButton(WalletTrx);
		scrollToTheMiddle();
		Thread.sleep(1000);

}
}
