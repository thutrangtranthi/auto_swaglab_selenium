package com.swaglab.java.pageobjects;

import com.swaglab.java.common.BrowserControl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbstractPage {

    By by;
    Actions action;
    WebElement element;
    WebDriver driver = BrowserControl.browser;
    WebDriverWait waitExplicit;
    Select select;

    public AbstractPage() {
        waitExplicit = new WebDriverWait(driver, Duration.ofSeconds(30));
        action = new Actions(driver);
    }

    public void waitForElementVisible(String locator) {
        by = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void clickOnElement(String locator) {
        waitForElementVisible(locator);
        by = By.xpath(locator);
        element = driver.findElement(by);
        element.isEnabled();
        action.moveToElement(element).perform();
        element.click();
    }
    public void sendKeyOnElement(String locator, String value) {
        waitForElementVisible(locator);
        by = By.xpath(locator);
        element = driver.findElement(by);
        element.clear();
        element.sendKeys(value);
    }

    public String getTextElement(String locator) {
        waitForElementVisible(locator);
        by = By.xpath(locator);
        element = driver.findElement(by);
        return element.getText();
    }

    public boolean isElementDisplayed(String locator) {
        try {
            // Handle element exists in DOM, display or not
            by = By.xpath(locator);
            element = driver.findElement(by);
            return element.isDisplayed() || element.isEnabled();
        } catch (NoSuchElementException e) {
            // Handle element not exist in DOM
            e.printStackTrace();
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void selectItemInDropdown(String locator, String valueItem) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        select.selectByValue(valueItem);
    }

    public void hoverToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        action.moveToElement(element).perform();
    }

    public boolean isStringListSortedAscending(ArrayList<String> arrayList) {
        if (arrayList == null){
            return true;
        }
        ArrayList<String> sortList = new ArrayList<>(arrayList);
        /* Sorting */
        Collections.sort(sortList);
        return arrayList.equals(sortList);
    }

    public boolean isStringListSortedDescending(ArrayList<String> arrayList) {
        if (arrayList == null){
            return true;
        }
        ArrayList<String> sortList = new ArrayList<>(arrayList);
        /* Sorting */
        arrayList.sort(Collections.reverseOrder());
        return arrayList.equals(sortList);
    }

    public boolean isFloatListSortedAscending(ArrayList<Float> arrayList){
        if (arrayList == null){
            return true;
        }
        ArrayList<Float> sortList = new ArrayList<>(arrayList);
        /* Sorting */
        Collections.sort(sortList);
        return arrayList.equals(sortList);
    }

    public boolean isFloatListSortedDescending(ArrayList<Float> arrayList) {
        if (arrayList == null){
            return true;
        }
        ArrayList<Float> sortList = new ArrayList<>(arrayList);
        /* Sorting */
        arrayList.sort(Collections.reverseOrder());
        return arrayList.equals(sortList);
    }

    public ArrayList<String> getListTextElements(String locator) {
        List<WebElement> listElements = driver.findElements(By.xpath(locator));
        ArrayList<String> arrayList = new ArrayList<>();
        for (WebElement listElement : listElements) {
            arrayList.add(listElement.getText());
        }
        return arrayList;
    }

    public int getSizeOfListElements(String locator){
        List<WebElement> listElements = driver.findElements(By.xpath(locator));
        return listElements.size();
    }
}
