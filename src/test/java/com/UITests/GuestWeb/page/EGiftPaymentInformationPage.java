/**
 * 
 */
package com.UITests.GuestWeb.page;


import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.TestCase;
import com.UITests.page.Page;

public class EGiftPaymentInformationPage extends Page{

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Continue'][2]")
	private WebElement continueButton;

	@FindBy(id = "bill_first_name_input")
	private WebElement billFirstNameInputField;

	@FindBy(id = "bill_last_name_input")
	private WebElement billLastNameInputField;

	@FindBy(id = "bill_address1_input")
	private WebElement billAddressInputField;

	@FindBy(id = "bill_city_input")
	private WebElement billCityInputField;

	@FindBy(id = "bill_country_input")
	private WebElement billCountryInputDropdown;

	@FindBy(id = "bill_postal_input")
	private WebElement billPostalInputField;

	@FindBy(id = "bill_phone_input")
	private WebElement billPhoneInputField;

	@FindBy(id = "bill_email_input")
	private WebElement billEmailInputField;
	
	@FindBy(xpath = "//input[@id='card_number']")
	private WebElement cardNumberField;
	
	@FindBy(xpath = "//input[@id='cvv']")
	private WebElement cvvCodeField;


	private static final String[] TITLE_WORDS = { "Payment", "Information" };

	public EGiftPaymentInformationPage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}
	
	public static EGiftPaymentInformationPage using(WebDriver driver) {
		return new EGiftPaymentInformationPage(driver);
	}
	
	public EGiftPaymentInformationPage switchTo_spreedly_cc_number_iframe() throws InterruptedException {
		By locator = By.xpath("//iframe[contains(@name,'spreedly-number-frame')]");
		WebDriverWait wait = new WebDriverWait(driver,TestCase.DEFAULT_TIMEOUT);
		wait.until(frameToBeAvailableAndSwitchToIt(locator));
		sleep(100);
		return this;
	}
	
	public EGiftPaymentInformationPage switchTo_spreedly_cvv_iframe() throws InterruptedException {
		By locator = By.xpath("//iframe[contains(@name,'spreedly-cvv-frame')]");
		WebDriverWait wait = new WebDriverWait(driver,TestCase.DEFAULT_TIMEOUT);
		wait.until(frameToBeAvailableAndSwitchToIt(locator));
		sleep(100);
		return this;
	}
	
	public EGiftPaymentInformationPage switchToDefaultContent() throws InterruptedException {
		this.driver.switchTo().defaultContent();
		sleep(100);
		return this;
	}


	
	public EGiftPaymentInformationPage clickExpirationMonth(String month) throws InterruptedException {
		WebElement expirationMonth = this.driver.findElement(
				By.xpath("//select[@id='cc_exp_month_input']/option[text()='" + month + "']"));
		click(expirationMonth);
		sleep(100);
		return this;
	}
	
	public EGiftPaymentInformationPage clickExpirationYear(String year) throws InterruptedException {
		WebElement expirationYear = this.driver.findElement(
				By.xpath("//select[@id='cc_exp_year_input']/option[text()='" + year + "']"));
		click(expirationYear);
		sleep(100);
		return this;
	}
	
	
	public EGiftPaymentInformationPage setCvvCodeField(String text)  throws InterruptedException{
		waitFor(cvvCodeField).clear();
		cvvCodeField.sendKeys(text);
		sleep(100);
		return this;
	}
	
	public EGiftPaymentInformationPage setCardNumberField(String text) throws InterruptedException {
		waitFor(cardNumberField).clear();
		cardNumberField.sendKeys(text);
		sleep(100);
		return this;
	}
	

	public EGiftPaymentInformationPage setBillFirstNameInputField(String text)  throws InterruptedException{
		waitFor(billFirstNameInputField).clear();
		billFirstNameInputField.sendKeys(text);
		sleep(100);
		return this;
	}


	public EGiftPaymentInformationPage setBillLastNameInputField(String text)  throws InterruptedException{
		waitFor(billLastNameInputField).clear();
		billLastNameInputField.sendKeys(text);
		sleep(100);
		return this;
	}

	public EGiftPaymentInformationPage setBillAddressInputField(String text)  throws InterruptedException{
		waitFor(billAddressInputField).clear();
		billAddressInputField.sendKeys(text);
		sleep(100);
		return this;
	}

	public EGiftPaymentInformationPage setBillCityInputField(String text)  throws InterruptedException{
		waitFor(billCityInputField).clear();
		billCityInputField.sendKeys(text);
		sleep(100);
		return this;
	}

	public EGiftPaymentInformationPage selectBillCountryInputDropdown(String text)  throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(billCountryInputDropdown).getFirstSelectedOption().getText().trim()
				.length() > 0);
		Select dropdown = new Select(billCountryInputDropdown);
		dropdown.selectByVisibleText(text);
		sleep(500);
		return this;
	}

	public EGiftPaymentInformationPage setBillPostalInputField(String text)  throws InterruptedException{
		waitFor(billPostalInputField).clear();
		billPostalInputField.sendKeys(text);
		sleep(100);
		return this;
	}

	public EGiftPaymentInformationPage setBillPhoneInputField(String text)  throws InterruptedException{
		waitFor(billPhoneInputField).clear();
		billPhoneInputField.sendKeys(text);
		sleep(100);
		return this;
	}

	public EGiftPaymentInformationPage setBillEmailInputField(String text)  throws InterruptedException{
		waitFor(billEmailInputField).clear();
		billEmailInputField.sendKeys(text);
		sleep(100);
		return this;
	}

	public EGiftPaymentInformationPage clickContinueButton()  throws InterruptedException{
		click(continueButton);
		sleep(100);
		return this;
	}

}