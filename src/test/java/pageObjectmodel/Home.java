package pageObjectmodel;

import org.openqa.selenium.By;
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
	 
        public WebElement winlogoHome() {
     		 String logoLocator = BaseTest.loc.getProperty("home-logo-link"); 
		 
     		 WebElement logo = driver.findElement(By.cssSelector(logoLocator));
			return logo ;		
     		
     	 }	
        
        // Tabs
        
        public By homeTab() {
        WebElement homeTab= driver.findElement(By.xpath("home-link"));
        	
			return (By) homeTab;
        }
        
        public WebElement tvShowsTab() {
            WebElement tvshowsTab= driver.findElement(By.xpath("tvShows-link"));
            	
    			return tvshowsTab;
            }
        
        public WebElement moviesTab() {
            WebElement moviesTab= driver.findElement(By.xpath("movies-linlk"));
            	
    			return moviesTab;
            }
        
        public WebElement watchFreeTab() {
            WebElement watchfreeTab= driver.findElement(By.xpath("watchFree-link"));
            	
    			return watchfreeTab;
            }
        public WebElement liveTvTab() {
            WebElement livetvTab= driver.findElement(By.xpath("livetv-link"));
            	
    			return livetvTab;
            }
        
        //Buttons on navbar
        
        public WebElement searchButton() {
            WebElement searchButton= driver.findElement(By.xpath("searchbtn-link"));
            	
    			return searchButton;
            }
        
        public WebElement subscribeButton() {
            WebElement subscribebutton = driver.findElement(By.xpath("subscribebtn-link"));
            	
    			return subscribebutton;
            }
        public WebElement loginButton() {
            WebElement loginbutton = driver.findElement(By.xpath("loginbtn-link"));
            	
    			return loginbutton;
            }
	 
	 

}
