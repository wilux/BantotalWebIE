package Action;

import Tools.Frame;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Click {
    WebDriver driver;


    public Click(WebDriver driver) {

        this.driver = driver;

    }




    public void On(By locator) {
        Frame frame = new Frame ( driver );
        final Stopwatch stopwatch = Stopwatch.createStarted ();
        boolean estado = false;
        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 5)) {
            if ( frame.BuscarFrame ( locator ) ) {
                try {
                    driver.findElement ( locator ).click ();
                    estado = true;
                    break;
                } catch (Exception e) {
                    System.out.println ( "No se encontró " + locator );
                    continue;
                }
            }
            else {
                System.out.println ( "Reintentando busqueda de Frame para " + locator );
                System.out.println ( "Tiempo " + stopwatch.elapsed ( TimeUnit.SECONDS ) );
                continue;
            }
        }
        if ( !estado ) {
            Assert.fail ( "No se encontró " + locator + " y no se puede continuar prueba." );
            System.out.println ( "No se encontró " + locator + " y no se puede continuar prueba." );
        }
    }


    public void Js(By locator) {
        Frame frame = new Frame ( driver );
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        final Stopwatch stopwatch = Stopwatch.createStarted ();

        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 10)) {
            if ( frame.BuscarFrame ( locator ) ) {
                try {
                    executor.executeScript ( "arguments[0].click();", driver.findElement ( locator ) );
                    break;
                } catch (Exception e) {
                    System.out.printf ( "Intento de Click fallido en " + locator );
                    continue;
                }
            }
            else {
                System.out.println ( "Buscando Frame para " + locator );
                System.out.println ( "Tiempo " + stopwatch.elapsed ( TimeUnit.SECONDS ) );
                continue;
            }
        }

    }
}

