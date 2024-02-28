package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.baseutils.ElementUtils;
import com.qa.opencart.constants.AppConstants;

public class LoginPage {
	
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	//1.private By locators
		
	private By loginid = By.name("email");
	private By loginpwd = By.id("input-password"); 
	private By forgotPassword = By.xpath("//a[text()='Forgotten Password']");
	private By loginBtn = By.cssSelector("input.btn.btn-primary");
	private By seachBox = By.name("search");
	private By myAccount = By.xpath("//h5[text()='My Account']");
	private By aboutUs = By.xpath("//h5[text()='Information']/following::li/a[text()='About Us']");
	
	private By registerLink = By.linkText("Register");

	//2.page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}
	
	//3.page actions/Methods
	
	//here private variables used in public methods - good ex for encapsulation
	//we dont want other pages to access the locators
	//they will have to access the methods not tghe private variables
	
	public boolean isSearchPresent() {
		return eleUtil.waitVisibilityOfElelement(seachBox, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		//	return driver.findElement(seachBox).isDisplayed();
	}
	
	public String GetTitle() {
		String title = eleUtil.waitForTitleContainsandFetch(AppConstants.DEFAULT_MEDIUM_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE);
		//String title = driver.getTitle();
		return title;
	}
	
	public boolean isForgotPwdLinkPresent() {
		return eleUtil.waitVisibilityOfElelement(forgotPassword, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
//		return driver.findElement(forgotPassword).isDisplayed();
	}
	
	public String checkCurrentUrl() {
		String url =  eleUtil.waitForUrlContainsandFetch(AppConstants.DEFAULT_MEDIUM_TIMEOUT, "account");
		return url;
		//return driver.getCurrentUrl();

	}
	
	public boolean IsAboutUsPresent() {
		return eleUtil.waitVisibilityOfElelement(aboutUs,AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		//return driver.findElement(aboutUs).isDisplayed();
	}
	
	public boolean IsMyAccountPresent() {
		return eleUtil.waitVisibilityOfElelement(myAccount, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		//return driver.findElement(myAccount).isDisplayed();
	}
			
	public AccountsPage doLogin(String uname, String pwd) {
		
		
		eleUtil.waitVisibilityOfElelement(loginid, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(uname);
		eleUtil.doSendKeys(loginpwd, pwd);
		eleUtil.doClick(loginBtn);
		
	   //driver.findElement(loginid).sendKeys(uname);
	   //driver.findElement(loginpwd).sendKeys(pwd);
  	  //driver.findElement(loginBtn).click();
		return new AccountsPage(driver);
	}
	
	public Register navigateToRegister() {
		eleUtil.doClick(registerLink);
		return new Register(driver);
	}
	
	
}

