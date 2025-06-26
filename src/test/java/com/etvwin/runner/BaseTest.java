package com.etvwin.runner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.etvwin.listner.WebDriverEventHandler;
import com.etvwin.utility.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;

public class BaseTest {

    @BeforeSuite
    public void configuringLog4j() throws IOException {
        AllureConfigurator.configure();
    }

    // ✅ Launch browser ONCE before all tests in this class
    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUpBrowser(@Optional("chrome") String browserName) {
        System.out.println("======> @BeforeClass: browser setup called");
        launchBrowser(browserName, ConfigReader.getProperty("appUrl"));
    }

    // ✅ Start test logging here (NO browser launch here)
    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method) {
        ReportManager.startTest(method.getName());
    }

    // ✅ After each method: capture screenshot on failure (but DO NOT quit browser)
    @AfterMethod(alwaysRun = true)
    public void captureFailureIfAny(ITestResult result) {
        WebDriver driver = DriverManager.getInstance().getDriver();
        String testCaseName = result.getMethod().getConstructorOrMethod().getName();

        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                saveTextLog(testCaseName + " Failed, see screenshot below:");
                saveScreenshot(driver);

                String imageFilePath = ScreenshotUtility.takeFullScreenShot(driver, testCaseName + "_Failed");

                if (imageFilePath != null && !imageFilePath.isEmpty()) {
                    if (ReportManager.getTest() != null) {
                        ReportManager.getTest().fail("Screenshot",
                                MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath).build());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Exception in @AfterMethod: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ✅ Quit browser AFTER all tests in the class
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        WebDriver driver = DriverManager.getInstance().getDriver();
        if (driver != null) {
            System.out.println("Closing Browser from @AfterClass.");
            closeBrowser(driver);
            DriverManager.getInstance().setDriver(null); // Clean up for next class
        }
    }

    // ✅ Launch browser method
    public WebDriver launchBrowser(String browserName, String url) {
        WebDriver baseDriver;

        if (browserName == null) {
            throw new RuntimeException("browserName is null – check your testng.xml parameter!");
        }

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                baseDriver = new ChromeDriver(new ChromeOptions());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                baseDriver = new org.openqa.selenium.firefox.FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                baseDriver = new org.openqa.selenium.edge.EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        WebDriverListener listener = new WebDriverEventHandler();
        WebDriver driver = new EventFiringDecorator<>(listener).decorate(baseDriver);

        DriverManager.getInstance().setDriver(driver);
        DriverManager.getInstance().setWebDriverListener((WebDriverEventHandler) listener);

        driver.manage().window().maximize();
        driver.get(url);

        System.out.println("Launching Browser: " + browserName);
        return driver;
    }

    public void closeBrowser(WebDriver driver) {
        System.out.println("Closing Browser.");
        driver.quit();
    }

    // Allure Attachments

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Attachment(value = "video", type = "video/webm")
    public byte[] attachVideo(String path) throws Exception {
        return Files.readAllBytes(Paths.get(new File(path).getAbsolutePath()));
    }
}














