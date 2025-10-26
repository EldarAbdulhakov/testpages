import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class OneTest {

    @Test
    public void testSauceDemo() throws MalformedURLException {
        RemoteWebDriver driver;

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920,1080");

//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--disable-extensions");
//        options.addArguments("--disable-plugins");
//        options.addArguments("--disable-background-timer-throttling");

        String selenoidUrl = System.getenv("SELENOID_URL");

        if (selenoidUrl == null || selenoidUrl.isEmpty()) {
            throw new IllegalStateException("SELENOID_URL is not set!");
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("121.0");

        driver = new RemoteWebDriver(new URL(selenoidUrl), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://www.saucedemo.com");

        WebElement usernameBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"user-name\"]")));
        usernameBox.sendKeys("standard_user");
        WebElement passwordBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]")));
        passwordBox.sendKeys("secret_sauce");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login-button\"]")));
        button.click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText(), "Sauce Labs Backpack");

        driver.quit();
    }
}
