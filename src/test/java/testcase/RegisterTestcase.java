package testcase;

import common.BaseTest;
import keywords.ExcelHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.RegisterPage;

public class RegisterTestcase  extends BaseTest {
    RegisterPage registerPage;
    String email, phone, fullname, password;
    @BeforeClass
    public void setUpBrower() throws Exception {
        excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/java/data/DataYody.xlsx", "Sheet4");
    }
    @Test(priority = 1, description = "Kiểm tra nhập SĐT là số và đúng định dạng của nhà mạng")
    public void TC01() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 1);
        email = excelHelpers.getCellData("email", 1);
        password = excelHelpers.getCellData("password", 1);
        registerPage.registerSuscess(fullname, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra nhập SĐT là số và đúng định dạng của nhà mạng và có +84 ở đầu")
    public void TC02() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 2);
        phone = excelHelpers.getCellData("phone", 2);
        email = excelHelpers.getCellData("email", 2);
        password = excelHelpers.getCellData("password", 2);
        registerPage.registerSuscessWithPhoneHave84(fullname, email, password);
    }

    @Test(priority = 2, description = "Kiểm tra nhập số điện thoại ít hơn 10 số")
    public void TC03() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 3);
        phone = excelHelpers.getCellData("phone", 3);
        email = excelHelpers.getCellData("email", 3);
        password = excelHelpers.getCellData("password", 3);
        registerPage.registerWithInvalidPhoneNumber(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập số điện thoại trên 10 chữ số")
    public void TC04() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 4);
        phone = excelHelpers.getCellData("phone", 4);
        email = excelHelpers.getCellData("email", 4);
        password = excelHelpers.getCellData("password", 4);
        registerPage.registerWithInvalidPhoneNumber(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập số điện thoại đủ 10 số nhưng không bao gồm số 0 ở đầu")
    public void TC05() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 5);
        phone = excelHelpers.getCellData("phone", 5);
        email = excelHelpers.getCellData("email", 5);
        password = excelHelpers.getCellData("password", 5);
        registerPage.registerWithInvalidPhoneNumber(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập số điện thoại là 1 dãy số liên tiếp 0123456789")
    public void TC06() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 6);
        phone = excelHelpers.getCellData("phone", 6);
        email = excelHelpers.getCellData("email", 6);
        password = excelHelpers.getCellData("password", 6);
        registerPage.registerWithInvalidPhoneNumber(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập SĐT là chữ")
    public void TC07() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 7);
        phone = excelHelpers.getCellData("phone", 7);
        email = excelHelpers.getCellData("email", 7);
        password = excelHelpers.getCellData("password", 7);
        registerPage.registerWithInvalidPhoneNumber2(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập SĐT là ký tự đặc biệt")
    public void TC08() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 8);
        phone = excelHelpers.getCellData("phone", 8);
        email = excelHelpers.getCellData("email", 8);
        password = excelHelpers.getCellData("password", 8);
        registerPage.registerWithInvalidPhoneNumber2(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập SĐT là số âm")
    public void TC09() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 9);
        phone = excelHelpers.getCellData("phone", 9);
        email = excelHelpers.getCellData("email", 9);
        password = excelHelpers.getCellData("password", 9);
        registerPage.registerWithInvalidPhoneNumber(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập SĐT là số thập phân")
    public void TC10() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 10);
        phone = excelHelpers.getCellData("phone", 10);
        email = excelHelpers.getCellData("email", 10);
        password = excelHelpers.getCellData("password", 10);
        registerPage.registerWithInvalidPhoneNumber(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập SĐT là toàn số 0")
    public void TC11() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 11);
        phone = excelHelpers.getCellData("phone", 11);
        email = excelHelpers.getCellData("email", 11);
        password = excelHelpers.getCellData("password", 11);
        registerPage.registerWithInvalidPhoneNumber(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập SĐT có chưa khoảng trắng ở giữa")
    public void TC12() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 12);
        phone = excelHelpers.getCellData("phone", 12);
        email = excelHelpers.getCellData("email", 12);
        password = excelHelpers.getCellData("password", 12);
        registerPage.registerWithPhoneHaveSpaceBetwen(fullname, email, password);
    }

    @Test(priority = 2, description = "Nhập SĐT có chứa khoảng trắng ở đầu và cuối")
    public void TC13() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 13);
        phone = excelHelpers.getCellData("phone", 13);
        email = excelHelpers.getCellData("email", 13);
        password = excelHelpers.getCellData("password", 13);
        registerPage.registerSuscessWithPhoneHaveSpace(fullname, phone, email, password);
    }
    @Test(priority = 2, description = "Nhập họ tên không phân biệt hoa thường ")
    public void TC14() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 14);
        phone = excelHelpers.getCellData("phone", 14);
        email = excelHelpers.getCellData("email", 14);
        password = excelHelpers.getCellData("password", 14);
        registerPage.registerSuscessWithNameUpperLowerCase(fullname, email, password);
    }
    @Test(priority = 2, description = "Nhập họ tên có chứa số ")
    public void TC15() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 15);
        phone = excelHelpers.getCellData("phone", 15);
        email = excelHelpers.getCellData("email", 15);
        password = excelHelpers.getCellData("password", 15);
        registerPage.registerWithNameHaveNumber(fullname, email, password);
    }
    @Test(priority = 2, description = "Nhập họ tên và refresh page ")
    public void TC16() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 16);
        registerPage.registerWithNameRefresh(fullname);
    }
    @Test(priority = 2, description = "Bỏ trống hết dữ liệu và bấm đăng kí")
    public void TC17() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 16);
        registerPage.registerWithNullData();
    }
    @Test(priority = 2, description = "Bỏ trống mật khẩu")
    public void TC18() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 17);
        phone = excelHelpers.getCellData("phone", 17);
        email = excelHelpers.getCellData("email", 17);
        registerPage.registerWithNullPW(fullname, phone, email);
    }
    @Test(priority = 2, description = "Nhập mật khẩu và refresh")
    public void TC19() throws Exception {
        registerPage = new RegisterPage(driver);
        password = excelHelpers.getCellData("email", 18);
        registerPage.registerWithPWRefresh(password);
    }
    @Test(priority = 1, description = "Kiểm tra đăng kí với  mật khẩu có dấu cách")
    public void TC20() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 19);
        email = excelHelpers.getCellData("email", 19);
        password = excelHelpers.getCellData("password", 19);
        registerPage.registerSuscess(fullname, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra đăng kí khi bỏ trống trường họ tên")
    public void TC21() throws Exception {
        registerPage = new RegisterPage(driver);
        email = excelHelpers.getCellData("email", 20);
        password = excelHelpers.getCellData("password", 20);
        phone = excelHelpers.getCellData("phone", 20);
        registerPage.registerNullName(phone, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra đăng kí email trùng trong hệ thống")
    public void TC22() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 21);
        email = excelHelpers.getCellData("email", 21);
        password = excelHelpers.getCellData("password", 21);
        phone = excelHelpers.getCellData("phone", 21);
        registerPage.registerWithExitEmail(fullname,phone, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra đăng kí bỏ trống email")
    public void TC23() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 22);
        email = excelHelpers.getCellData("email", 22);
        password = excelHelpers.getCellData("password", 22);
        phone = excelHelpers.getCellData("phone", 22);
        registerPage.registerWithNullEmail(fullname,phone, password);
    }
    @Test(priority = 1, description = "Kiểm tra đăng kí có email thiếu các kí tự @, .com")
    public void TC24() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 23);
        email = excelHelpers.getCellData("email", 23);
        password = excelHelpers.getCellData("password", 23);
        phone = excelHelpers.getCellData("phone", 23);
        registerPage.registerWithInvalidEmail(fullname,phone, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra đăng kí có email là các kí tự đặc biệt")
    public void TC25() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 24);
        email = excelHelpers.getCellData("email", 24);
        password = excelHelpers.getCellData("password", 24);
        phone = excelHelpers.getCellData("phone", 24);
        registerPage.registerWithInvalidEmail(fullname,phone, email, password);
    }
    @Test(priority = 2, description = "Kiểm tra đăng kí có email có khoảng trắng đầu cuối")
    public void TC26() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 25);
        email = excelHelpers.getCellData("email", 25);
        password = excelHelpers.getCellData("password", 25);
        registerPage.registerSuscessWithEmailHaveSpace(fullname, email, password);
    }

    @Test(priority = 2, description = "Kiểm tra nhập tên chứa các khoảng trắng")
    public void TC27() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 26);
        email = excelHelpers.getCellData("email", 26);
        password = excelHelpers.getCellData("password", 26);
        registerPage.registerWithNameHaveSpace(fullname, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra đăng kí tài khoản thành công")
    public void TC28() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 27);
        email = excelHelpers.getCellData("email", 27);
        password = excelHelpers.getCellData("password", 27);
        registerPage.registerSuscess(fullname, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra nhập họ và tên hợp lệ : là chữ cái")
    public void TC29() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 28);
        email = excelHelpers.getCellData("email", 28);
        password = excelHelpers.getCellData("password", 28);
        registerPage.registerSuscess(fullname, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra nhập họ và tên hợp lệ : là chữ cái")
    public void TC30() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 29);
        email = excelHelpers.getCellData("email", 29);
        password = excelHelpers.getCellData("password", 29);
        registerPage.registerSuscess(fullname, email, password);
    }
    @Test(priority = 1, description = "Kiểm tra nhập mật khẩu hợp lệ")
    public void TC31() throws Exception {
        registerPage = new RegisterPage(driver);
        fullname = excelHelpers.getCellData("name", 30);
        email = excelHelpers.getCellData("email", 30);
        password = excelHelpers.getCellData("password", 30);
        registerPage.registerSuscess(fullname, email, password);
    }

}
