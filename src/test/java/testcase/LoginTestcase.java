package testcase;

import common.BaseTest;
import keywords.ExcelHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.LoginPage;

public class LoginTestcase extends BaseTest {
    LoginPage loginPage;

    @BeforeClass
    public void setUpBrower() throws Exception {
        excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/java/data/DataYody.xlsx", "Sheet1");
    }

    @Test(priority = 1, description = "Kiểm tra đăng nhập thành công")
    public void TC01_loginTestSuccess() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginSuccess(excelHelpers.getCellData("username", 1), excelHelpers.getCellData("password", 1));
    }

    @Test(priority = 2, description = "Kiểm tra đăng nhập không thành công do bỏ trống dữ liệu")
    public void TC02_loginTestWithNullUsAndPw() {
        loginPage = new LoginPage(driver);
        loginPage.loginNullData();
    }

    @Test(priority = 2, description = "Kiểm tra Đăng nhập với email tồn tại và password sai")
    public void TC03_loginUnsuccessWithIncorrectPw() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginWithIncorrectPW(excelHelpers.getCellData("username", 2), excelHelpers.getCellData("password", 2));
    }

    @Test(priority = 2, description = "Kiểm tra Đăng nhập với sai email và password tồn tại")
    public void TC04_LoginWithIncorrectEmail() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginWithIncorrectUs(excelHelpers.getCellData("username", 3), excelHelpers.getCellData("password", 3));
    }

    @Test(priority = 1, description = "Kiểm tra Đăng nhập với email có chữ hoa, thường xen kẽ và password đúng")
    public void TC05_LoginWithIncorrectEmail() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginWithIncorrectUs(excelHelpers.getCellData("username", 4), excelHelpers.getCellData("password", 4));
    }

    @Test(priority = 2, description = "Kiểm tra Đăng nhập với sao email có space đầu cuối và password tồn tại")
    public void TC06_LoginSucces() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginSuccess(excelHelpers.getCellData("username", 5), excelHelpers.getCellData("password", 5));
    }

    @Test(priority = 2, description = "Đăng nhập với Bỏ trống email và nhập đúng pw ")
    public void TC07_LoginUnSucces() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginWithIncorrectUs(excelHelpers.getCellData("username", 6), excelHelpers.getCellData("password", 6));
    }

    @Test(priority = 3, description = "Kiểm tra Đăng nhập với email tồn tại  và refresh trang")
    public void TC08_LoginUnSucces() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.refreshpagewithEmail(excelHelpers.getCellData("username", 7));
    }

    @Test(priority = 2, description = "Kiểm tra với email hợp lệ và bỏ trống mật khẩu")
    public void TC09_LoginUnSuccesWithNullPW() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginWithIncorrectPw(excelHelpers.getCellData("username", 8), excelHelpers.getCellData("password", 8));
    }

    @Test(priority = 1, description = "Kiểm tra đăng nhập với email hợp lệ và mật khẩu không phân biệt chữ hóa thường")
    public void TC10_LoginUnSuccesWithIncorrectPW() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginWithIncorrectPw(excelHelpers.getCellData("username", 9), excelHelpers.getCellData("password", 9));
    }

    @Test(priority = 1, description = "Kiểm tra đăng nhập với email hợp lệ và password thêm dấu cách")
    public void TC11_LoginUnSuccesWithIncorrectPW() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginWithIncorrectPw(excelHelpers.getCellData("username", 10), excelHelpers.getCellData("password", 10));
    }

    @Test(priority = 3, description = "Kiểm tra nhập mật khẩu và refresh page")
    public void TC12_RefreshPassword() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.refreshpagewithPw(excelHelpers.getCellData("password", 11));
    }

    @Test(priority = 2, description = "Nhập email và mật khẩu chưa từng đăng ký")
    public void TC13_EmailAndPassworNotRegister() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.loginWrongUsernameAndPW(excelHelpers.getCellData("username", 12), excelHelpers.getCellData("password", 12));
    }
}
