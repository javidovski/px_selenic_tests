/**
 * 
 */
package com.UITests.CategoryManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.UITests.TestCase;
import com.UITests.TestRail;
import com.UITests.CategoryManager.page.CategoryManagerPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CategoryManagerTests extends TestCase{


	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}

	@Test
	@TestRail(id = "5845") 
	public void testCreate_delete_category() throws Throwable {
		login();
		switchMerchant();
		merchantHomePage.hoverProgramAdministrationLink();
		merchantHomePage.clickCategoryManager();

		String categoryName = "Test Category" + getRandomAlphanumeric(5);
		CategoryManagerPage categoryManagerPage = new CategoryManagerPage(driver);
		categoryManagerPage.clickCreateButton()
						   .setCatEditionNameField(categoryName)
						   .clickApplyButton()		
						   .clickViewCategory(categoryName);
		
		
		assertEquals(categoryName, categoryManagerPage.getLabelValueFieldText());
		merchantHomePage.hoverProgramAdministrationLink();
		merchantHomePage.clickCategoryManager(); sleep(1000);
		
		categoryManagerPage.clickSelectCategory(categoryName)
						   .clickDeleteButton()
						   .clickApplyButton();
		
		merchantHomePage.clickLogout(); 
	}

}