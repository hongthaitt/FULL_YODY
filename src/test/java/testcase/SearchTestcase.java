package testcase;

import common.BaseTest;
import io.qameta.allure.Description;
import keywords.ExcelHelpers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.LoginPage;
import page.SearchPage;

public class SearchTestcase extends BaseTest {
    SearchPage searchPage;

    @BeforeClass
    public void setUpBrower(){
        excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/java/data/DataYody.xlsx", "Sheet2");
    }
    @Description("Kiểm tìm kiếm keyword giống với kết quả")
    @Test(priority = 1, description = "Kiểm tìm kiếm keyword giống với kết quả")
    public void TC01() {
        searchPage = new SearchPage(driver);
        searchPage.searchProduct(excelHelpers.getCellData("Data", 1));
    }

    @Description("Kiểm tra tìm kiếm keyword giống với 1 phần kết quả")
    @Test(priority = 1, description = "Kiểm tra tìm kiếm keyword giống với 1 phần kết quả")
    public void TC02() {
        searchPage = new SearchPage(driver);
        searchPage.searchProduct(excelHelpers.getCellData("Data", 2));
    }

    @Description("Keywork chữ hoa lẫn chữ thường")
    @Test(priority = 1, description = "Keywork chữ hoa lẫn chữ thường")
    public void TC03() throws Exception {
        searchPage = new SearchPage(driver);
        searchPage.searchProduct(excelHelpers.getCellData("Data", 3));
    }

    @Description("Nhập keyword là số")
    @Test(priority = 2, description = "Nhập keyword là số")
    public void TC04() throws Exception {
        searchPage = new SearchPage(driver);
        searchPage.searchProduct(excelHelpers.getCellData("Data", 4));

    }

    @Description("Kiểm tra tìm kiếm thông tin không tồn tại")
    @Test(priority = 1, description = "Kiểm tra tìm kiếm thông tin không tồn tại")
    public void TC05(){
        searchPage = new SearchPage(driver);
        searchPage.noResult(excelHelpers.getCellData("Data", 5));
    }

    @Description("Kiểm tra tìm kiếm bằng kí tự đặc biệt")
    @Test(priority = 2, description = "Kiểm tra tìm kiếm bằng kí tự đặc biệt")
    public void TC06()  {
        searchPage = new SearchPage(driver);
        searchPage.noResult(excelHelpers.getCellData("Data", 6));
    }

    @Description("Kiểm tra nhập thông tin tìm kiếm là dãy số")
    @Test(priority = 3, description = "Kiểm tra nhập thông tin tìm kiếm là dãy số")
    public void TC07(){
        searchPage = new SearchPage(driver);
        searchPage.noResult(excelHelpers.getCellData("Data", 7));
    }

    @Description("Bỏ trống không nhập giá trị")
    @Test(priority = 3, description = "Bỏ trống không nhập giá trị")
    public void TC08() {
        searchPage = new SearchPage(driver);
        searchPage.nullDataSearch();
    }

    @Description("Check không có sản phẩm trùng khớp vẫn có thanh cuộn dọc")
    @Test(priority = 3, description = "Check không có sản phẩm trùng khớp vẫn có thanh cuộn dọc")
    public void TC09(){
        searchPage = new SearchPage(driver);
        searchPage.haveScrollBar(excelHelpers.getCellData("Data", 9));

    }

    @Description("Tìm kiếm có 1 data trùng khớp và kiểm tra thanh cuộn dọc")
    @Test(priority = 3, description = "Tìm kiếm có 1 data trùng khớp và kiểm tra thanh cuộn dọc")
    public void TC10(){
        searchPage = new SearchPage(driver);
        searchPage.haveScrollBarWith01Result(excelHelpers.getCellData("Data", 10));

    }

    @Description("Tìm kiếm với dấu cách")
    @Test(priority = 2, description = "Tìm kiếm với dấu cách")
    public void TC11() {
        searchPage = new SearchPage(driver);
        searchPage.searchProductWithSpace(excelHelpers.getCellData("Data", 11));
    }
}
