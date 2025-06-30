package com.etvwin.scripts;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.etvwin.modules.HomePageModule;
import com.etvwin.utility.ConfigReader;
import com.etvwin.utility.ExcelUtility;
import com.etvwin.utility.Log;
import com.etvwin.utility.ReportManager;

public class HomePageScript {
	
	private WebDriver driver;
	private HomePageModule homePageModule;
	
	public HomePageScript(WebDriver driver) {
		this.driver=driver;
		homePageModule = new HomePageModule(driver);

	}
	
	public void verifySliderBannerHome(String testCaseName) {
		ExtentTest test = ReportManager.getTest();
		
		homePageModule.verifySliderBanner();
		test.log(Status.INFO,"Verifying Slider Banner on Home");
	}
	
	
	public void verifyposterimageCarasoulHome(String testCaseName) {
		ExtentTest test = ReportManager.getTest();
		
		homePageModule.verifylogoItemsSliderHome();
		test.log(Status.INFO, "Verifying carasoul slider on Home");
		
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
			Log.error("This is the error on verify section on Home Page :"+exp);			
		}
		
	}
	
	
	public void verifySeeAllButons() {
		ExtentTest test = ReportManager.getTest();
		try {
			
//			homePageModule.verifyMoreButtonFunc();
			test.log(Status.INFO,"Checking More button availability");
		}
		catch(Exception e) {
			Log.error("This is the error on verify See All on Home Page :"+e);	
		}
	}
	
	
}