package com.swaglab.java.tests;

import com.swaglab.java.pageobjects.ProductPage;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Slf4j
public class ProductTest extends BaseTest{
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login(){
        loginToSwagLab();
    }

    @Test
    public void checkProductPageDisplay() {
        ProductPage productPage = new ProductPage();
        softAssert.assertTrue(productPage.checkAppLogoIsDisplay(), "App logo should display");
        softAssert.assertEquals(productPage.getAppLogoText(), "Swag Labs", "App logo text should be Swag Labs");
        softAssert.assertTrue(productPage.checkPageTitleIsDisplay(), "Page title should display");
        softAssert.assertEquals(productPage.getPageTitleText(), "Products", "Page title text should be Products");
        softAssert.assertTrue(productPage.checkFilterDropDownIsDisplay(), "Filter drop down should display");
        softAssert.assertAll();
    }
    //Cap bo du lieu
    @DataProvider(name = "sortOptions")
    public Object[][] sortOptions() {
        return new Object[][]{
                {"az", "asc"},
                {"za", "des"},
                {"lohi", "asc"},
                {"hilo", "des"}
        };
    }

    @Test(dataProvider = "sortOptions")
    public void verifySortProductResult(String sortOption, String sortType){

        ProductPage productPage = new ProductPage();

        log.info(String.format("Do filter products by %s with %s",
                sortOption.equals("az") || sortOption.equals("za") ? "name" : "price",
                sortType.equals("asc") ? "ascending" : "descending"));

        productPage.doFilter(sortOption);
        log.info("Verify filter results");

        Assert.assertTrue(sortOption.equals("az") || sortOption.equals("za")
                ? productPage.isProductNameResultsSortedCorrectly(sortType)
                : productPage.isProductPriceResultsSortedCorrectly(sortType)
                , "The filter results are not sorted with expectation");
        log.info("Filter results correct with expectation");
        log.info("Finish verifying filter results");
    }


}
