package com.felipe.bcr.cases.us04;

import com.felipe.bcr.Main;
import com.felipe.bcr.controller.FavoriteController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;

/*

Caso 2:
    ID: 002
    Descripción: Validar que un producto de la sección “Supermercado” tenga la opción de ser agregado a favoritos.
    Pre-condiciones:
    Abrir: https://www.mercadolibre.com.ar/ofertas/supermercado#menu=categories
    Entradas: N/A
    Pasos:
    1. Hacer click en la sección de “Bebidas” (ref. imagen-drinks-section)
    2. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    Resultados esperados: Debería aparecer un botón con forma de corazón “Favoritos” (ref. imagen-fav)
    Condiciones posteriores: N/A

 */
public class US04Case02 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                2,
                "Validar que un producto de la sección “Supermercado” tenga la opción de ser agregado a favoritos",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas/supermercado#menu=categories");

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            /*
            Mercado Libre muestra los productos de la sección "Bebidas" en un carrusel, el cual el modo de acceso cambio constantemente,
            esto se podría automatizar y hacer, pero por cuestiones de tiempo, se opto por acceder a un producto de la sección "Zapatillas"
            de forma directa con un enlace.

            ¡ATENCIÓN! El producto puede ser eliminado de la plataforma, por lo que el caso de prueba puede fallar. Para actualizar el link
            del producto, se debe acceder a la sección "Bebibas" y copiar el enlace del primer producto que se muestra.
             */

            String URL_PRIMER_PRODUCTO = "https://www.mercadolibre.com.ar/whisky-escoces-blended-red-label-johnnie-walker-750ml/p/MLA19788142?pdp_filters=category:MLA178700%7Cdeal:MLA3935#searchVariation=MLA19788142&position=1&search_layout=grid&type=product&tracking_id=33221d38-9fd7-4673-a5ae-966f0500f1e2";
            Main.getDriver().get(URL_PRIMER_PRODUCTO);

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            if (!Main.getFavoriteController().hasFavoriteButton()) {
                caseToTest.setStatus(Status.FAILED);

                return;
            }
        } catch (Exception e) {
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
