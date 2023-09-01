package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

        Element.JOIN_BUTTON.getElement().click();

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        Element.USERNAME_INPUT.getElement().sendKeys(username);

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        try {
            Element.CONTINUE_BUTTON.getElement().click();
        } catch (NoSuchElementException e) {
            System.out.println("No se encontró el botón de continuar, reescribiendo el usuario...");

            Element.USERNAME_INPUT.getElement().sendKeys(Keys.CONTROL + "a");
            Element.USERNAME_INPUT.getElement().sendKeys(Keys.DELETE);

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.USERNAME_INPUT.getElement().sendKeys(username);

            return;
        }

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

        Element.PASSWORD_INPUT.getElement().sendKeys(password);

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement logginButton = Main.getDriver().findElement(By.cssSelector("#action-complete"));
        logginButton.click();

        Element.PASSWORD_INPUT.getElement().sendKeys(password);

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
