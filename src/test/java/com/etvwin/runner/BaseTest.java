package com.etvwin.runner;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.etvwin.listner.WebDriverEventHandler;
import com.etvwin.utility.AllureConfigurator;
import com.etvwin.utility.ConfigReader;
import com.etvwin.utility.DriverManager;
import com.etvwin.utility.ReportManager;
import com.etvwin.utility.ScreenshotUtility;

import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;

public class BaseTest {

	@BeforeSuite
	public void configuringLog4j() throws IOException {
		AllureConfigurator.configure();		
	}
	
	@BeforeMethod
	public void browserSetup() {
		launchBrowser(ConfigReader.getProperty("appUrl"));
	}
	
	 @AfterMethod(alwaysRun = true)
	    public void browserTeardown(ITestResult result) {
	        WebDriver driver = DriverManager.getInstance().getDriver();
	                  System.out.println(driver);
	        String testCaseName = result.getMethod().getConstructorOrMethod().getName();
	        if (result.getStatus() == ITestResult.FAILURE) {
	            saveTextLog(testCaseName + " Failed, Please find the attached screenshot");
	            saveScreenshot(driver);
	            String imageFilePath = ScreenshotUtility.takeFullScreenShot(driver, testCaseName + "_Failed");
	            ReportManager.getTest().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath).build());
	        }
	        closeBrowser(driver);
	    }
	
	
	
	 public WebDriver launchBrowser(String url) {
	       

	        // Set up ChromeDriver
	        WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-infobars");
	        options.addArguments("--incognito");
	        WebDriver baseDriver = new ChromeDriver(options);

	        // Register WebDriverListener properly
	        WebDriverListener listener = new WebDriverEventHandler();
	        WebDriver driver = new EventFiringDecorator<>(listener).decorate(baseDriver);
	        
            //Saving the driver instance
	        DriverManager.getInstance().setDriver(driver);
	        DriverManager.getInstance().setWebDriverListener((WebDriverEventHandler) listener);
	        // Perform browser setup
	        driver.get(url);
	        driver.manage().window().maximize();
	        System.out.println("Launching Browser.");
	        return driver;
	    
	}
	
	
	 
	 
	 
	 
	public void closeBrowser(WebDriver driver){
		System.out.println("Closing Browser.");
		driver.quit();
	}
	
	
	
	
	
	
	// Image attachments for Allure
		@Attachment(value = "Page screenshot", type = "image/png")
		public byte[] saveScreenshot(WebDriver driver) {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}

		// Text attachments for Allure
		@Attachment(value = "{0}", type = "text/plain")
		public static String saveTextLog(String message) {
			return message;
		}
		

		// HTML attachments for Allure
		@Attachment(value = "{0}", type = "text/html")
		public static String attachHtml(String html) {
			return html;
		}

		
		// Video attachments for Allure
		@Attachment(value = "video",type="video/webm")
		public byte[] attachVideo(String path) throws Exception {
		    return Files.readAllBytes(Paths.get(new File(path).getAbsolutePath()));
		}
		

}