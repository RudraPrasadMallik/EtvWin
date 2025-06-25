package com.etvwin.modules;
import org.openqa.selenium.WebDriver;
import com.etvwin.utility.Log;
import com.etvwin.pageobjects.HomePageObject;

	public class HomePageModule {

	    private HomePageObject homePage;

	    public HomePageModule(WebDriver driver) {
	    	
	        homePage = new HomePageObject(driver);
	    }
	    
	    
	    public void  verifySliderBanner() {
	    	
	    	
	    	Log.info("Verifying video player on carasoul...");    
	    	homePage.videopayerCarasoul(true, true, true);
	    	Log.info("Checking video player carasoul Home..");
	    	
//	    	homePage.clickAudioToggle();
//	    	Log.info("Clicking on Soung ON button Home Carasoul..");
	    	
	    }
	    
	    public void verifylogoItemsSliderHime() {
	    	Log.info("Verifying logo Slider on Home carasoul...");
	    	homePage.verifyItemsonSlider();
	    	
	    }
	    
	    

	    public void verifySections() {
	        int totalSections = homePage.getTotalSections();
	        Log.info("Total Sections Found: " + totalSections);

	        for (int i = 0; i < totalSections; i++) {
	            String sectionName = homePage.getSectionName(i);
	            Log.info("Verifying Section: " + sectionName);

	            
//	            if (homePage.isMoreButtonAvailable(i)) {
//	               Log.info("More Button Available for " + sectionName);
//	                homePage.clickMoreButton(i);
//	                // Add validation for more page navigation
//	            } 
//	            else {
//	                Log.info("No More Button for " + sectionName);
//	            }
	        }
	    }
	}
