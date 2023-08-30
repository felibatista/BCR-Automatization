package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import org.openqa.selenium.By;

import java.time.Duration;

public class CaptchaController {
    //*[@id="login_user_form"]/div[1]/div[2]/span[1]

    public static boolean hasCaptcha() {
        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        try {
            Main.getDriver().findElement(By.xpath("//*[@id=\"login_user_form\"]/div[1]/div[2]/span[1]"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
