




package com.qa.opencart.baseutils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	
	private WebDriver driver;
	
	public ElementUtils(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement getElement(By by) {
		return driver.findElement(by);
	}
	
	public WebElement getElementWithWait(By by, int timeOut) {
		return waitVisibilityOfElelement(by, timeOut);

		//return driver.findElement(by);
	}
	
	
	public List<WebElement> getElements(By by) {
		return driver.findElements(by);
	}
	
	public void doSendKeys(By by, String value) {
		WebElement ele = getElement(by);
		ele.clear();
		ele.sendKeys(value);
	}
	
	public void doSendKeysWithWait(By by, String str, int timeOut) {
		getElementWithWait(by, timeOut).sendKeys(str);
	}
	
	
	public void doClick(By by) {
		getElement(by).click();
	}
	
	public void doActionsCick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	
	public String doGetText(By by) {
		return getElement(by).getText();
	}
	
	public void doClickKeysWithWait(By by, int timeOut) {
		getElementWithWait(by, timeOut).click();
	}
	
	//****************Wait ********************
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible on the page
	 * DOM gets loaded before the webpage.
	 * @param locator
	 * @param value
	 * @param timeOut
	 * @return
	 */
	
	public WebElement waitPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.

	 * @param locator
	 * @param timeOut
	 * @return
	 */
	
	public WebElement waitVisibilityOfElelement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public Alert waitForAlert(int timeOut ) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public String alertGetText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void alertAccept(int timeOut) {
		waitForAlert(timeOut).accept();
	}
	public void alertDismiss(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}
	public void alertSendKeys(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);
	}
	
	public String waitForTitleContainsandFetch(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
		
	}
	public String waitForTitleIs(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
		
	}
	public String waitForUrlContainsandFetch(int timeOut, String fraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlContains(fraction));
		return driver.getCurrentUrl();
		
	}
	public void waitForUrlContains(int timeOut, String fraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlContains(fraction));		
	}
	public void waitForUrlToBe(int timeOut, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlToBe(url));		
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param timeOut
	 * @param locator
	 * @return 
	 */
	public List<WebElement> waitforPresenceofElelemts(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. Visibility means that the elements
	 *  are not only displayed but also have a height and width that is greater than 0.
	 * @param timeOut
	 * @param locator
	 * @return 
	 */
	
	public List<WebElement> waitforVisibilotyofElelemts(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	
	public WebDriver waitforFrametoBeAvailale(int timeOut, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		
	}
	
	public void waitforElelemtToBeClickable(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		
	}
	
	public WebElement waitforElelemtToBeReadyToClick(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	public void doClickWithActions(int timeOut, By locator) {
		WebElement ele = waitforElelemtToBeReadyToClick(timeOut,locator);
		Actions actions = new Actions(driver);
		actions.click(ele).build().perform();
		
	}
	public WebElement waitForPresenceOfElelementWithFluentWait(int timeOut, By locator, int interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver> (driver)
									.withTimeout(Duration.ofSeconds(timeOut))
									.ignoring(NoSuchElementException.class)
												.ignoring(StaleElementReferenceException.class)
												.pollingEvery(Duration.ofSeconds(interval))
														.withMessage("......No Such Element Found......");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				
	}
	public void waitAlertPresentFluentWait(int timeOut, int interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver> (driver)
								.withTimeout(Duration.ofSeconds(timeOut))
								.ignoring(NoAlertPresentException.class)
								.pollingEvery(Duration.ofSeconds(interval))
								.withMessage("Alert is not found on the Page.........");
		wait.until(ExpectedConditions.alertIsPresent());
	}
	

}
