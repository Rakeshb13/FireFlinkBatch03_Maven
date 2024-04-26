package org.automation.test_scripts;

import org.automation.generic_library.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Books02Test extends BaseTest{
	
	@Test(groups = "Functionality")
	public void verfy_Computing_And_Internet_Page_Is_Displayed() {
		
		driver.findElement(By.partialLinkText("Books")).click();
		driver.findElement(By.xpath("//a[text()='Computing and Internet']")).click();
		
		if(driver.getTitle().equals("Demo Web Shop. Computing and Internet"))
		{
			System.out.println("Computing and Internet Test Case Pass..");
		}
		else
			System.out.println("Computing and Internet Test Case Fail..");
		
	}

}
