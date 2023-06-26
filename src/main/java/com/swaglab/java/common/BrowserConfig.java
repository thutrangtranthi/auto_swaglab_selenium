package com.swaglab.java.common;

public final class BrowserConfig {

  public static Browser getBrowser(String browserName) {
    switch (browserName.toLowerCase()) {
      case "firefox":
        return Browser.FIREFOX;
      case "chrome":
        return Browser.CHROME;
      case "ie":
        return Browser.IE;
      case "edge":
        return Browser.EDGE;
      case "safari":
        return Browser.SAFARI;
      default:
        return Browser.CHROME;
    }
  }

  public enum Browser {
    FIREFOX,
    CHROME,
    IE,
    EDGE,
    SAFARI
  }

}
