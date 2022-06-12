/**
 * 
 */
package com.UITests.CardTemplates.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class SelectCardTemplatePage extends Page{

	@FindBy(xpath = "//tr[4]/td[2]/div/a")
	private WebElement editCombinedCardTemplate_gear;

	@FindBy(xpath = "//tr[6]/td[2]/div/a")
	private WebElement editEClubTemplate_gear;

	private static final String[] TITLE_WORDS = { "Select", "Card", "Template" };

	public SelectCardTemplatePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void clickEditCombinedCardTemplate_gear() {
		click(editCombinedCardTemplate_gear);
	}
	
	public void clickEditEClubTemplate_gear() {
		click(editEClubTemplate_gear);
	}

}