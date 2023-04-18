package org.qa_auto_project;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class InventoryPage {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/asset/Documents/Automation QA/chromedriver/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement usernameInput4 = driver.findElement(By.id("user-name"));
        WebElement passwordInput4 = driver.findElement(By.id("password"));
        WebElement loginButton4 = driver.findElement(By.id("login-button"));

        usernameInput4.sendKeys("standard_user");
        passwordInput4.sendKeys("secret_sauce");
        loginButton4.click();
    }

    @Test(priority = 0)
    public void testPageLoad() {

        WebElement productsContainer = driver.findElement(By.className("inventory_container"));
        Assert.assertTrue(productsContainer.isDisplayed(), "Products container not visible");
    }

    @Test(priority = 1)
    public void testProductSorting() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        Select selectSort = new Select(sortDropdown);
        selectSort.selectByVisibleText("Price (low to high)");


        // Assuming the first product is the cheapest
        WebElement firstProduct = driver.findElement(By.className("inventory_item_name"));
        String firstProductName = firstProduct.getText();
        Assert.assertEquals(firstProductName, "Sauce Labs Onesie", "Products not sorted by price");

        WebElement sortDropdown2 = driver.findElement(By.className("product_sort_container"));
        Select selectSort2 = new Select(sortDropdown2);
        selectSort2.selectByVisibleText("Price (high to low)");
        WebElement firstProduct2 = driver.findElement(By.className("inventory_item_name"));
        String firstProductName2 = firstProduct2.getText();
        Assert.assertEquals(firstProductName2, "Sauce Labs Fleece Jacket", "Products not sorted by price");

        WebElement sortDropdown3 = driver.findElement(By.className("product_sort_container"));
        Select selectSort3 = new Select(sortDropdown3);
        selectSort3.selectByVisibleText("Name (Z to A)");
        WebElement firstProduct3 = driver.findElement(By.className("inventory_item_name"));
        String firstProductName3 = firstProduct3.getText();
        Assert.assertEquals(firstProductName3, "Test.allTheThings() T-Shirt (Red)", "Products not sorted by price");

        WebElement sortDropdown4 = driver.findElement(By.className("product_sort_container"));
        Select selectSort4 = new Select(sortDropdown4);
        selectSort4.selectByVisibleText("Name (A to Z)");
        WebElement firstProduct4 = driver.findElement(By.className("inventory_item_name"));
        String firstProductName4 = firstProduct4.getText();
        Assert.assertEquals(firstProductName4, "Sauce Labs Backpack", "Products not sorted by price");
    }

    @Test(priority = 2)
    public void testProductDetails() {
        WebElement productLink = driver.findElement(By.xpath("//div[@class='inventory_item_name'][text()='Sauce Labs Onesie']"));
        productLink.click();

        WebElement productTitle = driver.findElement(By.className("inventory_details_name"));
        Assert.assertEquals(productTitle.getText(), "Sauce Labs Onesie", "Product details not displayed");
    }

    @Test(priority = 3)
    public void testAddToCartAndRemove() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        WebElement cartItemsCount = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartItemsCount.getText(), "1", "Product not added to cart");
        WebElement removeFromCartButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeFromCartButton.click();
        int size = driver.findElements(By.className("shopping_cart_badge")).size();
        if(size==0){
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 4)
    public void menuButton() throws InterruptedException {
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();
        Thread.sleep(3000);
        WebElement itemList = driver.findElement(By.className("bm-item-list"));
        Assert.assertTrue(itemList.isDisplayed());
    }

    @Test(priority = 5)
    public void allItemButton() {
        WebElement productLink = driver.findElement(By.xpath("//div[@class='inventory_item_name'][text()='Sauce Labs Onesie']"));
        productLink.click();
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();

        WebElement allItemButton = driver.findElement(By.id("inventory_sidebar_link"));
        allItemButton.click();
        testPageLoad();
        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();
        WebElement menuButton2 = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton2.click();

        WebElement allItemButton2 = driver.findElement(By.id("inventory_sidebar_link"));
        allItemButton2.click();
        testPageLoad();
    }
    @Test(priority = 6)
    public void aboutButton() {
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();

        WebElement aboutButton = driver.findElement(By.id("about_sidebar_link"));
        aboutButton.click();
        String sauslabSite = driver.getCurrentUrl();
        Assert.assertEquals(sauslabSite, "https://saucelabs.com/");
    }
    @Test(priority = 7)
    public void resetAppStateButton() {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();

        WebElement resetAppStateButton = driver.findElement(By.id("reset_sidebar_link"));
        resetAppStateButton.click();

        int size = driver.findElements(By.className("shopping_cart_badge")).size();
        if(size==0){
            Assert.assertTrue(true);
        }
    }
    @Test(priority = 8)
    public void logoutButton() {
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
