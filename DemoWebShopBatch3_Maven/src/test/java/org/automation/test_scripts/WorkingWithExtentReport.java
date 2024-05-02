package org.automation.test_scripts;

import java.time.Duration;
import java.time.LocalDateTime;

import org.automation.generic_library.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class WorkingWithExtentReport {
	
	
	public static void main(String[] args) {
		
		UtilityMethods um=new UtilityMethods();
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./Reports/"+um.getLocalTime()+".html");
		ExtentReports report = new ExtentReports();
		report.attachReporter(sparkReporter);
		
		ExtentTest test = report.createTest("Royal Enfield Test Case");
		
		WebDriver driver = new ChromeDriver();
		test.log(Status.INFO, "Browser Launched");
		
		driver.manage().window().maximize();
		test.log(Status.INFO, "Browser maximized");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.royalenfield.com/in/en/home/");
		test.log(Status.PASS, "Royal Enfield Test Case Pass...");
		
		test.addScreenCaptureFromPath(um.toCaptureScreenShot(driver), "Royal Enfield Test Case Pass...");
		
		driver.close();
		test.log(Status.INFO, "Browser closed");
		
		test= report.createTest("KTM Test Case");
		driver = new ChromeDriver();
		test.log(Status.INFO, "Browser Launched");
		
		driver.manage().window().maximize();
		test.log(Status.INFO, "Browser maximized");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.ktmindia.com/");
		test.log(Status.PASS, "KTM Test Case Pass...");
		
		test.addScreenCaptureFromPath(um.toCaptureScreenShot(driver), "KTM Test Case Pass...");
		
		driver.close();
		test.log(Status.INFO, "Browser closed");
		
		report.flush();
		

	}
}
