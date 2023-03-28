package Task;

import Action.Click;
import Action.Write;
import Page.RevisionProductosPage;
import org.openqa.selenium.WebDriver;

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
