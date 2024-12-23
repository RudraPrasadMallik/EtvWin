package testcase;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjectmodel.Home;

public class HomeTest extends BaseTest{

	private static final Logger logger = LogManager.getLogger(HomeTest.class);
	
	 Home homePage;
	 
	 @BeforeMethod
	    public void setUpHomePage() {
	        
	        homePage = new Home(driver);
	    }
	 
	 
	@Test(priority =1)
	public void verifyUrl() {
		
		String url = homePage.getUrl();
	    String 	url1="https://prodweb.etvwin.com:3000/home";
	    logger.info("This is the URL:"+url);
		if(url.equals(url1)) {
			
			logger.info(url1);
		}
		else {
		  logger.error("This Url is not matching");
		}
		
		homePage.maximizeScreen();
	}
	
	
	
//	@Test(priority =2)
	public void verifyHomeWinLogo() {
		 WebElement logo = homePage.winlogoHome();
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));  
		    wait.until(ExpectedConditions.visibilityOf(logo));
	    
	    Assert.assertTrue(logo.isDisplayed(), "Home logo is not displaying.");
	    
	    org.openqa.selenium.Point position = logo.getLocation();
	    int x = position.getX();
	    int y = position.getY();
	    System.out.println("Logo is located at X: " + x + ", Y: " + y);
	    Assert.assertTrue(x >= 0, "Logo X position is invalid.");
	    Assert.assertTrue(y >= 0, "Logo Y position is invalid.");
	}


	
	@Test(priority =3)
	 public void allicontentVisibilityonHome() {
        // Create a list of WebElements to verify dynamically
	
        List<WebElement> elementsToVerify = Arrays.asList(
            homePage.homeTab(),
            homePage.tvShowsTab(),
            homePage.moviesTab(),
            homePage.watchFreeTab(),
            homePage.liveTvTab(),
            homePage.searchButton(),
            homePage.subscribeButton(),
            homePage.loginButton()
        );

        // WebDriverWait to wait for visibility of elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        // Iterate through the list and verify the visibility of each element
        for (WebElement element : elementsToVerify) {
            try {
                // Wait for the element to be visible
                wait.until(ExpectedConditions.visibilityOf(element));
                
                // If visible, log success
                if (element.isDisplayed()) {
                    logger.info("Element is visible: " + element);
                } else {
                    logger.warn("Element is not visible (but is in DOM): " + element);
                }
            } catch (Exception e) {
                // Catch any exception (e.g., element not found, not visible, etc.)
                logger.error("Element not found or not visible: " + element, e);
            }
            
        }
       
	}
	
	@Test(priority=4)
	public void TC004() {
		
		if(homePage.searchButton().isEnabled()) {
			homePage.searchButton().click();
			logger.info("Clicked on Search Button");
		}
		else {
			logger.error("Not able to click on Search");
		}
		
		
	}
	
	
	

}