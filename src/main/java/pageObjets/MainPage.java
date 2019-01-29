package pageObjets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class MainPage extends PageObjectTemplate {

    @FindBy(xpath = "//button[text()='Create Blank Tree']")
    private WebElement createBlankTreeButton;

    @FindBy(xpath = "//input[@placeholder='New Decision Tree Name']")
    private WebElement treeNameInput;

    @FindBy(css = ".section-label-value select")
    private WebElement aiModelForDecisonTreeSelect;

    @FindBy(xpath = "//button[text()='Create New Tree']")
    private WebElement createNewTreeButton;

    @FindBy(xpath = "//input[@placeholder='Confirm your decision']")
    private WebElement treeRemoveConfirmationInput;

    @FindBy(xpath = "//button[text()='Remove this Decision Tree']")
    private WebElement removeTreeButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void addBlankTree(String treeName) {
        this.createBlankTreeButton.click();
        this.treeNameInput.sendKeys(treeName);
        new Select(this.aiModelForDecisonTreeSelect).selectByVisibleText(" sens-213 ");
        this.createNewTreeButton.click();

        if (this.isDecisionTreeCreated(treeName))
            Reporter.log(String.format("Decision tree %s was created", treeName));
        else
            Assert.fail(String.format("Decision tree %s was not created", treeName));
    }

    public void openDecisionTree(String treeName) {
        driver.findElement(By.xpath("//a[text()='test_batch_assign']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[starts-with(text(),'Decision Tree')]")));
    }

    public boolean isDecisionTreeCreated(String treeName) {
        String decisionTreeRowXpath = String.format("//table[contains(@class,'decision-trees-list')]//tr//a[text()='%s']", treeName);
        return driver.findElements(By.xpath(decisionTreeRowXpath)).size() > 0;
    }

    public void deleteTree(String treeName) {
        this.getDecisionTreeRow(treeName).findElement(By.xpath("//button[text()='Remove']")).click();
        this.treeRemoveConfirmationInput.sendKeys("Delete");

    }

    public WebElement getDecisionTreeRow(String treeName) {
        List<WebElement> allDecisionTreeRows = driver.findElements(By.cssSelector(".decision-trees-list tr"));
        for (WebElement row : allDecisionTreeRows) {
            if (row.findElements(By.xpath(String.format("//td[@class='name']//a[contains(@class, 'decision-tree-name') and text()='%s']", treeName))).size() > 0)
                return row;
        }
        Assert.fail(String.format("Decision tree with name %s was not found. Aborting test!", treeName));
        return null;
    }
}
