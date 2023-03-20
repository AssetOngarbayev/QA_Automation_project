package org.qa_auto_project;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/asset/Documents/Automation QA/chromedriver/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));


        // Test Case 1: Invalid username
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

        // Test Case 3: Invalid password
        usernameInput.clear();
        passwordInput.clear();
        Thread.sleep(2000);
        // enter valid username and invalid password
        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("invalid_password");
        loginButton.click();

        // verify that error message is displayed stating "Username and password do not match any user in this service"
        errorMessage = driver.findElement(By.tagName("h3"));
        expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        actualErrorMessage = errorMessage.getText();


        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test Case 2 Passed: Error message is displayed for invalid password.");
        } else {
            System.out.println("Test Case 2 Failed: Error message is not displayed for invalid password.");
        }
        Thread.sleep(2000);


        // Test Case 3: Empty username and password
        driver.get("https://www.saucedemo.com/");
        WebElement usernameInput2 = driver.findElement(By.id("user-name"));
        WebElement passwordInput2 = driver.findElement(By.id("password"));
        WebElement loginButton2 = driver.findElement(By.id("login-button"));

        loginButton2.click();

        errorMessage = driver.findElement(By.tagName("h3"));
        expectedErrorMessage = "Epic sadface: Username is required";
        actualErrorMessage = errorMessage.getText();
        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Test Case 3 Passed: Error message is displayed for Empty username and password.");
        } else {
            System.out.println("Test Case 3 Failed: Error message is not displayed for Empty username and password.");
        }
        Thread.sleep(2000);


        // Test Case 1: Valid login credentials
        // enter valid username and password
        usernameInput2.clear();
        passwordInput2.clear();
        usernameInput2.sendKeys("standard_user");
        passwordInput2.sendKeys("secret_sauce");
        loginButton2.click();
        Thread.sleep(2000);
        // verify that user is logged in and redirected to inventory page
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        driver.manage().window().maximize();

        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Test Case 4 Passed: User is logged in and redirected to the inventory page.");
        } else {
            System.out.println("Test Case 4 Failed: User was not redirected to the inventory page.");
        }
        // close the browser
        driver.quit();
    }
}
