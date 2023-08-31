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

ID: 008
    Descripción: Validar que se pueda iniciar sesión con nickname existente en la base de datos.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas:
    Nickname válido: “HELLOJAVAA”
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    2. En el formulario abierto escribir el “Nickname” en la sección de “Email, teléfono o usuario”
    3. Realizar el Captcha en caso de ser solicitado.
    4. Hacer click en el botón de “Continuar” para seguir el proceso.
    Resultados esperados: Se debería abrir un nuevo formulario para introducir la contraseña.
    Condiciones posteriores: N/A

 */
public class US02Case08 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                8,
                "Validar que se pueda iniciar sesión con correo electrónico existente en la base de datos",
                Status.NOT_EXECUTED
        );

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Element.JOIN_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.USERNAME_INPUT.getElement().sendKeys("HELLOJAVAA");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.CONTINUE_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            //wait for captcha to be filled
            while (CaptchaController.hasCaptcha()){
                System.out.println("Esperando acción de rellenado de captcha manual...");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Captcha rellenado correctamente");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try {
                Element.PASSWORD_INPUT.getElement();
            } catch (NoSuchElementException e) {
                caseToTest.addLog("(Error #02-08-1) No se pudo encontrar el elemento de ingreso de contraseña");
                caseToTest.setStatus(Status.FAILED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-08-2) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
