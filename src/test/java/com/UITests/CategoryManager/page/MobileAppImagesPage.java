/**
 * 
 */
package com.UITests.CategoryManager.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class MobileAppImagesPage extends Page{

	@FindBy(xpath = "/descendant::*[@id='LNS_ProgramAdministration']/descendant::a[normalize-space(.)='Category Manager']")
	private WebElement lNSProgramAdministrationLink;

	private static final String[] TITLE_WORDS = { "Mobile", "App", "Images" };

	public MobileAppImagesPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickLNSProgramAdministrationLink() {
		click(lNSProgramAdministrationLink);
	}

}