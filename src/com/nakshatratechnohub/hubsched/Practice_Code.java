package com.nakshatratechnohub.hubsched;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Practice_Code {
	WebDriver driver;

	@BeforeMethod
	public void configsetting() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:5000/admin/login");
		// driver.get("https://5625-182-48-252-114.ngrok-free.app/admin/login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@AfterMethod
//	public void CloseBrowser() {
//		driver.close();

//	}
	@Test
	public void Verify_Login() {
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("123456");
		driver.findElement(By.xpath("//*[text()='Sign In']")).click();
		Create_Employee();
	}

	public void Create_Rooms() {
		String[] roomNames = { "Alpha", "Beta", "Gamma" };

		Random random = new Random();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Manage Rooms']")));
		element.click();

		for (int i = 0; i < 3; i++) {

			int randomRoomNameIndex = random.nextInt(roomNames.length);

			String roomName = roomNames[randomRoomNameIndex];

			// Generating a random 2-digit room number
			int roomNumber = 10 + random.nextInt(90);

			// Generating a random 2-digit floor number
			int floorNumber = 1 + random.nextInt(10);

			// Generating a random seat capacity
			int seatCapacity = 10 + random.nextInt(91); // Considering a range between 10 and 100

			driver.findElement(By.id("btn-add-contact")).click();

			WebElement roomNameField = driver.findElement(By.id("n-id")); // Replace "roomName" with the actual ID of
																			// the room name field
			roomNameField.clear();
			roomNameField.sendKeys(roomName);

			WebElement roomNumberField = driver.findElement(By.id("room-no")); // Replace "roomNumber" with the actual
																				// ID of the room number field
			roomNumberField.clear();
			roomNumberField.sendKeys(String.valueOf(roomNumber));

			WebElement floorNumberField = driver.findElement(By.id("floor-no")); // Replace "floorNumber" with the
																					// actual ID of the floor number
																					// field
			floorNumberField.clear();
			floorNumberField.sendKeys(String.valueOf(floorNumber));

			WebElement seatCapacityField = driver.findElement(By.id("seats-cap")); // Replace "seatCapacity" with the
																					// actual ID of the seat capacity
																					// field
			seatCapacityField.clear();
			seatCapacityField.sendKeys(String.valueOf(seatCapacity));

			driver.findElement(By.id("whiteboard-1")).click();
			driver.findElement(By.id("wifi-1")).click();
			Actions a = new Actions(driver);
			a.sendKeys(Keys.PAGE_DOWN).build().perform();
			driver.findElement(By.xpath("//*[@id='projector-1']//following::button")).click();
			driver.findElement(By.xpath("//*[@id=\"al-success-alert\"]//following::button")).click();
		}
	}

	public void Create_Employee() {

		driver.findElement(By.xpath("//*[text()='Employees']")).click();

		for (int i = 0; i < 3; i++) {

			driver.findElement(By.xpath("//*[@id='btn-add-contact']")).click();
			WebElement idField = driver.findElement(By.id("e-id"));
			idField.clear();
			idField.sendKeys(String.valueOf(AUtils.generateRandomId()));

			WebElement nameField = driver.findElement(By.id("e-name"));
			nameField.clear();
			nameField.sendKeys(AUtils.generateRandomName());

			WebElement emailField = driver.findElement(By.id("e-mail"));

			emailField.clear();
			emailField.sendKeys(AUtils.generateRandomEmail());

			WebElement mobileField = driver.findElement(By.id("e-mobile"));
			mobileField.clear();
			mobileField.sendKeys(String.valueOf(AUtils.generateRandomMobileNumber()));

			driver.findElement(By.id("autocomplete-input")).sendKeys("Prashant Ptil");
			driver.findElement(By.xpath("//*[@id=\"autocomplete-list\"]/div")).click();

			WebElement popupElement = driver
					.findElement(By.xpath("//*[@id=\"scroll-long-outer-modal\"]/div/div/div[2]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Scroll the pop-up window till the end
			js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", popupElement);

			WebElement dropdown1 = driver.findElement(By.id("e-gender"));
			Select select1 = new Select(dropdown1);
			select1.selectByVisibleText("Male");

			WebElement dropdown2 = driver.findElement(By.id("e-position"));
			Select select2 = new Select(dropdown2);
			
			// Get all the options from the dropdown
			List<WebElement> options = select2.getOptions();

			// Get the size of the options list
			int numberOfOptions = options.size();

			// Generate a random index within the range of available options
			Random rand = new Random();
			int randomIndex = rand.nextInt(numberOfOptions);

			// Select the option at the random index
			select2.selectByIndex(randomIndex);


			driver.findElement(By.xpath("//*[@id=\"trackable\"]//following::span")).click();
			driver.findElement(By.id("btn-add")).click();

			driver.findElement(By.xpath("//*[@id='al-success-alert']//following::button")).click();

		}
	}

}
