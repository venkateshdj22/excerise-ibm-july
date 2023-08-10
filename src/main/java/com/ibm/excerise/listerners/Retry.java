package com.ibm.excerise.listerners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int counter = 0;
    int retryCount = 2;

    public boolean retry(ITestResult result) {
        if (counter < retryCount) {
            counter++;
            return true;
        }
        return false;
    }
}