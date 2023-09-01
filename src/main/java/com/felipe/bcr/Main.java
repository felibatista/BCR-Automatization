package com.felipe.bcr;

import com.felipe.bcr.cases.us02.*;
import com.felipe.bcr.cases.us04.*;
import com.felipe.bcr.controller.FavoriteController;
import com.felipe.bcr.controller.LogginController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Main {
    private static WebDriver driver;
    private static LogginController logginController;
    private static FavoriteController favoriteController;

    public static void main(String[] args) {
        System.out.println("Iniciando controladores...");

        driver = new ChromeDriver();
        logginController = new LogginController();
        favoriteController = new FavoriteController();

        Case.generateHashMaps();

        System.out.println("Iniciando WebDriver...");

        driver.get("https://www.mercadolibre.com.ar/");

        String title = driver.getTitle();
        System.out.println("Chrome: " + title);

        System.out.println("Running case 9...");
        US04Case09.run();
        Case case1 = Case.getCaseByUserStoryAndID(UserStory.US04, 9);
        case1.end();
        System.out.println("Case 9 finished.");
        System.out.println(case1.getLogsAsString());

        //driver.close();
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

    public static FavoriteController getFavoriteController() {
        return favoriteController;
    }

    public void startConsoleMenu(){
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option!=4){
            try {
                option = scanner.nextInt();
                switch (option){
                    case 1: option1(); break;
                    case 4: exit(0);
                }
            }
            catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and 4");
                scanner.next();
            }
        }
    }

    public void option1(){
        System.out.println("Option 1 selected");
    }

    public void exit(int status){
        System.out.println("Exiting...");
        System.exit(status);
    }
}