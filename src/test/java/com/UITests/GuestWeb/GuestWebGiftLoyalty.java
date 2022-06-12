package com.UITests.GuestWeb;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.UITests.TestCase;
import com.UITests.TestRail;

import com.UITests.GuestWeb.page.GiftCardOrderPage;


public class GuestWebGiftLoyalty extends TestCase {

	
	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}
	
	@Test  
	@TestRail(id = "5256")
	public void testNavigateToCardSales() throws Throwable {
		navigateToURL(GUEST_BASE_URL + GUEST_PATH + "/card-sales");
		GiftCardOrderPage gcop = new GiftCardOrderPage(driver);
		assertTrue(gcop.headerVisible());
		assertTrue(gcop.personalizationTextVisible());
		assertTrue(gcop.shippingInputTextVisible());
		assertTrue(gcop.shippingMethodTextVisible());
	}
}
