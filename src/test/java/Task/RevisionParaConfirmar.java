package Task;

import Action.Click;
import Action.Get;
import Action.Keyboard;
import Action.Write;
import Config.Acciones;
import Page.RevisionParaConfirmarPage;
import Page.RevisionProductosPage;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class RevisionParaConfirmar extends RevisionParaConfirmarPage {
    WebDriver driver;


    public RevisionParaConfirmar(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }


    public void Confirmar() {
        Click click = new Click ( driver );
        Write write = new Write ( driver );

        write.On ( InputObservaciones, "Test" );
        click.On ( BTNOPCONFIRMAR );
        click.On ( BTN_SI );

    }

    public void PerfilRiesgo() throws InterruptedException, AWTException {
        Click click = new Click ( driver );
        Get get = new Get ( driver );
        Keyboard keyboard = new Keyboard ();
        final Stopwatch stopwatch = Stopwatch.createStarted ();

        if ( get.Habilitado ( BTNOPPERFILDERIESGO ) ) {
            keyboard.Ctrl ( 'r' );
        }
        else {
            while (!get.Habilitado ( BTNOPPERFILDERIESGO ) || (stopwatch.elapsed ( TimeUnit.SECONDS ) < 20)) {
                Thread.sleep ( 1000 );
            }
//            click.On ( BTNOPPERFILDERIESGO );
            keyboard.Ctrl ( 'r' );
        }
    }

    public void Liquidar() {
        Click click = new Click ( driver );

        click.On ( BTNOPLIQUIDAR );
        click.On ( BTN_SI );

    }


    public void Finalizar() {
        Click click = new Click ( driver );
        click.On ( BTNOPFINALIZAR );

    }
}
