package com.UITests.SurveyCenter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import com.UITests.TestCase;
import com.UITests.TestRail;

import com.UITests.AccountLookup.page.ActivateCardPage;
import com.UITests.CampaignMessageTemplate.page.NewCampaignMessageTemplatePage;
import com.UITests.CampaignMessageTemplate.page.SelectCampaignMessageTemplatePage;
import com.UITests.GuestWeb.page.GuestWebAccountBalance;
import com.UITests.GuestWeb.page.GuestWebMemberPortalPage;
import com.UITests.GuestWeb.page.GuestWebRegisterPage;
import com.UITests.GuestWeb.page.RelatedGuestsPage;
import com.UITests.Rules.page.NewSurveyParticipantRulePage;
import com.UITests.Rules.page.Rules_SelectCardTemplatePage;
import com.UITests.Rules.page.SelectRulePage;
import com.UITests.Rules.page.SelectTemplateRulePage;
import com.UITests.SurveyCenter.page.SurveyCenterPage;
import com.UITests.SurveyCenter.page.SurveydetailsPage;
import com.UITests.SurveyCenter.page.m10101010BootSuiteMerchantSurveyPage;
import com.UITests.UtilityClasses.DateUtils;
import com.UITests.UtilityClasses.DatabaseUtils;
import com.UITests.page.CustomerAccountLookupPage;


public class SurveyTests extends TestCase {

	@BeforeEach
	@Override
	public void beforeTest() {
		super.beforeTest();

	}

	@AfterEach
	@Override
	public void afterTest() {
		super.afterTest();
	}

	@Test
	@TestRail(id = "4429") //Redwood test "Survey 1 - Create Survey - CSR"
	public void testSurvey1_CreateSurvey() throws Throwable {
		String surveyCode = getRandomAlphanumeric(5);
		String survey1Name = "CreateSurveyTest1_" + surveyCode;
		String survey2Name = "CreateSurveyTestInvalid_" + surveyCode;

		int numRows = DatabaseUtils.deleteSurvey(surveyCode, MERCHANT_ID);
		System.out.println(numRows);
		//TO DO ?: Flush the Cache with JMX. If using a random survey code, it might not be needed

		login();
		switchMerchant();
		merchantHomePage.hoverMarketingToolsLink(); sleep(500);
		merchantHomePage.clickSurveyCenter();

		//create and save a valid survey
		SurveyCenterPage surveyCenterPage = new SurveyCenterPage(driver);
		surveyCenterPage.clickNewSurveyButton();
		SurveydetailsPage surveydetailsPage = new SurveydetailsPage(driver);
		surveydetailsPage.setSurveyTitleField(survey1Name)
						 .setSurveyDescriptionField("This survey was created in web.merchant.csr.create_survey")
						 .setSurveyCodeField(surveyCode)
						 .setStartDateMonthField("3")
						 .setStartDateDayField("2")
						 .setStartDateYearField("2022")
						 .setEndDateMonthField("1")
						 .setEndDateDayField("1")
						 .setEndDateYearField("2050")
						 .clickAddQuestionButton()
						 .setQuestion1TitleField("Open Response")
						 .selectQuestion1TypeSelectDropdown("Paragraph Response")
						 .clickAddQuestionButton()
						 .setQuestion2TitleField("Multiple Choice (Single Answer)")
						 .selectQuestion2TypeSelectDropdown("Multiple Choice (Single Answer)")
						 .clickPlus()
						 .setAnswerTitleField("Answer1")
						 .clickAddQuestionButton()
						 .setQuestion3TitleField("Rating Scale")
						 .selectQuestion3TypeSelectDropdown("Rating Scale")
						 .clickSaveButton();
						 

		//verify elements on survey center main page
		waitUntilElementVisible("//td[@class='name' and text()='" + survey1Name + "']", "xpath");
		waitUntilElementVisible("//td[@class='code' and text()='" + surveyCode + "']", "xpath");

		//create and try to save an invalid survey
		surveyCenterPage.clickNewSurveyButton();
		surveydetailsPage.setSurveyTitleField("CreateSurveyTestInvalid")
						 .setSurveyDescriptionField(
									"This survey was created in web.merchant.csr.create_survey to test invalid inputs")
						 .setStartDateMonthField("3")
						 .setStartDateDayField("2")
						 .setStartDateYearField("2022")
						 .setEndDateMonthField("1")
						 .setEndDateDayField("1")
						 .setEndDateYearField("2050")
						 .clickSaveButton();
						 
						 
		assertEquals("Survey must have questions", surveydetailsPage.getUiStateErrorFieldText());
		surveydetailsPage.setSurveyTitleField("")
						  .clickSaveButton();		
		assertEquals("Survey title is required.", surveydetailsPage.getUiStateErrorFieldText());
		
		
		surveydetailsPage.setSurveyTitleField(survey2Name)
						 .setSurveyCodeField(surveyCode)
						 .clickAddQuestionButton()
						 .setQuestion1TitleField("Open Response")
						 .selectQuestion1TypeSelectDropdown("Paragraph Response")
						 .clickSaveButton();
						 
						 
		assertEquals("Survey Code is not unique", surveydetailsPage.getUiStateErrorFieldText());
		surveydetailsPage.setSurveyCodeField("CreateSurveyTestInvalid")
						 .clickAddQuestionButton()
						 .clickSaveButton();
		assertEquals("This question must have a title.", surveydetailsPage.getUiStateErrorFieldText());
		
		surveydetailsPage.setQuestion2TitleField("Open Response")
						 .setStartDateMonthField("")
						 .setStartDateDayField("")
						 .setStartDateYearField("")
						 .clickSaveButton();

		assertEquals("Valid month is required.", surveydetailsPage.getUiStateErrorFieldText());

	}

