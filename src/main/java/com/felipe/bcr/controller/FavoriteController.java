package com.felipe.bcr.controller;

import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import org.openqa.selenium.NoSuchElementException;

public class FavoriteController {
    public static boolean hasFavoriteButton(){
        try {
            Element.FAV_BUTTON_ONE.getElement();
        } catch (NoSuchElementException e) {
            try {
                Element.FAV_BUTTON_TWO.getElement();
            } catch (NoSuchElementException e2) {
                try{
                    Element.FAV_BUTTON_THREE.getElement();
                } catch (NoSuchElementException e1) {
                    return false;
                }
            }
        }

        return true;
    }

}
