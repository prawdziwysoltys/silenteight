package pageObjets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;

public class BatchAssignPage extends PageObjectTemplate {
    private String batchXpath = "//div[text()='%s']";

    @FindBy(xpath = "//button[text()='Save Changes']")
    public WebElement saveChangesButton;


    public BatchAssignPage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonOnBatch(String batchButton, String batchName){
        this.getBatchRowWebElement(batchName).findElement(By.xpath(String.format("following-sibling::div/button[text()='%s']", batchButton))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(String.format(batchXpath, batchName))))); //waiting until batch is reloaded in different section
    }

    public boolean checkBatchAssignmentStatus(String batchName, String batchAssignmentStatus){
        WebElement sectionElement = this.getBatchRowWebElement(batchName).findElement(By.xpath("ancestor::ul")); //section element is parent element in DOM
        String sectionClass = sectionElement.getAttribute("class");
        if (sectionClass.contains(batchAssignmentStatus))
            return true;
        else
            return false;
    }

    private WebElement getBatchRowWebElement(String batchName){
        List<WebElement> batchRowElements = driver.findElements(By.xpath(String.format(batchXpath, batchName)));
        if (batchRowElements.size() == 0)
            Assert.fail(String.format("Batch with name %s was not found", batchName));
        if (batchRowElements.size() > 1)
            Assert.fail(String.format("Multiple Batch jobs name %s", batchName));
        return batchRowElements.get(0);
    }
}

