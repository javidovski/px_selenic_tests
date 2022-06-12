package com.UITests.GuestWeb.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;

public class GiftCardOrderPage extends Page {

	@FindBy(xpath="//span[text()='Order a Gift Card']")
	private WebElement header;
	
	@FindBy(xpath="//div[text()='Personalization']")
	private WebElement personalizationText;
	
	@FindBy(xpath="//div[text()='Shipping Input']")
	private WebElement shippingInputText;
	
	@FindBy(xpath="//div[text()='Shipping Method']")
	private WebElement shippingMethodText;
	
private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Gift", "Card", "Order" };
	
	public GiftCardOrderPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public boolean headerVisible() {
		return elementIsVisible(header, 5000, 2000);
	}
	
	public boolean personalizationTextVisible() {
		return elementIsVisible(personalizationText, 5000, 2000);
	}
	
	public boolean shippingInputTextVisible() {
		return elementIsVisible(shippingInputText, 5000, 2000);
	}
	
	public boolean shippingMethodTextVisible() {
		return elementIsVisible(shippingMethodText, 5000, 2000);
	}
	
	
}
