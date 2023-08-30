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
                7,
                "Validar que en la sección de favoritos el usuario pueda entrar directamente a un producto marcado haciendo click en él",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is not logged
        if (Main.getLogginController().checkIsLoggedWithJoinButton()) {
            Main.getLogginController().runAutoLogIn("TEST", "TEST");
        }

        //prevent to run if user has no favorites
        if (Main.getFavoriteController().getFavoritesUserCount() == 0) {
            System.out.println("(Error de PRE-REQUISITO): Debes tener al menos un producto en favoritos para correr este caso de prueba (US04Case07)");
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
                System.out.println("(Error #1626): No se pudo acceder al producto desde la sección de favoritos");
                caseToTest.setStatus(Status.FAILED);
                return;
            }
        } catch (Exception e) {
            caseToTest.setStatus(Status.BLOCKED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
