/**
 * 
 */
package com.UITests.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CampaignCenterPage extends Page{

	@FindBy(xpath = "/descendant::button[normalize-space(.)='CREATE NEW CAMPAIGN']")
	private WebElement cREATENEWCAMPAIGNButton;
	
	@FindBy(xpath = "/descendant::*[@id='LNS_MarketingTools']/descendant::a[normalize-space(.)='Campaign Message Templates']")
	private WebElement campaignMessageTemplatesLink;


	private static final String[] TITLE_WORDS = { "Campaign", "Center" };

	public CampaignCenterPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickCREATENEWCAMPAIGNButton() {
		click(cREATENEWCAMPAIGNButton);
	}
	
	public void clickCampaignMessageTemplatesLink() {
		click(campaignMessageTemplatesLink);
	}

}