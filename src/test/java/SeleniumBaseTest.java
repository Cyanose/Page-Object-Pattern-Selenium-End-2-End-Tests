import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class SeleniumBaseTest {
    protected WebDriver driver;
    protected Config config;

    @BeforeMethod
    public void baseBeforeMethod(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        this.config = new Config();
        driver.get(config.getApplicationUrl());
    }
    /*
     @AfterMethod
     public void baseAfterMethod(){
         driver.quit();
    }

     */
}
