/**
 * 
 */
package com.UITests.MerchantAdmin.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class MerchantAdminPage extends Page{

	@FindBy(xpath = "//a[contains(text(),'Features')]")
	private WebElement featuresLink;
	
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/div[4]/div/div/div[1]/a")
	private WebElement usersLink;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/div[10]/div/div/div[1]/a")
	private WebElement cardTemplatesLink;
	
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/div[6]/div/div/div[9]/a")
	private WebElement securityAgentsLink;
	
	@FindBy(linkText = "Template Rules")
	private WebElement templateRulesLink;
	
	@FindBy(linkText = "Transactional Email Config")
	private WebElement transactionalEmailConfigLink;
	
	@FindBy(linkText = "Event Schedules")
	private WebElement eventSchedulesLink;
	
	@FindBy(xpath = "(//a[text()='Home'])[1]")
	private WebElement merchantHomeLink;
	
	@FindBy(linkText = "Credit Card Processors")
	private WebElement creditCardProcessorsLink;

	private static final String[] TITLE_WORDS = { "View", "Merchant", "\"PX", "Generic", "Test", "Merchant\"" };

	public MerchantAdminPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public void clickFeaturesLink() {
		click(featuresLink);
	}

	public void clickCardTemplatesLink() {
		click(cardTemplatesLink);
	}
	
	public void clickSecurityAgentsLink() {
		click(securityAgentsLink);
	}

	public void clickUsersLink() {
		click(usersLink);
	}
	
	public void clickTemplateRules() {
		click(templateRulesLink);
	}
	
	public void clickTransactionalEmailConfigLink() {
		click(transactionalEmailConfigLink);
	}
	
	public void clickEventSchedulesLink() {
		click(eventSchedulesLink);
	}
	
	public void clickMerchantHomeLink() {
		click(merchantHomeLink);
	}
	public void clickCreditCardProcessorsLink() {
		click(creditCardProcessorsLink);
	}

}