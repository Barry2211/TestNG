package org.adactin;

import org.baseclass.DriverUtils;
import org.baseclass.MethodUtils;
import org.baseclass.ProductModels;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AdactinSite extends DriverUtils{
	
	@BeforeSuite
	private void lunch() {
		driverInit(MethodUtils.EDGE);
	}
	
	@Parameters("url")
	@BeforeTest
	private void siteLaunch(String url){
		windowOp(MethodUtils.max);
		urlInit(url);
		driverWait(10);
		
	}
	
	
	@Test
	private void Program() {
		locate(MethodUtils.id, "username").sendKeys("abcde");
		locate(MethodUtils.id, "password").sendKeys("password");
		locate(MethodUtils.id, "login").click();

	}

}
