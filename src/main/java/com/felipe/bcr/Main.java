package com.felipe.bcr;

import com.felipe.bcr.cases.us02.Case1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    private static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        System.out.println("Iniciando WebDriver...");

        driver.get("https://www.mercadolibre.com.ar/");

        String title = driver.getTitle();
        System.out.println("Chrome: " + title);

        System.out.println("Running case 1...");
        Case1.run();
        Case case1 = Case.getCaseByID(1);
        System.out.println("Case 1 finished. Status: " + case1.getStatus());

    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Main.driver = driver;
    }
}