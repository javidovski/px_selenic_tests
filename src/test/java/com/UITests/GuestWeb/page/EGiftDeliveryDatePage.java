/**
 * 
 */
package com.UITests.GuestWeb.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class EGiftDeliveryDatePage extends Page{

	@FindBy(xpath = "(//*[@value='Add to Cart'])[2]")
	private WebElement addToCart;


	private static final String[] TITLE_WORDS = { "Delivery", "Date" };

	public EGiftDeliveryDatePage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}
	

	public void clickAddToCart() {
		click(addToCart);
	}

}