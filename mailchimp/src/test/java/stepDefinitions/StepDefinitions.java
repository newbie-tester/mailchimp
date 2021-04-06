package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	private WebDriver driver;

	@Given("I navigate to the sign-up page")
	public void i_navigate_to_the_sign_up_page() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://login.mailchimp.com/signup/");
		driver.manage().window().maximize();
		click(driver, By.id("onetrust-accept-btn-handler"));
	}

	@Given("enter {string}, {string} and {string}")
	public void enter_email_username_and_password(String email, String username, String password) {
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("new_username")).sendKeys(username);
		driver.findElement(By.id("new_password")).sendKeys(password);
	}

	@When("I click on the sign-up button")
	public void i_click_on_the_sign_up_button() {
		click(driver, By.id("create-account"));
	}

	@Then("A {string} appears showing the sign-up status.")
	public void a_message_appears_showing_the_sign_up_status(String message) {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement h1 = driver.findElement(By.tagName("h1"));
		
		if (h1.getText().equals("Check your email")) {
			assertEquals("Check your email", h1.getAttribute("textContent"));
		}
		else if (h1.getText().equals("Welcome to Mailchimp")) {
			WebElement errorMessage = driver.findElement(By.className("invalid-error"));			
			assertEquals(message, errorMessage.getAttribute("textContent"));
		}
		driver.quit();	
	}

	private void click(WebDriver driver, By by) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
	}

}
