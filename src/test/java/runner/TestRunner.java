package runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//@ExtendWith(ReportPortalExtension.class)
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"stepdefinitions"},
        plugin = {"pretty","junit:target/junitreport.xml","json:target/jsonreport.json","html:target/cucumber-reports.html",
        		 "rerun:target/rerun.txt"  } // Save Failed test scenarios in rerun.txt file}
        
)
public class TestRunner {

    private TestRunner() {

    }
	 
}

