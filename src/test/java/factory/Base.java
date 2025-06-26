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
	static Properties properties;

	public static WebDriver initialiseBrowser() throws IOException {
		properties = new Properties();
		FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		properties.load(file);
		
		// First try to read browser from Maven command line. If not given, use the config.properties file.
	    String browser = System.getProperty("browser");
	    if (browser == null || browser.isEmpty()) {
	        browser = properties.getProperty("browser");
	    }
		//String browser = properties.getProperty("browser").toLowerCase();

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("No matching browser");
			driver = null;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static Properties getProperties() throws IOException {
		return properties;
	}

}
