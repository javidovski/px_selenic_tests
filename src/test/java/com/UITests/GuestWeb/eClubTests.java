package com.UITests.GuestWeb;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.UITests.TestCase;
import com.UITests.TestRail;
import com.UITests.CampaignMessageTemplate.page.EditCampaignMessageTemplateTestRESTMessagingExternalFieldPage;
import com.UITests.Features.page.NewFeatureEmailAddressasUsernamePage;
import com.UITests.Features.page.SelectFeaturePage;
import com.UITests.Features.page.ViewFeatureEmailAddressasUsernamePage;
import com.UITests.GuestWeb.page.PXGenericTestMerchantUnsubscribePage;
import com.UITests.GuestWeb.page.PXGenericTestMerchanteClubRegisterPage;
import com.UITests.MerchantAdmin.page.MerchantAdminPage;
import com.UITests.MerchantHome.page.MerchantHomePage;
import com.UITests.CampaignMessageTemplate.page.SelectCampaignMessageTemplatePage;
import com.UITests.CardTemplates.page.SelectCardTemplatePage;
import com.UITests.CardTemplates.page.ManageCardTemplatePage_eClub;
import com.UITests.TransactionalEmailConfig.page.TransactionalEmailConfigPage;
import com.UITests.UtilityClasses.DatabaseUtils;
import com.UITests.UtilityClasses.EmailUtils;


