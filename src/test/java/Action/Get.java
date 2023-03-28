package Action;

import Tools.Frame;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Get {
    WebDriver driver;


    public Get(WebDriver driver) {

        this.driver = driver;

    }

    public String ValueOnInput(By locator) {
        String value = "";
        Frame frame = new Frame ( driver );

        try {
            if ( frame.BuscarFrame ( locator ) ) {
                value = driver.findElement ( locator ).getAttribute ( "value" );

            }
            else {
                value = "";
                System.out.println ( "No se encontró " + locator );
            }

        } catch (Exception e) {

        }

        return value;
    }

    public String TextOnTag(By locator) {
        String value = "";
        Frame frame = new Frame ( driver );
        final Stopwatch stopwatch = Stopwatch.createStarted ();

        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 10)) {
            if ( frame.BuscarFrame ( locator ) ) {
                value = driver.findElement ( locator ).getText ();
                break;
            }
            else {
                System.out.println ( "No se encontró " + locator );
                continue;
            }
        }
        return value;
    }

    public String ValueJS(By locator, String id) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String value = "";
        Frame frame = new Frame ( driver );

        if ( frame.BuscarFrame ( locator ) ) {
            value = (String) js.executeScript ( "return document.getElementById('" + id + "').innerHTML" );
        }
        else {
            System.out.println ( "No se encontró " + locator );
        }
        return value;
    }

    public boolean Visible(By locator) {

        Frame frame = new Frame ( driver );
        boolean value = false;
        if ( frame.BuscarFrame ( locator ) ) {
            value = driver.findElement ( locator ).isDisplayed ();

        }
        else {
            System.out.println ( "No se encontró " + locator );
        }
        return value;
    }

    public boolean Seleccionado(By locator) {

        Frame frame = new Frame ( driver );
        boolean value = false;
        if ( frame.BuscarFrame ( locator ) ) {
            value = driver.findElement ( locator ).isSelected ();

        }
        else {
            System.out.println ( "No se encontró " + locator );
        }
        return value;
    }

    public boolean Habilitado(By locator) {

        Frame frame = new Frame ( driver );
        boolean value = false;
        if ( frame.BuscarFrame ( locator ) ) {
            value = driver.findElement ( locator ).isEnabled ();

        }
        else {
            System.out.println ( "No se encontró " + locator );
        }
        return value;
    }


    public boolean Existe(By locator) {
        Frame frame = new Frame ( driver );
        final Stopwatch stopwatch = Stopwatch.createStarted ();
        boolean estado = false;
        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 300)) {
            if ( frame.BuscarFrame ( locator ) ) {
                try {
                    driver.findElement ( locator ).isDisplayed ();
                    estado = true;
                    break;
                } catch (Exception e) {
                    System.out.println ( "No se encontró " + locator );
                    continue;
                }
            }
        }
        return estado;
    }


}


