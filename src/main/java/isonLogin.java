
import static org.testng.Assert.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class isonLogin {
	private WebDriver driver;
	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "ISBL0430", "ison12345" }, { "13AZSB201", "ison12345" }, 
			{ "ISBL02272", "ison12345" },
			{ "13AZSB602", "ison12345" } };
	}

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		String driverPath = "D:/Automation/drivers/browsers";
		//System.setProperty("webdriver.gecko.driver",driverPath+"/geckodriver-v0.12.0/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", driverPath+"/chrome/chromedriver.exe");

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setAcceptInsecureCerts(true);

		//driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), dc);


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

		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "Authentication")
	public void testLoginScript(String sUsername, String sPassword) throws Exception {
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

			driver.findElement(By.id("username")).sendKeys(sUsername);
			Thread.sleep(6000);
			driver.findElement(By.id("password")).sendKeys(sPassword);
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

			driver.findElement(By.id("username")).sendKeys(sUsername);
			Thread.sleep(6000);
			driver.findElement(By.id("password")).sendKeys(sPassword);
			Thread.sleep(6000);

			boolean var8=  driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).isDisplayed();
			System.out.println(var8);
			if (var8==true)
			{
				driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
				Thread.sleep(20000);
			}

		}


		/*

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(sUsername);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(sPassword);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(40000);
		 */

		System.out.println("User: " + sUsername + " logged in & logged out");
		Thread.sleep(10000);

		boolean var11=  driver.findElement(By.linkText("Logout")).isDisplayed();
		System.out.println(var11);
		if (var11==true)
		{
			driver.findElement(By.linkText("Logout")).click();
			Thread.sleep(60000);
		}

		//driver.findElement(By.linkText("Logout")).click();
		//Thread.sleep(30000);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
}
