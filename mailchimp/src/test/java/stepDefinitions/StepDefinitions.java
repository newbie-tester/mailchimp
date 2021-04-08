package stepDefinitions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	public void openSingUpPage() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://login.mailchimp.com/signup/");
		driver.manage().window().maximize();
		click(driver, By.id("onetrust-accept-btn-handler"));
	}

	@Given("enter {string}, {string} and {string}")
	public void enterCredentials(String email, String username, String password) {
		if (username.equals("random")) {
			username = getRandomUsername(username);
			email = username + "@hej.com";
		} else if (username.equals("over100characters")) {
			username = "Averylongusernameover100charactersAverylongusernameover100charactersAverylongusernameover100characters";
		}
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("new_username")).sendKeys(username);
		driver.findElement(By.id("new_password")).sendKeys(password);
	}

	@When("I click on the sign-up button")
	public void clickSignUpButton() {
//		Make sure that the sign-up button is visible and clickable
		WebElement cookie = driver.findElement(By.id("onetrust-accept-btn-handler"));
		if (cookie.isDisplayed()) {
			click(driver, By.id("onetrust-accept-btn-handler"));
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		click(driver, By.id("create-account"));
	}

	@Then("a {string} appears showing the sign-up status")
	public void verifyMessage(String message) {
		WebElement h1 = driver.findElement(By.tagName("h1"));		
		if (h1.getText().equals("Welcome to Mailchimp")) {
			WebElement errorMessage = driver.findElement(By.className("invalid-error"));			
			assertEquals(message, errorMessage.getAttribute("textContent"));
		}
		else {
			assertEquals(message, h1.getText());
		}
		driver.quit();	
	}

	private void click(WebDriver driver, By by) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
	}
	
	private String getRandomUsername(String username) {
		int number = (int) (Math.random() * 100000) + 1;
		String letter = "abcdefghijklmnopqrstuvwxyz";
		int index = (int) Math.round(Math.random() * 25);		
		username = letter.charAt(index) + Integer.toString(number);
		return username;
	}

}
