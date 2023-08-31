package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Main;
import com.felipe.bcr.controller.CaptchaController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 12:
    ID: 012
    Descripción: Validar que se muestre un mensaje de error al no ingresar ningún valor en el input del Email.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    2. Hacer click en el botón de “Iniciar sesión” o “Continuar” para completar el inicio.
    Resultados esperados: Se debería mostrar un mensaje de error, pidiendo que introduzca algún valor.
    Condiciones posteriores: N/A

 */

public class US02Case12 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                12,
                "Validar que se muestre un mensaje de error al no ingresar ningún valor en el input del Email",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/");

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Element.JOIN_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.USERNAME_INPUT.getElement().sendKeys(" ");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try {
                Element.CONTINUE_BUTTON.getElement().click();
            } catch (Exception e) {
                caseToTest.addLog("(Error #02-12-1) No se pudo hacer click en el botón para continuar");
                caseToTest.setStatus(Status.FAILED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-12-2) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
