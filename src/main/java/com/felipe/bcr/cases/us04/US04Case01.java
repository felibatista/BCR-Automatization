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

Caso 1:
    ID: 001
    Descripción: Validar que un producto de la sección “Moda” tenga la opción de ser agregado a favoritos.
    Pre-condiciones:
    Abrir: https://www.mercadolibre.com.ar/c/ropa-y-accesorios#menu=categories
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    Resultados esperados: Debería aparecer un botón con forma de corazón “Favoritos” (ref. imagen-fav)
    Condiciones posteriores: N/A

 */
public class US04Case01 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                1,
                "Validar que un producto de la sección “Moda” tenga la opción de ser agregado a favoritos",
                Status.NOT_EXECUTED
        );

        Main.getDriver().get("https://www.mercadolibre.com.ar/c/ropa-y-accesorios#menu=categories");

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            /*
            Mercado Libre muestra los productos de la sección "Zapatillas" en un carrusel, el cual el modo de acceso cambio constantemente,
            esto se podría automatizar y hacer, pero por cuestiones de tiempo, se opto por acceder a un producto de la sección "Moda"
            de forma directa con un enlace.

            ¡ATENCIÓN! El producto puede ser eliminado de la plataforma, por lo que el caso de prueba puede fallar. Para actualizar el link
            del producto, se debe acceder a la sección "Moda" y copiar el enlace del primer producto que se muestra.
             */

            String URL_PRIMER_PRODUCTO = "https://articulo.mercadolibre.com.ar/MLA-904849675-sweater-budapest-escote-en-v-sin-mangas-gris-topo-equus-_JM#position=1&search_layout=grid&type=item&tracking_id=6ab63741-670d-4eaf-928d-673b9a0d53e7&DEAL_ID=MLA28970&S=landingHubropa-y-accesorios&V=27&T=CarouselDynamic-home&L=OFERTAS-IMPERDIBLES-%F0%9F%94%A5&deal_print_id=17b37ab0-4778-11ee-8c32-c7a837b78082&c_id=carouseldynamic-home&c_element_order=undefined&c_campaign=OFERTAS-IMPERDIBLES-%F0%9F%94%A5&c_uid=17b37ab0-4778-11ee-8c32-c7a837b78082";

            Main.getDriver().get(URL_PRIMER_PRODUCTO);

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            if (!Main.getFavoriteController().hasFavoriteButton()) {
                caseToTest.addLog("(Error #04-01-1) No se encontró el botón de favoritos");
                caseToTest.setStatus(Status.FAILED);

                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #04-01-2) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
