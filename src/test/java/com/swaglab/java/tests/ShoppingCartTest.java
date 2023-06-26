package com.swaglab.java.tests;

import com.swaglab.java.pageobjects.ProductDetailPage;
import com.swaglab.java.pageobjects.ProductPage;
import com.swaglab.java.pageobjects.ShoppingCartPage;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Slf4j
public class ShoppingCartTest extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login(){
        loginToSwagLab();
    }

    @Test
    public void verifyAddProductToCartAtProductPageSuccessfully() {
        ProductPage productPage = new ProductPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        int numberOfProducts = productPage.getNumberOfProducts();

        if (numberOfProducts == 0){
            log.warn("Number of product must be not null");
            return;
        }

        int numberOfProductInCartBeforeAdd = shoppingCartPage.getNumberOfProductInCart();
        log.info("Add a product to cart");
        String productName = productPage.addProductToCart(numberOfProducts);
        productPage.goToShoppingCart();
        int numberOfProductInCartAfterAdd = shoppingCartPage.getNumberOfProductInCart();
        shoppingCartPage.checkProductAddedToCartSuccessfully(productName);
        softAssert.assertEquals(numberOfProductInCartAfterAdd - numberOfProductInCartBeforeAdd, 1,
                "The number of product in cart after add a product should increase 1");
        softAssert.assertAll();
        shoppingCartPage.goBackToContinueShopping();
    }

    @Test
    public void verifyAddProductToCartAtProductDetailPageSuccessfully() {
        ProductPage productPage = new ProductPage();
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        int numberOfProducts = productPage.getNumberOfProducts();

        if (numberOfProducts == 0){
            log.warn("Number of product must be not null");
            return;
        }

        int numberOfProductInCartBeforeAdd = shoppingCartPage.getNumberOfProductInCart();
        log.info("Add a product to cart");
        productPage.goToProductDetailPage(numberOfProducts);
        String productName = productDetailPage.getProductName();
        productDetailPage.clickAddToCartButton();
        productPage.goToShoppingCart();
        int numberOfProductInCartAfterAdd = shoppingCartPage.getNumberOfProductInCart();
        shoppingCartPage.checkProductAddedToCartSuccessfully(productName);
        softAssert.assertEquals(numberOfProductInCartAfterAdd - numberOfProductInCartBeforeAdd, 1,
                "The number of product in cart after add a product should increase by 1");
        softAssert.assertAll();
        shoppingCartPage.goBackToContinueShopping();
    }

    @Test
    public void verifyRemoveProductFromCartAtProductPageSuccessfully() {
        ProductPage productPage = new ProductPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        int numberOfProducts = productPage.getNumberOfProducts();

        if (numberOfProducts == 0){
            log.warn("Number of product must be not null");
            return;
        }

        log.info("Add a product to cart");
        productPage.addProductToCart(numberOfProducts);
        int numberOfProductInCartAfterAdd = shoppingCartPage.getNumberOfProductInCart();
        log.info("Remove a product from cart");
        productPage.removeProductFromCart(numberOfProducts);
        int numberOfProductInCartAfterRemove = shoppingCartPage.getNumberOfProductInCart();
        softAssert.assertEquals(numberOfProductInCartAfterAdd - numberOfProductInCartAfterRemove, 1,
                "The number of product in cart after remove a product should reduce by 1");
        softAssert.assertAll();
    }

    @Test
    public void verifyRemoveProductFromCartAtProductDetailPageSuccessfully() {
        ProductPage productPage = new ProductPage();
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        int numberOfProducts = productPage.getNumberOfProducts();

        if (numberOfProducts == 0){
            log.warn("Number of product must be not null");
            return;
        }

        log.info("Go to product detail page");
        productPage.goToProductDetailPage(numberOfProducts);
        log.info("Add a product to cart");
        productDetailPage.clickAddToCartButton();
        int numberOfProductInCartAfterAdd = shoppingCartPage.getNumberOfProductInCart();
        log.info("Remove a product from cart");
        productDetailPage.clickRemoveButton();
        int numberOfProductInCartAfterRemove = shoppingCartPage.getNumberOfProductInCart();
        softAssert.assertEquals(numberOfProductInCartAfterAdd - numberOfProductInCartAfterRemove, 1,
                "The number of product in cart after remove a product should reduce by 1");
        softAssert.assertAll();
    }

    @Test
    public void verifyRemoveProductFromCartAtShoppingCartPageSuccessfully() {
        ProductPage productPage = new ProductPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        int numberOfProducts = productPage.getNumberOfProducts();

        if (numberOfProducts == 0){
            log.warn("Number of product must be not null");
            return;
        }

        log.info("Add a product to cart");
        productPage.addProductToCart(numberOfProducts);
        int numberOfProductInCartAfterAdd = shoppingCartPage.getNumberOfProductInCart();
        log.info("Go to shopping cart");
        productPage.goToShoppingCart();
        log.info("Remove a product from cart");
        shoppingCartPage.clickRemoveProductFromCart(numberOfProductInCartAfterAdd);
        int numberOfProductInCartAfterRemove = shoppingCartPage.getNumberOfProductInCart();
        softAssert.assertEquals(numberOfProductInCartAfterAdd - numberOfProductInCartAfterRemove, 1,
                "The number of product in cart after remove a product should reduce by 1");
        softAssert.assertAll();
    }

}
