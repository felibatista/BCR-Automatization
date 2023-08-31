package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Scanner;

public class FavoriteController {
    public boolean hasFavoriteButton(){
        System.out.println("Verificando si existe el botón de favoritos...");

        try {
            Element.FAV_BUTTON_ONE.getElement();
        } catch (NoSuchElementException e) {
            try {
                Element.FAV_BUTTON_TWO.getElement();
            } catch (NoSuchElementException e2) {
                try{
                    Element.FAV_BUTTON_THREE.getElement();
                } catch (NoSuchElementException e1) {
                    try{
                        Element.FAV_BUTTON_FOUR.getElement();
                    } catch (NoSuchElementException e3) {
                        System.out.println("No se encontró el botón de favoritos, depende del caso podría no existir o no estar en una posición conocida.");
                        return false;
                    }
                }
            }
        }

        System.out.println("Se encontró el botón de favoritos correctamente");

        return true;
    }

    public void tryClickFavoriteButton(){
        try {
            Element.FAV_BUTTON_ONE.getElement().click();
        } catch (NoSuchElementException e) {
            try {
                Element.FAV_BUTTON_TWO.getElement().click();
            } catch (NoSuchElementException e2) {
                try{
                    Element.FAV_BUTTON_THREE.getElement().click();
                } catch (NoSuchElementException e1) {
                    try{
                        Element.FAV_BUTTON_FOUR.getElement().click();
                    } catch (NoSuchElementException e3) {
                        System.out.println("No se encontró el botón de favoritos, depende del caso podría no existir o no estar en una posición conocida.");
                    }
                }
            }
        }
    }

    public int getFavoritesUserCount(){
        Main.getDriver().get("https://myaccount.mercadolibre.com.ar/bookmarks/list");

        WebElement favoriteListSpan = Main.getDriver().findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/div/section/div/div[1]/p"));

        String favoriteListSpanText = favoriteListSpan.getText();
        String[] numbers = favoriteListSpanText.split("[^\\d]+");
        int favoritesCount = Integer.parseInt(numbers[2]);

        System.out.println("El número de favoritos es: " + favoritesCount);

        return favoritesCount;
    }
}
