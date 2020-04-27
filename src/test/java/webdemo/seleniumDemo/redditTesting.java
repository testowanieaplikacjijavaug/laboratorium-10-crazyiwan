package webdemo.seleniumDemo;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class redditTesting {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
        driver = new FirefoxDriver();
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://www.reddit.com/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }


    @Test
    public void test_count_links(){
        List<WebElement> linkList = driver.findElements(By.xpath("//a"));
        System.out.println(linkList.size());
        assertTrue(linkList.size() > 0);
    }

    @Test
    public void test_count_images(){
        List<WebElement> linkList = driver.findElements(By.xpath("//img"));
        System.out.println(linkList.size());
        assertTrue(linkList.size() > 0);
    }

    @Test
    public void test_open_every_link(){
        List<WebElement> linkList = driver.findElements(By.xpath("//a"));
        for (WebElement element: linkList){
            element.click();
        }
    }

}
