package Task;

import Config.Acciones;
import Page.RefinanciacionAprobarPropuestaPage;
import Page.RefinanciacionValidarPropuestaPage;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class RefinanciacionValidarPropuesta extends RefinanciacionValidarPropuestaPage {
    WebDriver driver;


    public RefinanciacionValidarPropuesta(WebDriver driver) {
        super ( driver );
        this.driver = driver;

    }


    public void confirmar() throws AWTException {
        Acciones acciones = new Acciones ( driver );
//        acciones.click ().On ( BTNOPCONFIRMAR ); --> No funciona
        acciones.keyboard ().Ctrl ( 'o' );
    }


}
