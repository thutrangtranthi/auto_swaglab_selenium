package com.swaglab.java.tests;

import com.swaglab.java.pageobjects.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Slf4j
public class PaymentTest extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login(){
        loginToSwagLab();
    }

    @Test
    public void verifyAddProductsToCartAndPaymentSuccessfully(){
        ProductPage productPage = new ProductPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        int numberOfProducts = productPage.getNumberOfProducts();

        if (numberOfProducts == 0){
            log.warn("Number of product must be not null");
            return;
        }

        log.info("Add a product to cart");
        String productName = productPage.addProductToCart(numberOfProducts);
        productPage.goToShoppingCart();
        shoppingCartPage.checkProductAddedToCartSuccessfully(productName);

//        shoppingCartPage.goBackToContinueShopping();
//        log.info("Add another product to cart");
//        if (numberOfProducts > 1){
//            productName = productPage.addProductToCart(numberOfProducts - 1);
//            productPage.goToShoppingCart();
//            shoppingCartPage.checkProductAddedToCartSuccessfully(productName);
//        }
//        else {
//            log.info("We have only one product");
//            productPage.goToShoppingCart();
//        }

        log.info("Start checkout");
        shoppingCartPage.clickCheckout();
        log.info("Enter information");
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage();
        checkoutStepOnePage.enterInformation("TRANG", "TRAN", "70000");
        checkoutStepOnePage.clickContinueButton();
        log.info("Check price!");
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage();
        softAssert.assertEquals(checkoutStepTwoPage.getTotalProductPriceCalculated(), checkoutStepTwoPage.calculatedTotalProductPrice());
        softAssert.assertEquals(checkoutStepTwoPage.getTotalPriceToPayInWeb(), checkoutStepTwoPage.calculateTotalPriceToPay());
        log.info("Finish checkout");
        checkoutStepTwoPage.clickFinishButton();
        log.info("Check complete page");
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
        softAssert.assertEquals(checkoutCompletePage.getCheckoutCompleteTitle(), "Checkout: Complete!");
        softAssert.assertEquals(checkoutCompletePage.getCheckoutCompleteHeader(), "Thank you for your order!");
        softAssert.assertEquals(checkoutCompletePage.getCheckoutCompleteMessage(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        checkoutCompletePage.clickBackHomeButton();
        softAssert.assertEquals(checkoutCompletePage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        softAssert.assertAll();

        log.info("Verify add product to cart and checkout successfully");
    }

    @Test
    public void verifyPaymentFailWithoutFillBuyerInformation() {
        ProductPage productPage = new ProductPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        int numberOfProducts = productPage.getNumberOfProducts();

        if (numberOfProducts == 0){
            log.warn("Number of product must be not null");
            return;
        }

        log.info("Add a product to cart");
        String productName = productPage.addProductToCart(numberOfProducts);
        productPage.goToShoppingCart();
        shoppingCartPage.checkProductAddedToCartSuccessfully(productName);
        shoppingCartPage.goBackToContinueShopping();

        log.info("Add another product to cart");
        if (numberOfProducts > 1){
            productName = productPage.addProductToCart(numberOfProducts - 1);
            productPage.goToShoppingCart();
            shoppingCartPage.checkProductAddedToCartSuccessfully(productName);
        }
        else {
            log.info("We have only one product");
            productPage.goToShoppingCart();
        }

        log.info("Start checkout");
        shoppingCartPage.clickCheckout();
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage();
        log.info("Check error message without enter first name information");
        checkoutStepOnePage.enterInformation("null", "null", "null");
        checkoutStepOnePage.clickContinueButton();
        softAssert.assertEquals(checkoutStepOnePage.getErrorMessageText(), "Error: First Name is required");
        log.info("Check error message without enter last name information");
        checkoutStepOnePage.enterInformation("TRANG", "null", "null");
        checkoutStepOnePage.clickContinueButton();
        softAssert.assertEquals(checkoutStepOnePage.getErrorMessageText(), "Error: Last Name is required");
        log.info("Check error message without enter postal code information");
        checkoutStepOnePage.enterInformation("TRANG", "TRAN", "null");
        checkoutStepOnePage.clickContinueButton();
        softAssert.assertEquals(checkoutStepOnePage.getErrorMessageText(), "Error: Postal Code is required");
        softAssert.assertAll();
    }
}
