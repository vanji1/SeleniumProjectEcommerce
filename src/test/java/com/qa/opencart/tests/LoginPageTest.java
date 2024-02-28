package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.basetest.BaseTestClass;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest  extends BaseTestClass {
	
	@Test
	public void getTitleTest() {
		
		String actualTitle = loginPage.GetTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
		
			}
	@Test
	public void isSearchPresentTest() {
		Assert.assertTrue(loginPage.isSearchPresent());
	}
	@Test
	public void isMyAccountPresent() {
		Assert.assertTrue(loginPage.IsMyAccountPresent());
	}

	@Test
	public void checkCurrentUrlTest() {
		String URL = loginPage.checkCurrentUrl();
		Assert.assertTrue(URL.contains(AppConstants.LOGIN_FRACTION_URL));
		
	}
	
	@Test
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountsPage.isSearchPresent());
	}

}