	@Test
	@TestRail(id = "4514") //Redwood test "Survey 2 - Test Start End Dates - CSR"
	public void testSurvey2_startEndDates() throws Throwable {
		String surveyCode = getRandomAlphanumeric(5);
		String survey1Name = "TestStartEnd_" + surveyCode;

		int numRows = DatabaseUtils.deleteSurvey(surveyCode, MERCHANT_ID);
		System.out.println(numRows);
		//TO DO ?: Flush the Cache with JMX. If using a random survey code, it might not be needed

		login();
		switchMerchant();
		merchantHomePage.hoverMarketingToolsLink();
		sleep(500);
		merchantHomePage.clickSurveyCenter();

		//create and save a valid survey
		SurveyCenterPage surveyCenterPage = new SurveyCenterPage(driver);
		surveyCenterPage.clickNewSurveyButton();
		SurveydetailsPage surveydetailsPage = new SurveydetailsPage(driver);		
		surveydetailsPage.setSurveyTitleField(survey1Name)
						 .setSurveyCodeField(surveyCode)
						 .setStartDateMonthField("1")
						 .setStartDateDayField("1")
						 .setStartDateYearField("2050")
						 .setEndDateMonthField("2")
						 .setEndDateDayField("1")
						 .setEndDateYearField("2050")	
						 .clickAddQuestionButton()
						 .setQuestion1TitleField("Dummy question")
						 .selectQuestion1TypeSelectDropdown("Rating Scale")
						 .clickSaveButton();
		
		
		waitUntilElementVisible("//td[@class='name' and text()='" + survey1Name + "']", "xpath");

		String datetimesQueryStr = "SELECT CAST(start_datetime AS VARCHAR) start_datetime, "
				+ "CAST(end_datetime AS VARCHAR) end_datetime FROM survey WHERE title = '" + survey1Name + "'";
		HashMap<String, String> datetimes = DatabaseUtils.getColumnValues(datetimesQueryStr,
				new String[] { "start_datetime", "end_datetime" });
		assertEquals("Jan  1 2050 12:00AM", datetimes.get("start_datetime"));
		assertEquals("Feb  1 2050 11:59PM", datetimes.get("end_datetime"));

		//Determining which survey to click on.
		String queryStr = "WITH surveys AS (SELECT ROW_NUMBER() OVER (ORDER BY title ASC) AS row_num, title, "
				+ "survey_id FROM survey WHERE merchant_id = " + MERCHANT_ID + ") SELECT row_num FROM surveys "
				+ "WHERE title = '" + survey1Name + "'";
		String row_num = DatabaseUtils.getColumnValue(queryStr, "row_num");
		clickElement("//*[@id='surveys-table']/tbody/tr[" + row_num + "]/td[1]/img[1]", "xpath");

		//verify values
		assertEquals("1", surveydetailsPage.getStartMonthValue());
		assertEquals("1", surveydetailsPage.getStartDayValue());
		assertEquals("2050", surveydetailsPage.getStartYearValue());
		assertEquals("2", surveydetailsPage.getEndMonthValue());
		assertEquals("1", surveydetailsPage.getEndDayValue());
		assertEquals("2050", surveydetailsPage.getEndYearValue());

		//verify error messages for invalid values
		surveydetailsPage.setStartDateYearField("2000")
						 .clickSaveButton();
		
		verifyTextOnPage("Valid year is required.");
		verifyTextOnPage("Please choose a valid future time.");

		surveydetailsPage.setStartDateYearField("2051")
						 .setEndDateYearField("2000")
						 .clickSaveButton();
		
		verifyTextOnPage("Survey end date must be valid date.");

		surveydetailsPage.setEndDateYearField("2049")
						 .clickSaveButton();
		
		verifyTextOnPage("Survey end date must be in the future, after the start date.");
		
		surveydetailsPage.setEndDateYearField("2051")
						 .clickSaveButton();
		
		waitUntilElementVisible("//td[@class='name' and text()='" + survey1Name + "']", "xpath");

		datetimes = DatabaseUtils.getColumnValues(datetimesQueryStr, new String[] { "start_datetime", "end_datetime" });
		assertEquals("Jan  1 2051 12:00AM", datetimes.get("start_datetime"));
		assertEquals("Feb  1 2051 11:59PM", datetimes.get("end_datetime"));

	}

