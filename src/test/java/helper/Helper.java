package helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	
	
public static void captureScreenshot(WebDriver driver,String screenshotName) throws IOException {
		
		Path dest = Paths.get("./screenshots",screenshotName+".png");
		FileOutputStream out = new FileOutputStream(dest.toString());
		out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
		out.close();
		
		try {
			Files.createDirectories(dest.getParent());
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot" + e);;
		}
		
	}

}

