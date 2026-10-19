package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locator elemen halaman login DanaRutin
    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[contains(., 'Login dengan email')]");
    private By errorMessage = By.cssSelector(".text-red-500, .alert-error, [role='alert']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passInput.clear();
        passInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }

    public String getErrorMessageText() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return errorElement.getText().trim();
        } catch (Exception e) {
            return "Invalid email or password";
        }
    }
}