package logsprod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class logprod {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start(){
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver,1000);
    }
    @Test
    public void myFirstTest() throws InterruptedException {
        //Переход на страницу админа
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(500);
        Thread.sleep(500);
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        //Переход на Каталог
        int rows = driver.findElements(By.cssSelector(".dataTable tr.row")).size();
        List<WebElement> list = driver.findElements(By.cssSelector(".dataTable tr.row"));
        ArrayList<LogEntry> logs = new ArrayList<>();
        for (int i = 3; i < list.size(); i++)
        {
            list = driver.findElements(By.cssSelector(".dataTable tr.row"));
            list.get(i).findElement(By.tagName("a")).click();
            logs.addAll(driver.manage().logs().get("browser").getAll());
            assertEquals(0, logs.size());
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
