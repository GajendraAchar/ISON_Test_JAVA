
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

public class searchTraining {
	private WebDriver driver;

	ConfigReader config= new ConfigReader();

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
	public void testSearchTraining() throws Exception {
		ConfigReader config= new ConfigReader();
		driver.get(config.getApplicationUrl());

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(config.getSGAUserName());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(config.getSGAUserPassword());
		driver.findElement(By.cssSelector("button.wr-btn.grey-bg.col-xs-12.col-md-12.col-lg-12.uppercase.font-extra-large.margin-bottom-double.submit-button-sso")).click();
		Thread.sleep(20000);


		WebElement menuBar1 = driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[4]/a"));
		Actions act1 = new Actions (driver);
		act1.moveToElement(menuBar1).perform();
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[4]/a")).click(); //Trainings
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/div[3]/div/ul/li[4]/ul/li[1]/a")).click(); //Manage Offline & Online Trainings                             
		Thread.sleep(30000);


		driver.findElement(By.name("select_mode")).click();
		new Select(driver.findElement(By.name("select_mode"))).selectByVisibleText("Online Training");
		driver.findElement(By.name("select_mode")).click();
		Thread.sleep(5000);  
		driver.findElement(By.name("select_type")).click();
		new Select(driver.findElement(By.name("select_type"))).selectByVisibleText("Operational");
		driver.findElement(By.name("select_type")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("select_cat")).click();
		new Select(driver.findElement(By.name("select_cat"))).selectByVisibleText("Briefing Report");
		driver.findElement(By.name("select_cat")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("select_region")).click();
		new Select(driver.findElement(By.name("select_region"))).selectByVisibleText("India");
		driver.findElement(By.name("select_region")).click();


		driver.findElement(By.xpath("//input[@class='search-members-button button js-form-submit form-submit form-control']")).click();
		Thread.sleep(3000);
		System.out.println("Searched Training in the search bar");


		System.out.println("Related Trainings Found for the Filters selected..!!!");
		Thread.sleep(8000); 

		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);

		 

		/*
		driver.get("https://lmstraining.isonxperiences.com/group/3035/assessmentstructure");
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[contains(text(),'Manage')]//span[@class='glyphicon glyphicon-cog']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@class='use-ajax btn btn-info btn-sm float-right open-module btn-add-feedback-two']/span")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'True False')]")).click();
		Thread.sleep(2000);

		System.out.println(driver.findElements(By.tagName("iframe")).size());

		driver.switchTo().frame("iFrameResizer0");
		Thread.sleep(5000);

		driver.findElement(By.id("edit-name-0-value")).sendKeys("True or False");
		Thread.sleep(10000);

		System.out.println(driver.findElements(By.tagName("iframe")).size());
		Thread.sleep(2000);


		driver.switchTo().frame(0);//Switched to Iframe
		Thread.sleep(5000);
		// Click On Send Anyway button if the prompt is displaying
		boolean var=  driver.findElement(By.xpath("//input[@id='field-extratitle--1']")).isDisplayed();
		System.out.println(var);
		if (var==true)
		{
			//Enter Title
			driver.findElement(By.id("field-extratitle--1")).sendKeys("Q1");
		}
		Thread.sleep(8000);



		///////////////////SEND TEXT TO CK EDITOR///////////////////////


		boolean var2=  driver.findElement(By.id("field-question-15")).isDisplayed();
		System.out.println(var2);
		if (var2==true)
		{
			//Enter Title
			driver.findElement(By.id("field-question-15")).click();
		}
		Thread.sleep(3000);

		System.out.println(driver.findElements(By.tagName("iframe")).size());
		Thread.sleep(3000);

		WebElement web3=driver.findElement(By.xpath("//html"));
		Thread.sleep(3000);

		boolean var3=  driver.findElement(By.xpath("//html")).isDisplayed();
		System.out.println(var3);
		if (var3==true)
		{
			Actions act1=new Actions(driver);
			act1.moveToElement(web3).sendKeys("Peacock is the National Bird Of India").build().perform();
			Thread.sleep(2000);
		}
		Thread.sleep(8000);

		driver.switchTo().parentFrame();
		Thread.sleep(5000);

		JavascriptExecutor js10 = (JavascriptExecutor) driver;
		WebElement Save = driver.findElement(By.id("edit-submit"));
		js10.executeScript("arguments[0].scrollIntoView();", Save);
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		System.out.println("Question Created for Assessment");
		Thread.sleep(8000);
		
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		
		boolean var33=  driver.findElement(By.xpath("//button[@title='Close']")).isDisplayed();
		System.out.println(var33);
		if (var33==true)
		{
			driver.findElement(By.xpath("//button[@title='Close']")).click();
			Thread.sleep(3000);
		}
		
		*/
		
		
		

































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
