package Pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class AdminRequestSettingsPage extends PageBase{

	public AdminRequestSettingsPage(WebDriver driver) {
		super(driver);	
		jse = (JavascriptExecutor) driver;
	}

	@FindBy (css = "[ng-model='searchModel.RequestName']")
	WebElement SearchByReqNameTxt;

	@FindBy (css = "[ng-click='GetAllRequestsTypes();']")
	WebElement SearchBtn;

	@FindBy (css = ".fa-edit")
	public WebElement ReqSetting;

	@FindBy (css = "[ng-model='ObjToEdit.nameAr']")
	public WebElement ReqArNameTxt;

	@FindBy (css = "[ng-model='ObjToEdit.expireDateInterval']")
	public WebElement ExpireDaysTxt;

	@FindBy (css = "[ng-model='ObjToEdit.amount']")
	public WebElement ReqAmountTxt;

	@FindBy(id="inlineCheckbox21")
	public WebElement ActiveLbl;

	@FindBy(xpath = "//div[@class='mt-checkbox-inline']/label[1]/span[1]")
	public WebElement ActiveChkBox;

	@FindBy (id="inlineCheckbox22")
	public WebElement AutoLbl;

	@FindBy(xpath = "//label[2]/span[1]")
	public WebElement AutoChkBox;

	@FindBy (id="inlineCheckbox23")
	public WebElement OTPLbl;

	@FindBy(xpath = "//label[3]/span[1]")
	public WebElement OTPChkBox;

	@FindBy (id="inlineCheckbox24")
	public WebElement AttachmentLbl;

	@FindBy(xpath = "//label[4]/span[1]")
	public WebElement AttachmentChkBox;

	@FindBy (id="inlineCheckbox25")
	public WebElement DownloadLbl;

	@FindBy(xpath = "//label[5]/span[1]")
	public WebElement DownloadChkBox;

	@FindBy (css = "[ng-click='UpdateSetting(ObjToEdit,updateForm);']")
	WebElement SaveBtn;

	@FindBy (css = "[ng-class='config.title']")
	public WebElement SaveMsg;

	@FindBy (css = "[visible='toggleEditShow'] .modal-footer > .btn")
	WebElement CloseSettingBtn;

	public void OpenRequestSettings(String ReqName) throws InterruptedException {
		Thread.sleep(1000);
		SearchByReqNameTxt.sendKeys(ReqName);
		Thread.sleep(1000);
		SearchBtn.click();
		Thread.sleep(1000);
	}



	public void ChangeAnyTextBoxinRequestSettings(WebElement WebEL, String NewValue) throws InterruptedException {
		ReqSetting.click();
		Thread.sleep(1000);
		WebEL.clear();
		WebEL.sendKeys(NewValue);
		SaveSettings();
		ReqSetting.click();
		Thread.sleep(1000);
		Assert.assertTrue(WebEL.getAttribute("value").contains(NewValue));
		Thread.sleep(1000);
		System.out.println(WebEL.getAttribute("value"));
		clickButton(CloseSettingBtn);

	}
	@FindBy (className = "label.mt-checkbox.mt-checkbox-outline")
	List<WebElement> Checkboxes;

	String classAtr;
	public void CheckCertainRequestOption(WebElement Label, WebElement CheckBox) throws InterruptedException {

		ReqSetting.click();
		Thread.sleep(1000);
		classAtr = Label.getAttribute("class");
		if (classAtr.contains("ng-not-empty"))
		{
			System.out.println("The Request Option is already Checked");

		}
		else
		{

			CheckBox.click();
			System.out.println("The Request Option is now Checked");
			System.out.println(classAtr);

		}

		Thread.sleep(1000);
		SaveSettings();
		clickButton(ReqSetting);
		Thread.sleep(1000);		
		classAtr = Label.getAttribute("class");
		System.out.println(classAtr);
		Assert.assertTrue(classAtr.contains("ng-not-empty"));
		Thread.sleep(1000);
		clickButton(CloseSettingBtn);

	}

	public void UnCheckCertainRequestOption(WebElement Label, WebElement CheckBox) throws InterruptedException {
		ReqSetting.click();
		Thread.sleep(1000);
		classAtr = Label.getAttribute("class");
		if (classAtr.contains("ng-not-empty"))
		{
			Thread.sleep(1000);
			CheckBox.click();
			Thread.sleep(1000);
			System.out.println("The Request Option is now UnChecked");
			System.out.println(classAtr);

		} 

		else{

			System.out.println("The Request Option is already UnChecked");

		}

		Thread.sleep(1000);
		SaveSettings();
		clickButton(ReqSetting);
		Thread.sleep(1000);
		classAtr = Label.getAttribute("class");
		Assert.assertTrue(classAtr.contains("ng-empty"));
		System.out.println(classAtr);
		Thread.sleep(1000);
		clickButton(CloseSettingBtn);

	}


	public void SaveSettings () throws InterruptedException{
		SaveBtn.click();
		Thread.sleep(1000);
		Assert.assertTrue(SaveMsg.getText().contains("تم التعديل بنجاح."));
		Thread.sleep(1000);

	}
}
