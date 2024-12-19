package testcase;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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

	 Home homePage;
	 
	 @BeforeMethod
	    public void setUpHomePage() {
	        
	        homePage = new Home(driver);
	    }
	 
	 
	@Test(priority =1)
	public void verifyUrl() {
		
		String url = homePage.getUrl();
	    String 	url1="https://prodweb.etvwin.com:3000/home";
	    System.out.println(url);
		if(url == url1 ) {
			System.out.println("Correct Url");
		}
		else {
			System.out.println("This Url is not matching");
		}
		
		homePage.maximizeScreen();
	}
	
	
	
	@Test(priority =2)
	public void verifyHomeWinLogo() {
		 WebElement logo = homePage.winlogoHome();
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  
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
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // List all the locators for the elements you want to check
		 List<By> locators = Arrays.asList(
		            homePage.homeTab()
//		            homePage.tvShowsTab(),
//		            homePage.moviesTab(),
//		            homePage.watchFreeTab(),
//		            homePage.liveTvTab(),
//		            homePage.searchButton(),
//		            homePage.subscribeButton(),
//		            homePage.loginButton()
		    );

		    // Iterate over the list of locators and check their visibility
		    for (By locator : locators) {
		        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));  // Use By locators here
		        Assert.assertNotNull(element, "Element is not found for locator: " + locator);
		        Assert.assertTrue(element.isDisplayed(), "Element is not displayed for locator: " + locator);
		    }
	
	}
	
	
	
	
	
	

}