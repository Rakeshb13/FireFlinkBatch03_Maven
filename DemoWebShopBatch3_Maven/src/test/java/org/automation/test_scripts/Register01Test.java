package org.automation.test_scripts;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.automation.generic_library.DataUtility;
import org.automation.generic_library.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Register01Test{
	
	@Test(groups = "System",dataProvider = "RegisterTestData")
	public void verify_User_Is_Able_To_Register(String firstName, String lastName, String email, String password, String confirmPassword) {
		
		UtilityMethods utility_Methods = new UtilityMethods();

		// Step 1 : launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demowebshop.tricentis.com/");

		// Step 2: Validate Demo Web Shop page is displayed or not
		if (driver.getTitle().equals("Demo Web Shop")) {
			System.out.println("Demo Web Shop Page Displayed..");
		} else
			System.out.println("Demo Web Shop Page not Displayed..");

		// Step 3: Navigating to Register Page
		driver.findElement(By.partialLinkText("Regis")).click();

		// Step 4: Validate Register page is Displayed or not
		if (driver.getTitle().equals("Demo Web Shop. Register")) {
			System.out.println("Demo Web Shop Page Displayed..");
		} else
			System.out.println("Demo Web Shop Page not Displayed..");

		// Step 5: entering all the inputs and clickin on Register button
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.name("FirstName")).sendKeys(firstName);
		driver.findElement(By.name("LastName")).sendKeys(lastName);
		driver.findElement(By.name("Email")).sendKeys(utility_Methods.getRandomNumber()+email);
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.name("ConfirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.name("register-button")).click();

		// Step 6: fetching the register sucessfull message
		String expText = driver.findElement(By.xpath("//div[@class='result']")).getText();

		// Step 7: Validating Register sucessfull or not
		if (expText.equals("Your registration completed")) {
			System.out.println("Register Test Case Pass");
		} else
			System.out.println("Register Test Case Fail");

		// Step 8: Perform Logout
		driver.findElement(By.linkText("Log out")).click();

		// Step 9: Close the browser
		driver.close();

	}
	
	@DataProvider(name = "RegisterTestData")
	public Object[][] dataSupply() throws EncryptedDocumentException, IOException {
		
		return DataUtility.getMultipleDataFromExcelFile("RegisterTestData");
	}

}
