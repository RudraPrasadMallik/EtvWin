package com.etvwin.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.etvwin.utility.ConfigReader;

public class SignUpUsingMobilePageObject {
	
	public SignUpUsingMobilePageObject(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 
	    Integer.parseInt(ConfigReader.getProperty("mobileNumber"))), this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Full Name*']")
	private WebElement fullName;
	
	@FindBy(xpath="//input[@placeholder='Mobile Number*']")
	private WebElement mobileNumber;
	
	@FindBy(name="agreeTerms")
	private WebElement checkbox;
	
	@FindBy(xpath="//button[@class='next-button-color']")
	private WebElement next;
	
	
	public void enterFullName(String fullName) {
		this.fullName.sendKeys(fullName);
	}
    
	public void enterMobileNumber(String mobileNumber) {
		this.mobileNumber.sendKeys(mobileNumber);
	}
	
	public void clickCheckBox() {
		this.checkbox.click();
	}
	public void clickNext() {
		this.next.click();
	}
	
}
