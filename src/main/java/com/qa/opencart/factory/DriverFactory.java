package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.qa.opencart.exception.FrameWorkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager options;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver launchBrowser(Properties prop) {
		options = new OptionsManager(prop);
		
		String browser = prop.getProperty("browser").trim();
		
		if(browser.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(options.getChromeOptions());  //this method will tell if incognito, headless in true or false
			tlDriver.set(new ChromeDriver(options.getChromeOptions()));
		}
		else if(browser.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(options.getEdgeOptions());
			tlDriver.set(new EdgeDriver(options.getEdgeOptions()));
		}
		
		else {
			System.out.println("Wrong browser passed...Please input the corerect browser ");
			throw new FrameWorkException("No Browser FOund Exception");
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
		}
	
	/**
	 * get the local thread copy of the driver
	 * @return 
	 */
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
		
	
	public Properties initProp() {
	
		prop=new Properties();
		FileInputStream ip = null;
		//mvn clean install -Denv="qa"
		
		String envName = System.getProperty("env");
		System.out.println("Running Test Cases on Env: " + envName);
		
		try {
		if(envName == null) {
			System.out.println("no env is passed...running tests on QA env");
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		else {
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
				
			case "integ":
				ip = new FileInputStream("./src/test/resources/config/integ.config.properties");
				break;

			default:
				System.out.println("...Wrong env is passed. Not running any tests.........");
				throw new FrameWorkException("Wrong Env is passed..");
				//break;
			}
		}
			}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	
	}
	}
		
	

