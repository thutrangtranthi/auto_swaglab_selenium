package com.swaglab.java.pageobjects;

public class CheckoutStepOnePage extends AbstractPage{
    private static final String FIRST_NAME_TEXTBOX = "//input[@id='first-name']";
    private static final String LAST_NAME_TEXTBOX = "//input[@id='last-name']";
    private static final String POSTAL_CODE_TEXTBOX = "//input[@id='postal-code']";
    private static final String CONTINUE_BUTTON = "//div[@class='checkout_buttons']/input[@id='continue']";
    private static final String ERROR_MESSAGE = "//h3[@data-test='error']";

    public void enterInformation(String firstName, String lastName, String postalCode){
        if (firstName != "null") {
            sendKeyOnElement(FIRST_NAME_TEXTBOX, firstName);
        }
        if (lastName != "null") {
            sendKeyOnElement(LAST_NAME_TEXTBOX, lastName);
        }
        if (postalCode != "null") {
            sendKeyOnElement(POSTAL_CODE_TEXTBOX, postalCode);
        }
    }

    public void clickContinueButton(){
        clickOnElement(CONTINUE_BUTTON);
    }

    public String getErrorMessageText() {
        return getTextElement(ERROR_MESSAGE);
    }

}
