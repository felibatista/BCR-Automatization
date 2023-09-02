package com.felipe.bcr.cases.us02;

import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 1:
    ID: 001
    Descripción: Validar que se pueda iniciar sesión en la página principal.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    Resultados esperados: Se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión.
    Condiciones posteriores: N/A

 */
public class US02Case01 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                201,
                "Validar que se pueda iniciar sesión en la página principal",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/");

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try {
                Element.BUY_BUTTON_ONE.getElement().click();
            } catch (Exception e){
                try {
                    Element.BUY_BUTTON_TWO.getElement().click();
                } catch (Exception e2) {
                    caseToTest.addLog("(Error #02-01-0) No se pudo encontrar el botón de compra");
                    caseToTest.setStatus(Status.BLOCKED);
                    return;
                }
            }

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try{
                Element.USERNAME_INPUT.getElement();
            } catch (NoSuchElementException e) {
                caseToTest.addLog("(Error #02-01-1) No se pudo encontrar el elemento de ingreso de usuario");
                caseToTest.setStatus(Status.BLOCKED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-01-2) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
