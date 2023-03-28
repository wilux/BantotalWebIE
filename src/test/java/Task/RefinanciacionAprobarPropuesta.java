package Task;

import Config.Acciones;
import Page.RefinanciacionAprobarPropuestaPage;
import org.openqa.selenium.WebDriver;

public class RefinanciacionAprobarPropuesta extends RefinanciacionAprobarPropuestaPage {
    WebDriver driver;


    public RefinanciacionAprobarPropuesta(WebDriver driver) {
        super ( driver );
        this.driver = driver;

    }


    public void aprobar() {
        Acciones acciones = new Acciones ( driver );
        acciones.click ().On ( BTNOPAPROBAR );
    }


}
