package com.swaglab.java.pageobjects;

import org.omg.CORBA.PUBLIC_MEMBER;

public class LoginPage extends AbstractPage {
    private static final String LOGIN_LOGO = "//div[@class='login_logo']";
    private static final String USERNAME_TEXTBOX = "//input[@id='user-name']";
    private static final String PASSWORD_TEXTBOX = "//input[@id='password']";
    private static final String LOGIN_BUTTON = "//input[@id='login-button']";
    private static final String LOGIN_FAIL_MESSAGE = "//h3[@data-test = 'error']";

    public void doLogin(String username, String password){
        waitForElementVisible(USERNAME_TEXTBOX);
        sendKeyOnElement(USERNAME_TEXTBOX, username);
        waitForElementVisible(PASSWORD_TEXTBOX);
        sendKeyOnElement(PASSWORD_TEXTBOX, password);
        clickOnElement(LOGIN_BUTTON);
    }

    public String getTextLoginFailMessage() {
        return getTextElement(LOGIN_FAIL_MESSAGE);
    }

    public String getLogoText() {
        return getTextElement(LOGIN_LOGO);
    }

    public boolean checkLoginLogoIsDisplay(){
        return isElementDisplayed(LOGIN_LOGO);
    }

    public boolean checkUserNameTextBoxIsDisplay() {
        return isElementDisplayed(USERNAME_TEXTBOX);
    }

    public boolean checkPasswordTextBoxIsDisplay() {
        return isElementDisplayed(PASSWORD_TEXTBOX);
    }

    public boolean checkLoginButtonIsDisplay() {
        return isElementDisplayed(LOGIN_BUTTON);
    }
}
