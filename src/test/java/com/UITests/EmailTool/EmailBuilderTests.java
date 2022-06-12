/**
 * 
 */
package com.UITests.EmailTool;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.UITests.TestCase;
import com.UITests.TestRail;

import com.UITests.EmailTool.page.BEEPluginPage;
import com.UITests.EmailTool.page.EmailBuilderPage;
import com.UITests.EmailTool.page.EmailToolPage;
import com.UITests.SurveyCenter.page.m10101010BootSuiteMerchantSurveyPage;
import com.UITests.UtilityClasses.DateUtils;
import com.UITests.UtilityClasses.EmailUtils;
import com.UITests.UtilityClasses.DatabaseUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class EmailBuilderTests extends TestCase {


	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}
	
	@Test
	@TestRail(id = "4428") //Redwood test "Survey 8 - Link From Test Email - CSR"
	public void testSurvey8_LinkFromTestEmail() throws Throwable {
		login();
		switchMerchant();

		//create and send email
		String randomStr = getRandomAlphanumeric(5);
		String emailName = "Template for Survey (Test Email)_" + randomStr;
		EmailUtils.deleteEmailsBySubject("Link To Test Email", SERVER);
		merchantHomePage.hoverMarketingToolsLink(); sleep(500);
		merchantHomePage.clickEmailTool(); sleep(500);

		EmailToolPage emailToolPage = new EmailToolPage(driver);
		emailToolPage.clickCreateNewEmailButton();

		EmailBuilderPage emailBuilderPage = new EmailBuilderPage(driver);
		emailBuilderPage.setEmailName(emailName);
		emailBuilderPage.clickSaveName();
		emailBuilderPage.setSenderNameField("The Surveyor"); sleep(3000);
		emailBuilderPage.clickFromEmailDropdown(); sleep(500);
		emailBuilderPage.clickFromEmailrewardsAtCampaign(); sleep(3000);
		emailBuilderPage.setReplyToEmailAddressField("development.archive@paytronix.com"); sleep(500);
		emailBuilderPage.setSubjectField("Link To Test Email"); sleep(500);

		switchToDefaultContent(); scrollDown(500);
		waitUntilFrameAvailableSwitchToIt(0);
		BEEPluginPage beePluginPage = new BEEPluginPage(driver); sleep(500);
		beePluginPage.dragdropTextImage(driver); sleep(3000);
		beePluginPage.clickContentBoxClickElement(); sleep(1000);
		beePluginPage.setTextBlockParagraph(driver, "This text should be contained in the body of the email.");
		sleep(1000);

		beePluginPage.clickContentTab(); 	sleep(500);	
		beePluginPage.dragdropTextImage(driver); sleep(500);
		beePluginPage.clickContentBoxClickElement(); sleep(500);
		beePluginPage.setTextBlockParagraph(driver, ""); sleep(1000);
		beePluginPage.clickParameters(); sleep(1000);
		switchToDefaultContent(); sleep(1000);
		emailBuilderPage.clickAddSurvey();
		emailBuilderPage.selectSurveyParametersDropdown("m10101010 Boot Suite Merchant");
		emailBuilderPage.setSurveyLabelField("Click To Take Survey"); sleep(500);
		assertTrue(emailBuilderPage.insertServeyIsVisible());
		emailBuilderPage.clickInsertSurvey();
		assertTrue(emailBuilderPage.insertServeyIsntVisible());

		waitUntilFrameAvailableSwitchToIt(0); sleep(1000);
		beePluginPage.clickParameters(); sleep(1000);
		switchToDefaultContent(); sleep(1000);
		emailBuilderPage.clickAddSpecialLink(); sleep(1000);
		emailBuilderPage.selectLinkParametersDropdown("Unsubscribe"); sleep(500);
		emailBuilderPage.setLinkLabelField("Click here to unsubscribe."); sleep(500);
		emailBuilderPage.clickInsertLink();
		waitUntilFrameAvailableSwitchToIt(0);
		beePluginPage.waitUntilUnsubscribeURLVisible();
		beePluginPage.clickSaveEmail();
		switchToDefaultContent(); sleep(1000);
		scrollUp(800); sleep(1000);
		emailBuilderPage.clickApproveEmail();
		emailBuilderPage.waitUntilApprovedVisible(); scrollDown(500);
		waitUntilFrameAvailableSwitchToIt(0); sleep(1000);
		beePluginPage.clickActions(); sleep(500);
		beePluginPage.clickSendTest(); sleep(500);

		switchToDefaultContent(); sleep(1000);
		scrollUp(1000); sleep(1000);
		emailBuilderPage.clickTextArea(); sleep(500);
		emailBuilderPage.setTextArea(EMAIL); sleep(1000);
		emailBuilderPage.clickSendTest();
		emailBuilderPage.waitUntilEmailSentVisible();
		emailBuilderPage.clickOK();

		//open survey, fill it out, and click submit
		String foundURL = EmailUtils.returnEmailHRefURLBySubject("Link to Test Email", SERVER, "60", "true");
		openNewTab();
		switchToTab(1);
		navigateToURL(foundURL); sleep(1000);
		String redirectedURL = getCurrentURL();
		String redirectedURL_withPort = redirectedURL.replace("atfleet.test", "atfleet.test:" + FAILOVER_PORT);
		navigateToURL(redirectedURL_withPort); sleep(2000);

		String timeInMillis = DateUtils.getTimeInMillis();
		m10101010BootSuiteMerchantSurveyPage mbsmsp = new m10101010BootSuiteMerchantSurveyPage(driver);
		mbsmsp.clickCookieBanner();
		mbsmsp.setOpenResponseField(timeInMillis);
		mbsmsp.clickSingleSelect1_PrettyGood(); sleep(500);
		mbsmsp.clickSingleSelect2_NeedsImprovement(); sleep(500);
		mbsmsp.clickSingleSelect3_NA(); sleep(500);
		mbsmsp.clickSubmitSurvey();

		verifyTextOnPage("Thanks for completing our survey! Check your email for a fun reward.");
		verifyTextOnPage("You have just submitted a test survey; your results have not been recorded in the database.");
		EmailUtils.deleteEmailsBySubject("Link To Test Email", SERVER);

		//open email and send test email
		switchToTab(0);
		navigateToURL(PAYTRONIX_BASE_URL + MERCHANT_HOME_PATH);
		merchantHomePage.hoverMarketingToolsLink(); sleep(500);
		merchantHomePage.clickEmailTool(); sleep(500);
		clickElement("//td[text()='" + emailName + "']/preceding-sibling::td/span/span[@id='edit-template']", "xpath");
		scrollDown(500);
		waitUntilFrameAvailableSwitchToIt(0); sleep(1000);
		beePluginPage.clickActions(); sleep(1000);
		beePluginPage.clickSendTest(); sleep(500);
		switchToDefaultContent();
		scrollUp(1000); sleep(1000);
		emailBuilderPage.clickTextArea(); sleep(500);
		emailBuilderPage.setTextArea(EMAIL); sleep(1000);
		emailBuilderPage.clickSendTest();
		emailBuilderPage.waitUntilEmailSentVisible();
		emailBuilderPage.clickOK();

		//open survey and click submit without filling it out
		foundURL = EmailUtils.returnEmailHRefURLBySubject("Link to Test Email", SERVER, "60", "true");
		openNewTab();
		switchToTab(2);
		navigateToURL(foundURL);
		sleep(2000);
		redirectedURL = getCurrentURL();
		redirectedURL_withPort = redirectedURL.replace("atfleet.test", "atfleet.test:" + FAILOVER_PORT);
		navigateToURL(redirectedURL_withPort); sleep(5000);
		m10101010BootSuiteMerchantSurveyPage mbsmsp2 = new m10101010BootSuiteMerchantSurveyPage(driver);
		mbsmsp2.clickCookieBanner();
		mbsmsp2.clickSubmitSurvey();
		verifyTextOnPage("Thanks for completing our survey! Check your email for a fun reward.");
		verifyTextOnPage("You have just submitted a test survey; your results have not been recorded in the database.");
		String sqlQuery = "SELECT COUNT(survey_answer_id) AS response_num FROM survey_answer WHERE merchant_id = "
				+ MERCHANT_ID + " AND text_value = '" + timeInMillis + "'";
		String responseNum = DatabaseUtils.getColumnValue(sqlQuery, "response_num");
		assertEquals("0", responseNum);

		switchToTab(0);
		merchantHomePage.clickLogout();

	}

	@Test
	@TestRail(id = "4934")
	public void testCreateBasicEmail() throws Throwable {
		String randomStr = getRandomAlphanumeric(5);
		String emailName = "My New Email_" + randomStr;
		
		login();
		switchMerchant();

		merchantHomePage.hoverMarketingToolsLink(); sleep(500);
		merchantHomePage.clickEmailTool();

		EmailToolPage emailToolPage = new EmailToolPage(driver);
		emailToolPage.clickCreateNewEmailButton();

		EmailBuilderPage emailBuilderPage = new EmailBuilderPage(driver);
		emailBuilderPage.setEmailName(emailName);
		emailBuilderPage.clickSaveName();
		emailBuilderPage.setSenderNameField("The Tester"); sleep(3000);
		emailBuilderPage.clickFromEmailDropdown(); sleep(500);
		emailBuilderPage.clickFromEmailrewardsAtCampaign(); sleep(3000);
		emailBuilderPage.setReplyToEmailAddressField("jm44667@paytronix.com"); sleep(500);
		emailBuilderPage.setSubjectField("Hello World"); sleep(500);
		switchToDefaultContent();
		scrollDown(500);

		waitUntilFrameAvailableSwitchToIt(0);
		BEEPluginPage bEEPluginPage = new BEEPluginPage(driver); sleep(500);
		bEEPluginPage.dragdropTextImage(driver); sleep(1000);
		bEEPluginPage.clickContentBoxClickElement(); sleep(1000);
		bEEPluginPage.setTextBlockParagraph(driver,
				"This text should be contained in the body of the email."); sleep(1000);
		bEEPluginPage.clickContentTab(); sleep(500);
		bEEPluginPage.dragdropTextImage(driver); sleep(500);
		bEEPluginPage.clickContentBoxClickElement(); sleep(500);

		bEEPluginPage.setTextBlockParagraph(driver,""); sleep(1000);
		bEEPluginPage.clickParameters(); sleep(1000);
		switchToDefaultContent(); sleep(1000);
		emailBuilderPage.clickAddSpecialLink(); sleep(1000);
		emailBuilderPage.selectLinkParametersDropdown("Unsubscribe"); sleep(500);
		emailBuilderPage.setLinkLabelField("Click here to unsubscribe."); sleep(500);
		emailBuilderPage.clickInsertLink();
		waitUntilFrameAvailableSwitchToIt(0);
		bEEPluginPage.waitUntilUnsubscribeURLVisible(); sleep(1000);
		bEEPluginPage.clickSaveEmail();

		switchToDefaultContent(); sleep(1000);
		scrollUp(800); sleep(500);
		emailBuilderPage.clickApproveEmail();
		emailBuilderPage.waitUntilApprovedVisible();

	}

}