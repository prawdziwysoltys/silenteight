package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Browser {

    public static WebDriver startNewBrowserInstance(){
        WebDriver driver;
        String browserType = ReadPropertiesFile.getPropertyByKey("browser");
        if (browserType.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if (browserType.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","src\\main\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }else{
            Assert.fail("Please provide valid browser name in config.properties file. Options: chrome, firefox");
            return null;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }
}
