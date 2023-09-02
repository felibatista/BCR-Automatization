package com.felipe.bcr;

import com.felipe.bcr.controller.ConsoleController;
import com.felipe.bcr.controller.FavoriteController;
import com.felipe.bcr.controller.LoginController;
import com.felipe.bcr.entitys.Case;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    private static WebDriver driver;
    private static LoginController loginController;
    private static FavoriteController favoriteController;
    private static ConsoleController consoleController;

    private static String email;
    private static String password;

    public static void main(String[] args) {
        System.out.println("Iniciando controladores...");

        driver = new ChromeDriver();
        loginController = new LoginController();
        favoriteController = new FavoriteController();
        consoleController = new ConsoleController();

        email = "";
        password = "";

        Case.generateHashMaps();

        System.out.println("Iniciando WebDriver...");

        driver.get("https://www.mercadolibre.com.ar/");

        String title = driver.getTitle();
        System.out.println("Chrome: " + title);

        getConsoleController().startConsoleMenu();

        /*
        System.out.println("Running case 9...");
        US04Case09.run();
        Case case1 = Case.getCaseByUserStoryAndID(UserStory.US04, 9);
        case1.end();
        System.out.println("Case 9 finished.");
        System.out.println(case1.getLogsAsString());

         */

        //driver.close();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static LoginController getLogginController() {
        return loginController;
    }

    public static FavoriteController getFavoriteController() {
        return favoriteController;
    }

    public static ConsoleController getConsoleController() {
        return consoleController;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setEmail(String email) {
        Main.email = email;
    }

    public static void setPassword(String password) {
        Main.password = password;
    }
}