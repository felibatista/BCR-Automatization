package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Case;
import com.felipe.bcr.Main;
import com.felipe.bcr.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

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
                1,
                "Validar que se pueda iniciar sesión en la página principal",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is logged
        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            WebElement joinButton = Main.getDriver().findElement(By.xpath("//a[contains(@data-link-id, 'login')]"));
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
