package Action;

import Tools.Frame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Grid {
    WebDriver driver;

    public Grid(WebDriver driver) {

        this.driver = driver;

    }

    public void rowSelectbyText(By locator, String valor) {

        Frame frame = new Frame ( driver );
        if ( frame.BuscarFrame ( locator ) ) {

            try {
                WebElement webElement = driver.findElement ( locator );
                WebElement span = webElement.findElement ( By.xpath ( "//span[contains(text(),'" + valor + "')]" ) );
                System.out.println ( "Encontre " + valor );
                span.click ();
                System.out.println ( "Seleccione " + valor );

            } catch (Exception e) {
                System.out.println ( "No encontre " + valor );
            }
        }
        else {
            System.out.println ( "No se encontro " + locator + " - " + valor );
        }

    }


    public void rowSelectbyFila(By locator, By fila) {

        Frame frame = new Frame ( driver );
        if ( frame.BuscarFrame ( locator ) ) {

            try {
                WebElement webElement = driver.findElement ( locator );
                webElement.findElement ( fila ).click ();

            } catch (Exception e) {
                System.out.println ( e );
            }
        }

    }

}
