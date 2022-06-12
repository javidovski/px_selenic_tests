/**
 * 
 */
package com.UITests.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AccountFilterPage extends Page {

	@FindBy(name = "account-filter-label")
	private WebElement accountFilterLabelField;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Save & Continue']")
	private WebElement saveContinueButton;

	@FindBy(id = "start-card-input")
	private WebElement startCardInputField;

	@FindBy(xpath = "/descendant::div[normalize-space(.)='Last Card #']")
	private WebElement lastCard;

	@FindBy(id = "end-card-input")
	private WebElement endCardInputField;

	@FindBy(id = "total-card-input")
	private WebElement totalCardInputField;


	private static final String[] TITLE_WORDS = { "Account", "Filter" };

	public AccountFilterPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void setAccountFilterLabelField(String text) {
		waitFor(accountFilterLabelField).clear();
		accountFilterLabelField.sendKeys(text);
	}

	public void clickSaveContinueButton() {
		click(saveContinueButton);
	}

	public void setStartCardInputField(String text) {
		waitFor(startCardInputField).clear();
		startCardInputField.sendKeys(text);
	}

	public void clickLastCard() {
		click(lastCard);
	}

	public void setEndCardInputField(String text) {
		waitFor(endCardInputField).clear();
		endCardInputField.sendKeys(text);
	}

	public void setTotalCardInputField(String text) {
		waitFor(totalCardInputField).clear();
		totalCardInputField.sendKeys(text);
	}

}