package com.felipe.bcr.controller;

import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import org.openqa.selenium.NoSuchElementException;

public class FavoriteController {
    public static boolean hasFavoriteButton(){
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

    public static void tryClickFavoriteButton(){
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
}
