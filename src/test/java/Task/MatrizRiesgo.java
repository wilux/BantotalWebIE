package Task;

import Action.Radio;
import Config.Acciones;
import Page.MatrizRiesgoPage;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class MatrizRiesgo extends MatrizRiesgoPage {
    WebDriver driver;


    public MatrizRiesgo(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }


    public void Confirmar() throws InterruptedException, AWTException {
        Acciones acciones = new Acciones ( driver );
        Thread.sleep ( 1000 );
        //confirmar
        acciones.keyboard ().Ctrl ( 'o' );
        Thread.sleep ( 3000 );
        //cerrar
        acciones.keyboard ().Ctrl ( 'e' );
    }


    public void ConfirmarFinal() throws InterruptedException, AWTException {
        Acciones acciones = new Acciones ( driver );
//        Click click = new Click ( driver );
//        Get get = new Get ( driver );
        Radio radio = new Radio ( driver );
        final Stopwatch stopwatch = Stopwatch.createStarted ();

        radio.On ( Radio_Si, 0 );
        //confirmar
        acciones.keyboard ().CtrlEnter ();
        Thread.sleep ( 3000 );
//        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 10)) {
//            if ( !get.Habilitado ( BTNOPCONFIRMAR ) ) {
//                break;
//            }
//            else {
//                click.On ( BTNOPCONFIRMAR );
//            }
//        }
        //cerrar
        acciones.keyboard ().Ctrl ( 'e' );
        Thread.sleep ( 3000 );
    }


}
