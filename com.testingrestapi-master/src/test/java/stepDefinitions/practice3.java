package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class practice3 {
    public static void main(String[] args) {
        WebDriver driver;
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        String baseUrl = "https://demowebshop.tricentis.com/";
        driver.get(baseUrl);

        driver.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Books']")).click();

       Select sortbydrp=new Select(driver.findElement(By.id("products-orderby")));
        sortbydrp.selectByVisibleText("Name: Z to A");

        List<WebElement> product_items=driver.findElements(By.xpath("//h2[@class='product-title']"));
       List<String> items1= product_items.stream().map(items -> items.getText()).collect(Collectors.toList());
       System.out.println("items1" + items1);
        List<String> items2= product_items.stream().map(items -> items.getText()).sorted().collect(Collectors.toList());
        System.out.println("items2" + items2);

        List<WebElement> prodTitles=driver.findElements(By.xpath("//h2[@class='product-title']"));
        List<WebElement> prodPrices=driver.findElements(By.xpath("//div[@class='prices']"));
        Map<String,Double> products_map=new HashMap<String,Double>();

        for(int i=0;i<prodTitles.size();i++)
        {
            String title=prodTitles.get(i).getText();
            double price=Double.parseDouble(prodPrices.get(i).getText());

            products_map.put(title, price);

        }


//Printing titles & prices using for..each loop
        System.out.println("**** Printing titles & prices using for..each loop**** ");
        for (Map.Entry<String,Double> entry : products_map.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        // Printing titles & prices using Hashmap .forEach() & Lamda expression
        System.out.println("**** Printing titles & prices using lamda expression**** ");
        products_map.forEach((t, p) -> System.out.println(t + " : " + p));


        //find product whose price is greater than 800 (for..each loop)
        System.out.println("**** Product price is > 800 ****");
        for (Map.Entry<String,Double> entry : products_map.entrySet()) {
            if(entry.getValue()>800)
            {
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
        }

        //find product whose price is greater than 800 (Process using filter)
        System.out.println("**** Product price is > 800 using filer & lambda ****");
        products_map.entrySet().stream().filter( e -> e.getValue() > 800).forEach(v->System.out.println(v));


        driver.quit();
         }

    }
