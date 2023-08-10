package com.ibm.excerise.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchCustomerPage {
	public WebDriver driver;
	@FindBy(how = How.ID, using = "SearchEmail")
	WebElement emailSearch;
	@FindBy(how = How.ID, using = "SearchFirstName")
	WebElement firstNameSearch;
	@FindBy(how = How.ID, using = "SearchLastName")
	WebElement txtLastName;
	@FindBy(how = How.ID, using = "search-customers")
	WebElement searchButton;
	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	WebElement searchResultTable;
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;

	public SearchCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setEmail(String email) {
		emailSearch.clear();
		emailSearch.sendKeys(email);
	}

	public void setFirstName(String firstName) {
		firstNameSearch.clear();
		firstNameSearch.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		txtLastName.clear();
		txtLastName.sendKeys(lastName);
	}

	public void clickSearch() {
		searchButton.click();
	}

	public int getNoOfRows() {
		return (tableRows.size());
	}

	public int getNoOfColumns() {
		return (tableColumns.size());
	}

	public boolean searchCustomerByEmail(String email) throws InterruptedException {
		Thread.sleep(5000);
		boolean flag = false;
		for (int i = 1; i <= getNoOfRows(); i++) {
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]")).getText();
			if (emailid.equalsIgnoreCase(email)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean searchCustomerByName(String Name) {
		boolean flag = false;
		for (int i = 1; i <= getNoOfRows(); i++) {
			String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]")).getText();
			if (Name.equalsIgnoreCase(name)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
