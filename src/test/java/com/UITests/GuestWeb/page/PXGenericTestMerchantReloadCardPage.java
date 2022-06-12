/**
 * 
 */
package com.UITests.GuestWeb.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PXGenericTestMerchantReloadCardPage {
	
	@FindBy(id="reload-value")
	private WebElement reloadValueField;

	@FindBy(xpath = "/descendant::input[@id=/descendant::label[normalize-space()='Name on Card']/@for]")
	private WebElement nameOnCardField;

	@FindBy(id = "card-type")
	private WebElement cardTypeDropdown;

	@FindBy(xpath = "/descendant::input[@id=/descendant::label[normalize-space()='Card Number']/@for]")
	private WebElement cardNumberField;

	@FindBy(xpath = "/descendant::input[@id=/descendant::label[normalize-space()='CVV Code']/@for]")
	private WebElement cVVCodeField;

	@FindBy(id = "expire-year")
	private WebElement expireYearDropdown;

	@FindBy(xpath = "/descendant::input[@id=/descendant::label[normalize-space()='Address']/@for]")
	private WebElement addressField;

	@FindBy(xpath = "/descendant::input[@id=/descendant::label[normalize-space()='City']/@for]")
	private WebElement cityField;

	@FindBy(id = "state")
	private WebElement stateDropdown;

	@FindBy(xpath = "/descendant::input[@id=/descendant::label[normalize-space()='Postal Code']/@for]")
	private WebElement postalCodeField;

	@FindBy(xpath = "/descendant::div[@class='row'][1]")
	private WebElement webElement2;

	@FindBy(className = "amount")
	private WebElement amountField;

	@FindBy(className = "status")
	private WebElement statusField;

	@FindBy(linkText = "Reload Card")
	private WebElement reloadCardLink;

	@FindBy(css = ".buttonRow > .col-md-12")
	private WebElement webElement;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//div/div/div/div/ul/li")
	private WebElement field;

	@FindBy(xpath = "/descendant::span[@class='amount'][1]")
	private WebElement field2;

	@FindBy(xpath = "/descendant::div[@class='status'][1]")
	private WebElement field3;

	private WebDriver driver;

	private static final int DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = 15;

	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Reload", "Card" };

	public PXGenericTestMerchantReloadCardPage(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		Arrays.stream(TITLE_WORDS).forEach(word -> {
			wait.until(attributeContains(By.tagName("title"), "innerHTML", word));
		});
		PageFactory.initElements(driver, this);
	}

	private WebElement waitFor(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		return wait.until(elementToBeClickable(element));
	}

	private WebElement scrollTo(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		return element;
	}

	protected WebElement click(WebElement element) {
		WebElement webElement = scrollTo(waitFor(element));
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		return wait.ignoring(ElementClickInterceptedException.class).until(webDriver -> {
			webElement.click();
			return webElement;
		});
	}

	public void clickReloadCardLink() {
		click(reloadCardLink);
	}

	public void clickWebElement() {
		click(webElement);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}

	public String getFieldText() {
		return waitFor(field).getText();
	}

	public String getField2Text() {
		return waitFor(field2).getText();
	}

	public String getField3Text() {
		return waitFor(field3).getText();
	}

	public void setNameOnCardField(String text) {
		waitFor(nameOnCardField).clear();
		nameOnCardField.sendKeys(text);
	}

	public void selectCardTypeDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(cardTypeDropdown).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(cardTypeDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void setCardNumberField(String text) {
		waitFor(cardNumberField).clear();
		cardNumberField.sendKeys(text);
	}

	public void setCVVCodeField(String text) {
		waitFor(cVVCodeField).clear();
		cVVCodeField.sendKeys(text);
	}

	public void selectExpireYearDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(expireYearDropdown).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(expireYearDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void setAddressField(String text) {
		waitFor(addressField).clear();
		addressField.sendKeys(text);
	}

	public void setCityField(String text) {
		waitFor(cityField).clear();
		cityField.sendKeys(text);
	}

	public void selectStateDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(stateDropdown).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(stateDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void setPostalCodeField(String text) {
		waitFor(postalCodeField).clear();
		postalCodeField.sendKeys(text);
	}
	
	public void setReloadAmountField(String amount) {
		waitFor(reloadValueField).clear();
		reloadValueField.sendKeys(amount);
	}

	public void clickWebElement2() {
		click(webElement2);
	}

	public String getAmountFieldText() {
		return waitFor(amountField).getText();
	}

	public String getStatusFieldText() {
		return waitFor(statusField).getText();
	}

}