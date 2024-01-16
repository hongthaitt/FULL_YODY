package page;

import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static keywords.WebUI.*;

public class CartPage {
    private WebDriver driver;
    public CartPage(WebDriver _driver) {
        driver = _driver;
        new WebUI(driver);
    }
    private String URL_CART ="https://yody.vn/cart";
    By removeCart = By.xpath("//*[@class='remove-cart']//*[@class='cart__btn-remove remove-item-cart ajaxifyCart--remove']");
    By deleteConfirm = By.xpath("//*[@class='button btn btn-confirm-delete']");
    By cart = By.xpath("//*[text()='GIỎ HÀNG']");
    public void removeCart(){
        openURL(URL_CART);
        List<WebElement> listElement = listElement(removeCart);
        if(listElement.size() > 0){
            System.out.println("Trong giỏ hàng có: " + listElement.size() +" sản phẩm");
            for(int i =0; i < listElement.size(); i++){
                clickOnElement(listElement.get(0));
                clickElement(deleteConfirm);
            }
        }
        else {
            System.out.println("Không có sản phẩm nào trong giỏ hàng");
        }
    }
    public void clickOnCart(){
        clickElement(cart);
    }
}
