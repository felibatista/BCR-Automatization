package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 10 (Positivo):
    ID: 010
    Descripción: Validar que se pueda iniciar sesión con una combinación de nickname y contraseña correctos.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas:
    Nickname válido:
    Contraseña válida:
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
                210,
                "Validar que se pueda iniciar sesión con una combinación de nickname y contraseña correctos",
                Status.NOT_EXECUTED
        );

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error en PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Main.getLogginController().runAutoLogIn(Main.getEmail(), Main.getPassword());
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            /*
            Resultados esperados:
            1. Se debería poder ingresar correctamente al aplicativo.
            2. En la parte superior de la página, se debería mostrar el apodo correcto.
            3. La opción de iniciar sesión ya no debería aparecer.
             */

            //1. Se debería poder ingresar correctamente al aplicativo.
            if (!Main.getDriver().getCurrentUrl().equals("https://www.mercadolibre.com.ar/")) {
                caseToTest.addLog("(Error #02-10-1) No se pudo ingresar correctamente al aplicativo");

                caseToTest.setStatus(Status.FAILED);
                return;
            }

            //2. En la parte superior de la página, se debería mostrar el apodo correcto.
            try {
                Main.getDriver().findElement(By.xpath("/html/body/header/div/div[6]/nav/div/label/a/span/span[2]"));
            } catch (NoSuchElementException e) {
                caseToTest.addLog("(Error #02-10-2) No se pudo encontrar el apodo en la parte superior de la página.");
                caseToTest.setStatus(Status.FAILED);
                return;
            }

            //3. La opción de iniciar sesión ya no debería aparecer.
            try {
                Element.JOIN_BUTTON.getElement();

                caseToTest.addLog("(Error #02-10-3) La opción de iniciar sesión sigue apareciendo");
                caseToTest.setStatus(Status.FAILED);
            } catch (NoSuchElementException e) {
                //do nothing
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-08-4) Hubo un error inesperado \n[" + e.getMessage() + "]");

            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
