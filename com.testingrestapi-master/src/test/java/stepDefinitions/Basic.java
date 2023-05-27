package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static utlis.Utils.getBrowser;

public class Basic {
    static WebDriver driver;

    public static WebDriver getDefaultDriver() {
        if (driver != null) {
            return driver;
        }
        if ("firefox".equalsIgnoreCase(getBrowser())) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-private");
            firefoxOptions.setHeadless(true);
            driver = new FirefoxDriver(firefoxOptions);
        }
        else
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions= new ChromeOptions();

            chromeOptions.setHeadless(!SystemUtils.IS_OS_WINDOWS);
            chromeOptions.addArguments("window-size=1200,600");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;

}
}