package org.automation.test_scripts;

import org.automation.element_repository.BooksPage;
import org.automation.element_repository.FictionExProductPage;
import org.automation.element_repository.WishListPage;
import org.automation.generic_library.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;


public class Books03Test extends BaseTest {
	
	@Test
	public void verify_User_Is_Able_To_Add_Product_To_WishList() 
	{
		home_Page.getBooksLinks().click();
		
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Books","Books page not displayed");
		Reporter.log("Books Page Displayed",true);
		test.log(Status.INFO,"Books Page Displayed");
		
		BooksPage books_Page = new BooksPage(driver);
		String actProductName=books_Page.getFictionExProductName().getText();
		books_Page.getFictionExProductName().click();
		
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Fiction EX","Fiction Ex Product Page not Displayed");
		Reporter.log("Fiction Ex Product Page Displayed",true);
		test.log(Status.INFO, "Fiction Ex Product Page Displayed");
		
		FictionExProductPage product_Page = new FictionExProductPage(driver);
		product_Page.getAddToWishListButton().click();
		
		home_Page.getWishListLink().click();
		
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Wishlist","Wish List Page not Displayed");
		Reporter.log("Wish List Page Displayed",true);
		test.log(Status.INFO, "Wish List Page Displayed");
		
		driver.navigate().refresh();
		
		WishListPage wishList_Page = new WishListPage(driver);
		String expProductName=wishList_Page.getFictionExProduct().getText();
		
		Assert.assertEquals(actProductName, expProductName,"verify_User_Is_Able_To_Add_Product_To_WishList Test Case Fail");
		Reporter.log("verify_User_Is_Able_To_Add_Product_To_WishList Test Case Pass..",true);
		test.log(Status.PASS, "verify_User_Is_Able_To_Add_Product_To_WishList Test Case Pass..");
		test.addScreenCaptureFromPath(utility_Methods.toCaptureScreenShot(driver), "verify_User_Is_Able_To_Add_Product_To_WishList Test Case Pass..");
		
		for(WebElement removeCheckBox : wishList_Page.getAllRemoveCheckBox())
		{
			removeCheckBox.click();
		}
		
		wishList_Page.getUpdateProductButton().click();
	}

}
