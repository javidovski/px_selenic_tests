package com.UITests.Paytronix.ScheduledFileUploads.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.UITests.page.Page;

public class PXSFileUploadInfoPage extends Page {
	
	
	@FindBy(name = "invoke_file_upload")
	private WebElement confirmInvokeFileUploadCheckbox;
	
	@FindBy(xpath = "//input[@value='Invoke File Upload Now']")
	private WebElement invokeFileUploadNow;
	
	private static final String[] TITLE_WORDS = { "PXS", "FileUpload", "Information" };
	
	public PXSFileUploadInfoPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public void set_confirmInvokeFileUploadCheckbox(boolean checked) {
		if ((!confirmInvokeFileUploadCheckbox.isSelected() && checked) || 
				(confirmInvokeFileUploadCheckbox.isSelected() && !checked)) {
			click(confirmInvokeFileUploadCheckbox);
		}
	}
	
	public void clickInvokeFileUploadNow() {
		click(invokeFileUploadNow);
	}
	
	

}
