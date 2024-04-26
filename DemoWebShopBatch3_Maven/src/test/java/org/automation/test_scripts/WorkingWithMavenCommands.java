package org.automation.test_scripts;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WorkingWithMavenCommands {
	
	@Test
	public void testCase() {
		
		String url=System.getProperty("URL");
		String email=System.getProperty("EMAIL");
		
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		System.out.println("-------------------------");
		System.out.println("Title : "+driver.getTitle());
		System.out.println("Email : "+email);
		System.out.println("-------------------------");
		driver.close();
		
		//mvn -Dtest=className test -DURL="value" -DEMAIL="value"
		
	}

}
