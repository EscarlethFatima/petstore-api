package com.petstore.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.petstore.stepdefs",
        plugin = {
                "pretty",
                "html:testReport/cucumber-reports.html",
                "junit:testReport/cucumber-reports/Cucumber.xml"
        }
)
public class TestRunner {
}
