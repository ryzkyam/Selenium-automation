package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import listeners.ExtentReportManager;
import pages.BudgetPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BudgetTest {

    private WebDriver driver;
    private BudgetPage.LoginPage loginPage;
    private BudgetPage budgetPage;

    // Variabel untuk ExtentReports
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setupSuite() {
        extent = ExtentReportManager.createInstance();
    }

    @BeforeMethod
    public void setUpAndLogin(ITestResult result) {
        // Buat entri test di ExtentReport berdasarkan nama method
        test = extent.createTest("BudgetTest - " + result.getMethod().getMethodName());

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Flow Auto-Login
        driver.get("https://danarutin.ress.my.id/login");
        test.info("Membuka halaman login DanaRutin");

        loginPage = new BudgetPage.LoginPage(driver);
        loginPage.enterEmail("reza.ramadhan.dev@gmail.com");
        loginPage.enterPassword("pekerjakeras");
        loginPage.clickLoginButton();
        test.info("Berhasil login dan siap menguji fitur Budget");

        budgetPage = new BudgetPage(driver);
    }

    @Test
    public void testTambahPemasukanBerhasil() {
        test.info("Navigasi ke tab Pemasukan");
        budgetPage.navigateToPemasukanTab();

        test.info("Mengisi form Pemasukan: Uang Magang - Nominal Rp 1.500.000");
        budgetPage.enterNamaPemasukan("Uang Magang");
        budgetPage.enterNominal("1500000");
        budgetPage.enterCatatan("Gaji magang bulan ini");
        budgetPage.clickTambahPemasukanButton();

        test.pass("Pemasukan berhasil ditambahkan!");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Gagal: " + result.getThrowable());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush(); // Menulis seluruh log (termasuk pages.BudgetPage.LoginPage.LoginTest & BudgetTest) ke ExtentReport.html
        }
    }
}