/**
 * 
 */
package com.UITests.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.UITests.TestCase;
import com.UITests.TestRail;
import com.UITests.MerchantAdmin.page.MerchantAdminPage;
import com.UITests.Users.page.SelectUserPage;
import com.UITests.Users.page.UploadNewUserPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class NewUserUpload extends TestCase {

	@BeforeEach
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
	}

	
	@Test
	@TestRail(id="3920")
	public void testNewUserUpload() throws Throwable {
		login();
		switchMerchant();

		merchantHomePage.clickADMINLink();
		MerchantAdminPage merchantAdminPage = new MerchantAdminPage(driver);
		merchantAdminPage.clickUsersLink();

		SelectUserPage selectUserPage = new SelectUserPage(driver);
		selectUserPage.clickUploadNew();

		UploadNewUserPage uploadNewUserPage = new UploadNewUserPage(driver);
		uploadNewUserPage.setField("/Users/jeffmartin/Desktop/download_upload_users.testUploadValidNewUsers.txt");
		uploadNewUserPage.clickSubmitButton();


	}

}