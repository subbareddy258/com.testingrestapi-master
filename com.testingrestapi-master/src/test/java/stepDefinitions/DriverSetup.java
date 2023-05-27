package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSetup {
    private static WebDriver driver;
    public static WebDriver getWebDriver()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver(chromeOptions);
        String baseUrl = "http://webapps.tekstac.com/AddressBook/";
        driver.get(baseUrl);
        return driver;
    }

}
