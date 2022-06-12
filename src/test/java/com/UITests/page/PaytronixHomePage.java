/**
 * 
 */
package com.UITests.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PaytronixHomePage extends Page{

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/b/a")
	private WebElement webElement;

	private static final String[] TITLE_WORDS = { "Paytronix", "Home" };

	public PaytronixHomePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickWebElement() {
		click(webElement);
	}

}