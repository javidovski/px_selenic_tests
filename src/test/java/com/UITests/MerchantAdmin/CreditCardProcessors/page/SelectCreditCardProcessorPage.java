/**
 * 
 */
package com.UITests.MerchantAdmin.CreditCardProcessors.page;

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

public class SelectCreditCardProcessorPage {

	@FindBy(xpath = "//table/tbody/tr[2]/td[1]/a[2]")
	private WebElement webElement;
	
	@FindBy(name = "newType")
	private WebElement newTypeDropdown;
	
	@FindBy(css = "input[value='New']")
	private WebElement newButton;

	private WebDriver driver;

	private static final int DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = 15;

	private static final String[] TITLE_WORDS = { "Select", "Credit", "Card", "Processor" };

	public SelectCreditCardProcessorPage(WebDriver driver) {
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

	public void clickWebElement() {
		click(webElement);
	}
	
	public void selectNewTypeDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(newTypeDropdown).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(newTypeDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void clickNewButton() {
		click(newButton);
	}
	
	public void deleteCCProcessor(String name) {
		By locator = By.xpath("//*[text()='" + name 
				+ "']/preceding-sibling::*/descendant-or-self::img[@title='Delete']/ancestor::a[1]");
		WebDriverWait wait =  new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.until(elementToBeClickable(locator)).click();
		
		locator = By.xpath("//input[@value='Yes']");
		wait.until(elementToBeClickable(locator)).click();
		
	}

}