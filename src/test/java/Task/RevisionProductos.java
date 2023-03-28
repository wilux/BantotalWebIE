package Task;

import Action.Click;
import Action.Get;
import Action.Grid;
import Action.Write;
import Page.ReutilizacionABMProductosPage;
import Page.RevisionProductosPage;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class RevisionProductos extends RevisionProductosPage {
    WebDriver driver;


    public RevisionProductos(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }


    public void Confirmar() {
        Click click = new Click ( driver );
        Write write = new Write ( driver );

        write.On ( InputObservaciones, "Test" );
        click.On ( BTNOPCONFIRMAR );
//        click.On ( BTN_SI );


    }


}
