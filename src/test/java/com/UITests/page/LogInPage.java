/**
 * 
 */
package com.UITests.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LogInPage extends Page{

	@FindBy(id = "merchant_username_input")
	private WebElement merchantUsernameInputField;

	@FindBy(id = "merchant_password_input")
	private WebElement merchantPasswordInputField;

	@FindBy(css = "input[value='Take me there']")
	private WebElement takeMeThereButton;

	private static final String[] TITLE_WORDS = { "Log", "In" };

	public LogInPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void setMerchantUsernameInputField(String text) {
		waitFor(merchantUsernameInputField).clear();
		merchantUsernameInputField.sendKeys(text);
	}

	public void setMerchantPasswordInputField(String text) {
		waitFor(merchantPasswordInputField).clear();
		merchantPasswordInputField.sendKeys(text);
	}

	public void clickTakeMeThereButton() {
		click(takeMeThereButton);
	}

}