/**
 * 
 */
package com.UITests.CardTemplates.page;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class ManageCardTemplatePage_Combined extends Page{

	
	@FindBy(id="activate-registration")
	private WebElement activateReg;

	@FindBy(linkText = "Activation Filters")
	private WebElement activationFiltersLink;

	@FindBy(xpath = "/descendant::span[normalize-space(.)='Save Changes']")
	private WebElement saveChanges;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;


	private static final String[] TITLE_WORDS = { "Manage", "Card", "Template" };

	public ManageCardTemplatePage_Combined(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	
	public void activateRegGuestWeb() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].checked=true;",activateReg);
	}
	
	public void deactivateRegGuestWeb() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].checked=false;",activateReg);
	}

	public void clickActivationFiltersLink() {
		click(activationFiltersLink);
	}

	public void clickSaveChanges() {
		click(saveChanges);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}

}