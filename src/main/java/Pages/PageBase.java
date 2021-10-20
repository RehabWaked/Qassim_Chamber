package Pages;

import java.awt.Desktop.Action;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



public class PageBase {

 
	public  PageBase(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	protected WebDriver driver;
	public JavascriptExecutor jse;
	public static Select select;
	Action action;
	
	


	//This Method Will use it in all pages that want click button
	protected static void clickButton(WebElement button)
	{
		button.click();
	}

	protected static void clearWebElement(WebElement textbox) {
		textbox.clear();
		
	}

	//This method will used all over the project to send the texts
	protected static void setTextElementText(WebElement txtElement , String value)
	{
		txtElement.sendKeys(value);
	}

	public void scrollToTheMiddle() throws InterruptedException {

		jse.executeScript("scrollBy(0,250)");
		Thread.sleep(1000);
	}

	public void scrollToBottom() throws InterruptedException
	{
		jse.executeScript("scrollBy(0,2500)");
		Thread.sleep(1000);
	}

	public void scrollUp() throws InterruptedException {

		jse.executeScript("scrollBy(0,-1500)");
		Thread.sleep(1000);
	}
	
    // Use below Lines to scroll from left to right

	public void scrollRight() throws InterruptedException {

	    jse.executeScript("window.scrollBy(500000, 0)");
		Thread.sleep(1000);
	}
    
    // Use below Lines to scroll from right to left
	public void scrollLeft() throws InterruptedException {

	    jse.executeScript("window.scrollBy(-500000, 0)");
		Thread.sleep(1000);
	}

	
    // Use below Lines to scroll to find eny element and do your action on this element
	public void scrollToFindElemenet() throws InterruptedException {

		//element refers to the element you want to do you action on it 
		// As an example :- click on detailsBTN
		jse.executeScript("arguments[0].scrollIntoView()" , "element");
		Thread.sleep(1000);
	}
	
	public String convertNumbersToEnglish(String str) {
	    String answer = str;
	    answer = answer.replace("١", "1");
	    answer = answer.replace("٢", "2");
	    answer = answer.replace("٣", "3");
	    answer = answer.replace("٤", "4");
	    answer = answer.replace("٥", "5");
	    answer = answer.replace("٦", "6");
	    answer = answer.replace("٧", "7");
	    answer = answer.replace("٨", "8");
	    answer = answer.replace("٩", "9");
	    answer = answer.replace("٠", "0");
	    answer = answer.replace("ر", "");
	    answer = answer.replace("ي", "");
	    answer = answer.replace("ا", "");
	    answer = answer.replace("ل", "");
	    answer = answer.replace(" ", "");
	    return answer;
	}
	

}
