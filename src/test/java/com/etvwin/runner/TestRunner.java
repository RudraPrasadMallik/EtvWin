package com.etvwin.runner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.automation.remarks.video.annotations.Video;
import com.etvwin.scripts.HomePageScript;
import com.etvwin.utility.ConfigReader;
import com.etvwin.utility.DriverManager;
import com.etvwin.utility.Log;

import io.qameta.allure.Description;
import io.qameta.allure.Story;

public class TestRunner extends BaseTest {
	
	@Video
	@Description("Test to check Slider banner on Home Page.")
	@Story("Verify Home Page Nav")
	@Test(priority = 1)
	public void  Tc001_VerifyItems_NavBar() {
		String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();	
		Log.info("Inside testCase 1 ");
		HomePageScript homePageScript = new HomePageScript(DriverManager.getInstance().getDriver());
		    homePageScript.verifySliderBannerHome(testCaseName);
		   
	}
	
	

	@Video
	@Description("Test to check Slider banner on Home Page.")
	@Story("Verify Home Page Slider images..")
	@Test(priority = 2)
	public void Tc002_VerifyposterimageonHomeCarasoul() {
		DriverManager.getInstance().getDriver().get(ConfigReader.getProperty("appUrl"));
		 WebDriver driver = DriverManager.getInstance().getDriver();
		Log.info("Current URL: " + driver.getCurrentUrl());

	    // Optional safety: reload home page
	    String appUrl = ConfigReader.getProperty("appUrl");
	    if (!driver.getCurrentUrl().equals(appUrl)) {
	        Log.info("Redirecting to appUrl again: " + appUrl);
	        driver.get(appUrl);
	    }

	    String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	    HomePageScript homePageScript = new HomePageScript(driver);
				homePageScript.verifyposterimageCarasoulHome(testCaseName);
		
	}
	
	
	//@Severity(SeverityLevel.BLOCKER)
	@Video
	@Description("Test to check items available on Home Page.")
	@Story("Verify Home Page")
	@Test(priority = 3)
	public void Tc003_VerifySectionsHome() {
		String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		Log.info("This is inside Testcase3");

		HomePageScript homePageScript = new HomePageScript(DriverManager.getInstance().getDriver());
		homePageScript.verifySectionsOnHomePage(testCaseName);
			
	}
	
	
	@Video
	@Description("Test to check SeeAll button available on Home Page.")
	@Story("Verify Home Page See All Buttons")
	@Test(priority = 4)
	public void Tc004_VerifySeeAllButonHome() {
		String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		Log.info("This is inside Testcase 4");

		HomePageScript homePageScript = new HomePageScript(DriverManager.getInstance().getDriver());
		homePageScript.verifySeeAllButons();
			
	}
	
	   
	
	
	
	
	
	
	
	
}