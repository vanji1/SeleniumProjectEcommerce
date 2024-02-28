package com.qa.opencart.pages;

import javax.naming.directory.SearchResult;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.baseutils.ElementUtils;
import com.qa.opencart.constants.AppConstants;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	By productResult = By.cssSelector("div#content div.product-layout" );

	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public int getProductResultCount() {
		int count =  eleUtil.waitforVisibilotyofElelemts(AppConstants.DEFAULT_MEDIUM_TIMEOUT, productResult).size();
		System.out.println("Product Count is is : "+ count);
		return count;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productlocator = By.linkText(productName);
		eleUtil.waitVisibilityOfElelement(productlocator, AppConstants.DEFAULT_MEDIUM_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}

}
