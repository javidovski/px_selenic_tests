/**
 * 
 */
package com.UITests.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SwitchMerchantPage extends Page{

	@FindBy(name = "pxid")
	private WebElement pxidField;

	@FindBy(css = "input[value='Go']")
	private WebElement goButton;


	private static final String[] TITLE_WORDS = { "Switch", "Merchant" };

	public SwitchMerchantPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void setPxidField(String text) {
		waitFor(pxidField).clear();
		pxidField.sendKeys(text);
	}

	public void clickGoButton() {
		click(goButton);
	}

}