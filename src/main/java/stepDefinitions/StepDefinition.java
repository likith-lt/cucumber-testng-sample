package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import MyRunner.TestRunner;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

public class StepDefinition extends TestRunner {

	public RemoteWebDriver driver = TestRunner.connection;

	@When("^I open the demo site$")
    public void open_site() {
		driver.get("https://stage-demo.lambdatest.com/");
    }
    

    @Then("^Select the desired location$")
    public void select_location() {
        driver.findElement(By.id("headlessui-listbox-button-1")).click();
		driver.findElement(By.id("Bali")).click();
        System.out.println("Location is selected as Bali.");
    }

    
    @And("^Select the number of guests$")
    public void selectGuests() {
        driver.findElement(By.id("headlessui-listbox-button-5")).click();
		driver.findElement(By.id("2")).click();
        System.out.println("Number of guests are selected.");
    }

    @Then("^Search for the results$")
    public void search() {
        driver.findElement(By.xpath("//*[@id='search']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^Select one of the hotels$")
    public void selectHotel() {
        driver.findElement(By.id("reserve-now")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @And("^Proceed with booking$")
    public void confirmHotel() {
        driver.findElement(By.id("proceed")).click();
        System.out.println("Booking is confirmed.");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^Download the invoice$")
    public void downloadInvoice() {
        driver.findElement(By.id("invoice")).click();
        System.out.println("Tests are run successfully!");
    }
	
}
