/**
 * 
 */
package com.UITests.CardTemplates.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class ManageCardTemplatePage_eClub extends Page{

	@FindBy(id="responsive-eclub-link")
	private WebElement eClubLinkURL;
	

	@FindBy(xpath = "/descendant::span[normalize-space(.)='Save Changes']")
	private WebElement saveChanges;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "(//a[text() = 'Merchant Admin'])[1]")
	private WebElement merchantAdminLink;


	private static final String[] TITLE_WORDS = { "Manage", "Card", "Template" };

	public ManageCardTemplatePage_eClub(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	
	public String getEclubLinkURL() {
		return eClubLinkURL.getAttribute("href");
	}

	public void clickSaveChanges() {
		click(saveChanges);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}
	
	public void clickMerchantAdminLink() {
		click(merchantAdminLink);
	}

}