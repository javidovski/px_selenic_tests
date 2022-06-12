/**
 * 
 */
package com.UITests.Rules.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class SelectRulePage extends Page {

	@FindBy(name = "newType")
	private WebElement newTypeDropdown;

	@FindBy(css = "input[value='New']")
	private WebElement newButton;


	private static final String[] TITLE_WORDS = { "Select", "Rule" };

	public SelectRulePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void selectNewTypeDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(newTypeDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(newTypeDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void clickNewButton() {
		click(newButton);
	}

}