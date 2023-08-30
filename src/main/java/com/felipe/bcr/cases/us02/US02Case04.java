package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Case;
import com.felipe.bcr.Main;
import com.felipe.bcr.Status;
import com.felipe.bcr.controller.LogginController;
import com.felipe.bcr.controller.ProtectionController;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/*

Caso 4:
    Para este caso tenemos un conjunto formado (Publicar un producto - Comprar un producto - Hacer preguntas) con la misma salida y/o resultado esperado por lo que se utilizará uno de estos para no realizar más pruebas redundantes.
    ID: 004
    Descripción: Validar que se pueda iniciar sesión en la página de detalles de un producto
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    2. Al estar cargada la página, hacer click en el botón “Ingresar” en la parte superior de la pantalla
    Resultados esperados: Se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión.
    Condiciones posteriores: N/A
    Resultado: Aprobado


 */
public class US02Case04 {
    public static void run(){
        Case caseToTest = new Case(
                4,
                "Validar que se pueda iniciar sesión en la página de detalles de un producto",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is not logged
        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            WebElement randomCard = Main.getDriver().findElement(By.xpath("/html/body/main/div[2]/div[2]/div/ol/li[1]/div/a"));
            String href = randomCard.getAttribute("href");
            Main.getDriver().get(href);

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            WebElement joinButton = Main.getDriver().findElement(By.xpath("//a[contains(@data-link-id, 'login')]"));
            joinButton.click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            WebElement usernameInput = Main.getDriver().findElement(By.xpath("//input[@id='user_id']"));
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
