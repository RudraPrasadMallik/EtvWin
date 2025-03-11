package com.etvwin.scripts;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.etvwin.modules.HomePageModule;
import com.etvwin.utility.ConfigReader;
import com.etvwin.utility.ExcelUtility;
import com.etvwin.utility.ReportManager;

public class HomePageScript {
	
	private WebDriver driver;
	private HomePageModule homePageModule;
	
	
	
	
	public HomePageScript(WebDriver driver) {
		this.driver=driver;
		homePageModule = new HomePageModule(driver);
		
	}
	
	
	public void verifySectionsOnHomePage(String testCaseName) {
		ExtentTest test = ReportManager.getTest();
		
		try {
		Map<String,String> testDataMap = new ExcelUtility().getData(testCaseName,"ExcelSheetpath",
				ConfigReader.getProperty(""));
		
		homePageModule.verifySections();
		test.log(Status.INFO,"Checking for all available Sections");
			
			
		}
		catch(Exception exp){
			
		}
		
	}
	

}
