package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;import org.testng.annotations.AfterSuite;import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;import pageObjets.BatchAssignPage;
import pageObjets.DecisionTreePage;
import pageObjets.LoginPage;
import pageObjets.MainPage;
import utils.Browser;

public class StepDefinition {
    public WebDriver driver;
    MainPage mainPage;
    BatchAssignPage editAssignmentPage;
    DecisionTreePage decisionTreePage;
    LoginPage loginPage;

    @Before
    public void before() {
        this.driver = Browser.startNewBrowserInstatnce("chrome");
    }

    @Given("^I'm log-in as test user$")
    public void I_m_log_in_as() throws Throwable {
        loginPage = new LoginPage(driver);
        editAssignmentPage = new BatchAssignPage(driver);
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
        Assert.assertTrue(decisionTreePage.isBatchOnAssignedList(batchName));
    }

    @Then("^Batch \"([^\"]*)\" is not visible in Assigned list in Decision Tree Page$")
    public void batch_is_not_visible_in_assigned_list(String batchName) throws Throwable {
        Assert.assertFalse(decisionTreePage.isBatchOnAssignedList(batchName));
    }

    /*
        Edit batch assignment actions
     */
    @When("^I click \"(Add|Remove|Activate|Deactivate)\" button on batch \"([^\"]*)\"$")
    public void i_click_button_on_batch(String buttonName, String batchName) throws Throwable {
        editAssignmentPage.clickButtonOnBatch(buttonName, batchName);
    }

    @When("^I click Save Changes button in Edit Assignment window$")
    public void i_click_save_changes() throws Throwable {
        editAssignmentPage.saveChangesButton.click();
    }

    @Then("^Batch \"([^\"]*)\" is in \"(available|assigned|active)\" section$")
    public void batch_is_in_section(String batchName, String sectionName) throws Throwable {
        Assert.assertTrue(editAssignmentPage.checkBatchAssignmentStatus(batchName, sectionName));
    }







    @AfterSuite
    public void after() {
        driver.quit();
    }
}
