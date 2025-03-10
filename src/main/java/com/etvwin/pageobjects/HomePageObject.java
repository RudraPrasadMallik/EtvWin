package com.etvwin.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class HomePageObject {
	
  public HomePageObject(WebDriver driver) {
	  PageFactory.initElements(new  AjaxElementLocatorFactory(driver,10), this);
  }
	
  // To get all the dynamic sections names
   @FindBy(xpath="//h2[normalize-space()]")
   private List<WebElement> sectionTitles;
	
   // to get all the More buttons
   @FindBy(xpath="//a[@class='more-heading']")
   private  List<WebElement> moreButtons;
	
   
   @FindBy(xpath = "//div[contains(@class,'content-item')]")
   private List<WebElement> contentItems;
   
   // To get the number of sections are available
   public int getTotalSections() {
	   return sectionTitles.size();
   }
   
   //To get all section names
   public String getSectionName(int index) {
	   return sectionTitles.get(index).getText();
   }
	
   // To check wheather more button is available or not
   public boolean isMoreButtonAvailable(int index) {
	   return moreButtons.size()>index;
   }
   
   public void clickMoreButton(int index) {
       if (isMoreButtonAvailable(index)) {
           moreButtons.get(index).click();
       }
   }
	
   // Method to count total content items on each section(if >10 = more button)
   public int getTotalContentItems() {
       return contentItems.size();
   }
	

}
