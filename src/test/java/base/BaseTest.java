package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader fr;
	public static FileReader fr1;
	
	
	
	@BeforeTest
	public void setUp() throws IOException  {
		
		System.out.println("setUp Started");
		
		if(driver == null) {
		
				fr = new FileReader(System.getProperty("D:\\Spring-Tool-Suit2\\Etv-Win\\src\\test\\resources\\config\\conf.properties"));
				fr1 = new FileReader(System.getProperty("D:\\Spring-Tool-Suit2\\Etv-Win\\src\\test\\resources\\config\\locators.properties"));
			   
				
//		String	fr = ("src\\test\\resources\\config\\config.properties");
//	    String fr1  =("src\\test\\resources\\config\\locators.properties");
//			
//				
//				File configFile = new File(fr);
//	            File locatorsFile = new File(fr1);
//	            System.out.println(config);
//	            
//	            if (!configFile.exists() || !locatorsFile.exists()) {
//	                System.out.println("Properties files not found at the expected location.");
//	                System.exit(1);  // Exit the program if files are not found
//	            }
//		
				
			prop.load(fr);
			loc.load(fr1);
		}
		else {
			System.out.println("If bluck in not executed");
		}
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager .chromedriver().setup();
			 driver = new ChromeDriver();
			 driver.get(loc.getProperty("main-Url"));
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			 driver.get(loc.getProperty("main-Url"));
	}		
}
	
	
//	@AfterTest
	public void tearDown() {
		driver.close();
		System.out.println("Driver Closed");
	}

	


}
