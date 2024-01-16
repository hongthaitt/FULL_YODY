package page;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import keywords.WebUI;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.util.List;

import static keywords.WebUI.*;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver _driver) {
        driver = _driver;
        new WebUI(driver);
    }

    private String URL = "https://yody.vn/";
    private String EXPECT_ERROR_MESSAGE = "Please fill out this field.";
    By searchField = By.xpath("//*[@id='header-search-product']//input[@name='query']");
    By searchBtn = By.xpath("//*[@id='header-search-product']//*[@class ='btn icon-fallback-text input-group-btn']");
    By listResult = By.xpath("//h3[@class='product-name']/a");
    By keySearch = By.xpath("//*[@id='textSearch']");
    By header = By.xpath("//*[@class='search-product' or @class='search-product aa']");
    By footer = By.xpath("//*[@class='footer']");
    By listResultSpaceSearch = By.xpath("//*[@id='header-search-product']//parent::div//*[@class='clearfix item_search']");
    By popup = By.xpath("//*[@class ='ins-element-content ins-selectable-element']");

    public void closePopUp(By by) {
        sleep(10);
        if (checkElementExist(by)) {
            clickElement(by);
            System.out.println("Click on close popup done");
        } else {
            System.out.println("No popup");
        }
    }

    public String getTextByInnerHtml(WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", element);
    }

    @Step("Verify result contains key")
    public void verifySearchReslt(String key, By listResult) {
        String[] splitKey = key.toLowerCase().split("\\s+");
        int count;
        String productName;
        scrollUntilEndPage();
        List<WebElement> webElementList = driver.findElements(listResult);
        System.out.println("size of list: " + webElementList.size());
        for (int i = 0; i < webElementList.size(); i++) {
            count = 0;
            if (webElementList.get(i).getText().length() == 0) {
                productName = getTextByInnerHtml(webElementList.get(i));
                System.out.println("kq tìm kiếm: " + i + "/ " + productName);
            } else {
                productName = webElementList.get(i).getText();
                System.out.println("kq tìm kiếm: " + i + "/ " + productName);
                for (int j = 0; j < splitKey.length; j++) {
                    if (productName.toLowerCase().contains(splitKey[j])) {
//                            webElementList.get(i).getText().toLowerCase().contains(key.toLowerCase())) {
                        count++;
                        System.out.println("KQ so sánh thứ" + j + webElementList.get(i).getText());
                    }
                }
                if (count == 0) {
                    System.out.println("Tên sản phẩm: " + webElementList.get(i).getText() + " không chứa từ khóa: " + key);
                }
                Assert.assertTrue(count > 0);
//            }

            }
        }
    }

    @Step("Verify no result when search")
    public void verfiyNoResult(String key) {
//        String[] splitKey = key.split("\\s+");
//        int count = 0;
        scrollUntilEndPage();
        List<WebElement> webElementList = driver.findElements(listResult);
//        System.out.println("size of list: " + webElementList.size());
//        for (int i = 1; i < webElementList.size(); i++) {
//            System.out.println("kq tìm kiếm: " + i + "/ " + webElementList.get(i).getText());
//            for (int j = 0; j < splitKey.length; j++) {
//                if ((webElementList.get(i).getText().toLowerCase().contains(splitKey[j].toLowerCase()) ||
//                        webElementList.get(i).getText().toLowerCase().contains(key.toLowerCase()) && !webElementList.get(i).getText().equals(""))) {
//                    count++;
//                    System.out.println("KQ so sánh thứ" + j + webElementList.get(i).getText());
//                }
//            }
            Assert.assertTrue(webElementList.size()==0);
//        }
    }

    @Step("Verify header contain key.")
    public void verifyHeaderSearch(String key) {
        waitForElementVisible(header);
        String actual = getTextElement(header).replace("\"", "");
        String expect = "KẾT QUẢ TÌM KIẾM SẢN PHẨM " + key;
        System.out.println("expect/ actual: " + expect + "/   " + actual);
        Assert.assertTrue(expect.equals(actual));
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Scroll until endPage")
    public void scrollUntilEndPage() {
        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(3000);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Verify have scroll bar")
    public void haveCrollBar() {
        Assert.assertTrue(verifyNoCrollBar() == true);
        Allure.addAttachment("Verify: ", " Have croll bar");
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Search product")
    public void searchProduct(String key) {
        System.out.println("key: " + key);
        openURL(URL);
        waitForElementVisible(searchField);
        setText(searchField, key);
        clickElement(searchBtn);
        verifyHeaderSearch(key);
        verifySearchReslt(key, listResult);
    }

    @Step("Search product but no result")
    public void noResult(String key) {
        System.out.println("key: " + key);
        openURL(URL);
        waitForElementVisible(searchField);
        setText(searchField, key);
        clickElement(searchBtn);
        verifyHeaderSearch(key);
        verfiyNoResult(key);
    }

    @Step("Search no result and verify no scroll bar")
    public void haveScrollBar(String key) {
        System.out.println("key: " + key);
        openURL(URL);
        waitForElementVisible(searchField);
        setText(searchField, key);
        clickElement(searchBtn);
        verifyHeaderSearch(key);
        verfiyNoResult(key);
        haveCrollBar();
    }

    @Step("Search has 1 result and verify no scroll bar")
    public void haveScrollBarWith01Result(String key) {
        System.out.println("key: " + key);
        openURL(URL);
        waitForElementVisible(searchField);
        setText(searchField, key);
        clickElement(searchBtn);
        verifyHeaderSearch(key);
        verifySearchReslt(key, listResult);
        haveCrollBar();
    }

    @Step("Verify message when no value input to search")
    public void verifyMessage(String expect) {
        Assert.assertTrue(html5(searchField).equals(expect));
        Allure.addAttachment("Verify: ", " message");
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Search when no value input to search")
    public void nullDataSearch() {
        openURL(URL);
        waitForElementVisible(searchField);
        clickElement(searchBtn);
        verifyMessage(EXPECT_ERROR_MESSAGE);
    }

    @Step("Search with space in keyboard")
    public void searchProductWithSpace(String key) {
        System.out.println("key: " + key);
        openURL(URL);
        waitForElementVisible(searchField);
        setText(searchField, key);
        enterSpace(searchField);
        verifySearchReslt(key, listResultSpaceSearch);
    }

    public void search(String key) {
        waitForElementVisible(searchField);
        setText(searchField, key);
//        closePopUp(popup);
        scrollToElementInTop();
        clickElement(searchBtn);
        closePopUp(popup);
    }
}
