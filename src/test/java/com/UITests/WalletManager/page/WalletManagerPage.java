/**
 * 
 */
package com.UITests.WalletManager.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class WalletManagerPage extends Page{

	@FindBy(id = "react-select-5-option-0")
	private WebElement categorySelection;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Create']/descendant::span[normalize-space(.)='Create']")
	private WebElement createButton;

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(css = ".wallet-category .css-1hwfws3")
	private WebElement categoryDropdown;

	@FindBy(xpath = "/descendant::div[normalize-space(.)='Cool Free Thing']")
	private WebElement coolFreeThing;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='APPLY']")
	private WebElement aPPLYButton;

	@FindBy(xpath = "/descendant::*[@id='LNS_ProgramAdministration']/descendant::a[normalize-space(.)='Category Manager']")
	private WebElement lNSProgramAdministrationLink;


	private static final String[] TITLE_WORDS = { "Wallet", "Manager" };

	public WalletManagerPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickCreateButton() {
		click(createButton);
	}

	public void setNameField(String text) {
		waitFor(nameField).clear();
		nameField.sendKeys(text);
	}

	public void clickCategoryDropdown() {
		click(categoryDropdown);
	}

	public void clickCoolFreeThing() {
		click(coolFreeThing);
	}

	public void clickContinueButton() {
		click(continueButton);
	}

	public void clickAPPLYButton() {
		click(aPPLYButton);
	}

	public void clickLNSProgramAdministrationLink() {
		click(lNSProgramAdministrationLink);
	}

	public void selectCategory() {
		click(categorySelection);
	}

}