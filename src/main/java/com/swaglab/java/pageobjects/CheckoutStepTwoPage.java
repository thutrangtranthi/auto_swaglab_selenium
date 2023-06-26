package com.swaglab.java.pageobjects;

import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;

import java.util.ArrayList;

public class CheckoutStepTwoPage extends AbstractPage{
    private static final String FINISH_BUTTON = "//div[@class='cart_footer']/button[@id='finish']";
    private static final String PRODUCT_ITEM_PRICE = "//div[@class='inventory_item_price']";
    private static final String TOTAL_PRODUCT_PRICE_CALCULATED = "//div[@class='summary_subtotal_label']";
    private static final String TAX = "//div[@class='summary_tax_label']";
    private static final String TOTAL_PRICE_TO_PAY = "//div[@class='summary_info_label summary_total_label']";

    public void clickFinishButton(){
        clickOnElement(FINISH_BUTTON);
    }

    private ArrayList<String> getListProductPrice() {
        return getListTextElements(PRODUCT_ITEM_PRICE);
    }

    public float calculatedTotalProductPrice(){
        ArrayList<String> listProductPrice = getListProductPrice();
        float totalPrice = 0;
        for (String price : listProductPrice) {
            totalPrice += Float.parseFloat(price.replace("$", "").trim());
        }
        return totalPrice;
    }

    public float getTotalProductPriceCalculated(){
        String totalPriceCalculatedString = getTextElement(TOTAL_PRODUCT_PRICE_CALCULATED);
        return Float.parseFloat(totalPriceCalculatedString.substring(totalPriceCalculatedString.indexOf("$") + 1).trim());
    }

    public float calculateTotalPriceToPay() {
        String taxString = getTextElement(TAX);
        float taxInt = Float.parseFloat(taxString.substring(taxString.indexOf("$") + 1).trim());
        return getTotalProductPriceCalculated() + taxInt;
    }

    public float getTotalPriceToPayInWeb() {
        String totalPriceToPayString = getTextElement(TOTAL_PRICE_TO_PAY);
        return Float.parseFloat(totalPriceToPayString.substring(totalPriceToPayString.indexOf("$") + 1).trim());
    }
}
