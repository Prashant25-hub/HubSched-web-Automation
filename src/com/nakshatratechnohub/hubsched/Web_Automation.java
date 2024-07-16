package com.nakshatratechnohub.hubsched;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Web_Automation {

	WebDriver driver;

	@BeforeMethod
	public void configsetting() {
		System.setProperty("webdriver.chrome.driver", "chromedriver1.exe");
		driver = new ChromeDriver();
	//     driver.get("https://app.hubsched.com/admin/login");
	    	driver.get("http://localhost:5000/admin/login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("prashant96.adm@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("123456");
		driver.findElement(By.xpath("//*[text()='Sign In']")).click();

	}

//	@AfterMethod
//	public void CloseBrowser() {
//		driver.close();
//	}

//	@Test
//	public void loginAutomation() {
//		driver.findElement(By.id("exampleInputEmail1")).sendKeys("prashant96.adm@gmail.com");
//		driver.findElement(By.id("exampleInputPassword1")).sendKeys("123456");
//		driver.findElement(By.xpath("//*[text()='Sign In']")).click();
//	}

	@Test(priority = 1)
	public void manageRoomsAutomation() {

		driver.findElement(By.linkText("Manage Rooms")).click();
		Actions a = new Actions(driver);

		for (int i = 0; i < 1; i++) {

			driver.findElement(By.xpath("//*[@id=\"btn-add-contact-room\"]")).click();

			WebElement roomNameField = driver.findElement(By.id("room-name"));
			roomNameField.clear();
			roomNameField.sendKeys(AUtils.generateRandomRoomName());

			WebElement roomNumberField = driver.findElement(By.id("room-no"));
			roomNumberField.clear();
			roomNumberField.sendKeys(AUtils.generateRandomRoomNo());

			WebElement floorNumberField = driver.findElement(By.id("floor-no"));
			floorNumberField.clear();
			floorNumberField.sendKeys(String.valueOf(AUtils.generateRandomFloorNo()));

			WebElement seatCapacityField = driver.findElement(By.id("seats-cap"));
			seatCapacityField.clear();
			seatCapacityField.sendKeys(String.valueOf(AUtils.generateRandomSeatCapacity()));

			driver.findElement(By.id("whiteboard-1")).click();
			driver.findElement(By.id("wifi-1")).click();
			driver.findElement(By.id("air-conditioning-1")).click();

		 // Actions a = new Actions(driver);
			a.sendKeys(Keys.PAGE_DOWN).build().perform();
			driver.findElement(By.xpath("//*[@id='projector-1']//following::button")).click();
			driver.findElement(By.xpath("//*[@id=\"al-success-alert\"]//following::button")).click();
		}
		//-------------Delete Room -------------------------------------------------------------------//
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		driver.findElement(By.xpath("//*[@id='main-wrapper']/div/div[11]/div[3]/div[1]/div/div[3]/a")).click();
		driver.findElement(By.id("yes")).click();
		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();
		// Scroll to the bottom of the page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}

	@Test(priority = 2)
	public void addempAutomation() {

		AUtils.sleepNow(3000);
		driver.findElement(By.xpath("//*[text()='Employees']")).click();

//		for (int i = 0; i < 3; i++) {

		driver.findElement(By.xpath("//*[@id='btn-add-contact']")).click();
		WebElement idField = driver.findElement(By.id("e-id"));
		idField.clear();
		idField.sendKeys(String.valueOf(AUtils.generateRandomId()));

		WebElement nameField = driver.findElement(By.id("e-name"));
		nameField.clear();
		nameField.sendKeys(AUtils.generateRandomName());

		WebElement emailField = driver.findElement(By.id("e-mail"));
		emailField.clear();
		// emailField.sendKeys(AUtils.generateRandomEmail());
		emailField.sendKeys("pp1946285@gmail.com");
		
		WebElement dob = driver.findElement(By.id("e-dob"));
		dob.click(); 
		
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/table/tbody/tr[5]/td[2]")).click();
		
		WebElement mobileField = driver.findElement(By.id("e-mobile"));
		mobileField.clear();
		mobileField.sendKeys(String.valueOf(AUtils.generateRandomMobileNumber()));
		// AUtils.sleepNow(1000);
		driver.findElement(By.id("autocomplete-input")).sendKeys("P");
		driver.findElement(By.xpath("//*[@id='autocomplete-list']/div")).click();

		WebElement popupElement = driver.findElement(By.xpath("//*[@id=\"scroll-long-outer-modal\"]/div/div/div[2]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll the pop-up window till the end
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", popupElement);

		WebElement dropdown1 = driver.findElement(By.id("e-gender"));
		Select select1 = new Select(dropdown1);
		select1.selectByVisibleText("Male");
		
		WebElement department = driver.findElement(By.id("e-dept"));
		Select dep = new Select(department);
		dep.selectByVisibleText("IT");

		WebElement dropdown2 = driver.findElement(By.id("e-position"));
		Select select2 = new Select(dropdown2);

		// Get all the options from the dropdown
		List<WebElement> options = select2.getOptions();

		// Get the size of the options list
		int numberOfOptions = options.size();

		// Generate a random index within the range of available options
		Random randam = new Random();
		int randomIndex = randam.nextInt(numberOfOptions);

		// Select the option at the random index
		select2.selectByIndex(randomIndex);

		driver.findElement(By.xpath("//*[@id='trackable']//following::span")).click();
		driver.findElement(By.id("btn-add")).click();

		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();

		// }
	}

	@Test(priority = 3)
	public void editEmployee() {
		AUtils.sleepNow(2000);
		driver.findElement(By.xpath("//*[text()='Employees']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollTo(0, document.body.scrollHeight/3)");
		Actions a = new Actions(driver);
		for (int i = 0; i < 4; i++) {
			a.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
		AUtils.sleepNow(1000);
		WebElement empedit = driver.findElement(By.xpath("//*[@id=\"myTable\"]//following::i[33]"));
		empedit.click();

		WebElement mobileField = driver.findElement(By.id("e-mobile"));
		mobileField.clear();
		mobileField.sendKeys(String.valueOf(AUtils.generateRandomMobileNumber()));

		WebElement element = driver.findElement(By.id("autocomplete-input"));
		element.clear();
		element.click();
		element.sendKeys("Rohit");
		driver.findElement(By.xpath("//*[@id='autocomplete-list']/div[1]")).click();

		WebElement dropdown1 = driver.findElement(By.id("e-gender"));
		Select select2 = new Select(dropdown1);
		select2.selectByVisibleText("Male");

		WebElement dropselect = driver.findElement(By.id("e-position"));
		Select select = new Select(dropselect);
		select.selectByIndex(9);

		WebElement popupElement = driver.findElement(By.xpath("//*[@id='scroll-long-outer-modal']/div/div/div[2]"));
		// Scroll the pop-up window till the end
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", popupElement);

		WebElement updatebtn = driver.findElement(By.id("btn-update"));
		updatebtn.click();

		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();
		driver.navigate().back();

	}

	@Test(priority = 4)
	public void meeting_HistoryAuto() {
		AUtils.sleepNow(1000);
		driver.findElement(By.linkText("Meeting History")).click();
		Actions action = new Actions(driver);
		for (int i = 0; i < 2; i++) {
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
		AUtils.sleepNow(2000);

	}

	@Test(priority = 5)
	public void eventBanners() {
		AUtils.sleepNow(2000);
		driver.findElement(By.linkText("Event Banners")).click();
		driver.findElement(By.linkText("Add Banner")).click();
		WebElement element = driver.findElement(By.id("news"));
		element.clear();
		element.click();
		element.sendKeys("New HubSched is finally here...");
		driver.findElement(By.id("event-banner"))
				.sendKeys("D:\\Prashant Patil\\Banners&Announcement img\\Event Banner\\1.jpg");
		WebElement addButton = driver.findElement(By.xpath("//*[@id='banner-form']/div[2]/button[2]"));
		addButton.click();
		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		driver.findElement(By.linkText("Delete")).click();
		driver.findElement(By.id("yes")).click();
		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();
		driver.navigate().back();

	}

	@Test(priority = 6)
	public void managePantry() {
		driver.findElement(By.linkText("Manage Pantry")).click();
		driver.findElement(By.linkText("Add Pantry Item")).click();
		driver.findElement(By.id("pantry-item")).sendKeys("Tea");
		driver.findElement(By.id("item-image")).sendKeys("D:\\Prashant Patil\\Pnatry item images\\Pantry1.jpg");
		driver.findElement(By.xpath("//*[@id='pantry-form']/div[2]/button[2]")).click();
		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		AUtils.sleepNow(1000);
		WebElement deletebtn = driver
				.findElement(By.xpath("//*[@id='main-wrapper']/div/div[11]/div[3]/div[4]/div/form/div"));
		deletebtn.click();
		driver.findElement(By.id("yes")).click();
		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();

		for (int i = 0; i < 3; i++) {
			a.sendKeys(Keys.PAGE_DOWN).build().perform();
		}

	}

	@Test(priority = 7)
	public void announcementAutomation() {
		AUtils.sleepNow(2000);
		driver.findElement(By.linkText("Announcements")).click();
		driver.findElement(By.linkText("Add Announcement")).click();
		driver.findElement(By.id("t-id")).sendKeys("Celebrating Johan Smith's Well-Deserved Promotion!");
		AUtils.sleepNow(2000);
		driver.findElement(By.id("desc")).sendKeys(
				"We are excited to announce the promotion of Johan Smit to their new role as Manager, Johan Smit has consistently demonstrated dedication and excellence, and we have every confidence in their continued success in this new role.");
		driver.findElement(By.id("thumb"))
				.sendKeys("D:\\Prashant Patil\\Banners&Announcement img\\Announcemet thumbnail img\\Promotion.png");
		driver.findElement(By.xpath("//*[@id='addContactModalTitle']//following::input[3]"))
				.sendKeys("D:\\Prashant Patil\\Banners&Announcement img\\Announcemet thumbnail img\\awsgsg-intro.pdf");
		WebElement date = driver.findElement(By.id("e-date"));
		date.click();
		driver.findElement(By.linkText("25")).click();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement popupElement1 = driver.findElement(By.xpath("//*[@id='with-grid-modal-1']/div/div/div[2]"));
		// Scroll the pop-up window till the end
		js1.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", popupElement1);
		driver.findElement(By.xpath("//*[@id='e-date']//following::button")).click();
		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button[1]")).click();

		driver.findElement(By.xpath("//*[@id='myTable']/tbody/tr[1]/td[7]/div")).click();
		driver.findElement(By.id("yes")).click();
		driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();

		Actions a3 = new Actions(driver);
		for (int i = 0; i < 7; i++) {
			a3.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
//		WebDriverWait wait = new WebDriverWait(driver, 0);
//		WebElement nextbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main']//following::button[3]")));
		WebElement nextbtn = driver.findElement(By.xpath("//*[@id='main']//following::button[3]"));
		if (nextbtn.isEnabled()) {
			nextbtn.click();
		}
		driver.navigate().back();
	}

	@Test(priority = 8)
	public void vouchersAutomation() {
		AUtils.sleepNow(1000);
		driver.findElement(By.linkText("Claim expenses")).click();
		Actions a4 = new Actions(driver);
		for (int i = 0; i < 7; i++) {
			a4.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
		AUtils.sleepNow(1000);
		WebElement nextbtn1 = driver
				.findElement(By.xpath("//*[@id='main-wrapper']/div/div[11]/div[3]/div/div/div/a/button"));

		if (nextbtn1.isEnabled()) {
			nextbtn1.click();
		}

	}

	@Test(priority = 9)
	public void ticketAutomation() {
		AUtils.sleepNow(2000);
		driver.findElement(By.linkText("Tickets/Grievances")).click();
		Actions a5 = new Actions(driver);
		for (int i = 0; i < 7; i++) {
			a5.sendKeys(Keys.PAGE_DOWN).build().perform();
		}

	}

	@Test(priority = 10)
	public void visitorAutomation() throws TimeoutException {

		driver.findElement(By.linkText("Visitors")).click();
		driver.findElement(By.xpath("//*[@id='empId']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Define the target element XPath
		String targetXPath = "//*[@id='addContactModalTitleEmp']/div[5]/button[3]";

		// Loop to continuously scroll until the target element is clickable
		boolean elementClickable = false;
		while (!elementClickable) {
			// Scroll the page to bring the target element into view
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement targetElement = driver.findElement(By.xpath(targetXPath));
			js.executeScript("arguments[0].scrollIntoView(true);", targetElement);

			// Check if the target element is clickable
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(targetXPath)));
			elementClickable = true; // Exit the loop if element is clickable
		}
		// Perform actions on the target element (e.g., click)
		WebElement targetElement = driver.findElement(By.xpath(targetXPath));
		targetElement.click();
		AUtils.sleepNow(2000);
		WebElement imgclick = driver.findElement(By.xpath("//*[@id='myTable']//following::img"));
		imgclick.click();
		WebElement closebtn = driver.findElement(By.xpath("//*[@id='with-grid-modal-3']//following::button"));
		closebtn.click();
		AUtils.sleepNow(2000);
		Actions a6 = new Actions(driver);
		for (int i = 0; i < 4; i++) {
			a6.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
	}

	@Test(priority = 11)
	public void accountSettingAutomation() {

		driver.findElement(By.linkText("Account Settings")).click();
		Actions a7 = new Actions(driver);
		a7.sendKeys(Keys.PAGE_DOWN).build().perform();

		WebElement resetbtn = driver.findElement(By.xpath("//*[@id='pills-account']//following::button"));
		resetbtn.click();

		WebElement okbtn = driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button"));
		okbtn.click();

		changePassAutomation();
	}

	public void changePassAutomation() {

		WebElement savebtn = driver.findElement(By.xpath("//*[@id='resetform']//button"));
		savebtn.click();

		SoftAssert softassert = new SoftAssert();

		WebElement result = driver.findElement(By.id("message1"));
		String actual_result = result.getText();

		String expected_result = "Fill the current password, please!";

		softassert.assertEquals(actual_result, expected_result);
		System.out.println("1.Current password validation is pass");
		// ------------------------------------------------------------//

		WebElement current_pass = driver.findElement(By.id("currentPass"));
		current_pass.sendKeys("123456");

		WebElement savebtn1 = driver.findElement(By.xpath("//*[@id='resetform']//button"));
		savebtn1.click();

		WebElement newpass_validation = driver.findElement(By.id("message2"));
		String actual_result1 = newpass_validation.getText();

		String expected_result1 = "Fill the new password, please!";

		softassert.assertEquals(actual_result1, expected_result1);

		System.out.println("2.New password Validation is Pass");
		// ------------------------------------------------------------------//

		WebElement newpass = driver.findElement(By.id("newPass"));
		newpass.sendKeys("123456");

		WebElement savebtn3 = driver.findElement(By.xpath("//*[@id='resetform']//button"));
		savebtn3.click();

		WebElement confirmpass_validation = driver.findElement(By.id("message3"));
		String actual_result2 = confirmpass_validation.getText();

		String expected_result2 = "Confirm the password, please!";

		softassert.assertEquals(actual_result2, expected_result2);

		System.out.println("3.Validation for confirm password is pass");
		// -------------------------------------------------------------------------//
		AUtils.sleepNow(1000);
		WebElement confirmpass = driver.findElement(By.id("confirmPass"));
		confirmpass.sendKeys("123456");

		WebElement savebtn4 = driver.findElement(By.xpath("//*[@id='resetform']//button"));
		savebtn4.click();

		AUtils.sleepNow(2000);
		WebElement alert = driver.findElement(By.id("alert_content"));
		String actual_result4 = alert.getText();

		String expected_result4 = "New password and current password must be different";

		softassert.assertEquals(actual_result4, expected_result4);

		System.out.println("4.Validation for New password and current password must be different is pass");

		WebElement okbtn = driver.findElement(By.xpath("//*[@id='al-danger-alert']//button"));
		okbtn.click();

		softassert.assertAll();

		personaldetailAutomation();
	}

	public void personaldetailAutomation() {
		AUtils.sleepNow(2000);
		Actions a8 = new Actions(driver);
		for (int i = 0; i < 3; i++) {
			a8.sendKeys(Keys.PAGE_DOWN).build().perform();
		}

		WebElement gender = driver.findElement(By.xpath("//*[@id='Admindetails']//following::select[1]"));
		Select gender1 = new Select(gender);

		List<WebElement> options = gender1.getOptions();
		// Get the size of the options list
		int numberOfOptions = options.size();
		// Generate a random index within the range of available options
		Random randam = new Random();
		int randomIndex = randam.nextInt(numberOfOptions);
		// Select the option at the random index
		gender1.selectByIndex(randomIndex);

		WebElement position = driver.findElement(By.xpath("//*[@id='Admindetails']/div/div[2]/div[2]/select"));
		// position.click();
		Select position1 = new Select(position);

		List<WebElement> options1 = position1.getOptions();
		// Get the size of the options list
		int numberOfOptions1 = options1.size();
		// Generate a random index within the range of available options
		Random randam1 = new Random();
		int randomIndex1 = randam1.nextInt(numberOfOptions1);
		// Select the option at the random index
		position1.selectByIndex(randomIndex1);
		// dropselect.selectByVisibleText("Android developer");

		WebElement mobilefield = driver.findElement(By.xpath("//*[@id='Admindetails']/div/div[2]/div[3]/input"));
		mobilefield.clear();
		mobilefield.sendKeys(String.valueOf(AUtils.generateRandomMobileNumber()));

		WebElement savebtn1 = driver.findElement(By.xpath("//*[@id='Admindetails']//following::button"));
		savebtn1.click();

		WebElement popup_okbtn = driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button"));
		popup_okbtn.click();

	}

	@Test(priority = 12)
	public void generateReportAutomation() {

		WebElement report0 = driver.findElement(By.linkText("Generate Report"));
 		report0.click();
 		
 		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", report0);

		WebElement report1 = driver.findElement(By.linkText("Meeting"));
		report1.click();

		WebElement start_date1 = driver.findElement(By.id("startDate"));
		start_date1.click();

		driver.findElement(By.linkText("1")).click();

		WebElement end_date1 = driver.findElement(By.id("endDate"));
		end_date1.click();

		driver.findElement(By.linkText("28")).click();

		WebElement download_btn = driver.findElement(By.id("search-btn-1"));
		download_btn.click();
		AUtils.sleepNow(2000);

		voucherReport();
	}

	public void voucherReport() {
		
		WebElement report1 = driver.findElement(By.linkText("Generate Report"));
 		report1.click(); report1.click();
 		
 		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", report1);
 		
		WebElement report2 = driver.findElement(By.xpath("//*[@id='sidebarnav']/li[12]/ul/li[2]/a/span"));
		report2.click();
		AUtils.sleepNow(1000);

		WebElement start_date2 = driver.findElement(By.id("startDate"));
		start_date2.click();

		driver.findElement(By.linkText("1")).click();

		WebElement end_date2 = driver.findElement(By.id("endDate"));
		end_date2.click();

		driver.findElement(By.linkText("28")).click();

		WebElement download_btn1 = driver.findElement(By.xpath("//*[@id='voucher-form']//following::button[2]"));
		download_btn1.click();
		AUtils.sleepNow(1000);
		
		tickteReport();
	}

	public void tickteReport() {
          AUtils.sleepNow(1000);
    	WebElement report_2 = driver.findElement(By.linkText("Generate Report"));
 		report_2.click();  report_2.click();
 		AUtils.sleepNow(3000);
 		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", report_2);

		WebElement ticketreport = driver.findElement(By.xpath("//*[@id='sidebarnav']/li[12]/ul/li[3]/a/span"));
		ticketreport.click();

		WebElement start_date2 = driver.findElement(By.id("startDate"));
		start_date2.click();

		driver.findElement(By.linkText("1")).click();

		WebElement end_date2 = driver.findElement(By.id("endDate"));
		end_date2.click();

		driver.findElement(By.linkText("28")).click();

		WebElement download_btn1 = driver.findElement(By.xpath("//*[@id=\"ticket-form\"]//following::button[2]"));
		download_btn1.click();
         AUtils.sleepNow(1000);
         
         visitorReport();
	}
	public void visitorReport() {
		 AUtils.sleepNow(1000);
		
		WebElement report_3 = driver.findElement(By.linkText("Generate Report"));
 		report_3.click(); report_3.click(); report_3.click();
 	   
 		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", report_3);

		WebElement report = driver.findElement(By.linkText("Generate Report"));
		report.click();

		WebElement visitorReport = driver.findElement(By.xpath("//*[@id='sidebarnav']/li[12]/ul/li[4]/a/span"));
		visitorReport.click();

		WebElement start_date3 = driver.findElement(By.id("startDate"));
		start_date3.click();

		driver.findElement(By.linkText("1")).click();

		WebElement end_date3 = driver.findElement(By.id("endDate"));
		end_date3.click();

		driver.findElement(By.linkText("28")).click();

		WebElement download_btn1 = driver.findElement(By.xpath("//*[@id='visitor-form']//following::button"));
		download_btn1.click();
		AUtils.sleepNow(1000);
		offciteTracking();

	}

	public void offciteTracking() {
		
		WebElement report_3 = driver.findElement(By.linkText("Generate Report"));
 		report_3.click(); report_3.click();
 		
 		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", report_3);

		WebElement offcitetracking = driver.findElement(By.linkText("Off Site Tracking"));
		offcitetracking.click();

		WebElement start_date3 = driver.findElement(By.id("startDate"));
		start_date3.click();

		driver.findElement(By.linkText("1")).click();

		WebElement end_date3 = driver.findElement(By.id("endDate"));
		end_date3.click();

		driver.findElement(By.linkText("14")).click();

		WebElement download_btn3 = driver.findElement(By.xpath("//*[@id='tracking-form']//following::button"));
		download_btn3.click();
		AUtils.sleepNow(1000);
		ext_MeetingFeedbackRepoert();

	}

	public void ext_MeetingFeedbackRepoert() {
		
		WebElement report_4 = driver.findElement(By.linkText("Generate Report"));
 		report_4.click(); report_4.click();
 		
 		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", report_4);

		WebElement extfeedback = driver.findElement(By.linkText("Ex-Meeting Feedback"));
		extfeedback.click();

		WebElement start_date4 = driver.findElement(By.id("startDate"));
		start_date4.click();

		driver.findElement(By.linkText("1")).click();

		WebElement end_date4 = driver.findElement(By.id("endDate"));
		end_date4.click();

		driver.findElement(By.linkText("14")).click();

		WebElement download_btn4 = driver.findElement(By.xpath("//*[@id='feedback-form']//following::button"));
		download_btn4.click();
		AUtils.sleepNow(2000);
		attendanceReport();

	}
	
	public void attendanceReport() {
		
		WebElement report_5 = driver.findElement(By.linkText("Generate Report"));
 		report_5.click(); report_5.click();
 		
 		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", report_5);
		
		WebElement attendance = driver.findElement(By.linkText("Attendance"));
		attendance.click();

		WebElement start_date5 = driver.findElement(By.id("startDate"));
		start_date5.click();

		driver.findElement(By.linkText("1")).click();

		WebElement end_date5 = driver.findElement(By.id("endDate"));
		end_date5.click();

		driver.findElement(By.linkText("14")).click();

		WebElement download_btn5 = driver.findElement(By.xpath("//*[@id='attendance-form']//following::button"));
		download_btn5.click();

	}

}