//package com.etvwin.runner;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.events.EventFiringDecorator;
//import org.openqa.selenium.support.events.WebDriverListener;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
//
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.etvwin.listner.WebDriverEventHandler;
//import com.etvwin.utility.AllureConfigurator;
//import com.etvwin.utility.ConfigReader;
//import com.etvwin.utility.DriverManager;
//import com.etvwin.utility.ReportManager;
//import com.etvwin.utility.ScreenshotUtility;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import io.qameta.allure.Attachment;
//
//public class BaseTest {
//
//	@BeforeSuite
//	public void configuringLog4j() throws IOException {
//		AllureConfigurator.configure();		
//	}
//	
//	 @BeforeMethod
//	 @Parameters("browser") 
//	public void browserSetup(String browserName, Method method) 
//	 {
//		 System.out.println("======> browserSetup() called");
//		    System.out.println("======> Parameter passed: " + browserName);
//		launchBrowser( browserName,ConfigReader.getProperty("appUrl"));
//		
//		  ReportManager.startTest(method.getName());
//	}
//	
//	 public WebDriver launchBrowser(String browserName, String url) {
//		    WebDriver baseDriver = null;  // initialize as null
//
//		    if (browserName.equalsIgnoreCase("chrome")) {
//		    	 if(browserName == null) {
//		    		throw new RuntimeException("browserName is null – check your testng.xml parameter!");
//		    	    }
//		        WebDriverManager.chromedriver().setup();
//		        ChromeOptions options = new ChromeOptions();
//		        // You can add options here if needed
//		        baseDriver = new ChromeDriver(options); // initialize only here
//		    } 
//		    else if (browserName.equalsIgnoreCase("firefox")) {
//		        WebDriverManager.firefoxdriver().setup();
//		        baseDriver = new org.openqa.selenium.firefox.FirefoxDriver();
//		    } 
//		    else if (browserName.equalsIgnoreCase("edge")) {
//		        WebDriverManager.edgedriver().setup();
//		        baseDriver = new org.openqa.selenium.edge.EdgeDriver();
//		    } 
//		    else {
//		        throw new IllegalArgumentException("Browser not supported: " + browserName);
//		    }
//
//		    WebDriverListener listener = new WebDriverEventHandler();
//		    WebDriver driver = new EventFiringDecorator<>(listener).decorate(baseDriver);
//
//		    DriverManager.getInstance().setDriver(driver);
//		    DriverManager.getInstance().setWebDriverListener((WebDriverEventHandler) listener);
//
//		    driver.manage().window().maximize();
//		    driver.get(url);   // <--- You missed opening the URL here in your current code
//
//		    System.out.println("Launching Browser: " + browserName);
//		    return driver;
//		}
//
//	 
//	 
//	 @AfterMethod(alwaysRun = true)
//	 public void browserTeardown(ITestResult result) {
//	     WebDriver driver = DriverManager.getInstance().getDriver();
//	     String testCaseName = result.getMethod().getConstructorOrMethod().getName();
//
//	     try {
//	         if (result.getStatus() == ITestResult.FAILURE) {
//	             saveTextLog(testCaseName + " Failed, Please find the attached screenshot");
//	             saveScreenshot(driver);
//
//	             String imageFilePath = ScreenshotUtility.takeFullScreenShot(driver, testCaseName + "_Failed");
//
//	             if (imageFilePath != null && !imageFilePath.isEmpty()) {
//	                 if (ReportManager.getTest() != null) {
//	                     ReportManager.getTest().fail("Screenshot",
//	                             MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath).build());
//	                 } else {
//	                     System.out.println("ReportManager.getTest() is returning null inside BaseTest.");
//	                 }
//	             } else {
//	                 System.err.println("ScreenshotUtility returned null or empty path inside Base Test.");
//	             }
//	         }
//	     } catch (Exception e) {
//	         System.err.println("Exception in @AfterMethod: " + e.getMessage());
//	         e.printStackTrace();
//	     } 
//	 }
//
//	 
//	 
//	 
//	
////	 @AfterMethod(alwaysRun = true)
////	    public void browserTeardown(ITestResult result) {
////	        WebDriver driver = DriverManager.getInstance().getDriver();
////	                  System.out.println(driver);
////	        String testCaseName = result.getMethod().getConstructorOrMethod().getName();
////	        if (result.getStatus() == ITestResult.FAILURE) {
////	            saveTextLog(testCaseName + " Failed, Please find the attached screenshot");
////	            saveScreenshot(driver);
////	            String imageFilePath = ScreenshotUtility.takeFullScreenShot(driver, testCaseName + "_Failed");
//////	            ReportManager.getTest().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath).build());
////	        }
////	        closeBrowser(driver);
////	    }
//
//	 
//	public void closeBrowser(WebDriver driver){
//		System.out.println("Closing Browser.");
//		driver.quit();
//	}
//	
//
//	// Image attachments for Allure
//		@Attachment(value = "Page screenshot", type = "image/png")
//		public byte[] saveScreenshot(WebDriver driver) {
//			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//		}
//
//		// Text attachments for Allure
//		@Attachment(value = "{0}", type = "text/plain")
//		public static String saveTextLog(String message) {
//			return message;
//		}
//		
//
//		// HTML attachments for Allure
//		@Attachment(value = "{0}", type = "text/html")
//		public static String attachHtml(String html) {
//			return html;
//		}
//
//		
//		// Video attachments for Allure
//		@Attachment(value = "video",type="video/webm")
//		public byte[] attachVideo(String path) throws Exception {
//		    return Files.readAllBytes(Paths.get(new File(path).getAbsolutePath()));
//		}
//		
//
//}