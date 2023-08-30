package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TwoFactorController {
    public static boolean hasTwoFactor() {
        try {
            Main.getDriver().findElement(By.xpath("/html/body/main/div/div/div/div[2]/div/div[2]/button"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
