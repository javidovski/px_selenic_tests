package com.UITests.GuestWeb.page;

import org.openqa.selenium.WebDriver;
import com.UITests.page.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RelatedGuestsPage extends Page {

	@FindBy(id = "relatedGuestSkipButton")
	private WebElement skipButton;

	
	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Related", "Guests" };

	public RelatedGuestsPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public String getSkipButtonText() {
		return waitFor(skipButton).getText();
	}
	
	public void clickSkipButton() {
		click(skipButton);
	}

}
