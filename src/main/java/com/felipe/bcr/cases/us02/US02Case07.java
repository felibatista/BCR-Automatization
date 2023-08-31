package com.felipe.bcr.cases.us02;

import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.controller.CaptchaController;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 7 (Negativo):
    ID: 007
    Descripción: Validar que no se puede iniciar sesión con correo electrónico inexistente en la base de datos.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas:
    Email inválido: “estonofuncionara@gmail.com”
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    2. En el formulario abierto escribir el “Email” en la sección de “Email, teléfono o usuario”
    3. Realizar el Captcha en caso de ser solicitado.
    4. Hacer click en el botón de “Continuar” para seguir el proceso.

    Resultados esperados: Debería aparecer un mensaje con un error indicando que el email no existe en la base de datos.
    Condiciones posteriores: N/A

 */
public class US02Case07 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                7,
                "Validar que no se puede iniciar sesión con correo electrónico inexistente en la base de datos",
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

            Element.USERNAME_INPUT.getElement().sendKeys("estonofuncionara@gmail.com");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.CONTINUE_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            //wait for captcha to be filled
            while (CaptchaController.hasCaptcha()){
                System.out.println("Esperando acción de rellenado de captcha manual...");

                try {
                    if (Main.getDriver().findElement(By.xpath("/html/body/main/div/div[1]/div[2]/div/form/div[1]/div[1]/div/div/span[2]/div/div")).isDisplayed()) {
                        break;
                    }
                } catch (NoSuchElementException e) {
                    //do nothing
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-07-1) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
