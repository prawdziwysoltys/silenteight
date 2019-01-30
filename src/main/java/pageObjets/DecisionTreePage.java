package pageObjets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class DecisionTreePage extends PageObjectTemplate {

    @FindBy(xpath = "//button[text()='Edit Assignments']")
    public WebElement editAssignmentsButton;

    @FindBy(xpath = "//div[@class='page-content']//h3[text()='Assigned']/following-sibling::ul/li")
    public List<WebElement> assignedBatchList;

    public DecisionTreePage(WebDriver driver) {
        super(driver);
    }

    public void openEditAssignmentWindow(){
        waitToClickableAndClick(this.editAssignmentsButton);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[text()='Edit Assignments']")));
    }

    public boolean isBatchOnAssignedList(String bathName){
        if (this.getAssignedBatchList().size() == 0)
            return false;
         for (WebElement positionOnList : this.getAssignedBatchList()){
            if (positionOnList.getText().equals(bathName))
                return true;
        }
        return false;
    }

    private List<WebElement> getAssignedBatchList(){
        return driver.findElements(By.xpath("//div[@class='page-content']//h3[text()='Assigned']/following-sibling::ul/li"));
    }
}
