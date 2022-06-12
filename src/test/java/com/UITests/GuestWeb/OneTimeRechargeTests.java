/**
 * 
 */
package com.UITests.GuestWeb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.UITests.TestCase;
import com.UITests.GuestWeb.page.GuestWebMemberPortalPage;
import com.UITests.GuestWeb.page.GuestWebAccountBalancePage;
import com.UITests.GuestWeb.page.GuestLoginPage;
import com.UITests.GuestWeb.page.PXGenericTestMerchantReloadCardPage;
import com.UITests.MerchantAdmin.CreditCardProcessors.page.EditCreditCardProcessorPage;
import com.UITests.MerchantAdmin.CreditCardProcessors.page.SelectCreditCardProcessorPage;
import com.UITests.MerchantAdmin.page.MerchantAdminPage;
import com.UITests.UtilityClasses.DatabaseUtils;
import com.UITests.page.CustomerAccountLookupPage;
import com.UITests.AccountLookup.page.ActivateCardPage;
import com.UITests.page.CSRMainMenuPage;
import com.UITests.AccountLookup.page.RegisterAccountPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OneTimeRechargeTests extends TestCase {

	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}

	@Test
	public void testOneTimeRechargeCCValidation() throws Throwable {
		String printedCardNumber = DatabaseUtils.getPrintedCardNumber();
		login();
		switchMerchant();
		merchantHomePage.clickADMINLink();
		MerchantAdminPage merchantAdminPage = new MerchantAdminPage(driver);
		merchantAdminPage.clickCreditCardProcessorsLink();

		SelectCreditCardProcessorPage selectCreditCardProcessorPage = new SelectCreditCardProcessorPage(driver);
		selectCreditCardProcessorPage.clickWebElement();

		EditCreditCardProcessorPage editCreditCardProcessorPage = new EditCreditCardProcessorPage(driver);
		editCreditCardProcessorPage.setCcProcessorAvsStreet(false);
		editCreditCardProcessorPage.setCcProcessorAvsZip(false);
		editCreditCardProcessorPage.setCcProcessorCvvMatch(false);;
		editCreditCardProcessorPage.clickSubmitButton();

		//ViewCreditCardProcessorPage viewCreditCardProcessorPage = new ViewCreditCardProcessorPage(driver);
		//viewCreditCardProcessorPage.clickWebElement();

		merchantAdminPage.clickMerchantHomeLink();
		merchantHomePage.clickCustomerServiceLink();
		CustomerAccountLookupPage customerAccountLookupPage = new CustomerAccountLookupPage(driver);
		customerAccountLookupPage.clickActivateCardLink();

		ActivateCardPage activateCardPage = new ActivateCardPage(driver);
		activateCardPage.selectStoreDropdown("corp - Corporate");
		activateCardPage.setCardNumberField(printedCardNumber);
		activateCardPage.clickSubmitButton();

		CSRMainMenuPage cSRMainMenuPage = new CSRMainMenuPage(driver);
		cSRMainMenuPage.clickRegisterAccountLink();

		RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
		registerAccountPage.selectSalutationDropdown("Mr.");
		registerAccountPage.setFirstnameField("test");
		registerAccountPage.setLastnameField("testy");
		registerAccountPage.setAddressField("1 main street");
		registerAccountPage.setCityField("brockton");
		registerAccountPage.selectStateDropdown("MA");
		registerAccountPage.setZipcodeField("02302");
		registerAccountPage.setEmailField("nowhere@paytronix.com");
		registerAccountPage.setEmailVerifyField("nowhere@paytronix.com");
		registerAccountPage.setUsernameField("nowhere");
		registerAccountPage.setPasswordField("test");
		registerAccountPage.setPasswordField("test123");
		registerAccountPage.setPasswordField2("test123");
		registerAccountPage.clickSubmitRegistrationInformationButton();

		DatabaseUtils.markEmailAsVerified(printedCardNumber,MERCHANT_ID);

		openNewTab();
		switchToTab(1);
		navigateToURL(GUEST_BASE_URL + "/guest/");
		clickCookieBanner();

		GuestWebMemberPortalPage guestWebMemberPortalPage = new GuestWebMemberPortalPage(driver);
		guestWebMemberPortalPage.clickLoginLink();

		GuestLoginPage guestLoginPage = new GuestLoginPage(driver);
		guestLoginPage.setUsernameField("nowhere");
		guestLoginPage.setPasswordField("test123");
		guestLoginPage.clickLoginButton();

		GuestWebAccountBalancePage GuestWebAccountBalancePage = new GuestWebAccountBalancePage(
				driver);
		GuestWebAccountBalancePage.clickReloadCardBtn();

		PXGenericTestMerchantReloadCardPage pXGenericTestMerchantReloadCardPage = new PXGenericTestMerchantReloadCardPage(
				driver);
		pXGenericTestMerchantReloadCardPage.setReloadAmountField("12.00");
		pXGenericTestMerchantReloadCardPage.setNameOnCardField("test testy");
		pXGenericTestMerchantReloadCardPage.selectCardTypeDropdown("Visa");
		pXGenericTestMerchantReloadCardPage.setCardNumberField("4111111111111111");
		pXGenericTestMerchantReloadCardPage.setCVVCodeField("121");
		pXGenericTestMerchantReloadCardPage.selectExpireYearDropdown("2023");
		pXGenericTestMerchantReloadCardPage.setAddressField("1 main street");
		pXGenericTestMerchantReloadCardPage.setCityField("Brockton");
		pXGenericTestMerchantReloadCardPage.selectStateDropdown("Massachusetts"); sleep(500);
		pXGenericTestMerchantReloadCardPage.setPostalCodeField("02302");
		pXGenericTestMerchantReloadCardPage.clickWebElement2();
		pXGenericTestMerchantReloadCardPage.clickSubmitButton();
		assertEquals("$12.00", pXGenericTestMerchantReloadCardPage.getAmountFieldText());
		assertEquals("Succeeded", pXGenericTestMerchantReloadCardPage.getStatusFieldText());

		switchToTab(0);

		merchantHomePage.clickADMINLink();
		merchantAdminPage.clickCreditCardProcessorsLink();
		selectCreditCardProcessorPage.clickWebElement();
		editCreditCardProcessorPage.setCcProcessorAvsStreet(true);
		editCreditCardProcessorPage.setCcProcessorAvsZip(true);
		editCreditCardProcessorPage.setCcProcessorCvvMatch(true);
		editCreditCardProcessorPage.clickSubmitButton();
		//viewCreditCardProcessorPage.clickLogoutLink();

		DatabaseUtils.resetCard(printedCardNumber,MERCHANT_ID);
	}

	


}