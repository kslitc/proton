package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "library", features = "src/test/resources/features/automated", plugin = {"pretty",
        "json:reports/cucumber/Proton.json"})
public class ProtonRunner extends AbstractTestNGCucumberTests {
}