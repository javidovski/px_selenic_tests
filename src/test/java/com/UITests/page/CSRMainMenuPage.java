/**
 * 
 */
package com.UITests.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CSRMainMenuPage extends Page {

	@FindBy(linkText = "Register Account")
	private WebElement registerAccountLink;
	
	@FindBy(xpath="//td[contains(.,'Username')]/following-sibling::td")
	private WebElement username;
	
	
	@FindBy(xpath="//td[contains(.,'Customer Tier:')]/following-sibling::td")
	private WebElement customerTier;
	
	@FindBy(xpath="//td[contains(.,'Card Status:')]/following-sibling::td")
	private WebElement cardStatus;
	

	private static final String[] TITLE_WORDS = { "CSR", "Main", "Menu" };

	public CSRMainMenuPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public String getUsername() {
		return waitFor(username).getText();
	}

	public String getCardStatus() {
		return waitFor(cardStatus).getText();
	}

	public String getCustomerTier() {
		return waitFor(customerTier).getText();
	}


	public void clickRegisterAccountLink() {
		click(registerAccountLink);
	}
}
