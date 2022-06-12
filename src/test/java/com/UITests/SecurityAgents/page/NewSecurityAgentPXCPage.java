/**
 * 
 */
package com.UITests.SecurityAgents.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class NewSecurityAgentPXCPage extends Page {

	@FindBy(name = "storeId")
	private WebElement storeIdDropdown;

	@FindBy(name = "agentName")
	private WebElement agentNameField;

	@FindBy(name = "softwareConfigId")
	private WebElement softwareConfigIdDropdown;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	

	private static final String[] TITLE_WORDS = { "New", "Security", "Agent", "\"PXC", "1\"" };

	public NewSecurityAgentPXCPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void selectStoreIdDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(storeIdDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(storeIdDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void setAgentNameField(String text) {
		waitFor(agentNameField).clear();
		agentNameField.sendKeys(text);
	}

	public void selectSoftwareConfigIdDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(softwareConfigIdDropdown).getFirstSelectedOption().getText().trim()
				.length() >= 0);
		Select dropdown = new Select(softwareConfigIdDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}

}