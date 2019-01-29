package pageObjets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReadPropertiesFile;

public class LoginPage extends PageObjectTemplate {

    @FindBy(id="username")
    private WebElement name;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(name="submit")
    private WebElement logInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        String url = ReadPropertiesFile.getPropertyByKey("url");
        driver.get(url);
    }

    public void logIn(){
        this.logIn(ReadPropertiesFile.getPropertyByKey("testUserName"),
                ReadPropertiesFile.getPropertyByKey("testUserPassword"));
    }

    public void logIn(String userName, String userPassword){
        this.name.clear();
        this.name.sendKeys(userName);

        this.password.clear();
        this.password.sendKeys(userPassword);

        this.logInButton.click();
    }
}
