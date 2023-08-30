package com.felipe.bcr.cases.us04;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 1:
    ID: 001
    Descripción: Validar que un producto de la sección “Moda” tenga la opción de ser agregado a favoritos.
    Pre-condiciones:
    Abrir: https://www.mercadolibre.com.ar/c/ropa-y-accesorios#menu=categories
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    Resultados esperados: Debería aparecer un botón con forma de corazón “Favoritos” (ref. imagen-fav)
    Condiciones posteriores: N/A

 */
public class US04Case01 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                1,
                "Validar que un producto de la sección “Moda” tenga la opción de ser agregado a favoritos",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/c/ropa-y-accesorios#menu=categories");

        //prevent to run if user is logged
        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.BUY_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.USERNAME_INPUT.getElement();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            caseToTest.setStatus(Status.BLOCKED);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
