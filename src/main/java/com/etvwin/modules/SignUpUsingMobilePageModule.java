package com.etvwin.modules;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.etvwin.utility.ConfigReader;
import com.etvwin.pageobjects.SignUpUsingMobilePageObject;


public class SignUpUsingMobilePageModule {
  
	private SignUpUsingMobilePageObject SignUpUsingMobilePageobject;
	
	public SignUpUsingMobilePageModule(WebDriver driver) {
		SignUpUsingMobilePageobject = new SignUpUsingMobilePageObject(driver);
	}

	
	public void signUpMobileNumber(Map<String,String>testDataMap) {
		SignUpUsingMobilePageobject.enterFullName(testDataMap.get(ConfigReader.getProperty("NameColumn")));
		SignUpUsingMobilePageobject.enterMobileNumber(testDataMap.get(ConfigReader.getProperty("MobileNumberColumn")));
		SignUpUsingMobilePageobject.clickCheckBox();
		SignUpUsingMobilePageobject.clickNext();
	}
	
}
