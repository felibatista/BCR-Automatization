package com.felipe.bcr.cases.us02;

import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 2:
    Para este caso tenemos un conjunto formado (Publicar un producto - Comprar un producto - Hacer preguntas) con la misma salida y/o resultado esperado por lo que se utilizará uno de estos para no realizar más pruebas redundantes.
    ID: 002
    Descripción: Validar si al intentar comprar un producto requiere autenticación.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    2. Al estar cargada la página, hacer click en el botón “Comprar ahora”
    Resultados esperados: No debería dejar realizar la compra y  se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión.
    Condiciones posteriores: N/A
 */
public class US02Case02 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                202,
                "Validar si al intentar comprar un producto requiere autenticación",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }


        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Main.getDriver().get(Element.PRODUCT_CARD_OFFER.getElement().getAttribute("href"));

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            try {
                Element.BUY_BUTTON_ONE.getElement().click();
            } catch (Exception e){
                try {
                    Element.BUY_BUTTON_TWO.getElement().click();
                } catch (Exception e2) {
                    caseToTest.addLog("(Error #02-02-0) No se pudo encontrar el botón de compra");
                    caseToTest.setStatus(Status.BLOCKED);
                    return;
                }
            }

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            try {
                Main.getDriver().findElement(By.cssSelector("#registration-link"));
            } catch (NoSuchElementException e) {
                caseToTest.addLog("(Error #02-02-1) No se pudo encontrar el elemento de registro");
                caseToTest.setStatus(Status.BLOCKED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-02-2) Hubo un error inesperado \n[" + e.getMessage() + "]");

            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
