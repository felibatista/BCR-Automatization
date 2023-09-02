package com.felipe.bcr.cases.us02;

import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;

import java.time.Duration;

/*

Caso 5:
    ID: 005
    Descripción: Validar que se abra una nueva pantalla solicitando los dos (2) campos obligatorios para iniciar sesión.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    Resultados esperados:
    1. Se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión
    2. Dentro debería estar un formulario que permita ingresar un “Email o Nickname” y a su vez también la “Contraseña”
    Condiciones posteriores: N/A
    Resultado: Error (#01)
    #01: Solo se muestra la opción para ingresar “Email o Nickname”, pero no deja ingresar la contraseña en el mismo lugar.

 */
public class US02Case05 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                205,
                "Validar que se abra una nueva pantalla solicitando los dos (2) campos obligatorios para iniciar sesión",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Element.JOIN_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            /*
            1. Se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión
            2. Dentro debería estar un formulario que permita ingresar un “Email o Nickname” y a su vez también la “Contraseña”
             */

            // 1. Se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión
            try {
                Element.USERNAME_INPUT.getElement();
            } catch (Exception e) {
                caseToTest.addLog("(Error #02-05-1) No se pudo encontrar el elemento de inicio de sesión");
                caseToTest.setStatus(Status.FAILED);
                return;
            }

            // 2. Dentro debería estar un formulario que permita ingresar un “Email o Nickname” y a su vez también la “Contraseña”
            try {
                Element.PASSWORD_INPUT.getElement();
            } catch (Exception e) {
                caseToTest.addLog("(Error #02-05-2) No se encontro el elemento para ingresar la contraseña");
                caseToTest.setStatus(Status.FAILED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-05-3) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
