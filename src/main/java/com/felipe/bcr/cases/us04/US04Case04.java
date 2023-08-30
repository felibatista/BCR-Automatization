package com.felipe.bcr.cases.us04;

import com.felipe.bcr.Main;
import com.felipe.bcr.controller.FavoriteController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;

import java.time.Duration;

/*

Caso 4:
    ID: 004
    Descripción: Validar que un producto se pueda agregar a favoritos si el usuario está logueado.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/ofertas#nav-header
    2. Estar logueado.
    El producto no debe estar en favoritos.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    2. Hacer click en el botón con forma de corazón “Favorito” (ref. imagen-fav)
    Resultados esperados: El producto debe ser agregado correctamente a favoritos.
    Condiciones posteriores: N/A

 */
public class US04Case04 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                4,
                "Validar que un producto se pueda agregar a favoritos si el usuario está logueado",
                Status.NOT_EXECUTED
        );

        //prevent to run if user is not logged
        if (Main.getLogginController().checkIsLoggedWithJoinButton()) {
            Main.getLogginController().runAutoLogIn("TEST", "TEST");
        }

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Main.getDriver().get(Element.PRODUCT_CARD_OFFER.getElement().getAttribute("href"));

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            if (!FavoriteController.hasFavoriteButton()) {
                caseToTest.setStatus(Status.BLOCKED);

                return;
            }else{
                FavoriteController.tryClickFavoriteButton();
            }
        } catch (Exception e) {
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
