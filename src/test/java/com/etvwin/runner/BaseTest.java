//package base;

//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Properties;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.BeforeTest;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class BaseTest {
//	
//	public static WebDriver driver;
//	public static Properties prop = new Properties();
//	public static Properties loc = new Properties();
//	public static FileReader fr;
//	public static FileReader fr1;
//	
//	
//	
//	@BeforeTest
//	public void setUp() throws IOException  {
//		
//		System.out.println("setUp Started");
//		
//		if(driver == null) {
//		
//				fr = new FileReader(System.getProperty("D:\\Spring-Tool-Suit2\\Etv-Win\\src\\test\\resources\\config\\conf.properties"));
//				fr1 = new FileReader(System.getProperty("D:\\Spring-Tool-Suit2\\Etv-Win\\src\\test\\resources\\config\\locators.properties"));
//			   
//				
////		String	fr = ("src\\test\\resources\\config\\config.properties");
////	    String fr1  =("src\\test\\resources\\config\\locators.properties");
////			
////				
////				File configFile = new File(fr);
////	            File locatorsFile = new File(fr1);
////	            System.out.println(config);
////	            
////	            if (!configFile.exists() || !locatorsFile.exists()) {
////	                System.out.println("Properties files not found at the expected location.");
////	                System.exit(1);  // Exit the program if files are not found
////	            }
////		
//				
//			prop.load(fr);
//			loc.load(fr1);
//		}
//		else {
//			System.out.println("If bluck in not executed");
//		}
//		
//		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
//			WebDriverManager .chromedriver().setup();
//			 driver = new ChromeDriver();
//			 driver.get(loc.getProperty("main-Url"));
//		}
//		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			 driver = new FirefoxDriver();
//			 driver.get(loc.getProperty("main-Url"));
//	}		
//}
//	
//	
////	@AfterTest
//	public void tearDown() {
//		driver.close();
//		System.out.println("Driver Closed");
//	}
//}

package com.etvwin.runner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.automationpractice.utility.AllureConfigurator;
import com.automationpractice.utility.ConfigReader;
import com.automationpractice.utility.ReportManager;
import com.automationpractice.utility.ScreenshotUtility;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Log;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;

public class BaseTest {
    
    // Declare the logger
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    
    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static Properties loc  = new Properties();
    public static Properties cont = new Properties();
    public static FileReader fr;
    public static FileReader fr1;
    public static FileReader fr2;

//    @Parameters({"browser"})
//    @BeforeTest
//    public void setUp(String browser) throws IOException {
////    	 if (browser == null || browser.isEmpty()) {
////             
////             browser = "chrome";
////         }
//    	 
//        logger.info("setUp Started");
//        
//        if (driver == null) {
//            String configFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties";
//            String locatorsFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config\\locators.properties";
//            String contanteFilePath = System.getProperty("user.dir")+ "\\src\\test\\resources\\config\\content.properties";
//            
//            File configFile = new File(configFilePath);
//            File locatorsFile = new File(locatorsFilePath);
//            
//            if (!configFile.exists() || !locatorsFile.exists()) {
//                logger.error("Properties files not found at the expected location.");
//                System.exit(1);
//            }
//
//            try {
//                fr = new FileReader(configFilePath);
//                fr1 = new FileReader(locatorsFilePath);
//                prop.load(fr);
//                loc.load(fr1);
//            } catch (IOException e) {
//                logger.error("Error reading properties files.", e);
//                System.exit(1);
//            }
//        }
//        
////       String browser = prop.getProperty("browser");
////        if (browser == null) {
////            logger.error("Browser not specified in config.properties");
////            System.exit(1);
////        }
//
//        
//        // Handle different browser configurations
//        if (browser.equalsIgnoreCase("chrome")) {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            driver.get(loc.getProperty("main-Url"));
//            logger.info("Chrome browser launched and navigated to URL.");
//            
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
//            logger.info("Page is loaded");
//            
//        } else if (browser.equalsIgnoreCase("firefox")) {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//            driver.get(loc.getProperty("main-Url"));
//            logger.info("Firefox browser launched and navigated to URL.");
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
//            logger.info("Page is loaded");
//        }
//        
//        else if (browser.equalsIgnoreCase("edge"))
//        {
//        	WebDriverManager.edgedriver().setup();
//        	driver = new EdgeDriver();
//        	 driver.get(loc.getProperty("main-Url"));
//        	 logger.info("Edge browser is launched and navigate to URL.");
//        	 JavascriptExecutor js = (JavascriptExecutor) driver;
//	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//	            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
//	            logger.info("Page is loaded");
//        }
//        
//        else {
//            logger.error("Unsupported browser specified: " + browser);
//            System.exit(1);
//        }
//    }
//
//    @AfterTest
//    public void tearDown() {
//    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        if (driver != null) {
//            driver.quit();  // Properly quit the driver after test
//            logger.info("Browser driver closed.");
//        }
//    }
    
    
    
    
    @BeforeSuite
	public void configuringLog4j() throws IOException {
		AllureConfigurator.configure();
	}

	@BeforeMethod
	public void browserSetup() {
		launchBrowser(ConfigReader.getProperty("appUrl"));
	}

	@AfterMethod (alwaysRun = true)
	public void browserTeardown(ITestResult result) {
		WebDriver driver = DriverManager.getInstance().getDriver();
		String testCaseName = result.getMethod().getConstructorOrMethod().getName();
		if(result.getStatus() == ITestResult.FAILURE) {
			try {
				saveTextLog(testCaseName+" Failed, Please find the attached screenshot");
				saveScreenshot(driver);	
				String imageFilePath = ScreenshotUtility.takeFullScreenShot(driver, testCaseName+"_Failed");
				ReportManager.getTest().error("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(imageFilePath).build());
			} catch (IOException e) {
				e.printStackTrace();
				Log.error("Error occured while attaching screenshot: "+e.getMessage());
			}
		}
		DriverManager.getInstance().getEventFiringWebDriver().unregister(DriverManager.getInstance().getWebDriverListener());
		closeBrowser(driver);
	}

	public WebDriver launchBrowser(String url){
		System.out.println("Launching Browser.");
		WebDriverManager.chromedriver().arch32().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-infobars");
		option.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(option);
		EventFiringWebDriver eventHandler = new EventFiringWebDriver(driver);
		WebDriverListener listener = new WebDriverListener();
		eventHandler.register(listener);
		driver = eventHandler;
		DriverManager.getInstance().setWebDriverListener(listener);
		DriverManager.getInstance().setEventFiringWebDriver(eventHandler);
		DriverManager.getInstance().setDriver(driver);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigReader.getProperty("implicitlyWaitTime")),TimeUnit.SECONDS);
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
