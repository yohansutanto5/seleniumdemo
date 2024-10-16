package  com.dbs;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/com/dbs/behavior/login.feature",  // Path to feature files
    glue = "com.dbs.pages",                 // Path to step definitions
    plugin = {"pretty", "html:target/cucumber-reports.html"}  // Reporting options
)
public class TestBDDRunner {
}
