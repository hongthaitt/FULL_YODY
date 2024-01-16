package common;

import keywords.ExcelHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    public WebDriver driver;
    public ExcelHelpers excelHelpers;

    @BeforeMethod
    public WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/WebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
