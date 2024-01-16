package page;

import constanst.CommonConstanst;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import keywords.WebUI;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static keywords.WebUI.*;


public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver _driver) {
        driver = _driver;
        new WebUI(driver);
    }

    private String URL_CART = "https://yody.vn/cart";
    private String ERROR_NULL_NAME = "Vui lòng nhập họ tên";
    private String ERROR_INVALID_NAME = "Họ tên không hợp lệ";
    private String ERROR_INVALID_PHONE = "Số điện thoại không hợp lệ";
    private String ERROR_NULL_PHONE = "Vui lòng nhập số điện thoại";
    private String ERROR_NULL_ADDRESS = "Vui lòng nhập địa chỉ";
    private String ERROR_PAYMENT = "Bạn cần chọn phương thức thanh toán";
    private String URL_LOGIN = "https://yody.vn/account/login";
    private String URL_DIRECT_VN_PAY_METHOD = "https://pay.vnpay.vn/Transaction/PaymentMethod";
    private String ERROR_VOUCHER = "Mã khuyến mãi không hợp lệ";
    private String FREE_SHIP = "Miễn phí";
    private String PAYMENT_FAIL = "THANH TOÁN THẤT BẠI";
    private String URL_DIRECT_VN_PAY_QR = "retry_payment";
    private String URL_DIRECT_MOMO = "https://payment.momo.vn/v2/gateway";
    private List<String> productNameList = new ArrayList<>();
    By productName = By.xpath("//*[@class='title-head mb-1']");
    By price = By.xpath("//*[@class='price-box clearfix d-flex align-items-center']//*[@class='price product-price']");
    By size = By.xpath("//*[@id='swatch-1-m']//parent::div//label");
    By sizeOption = By.xpath("//*[@id='swatch-1-m']");
    By sizeNumber = By.xpath("//*[@id='swatch-1-freesize']");
    By qty = By.xpath("//*[@id='qty']");
    By qtyPlus = By.xpath("//*[@id='btn-plus']");
    //    By addToCart = By.xpath("//*[@class='btn-add-cart-sticky']//span[contains(text(),'Thêm vào giỏ hàng')]");
    By buyNow = By.xpath("//*[@class='btn-mua d-none d-lg-block']//button[2]");
    By color = By.xpath("//*[@id='product-chonsize']/div[1]/div[1]/div[1]/span");
    By paymentNow = By.xpath("//*[@id='btn-proceed-checkout']//span");
    By nameBilling = By.xpath("//*[@id='billingName']");
    By phoneBilling = By.xpath("//*[@id='billingPhone']");
    By addressBilling = By.xpath("//*[@id='billingAddress']");
    //    By provinceBilling = By.xpath("//*[@id='select2-billingProvince-container']");
