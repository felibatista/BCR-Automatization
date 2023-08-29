package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import org.openqa.selenium.By;

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
}
