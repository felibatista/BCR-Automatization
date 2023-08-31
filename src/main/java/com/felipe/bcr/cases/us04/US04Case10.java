package com.felipe.bcr.cases.us04;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/*

Caso 10:
    ID: 010
    Descripción: Validar que un producto agregado a favoritos se muestre en el menú desplegable “Favoritos”, de la parte superior de la pantalla (ref. imagen-nav-fav)
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/ofertas#nav-header
    2. Estar logueado.
    El producto no debe estar en favoritos.
    Entradas: N/A
    Pasos:
    1. Hacer click en un Card de algún “producto en venta” (ref. imagen-card) para ser redirigido a la página de detalles de producto.
    2. Hacer click en el botón con forma de corazón “Favorito” (ref. imagen-fav)
    3. Hacer click en el botón de “Favoritos” de la parte superior de la pantalla (ref. imagen-nav-fav) y esperar que se despliegue el menú.
    Resultados esperados: El producto agregado a favoritos debería mostrarse en la parte superior del menú desplegable “Favoritos”, de la parte superior de la pantalla (ref. imagen-nav-fav)
    Condiciones posteriores: N/A

 */
public class US04Case10 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                10,
                "Validar que un producto agregado a favoritos se muestre en el menú desplegable “Favoritos”, de la parte superior de la pantalla (ref. imagen-nav-fav)",
                Status.NOT_EXECUTED
        );

        if (Main.getLogginController().checkIsLoggedWithJoinButton()) {
            Main.getLogginController().runAutoLogIn("TEST", "TEST");
        }

        Main.getDriver().get("https://www.mercadolibre.com.ar/ofertas#nav-header");

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            String productName = Main.getDriver().findElement(By.xpath("/html/body/main/div[2]/div[2]/div/ol/li[1]/div/a/div/div[2]/p")).getText();
            Main.getDriver().get(Element.PRODUCT_CARD_OFFER.getElement().getAttribute("href"));

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            if (!Main.getFavoriteController().hasFavoriteButton()) {
                caseToTest.addLog("(Error #04-10-1) No se encontró el botón de favoritos");
                caseToTest.setStatus(Status.BLOCKED);

                return;
            }else{
                Main.getFavoriteController().tryClickFavoriteButton();
            }

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.FAV_NAVBAR_BUTTON.getElement().click();

            //<iframe src="https://myaccount.mercadolibre.com.ar/bookmarks/list/widget" width="100%" height="100%" frameborder="0" scrolling="yes" style="height: 100%;"></iframe>
            WebElement iframe = Main.getDriver().findElement(By.xpath("/html/body/div[2]/div[2]/iframe[2]"));
            Main.getDriver().switchTo().frame(iframe);

            //Este es el primer elemento de la lista
            String recentItem = Main.getDriver().findElement(By.xpath("/html/body/main/div/ul/li[1]/div[2]/div[2]/a")).getText();

            if (!productName.equalsIgnoreCase(recentItem)) {
                caseToTest.addLog("(Error #04-10-2) El producto no se agregó a la lista desplegable de favoritos o no está primero en la lista");
                caseToTest.setStatus(Status.FAILED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #04-10-3) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
