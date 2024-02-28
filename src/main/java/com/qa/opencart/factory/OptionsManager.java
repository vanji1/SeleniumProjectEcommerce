
package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	
	
	private ChromeOptions co; 
	private Properties prop;
	private EdgeOptions eo; 
	private FirefoxOptions fo; 
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	
	public ChromeOptions getChromeOptions() {
		
		co = new ChromeOptions();
		
		if(prop.getProperty("headless").equals("true")){
			co.addArguments("--headless");}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {   // here we convert the string to boolean, another way of writing than previous line
			co.addArguments("--incognito");}
		return co;			
		}
	
	public EdgeOptions getEdgeOptions() {
		
		eo = new EdgeOptions();
		
		if(prop.getProperty("headless").equals("true")){
			eo.addArguments("--headless");}
		if(prop.getProperty("incognito").equals("true")) {
			eo.addArguments("--incognito");}
		return eo;			
		}
	
	public FirefoxOptions getFirefoxOptions() {
		
		fo = new FirefoxOptions();
				
		if(prop.getProperty("headless").equals("true")){
			fo.addArguments("--headless");}
		if(prop.getProperty("incognito").equals("true")) {
			fo.addArguments("--incognito");}
		return fo;			
		}
		
	}

