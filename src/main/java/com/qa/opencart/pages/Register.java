package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.baseutils.ElementUtils;
import com.qa.opencart.constants.AppConstants;

public class Register {
	
		
	 private WebDriver driver;
	 private ElementUtils util;
	 	 
	 //By Locators	 
	 private By firstName = By.name("firstname");
	 private By lastName = By.name("lastname");
	 private By emailId = By.cssSelector("input#input-email");
	 private By telephone = By.cssSelector("input[name='telephone']");
	 private By passWord = By.xpath("//input[@id='input-password']");
	 private By confirmPassword = By.name("confirm");
	 
	 private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	 private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	 
	 private By agree = By.name("agree");
	 private By continueBtn = By.cssSelector("input[value='Continue']");

	 private By registerSuccessMessage = By.cssSelector("div#content h1");
	 
	 private By logout = By.linkText("Logout");
	 private By register = By.linkText("Register");
	 
	 public Register(WebDriver driver) {
		 this.driver = driver;
		 util = new ElementUtils(driver);
	 }
	 
	 public boolean registerUser(String firstName, String lastName, String emailId, String telephone, String passWord, String subscriber ) {
		util.waitPresenceOfElement(this.firstName, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(firstName);
		util.doSendKeys(this.lastName, lastName);
		util.doSendKeys(this.emailId, emailId);
		util.doSendKeys(this.telephone, telephone);
		util.doSendKeys(this.passWord, passWord);
		util.doSendKeys(this.confirmPassword, passWord);
		if(subscriber.equalsIgnoreCase("yes")) {
			util.doClick(subscribeYes);
		}
		else
			util.doClick(subscribeNo);
		util.doClick(agree);
		util.doActionsCick(continueBtn);
				
		String successMessage = util.waitVisibilityOfElelement(registerSuccessMessage, AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		System.out.println("USer registration success message is :"+ successMessage); 
		
		if(successMessage.contains(AppConstants.REGISTER_SUCCESS_MESSAGE)) {
			
			util.doClick(logout);
			util.doClick(register);
			return true;
			}
		else
			return false;
					
	 }
	 
	 

}
