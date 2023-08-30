package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Main;
import com.felipe.bcr.controller.CaptchaController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/*

Caso 11 (Negativo):
    ID: 011
    Descripción: Validar que no se puede iniciar sesión con una combinación de email y contraseña inválidos.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas:
    Email válido: “felipebcr@gmail.com”
    Contraseña inválida: “abscjsaedf”
    Pasos:
    1. Hacer click en el botón “Ingresar” de la parte superior de la pantalla.
    2. En el formulario abierto escribir el “Nickname” en la sección de “Email, teléfono o usuario”
    3. Realizar el Captcha en caso de ser solicitado.
    4. Hacer click en el botón de “Continuar” para seguir el proceso.
    5. En la nueva pantalla, escribir la “Contraseña” en su respectiva sección.
    6. Hacer click en el botón de “Continuar” para seguir el proceso.
    Resultados esperados: Debería aparecer un mensaje con un error indicando que la contraseña es incorrecta.
    Condiciones posteriores: N/A

 */
public class US02Case11 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                11,
                "Validar que no se puede iniciar sesión con una combinación de email y contraseña inválidos",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is logged
        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Element.JOIN_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            Element.USERNAME_INPUT.getElement().sendKeys("hellojavaa@gmail.com");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            Element.CONTINUE_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

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

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            Element.PASSWORD_INPUT.getElement().sendKeys("abscjsaedf");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            WebElement logginButton = Main.getDriver().findElement(By.cssSelector("#action-complete"));
            logginButton.click();

            Element.PASSWORD_INPUT.getElement().sendKeys("abscjsaedf");

            //wait for captcha to be filled AGAIN
            while (CaptchaController.hasCaptcha()){
                System.out.println("Esperando acción de rellenado de captcha manual...");

                //1. Se debería mostrar un mensaje de error indicando que la contraseña es incorrecta.
                try {
                    if (Main.getDriver().findElement(By.xpath("/html/body/main/div/div[2]/div[2]/div/form/div[1]/div/div/div[2]/span[2]/span/div/div")).isDisplayed()) {
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
            e.printStackTrace();
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
