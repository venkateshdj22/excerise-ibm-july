package com.ibm.excerise.testBase;

import com.ibm.excerise.pageObjects.AddcustomerPage;
import com.ibm.excerise.pageObjects.LoginPage;
import com.ibm.excerise.pageObjects.SearchCustomerPage;
import com.ibm.excerise.utility.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class BaseTest {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Logger logger;
	public ReadConfig readConfig;

	public LoginPage loginPage;
	public SearchCustomerPage searchCustomerPage;
	public AddcustomerPage addcustomerPage;

	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		logger.info("Taking Screenshoot with name " + screenshotName);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/Screenshots/IBM_Module_" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);

		FileUtils.copyFile(source, finalDestination);
		logger.info("File copied to " + finalDestination);

		return destination;
	}

	@BeforeSuite
	public void setup() throws Exception {
		/***Logger Configuration***/
		logger = Logger.getLogger("nopCommerceDemoRun");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		readConfig = new ReadConfig();

		/***Launching browser***/
		String browser = readConfig.getBrowser();
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("Chrome Browser Initiated");
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("Firefox Browser Initiated");
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			logger.info("Edge Browser Initiated");
		}

		wait = new WebDriverWait(driver, 5000);

		driver.get(readConfig.getApp());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

		loginPage = new LoginPage(driver);
		searchCustomerPage = new SearchCustomerPage(driver);
		addcustomerPage = new AddcustomerPage(driver);
	}

	@AfterSuite
	public void teardown() {
		driver.close();
		logger.info("Browser Closed");
	}


}
