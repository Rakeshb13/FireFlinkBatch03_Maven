package org.automation.generic_library;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class UtilityMethods implements FrameworkConstant{
	
	public String getLocalTime()
	{
		return LocalDateTime.now().toString().replace(":", "-");
	}
	
	public int getRandomNumber() {
		Random r = new Random();
		return r.nextInt(4000); //0 --- 4000
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	// dismiss //getText --> string

	public void selectOptionByIndex(WebElement dropDown, int indexnum) {
		Select s = new Select(dropDown);
		s.selectByIndex(indexnum);
	}
	// value // visibleText

	public void actionsScrollForCertainPixel(WebDriver driver, int x, int y) {
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y).perform();
	}

	public void jsScrollTillElement(WebDriver driver, boolean value, WebElement target) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(" + value + ")", target);
	}
	
	public String toCaptureScreenShot(WebDriver driver)
	{
		String path=screenshot_Path + getLocalTime() + ".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg= new File(path);
		try {
			FileHandler.copy(src, trg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "."+path;
	}
	

}
