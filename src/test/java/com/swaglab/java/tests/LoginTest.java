package com.swaglab.java.tests;

import com.swaglab.java.pageobjects.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Slf4j
public class LoginTest extends BaseTest{
    SoftAssert softAssert = new SoftAssert();
    String loginFailMessage = "Epic sadface: Username and password do not match any user in this service";

    @Test
    public void checkLoginPageDisplay() {
        LoginPage loginPage = new LoginPage();
        softAssert.assertTrue(loginPage.checkLoginLogoIsDisplay(), "Login logo should display");
        softAssert.assertEquals(loginPage.getLogoText(), "Swag Labs", "Login logo text should be Swag Labs");
        softAssert.assertTrue(loginPage.checkUserNameTextBoxIsDisplay(), "User name text box should display");
        softAssert.assertTrue(loginPage.checkPasswordTextBoxIsDisplay(), "Password text box should display");
        softAssert.assertTrue(loginPage.checkLoginButtonIsDisplay(), "Login button should display");
        softAssert.assertAll();
    }

    @Test
    public void verifyLoginSuccessfully(){
        LoginPage loginPage = new LoginPage();
        loginToSwagLab();
        log.info("Verify Url after login");
        Assert.assertEquals(loginPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed");
    }

    @Test
    public void verifyLoginFailWithWrongUsername(){
        String username = userAccounts.get("standard_type").get("username") + "abcd123";
        String password = userAccounts.get("standard_type").get("password");
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(username, password);
        softAssert.assertEquals(loginPage.getCurrentUrl(), "https://www.saucedemo.com/", "The url is not match with expected");
        softAssert.assertEquals(loginPage.getTextLoginFailMessage(), loginFailMessage, "The message is not match with expected");
        softAssert.assertAll();
    }

    @Test
    public void verifyLoginFailWithWrongPassword(){
        String username = userAccounts.get("standard_type").get("username");
        String password = userAccounts.get("standard_type").get("password") + "abcd123";
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(username, password);
        softAssert.assertEquals(loginPage.getCurrentUrl(), "https://www.saucedemo.com/", "The url is not match with expected");
        softAssert.assertEquals(loginPage.getTextLoginFailMessage(), loginFailMessage, "The message is not match with expected");
        softAssert.assertAll();
    }
}
