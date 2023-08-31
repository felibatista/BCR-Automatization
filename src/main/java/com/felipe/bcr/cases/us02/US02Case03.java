package com.felipe.bcr.cases.us02;

import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.controller.ProtectionController;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 3:
    Para este caso tenemos un conjunto formado (Publicar un producto - Comprar un producto - Hacer preguntas) con la misma salida y/o resultado esperado por lo que se utilizará uno de estos para no realizar más pruebas redundantes.
    ID: 003
    Descripción: Validar si al intentar comprar un producto ya no es necesario volver a hacer la autenticación.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    1. Estar logueado.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    2. Al estar cargada la página, hacer click en el botón “Comprar ahora”
    Resultados esperados: Se debería abrir una pantalla para completar el pago y otras cuestiones de envío.
    Condiciones posteriores: N/A
    Resultado: Aprobado

 */
public class US02Case03 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US02,
                3,
                "Validar si al intentar comprar un producto ya no es necesario volver a hacer la autenticación",
                Status.NOT_EXECUTED
        );

        if (Main.getLogginController().checkIsLoggedWithJoinButton()) {
            Main.getLogginController().runAutoLogIn("TEST", "TEST");
        }

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Main.getDriver().get(Element.PRODUCT_CARD_OFFER.getElement().getAttribute("href"));

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.BUY_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            if (ProtectionController.hasProtection()){
                ProtectionController.solveProtection();
            }

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try{
                Main.getDriver().findElement(By.cssSelector("#registration-link"));

                caseToTest.addLog("(Error #02-03-1) Se abrió la página de logueo");
            } catch (NoSuchElementException e) {
                caseToTest.setStatus(Status.PASSED);
                return;
            }

            caseToTest.setStatus(Status.FAILED);
        } catch (Exception e) {
            caseToTest.addLog("(Error #02-03-2) Hubo un error inesperado");

            caseToTest.setStatus(Status.FAILED);
        }
    }
}
