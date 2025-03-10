package com.etvwin.listner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.etvwin.utility.Log;
import com.etvwin.utility.ConfigReader;
import com.etvwin.utility.ReportManager;



public class Listener implements ITestListener {
	@Override
	public void onFinish(ITestContext result) {
		ReportManager.endTest();
	}

	@Override
	public void onStart(ITestContext result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		String testCaseName = result.getName();
		String status = testCaseName+ConfigReader.getProperty("testCaseFail");	
		Log.error(status);
		Log.endTestCase(testCaseName, "FAILED");	
		ReportManager.getTest().log(Status.FAIL, status+"\n"+result.getThrowable());
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getName();
		String status = testCaseName+ConfigReader.getProperty("testCaseSkipped");
		Log.info(status);
		ReportManager.getTest().log(Status.SKIP, status);
	}

	@Override
	public void onTestStart(ITestResult result) {
		ReportManager.startTest(result.getName());
		Log.startTestCase(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getName();
		String status = testCaseName+ConfigReader.getProperty("testCasePass");
		Log.endTestCase(testCaseName, "PASSED");
		ReportManager.getTest().log(Status.PASS, status);
	}
	
	
	

}
