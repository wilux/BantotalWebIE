package Task;

import Config.Acciones;
import Page.RefinanciacionSeleccionProductosPage;
import org.openqa.selenium.WebDriver;

public class RefinanciacionSeleccionProductos extends RefinanciacionSeleccionProductosPage {
    WebDriver driver;


    public RefinanciacionSeleccionProductos(WebDriver driver) {
        super ( driver );
        this.driver = driver;

    }

    public void seleccionarTodaslasCuentas() {
        Acciones acciones = new Acciones ( driver );
        acciones.click ().On ( BTNOPSELECTODASCUENTAS );
    }

    public void seleccionarTodaslasTarjetas() {
        Acciones acciones = new Acciones ( driver );
        acciones.click ().On ( BTNOPSELECTODASTARJETAS );
    }

    public void seleccionarTodosPrestamo() {
        Acciones acciones = new Acciones ( driver );
        acciones.click ().On ( BTNOPSELECTODOSPRESTAMOS );
    }

    public void actualizarTotal() {
        Acciones acciones = new Acciones ( driver );
        acciones.click ().On ( BTNOPACTUALIZARTOTAL );
    }


    public void cuentaDesembolso() {
        Acciones acciones = new Acciones ( driver );
        acciones.checkBox ().Check ( checkCuentaDesembolso1 );
    }

    public void cuentaCobro() {
        Acciones acciones = new Acciones ( driver );
        acciones.checkBox ().Check ( checkCuentaCobro1 );
    }

    public void refinanciar() {
        Acciones acciones = new Acciones ( driver );
        acciones.checkBox ().Check ( BTNOPREFINANCIAR );
    }


}
