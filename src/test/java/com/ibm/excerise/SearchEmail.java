package com.ibm.excerise;

import com.ibm.excerise.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchEmail extends BaseTest {

    @Test(dataProvider = "SearchEmail_TestData")
    public void Test_SearchEmail(String userName, String password, String searchEmailText) throws InterruptedException {
        loginPage.login(userName, password);
        logger.info("Login Successful");
        loginPage.verifyApplicationTitle("Dashboard / nopCommerce administration");
        logger.info("Application Title Verified");
        addcustomerPage.clickOnCustomersMenu();
        logger.info("Clicked on Customer menu");
        addcustomerPage.clickOnCustomersMenuItem();
        logger.info("Clikced on customer menu item");
        searchCustomerPage.setEmail(searchEmailText);
        logger.info("Search Email Id is set");
        searchCustomerPage.clickSearch();
        logger.info("Clicked on Search button");
        Assert.assertTrue(searchCustomerPage.searchCustomerByEmail(searchEmailText));
    }

    @DataProvider(name = "SearchEmail_TestData")
    public Object[][] getData() {
        Object[][] data = {{"admin@yourstore.com", "admin", "admin@yourStore.com"}};
        return data;
    }


}
