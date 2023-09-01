package com.felipe.bcr.cases.us04;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;

import java.time.Duration;

/*

Caso 8:
    ID: 008
    Descripción: Validar que en la sección de favoritos el usuario pueda entrar directamente a un producto marcado haciendo click en él.
    Pre-condiciones:
    1. Estar logueado.
    2. Tener mínimo un (1) producto en favoritos.
    3. Abrir: https://myaccount.mercadolibre.com.ar/bookmarks/list
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card)
    Resultados esperados: Debería ser redirigido a la página de detalles del producto elegido.
    Condiciones posteriores: N/A

 */
public class US04Case08 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                8,
                "Validar que en la sección de favoritos el usuario pueda entrar directamente a un producto marcado haciendo click en él",
                Status.NOT_EXECUTED
        );

        if (Main.getLogginController().checkIsLoggedWithJoinButton()) {
            Main.getLogginController().runAutoLogIn(Main.getEmail(), Main.getPassword());
        }

        if (Main.getFavoriteController().getFavoritesUserCount() == 0) {
            System.out.println("(Error de PRE-CONDICIÓN): Se debe tener al menos un producto en favoritos para correr este caso de prueba");
            caseToTest.setStatus(Status.BLOCKED);
            return;
        }

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.FAV_NAVBAR_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Main.getDriver().get("https://myaccount.mercadolibre.com.ar/bookmarks/list");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            try {
                Main.getDriver().findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/div/section/div/ul/li/div/div[3]/a")).click();
            } catch (Exception e) {
                caseToTest.addLog("(Error #04-08-1) No se pudo realizar el click en el producto de favoritos");
                caseToTest.setStatus(Status.FAILED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #04-08-2) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
