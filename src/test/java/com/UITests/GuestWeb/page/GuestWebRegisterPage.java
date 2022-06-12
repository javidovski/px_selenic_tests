/**
 * This is the `Register My Card` page
 */
package com.UITests.GuestWeb.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;
import com.enums.UserInfo;

public class GuestWebRegisterPage extends Page{

	@FindBy(id = "registrationCode")
	private WebElement registrationCodeField;

	@FindBy(id = "salutation")
	private WebElement salutationDropdown;

	@FindBy(id = "firstName")
	private WebElement firstNameField;

	@FindBy(id = "lastName")
	private WebElement lastNameField;

	@FindBy(id = "address1")
	private WebElement addressField;

	@FindBy(id = "address2")
	private WebElement addressField2;

	@FindBy(id = "city")
	private WebElement cityField;

	@FindBy(id = "country")
	private WebElement countryDropdown;

	@FindBy(id = "stateProvince")
	private WebElement stateProvinceDropdown;

	@FindBy(id = "postalCode")
	private WebElement postalCodeField;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "username")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "confirmPassword")
	private WebElement confirmPasswordField;

	@FindBy(id = "optIn")
	private WebElement optIn;

	@FindBy(css = ".jumbotron > .container")
	private WebElement webElement;

	@FindBy(id = "printedCard")
	private WebElement printedCardField;


	@FindBy(xpath = "/descendant::span[normalize-space(.)='Submit']")
	private WebElement submit;


	@FindBy(xpath = "/descendant::input[@id=/descendant::label[normalize-space()='Email Opt In']/@for]")
	private WebElement emailOptInButton;


	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Register" };

	public GuestWebRegisterPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public static GuestWebRegisterPage using(WebDriver driver) {
		return new GuestWebRegisterPage(driver);
	}

	
	public GuestWebRegisterPage clickSubmit() throws InterruptedException {
		sleep(500);
		click(submit);
		sleep(500);
		return this;
	}


	public GuestWebRegisterPage clickEmailOptInButton() {
		click(emailOptInButton);
		return this;
	}

	public GuestWebRegisterPage clickWebElement() {
		click(webElement);
		return this;
	}

	public GuestWebRegisterPage setPrintedCardField(String text) throws InterruptedException {
		waitFor(printedCardField).clear();
		printedCardField.sendKeys(text);
		sleep(500);
		return this;
	}

	public GuestWebRegisterPage setRegistrationCodeField(String text) throws InterruptedException {
		waitFor(registrationCodeField).clear();
		registrationCodeField.sendKeys(text);
		sleep(500);
		return this;
	}

	public GuestWebRegisterPage selectSalutationDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		//List<WebElement> elementList = new Select(salutationDropdown).getOptions();
		//WebElement secondOption = elementList.get(1);
		//wait.until(webdriver -> secondOption.getText().trim().length() > 0);
		wait.until(webdriver -> new Select(salutationDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(salutationDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public GuestWebRegisterPage setFirstNameField(String text) {
		waitFor(firstNameField).clear();
		firstNameField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage setLastNameField(String text) {
		waitFor(lastNameField).clear();
		lastNameField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage setAddressField(String text) {
		waitFor(addressField).clear();
		addressField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage setAddressField2(String text) {
		waitFor(addressField2).clear();
		addressField2.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage setCityField(String text) {
		waitFor(cityField).clear();
		cityField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage selectCountryDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(countryDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(countryDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public GuestWebRegisterPage selectStateProvinceDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(stateProvinceDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(stateProvinceDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public GuestWebRegisterPage setPostalCodeField(String text) {
		waitFor(postalCodeField).clear();
		postalCodeField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage setEmailField(String text) {
		waitFor(emailField).clear();
		emailField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage setUsernameField(String text) {
		waitFor(usernameField).clear();
		usernameField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage setPasswordField(String text) {
		waitFor(passwordField).clear();
		passwordField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage setConfirmPasswordField(String text) {
		waitFor(confirmPasswordField).clear();
		confirmPasswordField.sendKeys(text);
		return this;
	}

	public GuestWebRegisterPage clickOptIn() {
		click(optIn);
		return this;
	}


	/**
	 * Fills out the form with info from the {@code UserInfo} enum ({@link com.enums.UserInfo })
	 * @param cardNum the card number {@code String}
	 * @param registrationCode the registration code {@code String}
	 * @throws Throwable
	 */
	public void registerForm(String cardNum, String registrationCode) throws Throwable {
		setPrintedCardField(cardNum);
		clickSubmit();
		setRegistrationCodeField(registrationCode);
		clickSubmit();

		selectSalutationDropdown(UserInfo.SALUTATION_MR.toString());
		setFirstNameField(UserInfo.FIRST_NAME.toString());
		setLastNameField(UserInfo.LAST_NAME.toString());
		setAddressField(UserInfo.ADDRESS.toString());
		setCityField(UserInfo.CITY.toString());
		selectCountryDropdown(UserInfo.COUNTRY.toString());
		selectStateProvinceDropdown(UserInfo.STATE.toString());
		setPostalCodeField(UserInfo.POSTAL_CODE.toString());
		setEmailField(UserInfo.EMAIL_ADDRESS.toString());
		setUsernameField(UserInfo.USERNAME.toString());
		setPasswordField(UserInfo.PASSWORD.toString());
		setConfirmPasswordField(UserInfo.PASSWORD.toString());
		clickSubmit();
	}

}