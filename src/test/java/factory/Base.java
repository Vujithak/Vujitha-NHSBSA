package factory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	 static WebDriver driver;
     static Properties p;
  	     
public static WebDriver initilizeBrowser() throws IOException
{
	p = getProperties();
    String browser = p.getProperty("browser").toLowerCase();
	
			switch(browser.toLowerCase()) 
			{
			case "chrome":
				WebDriverManager.chromedriver().setup();
		        driver=new ChromeDriver();
		        break;
		    case "edge":
		    	WebDriverManager.edgedriver().setup(); //wbmanager installs the compatible version of browser with driver and runs
		    	driver=new EdgeDriver();
		        break;
		    case "firefox":
		    	WebDriverManager.firefoxdriver().setup();
		    	driver=new FirefoxDriver();
		        break;
		    default:
		        System.out.println("No matching browser");
		        driver=null;
			}
	 driver.manage().deleteAllCookies(); 
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	 
	 return driver;
	 
}

public static WebDriver getDriver() {
		return driver;
	}
/**
 * Loads the configuration properties file.
 */
public static Properties getProperties() throws IOException
{		 
    FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
   	p=new Properties();
	p.load(file);
	return p;
}

}
