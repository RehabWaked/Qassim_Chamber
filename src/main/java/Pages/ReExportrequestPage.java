package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class ReExportrequestPage extends PageBase{

	public ReExportrequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}


	@FindBy(css = ".wallet > .title")
	WebElement CurrentWalletValue;

	@FindBy(css = "a.dropdown-toggle")
	List<WebElement> DDLOptions;

	@FindBy(css = ".navigation > li:nth-of-type(3) > .dropdown-toggle")
	WebElement RequestsDDL;

	@FindBy(css = ".navigation [ng-click='$root.redirectNewRequest(\\'/NewRequest\\')']")
	WebElement newRequest;

	@FindBy(css = ".navigation [href='/#/AllRequests']")
	WebElement RequestsLog;

	@FindBy(css = "[ng-model='searchModel.requestId']")
	WebElement requestNumTXT;

	@FindBy(css = "[ng-click='SearchAllRequest();']")
	WebElement searchBTN;

	@FindBy(css = "div.req-list-item.ng-scope")
	List<WebElement> RequestsType;

	@FindBy(css = "div.request-app.col-md-4.col-sm-12.ng-scope")
	List<WebElement> RequestsList;

	@FindBy(css = "[src='templateUrl()'] > .sp-main-box > .sp-main-title > h2")
	public WebElement requestTitle;

	@FindBy(className = "div.sp-main-title")
	public WebElement FixedRequests;
	
	@FindBy(css = ".alert")
	public WebElement ReExportAlertText;

	// 	ReExport Request Data Required to complete the cycle
	@FindBy(name = "MC_ExporterPhone")
	WebElement exporterPhoneTXT;

	@FindBy(name = "MC_ExporterZipCode")
	WebElement exporterZipCodeTXT;

	@FindBy(name = "MC_ExporterFax")
	WebElement exporterFaxTXT;

	@FindBy(name = "MC_ImporterName")
	WebElement importerNameTXT;

	@FindBy(name = "MC_ExporterPBox")
	WebElement exporterPBoxTXT;

	@FindBy(css = "[name='MC_ImporterCountry'] [placeholder]")
	WebElement ImporterCountryDLL;

	@FindBy(css = "div:nth-of-type(160) [ng-bind-html='c.nameAr']")
	WebElement countrySelected;

	@FindBy(name = "MC_ImporterPBox")
	WebElement importerPBoxTXT;

	@FindBy(name = "MC_ImporterCity")
	WebElement importerCityTXT;

	@FindBy(name = "MC_ImporterFax")
	WebElement importerFaxTXT;

	@FindBy(name = "MC_GoodsType")
	WebElement goodsTypeTXT;

	@FindBy(name = "MC_GoodsOrigin")
	WebElement goodsOriginTXT;

	@FindBy(name = "MC_GoodsInformation")
	WebElement goodsInformationTXT;

	@FindBy(name = "MC_GoodsQuantity")
	WebElement goodsQuantityTXT;

	@FindBy(name = "MC_CertificateOfOriginNum")
	WebElement certificateOriginNumberTXT;

	// Date Picker
	@FindBy(name = "MC_CertificateOfOriginDate")
	WebElement CertificateDateClick;

	@FindBy(css = "[title='عرض شهر آخر']")
	WebElement MonthClick;

	@FindBy(css = ".calendars-today")
	WebElement DaySelected;

	@FindBy(css = ".calendars-highlight")
	WebElement WrongDaySelected;

	@FindBy(css = "[title='عرض سنة آخرى']")
	WebElement YearClick;

	@FindBy(name = "MC_GoodsValue")
	WebElement goodsValueTXT;

	@FindBy(name = "MC_GoodsValueDollar")
	WebElement goodsValueDollarTXT;

	@FindBy(name = "MC_BillNum")
	WebElement billNumberTXT;

	// Date Picker
	@FindBy(name = "MC_BillDate")
	WebElement BillDateClick;

	@FindBy(css = "[name='MC_ForeignCurrency'] [placeholder]")
	WebElement ForeignCurrencyDDL;

	@FindBy(css = "[repeat='c.id as c in LKUP_ForeignCurrencies | filter: $select.search'] div:nth-of-type(5) [ng-bind-html='c.nameAr']")
	WebElement EGPCurrencySelected;

	@FindBy(name = "files")
	WebElement fileUploader;

	String fileName1 = "pdf-test.pdf";
	String fileName2 = "pdf-test1.pdf";
	String uploadPath = System.getProperty("user.dir") + "/uploads/" + fileName1;
	String uploadPath1 = System.getProperty("user.dir") + "/uploads/" + fileName2;

	@FindBy(css = "[name='next']")
	WebElement NextBTN;

	@FindBy(css = "[ng-disabled='page.currentStep <= 1']")
	WebElement BackBTN;

	@FindBy(css = "[ng-disabled='page.currentStep != steps.length || saveRequestStart ==true']")
	WebElement ApproveRequest;

	@FindBy(name = "codeToMatch")
	WebElement OTPTXT;

	@FindBy(css = "[src='templateUrl()'] h2")
	public WebElement RequestDetailsTXT;

	@FindBy(css = "ul.text-center.list-unstyled.custom-center-ul")
	List<WebElement> RequestDetailsComponent;

	@FindBy(css = ".confirm")
	WebElement ApproveRequesProcess;

	@FindBy(css = ".cancel")
	WebElement closeRequest;

	@FindBy(css = "[ng-class='config.title']")
	public WebElement RequestCanceledTextMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ExporterPhone.$error'] > .text-danger")
	public WebElement ExporterPhoneRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ExporterPBox.$error'] > .text-danger")
	public WebElement ExporterPBoxRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ExporterFax.$error'] > .text-danger")
	public WebElement ExporterFaxRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ExporterZipCode.$error'] > .text-danger")
	public WebElement ExporterZipCodeRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ImporterName.$error'] > .text-danger")
	public WebElement ImporterNameRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ImporterCountry.$error'] > .text-danger")
	public WebElement ImporterCountryRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ImporterCity.$error'] > .text-danger")
	public WebElement ImporterCityRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ImporterFax.$error'] > .text-danger")
	public WebElement ImporterFaxRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_GoodsType.$error'] > .text-danger")
	public WebElement GoodsTypeRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_GoodsOrigin.$error'] > .text-danger")
	public WebElement GoodsOriginRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_GoodsInformation.$error'] > .text-danger")
	public WebElement GoodsInformationRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_GoodsQuantity.$error'] > .text-danger")
	public WebElement GoodsQuantityRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_CertificateOfOriginNum.$error'] > .text-danger")
	public WebElement CertificateOriginNumRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_CertificateOfOriginDate.$error'] > .text-danger")
	public WebElement CertificateOriginDateRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_GoodsValue.$error'] > .text-danger")
	public WebElement GoodsValueRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_GoodsValueDollar.$error'] > .text-danger")
	public WebElement GoodsValueDollarRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_BillNum.$error'] > .text-danger")
	public WebElement BillingNumRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_BillDate.$error'] > .text-danger")
	public WebElement BillingDateRequiredMSG;

	@FindBy(css = "[ng-messages='requestForm.MC_ForeignCurrency.$error'] > .text-danger")
	public WebElement ForeignCurrencyRequiredMSG;

	@FindBy(css = "[data-balloon='طلب اعادة التصدير ']")
	public WebElement ReExportRequest;

	@FindBy(css = ".wallet > .title")
	WebElement Balance;
	
	@FindBy(css = ".custom-select > div:nth-of-type(2) h2")
	public WebElement AllRequestsText;
	

	
	
	
	public void CreateNewRequest_ReExportRequest(String exporterPhone, String exporterZipCode, String exporterFax, String exporterPBox, String importerName, String importerPBox, String importerCity, String importerFax, String goodsType, String goodsOrigin, String goodsInformation, String goodsQuantity, String certificateOriginNumber, String goodsValue, String goodsValueDollar, String billNumber, String otp) throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue(ReExportAlertText.getText().contains("فى حالة وجود بعض البيانات غير مكتملة يرجي التواصل مع الغرفة لتحديثها ."));
		Thread.sleep(1000);
		setTextElementText(exporterPhoneTXT,exporterPhone);
		Thread.sleep(1000);
		setTextElementText(exporterZipCodeTXT,exporterZipCode);
		Thread.sleep(1000);
		setTextElementText(exporterFaxTXT, exporterFax);
		Thread.sleep(1000);
		setTextElementText(exporterPBoxTXT, exporterPBox);
		Thread.sleep(1000);
		setTextElementText(importerNameTXT, importerName);
		Thread.sleep(1000);
		clickButton(ImporterCountryDLL);
		Thread.sleep(1000);
		clickButton(countrySelected);
		Thread.sleep(1000);
		setTextElementText(importerPBoxTXT, importerPBox);
		Thread.sleep(1000);
		setTextElementText(importerCityTXT, importerCity);
		Thread.sleep(1000);
		setTextElementText(importerFaxTXT, importerFax);
		Thread.sleep(1000);
		scrollToTheMiddle();
		Thread.sleep(1000);
		setTextElementText(goodsTypeTXT, goodsType);
		Thread.sleep(1000);
		setTextElementText(goodsOriginTXT, goodsOrigin);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,150)");
		Thread.sleep(1000);
		setTextElementText(goodsInformationTXT, goodsInformation);
		Thread.sleep(1000);
		setTextElementText(goodsQuantityTXT, goodsQuantity);
		Thread.sleep(1000);
		setTextElementText(certificateOriginNumberTXT, certificateOriginNumber);
		Thread.sleep(1000);
		clickButton(CertificateDateClick);
		Thread.sleep(1000);

		// Date Picker تاريخ شهادة المنشأ
		clickButton(MonthClick);
		Thread.sleep(1000);
		Select FromMonthOptionSelected = new Select (MonthClick);
		FromMonthOptionSelected.selectByVisibleText("سبتمبر");
		Thread.sleep(1000);
		clickButton(YearClick);
		Thread.sleep(1000);
		Select FromYearOptionSelected = new Select (YearClick);
		FromYearOptionSelected.selectByVisibleText("2021");
		Thread.sleep(1000);
		clickButton(DaySelected);
		Thread.sleep(1000);
		setTextElementText(goodsValueTXT, goodsValue);
		Thread.sleep(1000);
		setTextElementText(goodsValueDollarTXT, goodsValueDollar);
		Thread.sleep(1000);
		setTextElementText(billNumberTXT, billNumber);
		Thread.sleep(1000);

		// Date Picker تاريخ الفاتورة
		clickButton(BillDateClick);
		Thread.sleep(1000);
		Select FromMonthOptionForBilling = new Select (MonthClick);
		FromMonthOptionForBilling.selectByVisibleText("سبتمبر");
		Thread.sleep(1000);
		clickButton(YearClick);
		Thread.sleep(1000);
		Select FromYearOptionForBilling = new Select (YearClick);
		FromYearOptionForBilling.selectByVisibleText("2021");
		Thread.sleep(1000);
		clickButton(DaySelected);
		Thread.sleep(1000);
		clickButton(ForeignCurrencyDDL);
		Thread.sleep(1000);
		clickButton(EGPCurrencySelected);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,250)");
		Thread.sleep(1000);
		fileUploader.sendKeys(uploadPath);
		Thread.sleep(1000);
		fileUploader.sendKeys(uploadPath1);
		Thread.sleep(1000);
		clickButton(NextBTN);
		Thread.sleep(1000);

		for (WebElement ExporterInfo : RequestDetailsComponent) {

			System.out.println(ExporterInfo.getText().toString());
		}
		Thread.sleep(1000);
		//السابق
		clickButton(BackBTN);
		Thread.sleep(1000);
		// اعتماد الطلب
		clickButton(NextBTN);
		Thread.sleep(1000);
		//اعتماد الطلب
		clickButton(ApproveRequest);
		Thread.sleep(1000);
		setTextElementText(OTPTXT, otp);
		Thread.sleep(10000);
		clickButton(ApproveRequesProcess);
		Thread.sleep(10000);
		clickButton(closeRequest);
		Thread.sleep(1000);
		jse.executeScript("window.scrollBy(0,-350)", "");
	}



	public void BillDateValidation_ReExportRequest(String exporterPhone, String exporterZipCode, String exporterFax, String exporterPBox, String importerName, String importerPBox, String importerCity, String importerFax, String goodsType, String goodsOrigin, String goodsInformation, String goodsQuantity, String certificateOriginNumber, String goodsValue, String goodsValueDollar, String billNumber) throws InterruptedException {

		for (WebElement DropLists : DDLOptions) {

			System.out.println(DropLists.getText().toString());
		}

		try {

			// الطلبات
			Thread.sleep(5000);
			clickButton(RequestsDDL);
			Thread.sleep(1000);
			// طلب جديد
			clickButton(newRequest);
			Thread.sleep(1000);
			System.out.println("Side menu elements size " + RequestsType.size());
			System.out.println("Requests elements size " + RequestsList.size());
			scrollToTheMiddle();
			Thread.sleep(1000);

			for (WebElement RequetTypesGranted: RequestsType) {

				System.out.println("Requests Type Privileges Granted .... " + RequetTypesGranted.getText().toString());

				for (WebElement Request: RequestsList) {

					System.out.println("Request Type Per Requests Privileges Granted .... " + Request.getText().toString());

					if (Request.findElement(By.partialLinkText("طلب اعادة التصدير")).isDisplayed()) {
						Thread.sleep(1000);
						Request.click();
						Thread.sleep(6000);
						Assert.assertTrue(requestTitle.getText().contains("طلب اعادة التصدير"));
						Thread.sleep(1000);
						Assert.assertTrue(ReExportAlertText.getText().contains("فى حالة وجود بعض البيانات غير مكتملة يرجي التواصل مع الغرفة لتحديثها ."));
						Thread.sleep(1000);
						setTextElementText(exporterPhoneTXT,exporterPhone);
						Thread.sleep(1000);
						setTextElementText(exporterZipCodeTXT,exporterZipCode);
						Thread.sleep(1000);
						setTextElementText(exporterFaxTXT, exporterFax);
						Thread.sleep(1000);
						setTextElementText(exporterPBoxTXT, exporterPBox);
						Thread.sleep(1000);
						setTextElementText(importerNameTXT, importerName);
						Thread.sleep(1000);
						clickButton(ImporterCountryDLL);
						Thread.sleep(1000);
						clickButton(countrySelected);
						Thread.sleep(1000);
						setTextElementText(importerPBoxTXT, importerPBox);
						Thread.sleep(1000);
						setTextElementText(importerCityTXT, importerCity);
						Thread.sleep(1000);
						setTextElementText(importerFaxTXT, importerFax);
						Thread.sleep(1000);
						scrollToTheMiddle();
						Thread.sleep(1000);
						setTextElementText(goodsTypeTXT, goodsType);
						Thread.sleep(1000);
						setTextElementText(goodsOriginTXT, goodsOrigin);
						Thread.sleep(1000);
						jse.executeScript("scrollBy(0,150)");
						Thread.sleep(1000);
						setTextElementText(goodsInformationTXT, goodsInformation);
						Thread.sleep(1000);
						setTextElementText(goodsQuantityTXT, goodsQuantity);
						Thread.sleep(1000);
						setTextElementText(certificateOriginNumberTXT, certificateOriginNumber);
						Thread.sleep(1000);
						clickButton(CertificateDateClick);
						Thread.sleep(1000);

						// Date Picker تاريخ شهادة المنشأ
						clickButton(MonthClick);
						Thread.sleep(1000);
						Select FromMonthOptionSelected = new Select (MonthClick);
						FromMonthOptionSelected.selectByVisibleText("سبتمبر");
						Thread.sleep(1000);
						clickButton(YearClick);
						Thread.sleep(1000);
						Select FromYearOptionSelected = new Select (YearClick);
						FromYearOptionSelected.selectByVisibleText("2021");
						Thread.sleep(1000);
						clickButton(DaySelected);
						Thread.sleep(1000);
						setTextElementText(goodsValueTXT, goodsValue);
						Thread.sleep(1000);
						setTextElementText(goodsValueDollarTXT, goodsValueDollar);
						Thread.sleep(1000);
						setTextElementText(billNumberTXT, billNumber);
						Thread.sleep(1000);

						// Date Picker تاريخ الفاتورة
						clickButton(BillDateClick);
						Thread.sleep(1000);
						Select FromMonthOptionForBilling = new Select (MonthClick);
						FromMonthOptionForBilling.selectByVisibleText("اغسطس");
						Thread.sleep(1000);
						clickButton(YearClick);
						Thread.sleep(1000);
						Select FromYearOptionForBilling = new Select (YearClick);
						FromYearOptionForBilling.selectByVisibleText("2022");
						Thread.sleep(1000);
						clickButton(WrongDaySelected);
						Thread.sleep(1000);
						clickButton(ForeignCurrencyDDL);
						Thread.sleep(1000);
						clickButton(EGPCurrencySelected);
						Thread.sleep(1000);
						jse.executeScript("scrollBy(0,250)");
						Thread.sleep(1000);
						fileUploader.sendKeys(uploadPath);
						Thread.sleep(1000);
						fileUploader.sendKeys(uploadPath1);
						Thread.sleep(1000);
						clickButton(NextBTN);
						Thread.sleep(1000);
					}

					else
					{
						System.out.println("الرصيد غير كافى  يرجى الشحن .. ");}}
			}}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}


	public void CertificateDateValidation_ReExportRequest(String exporterPhone, String exporterZipCode, String exporterFax, String exporterPBox, String importerName, String importerPBox, String importerCity, String importerFax, String goodsType, String goodsOrigin, String goodsInformation, String goodsQuantity, String certificateOriginNumber, String goodsValue, String goodsValueDollar, String billNumber) throws InterruptedException {

		for (WebElement DropLists : DDLOptions) {

			System.out.println(DropLists.getText().toString());
		}

		try {

			// الطلبات
			Thread.sleep(3000);
			clickButton(RequestsDDL);
			Thread.sleep(1000);
			// طلب جديد
			clickButton(newRequest);
			Thread.sleep(1000);
			System.out.println("Side menu elements size " + RequestsType.size());
			System.out.println("Requests elements size " + RequestsList.size());
			scrollToTheMiddle();
			Thread.sleep(1000);

			for (WebElement RequetTypesGranted: RequestsType) {

				System.out.println("Requests Type Privileges Granted .... " + RequetTypesGranted.getText().toString());

				for (WebElement Request: RequestsList) {

					System.out.println("Request Type Per Requests Privileges Granted .... " + Request.getText().toString());

					if (Request.findElement(By.partialLinkText("طلب اعادة التصدير")).isDisplayed()) {
						Thread.sleep(1000);
						Request.click();
						Thread.sleep(6000);
						Assert.assertTrue(requestTitle.getText().contains("طلب اعادة التصدير"));
						Thread.sleep(1000);
						Assert.assertTrue(ReExportAlertText.getText().contains("فى حالة وجود بعض البيانات غير مكتملة يرجي التواصل مع الغرفة لتحديثها ."));
						Thread.sleep(1000);
						setTextElementText(exporterPhoneTXT,exporterPhone);
						Thread.sleep(1000);
						setTextElementText(exporterZipCodeTXT,exporterZipCode);
						Thread.sleep(1000);
						setTextElementText(exporterFaxTXT, exporterFax);
						Thread.sleep(1000);
						setTextElementText(exporterPBoxTXT, exporterPBox);
						Thread.sleep(1000);
						setTextElementText(importerNameTXT, importerName);
						Thread.sleep(1000);
						clickButton(ImporterCountryDLL);
						Thread.sleep(1000);
						clickButton(countrySelected);
						Thread.sleep(1000);
						setTextElementText(importerPBoxTXT, importerPBox);
						Thread.sleep(1000);
						setTextElementText(importerCityTXT, importerCity);
						Thread.sleep(1000);
						setTextElementText(importerFaxTXT, importerFax);
						Thread.sleep(1000);
						scrollToTheMiddle();
						Thread.sleep(1000);
						setTextElementText(goodsTypeTXT, goodsType);
						Thread.sleep(1000);
						setTextElementText(goodsOriginTXT, goodsOrigin);
						Thread.sleep(1000);
						jse.executeScript("scrollBy(0,150)");
						Thread.sleep(1000);
						setTextElementText(goodsInformationTXT, goodsInformation);
						Thread.sleep(1000);
						setTextElementText(goodsQuantityTXT, goodsQuantity);
						Thread.sleep(1000);
						setTextElementText(certificateOriginNumberTXT, certificateOriginNumber);
						Thread.sleep(1000);
						clickButton(CertificateDateClick);
						Thread.sleep(1000);

						// Date Picker تاريخ شهادة المنشأ
						clickButton(MonthClick);
						Thread.sleep(1000);
						Select FromMonthOptionSelected = new Select (MonthClick);
						FromMonthOptionSelected.selectByVisibleText("اغسطس");
						Thread.sleep(1000);
						clickButton(YearClick);
						Thread.sleep(1000);
						Select FromYearOptionSelected = new Select (YearClick);
						FromYearOptionSelected.selectByVisibleText("2022");
						Thread.sleep(1000);
						clickButton(WrongDaySelected);
						Thread.sleep(1000);
						setTextElementText(goodsValueTXT, goodsValue);
						Thread.sleep(1000);
						setTextElementText(goodsValueDollarTXT, goodsValueDollar);
						Thread.sleep(1000);
						setTextElementText(billNumberTXT, billNumber);
						Thread.sleep(1000);

						// Date Picker تاريخ الفاتورة
						clickButton(BillDateClick);
						Thread.sleep(1000);
						Select FromMonthOptionForBilling = new Select (MonthClick);
						FromMonthOptionForBilling.selectByVisibleText("سبتمبر");
						Thread.sleep(1000);
						clickButton(YearClick);
						Thread.sleep(1000);
						Select FromYearOptionForBilling = new Select (YearClick);
						FromYearOptionForBilling.selectByVisibleText("2021");
						Thread.sleep(1000);
						clickButton(DaySelected);
						Thread.sleep(1000);
						clickButton(ForeignCurrencyDDL);
						Thread.sleep(1000);
						clickButton(EGPCurrencySelected);
						Thread.sleep(1000);
						jse.executeScript("scrollBy(0,250)");
						Thread.sleep(1000);
						fileUploader.sendKeys(uploadPath);
						Thread.sleep(1000);
						fileUploader.sendKeys(uploadPath1);
						Thread.sleep(1000);
						clickButton(NextBTN);
						Thread.sleep(1000);
					}
					else
					{
						System.out.println("الرصيد غير كافى  يرجى الشحن .. ");

					}

				}

			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}



	public void CancelNewRequest_ReExportRequest(String exporterPhone, String exporterZipCode, String exporterFax, String exporterPBox, String importerName, String importerPBox, String importerCity, String importerFax, String goodsType, String goodsOrigin, String goodsInformation, String goodsQuantity, String certificateOriginNumber, String goodsValue, String goodsValueDollar, String billNumber, String otp) throws InterruptedException {

		try {

			Thread.sleep(1000);
			clickButton(RequestsDDL);
			Thread.sleep(1000);
			clickButton(newRequest);
			Thread.sleep(1000);
			System.out.println("Side menu elements size " + RequestsType.size());
			System.out.println("Requests elements size " + RequestsList.size());
			scrollToTheMiddle();
			Thread.sleep(1000);

			for (WebElement RequetTypesGranted: RequestsType) {

				System.out.println("Requests Type Privileges Granted .... " + RequetTypesGranted.getText().toString());


				for (WebElement Request: RequestsList) {

					System.out.println("Request Type Per Requests Privileges Granted .... " + Request.getText().toString());

					if (Request.findElement(By.partialLinkText("طلب اعادة التصدير")).isDisplayed()) {
						Thread.sleep(1000);
						Request.click();
						Thread.sleep(6000);
						Assert.assertTrue(requestTitle.getText().contains("طلب اعادة التصدير"));
						Thread.sleep(1000);
						Assert.assertTrue(ReExportAlertText.getText().contains("فى حالة وجود بعض البيانات غير مكتملة يرجي التواصل مع الغرفة لتحديثها ."));
						Thread.sleep(1000);
						setTextElementText(exporterPhoneTXT,exporterPhone);
						Thread.sleep(1000);
						setTextElementText(exporterZipCodeTXT,exporterZipCode);
						Thread.sleep(1000);
						setTextElementText(exporterFaxTXT, exporterFax);
						Thread.sleep(1000);
						setTextElementText(exporterPBoxTXT, exporterPBox);
						Thread.sleep(1000);
						setTextElementText(importerNameTXT, importerName);
						Thread.sleep(1000);
						clickButton(ImporterCountryDLL);
						Thread.sleep(1000);
						clickButton(countrySelected);
						Thread.sleep(1000);
						setTextElementText(importerPBoxTXT, importerPBox);
						Thread.sleep(1000);
						setTextElementText(importerCityTXT, importerCity);
						Thread.sleep(1000);
						setTextElementText(importerFaxTXT, importerFax);
						Thread.sleep(1000);
						scrollToTheMiddle();
						Thread.sleep(1000);
						setTextElementText(goodsTypeTXT, goodsType);
						Thread.sleep(1000);
						setTextElementText(goodsOriginTXT, goodsOrigin);
						Thread.sleep(1000);
						jse.executeScript("scrollBy(0,150)");
						Thread.sleep(1000);
						setTextElementText(goodsInformationTXT, goodsInformation);
						Thread.sleep(1000);
						setTextElementText(goodsQuantityTXT, goodsQuantity);
						Thread.sleep(1000);
						setTextElementText(certificateOriginNumberTXT, certificateOriginNumber);
						Thread.sleep(1000);
						clickButton(CertificateDateClick);
						Thread.sleep(1000);

						// Date Picker تاريخ شهادة المنشأ
						Select FromMonthOptionSelected = new Select (MonthClick);
						FromMonthOptionSelected.selectByVisibleText("اغسطس");
						Thread.sleep(1000);
						Select FromYearOptionSelected = new Select (YearClick);
						FromYearOptionSelected.selectByVisibleText("2021");
						Thread.sleep(1000);
						clickButton(DaySelected);
						Thread.sleep(1000);
						setTextElementText(goodsValueTXT, goodsValue);
						Thread.sleep(1000);
						setTextElementText(goodsValueDollarTXT, goodsValueDollar);
						Thread.sleep(1000);
						setTextElementText(billNumberTXT, billNumber);
						Thread.sleep(1000);

						// Date Picker تاريخ الفاتورة
						clickButton(BillDateClick);
						Thread.sleep(1000);
						Select FromMonthOptionForBilling = new Select (MonthClick);
						FromMonthOptionForBilling.selectByVisibleText("اغسطس");
						Thread.sleep(1000);
						//					clickButton(YearClick);
						//					Thread.sleep(1000);
						Select FromYearOptionForBilling = new Select (YearClick);
						FromYearOptionForBilling.selectByVisibleText("2021");
						Thread.sleep(1000);
						clickButton(DaySelected);
						Thread.sleep(1000);
						clickButton(ForeignCurrencyDDL);
						Thread.sleep(1000);
						clickButton(EGPCurrencySelected);
						Thread.sleep(1000);
						jse.executeScript("scrollBy(0,250)");
						Thread.sleep(1000);
						fileUploader.sendKeys(uploadPath);
						Thread.sleep(3000);
						fileUploader.sendKeys(uploadPath1);
						Thread.sleep(3000);
						clickButton(NextBTN);
						Thread.sleep(1000);

						for (WebElement ExporterInfo : RequestDetailsComponent) {

							System.out.println(ExporterInfo.getText().toString());
						}
						Thread.sleep(1000);

						clickButton(BackBTN);
						Thread.sleep(1000);
						// التالى
						clickButton(NextBTN);
						Thread.sleep(1000);
						// اعتماد الطلب
						clickButton(ApproveRequest);
						Thread.sleep(1000);
						setTextElementText(OTPTXT, otp);
						Thread.sleep(6000);
						clickButton(closeRequest);
						Thread.sleep(5000);
						jse.executeScript("scroll(0, -250);");
						Thread.sleep(1000);
					}

					else
					{
						System.out.println("Try Again ... ");
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}



	public void SendEmptyData_ReExportRequest() throws InterruptedException {

		try {

			Thread.sleep(1000);
			clickButton(RequestsDDL);
			Thread.sleep(1000);
			clickButton(newRequest);
			Thread.sleep(1000);
			System.out.println("Side menu elements size " + RequestsType.size());
			System.out.println("Requests elements size " + RequestsList.size());
			scrollToTheMiddle();
			Thread.sleep(1000);

			for (WebElement RequetTypesGranted: RequestsType) {

				System.out.println("Requests Type Privileges Granted .... " + RequetTypesGranted.getText().toString());


				for (WebElement Request: RequestsList) {

					System.out.println("Request Type Per Requests Privileges Granted .... " + Request.getText().toString());

					if (Request.findElement(By.partialLinkText("طلب اعادة التصدير")).isDisplayed()) {
						Thread.sleep(1000);
						Request.click();
						Thread.sleep(6000);

						Assert.assertTrue(requestTitle.getText().contains("طلب اعادة التصدير"));
						Thread.sleep(1000);
						Assert.assertTrue(ReExportAlertText.getText().contains("فى حالة وجود بعض البيانات غير مكتملة يرجي التواصل مع الغرفة لتحديثها ."));
						Thread.sleep(1000);
						scrollToBottom();
						Thread.sleep(3000);
						clickButton(NextBTN);
						Thread.sleep(1000);
					}

					else
					{
						System.out.println("Try Again ... ");
					}
				}
			}
		}



		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}



	public void AttachmentsField_ReExportRequest(String exporterPhone, String exporterZipCode, String exporterFax, String exporterPBox, String importerName, String importerPBox, String importerCity, String importerFax, String goodsType, String goodsOrigin, String goodsInformation, String goodsQuantity, String certificateOriginNumber, String goodsValue, String goodsValueDollar, String billNumber, String otp) throws InterruptedException {

		try {

			Thread.sleep(1000);
			clickButton(RequestsDDL);
			Thread.sleep(1000);
			clickButton(newRequest);
			Thread.sleep(1000);
			System.out.println("Side menu elements size " + RequestsType.size());
			System.out.println("Requests elements size " + RequestsList.size());
			scrollToTheMiddle();
			Thread.sleep(1000);

			for (WebElement RequetTypesGranted: RequestsType) {

				System.out.println("Requests Type Privileges Granted .... " + RequetTypesGranted.getText().toString());

				for (WebElement Request: RequestsList) {

					System.out.println("Request Type Per Requests Privileges Granted .... " + Request.getText().toString());

					if (Request.findElement(By.partialLinkText("طلب اعادة التصدير")).isDisplayed()) {
						Thread.sleep(1000);
						Request.click();
						Thread.sleep(6000);
						Assert.assertTrue(requestTitle.getText().contains("طلب اعادة التصدير"));
						Thread.sleep(1000);
						Assert.assertTrue(ReExportAlertText.getText().contains("فى حالة وجود بعض البيانات غير مكتملة يرجي التواصل مع الغرفة لتحديثها ."));
						Thread.sleep(1000);
						setTextElementText(exporterPhoneTXT,exporterPhone);
						Thread.sleep(1000);
						setTextElementText(exporterZipCodeTXT,exporterZipCode);
						Thread.sleep(1000);
						setTextElementText(exporterFaxTXT, exporterFax);
						Thread.sleep(1000);
						setTextElementText(exporterPBoxTXT, exporterPBox);
						Thread.sleep(1000);
						setTextElementText(importerNameTXT, importerName);
						Thread.sleep(1000);
						clickButton(ImporterCountryDLL);
						Thread.sleep(1000);
						clickButton(countrySelected);
						Thread.sleep(1000);
						setTextElementText(importerPBoxTXT, importerPBox);
						Thread.sleep(1000);
						setTextElementText(importerCityTXT, importerCity);
						Thread.sleep(1000);
						setTextElementText(importerFaxTXT, importerFax);
						Thread.sleep(1000);
						scrollToTheMiddle();
						Thread.sleep(1000);
						setTextElementText(goodsTypeTXT, goodsType);
						Thread.sleep(1000);
						setTextElementText(goodsOriginTXT, goodsOrigin);
						Thread.sleep(1000);
						jse.executeScript("scrollBy(0,150)");
						Thread.sleep(1000);
						setTextElementText(goodsInformationTXT, goodsInformation);
						Thread.sleep(1000);
						setTextElementText(goodsQuantityTXT, goodsQuantity);
						Thread.sleep(1000);
						setTextElementText(certificateOriginNumberTXT, certificateOriginNumber);
						Thread.sleep(1000);
						clickButton(CertificateDateClick);
						Thread.sleep(1000);

						// Date Picker تاريخ شهادة المنشأ
						clickButton(MonthClick);
						Thread.sleep(1000);
						Select FromMonthOptionSelected = new Select (MonthClick);
						FromMonthOptionSelected.selectByVisibleText("سبتمبر");
						Thread.sleep(1000);
						clickButton(YearClick);
						Thread.sleep(1000);
						Select FromYearOptionSelected = new Select (YearClick);
						FromYearOptionSelected.selectByVisibleText("2021");
						Thread.sleep(1000);
						clickButton(DaySelected);
						Thread.sleep(1000);
						setTextElementText(goodsValueTXT, goodsValue);
						Thread.sleep(1000);
						setTextElementText(goodsValueDollarTXT, goodsValueDollar);
						Thread.sleep(1000);
						setTextElementText(billNumberTXT, billNumber);
						Thread.sleep(1000);

						// Date Picker تاريخ الفاتورة
						clickButton(BillDateClick);
						Thread.sleep(1000);
						Select FromMonthOptionForBilling = new Select (MonthClick);
						FromMonthOptionForBilling.selectByVisibleText("سبتمبر");
						Thread.sleep(1000);
						clickButton(YearClick);
						Thread.sleep(1000);
						Select FromYearOptionForBilling = new Select (YearClick);
						FromYearOptionForBilling.selectByVisibleText("2021");
						Thread.sleep(1000);
						clickButton(DaySelected);
						Thread.sleep(1000);
						clickButton(ForeignCurrencyDDL);
						Thread.sleep(1000);
						clickButton(EGPCurrencySelected);
						Thread.sleep(1000);
						scrollToBottom();
						Thread.sleep(3000);
						clickButton(NextBTN);
						Thread.sleep(1000);
					}

					else
					{
						System.out.println("Try Again ... ");
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}



	@FindBy(name = "settings-outline")
	WebElement settingsOption;

	@FindBy(css = "#tblInfo > table > tbody > tr:nth-child(5) > td:nth-child(9) > div > ul")
	List<WebElement> OptionsList;

	@FindBy(xpath = "//a[.='الغاء الطلب']")
	WebElement CancelRequestOption;

	@FindBy(css = "[tabindex='3']")
	WebElement cancelRequestReasonTXT;

	@FindBy(css = "[ng-model='searchModel.requestId']")
	WebElement searchRequestID;
	
	
	public void UserCanceledNewRequestCreated(String CancellationReason) throws InterruptedException {
		clickButton(settingsOption);
		Thread.sleep(1000);
		clickButton(CancelRequestOption);
		Thread.sleep(1000);
		clickButton(ApproveRequesProcess);
		Thread.sleep(1000);
		setTextElementText(cancelRequestReasonTXT, CancellationReason);
		Thread.sleep(1000);
		clickButton(ApproveRequesProcess);
		Thread.sleep(1000);
		Assert.assertFalse(RequestCanceledTextMSG.getText().toString().contains("تم الغاء الطلب بنجاح"));
		Thread.sleep(1000);
	} 
} 