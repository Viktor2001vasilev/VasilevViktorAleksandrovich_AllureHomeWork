package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebDriverWait explicitWait = new WebDriverWait(driver, 2);
        stepsWithAvito.driver = driver;
        stepsWithAvito.explicitWait = explicitWait;
    }
    @After
    public void close() {
        stepsWithAvito.driver.quit();
    }
}
