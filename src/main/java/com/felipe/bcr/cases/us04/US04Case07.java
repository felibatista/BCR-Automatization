package com.felipe.bcr.cases.us04;

import com.felipe.bcr.Main;
import com.felipe.bcr.controller.FavoriteController;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/*

Caso 7:
    ID: 007
    Descripción: Validar la opción de ver todos los productos que fueron marcados como favoritos.
    Pre-condiciones:
    1. Abrir: https://www.mercadolibre.com.ar/
    2. Estar logueado.
    3. Tener mínimo un (1) producto en favoritos
    Entradas: N/A
    Pasos:
    1. Hacer click en el botón de “Favoritos” de la parte superior de la pantalla (ref. imagen-nav-fav) y esperar que se despliegue el menú.
    2. En el menú desplegable hacer click en el botón “Ver todos los favoritos”
    Resultados esperados:
    1. Debería mostrar todos los productos que estén marcados como favoritos.
    2. Los productos deberían estar ordenados por el más reciente arriba.
    3. Cada producto debería tener su imagen, el nombre/descripción, el precio y cuándo termina la publicación.
    Condiciones posteriores: N/A

 */
public class US04Case07 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                7,
                "Validar la opción de ver todos los productos que fueron marcados como favoritos",
                Status.NOT_EXECUTED
        );

        if (Main.getLogginController().checkIsLoggedWithJoinButton()) {
            Main.getLogginController().runAutoLogIn("TEST", "TEST");
        }

        if (Main.getFavoriteController().getFavoritesUserCount() == 0) {
            System.out.println("(Error de PRE-CONDICIÓN) Se debee tener al menos un producto en favoritos para correr este caso de prueba");
            caseToTest.setStatus(Status.BLOCKED);
            return;
        }

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.FAV_NAVBAR_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Main.getDriver().get("https://myaccount.mercadolibre.com.ar/bookmarks/list");

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            /*
            1. Debería mostrar todos los productos que estén marcados como favoritos.
            2. Los productos deberían estar ordenados por el más reciente arriba.
            3. Cada producto debería tener su imagen, el nombre/descripción, el precio y cuándo termina la publicación.
             */

            //1. Debería mostrar todos los productos que estén marcados como favoritos.
            try{
                //Este es el primer elemento de la lista de favoritos
                Main.getDriver().findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/div/section/div/ul/li"));
            } catch (Exception e) {
                System.out.println("(Error #2162): No se encontró la lista de favoritos");
                caseToTest.setStatus(Status.FAILED);
                return;
            }

            //2. Los productos deberían estar ordenados por el más reciente arriba.
            //Esta validación llevaría más logica hacerla. Por lo que se deja para una futura mejora por los tiempos disponibles.

            //3. Cada producto debería tener su imagen, el nombre/descripción, el precio y cuándo termina la publicación.
            //No se puede deducir que ID o xPath tendría el elemento que contenga la información para cuando termina la publicacion.
            //Por lo que se pondrá uno que se sabe que no existe para que falle el caso de prueba.
            try{
                Main.getDriver().findElement(By.xpath("/html/body/main/div/div/div[2]/div/div/div/section/div/ul/li/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]"));
            } catch (Exception e) {
                caseToTest.addLog("(Error #04-07-1) No se encontró la información de cuando termina la publicación");
                caseToTest.setStatus(Status.FAILED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #04-07-2) Hubo un error inesperado");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
