/**
 * 
 */
package com.UITests.CampaignMessageTemplate.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class SelectCampaignMessageTemplatePage extends Page {

	@FindBy(xpath = "/descendant::span[normalize-space(.)='New Campaign Message Template']")
	private WebElement newCampaignMessageTemplate;
	
	@FindBy(xpath = "//*[text()='Test RESTMessaging 1 External Field']/preceding-sibling::*/descendant::a[contains(@href,'edit')]")
	private WebElement edit_TestRestMessaging1ExternalField_Template;


	private static final String[] TITLE_WORDS = { "Select", "Campaign", "Message", "Template" };

	public SelectCampaignMessageTemplatePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void clickNewCampaignMessageTemplate() {
		click(newCampaignMessageTemplate);
	}
	
	public void click_edit_TestRestMessaging1ExternalField_Template() {
		click(edit_TestRestMessaging1ExternalField_Template);
	}

}