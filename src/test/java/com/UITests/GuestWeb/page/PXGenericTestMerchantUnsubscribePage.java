/**
 * 
 */
package com.UITests.GuestWeb.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class PXGenericTestMerchantUnsubscribePage extends Page {

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Continue']")
	private WebElement continueButton;

	@FindBy(id = "unsubscribe")
	private WebElement unsubscribe;

	@FindBy(id = "submit")
	private WebElement submit;


	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Unsubscribe" };

	
	
	public PXGenericTestMerchantUnsubscribePage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}


	public void clickContinueButton() {
		click(continueButton);
	}

	public void clickUnsubscribe() {
		click(unsubscribe);
	}

	public void clickSubmit() {
		click(submit);
	}


}