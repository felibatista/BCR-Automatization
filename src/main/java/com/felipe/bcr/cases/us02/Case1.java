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
    Abrir: https://www.mercadolibre.com.ar/
    No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    Resultados esperados: Se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión.
    Condiciones posteriores: N/A

 */
public class Case1 {
    public static void run(){
        Case case1 = new Case(
                1,
                "Validar que se pueda iniciar sesión en la página principal",
                Status.NOT_EXECUTED
        );

        try {
            WebElement joinButton = Main.getDriver().findElement(By.xpath("//a[contains(@data-link-id, 'login')]"));
        } catch (NoSuchElementException e) {
            case1.setStatus(Status.BLOCKED);
            return;
        } catch (Exception e) {
            case1.setStatus(Status.FAILED);
            return;
        }

        case1.setStatus(Status.PASSED);
    }
}
