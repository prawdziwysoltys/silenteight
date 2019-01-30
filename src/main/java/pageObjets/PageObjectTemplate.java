package pageObjets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectTemplate {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObjectTemplate(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20);
    }

    public void waitToClickableAndClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            System.out.println("Issue with clicking object " + element + ". Trying one more time via JS command");
            this.clickByJScommend(element);
        }
    }

    private void clickByJScommend(WebElement element) {
        new Actions(this.driver)
                .moveToElement(element).click(element)
                .perform();
    }
}
