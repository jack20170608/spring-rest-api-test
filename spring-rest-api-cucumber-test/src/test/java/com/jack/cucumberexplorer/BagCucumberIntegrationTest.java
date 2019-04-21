package com.jack.cucumberexplorer;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/bag.feature",
        "src/test/resources/features/calculator.feature" },
        plugin = {"pretty", "html:target/surefire-reports/cucumber", "json:target/surefire-reports/cucumber.json"})
public class BagCucumberIntegrationTest {
}