package com.felipe.bcr.cases.us01;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 1:
    ID: 102
    Descripción: Validar que al tocar el botón de registro se abra una nueva ventana para rellenar los datos.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    Hacer click en el botón “Creá tu cuenta” de la parte superior de la pantalla (ref. imagen-register)
    Resultados esperados: Debería abrirse una nueva ventana para rellenar los datos (ref. imagen-mockup).
    Condiciones posteriores: N/A

 */
public class US01Case02 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US01,
                102,
                "Validar que al tocar el botón de registro se abra una nueva ventana para rellenar los datos",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/");

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            try{
                Element.REGISTER_BUTTON.getElement().click();

                try {
                    Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

                    //al no saber como podría estar hecho el formulario en la versión antigua de la página, asumiremos el siguiente sabiendo que dará error
                    try {
                        Main.getDriver().findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/d"));
                    } catch (NoSuchElementException e) {
                        caseToTest.addLog("(Error #01-02-1) No se pudo encontrar el formulario para introducir los datos");
                        caseToTest.setStatus(Status.FAILED);
                        return;
                    }
                } catch (NoSuchElementException e) {
                    caseToTest.addLog("(Error #01-02-2) No se pudo encontrar el elemento de registro de usuario");
                    caseToTest.setStatus(Status.BLOCKED);
                    return;
                }
            } catch (NoSuchElementException e) {
                caseToTest.addLog("(Error #01-02-3) No se pudo encontrar el elemento de registro de usuario");
                caseToTest.setStatus(Status.BLOCKED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #01-02-4) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
