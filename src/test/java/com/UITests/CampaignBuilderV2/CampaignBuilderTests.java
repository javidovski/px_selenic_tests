/**
 * 
 */
package com.UITests.CampaignBuilderV2;

import com.UITests.TestCase;
import com.UITests.TestRail;

import com.UITests.UtilityClasses.EmailUtils;
import com.UITests.UtilityClasses.DatabaseUtils;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class CampaignBuilderTests extends TestCase {

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

	
	/*
	@Test
	public void testCampaignTestApp3_incognito() throws Throwable {
		driver.get(System.getProperty("PAYTRONIX_BASE_URL", PAYTRONIX_BASE_URL) + "/merchant/home.srv");
		
		
		if (driver.getPageSource().contains("connection is not private")) {        
			Actions builder = new Actions(driver);
		    WebElement advanced = driver.findElement(By.id("details-button"));
		    builder.click(advanced);
		    builder.perform();
		    
		    WebElement proceed = driver.findElement(By.id("proceed-link"));
		    builder.click(proceed);
		    builder.perform();
		}
		
		 

		LogInPage logInPage = new LogInPage(driver);
		logInPage.setMerchantUsernameInputField("test_px_admin");
		logInPage.setMerchantPasswordInputField("test");
		logInPage.clickTakeMeThereButton();

		MerchantHomePage merchantHomePage = new MerchantHomePage(driver);
		merchantHomePage.clickCookieBanner();
		merchantHomePage.clickSWITCHLink();

		SwitchMerchantPage switchMerchantPage = new SwitchMerchantPage(driver);
		switchMerchantPage.setPxidField("10101010");
		switchMerchantPage.clickGoButton();
		merchantHomePage.clickMarketingToolsLink();

		CampaignCenterPage campaignCenterPage = new CampaignCenterPage(driver);
		campaignCenterPage.clickCREATENEWCAMPAIGNButton();

		CampaignBuilderPage campaignBuilderPage = new CampaignBuilderPage(driver);
		campaignBuilderPage.setIsGoingField("New Campaign 14");
		campaignBuilderPage.clickCreateNew();
		
		campaignBuilderPage.clickAdd();
		campaignBuilderPage.clickWebElement4();
		campaignBuilderPage.clickFreeClifBar();
		campaignBuilderPage.setAdjustmentQuantity("10");
		campaignBuilderPage.clickWebElement22();
		campaignBuilderPage.clickGeneral();
		campaignBuilderPage.clickSAVEANDCONTINUE2();
		campaignBuilderPage.clickADDEMAIL();
		campaignBuilderPage.clickWebElement5();
		//Thread.sleep(3000000);
		campaignBuilderPage.clickSaveAndContinue4();
		campaignBuilderPage.clickWebElement23();
		campaignBuilderPage.clickSaveAndContinue22();
		campaignBuilderPage.clickSaveAndContinue22();
		Thread.sleep(1000);
		campaignBuilderPage.clickSaveAsDraft();
	}
*/
	
	@Test
	@TestRail(id="4405")
	public void testCampaignBuilder03_EmailMesssaging_CSR() throws Throwable {
		login();
		switchMerchant();
		String oldOptIn_expected = "17000";
		String newOptIn_expected = "17001";
	
		EmailUtils.deleteEmailsBySubject("Test Bulk Email Personalization",SERVER);
		String queryStr = "SELECT customer_setting_enum_id from user_field_config where label = 'Campaign Text Message Opt In' AND merchant_id=10101010";
		String oldOptIn = DatabaseUtils.getColumnValue(queryStr,"customer_setting_enum_id");		
		assertEquals(oldOptIn_expected,oldOptIn);

		
		String updateStr = "UPDATE user_field_config SET customer_setting_enum_id=" + newOptIn_expected + " WHERE label = 'Campaign Text Message Opt In' AND merchant_id=10101010";
		int numRows = DatabaseUtils.updateTable(updateStr);
		String newOptIn = DatabaseUtils.getColumnValue(queryStr,"customer_setting_enum_id");
		assertEquals(newOptIn_expected,newOptIn);

		
		updateStr = "UPDATE user_field_config SET customer_setting_enum_id=" + oldOptIn + " WHERE label = 'Campaign Text Message Opt In' AND merchant_id=10101010";
		numRows = DatabaseUtils.updateTable(updateStr);
		
		String finalOptIn = DatabaseUtils.getColumnValue(queryStr,"customer_setting_enum_id");
		assertEquals(oldOptIn_expected,finalOptIn);
		
		
		//String printedCardNumber = SQLUtils.getPrintedCardNumber(this);
		String campaignName = "Campaign " + getRandomAlphanumeric(5);
		System.out.println(campaignName);
		
		ArrayList<String> cardNumbers = DatabaseUtils.getPrintedCardNumbers(4);
		
		//EmailUtils.verifyEmailReceived("Test Bulk Email Personalization",SERVER,"300","true");		
		EmailUtils.deleteEmailsBySubject("Test Bulk Email Personalization",SERVER);
	}

}