package Page;


import Config.Acciones;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RefinanciacionSeleccionProductosPage {
    //Localizadores

    //Productos
    //Cuentas

    public By gridCuentas = By.id ( "HTMLTBLCUENTAS" );
    public By BTNOPANALIZARCUENTA = By.id ( "BTNOPANALIZARCUENTA" );
    public By BTNOPSELECTODASCUENTAS = By.id ( "BTNOPSELECTODASCUENTAS" );
    public By BTNOPDESELECTODASCUENTAS = By.id ( "BTNOPDESELECTODASCUENTAS" );

    //Tarjetas
    public By GRIDTARJETAS = By.id ( "GRIDTARJETAS" );
    public By checkTarjeta1 = By.name ( "_SEL_TARJ_0001" );
    public By inputSaldoTarjeta = By.name ( "_SALDO_TARJ_0001" );
    public By BTNOPSELECTODASTARJETAS = By.id ( "BTNOPSELECTODASTARJETAS" );
    public By BTNOPDESELECTODASTARJETAS = By.id ( "BTNOPDESELECTODASTARJETAS" );
    public By BTNOPGUARDARSALDOS = By.id ( "BTNOPGUARDARSALDOS  " );

    //Prestamos
    public By gridPrestamos = By.id ( "HTMLTBLPRESTAMOS" );
    public By checkPrestamo1 = By.name ( "_SEL_PREST_0001" );
    public By BTNOPSELECTODOSPRESTAMOS = By.id ( "BTNOPSELECTODOSPRESTAMOS" );
    public By BTNOPDESELECTODOSCONCEP = By.id ( "BTNOPDESELECTODOSCONCEP" );

    //otros coneceptos
    public By gridOtrosConceptos = By.id ( "HTMLTBLOTROSCONCEPTOS" );
    public By BTNOPSELECTODOSCONCEP = By.id ( "BTNOPSELECTODOSCONCEP" );
//    public By BTNOPDESELECTODOSCONCEP = By.id ( "BTNOPDESELECTODOSCONCEP" );

    //Detalle refinanciación
    public By inputOtros = By.id ( "_GASTOS" );
    public By inputDescripcion = By.id ( "_GASTOS_DSC" );
    public By BTNOPACTUALIZARTOTAL = By.id ( "BTNOPACTUALIZARTOTAL" );

    //Cuenta de desembolso
    public By gridCuentaDesembolso = By.id ( "HTMLTBLCUENTASDEDESEMBOLSO" );
    public By checkCuentaDesembolso1 = By.name ( "_SEL_CUE_DES_0001" );

    //Cuenta de cobro
    public By gridCuentaCobro = By.id ( "HTMLTBLCUENTASDECOBRO" );
    public By checkCuentaCobro1 = By.name ( "_SEL_CUE_COB_0001" );

    //Botonera
    public By BTNOPREFINANCIAR = By.id ( "BTNOPREFINANCIAR" );
    public By BTNOPRECHAZAR = By.id ( "BTNOPRECHAZAR" );
    public By BTNOPVOLVER = By.id ( "BTNOPVOLVER" );


    WebDriver driver;

    public RefinanciacionSeleccionProductosPage(WebDriver driver) {

        this.driver = driver;
        Acciones acciones = new Acciones ( driver );

    }


}


