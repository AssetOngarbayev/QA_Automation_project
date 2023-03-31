package org.qa_auto_project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/asset/Documents/Automation QA/chromedriver/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Test Case 0 Passed: Title is correct.");
        } else {
            System.out.println("Test Case 0 Failed: Title not correct.");
        }
    }

    @Test(priority = 0)
    public void invalidUsername() throws InterruptedException {
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        // enter invalid username
        usernameInput.sendKeys("invalid_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();
        // verify that error message is displayed stating "Username and password do not match any user in this service"
        WebElement errorMessage = driver.findElement(By.tagName("h3"));
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test Case 1 Passed: Error message is displayed for invalid username.");
        } else {
            System.out.println("Test Case 1 Failed: Error message is not displayed for invalid username.");
        }
    }
    @Test(priority = 1)
    public void invalidPassword() throws InterruptedException {
        driver.navigate().refresh();
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        // enter invalid password
        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("invalid_password");
        loginButton.click();
        // verify that error message is displayed stating "Username and password do not match any user in this service"
        WebElement errorMessage = driver.findElement(By.tagName("h3"));
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test Case 2 Passed: Error message is displayed for invalid password.");
        } else {
            System.out.println("Test Case 2 Failed: Error message is not displayed for invalid password.");
        }
    }
    @Test(priority = 2)
    public void emptyUsernameAndPassword() throws InterruptedException {
        driver.navigate().refresh();
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement errorMessage = driver.findElement(By.tagName("h3"));
        String expectedErrorMessage = "Epic sadface: Username is required";
        String actualErrorMessage = errorMessage.getText();
        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test Case 3 Passed: Error message is displayed for Empty username and password.");
        } else {
            System.out.println("Test Case 3 Failed: Error message is not displayed for Empty username and password.");
        }
    }
    @Test(priority = 3)
    public void emptyUsername() throws InterruptedException {
        driver.navigate().refresh();
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();
        WebElement errorMessage = driver.findElement(By.tagName("h3"));
        String expectedErrorMessage = "Epic sadface: Username is required";
        String actualErrorMessage = errorMessage.getText();
        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test Case 4 Passed: Error message is displayed for Empty username.");
        } else {
            System.out.println("Test Case 4 Failed: Error message is not displayed for Empty username.");
        }
    }

    @Test(priority = 4)
    public void emptyPassword() throws InterruptedException {
        driver.navigate().refresh();
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        usernameInput.sendKeys("standard_user");
        loginButton.click();
        WebElement errorMessage = driver.findElement(By.tagName("h3"));
        String expectedErrorMessage = "Epic sadface: Password is required";
        String actualErrorMessage = errorMessage.getText();
        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test Case 5 Passed: Error message is displayed for Empty password.");
        } else {
            System.out.println("Test Case 5 Failed: Error message is not displayed for Empty password.");
        }
    }

    @Test(priority = 5)
    public void lockedUser() throws InterruptedException {
        driver.navigate().refresh();
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        usernameInput.sendKeys("locked_out_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();
        WebElement errorMessage = driver.findElement(By.tagName("h3"));
        String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        String actualErrorMessage = errorMessage.getText();
        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test Case 6 Passed: Error message is displayed for locked-out user.");
        } else {
            System.out.println("Test Case 6 Failed: Error message is not displayed for locked-out user.");
        }
    }

    @Test(priority = 6)
    public void validLoginCredentials() throws InterruptedException {
        driver.navigate().refresh();
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Test Case 7 Passed: User is logged in and redirected to the inventory page.");
        } else {
            System.out.println("Test Case 7 Failed: User was not redirected to the inventory page.");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

