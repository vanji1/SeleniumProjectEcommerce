package com.qa.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.Register;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTestClass {
	
	WebDriver driver;
	DriverFactory factory;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchResultPage searchPage;
	protected ProductInfoPage productPage;
	protected SoftAssert softAssert;
	protected Register registerPage;

	
	@BeforeTest
	public void setup() {
       	factory = new DriverFactory();
       	prop = factory.initProp();
       	driver = factory.launchBrowser(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
