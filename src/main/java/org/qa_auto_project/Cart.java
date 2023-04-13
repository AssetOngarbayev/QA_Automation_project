package org.qa_auto_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Cart {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/asset/Documents/Automation QA/chromedriver/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
        WebElement usernameInput4 = driver.findElement(By.id("user-name"));
        WebElement passwordInput4 = driver.findElement(By.id("password"));
        WebElement loginButton4 = driver.findElement(By.id("login-button"));

        usernameInput4.sendKeys("standard_user");
        passwordInput4.sendKeys("secret_sauce");
        loginButton4.click();
    }

    @Test(priority = 1)
    public void addToCartAndRemove() throws InterruptedException {
        WebElement product1 = driver.findElement(By.className("inventory_item_name"));
        String productName1 = product1.getText();
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        WebElement cartItemsCount = driver.findElement(By.className("shopping_cart_badge"));
        cartItemsCount.click();
        WebElement productInCart = driver.findElement(By.className("inventory_item_name"));
        String productName2 = productInCart.getText();
        Assert.assertEquals(productName2, productName1);
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeButton.click();
        int productInCart2 = driver.findElements(By.className("inventory_item_name")).size();
        Assert.assertTrue(productInCart2==0);
    }
    @Test(priority = 2)
    public void continueShopping() throws InterruptedException {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement cartItemsCount = driver.findElement(By.className("shopping_cart_link"));
        cartItemsCount.click();
        WebElement continueShopping = driver.findElement(By.id("continue-shopping"));
        continueShopping.click();
        String inventoryPage = driver.getCurrentUrl();
        Assert.assertEquals(inventoryPage, "https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 3)
    public void checkoutPage() throws InterruptedException {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        WebElement cartItemsCount = driver.findElement(By.className("shopping_cart_badge"));
        cartItemsCount.click();
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
        String checkoutPage = driver.getCurrentUrl();
        Assert.assertEquals(checkoutPage, "https://www.saucedemo.com/checkout-step-one.html");
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement zipCodeField = driver.findElement(By.id("postal-code"));
        Assert.assertTrue (firstNameField.isDisplayed() && lastNameField.isDisplayed() && zipCodeField.isDisplayed());
        WebElement continueButton = driver.findElement(By.xpath("//input[@type='submit']"));
        Assert.assertTrue(continueButton.isDisplayed());
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        Assert.assertTrue(cancelButton.isDisplayed());

    }

    @Test(priority = 4)
    public void cancelCheckout() throws InterruptedException {
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        cancelButton.click();
        String cartPage = driver.getCurrentUrl();
        Assert.assertEquals(cartPage,"https://www.saucedemo.com/cart.html");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

