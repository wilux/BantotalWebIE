package Page;


import Config.Acciones;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RefinanciacionDatosGeneralesPage {
    //Localizadores

    //Plan de Pagos del Producto

    public By fechaValor = By.name ( "_PAOFVAL" );
    public By fechaPrimerPago = By.id ( "_FPRIMPAGO" );
    public By selectVto = By.id ( "_VTO" ); // value 1 o 2 // Fecha Fija Plazo Fijo
    public By inputCantidadCuotas = By.id ( "_CANTCUO" );

    //Botonera
    public By BTNOPSEGUROS = By.name ( "BTNOPSEGUROS" );
    public By BTNOPVALIDAR = By.name ( "BTNOPVALIDAR" );
    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );

    //Seguros de la Operación
    public By GRIDGRDSEGUROS = By.name ( "GRIDGRDSEGUROS" );
    public By checkSinSeguro = By.name ( "_ZG1_GR_GRDSEGUROS_CHECKROW_0001" );
    public By BTNOPCONTINUAR = By.id ( "BTNOPCONTINUAR" );

    //Plan de Pagos Amortizable
    public By BTNOPCOMISION = By.name ( "BTNOPCOMISION" );
    public By BTNOPIMPRIMIR = By.name ( "BTNOPIMPRIMIR" );

    WebDriver driver;

    public RefinanciacionDatosGeneralesPage(WebDriver driver) {

        this.driver = driver;
        Acciones acciones = new Acciones ( driver );

    }


}


