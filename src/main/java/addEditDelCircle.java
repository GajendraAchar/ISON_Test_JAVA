import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class addEditDelCircle {
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
	public void testAddEditDelCircle() throws Exception {
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());

		Thread.sleep(10000);

		// Click On Login button if the prompt is displaying
		boolean var22=  driver.findElement(By.linkText("Login")).isDisplayed();
		System.out.println(var22);
		if (var22==true)
		{
			driver.findElement(By.linkText("Login")).click();
			Thread.sleep(6000);
		}


		try {

			driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
			Thread.sleep(6000);
			driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
			Thread.sleep(6000);

			boolean var8= driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed();
			System.out.println(var8);
			if (var8==true)
			{
				driver.findElement(By.xpath("//button[@type='submit']")).click();
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

			boolean var8= driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed();
			System.out.println(var8);
			if (var8==true)
			{
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(20000);
			}

		} 


		
		boolean var7=  driver.findElement(By.xpath("//nav[@class='pcoded-navbar menu-light navbar-collapsed']")).isDisplayed();
		System.out.println(var7);
		if (var7==true)
		{
			Actions act = new Actions(driver);  	//Move to Left mouse over bar
			WebElement navigate =driver.findElement(By.xpath("//nav[@class='pcoded-navbar menu-light navbar-collapsed']"));
			act.moveToElement(navigate).build().perform();
			System.out.println("moved to Left Navigation Menu");
			Thread.sleep(5000);
		}

		driver.findElement(By.linkText("Demography")).click();
		Thread.sleep(5000);

		driver.findElement(By.linkText("Circles")).click();
		Thread.sleep(20000);

		//Add Circle
		System.out.println("Started to Add Circle !!");
		driver.findElement(By.linkText("Add")).click();
		Thread.sleep(5000);

		driver.findElement(By.name("circle_name")).sendKeys("Karunadu");
		Thread.sleep(5000);

		//Selected Client
		Select Client = new Select(driver.findElement(By.xpath("//select[@data-drupal-selector='edit-client-name']")));
		Client.selectByValue("7");
		Thread.sleep(3000);

		//Selected Site
		Select Site = new Select(driver.findElement(By.xpath("//select[@class='multiselect-available form-multiselect form-control']")));
		Site.selectByValue("1");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//textarea[@data-drupal-selector='edit-description']")).sendKeys("CicleOne");
		Thread.sleep(8000);
		
		boolean var33= driver.findElement(By.xpath("//input[@type='submit']")).isDisplayed();
		System.out.println(var33);
		if (var33==true)
		{
			driver.findElement(By.xpath("//input[@type='submit']")).click(); //Save
			Thread.sleep(10000);
		}
		
		System.out.println("Circle added Successfully !!");
		Thread.sleep(30000); 
		
		boolean var44= driver.findElement(By.tagName("input")).isDisplayed();
		System.out.println(var44);
		if (var44==true)
		{
			driver.findElement(By.tagName("input")).sendKeys("Karunadu");
			Thread.sleep(25000);
		}
		
		//Delete Circle
		boolean var3= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[6]/a[2]")).isDisplayed();
		System.out.println(var3);
		if (var3==true)
		{
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[6]/a[2]")).click();
		}
		Thread.sleep(8000);

		boolean var4= driver.findElement(By.id("edit-submit")).isDisplayed();
		System.out.println(var4);
		if (var4==true)
		{
			driver.findElement(By.id("edit-submit")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		}
		
		/*
		boolean var3= driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).isDisplayed();
		System.out.println(var3);
		if (var3==true)
		{
			driver.findElement(By.xpath("//text()[contains(.,'Search')]/ancestor::label[1]")).click();
		}
		Thread.sleep(8000);

		boolean var4= driver.findElement(By.linkText("Delete")).isDisplayed();
		System.out.println(var4);
		if (var4==true)
		{
			driver.findElement(By.linkText("Delete")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		}
		*/
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
		
		
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
