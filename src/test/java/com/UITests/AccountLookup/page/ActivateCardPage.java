/**
 * 
 */
package com.UITests.AccountLookup.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class ActivateCardPage extends Page {

	@FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Store:*\"]]/td[2]/select")
	private WebElement storeDropdown;

	@FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Card Number to Activate:*\"]]/td[2]/input")
	private WebElement cardNumberField;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	@FindBy(css = ".pageheader")
	private WebElement pageHeader;


	private static final String[] TITLE_WORDS = { "Activate", "Card" };

	public ActivateCardPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void selectStoreDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(storeDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(storeDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void setCardNumberField(String text) {
		waitFor(cardNumberField).clear();
		cardNumberField.sendKeys(text);
	}

	public String getPageHeader() {
		return waitFor(pageHeader).getText();
	}

	public void clickSubmitButton() {
		click(submitButton);
	}

}