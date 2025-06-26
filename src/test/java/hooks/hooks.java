package hooks;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

import factory.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hooks {
	
	WebDriver driver;
	
	@Before
	public void setup() throws IOException
	{	
		driver = Base.initialiseBrowser();
        String url = Base.getProperties().getProperty("appURL");
        driver.get(url);
	}
	
	@After
	public void teardown()
	{
		Base.getDriver().quit();	
	}

}
