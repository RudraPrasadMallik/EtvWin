package pageObjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import base.BaseTest;


public class Home {
	
	private static final Logger logger = LogManager.getLogger(Home.class);
	private WebDriver driver;
	
	 public Home(WebDriver driver) {
	        this.driver = driver;
	    }
	
	 
	 public  String getUrl() {
		  logger.info("This is HomeTse getUrl");
	        return driver.getCurrentUrl();
	      
	    }
	 
	 public void maximizeScreen() {
	        driver.manage().window().maximize(); // Maximizes the browser window
	        logger.info("Browser window maximized");
	    }
	 
        public WebElement winlogoHome() {
     		 String logoLocator = BaseTest.loc.getProperty("home-logo-link"); 
		 
     		 WebElement logo = driver.findElement(By.cssSelector(logoLocator));
			return logo ;		
     		
     	 }	
        
        
        // Tabs
        
        public WebElement homeTab() {
        	String home = BaseTest.loc.getProperty("home-link");
         WebElement homeTab= driver.findElement(By.xpath(home));
        	
			return  homeTab;
        }
        
        public WebElement tvShowsTab() 
        {
        	String tvshow = BaseTest.loc.getProperty("tvShows-link");
            WebElement tvshowsTab= driver.findElement(By.xpath(tvshow));
            	
    			return tvshowsTab;
            }
        
        public WebElement moviesTab() {
        	String movie = BaseTest.loc.getProperty("movies-linlk");
            WebElement moviesTab= driver.findElement(By.xpath(movie));
            	
    			return moviesTab;
            }
        
        public WebElement watchFreeTab() {
        	
        	String watchfree =BaseTest.loc.getProperty("watchFree-link");
            WebElement watchfreeTab= driver.findElement(By.xpath(watchfree));
            	
    			return watchfreeTab;
            }
        
        public WebElement liveTvTab() {
        	String livetv = BaseTest.loc.getProperty("livetv-link");
            WebElement livetvTab= driver.findElement(By.xpath(livetv));
            	
    			return livetvTab;
            }
        
        
        //Buttons on navbar
        
        public WebElement searchButton() {
        	String search = BaseTest.loc.getProperty("searchbtn-link");
            WebElement searchButton= driver.findElement(By.xpath(search));
            	
    			return searchButton;
            }
        
        public WebElement subscribeButton() {
        	String subscribe = BaseTest.loc.getProperty("subscribebtn-link");
            WebElement subscribebutton = driver.findElement(By.xpath(subscribe));
            	
    			return subscribebutton;
            }
        
        public WebElement loginButton() {
        	String login =BaseTest.loc.getProperty("loginbtn-link");
            WebElement loginbutton = driver.findElement(By.xpath(login));
            	
    			return loginbutton;
            }
	 
        public WebElement searchInput() {
        	 String input = BaseTest.loc.getProperty("search-inputbox");
        	 WebElement search = driver.findElement(By.id(input));
        	 
        	 return search;
        }
        
        public WebElement searchedContent() {
        	String items = BaseTest.loc.getProperty("searched-items");
        	WebElement searchitems = driver.findElement(By.xpath(items));
			return searchitems;
        }
        
        public WebElement searchCancelbtn() {
        	String items = BaseTest.loc.getProperty("search-cancel");
        	WebElement cancelbtn = driver.findElement(By.xpath(items));
        	return cancelbtn;
        	
        }
	 public WebElement yearlyPremiumplan() {
		 String plan = BaseTest.loc.getProperty("premium-plan-year");
		 WebElement yearPremium =  driver.findElement(By.xpath(plan));
		 return yearPremium;
	 }
	 public WebElement monthlyPremiumplan() {
		 String plan = BaseTest.loc.getProperty("premium-plan-month");
		 WebElement monthPremium =  driver.findElement(By.xpath(plan));
		 return monthPremium;
	 }
	 public WebElement youthplan() {
		 String plan = BaseTest.loc.getProperty("premium-plan-month");
		 WebElement youthplan =  driver.findElement(By.xpath(plan));
		 return youthplan;
	 }
	 
	 public WebElement buyNow() {
		 String buy = BaseTest.loc.getProperty("buy-now-btn");
		 WebElement buynowbtn = driver.findElement(By.id(buy));
		 return buynowbtn;
	 }
	 
	 public WebElement loginpage() {
		 String login = BaseTest.loc.getProperty("login-page");
		 WebElement loginpage = driver.findElement(By.xpath(login));
		 return loginpage;
	 }
	 
	 
	 

}
