package com.felipe.bcr.cases.us02;

import com.felipe.bcr.Case;
import com.felipe.bcr.Main;
import com.felipe.bcr.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/*

Caso 2:
    Para este caso tenemos un conjunto formado (Publicar un producto - Comprar un producto - Hacer preguntas) con la misma salida y/o resultado esperado por lo que se utilizará uno de estos para no realizar más pruebas redundantes.
    ID: 002
    Descripción: Validar si al intentar comprar un producto requiere autenticación.
    Pre-condiciones:
    Abrir: https://www.mercadolibre.com.ar/
    No estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    2. Al estar cargada la página, hacer click en el botón “Comprar ahora”
    Resultados esperados: No debería dejar realizar la compra y  se debería abrir una nueva pantalla o pop-up con la opción para iniciar sesión.
    Condiciones posteriores: N/A
 */
public class US02Case02 {
    public static void run(){
        Case caseToTest = new Case(
                2,
                "Validar si al intentar comprar un producto requiere autenticación",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is logged
        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            WebElement randomCard = Main.getDriver().findElement(By.xpath("//*[@id=\"root-app\"]/div/div/section[2]/section/div/section/div[2]/div/div/div[1]/div/div[2]/div[1]/p/a"));
            //*[@id="root-app"]/div/div/section[2]/section/div/section/div[2]/div/div/div[1]/div/div[2]/div[1]/p/a
            String href = randomCard.getAttribute("href");
            Main.getDriver().get(href);

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement buyButton = Main.getDriver().findElement(By.xpath("//*[@id=\":Rr9ahil7k:\"]"));
        } catch (NoSuchElementException e) {
            caseToTest.setStatus(Status.BLOCKED);
            return;
        } catch (Exception e) {
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
