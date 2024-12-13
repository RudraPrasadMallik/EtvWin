package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader fr;
	public static FileReader fr1;
	
	@BeforeTest
	public void setUp() throws IOException {
		
		if(driver == null) {
			 fr = new FileReader(System.getProperty("D:\\Spring-Tool-Suit2\\Etv-Win\\src\\test\\resources\\config\\config.properties"));
			 fr1 = new FileReader(System.getProperty("D:\\Spring-Tool-Suit2\\Etv-Win\\src\\test\\resources\\config\\locators.properties"));
		
			prop.load(fr);
			loc.load(fr1);
		}
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager .chromedriver().setup();
			 driver = new ChromeDriver();
			 driver.get(prop.getProperty("testurl"));
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			 driver.get(prop.getProperty("testurl"));
	}		
}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		System.out.println("Driver Closed");
	}

	


}
