/**
 * 
 */
package com.UITests.AccountLookup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.UITests.TestCase;
import com.UITests.TestRail;
import com.UITests.page.CustomerAccountLookupPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class LoginTest1 extends TestCase {

	

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


	@TestRail(id="3913")
	@Test
	public void testLoginTest() throws Throwable {	
		
		login();
		switchMerchant();
				
		merchantHomePage.hoverCustomerServiceLink();
		sleep(500);
		merchantHomePage.clickAccountLookup1();
		CustomerAccountLookupPage customerAccountLookupPage = new CustomerAccountLookupPage(driver);
		assertEquals("Card Number:", customerAccountLookupPage.getCardNumberLabelText());
		
		verifyElementExists_AtLeastNTimes("Activate Card","linkText",1);
			
	}

}