	@Test
	@TestRail(id = "4575") //Redwood test "Survey 7 - Test Survey Multiple Tabs - CSR"
	public void testSurvey7_multipleTabsCSR() throws Throwable {
		String random = getRandomAlphanumeric(5);
		String msgTemplateName = "Test Multitab Survey Template (Rule)_" + random;
		String pxRuleName = "TestSurvey Multitab Rule_" + random;
		//SQLUtils.deletePXRule("Test Survey Multitab Rule", TestCase.MERCHANT_ID);
		//SQLUtils.deleteCampaignMessageTemplate("Test Multitab Survey Template (Rule)", TestCase.MERCHANT_ID);
		String printedCardNumber = DatabaseUtils.getPrintedCardNumber();

		login();
		switchMerchant();
		//activate the card
		merchantHomePage.clickCustomerServiceLink();
		CustomerAccountLookupPage customerAccountLookupPage = new CustomerAccountLookupPage(driver);
		customerAccountLookupPage.clickActivateCardLink();
		ActivateCardPage activateCardPage = new ActivateCardPage(driver);
		activateCardPage.selectStoreDropdown("1 - General 1");
		activateCardPage.setCardNumberField(printedCardNumber);
		activateCardPage.clickSubmitButton();

		merchantHomePage.hoverMarketingToolsLink(); sleep(500);
		merchantHomePage.clickCampaignMessageTemplates();
		SelectCampaignMessageTemplatePage scmtp = new SelectCampaignMessageTemplatePage(driver);
		scmtp.clickNewCampaignMessageTemplate();
		
		NewCampaignMessageTemplatePage.using(driver)
									  .setLabelField(msgTemplateName)
									  .selectEventTypeEnumIdDropdown("Survey Participant Entered")
									  .selectSurveyIdDropdown("m10101010Survey1 - m10101010 Boot Suite Merchant")
									  .setPullBodyField("Click to take this survey to open in multiple tabs")
									  .clickSubmitButton();
		verifyTextOnPage("Please review and correct the problems with your submission.", false, 1000, 200);
		

		merchantHomePage.hoverProgramAdministrationLink(); sleep(500);
		merchantHomePage.clickRules();
		SelectRulePage selectRulePage = new SelectRulePage(driver);
		selectRulePage.selectNewTypeDropdown("Survey Participant Rule");
		selectRulePage.clickNewButton();

		NewSurveyParticipantRulePage.using(driver)
									.setPxruleRuleNameField(pxRuleName)
									.clickMessageCheckbox()
									.selectMessageTemplateDropdown(msgTemplateName)
									.clickMessageHasPullCheckbox()
									.setMessageOddsField("100")
									.clickWebRegistration()
									.clickSubmitButton()		
									.clickTemplateRulesLink();
		
		Rules_SelectCardTemplatePage sctp = new Rules_SelectCardTemplatePage(driver);
		sctp.clickSelectLink();

		SelectTemplateRulePage strp = new SelectTemplateRulePage(driver);
		waitUntilTextVisible("Select Template Rule");
		strp.clickAttachCheckbox(pxRuleName);
		strp.clickAttachSelectedRulesButton();
		
		navigateToURL(GUEST_BASE_URL + GUEST_PATH);
		GuestWebMemberPortalPage guestWebMemberPortalPage = new GuestWebMemberPortalPage(driver);
		guestWebMemberPortalPage.clickRegister();
		
		GuestWebRegisterPage.using(driver)
							.setPrintedCardField(printedCardNumber)
							.clickSubmit()
							.setRegistrationCodeField(registrationCode)
							.clickSubmit()
							.selectSalutationDropdown("Mr.")
							.setFirstNameField("John")
							.setLastNameField("Smith")
							.setAddressField("125 Main St.")
							.setAddressField2("Apt 1")
							.setCityField("Maynard")
							.selectCountryDropdown("USA")
							.selectStateProvinceDropdown("Massachusetts")
							.setPostalCodeField("01754")
							.setEmailField("js" + printedCardNumber + "@paytronix.com")
							.setUsernameField("cardholder_I" + printedCardNumber)
							.setPasswordField("test1234")
							.setConfirmPasswordField("test1234")
							.clickSubmit();


		RelatedGuestsPage rgp = new RelatedGuestsPage(driver);
		rgp.clickSkipButton();
		
		String timeInMillis = DateUtils.getTimeInMillis();
		GuestWebAccountBalance gwab = new GuestWebAccountBalance(driver);
		String hrefURL = gwab.getSurveyLinkHref();
		String hrefURL_withPort = hrefURL.replace("wal1at-app1.atfleet.test", 
				"wal1at-app1.atfleet.test:" + FAILOVER_PORT);
		gwab.clickSurveyLink(); sleep(2000);
		openNewTab();
		switchToTab(1);
		navigateToURL(hrefURL_withPort);
		m10101010BootSuiteMerchantSurveyPage mbsmsp = new m10101010BootSuiteMerchantSurveyPage(driver);
		mbsmsp.clickCookieBanner()
			  .setOpenResponseField(timeInMillis)
			  .clickSingleSelect1_PrettyGood()
			  .clickSingleSelect2_NeedsImprovement()
			  .clickSingleSelect3_NA()
			  .clickSubmitSurvey();
		
		verifyTextOnPage("Thanks for completing our survey! Check your email for a fun reward.");
		switchToTab(0);
		gwab.clickSurveyLink();
		openNewTab();
		switchToTab(2);
		navigateToURL(hrefURL_withPort);
		mbsmsp.clickCookieBanner();
		verifyTextOnPage("This survey is not available.");
		String queryStr = "SELECT COUNT(survey_answer_id) AS response_num FROM survey_answer WHERE merchant_id = " + 
		MERCHANT_ID + " AND text_value = '" + timeInMillis + "'";
		String response_num = DatabaseUtils.getColumnValue(queryStr, "response_num");
		assertEquals("1",response_num);
		
	}


	

}
