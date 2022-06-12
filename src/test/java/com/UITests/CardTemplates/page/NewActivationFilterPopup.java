/**
 * 
 */
package com.UITests.CardTemplates.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class NewActivationFilterPopup extends Page{

	@FindBy(id = "create-filter")
	private WebElement createFilter;

	@FindBy(id = "edit-event")
	private WebElement editEvent;

	@FindBy(id = "edit-program")
	private WebElement editProgram;

	@FindBy(id = "edit-sender")
	private WebElement editSender;

	@FindBy(id = "submit-new-filter")
	private WebElement submitNewFilter;

	@FindBy(xpath = "//*[text()='WEB']/preceding-sibling::*[text()='PX']/preceding-sibling::*[text()='Activate']/preceding-sibling::*/descendant::img[@class='delete-filter']")
	private WebElement activationFilterTrashImg;

	@FindBy(xpath = "/descendant::span[normalize-space(.)='Delete']")
	private WebElement delete;

	@FindBy(xpath = "//table/tbody/tr[1]/td[6]/a")
	private WebElement logout;

	private static final String[] TITLE_WORDS = { "Manage", "Card", "Template" };

	public NewActivationFilterPopup(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickCreateFilter() {
		click(createFilter);
	}

	public void selectEventDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(editEvent).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(editEvent);
		dropdown.selectByVisibleText(text);
	}

	public void selectProgramDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(editProgram).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(editProgram);
		dropdown.selectByVisibleText(text);
	}

	public void selectSenderDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(editSender).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(editSender);
		dropdown.selectByVisibleText(text);		
	}

	public void clickSubmitNewFilter() {
		click(submitNewFilter);
	}

	public void clickActivationFilterTrashImg() {
		click(activationFilterTrashImg);
	}

	public void clickDelete() {
		click(delete);
	}

	public void clickLogout() {
		click(logout);
	}

}