package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchResult;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.baseutils.ElementUtils;
import com.qa.opencart.constants.AppConstants;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);
	}
	
	//Private By locators
	
	By searchName = By.name("search");
	By searchButton = By.cssSelector("span button[type='button']");
	By accountHeaders = By.cssSelector("div h2");
	
	
	//public Methods
	
	public boolean isSearchPresent() {
		return eleUtil.waitVisibilityOfElelement(searchName, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		//return driver.findElement(searchName).isDisplayed();
	}

	public String getTitle() {
		String title = eleUtil.waitForTitleContainsandFetch(AppConstants.DEFAULT_MEDIUM_TIMEOUT, AppConstants.ACCOUNT_PAGE_TITLE);
		//String title = driver.getTitle();
		return title;
	}
	
	public String checkCurrentUrl() {
		
		return eleUtil.waitForUrlContainsandFetch(AppConstants.DEFAULT_MEDIUM_TIMEOUT, AppConstants.ACCOUNT_FRACTION_URL);
		//	return driver.getCurrentUrl();

	}
	
	public List<String> getAccountHeaders() {
		
		List<String> accountHeaderStringList = new ArrayList<String>();		
		List<WebElement> accountHeadersList = eleUtil.waitforVisibilotyofElelemts(AppConstants.DEFAULT_MEDIUM_TIMEOUT, accountHeaders);
		//List<WebElement> accountHeadersList = driver.findElements(accountHeaders);

		for(WebElement e:accountHeadersList)
			accountHeaderStringList.add(e.getText());
		
		for(String s:accountHeaderStringList)
			System.out.println(s);
		
		return accountHeaderStringList;				
	}
	
	public SearchResultPage performSearch(String str) {
		if (isSearchPresent()) {
			eleUtil.doSendKeys(searchName, str);
			eleUtil.doClick(searchButton);
			return new SearchResultPage(driver);
		
			}
		else
			return null;
			
		}
	
}
