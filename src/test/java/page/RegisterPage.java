package page;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import keywords.WebUI;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import net.datafaker.Faker;
import org.testng.Assert;

import java.io.ByteArrayInputStream;

import static keywords.WebUI.*;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver _driver) {
        driver = _driver;
        new WebUI(driver);
    }

    Faker faker = new Faker();
    private String URL = "https://yody.vn/";
    private String URL_REGISTER = "https://yody.vn/account/register";
    private String ERROR_NULL_NAME = "Vui lòng nhập Họ và tên";
    private String ERROR_NULL_PHONE = "Vui lòng nhập Số điện thoại";
    private String ERROR_NULL_EMAIL = "Vui lòng nhập Email";
    private String ERROR_NULL_PW = "Vui lòng nhập Mật khẩu";
    private String ERROR_PHONE = "Số điện thoại không hợp lệ.";
    private String ERROR_PHONE_2 = "Số điện thoại không hợp lệ";
    private String ERROR_SAME_PHONE = "Số điện thoại đã tồn tại.";
    private String WRONG_NAME = "Họ và tên chỉ là chữ cái";
    private String WRONG_EMAIL = "Email không hợp lệ";
    private String MESS_REGISTER_SUSCESS = "Đăng ký thành công";
    private String MESS_REGISTER_SAME_MAIL = "Email đã tồn tại. Bấm vào đây nếu bạn muốn thay đổi mật khẩu";


    By register = By.xpath("//*[text()='ĐĂNG KÝ']");
    By name = By.id("first_name");
    By phone = By.id("phone");
    By emailId = By.id("iptEmail");
    By password = By.id("password");
    By submit = By.id("btnSubmit");
    By errorMessageSameEmail = By.id("frmErrorText");
    By errorMessagePhone = By.xpath("//*[@id='customer_register']/fieldset[2]/div[@class='page-signup-error px-1']");
    By errorMessageName = By.xpath("//*[@id='customer_register']/fieldset[1]/div/div");
    By errorMessageEmail = By.xpath("//*[@id='customer_register']/fieldset[3]/div");
    By errorMessagePassword = By.xpath("//*[@id='customer_register']/fieldset[4]/div");
    By account = By.xpath("//*[text()='Cá nhân']");
    By nameInfo = By.xpath("//*[@class='block-account']//div//p");
    By messageSuccess = By.id("toast-content");

    public String generatePhone() {
        String phone = "097" + faker.number().digits(7);
        return phone;
    }

    public String generateEmail(String emailPrefix) {
        String email = emailPrefix + faker.number().digits(5) + "@tester.com";
        return email;
    }

    @Step("Click to register")
    public void clickToRegister() {
        clickElement(register);
    }

    @Step("Input name to register")
    public void enterName(String fullname) {
        setText(name, fullname);
        Allure.addAttachment("Input name: ", fullname);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Input phone to register")
    public void enterPhone(String phonenumber) {
        setText(phone, phonenumber);
        Allure.addAttachment("Input phone: ", phonenumber);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Input email to register")
    public void enterEmail(String email) {
        setText(emailId, email);
        Allure.addAttachment("Input phone: ", email);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Input password to register")
    public void enterPassword(String pw) {
        setText(password, pw);
        Allure.addAttachment("Input password: ", pw);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Click Đăng ký button")
    public void clickOnRegisterButton() {
        scrollToView(name);
        clickElement(submit);
    }

    @Step("Verify password not clean ")
    public void verifyPwNotClean() {
        try {
            Assert.assertFalse(getValue(password).equals(""));
        } catch (AssertionError e) {
            Assert.fail("Password was cleaned");
            System.out.println("Value of password is null");
        }
    }

    @Step("Check message success display")
    public void checkMessageIsDisplay() {
        waitForElementVisible(messageSuccess);
        if (isDisplay(messageSuccess)) {
            System.out.println(" Register success message display ");
        } else {
            System.out.println("Dont have Register success message");
        }
        Assert.assertTrue(isDisplay(messageSuccess));
    }


    @Step("Verify message dispaly")
    public void verifyMessageDisplay(By element, String expectMessage) {
        try {
            Assert.assertTrue(getTextElement(element).equals(expectMessage));
        } catch (AssertionError e) {
            Assert.fail("Verify message display not true");
        }
        Allure.addAttachment("Expect message display: ", expectMessage);
        Allure.addAttachment("IMAGE: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public void registerWithInvalidPhoneNumber(String fullname, String phone, String email, String password) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessagePhone, ERROR_PHONE);
    }

    public void registerWithInvalidPhoneNumber2(String fullname, String phone, String email, String password) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessagePhone, ERROR_PHONE_2);
    }

    public void registerWithPhoneHaveSpaceBetwen(String fullname, String email, String password) {
        String phone = "097" + "  " + faker.number().digits(7);
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessagePhone, ERROR_PHONE);
    }

    public void registerSuscessWithPhoneHaveSpace(String fullname, String phone, String email, String password) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        clickOnRegisterButton();
        waitForElementVisible(account);
        verifUrl(URL);
        checkMessageIsDisplay();
        verifyMessageDisplay(messageSuccess, MESS_REGISTER_SUSCESS);
    }

    public void registerSuscessWithPhoneHave84(String fullname, String email, String password) {
        String phone = "+84" + generatePhone();
        String validEmail = generateEmail(email);
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(validEmail);
        enterPassword(password);
        clickOnRegisterButton();
        waitForElementVisible(account);
        verifUrl(URL);
        checkMessageIsDisplay();
        verifyMessageDisplay(messageSuccess, MESS_REGISTER_SUSCESS);
    }

    public void registerSuscess(String fullname, String email, String password) {
        String phone = generatePhone();
        String validMail = generateEmail(email);
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(validMail);
        enterPassword(password);
        scrollToView(submit);
        clickOnRegisterButton();
        waitForElementVisible(account);
        verifUrl(URL);
        checkMessageIsDisplay();
        verifyMessageDisplay(messageSuccess, MESS_REGISTER_SUSCESS);
    }
    public void registerSuscessWithEmailHaveSpace(String fullname, String email, String password) {
        String phone = generatePhone();
        String validMail = "   " + generateEmail(email)  + "   ";
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(validMail);
        enterPassword(password);
        scrollToView(submit);
        clickOnRegisterButton();
        waitForElementVisible(account);
        verifUrl(URL);
        checkMessageIsDisplay();
        verifyMessageDisplay(messageSuccess, MESS_REGISTER_SUSCESS);
    }
    public void registerSuscessWithNameUpperLowerCase(String fullname, String email, String password) {
        String phone = generatePhone();
        String emailValid = generateEmail(email);
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(emailValid);
        enterPassword(password);
        clickOnRegisterButton();
        waitForElementVisible(account);
        verifUrl(URL);
        checkMessageIsDisplay();
        verifyMessageDisplay(messageSuccess, MESS_REGISTER_SUSCESS);
    }

    public void registerWithNameHaveNumber(String fullname, String email, String password) {
        String phone = generatePhone();
        String emailValid = generateEmail(email);
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(emailValid);
        enterPassword(password);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessageName, WRONG_NAME);
    }

    public void registerWithNameRefresh(String fullname) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        refreshPage();
        Assert.assertTrue(getTextElement(name).equals(""));
    }

    public void registerWithNullData() {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessageName, ERROR_NULL_NAME);
        verifyMessageDisplay(errorMessagePhone, ERROR_NULL_PHONE);
        verifyMessageDisplay(errorMessageEmail, ERROR_NULL_EMAIL);
        verifyMessageDisplay(errorMessagePassword, ERROR_NULL_PW);
    }

    public void registerWithNullPW(String fullname, String phone, String email) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(email);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessagePassword, ERROR_NULL_PW);
    }

    public void registerWithPWRefresh(String pww) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterPassword(pww);
        refreshPage();
        Assert.assertTrue(getTextElement(password).equals(""));
    }

    public void registerNullName(String phone, String email, String password) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        scrollToView(submit);
        clickElement(submit);
        verifyMessageDisplay(errorMessageName, ERROR_NULL_NAME);
        verifyPwNotClean();
    }

    public void registerWithExitEmail(String fullname, String phone, String email, String password) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        scrollToView(submit);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessageSameEmail, MESS_REGISTER_SAME_MAIL);
        verifyPwNotClean();
    }

    public void registerWithNullEmail(String fullname, String phone, String password) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterPassword(password);
        scrollToView(submit);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessageEmail, ERROR_NULL_EMAIL);
        verifyPwNotClean();
    }

    public void registerWithInvalidEmail(String fullname, String phone, String email, String password) {
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(email);
        enterPassword(password);
        scrollToView(submit);
        clickOnRegisterButton();
        verifyMessageDisplay(errorMessageEmail, WRONG_EMAIL);
        verifyPwNotClean();
    }

    public String handleFullName(String fullname) {
        String[] splitKey = fullname.split("\\s+");
        String newName = "";
        for (int i = 0; i < splitKey.length - 1; i++) {
            if (!(splitKey[i].equals(""))) {
                newName = newName + splitKey[i] + " ";
            }
        }
        newName = newName + splitKey[splitKey.length - 1];
        return newName;
    }

    public void verifyName(String fullname) {
        clickElement(account);
        waitForElementVisible(nameInfo);
        System.out.println("Actual: " + getTextElement(nameInfo));
        System.out.println("Expect: " + handleFullName(fullname));
        try {
            Assert.assertTrue(handleFullName(fullname).equals(getTextElement(nameInfo)));
            System.out.println("Name was removed space");
        } catch (AssertionError e) {
            Assert.fail("Name cant remove space");
        }

    }

    public void registerWithNameHaveSpace(String fullname, String email, String password) {
        String phone = generatePhone();
        String validMail = generateEmail(email);
        openURL(URL);
        waitForElementClickable(register, 20);
        clickToRegister();
        verifUrl(URL_REGISTER);
        waitForElementPresent(name);
        enterName(fullname);
        enterPhone(phone);
        enterEmail(validMail);
        enterPassword(password);
        scrollToView(submit);
        clickOnRegisterButton();
        waitForElementVisible(account);
        verifUrl(URL);
        verifyName(fullname);
    }
}
