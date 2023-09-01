package com.felipe.bcr.cases.us04;

import com.felipe.bcr.Main;
import com.felipe.bcr.controller.FavoriteController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;

import java.time.Duration;

/*

Caso 5:
    ID: 005
    Descripción: Validar que un producto no se pueda agregar a favoritos si el usuario no está logueado.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/ofertas#nav-header
    2. No estar logueado.
    Entradas: N/A
    Pasos:
    Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    Hacer click en el botón con forma de corazón “Favorito” (ref. imagen-fav)
    Resultados esperados: Se debería abrir un menú o pantalla para el inicio de sesión.
    Condiciones posteriores: N/A

 */
public class US04Case05 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                5,
                "Validar que un producto no se pueda agregar a favoritos si el usuario no está logueado",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        if (!Main.getLogginController().checkIsLoggedWithJoinButton()) {
            caseToTest.addLog("(Error de PRE-CONDICIÓN) El usuario está logueado");
            caseToTest.setStatus(Status.PRE_CONDITION_FAILED);
            return;
        }

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Main.getDriver().get(Element.PRODUCT_CARD_OFFER.getElement().getAttribute("href"));

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            if (!Main.getFavoriteController().hasFavoriteButton()) {
                caseToTest.addLog("(Error #04-05-1) No se encontró el botón de favoritos");
                caseToTest.setStatus(Status.BLOCKED);

                return;
            }else{
                Main.getFavoriteController().tryClickFavoriteButton();

                Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(500));

                try {
                    Main.getDriver().findElement(By.cssSelector("#registration-link"));
                } catch (Exception e) {
                    caseToTest.addLog("(Error #04-05-2) No se encontró el formulario para iniciar sesión");
                    caseToTest.setStatus(Status.FAILED);

                    return;
                }
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #04-05-3) Hubo un error inesperado \n[" + e.getMessage() + "]");

            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
