package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.basetest.BaseTestClass;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTestClass {
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void  isSearchPresentTest() {
		Assert.assertTrue(accountsPage.isSearchPresent());
	}

	@Test
	public void getTitleTest() {
	String title = 	accountsPage.getTitle();
	Assert.assertEquals(title, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void currentURLTest() {
		String uRL = accountsPage.checkCurrentUrl();
		Assert.assertTrue(uRL.contains(AppConstants.ACCOUNT_FRACTION_URL));
	}
	
	@Test
	public void getAccountHeadersCountTest() {
		List<String> accHeader = accountsPage.getAccountHeaders();
		Assert.assertEquals(accHeader.size(), 4);
	}
	
	@Test
	public void getAccountHeadersValueTest() {
		List<String> accHeader = accountsPage.getAccountHeaders();
		//Assert.assertTrue(accHeader.contains("My Account"));
		System.out.println("Expected Results: "+ accHeader);
		System.out.println("Actual results: "+ AppConstants.ACCOUNT_PAGE_HEADERS_LIST);
		Assert.assertEquals(accHeader, AppConstants.ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	
	
	@Test(dataProvider="getSearchData")
	public void searchProductCountTest(String searchkey) {
		searchPage = accountsPage.performSearch(searchkey);
		int result = searchPage.getProductResultCount();
		Assert.assertTrue(result>0);
		
			}
	

	
@DataProvider
public Object[][] getSearchData(){
	return new Object[][] {
		{"MacBook"},
		{"MacBook Air"},
		{"Apple"}
	};
}
}

