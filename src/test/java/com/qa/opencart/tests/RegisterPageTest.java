package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.basetest.BaseTestClass;
import com.qa.opencart.baseutils.ExcelUtil;
import com.qa.opencart.constants.AppConstants;

public class RegisterPageTest extends BaseTestClass {
	
	@BeforeClass
	public void registerPageSetup() {
		registerPage = loginPage.navigateToRegister();
	}
	
	public String getRandonEmail() {
		//Random random = new Random();
		//String email = "auto"+random.nextInt(1000)+"@gmail.com";
		String email = "auto"+System.currentTimeMillis() + "@gmail.com";
		return email;
		
		
	}
	
	@DataProvider
	public Object[][] getProviderData() {
		Object getRegisterData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return getRegisterData;
	}
	
	@Test(dataProvider="getProviderData")
	public void userRegTest(String firstName, String lastName, String telephone, String passWord, String subscriber){
		Assert.assertTrue(registerPage.registerUser(firstName, lastName, getRandonEmail(), telephone, passWord, subscriber));
		
	}
}
