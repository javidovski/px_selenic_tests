/**
 * 
 */
package com.UITests.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CustomerAccountLookupPage extends Page {

	@FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Card Number:\"]]/td[2]/input")
	private WebElement cardNumInput;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	@FindBy(linkText = "Marketing Tools")
	private WebElement marketingToolsLink;
	
	@FindBy(linkText = "Activate Card")
	private WebElement activateCardLink;

	@FindBy(xpath = "//table/tbody/tr[2]/td[1]/b")
	private WebElement cardNumberTextField;


	private static final String[] TITLE_WORDS = { "Customer", "Account", "Lookup" };

	public CustomerAccountLookupPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public String getCardNumberLabelText() {
		return waitFor(cardNumberTextField).getText();
	}

	public void clickMarketingToolsLink() {
		click(marketingToolsLink);
	}

	public void setCardNumInput(String text) {
		waitFor(cardNumInput).clear();
		cardNumInput.sendKeys(text);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}
	
	public void clickActivateCardLink() {
		click(activateCardLink);
	}

}