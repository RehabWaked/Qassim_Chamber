package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CompetitionsRequestPage extends PageBase{

	public CompetitionsRequestPage(WebDriver driver) {
		super(driver);
		jse = (JavascriptExecutor) driver;
	}


	@FindBy(name = "ContestName")
	WebElement contestNameTXT;

	@FindBy(name = "ContestPlace")
	WebElement contestPlaceTXT;

	@FindBy(name = "ContestStartDate")
	WebElement ContestStartDate;

	@FindBy(css = "[title='عرض شهر آخر']")
	WebElement MonthClick;

	@FindBy(css = "[title='عرض سنة آخرى']")
	WebElement YearClick;

	@FindBy(css = "[title='اختر يوم الأربعاء, سبتمبر 29']")
	WebElement DaySelected;

	@FindBy(name = "ContestEndDate")
	WebElement ContestEndDate;

	@FindBy(css = "[title='اختر يوم السبت, أكتوبر 2']")
	WebElement endDaySelected;

	@FindBy(css = "[title='اختر يوم الأحد, أكتوبر 3']")
	WebElement ProcedureDay;

	@FindBy(name = "ResultsDate")
	WebElement ResultsDate;

	@FindBy(name = "AwardsDate")
	WebElement AwardsDate;

	@FindBy(css = "[title='اختر يوم الأحد, أكتوبر 3']")
	WebElement ResultDay;

	@FindBy(name = "DistributionMethod")
	WebElement distributionMethodTXT;

	@FindBy(name = "RecieveCouponsPlace")
	WebElement recieveCouponsPlaceTXT;

	@FindBy(name = "SortingDate")
	WebElement SortingDate;

	@FindBy(name = "SortingPlace")
	WebElement sortingPlaceTXT;

	@FindBy(name = "AwardsPlace")
	WebElement awardsPlaceTXT;

	@FindBy(name = "ContestMethod")
	WebElement contestMethodTXT;

	@FindBy(name = "DeadlineAcceptingContestCouponDate")
	WebElement DeadlineAcceptingContestCouponDate;

	@FindBy(css = "[title='اختر يوم الأربعاء, سبتمبر 29']")
	WebElement SelectToday;

	@FindBy(name = "ContestCouponsNumbers")
	WebElement contestCouponsNumbersTXT;

	@FindBy(name = "ContestCouponsFrom")
	WebElement contestCouponsFromTXT;

	@FindBy(name = "ContestCouponsTo")
	WebElement contestCouponsToTXT;

	@FindBy(name = "ContestGoal")
	WebElement contestGoalTXT;

	@FindBy(name = "Question")
	WebElement questionTXT;

	@FindBy(name = "Answer")
	WebElement answerTXT;

	@FindBy(css = "[name='next']")
	WebElement nextBTN;

	@FindBy(css = "[ng-disabled='page.currentStep <= 1']")
	WebElement previousBTN;

	@FindBy(css = "[ng-disabled='page.currentStep != steps.length || saveRequestStart ==true']")
	WebElement ApproveRequest;

	@FindBy(name = "codeToMatch")
	WebElement OTPTXT;

	@FindBy(css = ".cancel")
	WebElement closeRequest;

	@FindBy(css = ".confirm")
	WebElement ApproveRequesProcess;

	@FindBy(name = "settings-outline")
	WebElement settingsOption;

	@FindBy(xpath = "//a[.='الغاء الطلب']")
	WebElement CancelRequestOption;

	@FindBy(css = "[tabindex='3']")
	WebElement cancelRequestReasonTXT;

	@FindBy(css = "[ng-class='config.title']")
	public WebElement RequestCanceledTextMSG;

	@FindBy(name = "files")
	WebElement fileUploader;

	String fileName1 = "pdf-test.pdf";
	String fileName2 = "pdf-test1.pdf";

	@FindBy(css = "[ng-click='setContestTabs(3,ContestDataForm,$event)']")
	WebElement CopetitionTermsBTN;
	
	@FindBy(xpath = "//button[contains(.,'التالى')]")
	WebElement nextStepBTN;
	
	String uploadPath = System.getProperty("user.dir") + "/uploads/" + fileName1;
	String uploadPath1 = System.getProperty("user.dir") + "/uploads/" + fileName2;


	//String country,
	public void createNewRequest_CompetitionsRequest(String contestName, String contestPlace, String distributionMethod, String recieveCouponsPlace, String sortingPlace, String awardsPlace, String contestMethod, String contestCouponsNumbers, String contestCouponsFrom, String contestCouponsTo, String contestGoal, String question, String answer, String otp) throws InterruptedException {

		scrollToBottom();
		clickButton(nextBTN);
		Thread.sleep(1000);
		setTextElementText(contestNameTXT, contestName);
		Thread.sleep(1000);
		setTextElementText(contestPlaceTXT, contestPlace);
		Thread.sleep(1000);
		//بداية المسابقة
		clickButton(ContestStartDate);
		Thread.sleep(1000);
		Select FromMonthOptionSelected = new Select (MonthClick);
		FromMonthOptionSelected.selectByVisibleText("سبتمبر");
		Thread.sleep(1000);
		Select FromYearOptionSelected = new Select (YearClick);
		FromYearOptionSelected.selectByVisibleText("2021");
		Thread.sleep(1000);
		clickButton(DaySelected);
		Thread.sleep(1000);
		// نهاية المسابقة
		clickButton(ContestEndDate);
		Thread.sleep(1000);
		clickButton(MonthClick);
		Thread.sleep(1000);
		Select FromMonthOptionsSelected = new Select (MonthClick);
		FromMonthOptionsSelected.selectByVisibleText("أكتوبر");
		Thread.sleep(1000);
		Select FromYearOptionsSelected = new Select (YearClick);
		FromYearOptionsSelected.selectByVisibleText("2021");
		clickButton(endDaySelected);
		Thread.sleep(1000);
		setTextElementText(distributionMethodTXT, distributionMethod);
		Thread.sleep(1000);
		setTextElementText(recieveCouponsPlaceTXT, recieveCouponsPlace);
		Thread.sleep(1000);
		//تاريخ إجراء الفرز
		clickButton(SortingDate);
		Thread.sleep(1000);
		clickButton(MonthClick);
		Thread.sleep(1000);
		Select FromMonthOptionSelect = new Select (MonthClick);
		FromMonthOptionSelect.selectByVisibleText("أكتوبر");
		Thread.sleep(1000);
		Select FromYearOptionSelect = new Select (YearClick);
		FromYearOptionSelect.selectByVisibleText("2021");
		Thread.sleep(1000);
		clickButton(ProcedureDay);
		Thread.sleep(1000);
		//تاريخ إعلان النتائج
		clickButton(ResultsDate);
		Thread.sleep(1000);
		clickButton(MonthClick);
		Thread.sleep(1000);
		Select FromMonthOption = new Select (MonthClick);
		FromMonthOption.selectByVisibleText("أكتوبر");
		Thread.sleep(1000);
		Select FromYearOption = new Select (YearClick);
		FromYearOption.selectByVisibleText("2021");
		Thread.sleep(1000);
		clickButton(ResultDay);
		Thread.sleep(1000);
		//تاريخ توزيع الجوائز
		clickButton(AwardsDate);
		Thread.sleep(1000);
		clickButton(MonthClick);
		Thread.sleep(1000);
		Select FromMonthOptions = new Select (MonthClick);
		FromMonthOptions.selectByVisibleText("أكتوبر");
		Thread.sleep(1000);
		Select FromYearOptions = new Select (YearClick);
		FromYearOptions.selectByVisibleText("2021");
		Thread.sleep(1000);
		clickButton(ResultDay);
		Thread.sleep(1000);
		setTextElementText(sortingPlaceTXT, sortingPlace);
		Thread.sleep(1000);
		setTextElementText(awardsPlaceTXT, awardsPlace);
		Thread.sleep(1000);
		setTextElementText(contestMethodTXT, contestMethod);
		Thread.sleep(1000);
		//آخـر موعد لقبول قسيمة الاشتراك بالمسابقة
		clickButton(DeadlineAcceptingContestCouponDate);
		Thread.sleep(1000);
		Select FromMonthOptionS = new Select (MonthClick);
		FromMonthOptionS.selectByVisibleText("سبتمبر");
		Thread.sleep(1000);
		Select FromYearOptionS = new Select (YearClick);
		FromYearOptionS.selectByVisibleText("2021");
		Thread.sleep(1000);
		clickButton(SelectToday);
		Thread.sleep(1000);
		setTextElementText(contestCouponsNumbersTXT, contestCouponsNumbers);
		Thread.sleep(1000);
		setTextElementText(contestCouponsFromTXT, contestCouponsFrom);
		Thread.sleep(1000);
		setTextElementText(contestCouponsToTXT, contestCouponsTo);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,1500)");
		Thread.sleep(1000);
		setTextElementText(contestGoalTXT, contestGoal);
		Thread.sleep(1000);
		setTextElementText(questionTXT, question);
		Thread.sleep(1000);
		setTextElementText(answerTXT, answer);
		fileUploader.sendKeys(uploadPath);
		Thread.sleep(1000);
		fileUploader.sendKeys(uploadPath1);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,1500)");
		Thread.sleep(1000);
		clickButton(CopetitionTermsBTN);
		Thread.sleep(1000);
		clickButton(nextStepBTN);
		Thread.sleep(1000);
		jse.executeScript("scrollBy(0,1500)");
		Thread.sleep(1000);
		clickButton(previousBTN);
		Thread.sleep(1000);
		clickButton(nextStepBTN);
		Thread.sleep(3000);
		//اعتماد الطلب
		scrollToBottom();
		clickButton(ApproveRequest);
		Thread.sleep(5000);
		setTextElementText(OTPTXT, otp);
		Thread.sleep(3000);
		//تاكيد خصم المبلغ
		clickButton(ApproveRequesProcess);
		Thread.sleep(10000);
		// اغلاق الاستبيان
		clickButton(closeRequest);
		Thread.sleep(1000);
		jse.executeScript("window.scrollBy(0,-350)", "");
		Thread.sleep(1000);
	}


	@FindBy(css = "[ng-class='config.title']")
	public WebElement CheckboxAlert;

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

	public void createNewRequest_CompetitionsRequestWithoutData() throws InterruptedException {

		scrollToBottom();
		clickButton(nextBTN);
		Thread.sleep(2000);
		System.out.println("Please insert the request required data to be able to complete your cycle and generate your request ... ");

	}

}
