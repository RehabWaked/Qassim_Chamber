package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WalletTransactionsPage extends PageBase{

	public WalletTransactionsPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}
	
	@FindBy (css = ".blue-box > .data-number")
	WebElement TotalBalance;
	String TotalBalanceStr;
	
	@FindBy (css = ".red-box > .data-number")
	WebElement LockedBalance;
	String LockedBalanceStr;
	@FindBy (xpath = "//ul[@class='nav navbar-nav navigation ng-scope']//a[.='الرئيسية']")
	WebElement HomePage;
	
	
	public int CalculateAvailableBalance() throws InterruptedException {
		Thread.sleep(1000);
		TotalBalanceStr = TotalBalance.getText().toString();
		TotalBalanceStr = convertNumbersToEnglish(TotalBalanceStr);
		System.out.println("Your Total Balance is: " + TotalBalanceStr);
		int TotalBalanceInt = Integer.parseInt(TotalBalanceStr);
		Thread.sleep(500);
		LockedBalanceStr = LockedBalance.getText().toString();
		LockedBalanceStr = convertNumbersToEnglish(LockedBalanceStr);
		System.out.println("Your Locked Balance is: " + LockedBalanceStr);
		int LockedBalanceInt = Integer.parseInt(LockedBalanceStr);
		Thread.sleep(500);
		int AvailableBalance = TotalBalanceInt - LockedBalanceInt;
		System.out.println("Your Available Balance is: " + Integer.toString(AvailableBalance));
		Thread.sleep(1000);
		return AvailableBalance;
		
	}
}
