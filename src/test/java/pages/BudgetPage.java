package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BudgetPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- LOCATORS (Elemen UI Budget / Pemasukan) ---
    private By budgetSidebarMenu = By.xpath("//a[contains(@href, '/budget')] | //span[text()='Budget']");
    private By pemasukanTab = By.xpath("//button[contains(text(), 'Pemasukan')] | //a[contains(text(), 'Pemasukan')]");
    private By namaPemasukanInput = By.xpath("//input[@placeholder='Contoh: Uang magang'] | //label[contains(text(), 'Nama pemasukan')]/following-sibling::input");
    private By nominalInput = By.xpath("//input[@type='number'] | //label[contains(text(), 'Nominal')]/following-sibling::input");
    private By catatanInput = By.xpath("//input[@placeholder='Opsional']");
    private By tambahPemasukanBtn = By.xpath("//button[contains(., 'Tambah pemasukan')]");

    public BudgetPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToPemasukanTab() {
        wait.until(ExpectedConditions.elementToBeClickable(budgetSidebarMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(pemasukanTab)).click();
    }

    public void enterNamaPemasukan(String nama) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(namaPemasukanInput)).clear();
        driver.findElement(namaPemasukanInput).sendKeys(nama);
    }

    public void enterNominal(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nominalInput)).clear();
        driver.findElement(nominalInput).sendKeys(nominal);
    }

    public void enterCatatan(String catatan) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(catatanInput)).clear();
        driver.findElement(catatanInput).sendKeys(catatan);
    }

    public void clickTambahPemasukanButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahPemasukanBtn)).click();
    }
}