package com.felipe.bcr.cases.us01;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 1:
    ID: 101
    Descripción: Validar que el botón “Registrar” se encuentre en la página de detalles de un producto.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/ofertas#nav-header
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    Resultados esperados: Debería encontrarse un botón en la parte superior que diga “Crea tu cuenta” (ref. imagen-register)
    Condiciones posteriores: N/A

 */
public class US01Case01 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US01,
                101,
                "Validar que el botón “Registrar” se encuentre en la página de detalles de un producto",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/");

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Main.getDriver().get(Element.PRODUCT_CARD_OFFER.getElement().getAttribute("href"));

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try{
                Element.REGISTER_BUTTON.getElement();
            } catch (NoSuchElementException e) {
                caseToTest.addLog("(Error #01-01-1) No se pudo encontrar el elemento de registro de usuario");
                caseToTest.setStatus(Status.BLOCKED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #01-01-2) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
