package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.basetest.BaseTestClass;

public class ProductInfoPageTest extends BaseTestClass {
	


	@BeforeClass
	public void productPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(dataProvider="getSearchTestData")
	public void selectProductTest(String searchKey, String clickProduct) {
		searchPage = accountsPage.performSearch(searchKey);
		if(searchPage.getProductResultCount()>0) {
			productPage = searchPage.selectProduct(clickProduct);
			String actualproductHeaderValue = productPage.getProductHeaderValue();
			Assert.assertEquals(actualproductHeaderValue, clickProduct);
		}
	}
		
	@DataProvider
	public Object[][] getSearchTestData(){
		return new Object[][] {
			{"MacBook","MacBook"},
			{"MacBook","MacBook Air"},
			{"Apple","Apple Cinema 30\""}
		};
	}
	
	@DataProvider
	public Object[][] getSearchTestData1(){
		return new Object[][] {
			{"MacBook","MacBook",5},
			{"MacBook","MacBook Air",4},
			{"Apple","Apple Cinema 30\"",6}
		};
	}
	
	@Test(dataProvider="getSearchTestData1")
	public void getProductImageCountTest(String product, String clickProduct, int count) {
		
		searchPage = accountsPage.performSearch(product);
		productPage = searchPage.selectProduct(clickProduct);
			int actualImageCount = productPage.getProductImageCount();
			Assert.assertEquals(actualImageCount, count);
			}
		
	@Test
	public void getProductInfotest() {
		searchPage = accountsPage.performSearch("MacBook");
		productPage = searchPage.selectProduct("MacBook");
		Map<String,String> map = productPage.getProductInfo();
		softAssert.assertEquals(map.get("Brand"), "Apple");
		softAssert.assertEquals(map.get("Product Code"), "Product 16");
		softAssert.assertEquals(map.get("Price"), "$602.00");
		softAssert.assertEquals(map.get("Ex Tax"), "$500.00");
		softAssert.assertAll();
	}
	
	@Test
	public void addtoCartTest() {
		searchPage = accountsPage.performSearch("MacBook");
		productPage = searchPage.selectProduct("MacBook");
		productPage.enterQuantity(2);
		String actualMsg = productPage.addProductToCart();
		System.out.println(actualMsg);
		softAssert.assertTrue(actualMsg.contains("Success"));
		softAssert.assertTrue(actualMsg.contains("MacBook"));
		softAssert.assertEquals(actualMsg, "Success: You have added MacBook to your shopping cart!");
		softAssert.assertAll();
		
		
	}
	
		
	}
	
