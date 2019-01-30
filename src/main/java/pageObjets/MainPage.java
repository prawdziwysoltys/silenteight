package pageObjets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import utils.ReadPropertiesFile;

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
        waitToClickableAndClick(this.createBlankTreeButton);
        waitToClickableAndClick(this.treeNameInput);
        this.treeNameInput.sendKeys(treeName);
        new Select(this.aiModelForDecisonTreeSelect).selectByIndex(1);
        this.createNewTreeButton.click();

        if (this.isDecisionTreeCreated(treeName))
            Reporter.log(String.format("Decision tree %s was created", treeName));
        else
            Assert.fail(String.format("Decision tree %s was not created", treeName));
    }

    public void openDecisionTree(String treeName) {
        driver.findElement(By.xpath(String.format("//a[text()='%s']", treeName))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[starts-with(text(),'Decision Tree')]")));
    }

    public boolean isDecisionTreeCreated(String treeName) {
        String decisionTreeRowXpath = String.format("//table[contains(@class,'decision-trees-list')]//tr//a[text()='%s']", treeName);
        return driver.findElements(By.xpath(decisionTreeRowXpath)).size() > 0;
    }

    public void deleteTree(String treeName) {
        //TODO xpath for remove button to be changed
        String xpathForRemoveButton = String.format("//td[@class='name']//a[contains(@class, 'decision-tree-name') and text()='test_batch_assign']/../../..//button[text()='Remove']", treeName);
        this.driver.findElement(By.xpath(xpathForRemoveButton)).click();
        this.treeRemoveConfirmationInput.sendKeys("Delete");
        waitToClickableAndClick(this.removeTreeButton);
        this.driver.get(ReadPropertiesFile.getPropertyByKey("url"));
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
