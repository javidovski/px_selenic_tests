package com.UITests.ReportCenter;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.UITests.TestCase;
import com.UITests.TestRail;

import com.UITests.AccountLookup.page.ActivateCardPage;
import com.UITests.AccountLookup.page.RegisterAccountPage;
import com.UITests.ReportCenter.page.GeneralGuestAnalysisReportPage;
import com.UITests.ReportCenter.page.GuestAnalysisReportAccountFilterPage;
import com.UITests.ReportCenter.page.GuestAnalysisReportCustomizeOutputPage;
import com.UITests.ReportCenter.page.RunReportsPage;
import com.UITests.UtilityClasses.FileUtils;
import com.UITests.UtilityClasses.DatabaseUtils;
import com.UITests.page.AccountFilterPage;
import com.UITests.page.CSRMainMenuPage;
import com.UITests.page.CustomerAccountLookupPage;
 
//comment s
public class ReportCenterTests extends TestCase {

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
	@TestRail(id="4404") //Redwood test "Guest Analysis Report 1 - HTML - CSR"
	public void testGuestAnalysisHTML() throws Throwable {		
		DatabaseUtils.deleteAccountFilter("Guest Analysis Filter HTML", TestCase.MERCHANT_ID);
		String printedCardNumber = DatabaseUtils.getPrintedCardNumber();

		login();
		switchMerchant();

		//activate the card
		merchantHomePage.clickCustomerServiceLink();
		CustomerAccountLookupPage customerAccountLookupPage = new CustomerAccountLookupPage(driver);
		customerAccountLookupPage.clickActivateCardLink();
		ActivateCardPage activateCardPage = new ActivateCardPage(driver);
		activateCardPage.selectStoreDropdown("1 - General 1");
		activateCardPage.setCardNumberField(printedCardNumber);
		activateCardPage.clickSubmitButton();

		//register the card
		CSRMainMenuPage csrMainMenuPage = new CSRMainMenuPage(driver);
		csrMainMenuPage.clickRegisterAccountLink();
		RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
		registerAccountPage.setCardNumberField(printedCardNumber);
		registerAccountPage.selectSalutationDropdown("Mr.");
		registerAccountPage.setFirstnameField("Joe");
		registerAccountPage.setLastnameField("Smith");
		registerAccountPage.setAddressField("80 Bridge St.");
		registerAccountPage.setCityField("Newton");
		registerAccountPage.selectStateDropdown("MA");
		registerAccountPage.setZipcodeField("02458");
		registerAccountPage.setEmailField("js" + printedCardNumber + "@paytronix.com");
		registerAccountPage.setEmailVerifyField("js" + printedCardNumber + "@paytronix.com");
		registerAccountPage.setUsernameField("cardHolderI_" + printedCardNumber);
		registerAccountPage.setPasswordField("test1234");
		registerAccountPage.setPasswordField2("test1234");
		registerAccountPage.clickSubmitRegistrationInformationButton();

		merchantHomePage.clickReportCenterLink();

		RunReportsPage runReportsPage = new RunReportsPage(driver);
		runReportsPage.clickGuestAnalysisDetailLink();
		GuestAnalysisReportAccountFilterPage garafp = new GuestAnalysisReportAccountFilterPage(driver);
		garafp.clickSelectAFilter();
		garafp.clickAccountFilterForATs();
		garafp.clickGoButton();
		AccountFilterPage accountFilterPage = new AccountFilterPage(driver);
		accountFilterPage.setAccountFilterLabelField("Guest Analysis Filter HTML");
		accountFilterPage.setStartCardInputField(printedCardNumber);
		accountFilterPage.setEndCardInputField(printedCardNumber);
		accountFilterPage.setTotalCardInputField("1");
		accountFilterPage.clickSaveContinueButton();
		GuestAnalysisReportCustomizeOutputPage garcop = new GuestAnalysisReportCustomizeOutputPage(driver);
		garcop.selectReportType("HTML");
		garcop.clickRunDemographicReportButton();
		sleep(3000);
		GeneralGuestAnalysisReportPage ggarp = new GeneralGuestAnalysisReportPage(driver);
		String runDate = getElementText(
				"//div[@id='legacy-content']/div/div/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]",
				"xpath");
		ggarp.clickDownloadReportLink(); sleep(5000);
		openNewTab();
		switchToTab(1);
		String baseDir = TestCase.DOWNLOAD_DIR;
		String reportFileName = FileUtils.getFileLastModified("*GeneralGuestAnalysisReport*.html", baseDir);
		String reportFilePath = baseDir + reportFileName;

		//report verification
		navigateToURL("file://" + reportFilePath);
		String reportInfo = getElementText("/html/body/table[1]/tbody/tr/td[1]", "xpath");
		verifyStringsEqual(reportInfo, "General Guest Analysis Report Account Filter: Guest Analysis Filter HTML");
		verifyTextInElement("/html/body/table[1]/tbody/tr/td[3]", "xpath", "Report Date: " + runDate);
		verifyTextInElement("/html/body/table[2]/tbody/tr[1]/td[1]/b", "xpath", "Email Opt-In");
		verifyTextInElement("/html/body/table[2]/tbody/tr[1]/td[3]/b", "xpath", "Title");
		verifyTextInElement("/html/body/table[2]/tbody/tr[1]/td[4]/b", "xpath", "First Name");
		verifyTextInElement("/html/body/table[2]/tbody/tr[1]/td[5]/b", "xpath", "Last Name");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[6]/b",
				"xpath","Address");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[7]/b",
				"xpath","Address 2");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[8]/b",
				"xpath","City");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[9]/b",
				"xpath","State/Province");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[10]/b",
				"xpath","Zip/Postal Code");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[11]/b",
				"xpath","Card Template");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[12]/b",
				"xpath","Tier");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[13]/b",
				"xpath","Card Number");
		
		verifyTextInElement("//table/tbody/tr[2]/td[1]","xpath","Yes");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[14]/b",
				"xpath","Enroll Date");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[15]/b",
				"xpath","Enroll Store Code");
		verifyTextInElement("//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[16]/b",
				"xpath","Enroll Store Name");
		verifyTextInElement( "//table/tbody/tr[td[1][normalize-space()=\"Email Opt-In\"]]/td[17]/b",
				"xpath","Last Guest Activity Date");
		verifyTextInElement("/html/body/table[2]/tbody/tr[2]/td[11]","xpath",
				"Combined Card Template");
		verifyTextInElement("/html/body/table[2]/tbody/tr[2]/td[12]","xpath",
				"Registered");
		verifyTextInElement("/html/body/table[2]/tbody/tr[2]/td[13]","xpath",
				printedCardNumber);
		

		switchToTab(0);
		merchantHomePage.clickLogout();
		FileUtils.deleteFile(reportFilePath);
		DatabaseUtils.resetCard(printedCardNumber, TestCase.MERCHANT_ID);
		
	}


}
