package cucumber.Options;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = "src/test/java/featureFiles"
        ,plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        ,glue={"stepDefinitions"}
        ,tags = "@Regression")
public class TestRunner extends AbstractTestNGCucumberTests {
}
