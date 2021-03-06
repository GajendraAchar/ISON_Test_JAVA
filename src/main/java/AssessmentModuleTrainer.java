
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import Utility.ConfigReader;

import static org.testng.Assert.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssessmentModuleTrainer{
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

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	@Test
	public void testAssessment() throws Exception {
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

		//click on Training n Assessment
		WebElement training = driver.findElement(By.xpath("//a[@title='Trainings']"));
		training.click();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Manage Assessment")).click();
		System.out.println("Clicked on Manage Assessment Button");
		Thread.sleep(6000);

		//driver.get("https://lmstraining.isonxperiences.com/node/add/assessments");
		driver.findElement(By.linkText("Add New Assessment")).click();
		System.out.println("Clicked on Add New Assessmen Button");

		Thread.sleep(8000);

		//Assessment name
		String date = new SimpleDateFormat("yyyMMddHHmmssSS").format(new Date());
		driver.findElement(By.name("title[0][value]")).sendKeys(("SGA Assessment" +date));
		Thread.sleep(3000);

		//Assessment Type
		Select TrMode= new Select(driver.findElement(By.id("edit-assessment-type")));
		TrMode.selectByVisibleText("Certification Scores Assessment");
		System.out.println("Selected Assessment Type");
		Thread.sleep(3000);

		//Assessment Status
		//driver.findElement(By.id("edit-assessment-publish-status-1")).click();
		//Thread.sleep(5000);



		System.out.println(driver.findElements(By.tagName("iframe")).size());
		Thread.sleep(15000);

		WebElement StartDate= driver.findElement(By.id("edit-assessment-start-date-date"));
		String dateString=config.getTrainingStartDate();
		datePickerByJs1(driver,dateString,StartDate);
		System.out.println("Selected Start Date");
		Thread.sleep(3000);

		WebElement EndDate= driver.findElement(By.id("edit-assessment-end-date-date"));
		String dateString2= config.getTrainingEndDate();
		datePickerByJs2(driver,dateString2,EndDate);
		System.out.println("Selected End Date");
		Thread.sleep(3000);

		//Start Time
		WebElement starttime= driver.findElement(By.id("edit-assessment-start-date-time"));
		String time1= "10:00:00";
		TimePickerByJs1(driver,time1,starttime);
		Thread.sleep(3000);

		//End Time
		WebElement endtime= driver.findElement(By.id("edit-assessment-end-date-time"));
		String time2= "03:00:00";
		TimePickerByJs2(driver,time2,endtime);
		Thread.sleep(3000);

		// Duration select Hours and Minutes
		Select hours = new Select(driver.findElement(By.id("edit-assessment-duration-hr")));
		hours.selectByValue("1");
		Thread.sleep(3000);

		//select Minutes
		Select mins = new Select(driver.findElement(By.id("edit-assessment-duration-min")));
		mins.selectByValue("30");
		Thread.sleep(3000);

		//select Region
		Select region = new Select(driver.findElement(By.id("edit-assessment-region")));
		region.selectByValue("1");
		Thread.sleep(4000);

		//select Site
		Select site = new Select(driver.findElement(By.id("edit-assessment-sites")));
		site.selectByValue("1");
		Thread.sleep(4000);

		//select Client
		Select client = new Select(driver.findElement(By.id("edit-assessment-clients")));
		client.selectByValue("1");
		System.out.println("Selected Region Site and Client");
		Thread.sleep(5000);

		Select Certificate= new Select(driver.findElement(By.id("edit-certificate-display")));
		Certificate.selectByValue("1");
		Thread.sleep(5000);

		Select CertificateType= new Select(driver.findElement(By.id("edit-certificate-type")));
		CertificateType.selectByValue("1");
		Thread.sleep(3000);

		//driver.findElement(By.id("edit-assessment-check-enable")).click();//click on Disable Check
		//Thread.sleep(5000);

		System.out.println(driver.findElements(By.tagName("iframe")).size());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");


		//driver.switchTo().frame("iFrameResizer1");
		driver.switchTo().frame(1);
		Thread.sleep(3000);


		driver.findElement(By.name("h5peditor-library")).click();
		Thread.sleep(3000);

		Select AssessmentFormat= new Select(driver.findElement(By.name("h5peditor-library")));
		Thread.sleep(5000);
		AssessmentFormat.selectByVisibleText("Question Set");  //Selected Question Set an Option
		Thread.sleep(15000);

		driver.switchTo().defaultContent();
		Thread.sleep(5000);

		driver.switchTo().frame(1);
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='field-extratitle--1']")).sendKeys("Q1");
		Thread.sleep(2000);


		driver.switchTo().defaultContent();
		Thread.sleep(5000);


		JavascriptExecutor js4 = (JavascriptExecutor) driver;
		js4.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(8000);

		driver.switchTo().frame(1);
		Thread.sleep(5000);



		//Select the T/F Question from the dropdown List
		Select QuestionSet= new Select(driver.findElement(By.id("field-question-36")));
		Thread.sleep(25000);
		QuestionSet.selectByVisibleText("True/False Question");
		Thread.sleep(60000);

		////////Send Text to CKEditor field in QuestionSet/////////
		WebElement element2=driver.findElement(By.xpath("(//div[@contenteditable='true'])[2]"));
		element2.click();
		Thread.sleep(30000);

		WebDriverWait wait = new WebDriverWait(driver, 120);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));

		WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

		//Now send text to EditorField
		body.sendKeys("Peacock is the National Bird Of India");
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		////////END OF CK EDITOR FIELDS /////////

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,400)");
		Thread.sleep(8000);

		driver.findElement(By.id("edit-submit")).click();
		System.out.println("Clicked on Save and Created Assessment");
		Thread.sleep(60000);

		//Search Assessment to add Participant
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("SGA Assessment" +date);
		Thread.sleep(3000);

		//driver.findElement(By.linkText("Add Participant")).click();
		WebElement ele2= driver.findElement(By.linkText("Add Participant"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele2);
		Thread.sleep(6000);

		Select Region2 = new Select(driver.findElement(By.id("edit-select-region")));
		Region2.selectByVisibleText("India");
		Thread.sleep(2000);

		Select Site2 = new Select(driver.findElement(By.id("edit-select-site")));
		Site2.selectByVisibleText("Bangalore");
		Thread.sleep(2000);

		Select Client2 = new Select(driver.findElement(By.id("edit-select-client")));
		Client2.selectByVisibleText("VIL");
		Thread.sleep(2000);

		Select User1 = new Select(driver.findElement(By.id("Array-available")));
		User1.selectByVisibleText("Manish Kumar (13AZSB602)");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//li[@class='multiselect-add']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='edit-submit']")).sendKeys(Keys.ENTER);
		Thread.sleep(20000);
		System.out.println("Trainees added to the assessment"); 


		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(60000);

		boolean var79=  driver.findElement(By.linkText("Login")).isDisplayed();
		System.out.println(var79);
		if (var79==true)
		{
			driver.findElement(By.linkText("Login")).click();
			Thread.sleep(6000);
		}
		
		
		try {

			driver.findElement(By.id("username")).sendKeys(config.getTrainerUserName());
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

			driver.findElement(By.id("username")).sendKeys(config.getTrainerUserName());
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
		
		boolean var8=  driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]")).isDisplayed();
		System.out.println(var8);
		if (var8==true)
		{
			Actions act = new Actions(driver);  	//Move to Left mouse over bar
			WebElement navigate =driver.findElement(By.xpath("//li[@class='menuparent menu-item menu-item--expanded'][1]"));
			act.moveToElement(navigate).build().perform();
			System.out.println("moved to Left Navigation Menu");
			Thread.sleep(10000);
		}
		

		//click on Training n Assessment
		WebElement training3 = driver.findElement(By.xpath("//a[@title='Trainings']"));
		training3.click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='My Trainings / Assessments']")).click();
		Thread.sleep(20000);

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("SGA Assessment" +date);
		Thread.sleep(3000);

		driver.findElement(By.linkText("Next")).click();
		Thread.sleep(60000);

		boolean var9=  driver.findElement(By.xpath("(//div[@class='h5p-true-false-answer'])[1]")).isDisplayed();
		System.out.println(var9);
		/*if (var9==true)
		{
			 	//Move to Left mouse over bar
			driver.findElement(By.xpath("(//div[@class='h5p-true-false-answer'])[1]")).click();
			Thread.sleep(5000);
		}*/

		if (var9==true)
		{
			for(int i=0;i<5;i++)
			{ 
				driver.findElement(By.xpath("(//div[@class='h5p-true-false-answer'])[1]")).click();  
			}  
		}
		Thread.sleep(10000);
		driver.findElement(By.xpath("//button[@title='Finish']")).click();
		Thread.sleep(14000);

		//driver.findElement(By.xpath("//button[@title='Check']")).click();
		//Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='Submit']")).click();
		Thread.sleep(3000);
		System.out.println("Assessment is Submitted"); 


		//driver.findElement(By.xpath("//button[@title='Check']")).click();
		//Thread.sleep(3000);


		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);

	}

	public static void datePickerByJs1(WebDriver driver,String date,WebElement StartDate)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", StartDate);

	}

	public static void datePickerByJs2(WebDriver driver,String date,WebElement EndDate)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+date+"')", EndDate);

	}
	public static void TimePickerByJs1(WebDriver driver,String time,WebElement starttime)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+time+"')", starttime);

	}

	public static void TimePickerByJs2(WebDriver driver,String time,WebElement endtime)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+time+"')", endtime);

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
