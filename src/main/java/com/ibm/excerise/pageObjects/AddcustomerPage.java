package com.ibm.excerise.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddcustomerPage {

    public WebDriver driver;
    @FindBy(xpath = "//p[text()[normalize-space()='Customers']]")
    WebElement menuCustomer;
    By customerMenuList_Customer = By.xpath("//p[text()=' Customers']");

    public AddcustomerPage(WebDriver rdriver) {
        driver = rdriver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCustomersMenu() throws InterruptedException {
        menuCustomer.click();
        Thread.sleep(1000);
    }

    public void clickOnCustomersMenuItem() throws InterruptedException {
        driver.findElement(customerMenuList_Customer).click();
        Thread.sleep(1000);
    }

}
