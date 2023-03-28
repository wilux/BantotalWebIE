package Task;

import Config.Acciones;
import Page.RefinanciacionControlaDocPage;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class RefinanciacionControlaDoc extends RefinanciacionControlaDocPage {
    WebDriver driver;


    public RefinanciacionControlaDoc(WebDriver driver) {
        super ( driver );
        this.driver = driver;

    }


    public void confirmar() throws InterruptedException, AWTException {
        Acciones acciones = new Acciones ( driver );
//        acciones.click ().On ( BTNOPCONFIRMAR );
        acciones.keyboard ().Ctrl ( 'o' );
        Thread.sleep ( 4000 );
        acciones.click ().On ( BTNCONFIRMATION );


    }


}
