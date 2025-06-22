package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseClass {

	WebDriver driver;
	WebDriverWait wait;
	public BaseClass(WebDriver driver)
	{
		this.driver=driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10)); 
		PageFactory.initElements(driver, this);
	}
}
