package com.swaglab.java.tests;


import com.swaglab.java.common.Fixtures;

import com.swaglab.java.hooks.logs.Log;
import com.swaglab.java.models.EnvironmentData;
import java.util.HashMap;

import com.swaglab.java.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
  public static HashMap<String, String> environmentUrls;
  public static HashMap<String, HashMap<String, String>> userAccounts;

  WebDriver browser;
  String appURL;

  @BeforeTest
  @Parameters({"browserName"})
  public void setConfiguration(@Optional("chrome") String browserName) {
    Log.info("Tests is starting!");
    this.initEnvironment();
    appURL = environmentUrls.get("base_url");
    browser = Fixtures.SetUp.initBrowser(appURL, browserName);
    Log.info("Login to Swag lab");
  }

  @AfterTest(alwaysRun = true)
  public void afterTestMethods() {
    Fixtures.TearDown.close(browser);
    Log.info("Tests are ending!");
  }

  void initEnvironment() {
    EnvironmentData environmentData = new EnvironmentData();
    environmentUrls = environmentData.environmentUrls;
    userAccounts = environmentData.userAccounts;
  }

  public void loginToSwagLab(){
    String username = userAccounts.get("standard_type").get("username");
    String password = userAccounts.get("standard_type").get("password");
    LoginPage loginPage = new LoginPage();
    loginPage.doLogin(username, password);
  }

}
