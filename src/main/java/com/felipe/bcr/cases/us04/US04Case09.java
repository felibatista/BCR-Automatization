package com.felipe.bcr.cases.us04;

import com.felipe.bcr.Main;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.Element;
import com.felipe.bcr.entitys.Status;
import com.felipe.bcr.entitys.UserStory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/*

Caso 9:
    ID: 009
    Descripción: Validar la cantidad de productos en la lista desplegable de la sección de favoritos de la parte superior de la pantalla (ref. imagen-nav-fav).
    Pre-condiciones:
    1. Estar logueado.
    2. Abrir: https://www.mercadolibre.com.ar/
    3. Tener mínimo quince (15) productos en favoritos.
    Entradas: N/A
    Pasos:
    1. Hacer click en el botón de “Favoritos” de la parte superior de la pantalla (ref. imagen-nav-fav) y esperar que se despliegue el menú.
    2. Bajar hasta el final el scroll del menú abierto para llegar al último producto.
    Resultados esperados: Los productos deben mostrarse de a 4 a la vez.
    Condiciones posteriores: N/A

 */
public class US04Case09 {
    public static void run(){
        Case caseToTest = new Case(
                UserStory.US04,
                9,
                "Validar la cantidad de productos en la lista desplegable de la sección de favoritos de la parte superior de la pantalla",
                Status.NOT_EXECUTED
        );

        if (Main.getLogginController().checkIsLoggedWithJoinButton()) {
            Main.getLogginController().runAutoLogIn("TEST", "TEST");
        }

        if (Main.getFavoriteController().getFavoritesUserCount() < 15) {
            System.out.println("(Error de PRE-CONDICIÓN): Se debe tener al quince (15) productos en favoritos para correr este caso de prueba");
            caseToTest.setStatus(Status.BLOCKED);
            return;
        }

        try {
            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            Element.FAV_NAVBAR_BUTTON.getElement().click();

            Main.getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            //<iframe src="https://myaccount.mercadolibre.com.ar/bookmarks/list/widget" width="100%" height="100%" frameborder="0" scrolling="yes" style="height: 100%;"></iframe>
            WebElement iframe = Main.getDriver().findElement(By.xpath("/html/body/div[2]/div[2]/iframe[2]"));
            Main.getDriver().switchTo().frame(iframe);

            try {
                //UL PADRE
                WebElement ul = Main.getDriver().findElement(By.xpath("/html/body/main/div/ul"));

                List<WebElement> listLi = ul.findElements(By.tagName("li"));

                System.out.println("Cantidad de productos en la lista: " + listLi.size());

                if (listLi.size() > 4){
                    caseToTest.addLog("(Error #04-09-1) La cantidad de productos en favoritos es mayor a 4");
                    caseToTest.setStatus(Status.FAILED);
                    return;
                }
            } catch (Exception e) {
                caseToTest.addLog("(Error #04-09-2) No se pudo encontrar la lista de productos favoritos");
                caseToTest.setStatus(Status.FAILED);
                return;
            }
        } catch (Exception e) {
            caseToTest.addLog("(Error #04-08-3) Hubo un error inesperado \n[" + e.getMessage() + "]");
            caseToTest.setStatus(Status.FAILED);
            return;
        }

        caseToTest.setStatus(Status.PASSED);
    }
}
