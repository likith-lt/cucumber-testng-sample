package MyRunner;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
        features = "src/main/java/Features",
        glue = {"stepDefinitions"},
        tags = {"~@Ignore"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        },plugin = "json:target/cucumber-reports/CucumberTestReport.json")

public class TestRunner {
	
    private TestNGCucumberRunner testNGCucumberRunner;
  
    public static RemoteWebDriver connection;
    
    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
    	 testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    
    @BeforeMethod(alwaysRun = true)
    @Parameters({ "browser", "version", "platform" })
    public void setUpClass(String browser, String version, String platform) throws Exception {

    		String username = System.getenv("LT_USERNAME") == null ? "YOUR LT_USERNAME" : System.getenv("LT_USERNAME"); 
    		String accesskey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY"); 

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", browser);
            capabilities.setCapability("browserVersion", version);
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("build", "Cucumber - Java");
            ltOptions.put("name", "Cucumber Demo Test");
            ltOptions.put("platformName", platform);
            ltOptions.put("selenium_version","4.0.0");
            ltOptions.put("geoLocation", "US");
            ltOptions.put("network", true); // To enable network logs
            ltOptions.put("visual", true); // To enable step by step screenshot
            ltOptions.put("console", true); // To capture console logs
            ltOptions.put("video",true);
            capabilities.setCapability("LT:Options", ltOptions);

    		String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
    		System.out.println(gridURL);
    		connection = new RemoteWebDriver(new URL(gridURL), capabilities);
    		System.out.println(capabilities);
    		System.out.println(connection.getSessionId());
}
 
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
 
    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}