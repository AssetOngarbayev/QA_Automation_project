package org.qa_auto_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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

    @Test(priority = 0)
    public void addToCart() throws InterruptedException {
        WebElement product1 = driver.findElement(By.className("inventory_item_name"));
        String productName1 = product1.getText();
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        WebElement cartItemsCount = driver.findElement(By.className("shopping_cart_badge"));
        cartItemsCount.click();
        WebElement productInCart = driver.findElement(By.className("inventory_item_name"));
        String productName2 = productInCart.getText();
        Assert.assertEquals(productName2, productName1);

    }

}

