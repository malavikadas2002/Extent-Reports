package Swag_page_package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Swag_login_page {
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"user-name\"]") WebElement username;
	@FindBy(xpath = "//*[@id=\"password\"]") WebElement password;
	@FindBy(xpath ="//*[@id=\"login-button\"]") WebElement loginbtn;
	
	public Swag_login_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	public String title_mthd()
	{
		String title = driver.getTitle();
		return title;
	}
	
	public void login_mthd(String un, String pw)
	{
		username.sendKeys(un);
		password.sendKeys(pw);
		loginbtn.click();
	}
	
	public String url_mthd()
	{
		String actual_url = driver.getCurrentUrl();
		return actual_url;
	}

}
