package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Main;
import com.felipe.bcr.controller.CaptchaController;
import com.felipe.bcr.controller.LogginController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/*

Caso 10 (Positivo):
    ID: 010
    Descripción: Validar que se pueda iniciar sesión con una combinación de nickname y contraseña correctos.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas:
    Nickname válido: “FelipeBCR”
    Contraseña válida: “testcases01@”
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    2. En el formulario abierto escribir el “Nickname” en la sección de “Email, teléfono o usuario”
    3. Realizar el Captcha en caso de ser solicitado.
    4. Hacer click en el botón de “Continuar” para seguir el proceso.
    5. En la nueva pantalla, escribir la “Contraseña” en su respectiva sección.
    6. Hacer click en el botón de “Continuar” para seguir el proceso.
    7. Realizar la autenticación en dos pasos en caso de ser solicitado.
    Resultados esperados:
    1. Se debería poder ingresar correctamente al aplicativo.
    2. En la parte superior de la página, se debería mostrar el apodo correcto.
    3. La opción de iniciar sesión ya no debería aparecer.

    Condiciones posteriores: Se enviará al usuario a la página principal.

 */
public class US02Case10 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                10,
                "Validar que se pueda iniciar sesión con una combinación de nickname y contraseña correctos",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is logged
        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Main.getLogginController().runAutoLogIn("TEST", "TEST");
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            /*
            Resultados esperados:
            1. Se debería poder ingresar correctamente al aplicativo.
            2. En la parte superior de la página, se debería mostrar el apodo correcto.
            3. La opción de iniciar sesión ya no debería aparecer.
             */

            //1. Se debería poder ingresar correctamente al aplicativo.
            if (!Main.getDriver().getCurrentUrl().equals("https://www.mercadolibre.com.ar/")) {
                System.out.println("Error (#02): No se pudo ingresar correctamente al aplicativo.");
                caseToTest.setStatus(Status.FAILED);
                return;
            }

            //2. En la parte superior de la página, se debería mostrar el apodo correcto.
            try {
                Main.getDriver().findElement(By.xpath("/html/body/header/div/div[6]/nav/div/label/a/span/span[2]"));
            } catch (NoSuchElementException e) {
                System.out.println("Error (#03): No se pudo encontrar el apodo en la parte superior de la página.");
                caseToTest.setStatus(Status.FAILED);
                return;
            }

            //3. La opción de iniciar sesión ya no debería aparecer.
            try {
                Element.JOIN_BUTTON.getElement();

                System.out.println("Error (#04): Se encontró el botón de iniciar sesión.");
                caseToTest.setStatus(Status.FAILED);
            } catch (NoSuchElementException e) {
                //do nothing
            }
        } catch (Exception e) {
            e.printStackTrace();
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
