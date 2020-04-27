package webdemo.seleniumDemo;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class redditTesting {
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
    public void test_form_fields(){
        driver.get("https://www.reddit.com/register/");
        List<WebElement> forms = driver.findElement(By.xpath("//form")).findElements(By.xpath("./*"));
        assertEquals(3, forms.size());

    }

}
