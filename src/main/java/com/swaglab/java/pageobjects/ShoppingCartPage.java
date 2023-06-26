package com.swaglab.java.pageobjects;

import lombok.extern.slf4j.Slf4j;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

@Slf4j
public class ShoppingCartPage extends AbstractPage{
    SoftAssert softAssert = new SoftAssert();
    private static final String PRODUCT_NAME = "//div[@class='inventory_item_name']";
    private static final String CONTINUE_SHOPPING_BUTTON = "//div[@class='cart_footer']/button[@id='continue-shopping']";
    private static final String CHECKOUT_BUTTON = "//div[@class='cart_footer']/button[@id='checkout']";
    private static final String NUMBER_OF_PRODUCT = "//span[@class='shopping_cart_badge']";
    private static final String REMOVE_BUTTON = "//div[@class='cart_item'][%d]//button[contains(@id, 'remove')]";

    private ArrayList<String> getListProductsName(){
        return getListTextElements(PRODUCT_NAME);
    }

    public boolean isProductAddedToCartSuccessfully(String productName){
        for (String product : getListProductsName()){
            if (productName.equals(product)){
                return true;
            }
        }
        return false;
    }

    public void goBackToContinueShopping(){
        clickOnElement(CONTINUE_SHOPPING_BUTTON);
    }

    public void clickCheckout(){
        clickOnElement(CHECKOUT_BUTTON);
    }

    public void checkProductAddedToCartSuccessfully(String productName){
        log.info("Verify product added to cart successfully");
        softAssert.assertTrue(isProductAddedToCartSuccessfully(productName), "Product is not added to cart");
    }

    public int getNumberOfProductInCart() {
        return isElementDisplayed(NUMBER_OF_PRODUCT)
                ? Integer.parseInt(getTextElement(NUMBER_OF_PRODUCT))
                : 0;
    }

    public void clickRemoveProductFromCart(int indexOfProduct) {
        clickOnElement(String.format(REMOVE_BUTTON, indexOfProduct));
    }

}
