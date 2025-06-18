package com.etvwin.listner;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import com.etvwin.utility.ElementOperations;
import com.etvwin.utility.Log;
import com.etvwin.utility.ElementOperations;

public class WebDriverEventHandler implements WebDriverListener {
	private String value;

	public void afterAlertAccept(WebDriver driver) {
		Log.info("Accepted Alert");
	}

	public void afterAlertDismiss(WebDriver driver) {
		Log.info("Dismissed Alert");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		value = element.toString();
		Log.info("Entered value into ["+value.substring(value.indexOf(">")+2));
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		
	}
	public void afterClick(WebElement element, WebDriver driver) {
		
	}	


	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		
	}

	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		Log.info("Screen shot was taken successfully.");		
	}

	public void afterGetText(WebElement element, WebDriver driver, String text) {
		new ElementOperations(driver).highlightElement(element);
		Log.info("Captured Text message: "+text);
	}

	public void afterNavigateBack(WebDriver driver) {
		Log.info("Navigated back to previous page "+driver.getCurrentUrl());	
	}

	public void afterNavigateForward(WebDriver driver) {
		Log.info("Navigated back to forward "+driver.getCurrentUrl());		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		Log.info("Refreshed the current page");
	}

	public void afterNavigateTo(String url, WebDriver driver) {}

	public void afterNavigateTo(WebDriver driver, String url) {

		System.out.println("Navigated to: "+url);		
	}

	public void afterScript(String text, WebDriver driver) {
		
	}

	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		Log.info("Switched to new Window: "+windowName);
	}

	
	
	public void beforeAlertAccept(WebDriver driver) {
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
	
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		Log.info("The searching for webelement using "+ by.toString() + " has started.");
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		
	}

	public void beforeGetText(WebElement element, WebDriver driver) {
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		Log.info("Navigating TO Next Page");
		
	}

	
	public void beforeNavigateTo(String url, WebDriver driver) {
		
	}

	
	public void beforeScript(String text, WebDriver driver) {		
		
	}

	
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		Log.info("Current Window: "+windowName);
	}

	
	public void onException(Throwable throwable, WebDriver driver) {
		Log.error("Exception occured: ",throwable);		
	}

}
