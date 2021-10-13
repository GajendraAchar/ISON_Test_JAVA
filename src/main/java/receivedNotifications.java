
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import Utility.ConfigReader;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class receivedNotifications {
	private WebDriver driver;

	ConfigReader config= new ConfigReader();

	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		String driverPath = "D:/Automation/drivers/browsers";
		String env=config.getEnvironmentName();
		System.out.println("environmentis :" +env);

		if(env.contains("UAT")){

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
		}
		else 
		{
			System.setProperty("webdriver.gecko.driver",driverPath+"/firefox/geckodriver.exe");


			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);

			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	@Test
	public void testReceiveNotifications() throws Exception {
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());
		Thread.sleep(12000);

		boolean var89=  driver.findElement(By.linkText("Login")).isDisplayed();
		System.out.println(var89);
		if (var89==true)
		{
			driver.findElement(By.linkText("Login")).click();
			Thread.sleep(6000);
		}


		try {

			driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
			Thread.sleep(6000);
			driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
			Thread.sleep(6000);

			boolean var88=  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).isDisplayed();
			System.out.println(var88);
			if (var88==true)
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


		boolean var8=  driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]")).isDisplayed();
		System.out.println(var8);
		if (var8==true)
		{
			Actions act = new Actions(driver);  	//Move to Left mouse over bar
			WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
			act.moveToElement(navigate).build().perform();
			System.out.println("moved to Left Navigation Menu");
			Thread.sleep(5000);
		}
		driver.findElement(By.linkText("My Dashboard")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Notifications")).click(); //Notifications
		Thread.sleep(30000);



		driver.findElement(By.linkText("View")).click();
		Thread.sleep(8000);

		boolean var7=  driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).isDisplayed();
		System.out.println(var7);
		if (var7==true)
		{
			driver.findElement(By.xpath("//span[@class='ui-button-icon ui-icon ui-icon-closethick']")).click();;

		}

		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
		//driver.get("https://bi-3.axxonet.com/isonlms/");
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
