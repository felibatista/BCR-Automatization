package com.felipe.bcr;

import com.felipe.bcr.cases.us02.*;
import com.felipe.bcr.controller.LogginController;
import com.felipe.bcr.entitys.Case;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    private static WebDriver driver;
    private static LogginController logginController;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        logginController = new LogginController();

        System.out.println("Iniciando WebDriver...");

        driver.get("https://www.mercadolibre.com.ar/");

        String title = driver.getTitle();
        System.out.println("Chrome: " + title);

        System.out.println("Running case 9...");
        US02Case09.run();
        Case case1 = Case.getCaseByID(9);
        case1.end();
        System.out.println("Case 9 finished. Status: " + case1.getStatus() + " - Time elapsed: " + case1.getFormatTimeElapsed());
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Main.driver = driver;
    }

    public static LogginController getLogginController() {
        return logginController;
    }
}