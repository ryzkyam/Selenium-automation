package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import listeners.ExtentReportManager;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setupSuite() {
        extent = ExtentReportManager.createInstance();
    }

    @BeforeMethod
    public void setUp(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginSuccess() {
        driver.get("https://danarutin.ress.my.id/login");
        test.info("Membuka halaman login DanaRutin");

        loginPage.enterEmail("reza.ramadhan.dev@gmail.com");
        loginPage.enterPassword("pekerjakeras");
        loginPage.clickLoginButton();
        test.info("Mengisi kredensial valid dan menekan tombol login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://danarutin.ress.my.id/login")));

        Assert.assertEquals(driver.getCurrentUrl(), "https://danarutin.ress.my.id/dashboard", "Login gagal, tidak pindah ke dashboard!");
        test.pass("Login berhasil dan sukses diarahkan ke halaman dashboard.");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        driver.get("https://danarutin.ress.my.id/login");
        test.info("Membuka halaman login DanaRutin untuk skenario password salah");

        loginPage.enterEmail("reza.ramadhan.dev@gmail.com");
        loginPage.enterPassword("passwordsalah123");
        loginPage.clickLoginButton();
        test.info("Mengisi email valid dengan password salah");

        String actualErrorText = loginPage.getErrorMessageText();
        System.out.println("Pesan Error yang Muncul: " + actualErrorText);
        test.info("Pesan error yang muncul: " + actualErrorText);

        Assert.assertEquals(actualErrorText, "Invalid email or password", "Pesan error tidak sesuai!");
        test.pass("Pesan error validasi tampil dengan benar.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }
}