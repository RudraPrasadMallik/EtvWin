package com.etvwin.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.etvwin.utility.ConfigReader;


public class SignUpUsingmailPageObject {
	
	public SignUpUsingmailPageObject(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 
				Integer.parseInt(ConfigReader.getProperty("webDriverWaitTime"))), this);
	}
	
	@FindBy(name = "firstname")
	private WebElement fullName;
	
	@FindBy(name ="email_id")
	private WebElement emailId;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(name="confirmPassword")
	private WebElement confirmpassword;
	
	@FindBy(name= "agreeTerms")
	private WebElement termsconditionCheckbox;
	
	@FindBy(xpath="//button[@class='next-button-color']")
	private WebElement next;
	
	
	
	public void enterFirstName(String fullName) {
		this.fullName.sendKeys(fullName);
	}
	
	public void enteremailId(String emailId) {
		this.emailId.sendKeys(emailId);
	}
	public void enterPassword(String password) {
		this.password.sendKeys(password);
	}
	public void enterconfirmPassword(String confirmpassword) {
		this.confirmpassword.sendKeys(confirmpassword);
	}
	public void clicktermsconditionCheckbox() {
		this.termsconditionCheckbox.click();
	}
	public void clickNext() {
		this.next.click();
	}

}