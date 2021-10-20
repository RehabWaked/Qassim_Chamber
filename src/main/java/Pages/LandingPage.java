package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LandingPage extends PageBase{

	public LandingPage(WebDriver driver) {
		super(driver);

		jse = (JavascriptExecutor) driver;
	}

	@FindBy(id = "relatedCard")
	WebElement relatedCardOpen;

	@FindBy(css = "div.sp-main-box.margin-bottom-30")
	List<WebElement> MembershipData;

	@FindBy(css = "div.main_content")
	List<WebElement> TemplatesData;

	@FindBy(css = "[ng-class='config.title']")
	public WebElement MessageTextDisplaying;

	@FindBy(css = "div.row")
	List<WebElement> rowList;

	public void OpenTheCurrentCard() throws InterruptedException {
		scrollUp();
		clickButton(relatedCardOpen);
		Thread.sleep(1000);
		//Assert.assertTrue(MessageTextDisplaying.getText().contains("تم التوجيه بنجاح إلى العضوية 601011123676"));
		Thread.sleep(1000);
		System.out.println(MembershipData.size());
		System.out.println(TemplatesData.size());

		for (WebElement MemberData : MembershipData) {

			System.out.println("MemberData..." + MemberData.getText().toString());
		}		

		for (WebElement TemplateData : TemplatesData) {

			System.out.println("TemplateData ... " + TemplateData.getText().toString());
		}

		System.out.println("Row Content size " + rowList.size());		
		for (WebElement RowContent : rowList) {

			System.out.println(RowContent.getText().toString());
		}
	}
}
