package com.swaglab.java.pageobjects;

import org.checkerframework.checker.units.qual.A;
import org.omg.CORBA.PUBLIC_MEMBER;

public class ProductDetailPage extends AbstractPage {
    public static final String ADD_TO_CART_BUTTON = "//button[contains(@id, 'add-to-cart')]";
    public static final String REMOVE_BUTTON = "//button[contains(@id, 'remove')]";
    public static final String PRODUCT_NAME = "//div[contains(@class, 'inventory_details_name')]";

    public void clickAddToCartButton() {
        hoverToElement(ADD_TO_CART_BUTTON);
        clickOnElement(ADD_TO_CART_BUTTON);
    }

    public void clickRemoveButton() {
        hoverToElement(REMOVE_BUTTON);
        clickOnElement(REMOVE_BUTTON);
    }

    public String getProductName() {
        return getTextElement(PRODUCT_NAME);
    }
}
