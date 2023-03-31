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


public class InventoryPage {
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
    public void testPageLoad() throws InterruptedException {

        WebElement productsContainer = driver.findElement(By.className("inventory_container"));
        Assert.assertTrue(productsContainer.isDisplayed(), "Products container not visible");
    }

    @Test(priority = 1)
    public void testProductSorting() throws InterruptedException {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        Select selectSort = new Select(sortDropdown);
        selectSort.selectByVisibleText("Price (low to high)");
        Thread.sleep(2000);

        // Assuming the first product is the cheapest
        WebElement firstProduct = driver.findElement(By.className("inventory_item_name"));
        String firstProductName = firstProduct.getText();
        Assert.assertEquals(firstProductName, "Sauce Labs Onesie", "Products not sorted by price");

    }

    @Test(priority = 2)
    public void testProductDetails() {
        WebElement productLink = driver.findElement(By.xpath("//div[@class='inventory_item_name'][text()='Sauce Labs Onesie']"));
        productLink.click();

        WebElement productTitle = driver.findElement(By.className("inventory_details_name"));
        Assert.assertEquals(productTitle.getText(), "Sauce Labs Onesie", "Product details not displayed");
    }

    @Test(priority = 3)
    public void testAddToCartAndRemove() throws InterruptedException {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        Thread.sleep(1000);
        WebElement cartItemsCount = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartItemsCount.getText(), "1", "Product not added to cart");
        WebElement removeFromCartButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeFromCartButton.click();
        Thread.sleep(1000);
        int size = driver.findElements(By.className("shopping_cart_badge")).size();
        if(size==0){
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 4)
    public void menuButton() throws InterruptedException {
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();
        WebElement itemList = driver.findElement(By.className("bm-item-list"));
        Thread.sleep(2000);
        Assert.assertTrue(itemList.isDisplayed());
    }

    @Test(priority = 5)
    public void allItemButton() throws InterruptedException {
        WebElement productLink = driver.findElement(By.xpath("//div[@class='inventory_item_name'][text()='Sauce Labs Onesie']"));
        productLink.click();
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();
        Thread.sleep(2000);
        WebElement allItemButton = driver.findElement(By.id("inventory_sidebar_link"));
        allItemButton.click();
        testPageLoad();
        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();
        WebElement menuButton2 = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton2.click();
        Thread.sleep(2000);
        WebElement allItemButton2 = driver.findElement(By.id("inventory_sidebar_link"));
        allItemButton2.click();
        testPageLoad();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
