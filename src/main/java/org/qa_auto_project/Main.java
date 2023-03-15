package org.qa_auto_project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/asset/Documents/Automation QA/chromedriver/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        System.out.println("Hello");
        driver.close();
        
    }
}