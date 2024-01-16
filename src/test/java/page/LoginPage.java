package page;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import keywords.WebUI;

import java.io.ByteArrayInputStream;

import static keywords.WebUI.*;


public class LoginPage {
    private String URL = "https://yody.vn/";
    private String URL_LOGIN = "https://yody.vn/account/login";
    private String URL_LOGIN_SUCCESS = "https://yody.vn/";
    private String PAGETEXT = "Login";
    private String ERROR_EMAIL = "Vui lòng nhập email";
    private String ERROR_PW = "Vui lòng nhập mật khẩu";
    private String ERROR_DATA = "Email của bạn hoặc mật khẩu không đúng, vui lòng thử lại.";

    By loginBtn = By.xpath("//*[@class='login']");
    By headerPage = By.xpath("//h1");
    By inputEmail = By.xpath("//*[@id='customer_email']");
    By inputPassword = By.xpath("//*[@id='customer_password']");
    By buttonLogin = By.xpath("//*[@class='btn-login btn-login-form']");
    By messageErrorEmail = By.xpath("//*[@id='errorEmailText']");
    By messageErrorPw = By.xpath("//*[@id='errorPassText']");
    By messageErrorData = By.xpath("//*[@id='errorData']");
    By loginSuccess = By.xpath("//*[@class='logined']");
    By account = By.xpath("//*[text()='Cá nhân']");
    By popup= By.xpath("//*[@class ='ins-element-content ins-selectable-element']");
    By email2 = By.id("customer_email");
    By buttonClosePopup = By.xpath("//*[@class='ins-element-content ins-selectable-element']");
    private WebDriver driver;
    public LoginPage(WebDriver _driver) {
        driver = _driver;
        new WebUI(driver);
    }

  @Step("Verify header search product")
    public void verifyHeaderPage(){
        Assert.assertEquals(getTextElement(headerPage), "Login", "FAIL. Header not match.");
      Allure.addAttachment("Assert: ", "header");
      Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

  }
    @Step("Verify error message dispaly")
    public void verifyErrorMessageDisplay(By element, String expectMessage) {
        Assert.assertTrue(getTextElement(element).equals(expectMessage));
        Allure.addAttachment("Expect error message display: ", expectMessage);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    @Step("Close popup")
    public void closePopUp(By by) {
        sleep(10);
        if(checkElementExist(by)){
            clickElement(by);
            System.out.println("Click on close popup done");
            Allure.addAttachment("POPUP: ", "  display");
            Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        else {
            Allure.addAttachment("POPUP: ", " dont display");
            Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            System.out.println("No popup");
        }
    }
    @Step("Input email to login")
    public void enterEmail(String email){
        setText(inputEmail, email);
        Allure.addAttachment("Input email: ", email);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    @Step("Input password to login")
    public void enterPassword(String password){
        setText(inputPassword, password);
        Allure.addAttachment("Input password: ", password);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    @Step("Click Đăng Nhập to login")
    public void clickOnLoginButton(){
        clickElement(buttonLogin);
    }
    @Step("Click Login button to login")
    public void clickToLogin()
    {
        clickElement(loginBtn);
    }
    @Step("Login successfull with email and password")
    public void loginSuccess(String email, String password){
        openURL(URL);
        waitForElementClickable(loginBtn, 20);
        clickToLogin();
        verifUrl(URL_LOGIN);
        waitForElementPresent(inputEmail);
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
        closePopUp(popup);
        waitForElementVisible(account);
        verifUrl(URL_LOGIN_SUCCESS);
//        checkElementExist(loginSuccess);
    }

    @Step("Login unsuccess with null data")
    public void loginNullData(){
        openURL(URL);
        waitForElementClickable(loginBtn, 50);
        clickToLogin();
        verifUrl(URL_LOGIN);
        waitForElementPresent(inputEmail);
        clickOnLoginButton();
        verifyErrorMessageDisplay(messageErrorEmail, ERROR_EMAIL);
        verifyErrorMessageDisplay(messageErrorPw, ERROR_PW);

    }
    @Step("Login unsuccess with Incorrect Password")
    public void loginWithIncorrectPW(String email, String password){
        openURL(URL);
        waitForElementClickable(loginBtn, 50);
        clickToLogin();
        verifUrl(URL_LOGIN);
        waitForElementPresent(inputEmail);
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
        verifyErrorMessageDisplay(messageErrorPw, ERROR_PW);
    }
    @Step("Login unsuccess with Incorrect Email")
    public void loginWithIncorrectUs(String email, String password){
        openURL(URL);
        waitForElementClickable(loginBtn, 30);
        clickToLogin();
        verifUrl(URL_LOGIN);
        waitForElementPresent(inputEmail);
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
        verifyErrorMessageDisplay(messageErrorEmail, ERROR_EMAIL);
    }
    @Step("Login unsuccess and refresh page after input email")
    public void refreshpagewithEmail(String email){
        openURL(URL);
        waitForElementClickable(loginBtn, 50);
        clickToLogin();
        verifUrl(URL_LOGIN);
        waitForElementPresent(inputEmail);
        enterEmail(email);
        driver.navigate().refresh();
        Assert.assertTrue(getTextElement(inputEmail).equals(""));
    }

    @Step("Login unsuccess with Incorrect Password")
    public void loginWithIncorrectPw(String email, String password){
        openURL(URL);
        waitForElementClickable(loginBtn, 50);
        clickToLogin();
        verifUrl(URL_LOGIN);
        waitForElementPresent(inputEmail);
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
        verifyErrorMessageDisplay(messageErrorPw, ERROR_PW);
    }
    @Step("Login unsuccess and refresh page after input password")
    public void refreshpagewithPw(String pw){
        openURL(URL);
        waitForElementClickable(loginBtn, 50);
        clickToLogin();
        verifUrl(URL_LOGIN);
        waitForElementPresent(inputEmail);
        enterPassword(pw);
        driver.navigate().refresh();
        Assert.assertTrue(getTextElement(inputPassword).equals(""));
    }
    public void loginWrongUsernameAndPW(String email, String password) {
        openURL(URL);
        clickToLogin();
        verifUrl(URL_LOGIN);
        waitForElementPresent(inputEmail);
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
        verifyErrorMessageDisplay(messageErrorEmail, ERROR_EMAIL);
        verifyErrorMessageDisplay(messageErrorPw, ERROR_PW);
    }
}