public class eClubTests extends TestCase {

	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}

	@Test
	@TestRail(id = "5809")
	public void testEmailAsUsername() throws Throwable {
		
		EmailUtils.deleteEmailsBySubject("Test (non-LSM) template",SERVER);
		DatabaseUtils.resetCardsWithEmail("eClub Template", EMAIL, MERCHANT_ID);
		DatabaseUtils.resetCardsWithEmail("Combined Card Template", EMAIL, MERCHANT_ID);
		
		login();
		switchMerchant();

		merchantHomePage.clickADMINLink();
		MerchantAdminPage merchantAdminPage = new MerchantAdminPage(driver);
		merchantAdminPage.clickFeaturesLink();

		
		SelectFeaturePage selectFeaturePage = new SelectFeaturePage(driver);
		//if feature doesn't exist, create it
		if (elementIsVisible("//td[text()='Email Address as Username']", "xpath", 1000, 200) == false) {
			selectFeaturePage.selectDropdown1("Email Address as Username");
			selectFeaturePage.clickNewButton();

			NewFeatureEmailAddressasUsernamePage nfeaup = new NewFeatureEmailAddressasUsernamePage(driver);
			nfeaup.clickSubmitButton();

			ViewFeatureEmailAddressasUsernamePage vfeaup = new ViewFeatureEmailAddressasUsernamePage(driver);
			vfeaup.clickFeaturesLink();
			assertTrue(elementIsVisible("//td[text()='Email Address as Username']", "xpath", 5000, 500));
		}
		
		merchantAdminPage.clickTransactionalEmailConfigLink();

		TransactionalEmailConfigPage tecp = new TransactionalEmailConfigPage(driver); sleep(2000);
		
		tecp.click_eClubRegisterGuestWebsite_ChangeDefaultValue(); sleep(1000);		
		tecp.select_eClubRegisterGuestWeb_TemplateDropdown("Test RESTMessaging 1 External Field"); sleep(1000);		
		tecp.turnOn_eClubRegisterGuestWeb_IsEmailActive(); sleep(1000);		
		tecp.clickSaveButton(); sleep(1000);
		
		tecp.clickMerchantHomeLink();

		MerchantHomePage merchantHomePage = new MerchantHomePage(driver);
		merchantHomePage.hoverMarketingToolsLink(); sleep(500);
		merchantHomePage.clickCampaignMessageTemplates();

		SelectCampaignMessageTemplatePage selectCampaignMessageTemplatePage = new SelectCampaignMessageTemplatePage(
				driver);
		selectCampaignMessageTemplatePage.click_edit_TestRestMessaging1ExternalField_Template();

		EditCampaignMessageTemplateTestRESTMessagingExternalFieldPage editCampaignMessageTemplateTestRESTMessagingExternalFieldPage = new EditCampaignMessageTemplateTestRESTMessagingExternalFieldPage(
				driver);
		editCampaignMessageTemplateTestRESTMessagingExternalFieldPage.selectDropdown1("Test (non-LSM) template");
		editCampaignMessageTemplateTestRESTMessagingExternalFieldPage.clickSubmitButton();

		merchantHomePage.clickADMINLink();
		merchantAdminPage.clickCardTemplatesLink();
		SelectCardTemplatePage selectCardTemplatePage = new SelectCardTemplatePage(driver);
		selectCardTemplatePage.clickEditEClubTemplate_gear();

		ManageCardTemplatePage_eClub manageCardTemplatePage = new ManageCardTemplatePage_eClub(driver);
		String eClubURL = manageCardTemplatePage.getEclubLinkURL();
		String eClubURL_withPort = eClubURL.replace("atfleet.test", "atfleet.test:" + FAILOVER_PORT);
		openNewTab();
		switchToTab(1);
		navigateToURL(eClubURL_withPort); sleep(1000);
		

		PXGenericTestMerchanteClubRegisterPage pXGenericTestMerchanteClubRegisterPage = 
				new PXGenericTestMerchanteClubRegisterPage(driver);
		pXGenericTestMerchanteClubRegisterPage.setFirstNameField("Test")
											  .setLastNameField("User")
											  .selectCountryDropdown("USA")
											  .setPostalCodeField("01886")
											  .setEmailField(EMAIL)
											  .selectFavoriteStoreStateDropdown("MA")
											  .selectFavoriteStoreDropdown("General 1");
		
		verifyTextOnPage("You have successfully joined our eClub program!",false, 1000, 200);
		pXGenericTestMerchanteClubRegisterPage.clickJoinNowButton();
		verifyTextOnPage("You have successfully joined our eClub program!");

		String unsubscribeURL = EmailUtils.returnEmailHRefURLBySubject("Test (non-LSM) template", SERVER, "300",
				"true");
		String unsubscribeURL_withPort = unsubscribeURL.replace("atfleet.test", "atfleet.test:" + FAILOVER_PORT);
		navigateToURL(unsubscribeURL_withPort); sleep(1000);
		
		
		PXGenericTestMerchantUnsubscribePage pXGenericTestMerchantUnsubscribePage = new PXGenericTestMerchantUnsubscribePage(
				driver);
		//pXGenericTestMerchantUnsubscribePage.clickContinueButton();
		pXGenericTestMerchantUnsubscribePage.clickUnsubscribe();
		pXGenericTestMerchantUnsubscribePage.clickSubmit();
		verifyTextOnPage("You have successfully customized email communications. " + 
		"To view your account information or change your preferences, you can log in to " + 
		"your account at any time, using your username and password.");
		
		switchToTab(0);
		//test cleanup to restore merchant web
		manageCardTemplatePage.clickMerchantAdminLink();
		merchantAdminPage.clickFeaturesLink();
		selectFeaturePage.clickDeleteEmailAsUsername();
		selectFeaturePage.clickConfirmFeatureDelete();
		
		merchantAdminPage.clickTransactionalEmailConfigLink(); sleep(1000);
		tecp.click_eClubGuestWeb_ResetToDefaultValue(); sleep(1000);
		tecp.clickSaveButton();
		
		merchantAdminPage.clickMerchantHomeLink();
		merchantHomePage.hoverMarketingToolsLink(); sleep(500);
		merchantHomePage.clickCampaignMessageTemplates();
		selectCampaignMessageTemplatePage.click_edit_TestRestMessaging1ExternalField_Template();
		editCampaignMessageTemplateTestRESTMessagingExternalFieldPage.selectDropdown1("Test RESTMessaging 1 External Field");
		editCampaignMessageTemplateTestRESTMessagingExternalFieldPage.clickSubmitButton();
	}



}
