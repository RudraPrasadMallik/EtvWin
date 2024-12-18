package pageObjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class Home {
	private WebDriver driver;
	
	 public Home(WebDriver driver) {
	        this.driver = driver;
	    }
	
	 
	 public  String getUrl() {
		  System.out.println("This is HomeTse getUrl");
	        return driver.getCurrentUrl();
	      
	    }
	 
	 public void maximizeScreen() {
	        driver.manage().window().maximize(); // Maximizes the browser window
	        System.out.println("Browser window maximized");
	    }
	 
//	 public void winlogoHome() {
//		 String logoLocator = BaseTest.loc.getProperty("home-logo-url"); 
//		 
//		 WebElement logo = driver.findElement("home-logo-url");
//		 
//	 }
	 
	 
	 

}
