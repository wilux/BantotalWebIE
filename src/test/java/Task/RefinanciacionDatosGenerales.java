package Task;

import Config.Acciones;
import Page.RefinanciacionDatosGeneralesPage;
import Page.RefinanciacionSeleccionProductosPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class RefinanciacionDatosGenerales extends RefinanciacionDatosGeneralesPage {
    WebDriver driver;


    public RefinanciacionDatosGenerales(WebDriver driver) {
        super ( driver );
        this.driver = driver;

    }


    //Plan de Pagos del Producto

//
//    //Plan de Pagos Amortizable
//    public By BTNOPCOMISION = By.name ( "BTNOPCOMISION" );
//    public By BTNOPIMPRIMIR = By.name ( "BTNOPIMPRIMIR" );

    public void cantidadCuotas(String cantidad) {
        Acciones acciones = new Acciones ( driver );
        acciones.write ().On ( inputCantidadCuotas, cantidad );
    }

    public void confirmar() {
        Acciones acciones = new Acciones ( driver );
        acciones.click ().On ( BTNOPCONFIRMAR );
    }

    public void selecionarSeguro() {
        Acciones acciones = new Acciones ( driver );
        acciones.checkBox ().Check ( checkSinSeguro );
    }

    public void confirmarSeguro() {
        Acciones acciones = new Acciones ( driver );
        acciones.click ().On ( BTNOPCONTINUAR );
    }

    public void confirmarPlanPago() throws AWTException {
        Acciones acciones = new Acciones ( driver );
//        acciones.click ().On ( BTNOPCONFIRMAR );  -> No funciona
        acciones.keyboard ().CtrlEnter ();
    }


}
