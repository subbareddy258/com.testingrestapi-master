package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends AbstractStepDefinition{
    @FindBy(how = How.ID,using= "login-email")
    public WebElement txtUserName;
    WebDriver driver = Basic.getDefaultDriver();
}
