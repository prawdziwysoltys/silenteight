package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import pageObjets.BatchAssignPage;
import pageObjets.DecisionTreePage;
import pageObjets.LoginPage;
import pageObjets.MainPage;
import utils.Browser;
import utils.ReadPropertiesFile;

public class StepDefinition {
    public WebDriver driver;
    MainPage mainPage;
    BatchAssignPage batchAssignPage;
    DecisionTreePage decisionTreePage;
    LoginPage loginPage;
    String pickedBatchName;

    @Given("^I'm log-in as test user$")
    public void I_m_log_in_as() throws Throwable {
        this.driver = Browser.startNewBrowserInstance();
        loginPage = new LoginPage(driver);
        batchAssignPage = new BatchAssignPage(driver);
        mainPage = new MainPage(driver);
        decisionTreePage = new DecisionTreePage(driver);

        loginPage.open();
        loginPage.logIn();
    }
    /*
        Main page actions
     */

    @When("^I remove decision tree \"([^\"]*)\" if exists$")
    public void i_remove_decision_tree(String treeName) throws Throwable {

    }

    @When("^I create new decision tree \"([^\"]*)\"$")
    public void i_create_new_decision_tree(String treeName) throws Throwable {
        if (mainPage.isDecisionTreeCreated(treeName))
            mainPage.deleteTree(treeName);
        mainPage.addBlankTree(treeName);
    }

    @When("^I click on decision tree \"([^\"]*)\"$")
    public void i_click_on_decision_tree(String treeName) throws Throwable {
        mainPage.openDecisionTree(treeName);
    }

    @When("^I click Edit Assignment button$")
    public void i_click_edit_assignment_button() throws Throwable {
        decisionTreePage.openEditAssignmentWindow();
    }

    @Then("^Decision Tree \"([^\"]*)\" is visible in main page$")
    public void decision_tree_is_vsible_in_main_page(String treeName) throws Throwable {
        Assert.assertTrue(mainPage.isDecisionTreeCreated(treeName));
    }

    @Then("^Batch \"([^\"]*)\" is visible in Assigned list in Decision Tree Page$")
    public void batch_is_visible_in_assigned_list(String batchName) throws Throwable {
        if (batchName.equals("picked"))
            batchName = pickedBatchName;
        Assert.assertTrue(decisionTreePage.isBatchOnAssignedList(batchName));
    }

    @Then("^Batch \"([^\"]*)\" is not visible in Assigned list in Decision Tree Page$")
    public void batch_is_not_visible_in_assigned_list(String batchName) throws Throwable {
        if (batchName.equals("picked"))
            batchName = pickedBatchName;
        Assert.assertFalse(decisionTreePage.isBatchOnAssignedList(batchName));
    }

    /*
        Edit batch assignment actions
     */
    @When("^I click \"(Add|Remove|Activate|Deactivate)\" button on batch \"([^\"]*)\"$")
    public void i_click_button_on_batch(String buttonName, String batchName) throws Throwable {
        if (batchName.equals("picked"))
            batchName = pickedBatchName;
        batchAssignPage.clickButtonOnBatch(buttonName, batchName);
    }

    @Then("^I pick first batch from Available list$")
    public void i_pick_first_batch_from_available_list() throws Throwable {
        pickedBatchName = batchAssignPage.getNameOfFirstAvailableBatch();
    }

    @When("^I click Save Changes button in Edit Assignment window$")
    public void i_click_save_changes() throws Throwable {
        batchAssignPage.waitToClickableAndClick(batchAssignPage.saveChangesButton);
    }

    @Then("^Batch \"([^\"]*)\" is in \"(available|assigned|active)\" section$")
    public void batch_is_in_section(String batchName, String sectionName) throws Throwable {
        if (batchName.equals("picked"))
            batchName = pickedBatchName;
        Assert.assertTrue(batchAssignPage.checkBatchAssignmentStatus(batchName, sectionName));
    }

    @When("^I open main page$")
    public void iOpenMainPage() throws Throwable {
        driver.get(ReadPropertiesFile.getPropertyByKey("url"));
    }

    @AfterClass
    public void after() {
        driver.close();
    }


}
