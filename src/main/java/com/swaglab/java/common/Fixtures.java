package com.swaglab.java.common;

import java.awt.Toolkit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.Dimension;

public class Fixtures {

    public static class SetUp {

        public static WebDriver initBrowser(String applicationURL, String browserName) {

            BrowserControl bc = new BrowserControl();

            WebDriver browser = bc.newBrowser(browserName);

            maximizeWindow(browser);

            browser.get(applicationURL);

            return browser;
        }
    }

    public static class TearDown {

        public static void close(WebDriver browser) {
            if (browser != null) {
                browser.quit();
            }
        }
    }

    private static void maximizeWindow(WebDriver browser) {
        try {
            browser.manage().window().maximize();
        } catch (WebDriverException ex) {
            try {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenResolution = new Dimension((int)
                        toolkit.getScreenSize().getWidth(), (int)
                        toolkit.getScreenSize().getHeight());

                browser.manage().window().setSize(screenResolution);
            } catch (WebDriverException e) {
                ((JavascriptExecutor) browser).executeScript("window.moveTo(0,0); window.resizeTo(screen.width, screen.height);");
            }
        }
    }
}
