/**
 * 
 */
package com.UITests.GuestWeb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.UITests.TestCase;
import com.UITests.TestRail;

import com.UITests.CardTemplates.page.ManageCardTemplatePage_Combined;
import com.UITests.CardTemplates.page.NewActivationFilterPopup;
import com.UITests.CardTemplates.page.SelectCardTemplatePage;
import com.UITests.GuestWeb.page.GuestWebMemberPortalPage;
import com.UITests.GuestWeb.page.GuestWebRegisterPage;
import com.UITests.MerchantAdmin.page.MerchantAdminPage;
import com.UITests.UtilityClasses.DatabaseUtils;
import com.UITests.page.CSRMainMenuPage;
import com.UITests.page.CustomerAccountLookupPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class GuestWebRegistration extends TestCase {

	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}
	
	

	@Test  
	@TestRail(id = "5258") //Redwood test Activate and Register on Guest Web
	public void testRegActivateGuestWeb() throws Throwable {
		login();
		switchMerchant();
		String printedCardNumber = DatabaseUtils.getPrintedCardNumber();

		//Go to card template page and check "Auto-activate inactive cards on registration through the website"
		merchantHomePage.clickADMINLink();
		MerchantAdminPage merchantAdminPage = new MerchantAdminPage(driver);
		merchantAdminPage.clickCardTemplatesLink();
		SelectCardTemplatePage selectCardTemplatePage = new SelectCardTemplatePage(driver);
		selectCardTemplatePage.clickEditCombinedCardTemplate_gear();
		ManageCardTemplatePage_Combined manageCardTemplatePage = new ManageCardTemplatePage_Combined(driver); sleep(1000);
		manageCardTemplatePage.activateRegGuestWeb(); sleep(1000);
		manageCardTemplatePage.clickSubmitButton(); sleep(1000);
		manageCardTemplatePage.clickActivationFiltersLink(); sleep(2000);

		
		//create new activation filter		
		NewActivationFilterPopup activationFilterPopup = new NewActivationFilterPopup(driver);
		activationFilterPopup.clickCreateFilter(); sleep(2000);
		activationFilterPopup.selectEventDropdown("Activate");
		activationFilterPopup.selectProgramDropdown("PX");
		activationFilterPopup.selectSenderDropdown("WEB"); sleep(1000);
		activationFilterPopup.clickSubmitNewFilter(); sleep(1000);

		
		//open new tab and register card through guest web
		openNewTab();
		switchToTab(1);
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
		.clickOptIn()
		.clickSubmit();
	
		
		//go back to merchant web and verify card is registered
		switchToTab(0);
		navigateToURL(PAYTRONIX_BASE_URL + MERCHANT_HOME_PATH);
		merchantHomePage.hoverCustomerServiceLink(); sleep(500);
		merchantHomePage.clickAccountLookup1();
		CustomerAccountLookupPage customerAccountLookupPage = new CustomerAccountLookupPage(driver);
		customerAccountLookupPage.setCardNumInput(printedCardNumber);
		customerAccountLookupPage.clickSubmitButton(); sleep(500);		
		CSRMainMenuPage csrMainMenuPage = new CSRMainMenuPage(driver);
		assertEquals("Registered",csrMainMenuPage.getCustomerTier());
		assertEquals("cardholder_I" + printedCardNumber,csrMainMenuPage.getUsername());
		

		//Go back to card template page and uncheck "Auto-activate inactive cards on registration through the website"
		merchantHomePage.clickADMINLink();
		merchantAdminPage.clickCardTemplatesLink();
		selectCardTemplatePage.clickEditCombinedCardTemplate_gear();
		manageCardTemplatePage.deactivateRegGuestWeb();
		manageCardTemplatePage.clickSubmitButton(); sleep(2000);
		
		
		//delete the activation filter and log out
		manageCardTemplatePage.clickActivationFiltersLink(); sleep(2000);
		activationFilterPopup.clickActivationFilterTrashImg(); sleep(500);
		activationFilterPopup.clickDelete(); sleep(500);
		activationFilterPopup.clickLogout(); sleep(500);
	}


}