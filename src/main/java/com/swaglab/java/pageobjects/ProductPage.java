package com.swaglab.java.pageobjects;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;

@Slf4j
public class ProductPage extends AbstractPage{
    private static final String APP_LOGO = "//div[@class='app_logo']";
    private static final String PAGE_TITLE = "//span[@class='title']";
    private static final String FILTER_DROPDOWN = "//select[@class='product_sort_container']";
    private static final String FILTER_OPTION = "//option[@value='%s']";
    private static final String INVENTORY_ITEM = "//div[@class='inventory_item']";
    private static final String INVENTORY_ITEM_NAME = "//div[@class='inventory_list']/div[%d]//div[@class='inventory_item_name']";
    private static final String INVENTORY_ITEM_PRICE = "//div[@class='inventory_list']/div[%d]//div[@class='inventory_item_price']";
    private static final String ADD_TO_CART_BUTTON = "//div[@class='inventory_list']/div[%d]//button[contains(@id, 'add-to-cart')]";
    private static final String REMOVE_BUTTON = "//div[@class='inventory_list']/div[%d]//button[contains(@id, 'remove')]";
    private static final String SHOPPING_CART = "//a[@class='shopping_cart_link']";

    public int getNumberOfProducts(){
        waitForElementVisible(INVENTORY_ITEM);
        return getSizeOfListElements(INVENTORY_ITEM);
    }
    public void doFilter(String option){
        clickOnElement(FILTER_DROPDOWN);
        waitForElementVisible(String.format(FILTER_OPTION, option));
        selectItemInDropdown(FILTER_DROPDOWN, option);
    }

    public ArrayList<String> getListProductNameResult(){
        ArrayList<String> listResult = new ArrayList<>();

        int numberOfProduct = getNumberOfProducts();
        if (numberOfProduct == 0){
            log.warn("Number of product must be not null");
            return null;
        }

        for (int i = 0; i < numberOfProduct; i++){
            listResult.add(i, getTextElement(String.format(INVENTORY_ITEM_NAME, i + 1)).trim());
        }
        log.info("List product name after filter!");
        for (String value : listResult){
            log.info("Product name: {}", value);
        }
        return listResult;
    }

    public ArrayList<Float> getListProductPriceResult(){
        ArrayList<Float> listResult = new ArrayList<>();

        int numberOfProduct = getNumberOfProducts();
        String priceValue;
        if (numberOfProduct == 0){
            log.warn("Number of product must be not null");
            return null;
        }

        for (int i = 0; i < numberOfProduct; i++){
            priceValue = getTextElement(String.format(INVENTORY_ITEM_PRICE, i + 1)).trim();
            listResult.add(i, Float.parseFloat(priceValue.replace("$","")));
        }
        log.info("List price values after filter:");
        for (Float value : listResult){
            log.info("Price value: {}", value);
        }
        return listResult;
    }

    public boolean isProductNameResultsSortedCorrectly(String sortType){
        ArrayList<String> resultList = getListProductNameResult();

        return sortType.equals("asc")
                ? isStringListSortedAscending(resultList)
                : isStringListSortedDescending(resultList);
    }

    public boolean isProductPriceResultsSortedCorrectly(String sortType){
        ArrayList<Float> resultList = getListProductPriceResult();

        return sortType.equals("asc")
                ? isFloatListSortedAscending(resultList)
                : isFloatListSortedDescending(resultList);
    }

    public String addProductToCart(int indexOfProduct){
        hoverToElement(String.format(ADD_TO_CART_BUTTON, indexOfProduct));
        clickOnElement(String.format(ADD_TO_CART_BUTTON, indexOfProduct));

        return getTextElement(String.format(INVENTORY_ITEM_NAME, indexOfProduct));
    }

    public void removeProductFromCart(int indexOfProduct) {
        hoverToElement(String.format(REMOVE_BUTTON, indexOfProduct));
        clickOnElement(String.format(REMOVE_BUTTON, indexOfProduct));
    }

    public void goToProductDetailPage(int indexOfProduct) {
        clickOnElement(String.format(INVENTORY_ITEM_NAME, indexOfProduct));
    }

    public void goToShoppingCart(){
        hoverToElement(SHOPPING_CART);
        clickOnElement(SHOPPING_CART);
    }

    public String getAppLogoText() {
        return getTextElement(APP_LOGO);
    }

    public String getPageTitleText() {
        return getTextElement(PAGE_TITLE);
    }

    public boolean checkAppLogoIsDisplay() {
        return isElementDisplayed(APP_LOGO);
    }

    public boolean checkPageTitleIsDisplay() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public boolean checkFilterDropDownIsDisplay() {
        return isElementDisplayed(FILTER_DROPDOWN);
    }
}
