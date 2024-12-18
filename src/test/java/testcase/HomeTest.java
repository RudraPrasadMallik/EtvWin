package testcase;

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
		
	}
	
	
	public void verifyHomeWinLogo() {
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