//    By districtBilling = By.xpath("//*[@id='select2-billingDistrict-container']");
//    By wardBilling = By.xpath("//*[@id='select2-billingWard-container']");
    By provinceBilling = By.xpath("//*[@id='select2-billingProvince-results']//*[text()='Hà Nội']");
    By districtBilling = By.xpath("//*[@id='select2-billingDistrict-results']//*[text()='Thị xã Sơn Tây']");
    By wardBilling = By.xpath("//*[@id='select2-billingWard-results']//*[text()='Phường Lê Lợi']");
    By listResult = By.xpath("//*[@class='product-name']");
    By header = By.xpath("//*[@class='search-product' or @class='search-product aa']");
    By removeCart = By.xpath("//*[@class='remove-cart']//*[@class='cart__btn-remove remove-item-cart ajaxifyCart--remove']");
    By order = By.xpath("//*[@id='order-summary']/div/div[4]/button/span");
    By errorName = By.xpath("//*[@id='billingName']//parent::div//parent::div//*[@class='field__message field__message--error']");
    By errorPhone = By.xpath("//*[@id='billingPhone']//parent::div//parent::div//*[@class='field__message field__message--error']");
    By errorAddress = By.xpath("//*[@id='billingAddress']//parent::div//parent::div//*[@class='field__message field__message--error']");
    By provinceSelect = By.xpath("//*[@aria-labelledby='select2-billingProvince-container']");
    By districtSelect = By.xpath("//*[@aria-labelledby='select2-billingDistrict-container']");
    By wardSelect = By.xpath("//*[@aria-labelledby='select2-billingWard-container']");
    By errorPayment = By.xpath("//*[@class='alert alert--danger']");
    By appVNPayMethod = By.xpath("//*[text()='Thanh toán qua thẻ thanh toán, ứng dụng ngân hàng VNPAY']");
    By appVNPayQRMethod = By.xpath("//*[text()='Thanh toán qua VNPAY-QR']");
    By momoMethod = By.xpath("//*[text()='Thanh toán qua Ví MoMo']");
    By logout = By.xpath("//*[text()='Đăng xuất']");
    By inputVoucher = By.id("reductionCode");
    By applyVoucherBtn = By.xpath("//*[text()='Áp dụng']");
    By errorMessVoucher = By.xpath("//*[@class='field__message field__message--error']");
    By feeShip = By.xpath("//*[@class='content-box__emphasis price']");
    By totalPrice = By.xpath("//*[@class='payment-due__price']");
    //    By differenceAddress = By.id("differenceAddress");
    By back = By.xpath("//*[text()='Quay lại']");
    By messagePaymentFail = By.xpath("//*[@class='section__title section__title--fail']");
    By acceptCancelPayment = By.id("btnCancelModal");
    By paymentSupport = By.xpath("//*[contains(text(),'Ứng dụng thanh toán hỗ trợ')]");
    By cancelDetailPayment = By.xpath("//span[contains(text(),'Hủy thanh toán')]");
    //    By province = By.xpath("//*[@id='select2-billingProvince-container']");
    //địa chỉ nhận hàng khác:
    By differenceAddress = By.id("differenceAddress");
    By nameShipping = By.id("shippingName");
    By phoneShipping = By.id("shippingPhone");
    By addressShipping = By.id("shippingAddress");
    By provinceShippingSelect = By.id("select2-shippingProvince-container");
    By districtShippingSelect = By.id("select2-shippingDistrict-container");
    By wardShippingSelect = By.id("select2-shippingWard-container");
    By provinceShipping = By.xpath("//*[@id='select2-shippingProvince-results']//*[text()='Hà Nội']");
    By districtShipping = By.xpath("//*[@id='select2-shippingDistrict-results']//*[text()='Quận Ba Đình']");
    By wardShipping = By.xpath("//*[@id='select2-shippingWard-results']//*[text()='Phường Phúc Xá']");
    By addToCart = By.xpath("//*[@class='btn-mua d-none d-lg-block']//*[@id=\"add-to-cart-wrapper\"]/button[1]/span");
    By listPrdName = By.xpath("//span[@class='product__description__name']");
    @Step("Click to detail random product shirt in list result search")
    public void clickToDetailProductPolo() {
        List<WebElement> listElement = listElement(listResult);
        clickOnElement(listElement.get(7));
    }

    @Step("Click to detail random product hat in list result search")
    public void clickToDetailProductHat() {
        List<WebElement> listElement = listElement(listResult);
        clickOnElement(listElement.get(8));
    }

    @Step("Click to select size of shirt")
    public void selectSize() {
        WebElement radioBtn = driver.findElement(sizeOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn);
//        clickElement(sizeOption);
    }

    @Step("Input qty of shirt to buy: {0}")
    public void inputQty(String qtyPrd) {
//        scrollToView(qtyPlus);
        scrollToView(sizeOption);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i < Integer.parseInt(qtyPrd); i++) {
            clickElement(qtyPlus);
        }
