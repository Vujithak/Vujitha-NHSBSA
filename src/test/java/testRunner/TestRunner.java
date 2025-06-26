package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/searchPage.feature",   // path to feature file
				  glue={"stepDefinitions","hooks"},                      // packages of step defintion and hooks
//				  dryRun = false,                                        // checks mappings of feature file line to step without running tests
//				  monochrome = true,                                     // makes console output clear
				  //tags = "@regression",
				  plugin = {"pretty", "html:target/cucumber-reports.html"} // generates html reports
				  )
public class TestRunner {

}
