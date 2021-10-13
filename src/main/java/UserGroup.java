import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UserGroup {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		String driverPath = "D:/Automation/drivers/browsers";
		System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");


		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setAcceptInsecureCerts(true);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("enable-automation");
		//options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--disable-gpu");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

		driver = new ChromeDriver(options);	

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}

	@Test
	public void testAddUser() throws Exception {
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());
		Thread.sleep(10000);

		// Click On Login button if the prompt is displaying
		boolean var9=  driver.findElement(By.linkText("Login")).isDisplayed();
		System.out.println(var9);
		if (var9==true)
		{
			driver.findElement(By.linkText("Login")).click();
			Thread.sleep(6000);
		}
		
		
		try {

			driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
			Thread.sleep(6000);
			driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
			Thread.sleep(6000);
			
			boolean var8=  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).isDisplayed();
			System.out.println(var8);
			if (var8==true)
			{
				driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
				Thread.sleep(20000);
			}
		}
		
		catch(Exception e) {

			driver.findElement(By.linkText("Login")).click();
			Thread.sleep(6000);

			driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
			Thread.sleep(6000);
			driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
			Thread.sleep(6000);
			
			boolean var8=  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).isDisplayed();
			System.out.println(var8);
			if (var8==true)
			{
				driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
				Thread.sleep(20000);
			}
			
		}

		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[8]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		System.out.println("MOved to Navigation Menu");
		Thread.sleep(3000);

		driver.findElement(By.linkText("User Management")).click();
		System.out.println("Clicked on User Management");
		Thread.sleep(4000);

		driver.findElement(By.linkText("Manage Users")).click(); //Manage Users
		System.out.println("Clicked On Manage Users");
		Thread.sleep(35000);

		driver.findElement(By.xpath("//a[@title='Manage User Group']")).click();
		Thread.sleep(8000);

		driver.findElement(By.linkText("Add New Group")).click();
		Thread.sleep(10000);
		String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());

		//Group Name
		driver.findElement(By.id("edit-user-group-name")).sendKeys("Test" +date);
		Thread.sleep(4000);

		//Group Name description
		driver.findElement(By.id("edit-user-group-description")).sendKeys("New" +date);
		Thread.sleep(4000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(10000);

		driver.findElement(By.xpath("//input[@placeholder='Search Here']")).sendKeys("Test" +date);
		Thread.sleep(3000);

		driver.findElement(By.linkText("Edit")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("edit-user-group-name")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("edit-user-group-name")).clear();
		Thread.sleep(3000);

		driver.findElement(By.id("edit-user-group-name")).sendKeys("Group" +date);
		Thread.sleep(3000);

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Back")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@placeholder='Search Here']")).sendKeys("Group" +date);
		Thread.sleep(3000);

		driver.findElement(By.linkText("Add Participant")).click();
		Thread.sleep(3000);

		Select region = new Select(driver.findElement(By.id("edit-select-region")));
		region.selectByValue("1");
		Thread.sleep(3000);

		//edit-select-client

		Select site = new Select(driver.findElement(By.id("edit-select-site")));
		site.selectByValue("1");
		Thread.sleep(3000);

		Select client = new Select(driver.findElement(By.id("edit-select-client")));
		client.selectByValue("1");
		Thread.sleep(3000);

		Select User1 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User1.selectByVisibleText("Melwyn Fernades (ISBL02224)");
		Thread.sleep(5000);
		System.out.println("user1 is selected");

		Select User3 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User3.selectByVisibleText("Sudesh D (ISBL02272)");
		Thread.sleep(5000);
		System.out.println("user2 is selected");


		Select User4 = new Select(driver.findElement(By.xpath("//select[@id='Array-available']")));
		User4.selectByVisibleText("Bharath KR (ISBL00351)");
		Thread.sleep(5000);
		System.out.println("user3 is selected");

		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		System.out.println("Clicked on add Icon to add user");
		Thread.sleep(15000);

		boolean var7=  driver.findElement(By.id("edit-submit")).isDisplayed();
		System.out.println(var7);
		if (var7==true)
		{
			WebElement element = driver.findElement(By.id("edit-submit"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(5000);

		}

		driver.findElement(By.xpath("//a[@title='Back']")).click();
		Thread.sleep(10000);

		driver.get(config.getpoll());
		Thread.sleep(10000);

		driver.findElement(By.xpath("//input[@placeholder='Search Here']")).sendKeys("Poll2021");
		Thread.sleep(3000);

		driver.findElement(By.linkText("Add Participant")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='Add by Group']")).click();
		Thread.sleep(3000);

		Select Group = new Select(driver.findElement(By.id("edit-select-group")));
		Group.selectByVisibleText("Group" +date);
		Thread.sleep(5000);
		System.out.println("Group is selected");

		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Logged Out");
		Thread.sleep(6000);



	} 
	public static void datePickerByJs1(WebDriver driver,String date,WebElement element)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", element);

	}


	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
			driver.close();
		}
	}
}
