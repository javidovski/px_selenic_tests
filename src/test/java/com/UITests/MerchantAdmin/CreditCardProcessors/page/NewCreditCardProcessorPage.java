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

import com.UITests.CampaignBuilderV2.page.CampaignBuilderV2Page;
import com.UITests.page.Page;

public class NewCreditCardProcessorPage extends Page {

	@FindBy(name = "ccProcessor_label")
	private WebElement ccProcessorLabelField;

	@FindBy(name = "ccProcessor_spreedlyPartnerConfigEnumId")
	private WebElement ccProcessorSpreedlyPartnerConfigEnumIdDropdown;

	@FindBy(name = "ccProcessor_enableAvsStreet")
	private WebElement ccProcessorAvsStreetCheckbox;

	@FindBy(name = "ccProcessor_enableAvsZip")
	private WebElement ccProcessorAvsZipCheckbox;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	

	private static final String[] TITLE_WORDS = { "New", "Credit", "Card", "Processor" };

	public NewCreditCardProcessorPage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}

	

	public void setCcProcessorLabelField(String text) {
		waitFor(ccProcessorLabelField).clear();
		ccProcessorLabelField.sendKeys(text);
	}

	public void selectCcProcessorSpreedlyPartnerConfigEnumIdDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(ccProcessorSpreedlyPartnerConfigEnumIdDropdown).getFirstSelectedOption()
				.getText().trim().length() > 0);
		Select dropdown = new Select(ccProcessorSpreedlyPartnerConfigEnumIdDropdown);
		dropdown.selectByVisibleText(text);
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

	public void clickSubmitButton() {
		click(submitButton);
	}

}