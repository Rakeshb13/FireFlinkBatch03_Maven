package org.automation.test_scripts;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.automation.generic_library.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DigitalDownloads01Test extends BaseTest {

	@Test(groups = "Integration")
	public void verify_User_Is_Able_To_Add_Product_To_Cart() throws IOException, InterruptedException {

		// Step 6 : To navigate to Digital downloads
		driver.findElement(By.partialLinkText("Digital downloads")).click();

		// Step 7 : Validate Digital downloads page is displayed or not
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Digital downloads",
				"Digital downloads page is not displayed");
		Reporter.log("Digital downloads page is displayed", true);

		// Step 8: Located all AddToCart buttons
		List<WebElement> allProducts = driver.findElements(By.xpath("//input[@value='Add to cart']"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Step 9: To Add all the products to the cart
		for (WebElement product : allProducts) {
			product.click();
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//a[text()='shopping cart']"))));
			Thread.sleep(1000);
		}

		// Step 10: To navigate to shopping cart
		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();

		// Step 11: validate shopping cart page is displayed or not
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Shopping Cart", "Shopping cart page not displayed");
		Reporter.log("Shopping Cart page displayed", true);

		List<WebElement> cartProducts = driver.findElements(By.xpath("//tr[@class='cart-item-row']"));

		// Step 12: Validate the number of products in shopping cart
		Assert.assertEquals(allProducts.size(), cartProducts.size(), "AddToCart Test Case Fail");
		Reporter.log("AddToCart Test Case Pass", true);

		List<WebElement> allRemoveCheckBox = driver
				.findElements(By.xpath("//tr[@class='cart-item-row']//input[@type='checkbox']"));

		for (WebElement removeCheckBox : allRemoveCheckBox) {
			removeCheckBox.click();
		}

		driver.findElement(By.xpath("//input[@value='Update shopping cart']")).click();

	}
}
