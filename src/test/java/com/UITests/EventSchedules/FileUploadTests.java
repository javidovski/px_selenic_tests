package com.UITests.EventSchedules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.UITests.TestCase;
import com.UITests.AccountLookup.page.ActivateCardPage;
import com.UITests.AccountLookup.page.RegisterAccountPage;
import com.UITests.EventSchedules.page.NewCampaignFileUploadPage;
import com.UITests.EventSchedules.page.NewFileUploadSchedulePage;
import com.UITests.EventSchedules.page.NewScheduledReportDropPage;
import com.UITests.EventSchedules.page.SelectSchedulePage;
import com.UITests.EventSchedules.page.SelectScheduledFileUploadPage;
import com.UITests.EventSchedules.page.SelectScheduledReportDropPage;
import com.UITests.MerchantAdmin.page.MerchantAdminPage;
import com.UITests.Paytronix.ScheduledFileUploads.page.PXSFileUploadInfoPage;
import com.UITests.UtilityClasses.DatabaseUtils;
import com.UITests.UtilityClasses.DateUtils;
import com.UITests.UtilityClasses.EmailUtils;
import com.UITests.UtilityClasses.FileUtils;
import com.UITests.page.CSRMainMenuPage;
import com.UITests.page.CustomerAccountLookupPage;

public class FileUploadTests extends TestCase {
	
	@BeforeEach
	public void beforeTest() {
		super.beforeTest();		
	}

