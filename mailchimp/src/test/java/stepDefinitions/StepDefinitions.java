package stepDefinitions;

import static org.junit.Assert.assertEquals;

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
//		WebElement cookieAccept = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
//				presenceOfElementLocated(By.id("onetrust-accept-btn-handler)));
//		cookieAccept.click();
	}

	@Given("enter valid email address, username and password")
	public void enter_valid_email_address_username_and_password() {
	   WebElement email = driver.findElement(By.id("email"));
	   email.sendKeys("hej@hej.com");
	   WebElement username = driver.findElement(By.id("new_username"));
	   username.sendKeys("hej@hej.com");
	   WebElement password = driver.findElement(By.id("new_password"));
	   password.sendKeys("Abcdef+1");
	}
	
	@When("I click on the sign-up button")
	public void i_click_on_the_sign_up_button() {
		click(driver, By.id("create-account"));

//		WebElement signUpButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
//				presenceOfElementLocated(By.id("create-account")));
//		signUpButton.click();
	}
	
	@Then("I am registered as a user")
	public void i_am_registered_as_a_user() {
	    WebElement successPage = (new WebDriverWait(driver, 10)).until(ExpectedConditions.
				presenceOfElementLocated(By.tagName("h1")));
	    assertEquals("Check your email", successPage.getAttribute("textContent"));
	    driver.quit();
	}

	private void click(WebDriver driver, By by) {

		(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();

		}
	
}
