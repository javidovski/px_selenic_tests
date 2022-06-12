/**
 * 
 */
package com.UITests.TransactionalEmailConfig.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class TransactionalEmailConfigPage extends Page {

	@FindBy(xpath = "(//legend[contains(text(),'eClubRegister')]/parent::*/descendant::div[contains(text(),'Guest Website')]/following-sibling::*/descendant::a[text()='Change Default Value'])[2]")
	private WebElement eClubRegisterGuestWebsite_ChangeDefaultValue;

	@FindBy(xpath = "(//legend[contains(text(),'eClubRegister')]/parent::*/descendant::div[contains(text(),'Guest Website')]/following-sibling::*/descendant::select)[1]")
	private WebElement eClubGuestWebTemplateDropdown;

	@FindBy(xpath = "(//legend[contains(text(),'eClubRegister')]/parent::*/descendant::div[contains(text(),'Guest Website')]/following-sibling::*/descendant::input[contains(@id,'isEmailActive') and @type='checkbox'])[1]")
	private WebElement eClubRegisterIsEmailActive;
	
	@FindBy(xpath = "//legend[contains(text(),'eClubRegister')]/parent::*/descendant::div[contains(text(),'Guest Website')]/following-sibling::*/descendant::a[text()='Reset to Default Value']")
	private WebElement eClubGuestWeb_resetToDefaultValue;
	
	@FindBy(css = "input[value='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/a")
	private WebElement merchantHomeLink;


	private static final String[] TITLE_WORDS = { "Transactional", "Email", "Config" };
	
	public TransactionalEmailConfigPage(WebDriver driver) {		
		super(driver,TITLE_WORDS);
	}


	public void click_eClubRegisterGuestWebsite_ChangeDefaultValue() {
		click(eClubRegisterGuestWebsite_ChangeDefaultValue);
		
	}

	public void select_eClubRegisterGuestWeb_TemplateDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(eClubGuestWebTemplateDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(eClubGuestWebTemplateDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void turnOn_eClubRegisterGuestWeb_IsEmailActive() {
		if (!eClubRegisterIsEmailActive.isSelected()) {
			click(eClubRegisterIsEmailActive);
		}
	}
	
	public void turnOff_eClubRegisterGuestWeb_IsEmailActive() {
		if (eClubRegisterIsEmailActive.isSelected()) {
			click(eClubRegisterIsEmailActive);
		}
	}
	
	public void click_eClubGuestWeb_ResetToDefaultValue() {
		click(eClubGuestWeb_resetToDefaultValue);
	}

	public void clickSaveButton() {
		click(saveButton);
	}

	public void clickMerchantHomeLink() {
		click(merchantHomeLink);
	}

}