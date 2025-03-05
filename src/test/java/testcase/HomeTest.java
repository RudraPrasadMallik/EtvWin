package testcase;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.etvwin.runner.BaseTest;

import pageObjectmodel.Global;
import pageObjectmodel.Home;

public class HomeTest extends BaseTest{

	private static final Logger logger = LogManager.getLogger(HomeTest.class);
	
	 Home homePage;
	 
	 @BeforeMethod
	    public void setUpHomePage() 
	 {  
	        homePage = new Home(driver);
	        
	  }
	 
	 
	@Test(priority =1)
	public void verifyUrl() {
		
		String url = homePage.getUrl();
	    String 	url1="https://prodweb.etvwin.com:3000/home";
	    logger.info("This is the URL:"+url);
		if(url.equals(url1)) {
			
			logger.info("Url is: "+url1);
		}
		else {
		  logger.error("This Url is not matching");
		}
		
		homePage.maximizeScreen();
	}
		
	
	@Test(priority =2)
	public void verifyHomeWinLogo() {
		   WebElement logo = homePage.winlogoHome();

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		    wait.until(ExpectedConditions.visibilityOf(logo));

		    try {
		        Assert.assertTrue(logo.isDisplayed(), "Home logo is not displaying.");
		        
		        Global.takescreenshot(driver, "homeLogoVerification");

		        org.openqa.selenium.Point position = logo.getLocation();
		        int x = position.getX();
		        int y = position.getY();
		        System.out.println("Logo is located at X: " + x + ", Y: " + y);
		        Assert.assertTrue(x >= 0, "Logo X position is invalid.");
		        Assert.assertTrue(y >= 0, "Logo Y position is invalid.");
		        
		    } catch (AssertionError e) {
		      
		        logger.error("Logo is not displayed. Taking screenshot.");
		        Global.takescreenshot(driver, "homeLogoNotDisplayed");
		        throw e; 
		    }
		    
	}
	

   	@Test(priority =3)
	 public void allicontentVisibilityonHome() {
    
   		WebElement element = homePage.homeTab();
    		  
   		
//     List<WebElement> elementsToVerify = Arrays.asList(
//            homePage.homeTab(),
//            homePage.tvShowsTab(),
//            homePage.moviesTab(),
//            homePage.watchFreeTab(),
//            homePage.liveTvTab(),
//            homePage.searchButton(),
//            homePage.subscribeButton(),
//            homePage.loginButton()
//        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        Global.takescreenshot(driver, "home-all-TabsPresentVerification.");
        logger.info(" allicontentVisibilityonHome has been executed");
        
      
//        for (WebElement element : elementsToVerify) {
//            try {
//               
//                wait.until(ExpectedConditions.visibilityOf(element));
//                
//               
//                if (element.isDisplayed()) {
//                    logger.info("Element is visible: " + element);
//                } else {
//                    logger.warn("Element is not visible (but is in DOM): " + element);
//                }
//            } catch (Exception e) {
//                
//                logger.error("Element not found or not visible: " + element, e);
//            }   
//        }
        
	}
	 
	 
	//@Test(priority=4)
	public void TC004() {
		 String movieName = BaseTest.prop.getProperty("movieName");
		    
		    WebElement searchButton = homePage.searchButton();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		    
		    // Wait for the search button to be clickable
		    wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		    searchButton.click();
		    logger.info("Clicked on Search Button");
		    WebElement searchInput = homePage.searchInput();
		    searchInput.sendKeys(movieName);
		    logger.info("Entered movie name: " + movieName);
		    
		   logger.info("This is the searched Content:"+homePage.searchedContent());
		
		   homePage.searchCancelbtn().click();
		   logger.info("Clicked on cancel btn on search page");
	}
	
	
	//@Test(priority=5)
	public void TC005() {
	
		    String invalidMovieName = "SomeNonExistentMovie";

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		    
		    WebElement searchInput = homePage.searchInput();
		    wait.until(ExpectedConditions.visibilityOf(searchInput));
		    
		    searchInput.sendKeys(invalidMovieName);
		    logger.info("Entered invalid movie name: " + invalidMovieName);

		    
		    wait.until(ExpectedConditions.visibilityOf(homePage.searchedContent()));
		    
		    String searchResultsText = homePage.searchedContent().getText();
		    logger.info("Search results: " + searchResultsText);
		    
		    // Assert that no results are found or an appropriate message is displayed
		    Assert.assertTrue(searchResultsText.contains("No results found") || searchResultsText.isEmpty(), 
		                      "Invalid search did not show 'no results' message.");
		    
		    // Optionally, cancel the search after the test
		    homePage.searchCancelbtn().click();
		    logger.info("Clicked on cancel button on search page");
		}
	
	
	@Test(priority=6)
	 public void TC006() {
		 WebElement subscribe = homePage.subscribeButton();
		 
		 String homeUrl = BaseTest.loc.getProperty("main-Url");
		   String currentUrl = driver.getCurrentUrl();
		   logger.info(currentUrl);
		   if(currentUrl != homeUrl ) {
			   driver.navigate().to(homeUrl);
		   }
		   
		   
		   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		   
		   wait.until(ExpectedConditions.visibilityOf(subscribe));
		   subscribe.click();
		   logger.info("Clicked on Subscribe");
		   logger.info("current page is :" + driver.getCurrentUrl());
		 
	 }
		
	
	@Test(priority=7)
	public void scrollDown() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		 logger.info("Page is scrolled down");
	}
	
	
	
	
	
	
	
	// @Test(priority =50)
		public void TC007() {
		String plantxt = BaseTest.loc.getProperty("choose-plan-txt");
		  logger.info(plantxt);
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
		}
		
		 
	 
	
	

}