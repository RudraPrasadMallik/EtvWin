package com.etvwin.pageobjects;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.etvwin.utility.ElementOperations;
import com.etvwin.utility.GlobalMethods;
import com.etvwin.utility.Log;


public class HomePageObject {
	private WebDriver driver;
    private ElementOperations elementOps;
	
  public HomePageObject(WebDriver driver) {
	  this.driver = driver;
	  PageFactory.initElements(new  AjaxElementLocatorFactory(driver,10), this);
	  this.elementOps = new ElementOperations(driver);  
  }
  

  
  @FindBy(xpath="//a[@class='nav-link']")
  private WebElement tabsOnNavbar;
  
  @FindBy(xpath="//*[name()='path' and contains(@d,'M301.1 34.')]")
  private WebElement audioToggle;
  
  @FindBy(xpath="//div[@class='bannerDescriptionList imagetitle']//div[@class='watchbitton']")//div[@class='watchbutton-content d-flex']
  private WebElement watchNowButtonOnCarasoul;
  
  //
  
  @FindBy(css =".logo-slider")
  private WebElement logoSliderHome;
  
  @FindBy(css = ".logo-slider-inner img.logo")
  private List<WebElement> logoImages;
  
  @FindBy(xpath="//*[name()='path' and contains(@d,'M8.72 18.7')]")
  private WebElement forwardItem;
  
  @FindBy(xpath="//*[name()='path' and contains(@d,'M15.28 5.2')]")
  private WebElement backwordItem;  
  
  
  //
  
   @FindBy(xpath="//div[@class='footer']//ul")
   private WebElement footertab;
	
   @FindBy(xpath="//h2[normalize-space()]")
   private List<WebElement> sectionTitles;
	
   @FindBy(xpath="//a[@class='more-heading']")
   private  List<WebElement> moreButtons;
	
   @FindBy(xpath = "//div[contains(@class,'content-item')]")
   private List<WebElement> contentItems;
   
      
   
   public int getItemsOnNavBar() {  
	   
	return 0;//"These are the items in NavBar"+ tabsOnNavbar.get().getText();
	   
   }
   
//   public void clickAudioToggle() {
//	    elementOps.waitForElementToBeClickable(audioToggle);
//	    audioToggle.click(); 
//	}
   
     
   public void videopayerCarasoul(boolean pause, boolean mute, boolean waitToPlay) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    Boolean videoExists = (Boolean) js.executeScript("return !!document.querySelector('video');");
	    if (!videoExists) {
	        Log.info("No video is  found on the Home page carsoul.");
	        return;
	    }

	    if (waitToPlay) {  
	        // Wait until video starts playing
	    	
	        new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
	            Object currentTime = js.executeScript("return document.querySelector('video').currentTime;");
	            return currentTime instanceof Number && ((Number) currentTime).doubleValue() > 0;
	        });
	        Log.info("Video started playing.");
	        
	        try {
	        	elementOps.waitForElementToBeClickable(audioToggle);
	             audioToggle.click();
	            Log.info("Audio toggle button clicked.");
	        } catch (Exception e) {
	            Log.info("Audio toggle button not found or not clickable & This is the exception.."+e);
	        }

	        
//	        hovering on Watch Now 
	        try {
	            elementOps.waitForvisibilityOfElement(watchNowButtonOnCarasoul);
	            Actions action = new Actions(driver);
	            action.moveToElement(watchNowButtonOnCarasoul).perform();
	            Log.info("Hovering On WatchNow Button On Carasoul");
	        } catch(Exception e) {
	            Log.error(e + " Not able to hover on Watch Now on Home.");
	        }
	        //here need to take screen shot

	        
	        // Wait until video ends
	        new WebDriverWait(driver, Duration.ofMinutes(5)).until(d -> {
	            Object ended = js.executeScript("return document.querySelector('video').ended;");
	            return ended instanceof Boolean && (Boolean) ended;
	        });
	        Log.info("Video playback completed.");
	    }

	    if (pause) {
	        js.executeScript("document.querySelector('video').pause();");
	        Log.info("Video was paused.");
	    }

	    if (mute) {
	        js.executeScript("document.querySelector('video').muted = true;");
	        Log.info("Video was mute.");
	    }
	}

   
   
   public void verifyItemsonSlider() {
	   
	   boolean keepClicking = true;
       int previousSize = 0;
       int maxAttempts = 25;
       int attempt = 0;
       
	   JavascriptExecutor js = (JavascriptExecutor) driver;
       Actions actions = new Actions(driver);
        js.executeAsyncScript("arguments[0].scrollIntoView(true);",logoSliderHome);
       
        
        Set<String>  allImageUrls = new HashSet<>();
        
        while(keepClicking && attempt < maxAttempts) {
        	
        	List<WebElement>  images =  logoImages;
        	
        	
            for (WebElement img : images) {
                String src = img.getAttribute("src");
                if (src != null && !src.isEmpty()) {
                    allImageUrls.add(src);
                    Log.info("These are the images" +images);                   
                }
            }
            
            
            
        }
             
   }
   
   
   
   public int getTotalSections() {
       int previousCount = 0;
       int currentCount = sectionTitles.size();
       int maxAttempts = 20; 
       int attempt = 0;

       while (attempt < maxAttempts) {
           GlobalMethods.scrollDown(driver);

           if (!sectionTitles.isEmpty()) {
               elementOps.waitForAllSectionsToLoad(sectionTitles);
           }


           previousCount = currentCount;
           currentCount = sectionTitles.size();
           Log.info("Attempt " + attempt + ": Sections found = " + currentCount);

           if (currentCount == previousCount) {
               if (!GlobalMethods.scrollToBottom(driver)) {
                   break;
               }
               if (!sectionTitles.isEmpty()) {
                   elementOps.waitForAllSectionsToLoad(sectionTitles);
               }
              
               currentCount = sectionTitles.size();
               Log.info("After bottom scroll, sections found = " + currentCount);
           }

           attempt++;
       }

       Log.info("Final number of sections is : " + sectionTitles.size());
       return sectionTitles.size();
   }
   
   
   public String getSectionName(int index) {
	   return  "These are the sections " + sectionTitles.get(index).getText();
   }
	
   
   
   public boolean isMoreButtonAvailable(int index) {
	   return moreButtons.size()>index;
	   
   }
   
   
   public void clickMoreButton(int index) {
       if (isMoreButtonAvailable(index)) {
           moreButtons.get(index).click();
       }
   }
   
   
   public int getTotalContentItems() {
       return contentItems.size();
   }
   
}