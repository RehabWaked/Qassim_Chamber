package Pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RequestsPage extends PageBase{

	public RequestsPage(WebDriver driver) {
		super(driver);
		jse= (JavascriptExecutor) driver;
	}
	public static WebDriver driver ; 
	@FindBy(css = "[src='templateUrl()'] > .sp-main-box > .sp-main-title > h2")
	public WebElement requestTitle;

	@FindBy(css = "div.request-app.col-md-4.col-sm-12.ng-scope")
	public List<WebElement> RequestsList;
	
	@FindBy(css = "p.request-price.ng-binding.ng-scope")
	WebElement ReqPrice;
	String ReqPriceStr;
	int RequestPrice;
	
	@FindBy(css = "[ng-model='page.filter_categories_subcategories']")
	WebElement SearchBox;

	public int GetRequestPrice (String RequestName) throws InterruptedException {
		setTextElementText(SearchBox, RequestName);
		Thread.sleep(1000);
		ReqPriceStr = ReqPrice.getText().toString();
		ReqPriceStr = convertNumbersToEnglish(ReqPriceStr);
		RequestPrice = Integer.parseInt(ReqPriceStr); 
		System.out.println("Request cost is: " + RequestPrice);
		return RequestPrice;
	}
	public void OpenNewRequestPage (String RequestName) throws InterruptedException {
		try {
				for (WebElement Request: RequestsList) {
						if (Request.getText().toString().contains(RequestName)) {
							Request.click();
							Thread.sleep(1000);
							Assert.assertTrue(requestTitle.getText().contains(RequestName));
							Thread.sleep(1000);
						}
						else
						{
							System.out.println("الطلب غير موجود");
						}
					}
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
