/**
 * 
 */
package com.UITests.CampaignBuilderV2;

import com.UITests.TestCase;
import com.UITests.CampaignBuilderV2.page.CampaignBuilderV2Page;
import com.UITests.CampaignBuilderV2.page.CampaignCenterPage;
import com.UITests.UtilityClasses.DatabaseUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CampaignBuilderV2Tests extends TestCase {

	@BeforeEach
	@Override
	public void beforeTest() {
		super.beforeTest();
		//DatabaseUtils.changeCampaignBuilderVersion("CampaignBuilderV2");
	}

	@AfterEach
	@Override
	public void afterTest() {
		super.afterTest();
		//		DatabaseUtils.changeCampaignBuilderVersion("CampaignBuilderV1");
	}

	
	@Test
	public void testSimplenosavecampaign() throws Throwable {
		login();
		switchMerchant();

		String campaignName = "New Campaign " + getRandomAlphanumeric(5);
		merchantHomePage.hoverMarketingToolsLink();
		merchantHomePage.clickCampaignCenterLink();

		CampaignCenterPage campaignCenterPage = new CampaignCenterPage(driver);
		campaignCenterPage.clickCREATENEWCAMPAIGNButton();

		CampaignBuilderV2Page campaignBuilderPage2 = new CampaignBuilderV2Page(driver); 
		campaignBuilderPage2.setCampaignNameField(campaignName)
							.clickAddWalletButton() 		
							.setWalletInputField1("free") 	
							.clickFreeClifBar()
							.setWalletQuantityField1("2")
							.clickStoreInputFieldDropdownArrow() 
							.clickCorporateStore()
							.clickRelativeExpRadioButton()
							.setRelativeExpirationInputField("0")
							.setIsRuleBasedCheckbox(false)
							.clickMessageTab()
							.clickTestClickToLoadSelectButton()
							.clickSegmentTab()
							.clickAccountFiltersForATs()
							.clickScheduleTab()
							.clickSaveReviewButton();
		sleep(1000000);
		
		
	}




}