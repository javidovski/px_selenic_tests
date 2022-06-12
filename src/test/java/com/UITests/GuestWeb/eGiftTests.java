package com.UITests.GuestWeb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.UITests.TestCase;
import com.UITests.TestRail;
import com.UITests.GuestWeb.page.EGiftDeliveryDatePage;
import com.UITests.GuestWeb.page.EGiftPaymentInformationPage;
import com.UITests.GuestWeb.page.EGiftProceedToCheckoutPage;
import com.UITests.GuestWeb.page.EGiftRecipientInformationPage;
import com.UITests.GuestWeb.page.EGiftOrderReviewPage;
import com.UITests.GuestWeb.page.PurchaseEgiftCardsForPXGenericTestMerchantPage;
import com.UITests.MerchantAdmin.CreditCardProcessors.page.NewCreditCardProcessorPage;
import com.UITests.MerchantAdmin.CreditCardProcessors.page.SelectCreditCardProcessorPage;
import com.UITests.MerchantAdmin.WebSalePrograms.page.EditWebSaleProgramEGiftPage;
import com.UITests.MerchantAdmin.WebSalePrograms.page.SelectWebSaleProgramPage;
import com.UITests.MerchantAdmin.page.MerchantAdminPage;

public class eGiftTests extends TestCase {

	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}

	@Test
	@TestRail(id = "5846")
	public void testSpreedly_NameWithApostrophe_UK() throws Throwable {
		login();
		switchMerchant();
		String newCCProcessorName = "Spreedly Test Processor " + getRandomAlphanumeric(5);


		
		
		// need to create the Spreedly CC Processor until it exists in the base
		merchantHomePage.clickADMINLink();
		MerchantAdminPage merchantAdminPage = new MerchantAdminPage(driver);
		merchantAdminPage.clickCreditCardProcessorsLink();
			
		SelectCreditCardProcessorPage selectCreditCardProcessorPage = new SelectCreditCardProcessorPage(driver);
		selectCreditCardProcessorPage.selectNewTypeDropdown("Spreedly");
		selectCreditCardProcessorPage.clickNewButton();
		
		NewCreditCardProcessorPage newCreditCardProcessorPage = new NewCreditCardProcessorPage(driver);
		newCreditCardProcessorPage.setCcProcessorLabelField(newCCProcessorName);
		newCreditCardProcessorPage.selectCcProcessorSpreedlyPartnerConfigEnumIdDropdown("Test");
		newCreditCardProcessorPage.setCcProcessorAvsStreet(false);
		newCreditCardProcessorPage.setCcProcessorAvsZip(false);
		newCreditCardProcessorPage.clickSubmitButton();

		// set egift to use this processor
		navigateToURL(PAYTRONIX_BASE_URL + "/pxadmin/webui/select_web_sale_program.srv");

		SelectWebSaleProgramPage swspp = new SelectWebSaleProgramPage(driver);
		swspp.clickEditEGift();
		EditWebSaleProgramEGiftPage ewspegp = new EditWebSaleProgramEGiftPage(driver);
		//get name of existing cc processor and store it in a variable
		String currentCCProcessorName =  ewspegp.getCurrentCreditCardProcessorText();
		System.out.println("current cc processor name is" + currentCCProcessorName);
		ewspegp.selectWebSaleProgramCcProcessorIdDropdown(newCCProcessorName);
		ewspegp.clickSubmitButton();

		openNewTab();
		switchToTab(1);
		navigateToURL(GUEST_BASE_URL + GUEST_PATH + "/egift");

		PurchaseEgiftCardsForPXGenericTestMerchantPage.using(driver).clickLittleNerdsImg().setManualValueField("10.00")
				.setQuantityInputField("1").clickContinueButton();

		if (elementIsVisible("input[value='Continue']", "cssselector", 2000, 500)) {
			PurchaseEgiftCardsForPXGenericTestMerchantPage.using(driver).clickContinueButton();
		}

		EGiftRecipientInformationPage egrip = new EGiftRecipientInformationPage(driver);
		egrip.setRecipientFromField("Robot1").setRecipientToField("Robot2")
				.setRecipientEmailField("bulkmail.qa@paytronix.com")
				.setRecipientEmailConfirmField("bulkmail.qa@paytronix.com").setMsgContentField("Beep Boop Beep")
				.clickWebElement();

		assertEquals("Value:$10.00", egrip.getValueFieldText());
		assertEquals("1", egrip.getResultQuantityFieldText());
		assertEquals("Beep Boop Beep", egrip.getResultMsgFieldText());
		egrip.clickContinue();

		EGiftDeliveryDatePage deliveryDatePage = new EGiftDeliveryDatePage(driver);
		deliveryDatePage.clickAddToCart();
		EGiftProceedToCheckoutPage egptcp = new EGiftProceedToCheckoutPage(driver);
		egptcp.clickCheckout();

		EGiftPaymentInformationPage.using(driver).setBillFirstNameInputField("Jack")
				.setBillLastNameInputField("O'Connell").selectBillCountryInputDropdown("United Kingdom")
				.setBillAddressInputField("1 Bard St.").setBillCityInputField("London")
				.setBillPostalInputField("W10 6TP").setBillPhoneInputField("(978) 456 - 2234")
				.setBillEmailInputField("jackoconnell@paytronix.com").switchTo_spreedly_cc_number_iframe()
				.setCardNumberField("4111111111111111").switchToDefaultContent().clickExpirationMonth("April")
				.clickExpirationYear("2037").switchTo_spreedly_cvv_iframe().setCvvCodeField("123")
				.switchToDefaultContent().clickContinueButton();

		EGiftOrderReviewPage orderReviewPage = new EGiftOrderReviewPage(driver);
		orderReviewPage.clickSubmitOrder();
		if (elementIsVisible("(//input[@value='Submit Order'])[2]", "xpath", 2000, 500)) {
			orderReviewPage.clickSubmitOrder();
		}

		assertTrue(orderReviewPage.getBillingInfoFieldText().contains("Jack O'Connell"),
				"Jack O'Connell does not exist");
		assertTrue(orderReviewPage.getBillingInfoFieldText().contains("1 Bard St."), "1 Bard St does not exist");
		assertTrue(orderReviewPage.getBillingInfoFieldText().contains("London, W10 6TP"),
				"London, W10 6TP does not exist");
		assertTrue(orderReviewPage.getBillingInfoFieldText().contains("United Kingdom"),
				"United Kingdom does not exist");
		assertTrue(orderReviewPage.getBillingInfoFieldText().contains("9784562234"), "9784562234 does not exist");
		assertTrue(orderReviewPage.getBillingInfoFieldText().contains("jackoconnell@paytronix.com"),
				"jackoconnell@paytronix.com does not exist");

		switchToTab(0);
		//reset egift web sale program cc processor back to what it was before you changed it.
		navigateToURL(PAYTRONIX_BASE_URL + "/pxadmin/webui/select_web_sale_program.srv");
		swspp.clickEditEGift();
		ewspegp.selectWebSaleProgramCcProcessorIdDropdown(currentCCProcessorName);
		ewspegp.clickSubmitButton();
		
		merchantAdminPage.clickCreditCardProcessorsLink();		
		selectCreditCardProcessorPage.deleteCCProcessor(newCCProcessorName);

	}

	
}
