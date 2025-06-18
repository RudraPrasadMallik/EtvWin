//package com.etvwin.listner;
//
//import org.testng.IRetryAnalyzer;
//import org.testng.ITestResult;
//
//public class RetryFailedTestCases implements IRetryAnalyzer{
//
//	private int retryCount = 0;
//	private int maxRetryCount = 1;
//	
//	@Override
//	public boolean retry(ITestResult result) {
//		if(retryCount < maxRetryCount) {
//			System.out.println("Retrying the failed test cases.");
//			retryCount++;
//			return true;
//	}
//		return false;
//
//}
//}