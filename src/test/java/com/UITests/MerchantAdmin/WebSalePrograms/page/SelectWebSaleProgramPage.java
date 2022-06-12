/**
 * 
 */
package com.UITests.MerchantAdmin.WebSalePrograms.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class SelectWebSaleProgramPage extends Page {

	@FindBy(xpath = "//table/tbody/tr[6]/td[1]/a[2]")
	private WebElement editEGift;

	

	private static final String[] TITLE_WORDS = { "Select", "Web", "Sale", "Program" };

	public SelectWebSaleProgramPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	

	public void clickEditEGift() {
		click(editEGift);
	}

}