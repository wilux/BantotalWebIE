package Task;

import Action.Click;
import Action.Get;
import Action.Grid;
import Page.ReutilizacionABMProductosPage;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Reutilizacion extends ReutilizacionABMProductosPage {
    WebDriver driver;


    public Reutilizacion(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }


    public void AceptarTarjetaDebito() throws InterruptedException {
        Click click = new Click ( driver );
        Grid grid = new Grid ( driver );
        Get get = new Get ( driver );

        if ( get.Existe ( BTNOPACEPTARTExistente ) ) {
            grid.rowSelectbyFila ( GRID_TarjetasDebito, PrimerTarjeta );
            click.On ( BTNOPACEPTARTExistente );
        }
        else {
            click.On ( BTNOPACEPTARTDNUEVA );
        }
    }

    public void PerfilRiesgo() throws InterruptedException {
        Click click = new Click ( driver );
        Get get = new Get ( driver );
        final Stopwatch stopwatch = Stopwatch.createStarted ();

        if ( get.Habilitado ( BTNOPPERFILDERIESGO ) ) {
            click.On ( BTNOPPERFILDERIESGO );
        }
        else {
            while (!get.Habilitado ( BTNOPPERFILDERIESGO ) || (stopwatch.elapsed ( TimeUnit.SECONDS ) < 10)) {
                Thread.sleep ( 1000 );
            }
            click.On ( BTNOPPERFILDERIESGO );
        }
    }


}
