/**
 * 
 */
package com.UITests.Rules.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.UITests.page.Page;


//you get to this page by clicking "Template Rules" on the left nav
public class Rules_SelectCardTemplatePage extends Page {

	@FindBy(xpath = "//table/tbody/tr[4]/td[1]/a")
	private WebElement selectLink;

	

	private static final String[] TITLE_WORDS = { "Select", "Card", "Template" };

	public Rules_SelectCardTemplatePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void clickSelectLink() {
		click(selectLink);
	}

}