//        clickElement(qty);
//        clearText(qty);
//        setText(qty,qtyPrd);
    }

    public void selectNumberSize() {
        WebElement radioBtn = driver.findElement(sizeNumber);
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtn);
//        clickElement(sizeOption);
    }

    public By selectProviceBilling(String prv) {
        String xpath = "//*[@id='select2-billingProvince-results']//*[text()='" + prv + "']";

        return By.xpath(xpath);
    }

    public By selectDistrictBilling(String dist) {
        String xpath = "//*[@id='select2-billingDistrict-results']//*[text()='" + dist + "']";
        return By.xpath(xpath);
    }

    public By selectWardBilling(String ward) {
        String xpath = "//*[@id='select2-billingWard-results']//*[text()='" + ward + "']";
        return By.xpath(xpath);
    }

    public By selectProviceShipping(String prv) {
        String xpath = "//*[@id='select2-shippingProvince-results']//*[text()='" + prv + "']";
        return By.xpath(xpath);
    }

    public By selectDistrictShipping(String dist) {
        String xpath = "//*[@id='select2-shippingDistrict-results']//*[text()='" + dist + "']";
        return By.xpath(xpath);
    }

    public By selectWardShipping(String ward) {
        String xpath = "//*[@id='select2-shippingWard-results']//*[text()='" + ward + "']";
        return By.xpath(xpath);
    }

    @Step("Click to logout in checkout page")
    public void logout() {
        clickElement(logout);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Input name billing")
    public void setname(String name) {
        setText(nameBilling, name);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Input phone number billing")
    public void setPhone(String phone) {
        setText(phoneBilling, phone);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public void buyNow() {
        scrollToView(sizeOption);
        clickElement(buyNow);

    }

    @Step("Get info shirt product in detail page")
    public void getInfo() {
        CommonConstanst.NAME_PRODUCT = getTextElement(productName);
        productNameList.add(getTextElement(productName));
        CommonConstanst.PRICE_PRODUCT = getTextElement(price);
        CommonConstanst.SIZE = getTextElement(size);
        CommonConstanst.QTY_PRODUCT = getValue(qty);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public void setAddToCart() {
        scrollToView(qty);
        clickElement(addToCart);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Get info hat product in detail page")
    public void getInfoHat() {
        CommonConstanst.NAME_PRODUCT = getTextElement(productName);
        productNameList.add(getTextElement(productName));
        CommonConstanst.PRICE_PRODUCT = getTextElement(price);
        CommonConstanst.QTY_PRODUCT = getValue(qty);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Verify error message display ")
    public void verifyErrorMessageDisplay(By element, String expectMessage) {
        try {
            Assert.assertTrue(getTextElement(element).equals(expectMessage));
        } catch (AssertionError e) {
            Assert.fail("Verify message display: FAILED");
        }
    }

    @Step("Verify error message not display ")
    public void verifyErrorMessageNotDisplay(By element, String expectMessage) {
        try {
            Assert.assertFalse(getTextElement(element).equals(expectMessage));
        } catch (AssertionError e) {
            Assert.fail("Verify error message not display : FAIL");
        }
    }

    @Step("Verify text display in text field ")
    public void verifyTextField(By element) {
        try {
            Assert.assertTrue(getValue(element).length() == 0);
        } catch (AssertionError e) {
            Assert.fail("Verify text display in text field :FAILED");
        }

    }

    @Step("Verify current url contain expect text {0}")
    public void verifyUrlDirectContain(String expect) {
        waitForUrlContain(expect);
    }

    @Step("Input voucher {0} to voucher field ")
    public void inputVoccher(String voucher) {
        setText(inputVoucher, voucher);
    }

    public void clickApplyVoucher() {
        clickElement(applyVoucherBtn);
    }

    //    public void verifyUrlDirectEqual(String expect){
//        verifUrl(expect);
//    }
    @Step("Verify grand total in checkout page ")
    public void verifyGrandTotal() {
        int qty = Integer.parseInt(CommonConstanst.QTY_PRODUCT);
        String price = CommonConstanst.PRICE_PRODUCT.replace(".", "").replace("đ", "");
        System.out.println("price : " + price + "   qty:  " + qty);
        int total = 0;
        if (getTextElement(feeShip).equals("Miễn phí")) {
            total = Integer.parseInt(price) * qty;
        } else {
            String fee = getTextElement(feeShip);
            String feeShip = fee.replace(".", "").replace("đ", "");
            total = Integer.parseInt(price) * qty + Integer.parseInt(feeShip);
        }
        System.out.println("total : " + total);
        String actualTotal = getTextElement(totalPrice).replace(".", "").replace("đ", "");
        System.out.println("actualTotal : " + actualTotal);
        Assert.assertTrue(String.valueOf(total).equals(actualTotal));
    }

    @Step("Click payment now button ")
    public void clickPaymentNow() {
        clickElement(paymentNow);
    }

    public void setInfoBilling(String name, String phone, String address, String province, String district, String ward) {
        setText(nameBilling, name);
        setText(phoneBilling, phone);
        setText(addressBilling, address);
        selectValue(provinceBilling, province);
        selectValue(districtBilling, district);
        selectValue(wardBilling, ward);
    }

    //    @Step("Select address in billing")
//    public void selectAddress(String address) {
//        clearText(addressBilling);
//        setText(addressBilling, address);
//        clickElement(provinceSelect);
//        clickElement(provinceBilling);
//        clickElement(districtSelect);
//        clickElement(districtBilling);
//        clickElement(wardSelect);
//        clickElement(wardBilling);
//    }
    @Step("Select address in billing")
    public void selectAddress(String address, String prv, String dist, String ward) {
        clearText(addressBilling);
        setText(addressBilling, address);
        clickElement(provinceSelect);
        clickElement(selectProviceBilling(prv));
        clickElement(districtSelect);
        clickElement(selectDistrictBilling(dist));
        clickElement(wardSelect);
        clickElement(selectWardBilling(ward));
    }

    @Step("Select difference address in shipping ")
    public void selectDifferenceAddress(String name, String phone, String address, String prv, String dist, String ward) {
        setText(nameShipping, name);
        setText(phoneShipping, phone);
        setText(addressShipping, address);
        clickElement(provinceShippingSelect);
        clickElement(selectProviceShipping(prv));
        clickElement(districtShippingSelect);
        clickElement(selectDistrictShipping(dist));
        clickElement(wardShippingSelect);
        clickElement(selectWardShipping(ward));
    }

    public void checkoutWithNoBillingAddress() {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        clearText(nameBilling);
        clearText(phoneBilling);
        clearText(addressBilling);
        clickElement(appVNPayMethod);
        clickElement(order);
        verifyErrorMessageDisplay(errorName, ERROR_NULL_NAME);
        verifyErrorMessageDisplay(errorPhone, ERROR_NULL_PHONE);
        verifyErrorMessageDisplay(errorAddress, ERROR_NULL_ADDRESS);
    }

    public void checkoutWithNoPaymentMethod(String name, String phone, String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        clearText(nameBilling);
        setname(name);
        clearText(phoneBilling);
        setPhone(phone);
        selectAddress(address, prv, dist, ward);
        clickElement(order);
        verifyErrorMessageDisplay(errorPayment, ERROR_PAYMENT);
    }

    public void checkoutWithDontLogin(String name, String phone, String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        clearText(nameBilling);
        setname(name);
        setPhone(phone);
        clearText(phoneBilling);
        selectAddress(address, prv, dist, ward);
        clickElement(order);
        verifyUrlDirectContain(URL_LOGIN);
    }

    public void checkoutSuccessWithVnPayMethod(String name, String phone, String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        clearText(nameBilling);
        setname(name);
        clearText(phoneBilling);
        setPhone(phone);
        selectAddress(address, prv, dist, ward);
        clickElement(appVNPayMethod);
        verifyGrandTotal();
//        clickElement(order);
//        verifyUrlDirectContain(URL_DIRECT_VN_PAY_METHOD);

    }

    public void checkoutWithNameBillingHaveNumber(String name, String phone, String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        clearText(nameBilling);
        setname(name);
        clearText(phoneBilling);
        setPhone(phone);
        selectAddress(address, prv, dist, ward);
        clickElement(appVNPayMethod);
        clickElement(order);
        verifyErrorMessageDisplay(errorName, ERROR_INVALID_NAME);
    }

    public void checkoutWithInvalidPhoneBilling(String name, String phone, String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        clearText(nameBilling);
        setname(name);
        clearText(phoneBilling);
        setPhone(phone);
        selectAddress(address, prv, dist, ward);
        clickElement(appVNPayMethod);
        clickElement(order);
        verifyErrorMessageDisplay(errorPhone, ERROR_INVALID_PHONE);
    }

    public void checkoutSuccessWithVnPayQrMethod(String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        selectAddress(address, prv, dist, ward);
        clickElement(appVNPayQRMethod);
        verifyGrandTotal();
        verifyProductName();
//        clickElement(order);
//        verifyUrlDirectContain(URL_DIRECT_VN_PAY_QR);
    }

    public void checkoutSuccessWithMomoMethod(String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        selectAddress(address, prv, dist, ward);
        clickElement(momoMethod);
        verifyGrandTotal();
//        clickElement(order);
//        verifyUrlDirectContain(URL_DIRECT_MOMO);
    }

    public void checkoutAndClickLogout(String address) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        logout();
        verifUrl(URL_LOGIN);
    }

    public void inputInvalidVoucher(String voucher) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        inputVoccher(voucher);
        clickApplyVoucher();
        verifyErrorMessageDisplay(errorMessVoucher, ERROR_VOUCHER);
    }

    public void checkFreeShip() {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        verifyErrorMessageDisplay(feeShip, FREE_SHIP);

    }

    public void checkNotFreeShip() {
        scrollToView(header);
        clickToDetailProductHat();
        waitForElementVisible(productName);
//        scrollToView(sizeNumber);
//        selectNumberSize();
        getInfoHat();
        scrollToView(qty);
        clickElement(buyNow);
//        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        verifyErrorMessageNotDisplay(feeShip, FREE_SHIP);
        verifyGrandTotal();
    }

    public void backWhenPayment(String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        selectAddress(address, prv, dist, ward);
        clickElement(appVNPayMethod);
        clickElement(order);
        waitForElementVisible(back);
        clickElement(back);
        clickElement(acceptCancelPayment);
        verifyErrorMessageDisplay(messagePaymentFail, PAYMENT_FAIL);
    }

    public void cancelPaymentSupport(String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        selectAddress(address, prv, dist, ward);
        clickElement(appVNPayMethod);
        clickElement(order);
        waitForElementVisible(back);
        clickElement(paymentSupport);
        clickElement(cancelDetailPayment);
        clickElement(acceptCancelPayment);
        verifyErrorMessageDisplay(messagePaymentFail, PAYMENT_FAIL);
    }

    public void checkoutWithDifferenceAddress(String name, String phone, String address1, String prv,
                                              String dist, String ward, String address2, String prvS, String distS, String wardS) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        selectAddress(address1, prv, dist, ward);
        clickElement(differenceAddress);
        scrollToView(nameShipping);
        verifyTextField(nameShipping);
        selectDifferenceAddress(name, phone, address2, prvS, distS, wardS);
        clickElement(appVNPayMethod);
//        clickElement(order);
//        verifyUrlDirectContain(URL_DIRECT_VN_PAY_METHOD);
    }

    public void checkoutDifferenceAddress(String name, String phone, String address2, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
//        selectAddress(address1);
        clearText(nameBilling);
        clickElement(differenceAddress);
        scrollToView(nameShipping);
        selectDifferenceAddress(name, phone, address2, prv, dist, ward);
        clickElement(appVNPayMethod);
        clickElement(order);
        verifyErrorMessageDisplay(errorName, ERROR_NULL_NAME);
    }

    public void checkoutSuccessWithVnPayMethodQty2(String qty, String address, String prv, String dist, String ward) {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        inputQty(qty);
        getInfo();
        buyNow();
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        selectAddress(address, prv, dist, ward);
        clickElement(appVNPayMethod);
        verifyGrandTotal();
//        clickElement(order);
//        verifyUrlDirectContain(URL_DIRECT_VN_PAY_METHOD);

    }

    @Step("Add polo shirt to cart")
    public void addPrdPoloToCard() {
        scrollToView(header);
        clickToDetailProductPolo();
        waitForElementVisible(productName);
        selectSize();
        getInfo();
        setAddToCart();
        CommonConstanst.QTY_PRODUCT_1 = CommonConstanst.QTY_PRODUCT;
        CommonConstanst.PRICE_PRODUCT_1 = CommonConstanst.PRICE_PRODUCT;
        refreshPage();
    }

    @Step("Add hat to cart")
    public void addPrdHatToCard() {
        scrollToView(header);
        clickToDetailProductHat();
        waitForElementVisible(productName);
        getInfoHat();
        scrollToView(qty);
        setAddToCart();
        waitForPageLoaded();
        CommonConstanst.QTY_PRODUCT_2 = CommonConstanst.QTY_PRODUCT;
        CommonConstanst.PRICE_PRODUCT_2 = CommonConstanst.PRICE_PRODUCT;
    }

    @Step("To checkout")
    public void checkout(String address, String prv, String dist, String ward) {
        waitForElementVisible(paymentNow);
        clickPaymentNow();
        waitForElementVisible(nameBilling);
        selectAddress(address, prv, dist, ward);
        clickElement(appVNPayMethod);
        verifyGrantotal2Prd();
        verifyProductName();
//        clickElement(order);
//        verifyUrlDirectContain(URL_DIRECT_VN_PAY_METHOD);
    }

    @Step("Verify grand total 2 product in checkout page ")
    public void verifyGrantotal2Prd() {
        int qty1 = Integer.parseInt(CommonConstanst.QTY_PRODUCT_1);
        int qty2 = Integer.parseInt(CommonConstanst.QTY_PRODUCT_2);
        String price1 = CommonConstanst.PRICE_PRODUCT_1.replace(".", "").replace("đ", "");
        String price2 = CommonConstanst.PRICE_PRODUCT_2.replace(".", "").replace("đ", "");
        System.out.println("price : " + price1 + price2 + "   qty:  " + qty1 + qty2);
        int total = 0;
        if (getTextElement(feeShip).equals("Miễn phí")) {
            total = Integer.parseInt(price1) * qty1 + Integer.parseInt(price2) * qty2;
        } else {
            String fee = getTextElement(feeShip);
            String feeShip = fee.replace(".", "").replace("đ", "");
            total = Integer.parseInt(price1) * qty1 + Integer.parseInt(price2) * qty2 + Integer.parseInt(feeShip);
        }
        System.out.println("total : " + total);
        String actualTotal = getTextElement(totalPrice).replace(".", "").replace("đ", "");
        System.out.println("actualTotal : " + actualTotal);
        Assert.assertTrue(String.valueOf(total).equals(actualTotal));
    }
    public void verifyProductName(){
        List<String> prdActual = new ArrayList<>();
        List<WebElement> prdNameXpath = driver.findElements(listPrdName);
        for(int i= 0; i <prdNameXpath.size(); i ++){
            String prd = prdNameXpath.get(i).getText();
            prdActual.add(prd);
        }
        Assert.assertTrue(prdActual.size() == productNameList.size());
        for(int j = 0; j < prdActual.size(); j++){
            System.out.println("Prd name actual/ expect:  " +prdActual.get(j) + "/ " + productNameList.get(j));
            Assert.assertTrue(prdActual.get(j).toLowerCase().equals(productNameList.get(j).toLowerCase()));
        }
    }
}
