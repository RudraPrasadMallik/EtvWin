package com.etvwin.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class GlobalMethods {

    public static void scrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Log.info("Scrolling down");
        js.executeScript("window.scrollBy(0, 300);");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long previousHeight = (Long) js.executeScript("return document.body.scrollHeight");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long currentHeight = (Long) js.executeScript("return document.body.scrollHeight");
        return !previousHeight.equals(currentHeight);
    }
    
    public static String  getCurrentPage(WebDriver driver) {
    	
		return driver.getTitle();    	
    }
}