package com.swaglab.java.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public final class BrowserControl {
  public static WebDriver browser;

  public WebDriver newBrowser(String browserName) {
      return browser = setLocalBrowser(BrowserConfig.getBrowser(browserName));
  }

  private WebDriver setLocalBrowser(BrowserConfig.Browser browser) {
    setDriversPath(browser);
    switch (browser) {
      case FIREFOX:
        return new FirefoxDriver();
      case IE:
        return new InternetExplorerDriver();
      case SAFARI:
        return new SafariDriver();
      case EDGE:
        return new EdgeDriver();
      default:
        return new ChromeDriver();
    }
  }

  private void setDriversPath(BrowserConfig.Browser browser) {
    if (SystemUtils.IS_OS_WINDOWS) {
      switch (browser) {
        case FIREFOX:
          WebDriverManager.firefoxdriver().setup();
        case EDGE:
          WebDriverManager.edgedriver().setup();
        case IE:
          WebDriverManager.iedriver().setup();
        default:
          WebDriverManager.chromedriver().setup();
      }

    } else if (SystemUtils.IS_OS_LINUX) {
      System.setProperty("webdriver.chrome.driver", "./src/bin/linux/chromedriver");
      System.setProperty("webdriver.gecko.driver", "./src/bin/linux/geckodriver");

    }
  }
}
