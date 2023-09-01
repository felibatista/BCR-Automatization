package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ProtectionController {
    public static boolean hasProtection() {
        //TODO: CAMBIAR ESTO
        // #\:R5a9\:
        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        try {
            Main.getDriver().findElement(By.xpath("//*[@id=\":R5a9:\"]"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static void solveProtection() {
        // #\:R3a9\:
        Main.getDriver().findElement(By.xpath("//*[@id=\":R3a9:\"]")).click();
    }
}