	@AfterEach
	public void afterTest() {
		super.afterTest();
		System.out.println("Test finished!");
	}

	
	@Test
	public void testCampaignFilePickup1() throws Throwable {
		EmailUtils.deleteEmailsBySubject("Test (non-LSM) template",SERVER);
		String randomID = getRandomAlphanumeric(5);
		String uniqueSchedule = "Upload Schedule - " + randomID;
		String uniqueUpload = "File Upload - " + randomID;
		String uniqueFileName = "single_row_file_" + randomID + ".txt";
		String uniqueCampaign = "Campaign - " + randomID;
		String agentName = getHostname() + "eng.pxs.cloud";
		String filePickupSite = "ftp://" + agentName + "/" + uniqueFileName;
		System.out.println("filePIckupSite is" + filePickupSite);
		String walletCode = "7";
		String adjustmentAmount = "1";
		String messageTemplateCode = "1";
		
		String printedCardNumber = DatabaseUtils.getPrintedCardNumber();
		System.out.println(agentName);
		String sendingDate = DateUtils.getFutureTimeByMinutes(45,"yyyy-MM-dd'T'HH:mm:ss");
		System.out.println("sendingDate is " + sendingDate);
		
		String finalDestination = "C:\\Users\\jmartin\\eclipse-workspace\\px_selenic_tests\\src\\test\\resources\\" + 
				   "CampaignUploadsRoot\\" + uniqueFileName;
		FileUtils.copyFile("C:\\Users\\jmartin\\eclipse-workspace\\px_selenic_tests\\src\\test\\" + 
						   "resources\\upload_files\\campaign_uploads\\single_row_campaign.txt",
						   finalDestination);
		int daysToExpire = 2;
		String expDate = DateUtils.getDate("yyyy-MM-dd",daysToExpire);
		System.out.println("expDate is "  + expDate);
		int row = 1;
		FileUtils.replaceTextInFile(finalDestination,"card_number_" + row, printedCardNumber);
		FileUtils.replaceTextInFile(finalDestination,"campaign_label_" + row, uniqueCampaign);
		FileUtils.replaceTextInFile(finalDestination,"wallet_code_" + row, walletCode);
		FileUtils.replaceTextInFile(finalDestination,"adjustment_amount_" + row, adjustmentAmount);
		FileUtils.replaceTextInFile(finalDestination,"expiration_date_" + row, expDate);
		FileUtils.replaceTextInFile(finalDestination,"message_template_code_" + row, messageTemplateCode);
		FileUtils.replaceTextInFile(finalDestination,"send_date_" + row, sendingDate);
		
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

		//verify it's active and unregistered
		CSRMainMenuPage csrMainMenuPage = new CSRMainMenuPage(driver);
		assertEquals(csrMainMenuPage.getCustomerTier(),"Unregistered");
		assertEquals(csrMainMenuPage.getCardStatus(),"ACTIVE");
		csrMainMenuPage.clickRegisterAccountLink();
		
		RegisterAccountPage.using(driver)
		   .setCardNumberField(printedCardNumber)
		   .selectSalutationDropdown("Mr.")
		   .setFirstnameField("Joe")
		   .setLastnameField("Smith")
		   .setAddressField("80 Bridge St.")
		   .setCityField("Newton")
		   .selectStateDropdown("MA")
		   .setZipcodeField("02458")
		   .setEmailField(EMAIL)
		   .setEmailVerifyField(EMAIL)
		   .setUsernameField("cardholder_I" + printedCardNumber)
		   .setPasswordField("test1234")
		   .setPasswordField2("test1234")
		   .clickSubmitRegistrationInformationButton();
		
		merchantHomePage.clickADMINLink();
		MerchantAdminPage merchantAdminPage = new MerchantAdminPage(driver);
		merchantAdminPage.clickEventSchedulesLink();
		
		String fullTime = DateUtils.getFutureTimeByMinutes(45,"HH:mm");
		System.out.println("fullTime is " + fullTime);
		String hours = DateUtils.reformatTime("HH:mm", "HH", fullTime);
		System.out.println("hours is " + hours);
		String minutes = DateUtils.reformatTime("HH:mm", "mm", fullTime);
		System.out.println("minutes is " + minutes);
		String dayNumber = DateUtils.getDate("dd",0);
		System.out.println("dayNumber is " + dayNumber);
		String monthNumber = DateUtils.getDate("M",-1);
		System.out.println("monthNumber before decrement is " + monthNumber);
		monthNumber =  String.valueOf(Integer.parseInt(monthNumber)-1);
		System.out.println("monthNumber after decrement is " + monthNumber);
		
		//create file drop schedule
		SelectSchedulePage selectSchedulePage = new SelectSchedulePage(driver);
		selectSchedulePage.selectNewScheduleType("Scheduled File Upload Schedule"); sleep(1000);
		selectSchedulePage.clickNewScheduleButton(); sleep(500);
		NewFileUploadSchedulePage.using(driver)
				.setScheduleNameField(uniqueSchedule)
				.setScheduleHoursField(hours)
				.setScheduleMinutesField(minutes)
				.setScheduleDaysOfMonthField(dayNumber)
				.selectMonths(monthNumber)
				.selectDaysOfWeek("ALL")
				.clickSubmit(); sleep(500);
		verifyTextOnPage("View Scheduled File Upload Schedule \"" + uniqueSchedule + "\"");
		
		
		//navigate to and create file drop
		navigateToURL(PAYTRONIX_BASE_URL + "/pxadmin/webui/select_scheduled_report_drop.srv"); sleep(1000);
		SelectScheduledReportDropPage ssrdp = new SelectScheduledReportDropPage(driver);
		ssrdp.clickNewReportDrop(); sleep(1000);
		NewScheduledReportDropPage.using(driver)
				.setURLField(filePickupSite)  //need to set up FTP Server
				.setUsernameField("anonymous")
				.clickSubmit();
		
		assertTrue(elementIsVisible("//span[contains(.,'View Scheduled Report Drop')]", "xpath", 5000, 1000));
		
		
		//navigate to and create scheduled file upload
		navigateToURL(PAYTRONIX_BASE_URL + "/pxadmin/webui/select_file_upload.srv");
		SelectScheduledFileUploadPage ssfup = new SelectScheduledFileUploadPage(driver);
		ssfup.selectNewUploadType("Campaign Info File Upload");
		ssfup.clickNewUploadButton(); sleep(1000);
		NewCampaignFileUploadPage.using(driver)
				.setLabelField(uniqueUpload)
				.selectSchedule(uniqueSchedule)
				.selectFilePickupSite(filePickupSite)
				.setNotifyAddressField(EMAIL)
				.setFailureNotifyAddressField(EMAIL)
				.clickSubmit();
		assertTrue(elementIsVisible("//span[.='View Campaign File Upload \"" + uniqueUpload + "\"']",
				"xpath",5000,1000));
		
		
		//navigate to, invoke, and verify file upload message
		navigateToURL(PAYTRONIX_BASE_URL + "/pxadmin/fileuploadtool.srv");
		PXSFileUploadInfoPage pfuip = new PXSFileUploadInfoPage(driver);

		String recentButtonXPath = "//td[.='" + uniqueUpload + "']/preceding-sibling::td/a[.='Recent']";
		System.out.println("recentButtonXPath is " + recentButtonXPath);
		waitUntilElementClickable(recentButtonXPath,"xpath",10000);
		clickElement(recentButtonXPath,"xpath");
		
		pfuip.set_confirmInvokeFileUploadCheckbox(true);
		pfuip.clickInvokeFileUploadNow();
		
		String today = DateUtils.getDate("yyyy-MM-dd",0);
		String rowTextXPath =  "//td[.='" + SERVER + "']/following-sibling::td[contains(.,'" + today + "')]" + 
		"/following-sibling::td[contains(.,'Succeeded at " + today + "')]";
		System.out.println("rowTextXPath is " + rowTextXPath);
				
		 // 25 retries to locate this element on the page, wait 10 seconds after each attempt		 		 
		waitForElementWithRefresh(rowTextXPath,"xpath",250000,10000);
			
		
	}

}
