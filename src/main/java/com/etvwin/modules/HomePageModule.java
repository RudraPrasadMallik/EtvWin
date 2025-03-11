	package com.etvwin.modules;

	import com.etvwin.pageobjects.HomePageObject;
	import org.openqa.selenium.WebDriver;

	public class HomePageModule {

	    private HomePageObject homePage;

	    public HomePageModule(WebDriver driver) {
	        homePage = new HomePageObject(driver);
	    }

	    // Method to verify all sections dynamically
	    public void verifySections() {
	        int totalSections = homePage.getTotalSections();
	        System.out.println("Total Sections Found: " + totalSections);

	        for (int i = 0; i < totalSections; i++) {
	            String sectionName = homePage.getSectionName(i);
	            System.out.println("Verifying Section: " + sectionName);

	            
	            if (homePage.isMoreButtonAvailable(i)) {
	                System.out.println("More Button Available for " + sectionName);
	                homePage.clickMoreButton(i);
	                // Add validation for more page navigation
	            } else {
	                System.out.println("No More Button for " + sectionName);
	            }
	        }
	    }
	}
