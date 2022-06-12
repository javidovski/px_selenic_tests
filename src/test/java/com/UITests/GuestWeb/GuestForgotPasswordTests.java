/**
 * 
 */
package com.UITests.GuestWeb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.UITests.TestCase;
import com.UITests.TestRail;
import com.UITests.GuestWeb.page.GuestWebMemberPortalPage;
import com.UITests.GuestWeb.page.GuestWebRegisterPage;
import com.UITests.GuestWeb.page.GuestWebAccountBalancePage;
import com.UITests.GuestWeb.page.GuestWebForgotPasswordPage;
import com.UITests.GuestWeb.page.GuestLoginPage;
import com.UITests.GuestWeb.page.RelatedGuestsPage;
import com.enums.DefaultCardInfo;
import com.enums.UserInfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuestForgotPasswordTests extends TestCase {

	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}

	// login, switch merchant, click activate link steps
	public void startUp() throws Throwable {
		System.out.println("Log in and switch merchat ...");
		login();
		switchMerchant();

		// Do not add sleep() between hover and the click becuase the nav will dissappear right away.
		merchantHomePage.hoverCustomerServiceLink();
		merchantHomePage.clickActivateCardLink();
		// adding sleep() after hover and click
		sleep(500);
	}

	
	public void registerForm() throws Throwable {
		GuestWebMemberPortalPage guestWebMemberPortalPage = new GuestWebMemberPortalPage(driver);
		guestWebMemberPortalPage.clickRegister();

		GuestWebRegisterPage guestWebRegisterPage = new GuestWebRegisterPage(driver);
		guestWebRegisterPage.registerForm(DefaultCardInfo.CARD_NUM.toString(), DefaultCardInfo.REGISTRATION_CODE.toString());
	}


	@TestRail(id="6671")
	@Test
	public void forgotPasswordTest() throws Throwable {
		// reset Card in order to reuse the same card everytime
		resetCard(DefaultCardInfo.CARD_NUM.toString(), MERCHANT_ID);
		startUp();
		activateCard(DefaultCardInfo.CARD_NUM.toString());
	
		// switch to new tab to navigate to Guest URL
		openNewTab();
		switchToTab(1);
		navigateToURL(GUEST_BASE_URL + "/guest/");

		// Register the card and fill out the form
		registerForm();
		System.out.println("Successfull filling out the form.");

		RelatedGuestsPage relatedGuestsPage = new RelatedGuestsPage(driver);		
		
		if (Boolean.parseBoolean(relatedGuestsPage.getSkipButtonText())) {
			System.out.println("Couldn't get to the next page after registering");
			System.out.println(!Boolean.parseBoolean(relatedGuestsPage.getSkipButtonText()));
			return;
		}
		System.out.println("\nSuccessfully registered the card.\n");
		relatedGuestsPage.clickSkipButton();
		sleep(500);
		


		// asserting correct Card num appears after log in
		GuestWebAccountBalancePage guestWebAccountBalancePage = new GuestWebAccountBalancePage(driver);
		if (Boolean.parseBoolean(guestWebAccountBalancePage.getStoredValue())) {
			System.out.println("Couldn't get to the next page after registering");
			return;
		}
		assertEquals(DefaultCardInfo.CARD_NUM.toString(), guestWebAccountBalancePage.getNavCardAccountNum());
		assertEquals("Card Number: 1010 1010 9000 0303", guestWebAccountBalancePage.getManageSectionCard());
		guestWebAccountBalancePage.clickLogoutLink();
		sleep(500);


		// Try to log in with an incorrect password
		GuestLoginPage guestLoginPage = new GuestLoginPage(driver);
		
		guestLoginPage.login(UserInfo.USERNAME.toString(), "wrongPass");
		assertEquals("The username could not be found or the password you entered was incorrect. Please try again.", guestLoginPage.getLoginErrorMessage());
		System.out.println("\nSuccessfull in failed log in attemp 1");
		
		
		guestLoginPage.login(UserInfo.USERNAME.toString(), "anotherWrongPass");
		assertEquals("The username could not be found or the password you entered was incorrect. Please try again.",
		guestLoginPage.getLoginErrorMessage());
		System.out.println("Successfull in failed log in attemp 2\n");


		guestLoginPage.clickRequestNewPassword();
		System.out.println("Forgot password Page");
		
		GuestWebForgotPasswordPage guestWebForgotPasswordPage = new GuestWebForgotPasswordPage(
				driver);
		assertEquals("Use this page to request that a new password be emailed to you. If you know your username, enter it below.", guestWebForgotPasswordPage.getUserNameMessage());
		assertEquals("If you are a cardholder and cannot remember your username, you may enter your card number below.",
		guestWebForgotPasswordPage.getCardNumMessage());
		assertEquals(
				"If you are a cardholder and cannot remember either of the above, you can enter your email address below.",
				guestWebForgotPasswordPage.getEmailMessage());
				guestWebForgotPasswordPage.clickLoginLink();

		System.out.println("Going back and login with correct credentials\n\n");
		// login with correct credentials
		guestLoginPage.login(UserInfo.USERNAME.toString(), UserInfo.PASSWORD.toString());
		assertEquals(DefaultCardInfo.CARD_NUM.toString(), guestWebAccountBalancePage.getNavCardAccountNum());
		assertEquals("Card Number: 1010 1010 9000 0303", guestWebAccountBalancePage.getManageSectionCard());
	}

}