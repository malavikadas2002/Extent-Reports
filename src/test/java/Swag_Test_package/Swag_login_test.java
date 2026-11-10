package Swag_Test_package;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base_package.Swag_Base_class;

public class Swag_login_test extends Swag_Base_class{
	
	@Test
	public void title_verification()
	{
		test=extent.createTest("Title Verification");
		String actual_title = lp.title_mthd();
		Assert.assertEquals(actual_title, "Swag Labs");
	}
	
	@Test
	public void logintest_mthd()
	{
		test=extent.createTest("Login Verification");
		lp.login_mthd("standard_user","secret_sauce");
	}
	
	@Test
	public void url_validation()
	{
		test=extent.createTest("Login Verification");
		String home_url = lp.url_mthd();
		System.out.println("Home url = "+home_url);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
	}
}
