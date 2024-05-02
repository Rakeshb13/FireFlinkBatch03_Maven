package org.automation.generic_library;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.automation.element_repository.HomePage;
import org.automation.element_repository.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
   //@author [--Rakesh B--]

public class BaseTest implements FrameworkConstant{
	
	public UtilityMethods utility_Methods=new UtilityMethods();
	public DataUtility data_Utility = new DataUtility();
	public static WebDriver driver;
	public HomePage home_Page;
	static ExtentSparkReporter sparkReporter;
	static ExtentReports report;
	public static ExtentTest test;
	
	@BeforeSuite
	public void generateReport() {
		sparkReporter = new ExtentSparkReporter("./Reports/"+utility_Methods.getLocalTime()+".html");
		report = new ExtentReports();
		report.attachReporter(sparkReporter);
	}
	
	@BeforeClass(alwaysRun = true)
	public void launchBrowser() throws IOException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitwait_Time));
		driver.get(data_Utility.getDataFromPropertiesFile("url"));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void performLogin(Method result) throws IOException {
		
		test=report.createTest(result.getName());
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
	
	@AfterSuite
	public void flushReports() {
		report.flush();
	}
}