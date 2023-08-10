package com.ibm.excerise.pageObjects;

import com.ibm.excerise.testBase.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage {

	public WebDriver driver;
	@FindBy(id = "Email")
	WebElement textEmail;
	@FindBy(id = "Password")
	WebElement textPassword;
	@FindBy(css = ".button-1")
	WebElement loginButton;
	@FindBy(linkText = "Logout")
	WebElement logoutButton;
	@FindBy(xpath = "//input[@name='RememberMe']")
	WebElement rememberMeCheckBox;

	public LoginPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public void setUserName(String username) {
		textEmail.clear();
		textEmail.sendKeys(username);
	}

	public void setPassword(String password) {
		textPassword.clear();
		textPassword.sendKeys(password);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public void clickLogout() {
		logoutButton.click();
	}

	public void clickRememberCheckbox() {
		rememberMeCheckBox.click();
	}

	public void login(String email, String pass) {
		BaseTest.wait.until(ExpectedConditions.visibilityOf(textEmail));
		setUserName(email);
		setPassword(pass);
		clickRememberCheckbox();
		clickLogin();
	}

	public void verifyApplicationTitle(String expTitle) throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), expTitle);
	}

}
