/**
 * 
 */
package com.UITests.GuestWeb.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class PurchaseEgiftCardsForPXGenericTestMerchantPage extends Page{

	@FindBy(xpath = "/descendant::img[@class='thumbnail img-med'][4]")
	private WebElement littleNerdsImg;

	@FindBy(id = "manual_value")
	private WebElement manualValueField;

	@FindBy(id = "quantity_input")
	private WebElement quantityInputField;

	@FindBy(css = "input[value='Continue']")
	private WebElement continueButton;

	

	private static final String[] TITLE_WORDS = { "Purchase", "Egift", "Cards", "for", "PX", "Generic", "Test",
			"Merchant" };

	public static PurchaseEgiftCardsForPXGenericTestMerchantPage using(WebDriver driver) {
		return new PurchaseEgiftCardsForPXGenericTestMerchantPage(driver);
	}
	
	public PurchaseEgiftCardsForPXGenericTestMerchantPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	

	public PurchaseEgiftCardsForPXGenericTestMerchantPage clickLittleNerdsImg() {
		click(littleNerdsImg);
		return this;
	}

	public PurchaseEgiftCardsForPXGenericTestMerchantPage setManualValueField(String text) {
		waitFor(manualValueField).clear();
		manualValueField.sendKeys(text);
		return this;
	}

	
	public PurchaseEgiftCardsForPXGenericTestMerchantPage setQuantityInputField(String text) {
		waitFor(quantityInputField).clear();
		quantityInputField.sendKeys(text);
		return this;
	}

	public PurchaseEgiftCardsForPXGenericTestMerchantPage clickContinueButton() {
		click(continueButton);
		return this;
	}

}