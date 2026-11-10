package Base_package;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Swag_page_package.Swag_login_page;

public class Swag_Base_class {
	
	public static WebDriver driver;
	public Swag_login_page lp;
	
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeTest
	public void setup()
	{
		reporter = new ExtentSparkReporter("./Reports/swag_report.htlm");	//create a report
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setReportName("Functional Test");
		reporter.config().setTheme(Theme.DARK);
				
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Hostname", "localhost");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Testername", "ABC");
		extent.setSystemInfo("Browsername", "chrome");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("---incognito");
		driver = new ChromeDriver(options);
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void objectmethod()
	{
		lp = new Swag_login_page(driver);
	}
	
	@AfterMethod
	public void aftr_mthd(ITestResult result) throws Exception
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.FAIL, "Test case failed is : "+result.getName());
			test.log(Status.FAIL, "Test case failed is : "+result.getThrowable());
			String screenshotpath = screenshotMethod(driver,result.getName());
			test.addScreenCaptureFromPath(screenshotpath);
		}
		
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.skip("Test Skipped : "+result.getName());
		}
	}

	private String screenshotMethod(WebDriver driver2, String name) throws Exception
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"./Screenshot/"+name+".png";
		FileHandler.copy(src, new File(destination));
		return destination;
	}
	
	@AfterTest
	public void endreport()
	{
		extent.flush();
		driver.quit();
	}
	
	
}
