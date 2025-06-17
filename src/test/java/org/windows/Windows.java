package org.windows;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.baseclass.DriverUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class Windows extends DriverUtils{

	@Test
	public void handling() {
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://adactinhotelapp.com/");
		String homePage = driver.getWindowHandle();
		javascript(newWindow);
		javascript(newWindow);
		Set<String> landingPages =  new LinkedHashSet<>(driver.getWindowHandles());
		List<String> windowList = new ArrayList<>(landingPages);
		driver.switchTo().window(windowList.get(1));
		driver.get("https://www.facebook.com/");
		driver.switchTo().window(windowList.get(2));
		driver.get("https://www.amazon.com/");
	}
}
