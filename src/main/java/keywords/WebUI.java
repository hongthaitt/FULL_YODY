package keywords;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.util.List;

public class WebUI {
    private static int EXPLICIT_WAIT_TIMEOUT = 50;
    private static int WAIT_PAGE_LEADED_TIMEOUT = 50;

    public static WebDriver driver;

    public WebUI(WebDriver _driver) {
        driver = _driver;
    }

    public static WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    public static void logConsole(String message) {
        System.out.println(message);
    }

    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }


    @Step("Open URL : {0}")
    public static void openURL(String URL) {
        logConsole("Open URL: " + URL);
        driver.get(URL);
        waitForPageLoaded();
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static String getCurrentUrl() {
        waitForPageLoaded();
        logConsole("Get Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public static void verifUrl(String expectUrl) {
        Assert.assertTrue(getCurrentUrl().equals(expectUrl));
    }

    public static void clickElement(By by) {
        waitForElementVisible(by);
        highLightElement(by);
        getWebElement(by).click();
        logConsole("Click on element " + by);
    }

    public static void clickOnElement(WebElement element) {
        element.click();
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        logConsole("Set text " + value + " on element " + by);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        logConsole("Get text of element " + by);
        logConsole("==> Text: " + getWebElement(by).getText());
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return getWebElement(by).getText();

    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } catch (Exception e) {
            Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            Assert.fail("Element " + by + " is not visible.");
        }
    }

    public static void waitForUrlContain(String expectUrl) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
            wait.until(ExpectedConditions.urlContains(expectUrl));
            Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } catch (Exception e) {
            Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            Assert.fail("Url not contains: " + expectUrl);
        }

    }

    public static void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Wait element to be clickable {0} in {1} second")
    public static void waitForElementClickable(By by, int second) {
        WebDriverWait wait = new WebDriverWait(driver, second);

        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean checkElementExist(By by) {
        List<WebElement> listElement = driver.findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " existing.");
            return true;
        } else {
            System.out.println("size of element: " + listElement.size());
            System.out.println("Element " + by + " NOT exist.");
            return false;
        }
    }

    public static void scrollToView(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getWebElement(by));
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_PAGE_LEADED_TIMEOUT);
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static boolean verifyNoCrollBar() {
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        boolean vertscrollStatus = (Boolean) javascript.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
        if (vertscrollStatus == true) {
            System.out.println("Có thanh cuộn dọc hiển  thị");
        } else System.out.println("Không có thanh cuộn dọc hiển  thị");
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return vertscrollStatus;
    }

    public static String html5(By by) {
        System.out.println("Value message: " + driver.findElement(by).getAttribute("validationMessage"));
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return driver.findElement(by).getAttribute("validationMessage");
    }

    public static String getValue(By by) {
        waitForElementVisible(by);
        String text = driver.findElement(by).getAttribute("value");
        System.out.println("Log value: " + text);
        return text;
    }

    public static void enterSpace(By by) {

        driver.findElement(by).sendKeys(Keys.SPACE);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static List<WebElement> listElement(By by) {
        return driver.findElements(by);
    }

    public static void selectValue(By by, String value) {
        waitForElementVisible(by);
        Select drpCountry = new Select(driver.findElement(by));
        drpCountry.selectByValue(value);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void clearText(By by) {
        getWebElement(by).clear();
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }

    public static boolean isDisplay(By by) {
        return driver.findElement(by).isDisplayed();
    }
    public static void scrollToElementInTop(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

    }

    public static void select(){
        Select select = new Select(driver.findElement(By.id("")));
    }
}
