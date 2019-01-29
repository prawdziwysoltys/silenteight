import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;


import java.io.File;
//import org.testng.Test;

@CucumberOptions(
        features = "src/test/features",
        glue = {"stepDefinitions"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"},
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:output/report.html"}
        )

public class TestRunner {
    private TestNGCucumberRunner cucumberRunner;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browserType"})
    public void setUpClass(String browserType) throws Exception {
        cucumberRunner = new TestNGCucumberRunner(this.getClass());
        //create driver
        //log in
    }

    @Test(groups = "userScenarios", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        cucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return cucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        cucumberRunner.finish();
//        Reporter.loadXMLConfig(new File("extent-config.xml"));
    }

}
