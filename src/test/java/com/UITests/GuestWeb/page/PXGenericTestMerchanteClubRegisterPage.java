/**
 * 
 */
package com.UITests.GuestWeb.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class PXGenericTestMerchanteClubRegisterPage extends Page {

	@FindBy(id = "firstName")
	private WebElement firstNameField;

	@FindBy(id = "lastName")
	private WebElement lastNameField;

	@FindBy(id = "country")
	private WebElement countryDropdown;

	@FindBy(id = "postalCode")
	private WebElement postalCodeField;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "favoriteStoreState")
	private WebElement favoriteStoreStateDropdown;

	@FindBy(id = "favoriteStore")
	private WebElement favoriteStoreDropdown;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Join Now!']")
	private WebElement joinNowButton;


	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "eClub", "Register" };

	
	
	public PXGenericTestMerchanteClubRegisterPage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}
	
	public static PXGenericTestMerchanteClubRegisterPage using(WebDriver driver) {
		return new PXGenericTestMerchanteClubRegisterPage(driver);
	}

	

	public PXGenericTestMerchanteClubRegisterPage setFirstNameField(String text) {
		waitFor(firstNameField).clear();
		firstNameField.sendKeys(text);
		return this;
	}

	public PXGenericTestMerchanteClubRegisterPage setLastNameField(String text) {
		waitFor(lastNameField).clear();
		lastNameField.sendKeys(text);
		return this;
	}

	public PXGenericTestMerchanteClubRegisterPage selectCountryDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(countryDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(countryDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public PXGenericTestMerchanteClubRegisterPage setPostalCodeField(String text) {
		waitFor(postalCodeField).clear();
		postalCodeField.sendKeys(text);
		return this;
	}

	public PXGenericTestMerchanteClubRegisterPage setEmailField(String text) {
		waitFor(emailField).clear();
		emailField.sendKeys(text);
		return this;
	}

	public PXGenericTestMerchanteClubRegisterPage selectFavoriteStoreStateDropdown(String text) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(favoriteStoreStateDropdown).getFirstSelectedOption().getText().trim()
				.length() > 0);
		Select dropdown = new Select(favoriteStoreStateDropdown);
		dropdown.selectByVisibleText(text);
		sleep(1000);
		return this;
	}

	public PXGenericTestMerchanteClubRegisterPage selectFavoriteStoreDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(
				webdriver -> new Select(favoriteStoreDropdown).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(favoriteStoreDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public PXGenericTestMerchanteClubRegisterPage clickJoinNowButton() {
		click(joinNowButton);
		return this;
	}

	

}