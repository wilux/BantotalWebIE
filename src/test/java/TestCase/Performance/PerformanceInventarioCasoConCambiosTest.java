package TestCase.Performance;


import Config.BaseTest;
import Page.InventariodeCasosPage;
import Task.Ejecutar;
import Task.Login;
import Task.Menu;
import Tools.Frame;
import com.google.common.base.Stopwatch;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class PerformanceInventarioCasoConCambiosTest extends BaseTest {


    @Test
    //Pruebas de Performance para Inventario de Legajo con Ajustes
    public void ConCambios() throws InterruptedException, AWTException {
        System.out.println ( "Pruebas de Performance para Inventario de Legajo Con Ajustes" );
        //Instancio Objectos
        Login login = new Login ( driver );
        Menu menu = new Menu ( driver );
        Ejecutar ejecutar = new Ejecutar ( driver );
        InventariodeCasosPage inventarioCasos = new InventariodeCasosPage ( driver );
        Frame frame = new Frame ( driver );

        //Hago el Login
        login.Ingresar ( "QA" );

        //Menu Ejecutar
        menu.Ejecutar ();

        //Ejecuto un programa
        ejecutar.Programa ( "hbnqgc30" );

        //Pruebas en Inventario

        //Fechas
        frame.BuscarFrame ( inventarioCasos.inputFechaDesde );
        driver.findElement ( inventarioCasos.inputFechaDesde ).click ();
        driver.findElement ( inventarioCasos.inputFechaDesde ).sendKeys ( "01/01/2021" );

        // Crea e inicia un nuevo cronómetro
        Stopwatch stopwatch;
        long timeElapsed;

        //Prueba 1 - Caso No existente
        frame.BuscarFrame ( inventarioCasos.inputNroCaso );
        driver.findElement ( inventarioCasos.inputNroCaso ).sendKeys ( "999999" );
        stopwatch = Stopwatch.createStarted ();
        /* … El código que se está midiendo comienza … */
        driver.findElement ( inventarioCasos.botonFiltrar ).click ();
        stopwatch.stop ();    // end
        System.out.println ( "Caso 1 - Prueba de Caso inexistente: " + stopwatch.elapsed ( TimeUnit.MILLISECONDS ) + " segundos." );


        //Prueba 2 - Caso existente
        frame.BuscarFrame ( inventarioCasos.inputNroCaso );
        driver.findElement ( inventarioCasos.inputNroCaso ).sendKeys ( "55054" );

        stopwatch = Stopwatch.createStarted ();
        /* … El código que se está midiendo comienza … */
        driver.findElement ( inventarioCasos.botonFiltrar ).click ();
        stopwatch.stop ();    // end
        System.out.println ( "Caso 2 - Prueba de Caso existente: " + stopwatch.elapsed ( TimeUnit.MILLISECONDS ) + " segundos." );


        //Prueba 3 - Cuenta
        frame.BuscarFrame ( inventarioCasos.inputCuenta );
        driver.findElement ( inventarioCasos.inputCuenta ).sendKeys ( "670455" );
        stopwatch = Stopwatch.createStarted ();
        /* … El código que se está midiendo comienza … */
        driver.findElement ( inventarioCasos.botonFiltrar ).click ();
        stopwatch.stop ();    // end
        System.out.println ( "Caso 3 - Prueba de cuenta: " + stopwatch.elapsed ( TimeUnit.MILLISECONDS ) + " segundos." );

        //Prueba 4 - Instancia
        frame.BuscarFrame ( inventarioCasos.inputNroInstancia );
        driver.findElement ( inventarioCasos.inputNroInstancia ).sendKeys ( "2912607" );
        stopwatch = Stopwatch.createStarted ();
        /* … El código que se está midiendo comienza … */
        driver.findElement ( inventarioCasos.botonFiltrar ).click ();
        stopwatch.stop ();    // end
        System.out.println ( "Caso 4 - Prueba de instancia: " + stopwatch.elapsed ( TimeUnit.MILLISECONDS ) + " segundos." );

        //Prueba 5 - Cuil
        frame.BuscarFrame ( inventarioCasos.inputCuilDNI );
        driver.findElement ( inventarioCasos.inputCuilDNI ).sendKeys ( "27395237840" );
        stopwatch = Stopwatch.createStarted ();
        /* … El código que se está midiendo comienza … */
        driver.findElement ( inventarioCasos.botonFiltrar ).click ();
        stopwatch.stop ();    // end
        System.out.println ( "Caso 5 - Prueba de Cuil: " + stopwatch.elapsed ( TimeUnit.MILLISECONDS ) + " segundos." );

    }
}
