package webdemo.seleniumDemo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class SearchEngineTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        driver = new ChromeDriver(chromeOptions);
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://google.pl/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void enterFirstResult(){

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("trojmiasto");
        element.submit();
        List<WebElement> linkList = driver.findElements(By.tagName("a"));
        linkList.get(0).click();
        assertNotNull(driver.getTitle());
    }

    @Test
    public void enterThirdResult(){
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("trojmiasto");
        element.submit();
        List<WebElement> linkList = driver.findElements(By.className("result__a"));
        linkList.get(2).click();
        assertNotNull(driver.getTitle());
    }

    @Test
    public void differentClickTypes(){
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("trojmiasto");
        element.submit();
        List<WebElement> linkList = driver.findElements(By.tagName("a"));
        //default click
        linkList.get(0).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.history.go(-1)");
        // using sendKeys
        // linkList.get(0).sendKeys(Keys.RETURN);
        // using click from Actions class
        Actions action = new Actions(driver);
        action.click(linkList.get(0));


    }
    @Test
    public void nonExistingElements(){
        Assertions.assertThrows(NotFoundException.class, () ->
            driver.findElement(By.linkText("brbrbrbrbrbrbrbrbr")));
    }



}
