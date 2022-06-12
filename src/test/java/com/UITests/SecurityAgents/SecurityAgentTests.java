/**
 * 
 */
package com.UITests.SecurityAgents;
import com.UITests.TestCase;
import com.UITests.TestRail;
import com.UITests.MerchantAdmin.page.MerchantAdminPage;
import com.UITests.SecurityAgents.page.NewSecurityAgentPXCPage;
import com.UITests.SecurityAgents.page.SelectSecurityAgentPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class SecurityAgentTests extends TestCase {

	@BeforeEach
	@Override
	public void beforeTest() {
		super.beforeTest();
	}

	@AfterEach
	@Override
	public void afterTest() {
		super.afterTest();
	}


	@Test
	@TestRail(id = "4933") //for test task PXS-69357
	public void testAddNewSecurityAgent() throws Throwable {
		String securityAgentName = "New PXC " + getRandomAlphanumeric(5);
		login();
		switchMerchant();
		merchantHomePage.clickADMINLink();
		MerchantAdminPage merchantAdminPage = new MerchantAdminPage(driver);
		merchantAdminPage.clickSecurityAgentsLink();


		SelectSecurityAgentPage selectSecurityAgentPage = new SelectSecurityAgentPage(driver);
		selectSecurityAgentPage.clickNewSecurityAgentLink();

		NewSecurityAgentPXCPage newSecurityAgentPXCPage = new NewSecurityAgentPXCPage(driver);
		newSecurityAgentPXCPage.selectStoreIdDropdown("1 - General 1");
		newSecurityAgentPXCPage.setAgentNameField(securityAgentName);
		newSecurityAgentPXCPage.selectSoftwareConfigIdDropdown("General 1 PXC Configuration");
		newSecurityAgentPXCPage.clickSubmitButton();

		merchantAdminPage.clickSecurityAgentsLink();
		verifyTextOnPage(securityAgentName);
	}

}