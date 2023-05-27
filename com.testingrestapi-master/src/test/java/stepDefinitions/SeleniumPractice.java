package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.stream.Stream;

public class SeleniumPractice {
    public static void main (String[] args)
    {   WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        String baseUrl = "https://demowebshop.tricentis.com/";
        driver.get(baseUrl);

      List<WebElement> links = driver.findElements(By.tagName("a"));
      System.out.println("Links size" + links.size());
    /*  for (WebElement link : links)
        {

            System.out.println(link.getText());
        }
*/
        //lambda  expression for each
        links.forEach(link -> System.out.println(link.getText()));
//processing elements with stream - filter and using predicate it return true or false boolean
long brokenLinks = links.stream().filter(link -> link.getAttribute("href")!=null).count();
        System.out.println(brokenLinks);

        System.out.println("generate");
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
      driver.quit();

    }}
