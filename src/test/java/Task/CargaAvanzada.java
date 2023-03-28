package Task;

import Action.Click;
import Page.CargaAvanzadaPage;
import org.openqa.selenium.WebDriver;

public class CargaAvanzada extends CargaAvanzadaPage {
    WebDriver driver;


    public CargaAvanzada(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }


    public void Aceptar() throws InterruptedException {
        //Buscar entrevista
        Click click = new Click ( driver );
        click.On ( BTNOPACEPTAR );
        Thread.sleep ( 3000 );
        click.On ( BTN_SI );

    }


}
