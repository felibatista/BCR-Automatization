package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LogginController {
    //span[contains(@class, 'nav-header-avatar-user')]

    public boolean pageHasNavbar(){
        if (checkIsLoggedWithAvatar() || checkIsLoggedWithJoinButton() || checkIsLoggedWithNavbar()) {
            return true;
        }

        return false;
    }

    public boolean checkIsLoggedWithAvatar() {
        try {
            Main.getDriver().findElement(By.xpath("//span[contains(@class, 'nav-header-avatar-user')]"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean checkIsLoggedWithJoinButton() {
        try {
            Main.getDriver().findElement(By.xpath("//a[contains(@data-link-id, 'login')]"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean checkIsLoggedWithNavbar() {
        try {
            Main.getDriver().findElement(By.xpath("//div[@class='nav-header-plus-menu-wrapper nav-area nav-bottom-area nav-right-area']"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void runAutoLogIn(String username, String password) {
        System.out.println("Comenzando proceso de loggeo automático...");
        WebElement joinButton = Main.getDriver().findElement(By.xpath("//a[contains(@data-link-id, 'login')]"));
        joinButton.click();

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement usernameInput = Main.getDriver().findElement(By.xpath("//input[@id='user_id']"));
        usernameInput.sendKeys(username);

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement continueButton = Main.getDriver().findElement(By.xpath("/html/body/main/div/div[1]/div[2]/div/form/div[2]/button"));
        continueButton.click();

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        //wait for captcha to be filled
        while (CaptchaController.hasCaptcha()){
            System.out.println("Esperando acción de rellenado de captcha manual...");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Captcha rellenado correctamente");

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement passwordInput = Main.getDriver().findElement(By.xpath("/html/body/main/div/div[2]/div[2]/div/form/div[1]/div[1]/div/div[1]/input"));
        passwordInput.sendKeys(password);

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(100));

        WebElement logginButton = Main.getDriver().findElement(By.cssSelector("#action-complete"));
        logginButton.click();

        WebElement passwordInputAgain = Main.getDriver().findElement(By.xpath("/html/body/main/div/div[2]/div[2]/div/form/div[1]/div[1]/div/div[1]/input"));
        passwordInputAgain.sendKeys(password);

        //wait for captcha to be filled AGAIN
        while (CaptchaController.hasCaptcha()){
            System.out.println("Esperando acción de rellenado de captcha manual...");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Captcha rellenado correctamente");

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement qrCode = Main.getDriver().findElement(By.xpath("/html/body/main/div/div/div/div[4]/div/div[1]/div/ul/li[1]/div[1]"));
        qrCode.click();

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        while (TwoFactorController.hasTwoFactor()){
            System.out.println("Esperando acción de verficiación en dos pasos manual...");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Inicio de sesión completado correctamente");
    }
}
