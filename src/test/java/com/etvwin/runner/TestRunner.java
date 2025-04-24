package com.etvwin.runner;

import org.testng.annotations.Test;

import com.automation.remarks.video.annotations.Video;
import com.etvwin.scripts.HomePageScript;
import com.etvwin.utility.DriverManager;

import io.qameta.allure.Description;
import io.qameta.allure.Story;

public class TestRunner extends BaseTest {
	
	//@Severity(SeverityLevel.BLOCKER)
	@Video
	@Description("Test to check items available on Home Page.")
	@Story("Verify Home Page")
	@Test(priority = 1)
	public void Tc001_VerifyHomePAge() {
		String testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		System.out.println("This is inside Testcase1");

		HomePageScript homePageScript = new HomePageScript(DriverManager.getInstance().getDriver());
		homePageScript.verifySectionsOnHomePage(testCaseName);
		
		
	}
	

}