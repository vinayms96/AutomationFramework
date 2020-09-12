package com.magento.pageModels;

import com.magento.extent_reports.ExtentReport;
import com.magento.loggers.Loggers;
import com.magento.modules.MouseActions;
import com.magento.modules.WebdriverWait;
import com.magento.utilities.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class OrderSuccessModel {
    private static String order_number;

    @FindBy(xpath = "//div[@class='checkout-success'] //strong")
    private WebElement order_num;
    @FindBy(xpath = "//h1/span")
    private WebElement thank_you;
    @FindBy(css = ".primary .action.continue")
    private WebElement continue_shopping;

    public OrderSuccessModel(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * @param driver - Webdriver Element
     *               Verifying the Order Placement
     */
    public void verifyOrderSuccess(WebDriver driver) {
        /*Setting up Extent Node*/
        ExtentReport.createNode("Verify the Order Success");

        WebdriverWait.waitTillVisibility(order_num, 15);

        /*Verifying Order Success*/
        Assert.assertTrue(driver.getCurrentUrl().equals(Property.getProperty("url")+"/checkout/onepage/success/"));
        Assert.assertTrue(thank_you.getText().equals("Thank you for your purchase!"));
        Loggers.getLogger().info("Order Placed Successfully");
        ExtentReport.getExtentNode().pass("Order Placed Successfully");

    }

    /**
     * Fetching the Order Number
     */
    public void fetchOrderNumber() {
        /*Setting up Extent Node*/
        ExtentReport.createNode("Fetch the Order Number");

        order_number = order_num.getText();
        Loggers.getLogger().info("Fetched the Order Number: "+ order_number);
        ExtentReport.getExtentNode().pass("Fetched the Order Number" + order_number);

        /*Redirect to Homepage*/
        MouseActions.moveClickEvent(continue_shopping);
    }

    /**
     * @return - Order Number
     */
    public static String getOrderNumber() {
        return order_number;
    }

}