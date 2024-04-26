package org.automation.element_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FictionExProductPage {
	
	@FindBy(xpath = "//input[@value='Add to wishlist']")
	private WebElement addToWishListButton;
	
	public FictionExProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getAddToWishListButton() {
		return addToWishListButton;
	}
	
	

}
