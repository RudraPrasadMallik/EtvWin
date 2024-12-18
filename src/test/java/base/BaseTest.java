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

package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static Properties loc = new Properties();
    public static FileReader fr;
    public static FileReader fr1;
    
    @BeforeTest
    public void setUp() throws IOException {
        
        System.out.println("setUp Started");
        
        if (driver == null) {
            // Using relative paths instead of hardcoded absolute paths
            String configFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties";
            String locatorsFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config\\locators.properties";
            
            File configFile = new File(configFilePath);
            File locatorsFile = new File(locatorsFilePath);
            
            if (!configFile.exists() || !locatorsFile.exists()) {
                System.out.println("Properties files not found at the expected location.");
                System.exit(1);  // Exit the program if files are not found
            }
            
            // Load the properties files
            try {
                fr = new FileReader(configFilePath);
                fr1 = new FileReader(locatorsFilePath);
                prop.load(fr);
                loc.load(fr1);
            } catch (IOException e) {
                System.out.println("Error reading properties files.");
                e.printStackTrace();
                System.exit(1);  // Exit if error in reading properties
            }
        }
        
        // Dynamically handle browser configuration
        String browser = prop.getProperty("browser");
        if (browser == null) {
            System.out.println("Browser not specified in config.properties");
            System.exit(1);
        }
        
        // Handle different browser configurations
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(loc.getProperty("main-Url"));  // Ensure this URL exists in locators.properties
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(loc.getProperty("main-url"));  // Ensure this URL exists in locators.properties
        } else {
            System.out.println("Unsupported browser specified: " + browser);
            System.exit(1);
        }
    }
    
    // Cleanup after test
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Properly quit the driver after test
        }
        System.out.println("Driver Closed");
    }
}







