/**
 * 
 */
package com.UITests.AccountLookup;

import com.UITests.TestCase;
import com.UITests.TestRail;

import com.UITests.AccountLookup.page.ActivateCardPage;
import com.UITests.AccountLookup.page.RegisterAccountPage;
import com.UITests.UtilityClasses.DatabaseUtils;
import com.UITests.page.CSRMainMenuPage;
import com.UITests.page.CustomerAccountLookupPage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class RegActivatePXSWeb extends TestCase {

	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
		
		
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
		System.out.println("Test finished!");
	}

	
	@Test
	@TestRail(id="3907")
	public void testRegActivatePXSWeb() throws Throwable {
		login();
		switchMerchant();
		String printedCardNumber = DatabaseUtils.getPrintedCardNumber();

		//activate the card
		merchantHomePage.clickCustomerServiceLink();
		CustomerAccountLookupPage customerAccountLookupPage = new CustomerAccountLookupPage(driver);
		customerAccountLookupPage.clickActivateCardLink();
		ActivateCardPage activateCardPage = new ActivateCardPage(driver);
		activateCardPage.selectStoreDropdown("1 - General 1");
		activateCardPage.setCardNumberField(printedCardNumber);
		activateCardPage.clickSubmitButton();

		//verify it's active and unregistered
		CSRMainMenuPage csrMainMenuPage = new CSRMainMenuPage(driver);
		assertEquals(csrMainMenuPage.getCustomerTier(),"Unregistered");
		assertEquals(csrMainMenuPage.getCardStatus(),"ACTIVE");
		csrMainMenuPage.clickRegisterAccountLink();

		//register the card
		//RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
		RegisterAccountPage.using(driver)
						   .setCardNumberField(printedCardNumber)
						   .selectSalutationDropdown("Mr.")
						   .setFirstnameField("Joe")
						   .setLastnameField("Smith")
						   .setAddressField("80 Bridge St.")
						   .setCityField("Newton")
						   .selectStateDropdown("MA")
						   .setZipcodeField("02458")
						   .setEmailField("js" + printedCardNumber + "@paytronix.com")
						   .setEmailVerifyField("js" + printedCardNumber + "@paytronix.com")
						   .setUsernameField("cardHolderI_" + printedCardNumber)
						   .setPasswordField("test1234")
						   .setPasswordField2("test1234")
						   .clickOptIn()
						   .clickSubmitRegistrationInformationButton();
		
		
		//verify it's active and registered
		assertEquals("Registered",csrMainMenuPage.getCustomerTier());
		assertEquals("ACTIVE",csrMainMenuPage.getCardStatus());		
		assertEquals("cardHolderI_" + printedCardNumber,csrMainMenuPage.getUsername());
	}

}