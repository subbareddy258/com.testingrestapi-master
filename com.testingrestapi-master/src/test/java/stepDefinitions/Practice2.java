package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

public class Practice2 {
    public static void main(String[] args) {
        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.flipkart.com/");
       driver.switchTo().newWindow(WindowType.TAB);
       driver.get("https://www.amazon.in/");
      Set<String> windows= driver.getWindowHandles();
      windows.forEach(wind -> System.out.println(driver.switchTo().window(wind).getTitle()));


      driver.quit();

    }
}
