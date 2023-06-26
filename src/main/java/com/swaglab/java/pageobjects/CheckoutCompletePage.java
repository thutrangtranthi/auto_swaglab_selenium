package com.swaglab.java.pageobjects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckoutCompletePage extends AbstractPage{
    private static final String CHECKOUT_COMPLETE_TITLE = "//div[@class='header_secondary_container']/span";
    private static final String CHECKOUT_COMPLETE_HEADER = "//h2[@class='complete-header']";
    private static final String CHECKOUT_COMPLETE_MESSAGE = "//div[@class='complete-text']";
    private static final String BACK_HOME_BUTTON = "//button[@id='back-to-products']";

    public String getCheckoutCompleteTitle() {
        return getTextElement(CHECKOUT_COMPLETE_TITLE);
    }

    public String getCheckoutCompleteHeader() {
        return getTextElement(CHECKOUT_COMPLETE_HEADER);
    }

    public String getCheckoutCompleteMessage() {
        return getTextElement(CHECKOUT_COMPLETE_MESSAGE);
    }

    public void clickBackHomeButton() {
        clickOnElement(BACK_HOME_BUTTON);
    }
}

