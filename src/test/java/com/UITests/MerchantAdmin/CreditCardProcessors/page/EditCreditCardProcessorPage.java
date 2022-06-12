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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class EditCreditCardProcessorPage extends Page {

	@FindBy(name = "ccProcessor_enableAvsStreet")
	private WebElement ccProcessorAvsStreetCheckbox;

	@FindBy(name = "ccProcessor_enableAvsZip")
	private WebElement ccProcessorAvsZipCheckbox;	

	@FindBy(name = "ccProcessor_enableCvvMatch")
	private WebElement ccProcessorCvvMatchCheckbox;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	private WebDriver driver;

	private static final int DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = 15;

	private static final String[] TITLE_WORDS = { "Edit", "Credit", "Card", "Processor" };

	public EditCreditCardProcessorPage(WebDriver driver)  {
		super(driver,TITLE_WORDS);
	}
	
	public void setCcProcessorAvsStreet(boolean checked) throws InterruptedException {
		if ((!ccProcessorAvsStreetCheckbox.isSelected() && checked) || (ccProcessorAvsStreetCheckbox.isSelected() && !checked)) {
			ccProcessorAvsStreetCheckbox.click();
		}
		sleep(500);
	}

	public void setCcProcessorAvsZip(boolean checked) throws InterruptedException {
		if ((!ccProcessorAvsZipCheckbox.isSelected() && checked) || (ccProcessorAvsZipCheckbox.isSelected() && !checked)) {
			ccProcessorAvsZipCheckbox.click();
		}
		sleep(500);
	}
	
	public void setCcProcessorCvvMatch(boolean checked) throws InterruptedException {
		if ((!ccProcessorCvvMatchCheckbox.isSelected() && checked) || (ccProcessorCvvMatchCheckbox.isSelected() && !checked)) {
			ccProcessorCvvMatchCheckbox.click();
		}
		sleep(500);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}


}