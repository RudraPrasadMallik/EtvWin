package com.etvwin.runner;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.etvwin.listner.WebDriverEventHandler;
import com.etvwin.utility.AllureConfigurator;
import com.etvwin.utility.ConfigReader;
import com.etvwin.utility.DriverManager;
import com.etvwin.utility.ReportManager;
import com.etvwin.utility.ScreenshotUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;

public class BaseTest {

	@BeforeSuite
	public void configuringLog4j() throws IOException {
		AllureConfigurator.configure();		
	}
	
	 @BeforeMethod
	 @Parameters("browser") 
	public void browserSetup(@Optional("chrome")String browserName, Method method) 
	 {
		 System.out.println("======> browserSetup() called");
		    System.out.println("======> Parameter passed: " + browserName);
		launchBrowser( browserName,ConfigReader.getProperty("appUrl"));
		
		  ReportManager.startTest(method.getName());
	}
	
	 public WebDriver launchBrowser(String browserName, String url) {
		    WebDriver baseDriver = null;  // initialize as null

		    if (browserName.equalsIgnoreCase("chrome")) {
		    	 if(browserName == null) {
		    		throw new RuntimeException("browserName is null – check your testng.xml parameter!");
		    	    }
		        WebDriverManager.chromedriver().setup();
		        ChromeOptions options = new ChromeOptions();
		        // You can add options here if needed
		        baseDriver = new ChromeDriver(options); // initialize only here
		    } 
		    else if (browserName.equalsIgnoreCase("firefox")) {
		        WebDriverManager.firefoxdriver().setup();
		        baseDriver = new org.openqa.selenium.firefox.FirefoxDriver();
		    } 
		    else if (browserName.equalsIgnoreCase("edge")) {
		        WebDriverManager.edgedriver().setup();
		        baseDriver = new org.openqa.selenium.edge.EdgeDriver();
		    } 
		    else {
		        throw new IllegalArgumentException("Browser not supported: " + browserName);
		    }

		    WebDriverListener listener = new WebDriverEventHandler();
		    WebDriver driver = new EventFiringDecorator<>(listener).decorate(baseDriver);

		    DriverManager.getInstance().setDriver(driver);
		    DriverManager.getInstance().setWebDriverListener((WebDriverEventHandler) listener);

		    driver.manage().window().maximize();
		    driver.get(url);   // <--- You missed opening the URL here in your current code

		    System.out.println("Launching Browser: " + browserName);
		    return driver;
		}

	 
	 
	 @AfterMethod(alwaysRun = true)
	 public void browserTeardown(ITestResult result) {
	     WebDriver driver = DriverManager.getInstance().getDriver();
	     String testCaseName = result.getMethod().getConstructorOrMethod().getName();

	     try {
	         if (result.getStatus() == ITestResult.FAILURE) {
	             saveTextLog(testCaseName + " Failed, Please find the attached screenshot");
	             saveScreenshot(driver);

	             String imageFilePath = ScreenshotUtility.takeFullScreenShot(driver, testCaseName + "_Failed");

	             if (imageFilePath != null && !imageFilePath.isEmpty()) {
	                 if (ReportManager.getTest() != null) {
	                     ReportManager.getTest().fail("Screenshot",
	                             MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath).build());
	                 } else {
	                     System.out.println("ReportManager.getTest() is returning null inside BaseTest.");
	                 }
	             } else {
	                 System.err.println("ScreenshotUtility returned null or empty path inside Base Test.");
	             }
	         }
	     } catch (Exception e) {
	         System.err.println("Exception in @AfterMethod: " + e.getMessage());
	         e.printStackTrace();
	     } finally {
	         closeBrowser(driver);
	     }
	 }

	
//	 @AfterMethod(alwaysRun = true)
//	    public void browserTeardown(ITestResult result) {
//	        WebDriver driver = DriverManager.getInstance().getDriver();
//	                  System.out.println(driver);
//	        String testCaseName = result.getMethod().getConstructorOrMethod().getName();
//	        if (result.getStatus() == ITestResult.FAILURE) {
//	            saveTextLog(testCaseName + " Failed, Please find the attached screenshot");
//	            saveScreenshot(driver);
//	            String imageFilePath = ScreenshotUtility.takeFullScreenShot(driver, testCaseName + "_Failed");
////	            ReportManager.getTest().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath).build());
//	        }
//	        closeBrowser(driver);
//	    }

	 
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