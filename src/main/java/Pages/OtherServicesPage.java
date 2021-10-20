package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class OtherServicesPage extends PageBase{

	public OtherServicesPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}



	@FindBy(css = "body > div.page-wrap.ng-isolate-scope > div.dashboard-content > div > div.container.chamber-601 > div > div.col-md-9.col-sm-8 > div > div.col-md-8 > div > div.sp-main-title > h2")
	WebElement OtherServicesTitle;

	@FindBy(css = "div.col-md-4.col-sm-6")
	List<WebElement> OtherServicesList;

	@FindBy(partialLinkText = "تحديث البيانات")
	WebElement UpdateInfoService;
	
	@FindBy(css = "body > div.page-wrap.ng-isolate-scope > div.dashboard-content > div > div > div > div > div > div > div.sp-main-form > p")
	WebElement UpdateInfoTitle;
	
	@FindBy(css = "body > div.page-wrap.ng-isolate-scope > div.dashboard-content > div > div > div > div > div > div > div.sp-main-form > div > p")
	WebElement updateInfoMessage;
	
	@FindBy(css = "a.sp-main-button.sp-main-sm-button.pull-left")
	WebElement backBTN;
	
	
	
	public void DisplayAndCheckUpdateInfoService() throws InterruptedException {

		System.out.println("Electronic Other Services Title Is " + OtherServicesTitle.getText().toString());
		Thread.sleep(1000);

		System.out.println("الخدمات الالكترونية المتاحة هى " + OtherServicesList.size());
		scrollToTheMiddle();
		Thread.sleep(1000);
		for (WebElement service : OtherServicesList) {

			System.out.println(service.getText().toString());
			Thread.sleep(1000);			
		}

		if (UpdateInfoService.isDisplayed())
		{
			UpdateInfoService.click();
			Thread.sleep(3000);
			System.out.println(UpdateInfoTitle.getText().toString());
			Thread.sleep(1000);	
			System.out.println(updateInfoMessage.getText().toString());
			Thread.sleep(1000);	
			Assert.assertTrue(updateInfoMessage.getText().toString().contains("لتحديث البيانات برجاء إرسال صورة من المستندات التالية (السجل التجاري - هوية المدير ) على البريد الإلكتروني ssh@qcc.org.sa"));
			Thread.sleep(1000);
			clickButton(backBTN);
			Thread.sleep(1000);


		}
		
		else {
			System.out.println("Not Exist ... ");
		}
	}

}
