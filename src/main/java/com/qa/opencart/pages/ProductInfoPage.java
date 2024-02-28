package com.qa.opencart.pages;

import org.openqa.selenium.By;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.baseutils.ElementUtils;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtils eleUtil;
	private Map<String,String> map;
	
	private By productInfo = By.cssSelector("div#content h1");
	private By imageCount = By.xpath("//ul[@class='thumbnails']/li");
	private By metaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By priceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.name("quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successMesage = By.cssSelector("div.alert.alert-success");

	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(driver);

	}


	public String getProductHeaderValue() {
		
		String productHeaderValue =  eleUtil.doGetText(productInfo);
		System.out.println("Product header Value is: "+ productHeaderValue);
		return productHeaderValue;
	}

	
	public int getProductImageCount() {
		return eleUtil.waitforVisibilotyofElelemts(AppConstants.DEFAULT_MEDIUM_TIMEOUT, imageCount).size();
	}
	
	/**
	 * This method gives the product Metadata info
	 */
	private void getProductInfoDetails() {
		
		map = new HashMap<String,String>();	
		
		List<WebElement> list = eleUtil.waitforVisibilotyofElelemts(AppConstants.DEFAULT_MEDIUM_TIMEOUT, metaData);
		for (WebElement e: list) {
			String[] text = e.getText().split(":");
			map.put(text[0].trim(), text[1].trim());
		
		}
		System.out.println(map);
			}
	
	/**
	 * This method gives the pricing details
	 */
	
	private void getProductPriceDetails() {
			
		List<WebElement> link = eleUtil.waitforVisibilotyofElelemts(AppConstants.DEFAULT_MEDIUM_TIMEOUT, priceData);
		for(WebElement e:link) {
			String[] text = e.getText().split(":");
			
			if(!(text.length ==2)) 
				map.put("Price", text[0].trim());
			else
				map.put(text[0].trim(), text[1].trim());			
			}
			System.out.println(map);

		}
		
	public Map<String, String> getProductInfo() {
		map = new HashMap<String,String>();
		map.put("productname", getProductHeaderValue().trim());
		getProductInfoDetails();
		getProductPriceDetails();
		System.out.println(map);
		return map;
		
		}
	
	public void enterQuantity(int qty) {
		System.out.println("The product Quantity is: " + qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));

	}
	
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMsg = eleUtil.waitVisibilityOfElelement(successMesage, AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		//StringBuilder sb = new StringBuilder(successMsg);
		String msg = successMsg.substring(0, successMsg.length()-1).replace("\n", "");
		System.out.println("Success Message is : "+ msg);
		return msg;
		
	}
	
	}
	

