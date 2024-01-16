package testcase;

import common.BaseTest;
import keywords.ExcelHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.CartPage;
import page.CheckoutPage;
import page.LoginPage;
import page.SearchPage;

public class CheckoutTestcase extends BaseTest {
    LoginPage loginPage;
    SearchPage searchPage;
    CheckoutPage checkoutPage;
    CartPage cartPage;
    String username, password, key, name, phone, address, prvBilling, distBilling, wardBilling,
            voucher, nameShipping, phoneShipping, addressShipping, prvShipping, distShipping, wardShipping, qty;

    @BeforeClass
    public void setUpBrower() throws Exception {
        excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/java/data/DataYody.xlsx", "Sheet3");
    }

    @Test(priority = 1, description = "Kiểm tra nhập tên ở địa chỉ mua hàng là dãy số vẫn bấm thanh toán")
    public void TC01() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 1);
        password = excelHelpers.getCellData("password", 1);
        key = excelHelpers.getCellData("key_search", 1);
        name = excelHelpers.getCellData("name_billing", 1);
        phone = excelHelpers.getCellData("phone_billing", 1);
        address = excelHelpers.getCellData("address_billing", 1);
        prvBilling = excelHelpers.getCellData("prv_billing", 1);
        distBilling = excelHelpers.getCellData("dist_billing", 1);
        wardBilling = excelHelpers.getCellData("ward_billing", 1);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutWithNameBillingHaveNumber(name, phone, address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra nhập số điện thoại ở địa chỉ mua hàng không đúng định dạng của số điện thoại")
    public void TC02() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 2);
        password = excelHelpers.getCellData("password", 2);
        key = excelHelpers.getCellData("key_search", 2);
        name = excelHelpers.getCellData("name_billing", 2);
        phone = excelHelpers.getCellData("phone_billing", 2);
        address = excelHelpers.getCellData("address_billing", 2);
        prvBilling = excelHelpers.getCellData("prv_billing", 2);
        distBilling = excelHelpers.getCellData("dist_billing", 2);
        wardBilling = excelHelpers.getCellData("ward_billing", 2);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutWithInvalidPhoneBilling(name, phone, address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra khi bỏ qua trường địa chỉ mua hàng và bấm thanh toán")
    public void TC03() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 3);
        password = excelHelpers.getCellData("password", 3);
        key = excelHelpers.getCellData("key_search", 3);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutWithNoBillingAddress();
    }


    @Test(priority = 1, description = "Kiểm tra không chọn phương thức thanh toán vẫn bấm thanh toán")
    public void TC04() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 4);
        password = excelHelpers.getCellData("password", 4);
        key = excelHelpers.getCellData("key_search", 4);
        name = excelHelpers.getCellData("name_billing", 4);
        phone = excelHelpers.getCellData("phone_billing", 4);
        address = excelHelpers.getCellData("address_billing", 4);
        prvBilling = excelHelpers.getCellData("prv_billing", 4);
        distBilling = excelHelpers.getCellData("dist_billing", 4);
        wardBilling = excelHelpers.getCellData("ward_billing", 4);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutWithNoPaymentMethod(name, phone, address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra thanh toán bằng app VPN pay ")
    public void TC05() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 5);
        password = excelHelpers.getCellData("password", 5);
        key = excelHelpers.getCellData("key_search", 5);
        name = excelHelpers.getCellData("name_billing", 5);
        phone = excelHelpers.getCellData("phone_billing", 5);
        address = excelHelpers.getCellData("address_billing", 5);
        prvBilling = excelHelpers.getCellData("prv_billing", 5);
        distBilling = excelHelpers.getCellData("dist_billing", 5);
        wardBilling = excelHelpers.getCellData("ward_billing", 5);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutSuccessWithVnPayMethod(name, phone, address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra thanh toán bằng VPN pay - QR")
    public void TC06() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 6);
        password = excelHelpers.getCellData("password", 6);
        key = excelHelpers.getCellData("key_search", 6);
        address = excelHelpers.getCellData("address_billing", 6);
        prvBilling = excelHelpers.getCellData("prv_billing", 6);
        distBilling = excelHelpers.getCellData("dist_billing", 6);
        wardBilling = excelHelpers.getCellData("ward_billing", 6);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutSuccessWithVnPayQrMethod(address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra thanh toán bằng Momo")
    public void TC07() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 7);
        password = excelHelpers.getCellData("password", 7);
        key = excelHelpers.getCellData("key_search", 7);
        address = excelHelpers.getCellData("address_billing", 7);
        prvBilling = excelHelpers.getCellData("prv_billing", 7);
        distBilling = excelHelpers.getCellData("dist_billing", 7);
        wardBilling = excelHelpers.getCellData("ward_billing", 7);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutSuccessWithMomoMethod(address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra mua hàng, đến trang thanh toán thì đăng xuất ra")
    public void TC08() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 8);
        password = excelHelpers.getCellData("password", 8);
        key = excelHelpers.getCellData("key_search", 8);
        address = excelHelpers.getCellData("address_billing", 8);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutAndClickLogout(address);
    }

    @Test(priority = 2, description = "Kiểm tra đơn hàng lớn hơn 200 để được freeship")
    public void TC09() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 9);
        password = excelHelpers.getCellData("password", 9);
        key = excelHelpers.getCellData("key_search", 9);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkFreeShip();
    }

    @Test(priority = 2, description = "Kiểm tra đơn hàng nhỏ hơn 200 không được freeship")
    public void TC10() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 10);
        password = excelHelpers.getCellData("password", 10);
        key = excelHelpers.getCellData("key_search", 10);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkNotFreeShip();
    }

    @Test(priority = 2, description = "Kiểm tra nhập voucher không hợp lệ")
    public void TC11() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 11);
        password = excelHelpers.getCellData("password", 11);
        key = excelHelpers.getCellData("key_search", 11);
        voucher = excelHelpers.getCellData("voucher", 11);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.inputInvalidVoucher(voucher);
    }

    @Test(priority = 2, description = "Kiểm tra mua hàng không đăng nhập")
    public void TC12() throws Exception {
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        name = excelHelpers.getCellData("name_billing", 12);
        phone = excelHelpers.getCellData("phone_billing", 12);
        address = excelHelpers.getCellData("address_billing", 12);
        key = excelHelpers.getCellData("key_search", 12);
        prvBilling = excelHelpers.getCellData("prv_billing", 12);
        distBilling = excelHelpers.getCellData("dist_billing", 12);
        wardBilling = excelHelpers.getCellData("ward_billing", 12);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutWithDontLogin(name, phone, address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 2, description = "Kiểm tra mua hàng, đến bước thanh toán thì bấm quay lại")
    public void TC13() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 13);
        password = excelHelpers.getCellData("password", 13);
        key = excelHelpers.getCellData("key_search", 13);
        address = excelHelpers.getCellData("address_billing", 13);
        prvBilling = excelHelpers.getCellData("prv_billing", 13);
        distBilling = excelHelpers.getCellData("dist_billing", 13);
        wardBilling = excelHelpers.getCellData("ward_billing", 13);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.backWhenPayment(address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 2, description = "Kiểm tra mua hàng, đến bước thanh toán thì cancel")
    public void TC14() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 14);
        password = excelHelpers.getCellData("password", 14);
        key = excelHelpers.getCellData("key_search", 14);
        address = excelHelpers.getCellData("address_billing", 14);
        prvBilling = excelHelpers.getCellData("prv_billing", 14);
        distBilling = excelHelpers.getCellData("dist_billing", 14);
        wardBilling = excelHelpers.getCellData("ward_billing", 14);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.cancelPaymentSupport(address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra mua hàng với địa chỉ nhận hàng khác địa chỉ đơn hàng")
    public void TC15() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 15);
        password = excelHelpers.getCellData("password", 15);
        key = excelHelpers.getCellData("key_search", 15);
        address = excelHelpers.getCellData("address_billing", 15);
        nameShipping = excelHelpers.getCellData("name_shipping", 15);
        phoneShipping = excelHelpers.getCellData("phone_shipping", 15);
        addressShipping = excelHelpers.getCellData("address_shipping", 15);
        prvBilling = excelHelpers.getCellData("prv_billing", 15);
        distBilling = excelHelpers.getCellData("dist_billing", 15);
        wardBilling = excelHelpers.getCellData("ward_billing", 15);
        prvShipping = excelHelpers.getCellData("prv_shipping", 15);
        distShipping = excelHelpers.getCellData("dist_shipping", 15);
        wardShipping = excelHelpers.getCellData("ward_shipping", 15);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutWithDifferenceAddress(nameShipping, phoneShipping, address, prvBilling, distBilling, wardBilling, addressShipping,prvShipping, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra mua hàng với bỏ trống địa chỉ đơn hàng và nhập địa chỉ shipping hàng")
    public void TC16() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 16);
        password = excelHelpers.getCellData("password", 16);
        key = excelHelpers.getCellData("key_search", 16);
        nameShipping = excelHelpers.getCellData("name_shipping", 16);
        phoneShipping = excelHelpers.getCellData("phone_shipping", 16);
        addressShipping = excelHelpers.getCellData("address_shipping", 16);
        prvShipping = excelHelpers.getCellData("prv_shipping", 16);
        distShipping = excelHelpers.getCellData("dist_shipping", 16);
        wardShipping = excelHelpers.getCellData("ward_shipping", 16);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutDifferenceAddress(nameShipping, phoneShipping, addressShipping, prvShipping, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra mua hàng với 2 loại sản phẩm khác nhau")
    public void TC17() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 17);
        password = excelHelpers.getCellData("password", 17);
        String key1 = excelHelpers.getCellData("key_search", 17);
        String key2 = excelHelpers.getCellData("key_search2", 17);
        qty = excelHelpers.getCellData("qty", 17);
        String address = excelHelpers.getCellData("address_billing", 17);
        prvBilling = excelHelpers.getCellData("prv_billing", 17);
        distBilling = excelHelpers.getCellData("dist_billing", 17);
        wardBilling = excelHelpers.getCellData("ward_billing", 17);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key1);
        checkoutPage.addPrdPoloToCard();
        searchPage.search(key2);
        checkoutPage.addPrdHatToCard();
        cartPage.clickOnCart();
        checkoutPage.checkout(address, prvBilling, distBilling, wardBilling);
    }

    @Test(priority = 1, description = "Kiểm tra thanh toán bằng app VPN pay và số lượng là 2 sản phẩm ")
    public void TC18() throws Exception {
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        cartPage = new CartPage(driver);
        username = excelHelpers.getCellData("user_name", 18);
        password = excelHelpers.getCellData("password", 18);
        key = excelHelpers.getCellData("key_search", 18);
        qty = excelHelpers.getCellData("qty", 18);
        address = excelHelpers.getCellData("address_billing", 18);
        prvBilling = excelHelpers.getCellData("prv_billing", 18);
        distBilling = excelHelpers.getCellData("dist_billing", 18);
        wardBilling = excelHelpers.getCellData("ward_billing", 18);
        loginPage.loginSuccess(username, password);
        cartPage.removeCart();
        searchPage.search(key);
        checkoutPage.checkoutSuccessWithVnPayMethodQty2(qty, address, prvBilling, distBilling, wardBilling);
    }

}
