package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.setProperty;

public class ProtonController {
    private WebDriver driver;

    /**
     * Set up log4j, chrome driver and maximize browser window
     */
    public void setUp() {
        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "log4j2.properties");
        context.setConfigLocation(file.toURI());
        setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator
                + "test" + File.separator + "resources" + File.separator + "drivers" + File.separator
                + "chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions());
        driver.manage().window().maximize();
    }

    /**
     * Close browser window and end session
     */
    public void tearDown() {
        driver.quit();
    }

    /**
     * Go to defined url
     *
     * @param url link to get access to
     */
    public void goToUrl(String url) {
        driver.get(url);
    }

    /**
     * Click on the specified element
     *
     * @param element element to be clicked
     */
    public void click(String element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        switch (element) {
            case "Email":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Email']")));
                driver.findElement(By.xpath("//a[text()='Email']")).click();
                wait.until(ExpectedConditions.urlContains("mail"));
                break;
            case "Pricing":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//header//li/a[text()='" + element +
                        "']")));
                driver.findElement(By.xpath("//header//li/a[text()='" + element + "']")).click();
                break;
            case "Businesses":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "(//div[@data-testid='pricing-page-switcher-top']//span[text()='" + element + "'])[1]")));
                driver.findElement(By.xpath("(//div[@data-testid='pricing-page-switcher-top']//span[text()='" + element
                        + "'])[1]")).click();
                wait.until(ExpectedConditions.urlContains("business"));
                break;
            case "Families":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "(//div[@data-testid='pricing-page-switcher-top']//span[text()='" + element + "'])[1]")));
                driver.findElement(By.xpath("(//div[@data-testid='pricing-page-switcher-top']//span[text()='" + element
                        + "'])[1]")).click();
                wait.until(ExpectedConditions.urlContains("family"));
                break;
            default:
                throw new IllegalStateException("Unexpected element: " + element);
        }
    }

    /**
     * Get current url
     *
     * @return current url
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Scan the webpage to look for the specified logo
     *
     * @param logo logo to look for in the webpage
     * @return true or false depending on if there is a logo matching the one specified or not
     */
    public boolean getLogo(String logo) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//header//img[@alt='" + logo + "'])[1]")));
        return driver.findElement(By.xpath("(//header//img[@alt='" + logo + "'])[1]")).isDisplayed();
    }

    /**
     * Select the specified element for a specific option
     *
     * @param element element to select
     * @param type    option to be selected
     */
    public void select(String element, String type) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        switch (type) {
            case "currency":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@data-testid='select-id']")));
                driver.findElement(By.xpath("//select[@data-testid='select-id']")).click();
                driver.findElement(By.xpath("//option[@value='" + element + "']")).click();
                break;
            case "period":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='" + element + "']")));
                driver.findElement(By.xpath("//label[text()='" + element + "']")).click();
                break;
            default:
                throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    /**
     * Get currency for the selected plan
     *
     * @param symbol symbol to check
     * @param plan   selected plan
     * @return number of occurrences for the specified currency
     */
    public int getCurrency(String symbol, String plan) {
        switch (plan) {
            case "Businesses":
                return driver.findElements(By.xpath("//div[@data-test='b2b-cards']//p[contains(text(),'" + symbol
                        + "')]")).size();
            case "Families":
                return driver.findElements(By.xpath("//div[contains(text(),'" + symbol + "')]")).size();
            default:
                throw new IllegalStateException("Unexpected plan: " + plan);
        }
    }

    /**
     * Get current plan prices
     *
     * @return plan price values
     */
    public List<String> getCurrentPlanPrices() {
        List<String> priceValues = new ArrayList<>();
        List<WebElement> prices = driver.findElements(By.xpath(
                "//div[@data-test='b2b-cards']//p[contains(text(),'€')]"));
        for (WebElement price : prices) {
            priceValues.add(price.getText());
        }
        return priceValues;
    }
}
