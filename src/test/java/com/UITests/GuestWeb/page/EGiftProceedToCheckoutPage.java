/**
 * 
 */
package com.UITests.GuestWeb.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class EGiftProceedToCheckoutPage extends Page{

	@FindBy(xpath = "(//*[@value='Checkout'])[2]")
	private WebElement checkout;

	
	private static final String[] TITLE_WORDS = { "Electronic", "Gift", "Card", "Ordering" };

	public EGiftProceedToCheckoutPage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}

	
	public void clickCheckout() {
		click(checkout);
	}

}