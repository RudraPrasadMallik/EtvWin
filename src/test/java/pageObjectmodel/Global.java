package pageObjectmodel;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Global {
	private static  Logger logger = LogManager.getLogger(Global.class);
	
	 public static void takescreenshot(WebDriver driver, String screenshotName) {
		 
		 TakesScreenshot ts = (TakesScreenshot) driver;
	 
		 File source = ts.getScreenshotAs(OutputType.FILE);
	 
	      File destination = new File(System.getProperty("D:\\Screen shots\\screenshot.png") +
			          "/screenshots/" + screenshotName + ".png");
	  
	  
	  try {
		  FileUtils.copyFile(source,destination);
		  logger.info("Screenshot executed and saved in this location: "+ destination.getAbsolutePath());
		
	} catch (Exception e) {
		logger.error("Not able to save the error");
	}
	  
}


	
	
	
	

}
