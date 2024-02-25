package stepDefinations;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;

public class FlipkartAutomationTest {
    public static void main(String[] args) {
        WebDriver driver = initializeChromeDriver();
        navigateToFlipkart(driver);
        searchForProduct(driver, "ipad");
        selectSuggestion(driver, 2);
        applyOnlineFilter(driver);
        selectProduct(driver, 5);
        switchToNewTab(driver);
        System.out.println("Page title of new tab: " + driver.getTitle());
        buyNow(driver);
        enterEmail(driver, "pokal70480@massefm.com");
        enterPhoneNumber(driver, "1234567890");
        driver.quit();
    }

    private static WebDriver initializeChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    private static void navigateToFlipkart(WebDriver driver) {
        driver.get("https://www.flipkart.com");
        driver.manage().window().maximize();
    }

    private static void searchForProduct(WebDriver driver, String product) {
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
        searchBox.sendKeys(product);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='_3D0G9a']")));
    }

    private static void selectSuggestion(WebDriver driver, int index) {
        List<WebElement> suggestions = driver.findElements(By.xpath("//li[@class='_3D0G9a']"));
        suggestions.get(index).click();
    }

    private static void applyOnlineFilter(WebDriver driver) {
        WebElement flipkartAssuredCheckbox = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div[1]/div/section[3]/label/div[1]"));
        flipkartAssuredCheckbox.click();

    }

    private static void selectProduct(WebDriver driver, int index) {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='_4ddWXP']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_4ddWXP']")));
        products.get(index).click();
    }

    private static void switchToNewTab(WebDriver driver) {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
    }

    private static void buyNow(WebDriver driver) {
        driver.findElement(By.xpath("//button[text()='Buy Now']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='_2IX_2- _17N0em']")));
    }

    private static void enterEmail(WebDriver driver, String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@class='_2IX_2- _17N0em']"));
        emailField.sendKeys(email);
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='_2IX_2- _17N0em']")));
    }

    private static void enterPhoneNumber(WebDriver driver, String phoneNumber) {
        WebElement phoneField = driver.findElement(By.xpath("//input[@class='_2IX_2- _17N0em']"));
        phoneField.sendKeys(phoneNumber);
    }
}