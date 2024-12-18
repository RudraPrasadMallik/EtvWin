package pageObjectmodel;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home {
	private WebDriver driver;
	
	 public Home(WebDriver driver) {
	        this.driver = driver;
	    }
	
	 public  String getUrl() {
		  System.out.println("This is HomeTse getUrl");
	        return driver.getCurrentUrl();
	      
	    }

}
