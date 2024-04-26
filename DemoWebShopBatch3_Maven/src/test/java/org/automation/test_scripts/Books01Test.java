package org.automation.test_scripts;

import org.automation.generic_library.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Books01Test extends BaseTest {
	
	@Test(groups = "Functionality")
	public void verify_Books_Page_Is_Displayed()
	{
		driver.findElement(By.partialLinkText("Books")).click();
		
		if(driver.getTitle().equals("Demo Web Shop. Books"))
		{
			System.out.println("Books Test Case Pass..");
		}
		else
			System.out.println("Books Test Case Fail..");
		
	}

}
