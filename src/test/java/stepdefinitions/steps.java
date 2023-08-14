package stepdefinitions;


import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;
import utils.ConfigReader;
//import com.epam.reportportal.example.cucumber6.Belly;

public class steps extends BasePage{
	
//	private final Belly belly = new Belly();
	LoginPage loginPage;
	ItemsPage itemsPage;
	CheckoutPage checkoutPage;
	
	Page page;
	
	@Given("^User launched SwagLabs application$")
	public void user_launched_swaglabs_application() {
		try {
//			BrowserContext context = browser.newContext();
//			context.tracing().start(new Tracing.StartOptions()
//					  .setScreenshots(true)
//					  .setSnapshots(true));
			page = createPlaywrightPageInstance(ConfigReader.getProperty("browser"));
			page.navigate(ConfigReader.getProperty("applicationUrl"));
			
			loginPage = new LoginPage(page);
			itemsPage = new ItemsPage(page);
			checkoutPage = new CheckoutPage(page);
		    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@When("User logged in the app using username {string} and password {string}")
	public void user_logged_in_the_app_using_username_and_password(String username, String password) {
		loginPage.login(username, password);
	}

	@Then("^user should be able to log in$")
	public void logInSuccessful() {
		itemsPage.loginSuccessful();
	}

	@Then("^User should not get logged in$")
	public void logInFailed() {
		loginPage.loginFailed();
	}

	@When("User adds {string} product to the cart")
	public void user_adds_product_to_the_cart(String product) {
        itemsPage.orderProduct(product);
	}

	@When("User enters Checkout details with {string}, {string}, {string}")
	public void user_enters_Checkout_details_with(String FirstName, String LastName, String Zipcode) {
		checkoutPage.fillCheckoutDetails(FirstName, LastName, Zipcode);
	}
	
	@When("User completes Checkout process")
	public void user_completes_checkout_process() {
         checkoutPage.completeCheckout();
	}

	@Then("User should get the Confirmation of Order")
	public void user_should_get_the_Confirmation_of_Order() {
         checkoutPage.checkoutSuccessful();
	}
	
//	@Before
//    public void setUpReportPortal() {
//        ScenarioReporter.class.
//    }
	@AfterStep
    public void captureScreenshotAfterStep(Scenario scenario) {
        // Capture screenshot
//        Page page = browser.newPage();
		 byte[] screenshot = page.screenshot(new Page.ScreenshotOptions());
		 scenario.attach(screenshot, "image/png", "screenshot.png");
//        page.close();
    }
	@After
	public void tearDown(Scenario scenario) {
//		  try {
//	            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions());
//	            scenario.attach(screenshot, "image/png", "screenshot.png");
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
		if (browser != null) {
			browser.close();
		}
		if (page != null) {
			page.close();
		}
	}
}
