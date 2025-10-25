import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class OneTest {

    @Test
    public void testSauceDemo() throws MalformedURLException {
        WebDriver driver;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        String selenoidUrl = System.getenv("SELENOID_URL");

        if (selenoidUrl == null || selenoidUrl.isEmpty()) {
            throw new IllegalStateException("SELENOID_URL is not set!");
        }

        driver = new RemoteWebDriver(new URL(selenoidUrl), options);

        driver.get("https://www.saucedemo.com");

        WebElement usernameBox = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        usernameBox.sendKeys("standard_user");
        WebElement passwordBox = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordBox.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        button.click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText(), "Sauce Labs Backpack");

        driver.quit();
    }
}
