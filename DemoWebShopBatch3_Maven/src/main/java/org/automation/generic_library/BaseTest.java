package org.automation.generic_library;

import java.io.IOException;
import java.time.Duration;

import org.automation.element_repository.HomePage;
import org.automation.element_repository.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseTest implements FrameworkConstant{
	
	public DataUtility data_Utility = new DataUtility();
	public static WebDriver driver;
	public HomePage home_Page;
	
	@BeforeClass(alwaysRun = true)
	public void launchBrowser() throws IOException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitwait_Time));
		driver.get(data_Utility.getDataFromPropertiesFile("url"));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void performLogin() throws IOException {
		
		home_Page = new HomePage(driver);
		home_Page.getLoginLink().click();
		LoginPage login_Page = new LoginPage(driver);
		login_Page.getEmailTextFeild().sendKeys(data_Utility.getDataFromPropertiesFile("email"));
		login_Page.getPasswordTextFeild().sendKeys(data_Utility.getDataFromPropertiesFile("pwd"));
		login_Page.getLoginButton().click();
	}
	
	@AfterMethod(alwaysRun = true)
	public void performLogout() {
		home_Page.getLogoutLink().click();
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}
}