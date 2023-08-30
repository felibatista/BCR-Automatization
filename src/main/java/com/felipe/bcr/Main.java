package com.felipe.bcr;

import com.felipe.bcr.cases.us02.*;
import com.felipe.bcr.controller.LogginController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    private static WebDriver driver;
    private static LogginController logginController;

    public static void main(String[] args) {
        System.out.println("Iniciando controladores...");

        driver = new ChromeDriver();
        logginController = new LogginController();
        Case.generateHashMaps();

        System.out.println("Iniciando WebDriver...");

        driver.get("https://www.mercadolibre.com.ar/");

        String title = driver.getTitle();
        System.out.println("Chrome: " + title);

        System.out.println("Running case 11...");
        US02Case11.run();
        Case case1 = Case.getCaseByUserStoryAndID(UserStory.US02, 11);
        case1.end();
        System.out.println("Case 11 finished. Status: " + case1.getStatus() + " - Time elapsed: " + case1.getFormatTimeElapsed());

        driver.close();
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