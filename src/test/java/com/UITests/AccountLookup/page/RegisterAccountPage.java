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

public class RegisterAccountPage extends Page{

	@FindBy(name = "billnum")
	private WebElement cardNumberField;

	@FindBy(name = "salutation")
	private WebElement salutationDropdown;

	@FindBy(name = "firstname")
	private WebElement firstnameField;

	@FindBy(name = "lastname")
	private WebElement lastnameField;

	@FindBy(name = "address")
	private WebElement addressField;

	@FindBy(name = "city")
	private WebElement cityField;

	//should be "state" not "states"
	@FindBy(name = "state")
	private WebElement stateDropdown;

	@FindBy(name = "zipcode")
	private WebElement zipcodeField;

	@FindBy(name = "email")
	private WebElement emailField;

	@FindBy(name = "emailVerify")
	private WebElement emailVerifyField;

	@FindBy(name = "username")
	private WebElement usernameField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(name = "password2")
	private WebElement passwordField2;

	@FindBy(name = "opt_in")
	private WebElement optIn;

	@FindBy(css = "input[value='Submit Registration Information']")
	private WebElement submitRegistrationInformationButton;


	private static final String[] TITLE_WORDS = { "Register", "Account" };

	public RegisterAccountPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public static RegisterAccountPage using(WebDriver driver) {
		return new RegisterAccountPage(driver);
	}


	public RegisterAccountPage setCardNumberField(String text) {
		waitFor(cardNumberField).clear();
		cardNumberField.sendKeys(text);
		return this;
	}


	public RegisterAccountPage selectSalutationDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(salutationDropdown).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(salutationDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public RegisterAccountPage setFirstnameField(String text) {
		waitFor(firstnameField).clear();
		firstnameField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage setLastnameField(String text) {
		waitFor(lastnameField).clear();
		lastnameField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage setAddressField(String text) {
		waitFor(addressField).clear();
		addressField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage setCityField(String text) {
		waitFor(cityField).clear();
		cityField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage selectStateDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(stateDropdown).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(stateDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public RegisterAccountPage setZipcodeField(String text) {
		waitFor(zipcodeField).clear();
		zipcodeField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage setEmailField(String text) {
		waitFor(emailField).clear();
		emailField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage setEmailVerifyField(String text) {
		waitFor(emailVerifyField).clear();
		emailVerifyField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage setUsernameField(String text) {
		waitFor(usernameField).clear();
		usernameField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage setPasswordField(String text) {
		waitFor(passwordField).clear();
		passwordField.sendKeys(text);
		return this;
	}

	public RegisterAccountPage setPasswordField2(String text) {
		waitFor(passwordField2).clear();
		passwordField2.sendKeys(text);
		return this;
	}

	public RegisterAccountPage clickOptIn() {
		click(optIn);
		return this;
	}

	public RegisterAccountPage clickSubmitRegistrationInformationButton() {
		click(submitRegistrationInformationButton);
		return this;
	}

}