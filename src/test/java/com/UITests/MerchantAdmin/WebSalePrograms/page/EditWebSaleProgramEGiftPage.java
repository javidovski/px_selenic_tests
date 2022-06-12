
package com.UITests.MerchantAdmin.WebSalePrograms.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class EditWebSaleProgramEGiftPage extends Page{

	@FindBy(name = "webSaleProgram_ccProcessorId")
	private WebElement webSaleProgramCcProcessorIdDropdown;
	
	//@FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Credit Card Processor\"]]/td[2]")
	@FindBy(xpath = "//*[@name='webSaleProgram_ccProcessorId']/descendant::option[@selected='selected']")
	private WebElement currentCreditCardProcessorOption;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	

	private static final String[] TITLE_WORDS = { "Edit", "Web", "Sale", "Program", "\"Web", "eGift\"" };

	public EditWebSaleProgramEGiftPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public String getCurrentCreditCardProcessorText() {
		return waitFor(currentCreditCardProcessorOption).getText();
	}
	
	public void selectWebSaleProgramCcProcessorIdDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(webSaleProgramCcProcessorIdDropdown).getFirstSelectedOption().getText()
				.trim().length() > 0);
		Select dropdown = new Select(webSaleProgramCcProcessorIdDropdown);
		System.out.println("cc processor name is " + text);
		dropdown.selectByVisibleText(text);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}

}