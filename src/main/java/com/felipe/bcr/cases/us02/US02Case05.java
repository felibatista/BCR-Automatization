package com.felipe.bcr.cases.us02;

import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Status;

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
    Se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión
    Dentro debería estar un formulario que permita ingresar un “Email o Nickname” y a su vez también la “Contraseña”
    Condiciones posteriores: N/A
    Resultado: Error (#01)
    #01: Solo se muestra la opción para ingresar “Email o Nickname”, pero no deja ingresar la contraseña en el mismo lugar.

 */
public class US02Case05 {
    public static void run(){
        Case caseToTest = new Case(
                5,
                "Validar que se abra una nueva pantalla solicitando los dos (2) campos obligatorios para iniciar sesión",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        //prevent to run if user is logged
        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        Element.JOIN_BUTTON.getElement().click();

        Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        try{
            Element.USERNAME_INPUT.getElement();
            Element.PASSWORD_INPUT.getElement();
        } catch (Exception e) {
            System.out.println("Error (#01): No se encontró el botón de Username o Password");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
