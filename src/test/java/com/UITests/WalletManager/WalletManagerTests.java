/**
 * 
 */
package com.UITests.WalletManager;

import com.UITests.TestCase;
import com.UITests.CategoryManager.page.CategoryManagerPage;
import com.UITests.CategoryManager.page.MobileAppImagesPage;
import com.UITests.MerchantHome.page.MerchantHomePage;
import com.UITests.WalletManager.page.WalletManagerPage;
import com.UITests.page.CampaignCenterPage;
import com.UITests.page.LogInPage;
import com.UITests.page.SwitchMerchantPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


public class WalletManagerTests extends TestCase {



	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}


	@Test
	public void testWalletManagerTest() throws Throwable {
		login();
		switchMerchant();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		merchantHomePage.clickProgramAdministrationLink();

		MobileAppImagesPage mobileAppImagesPage = new MobileAppImagesPage(driver);
		mobileAppImagesPage.clickLNSProgramAdministrationLink();

		String categoryName1 = "Jelly Beans";
		String categoryName2 = "Candy Bar";
		CategoryManagerPage categoryManagerPage = new CategoryManagerPage(driver);
		
		categoryManagerPage.clickCreateButton();
		categoryManagerPage.setCatEditionNameField(categoryName1);
		categoryManagerPage.clickApplyButton();
		sleep(500);
		categoryManagerPage.clickCreateButton();
		categoryManagerPage.setCatEditionNameField(categoryName2);
		categoryManagerPage.clickApplyButton();
		sleep(500);
		
		categoryManagerPage.clickLNSProgramAdministrationLink();

		WalletManagerPage walletManagerPage = new WalletManagerPage(driver);
		walletManagerPage.clickCreateButton();
		walletManagerPage.setNameField("Free " + categoryName1 + " Wallet");
		walletManagerPage.clickCategoryDropdown();
		//WebElement reactElement = driver.findElement(By.id("react-select-5-option-1"));
		//Object attr = executor.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", reactElement);
		//System.out.println(attr);
		
		walletManagerPage.selectCategory();
		sleep(50000);
		walletManagerPage.clickAPPLYButton();
		walletManagerPage.clickLNSProgramAdministrationLink();

		sleep(1000);
		WebElement checkbox = driver.findElement(By.xpath("//td[text()='" + categoryName2 + "']" + 
		"/preceding-sibling::td[contains(@class,'selection-cell')]/descendant::input[@type='checkbox']"));
		
		executor.executeScript("arguments[0].click();",checkbox);
		categoryManagerPage.clickDeleteButton();
		categoryManagerPage.clickApplyButton();
		
	}

	

}