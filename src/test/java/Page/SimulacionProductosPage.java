package Page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimulacionProductosPage {
    //Mensajes
    public By MsgTextArriba = By.className ( "MsgText" );
    public By MsgTextMedio = By.className ( "HTMLTXTTEXT1" );
    public By MsgTextAbajo = By.className ( "HTMLTXTTEXT5" );
    //Simulacion
    //Categoria Productos Solicitados
    public By SelectPaquete = By.name ( "_JBNYC5PQTE" );
    public By SelectTipoPaquete = By.name ( "_TIPOPAQUETE" );
    public By CheckPrestamo = By.name ( "_BNQFPC5PP" );
    public By CheckCtaCte = By.name ( "_CTACTE" );
    public By CheckCaDolar = By.name ( "_CA_DOLARES" );
    public By CheckCalificacion = By.name ( "_BNQFPC5CBE" );
    public By CheckTarjeta = By.name ( "_BNQFPC5TC" );
    public By SelectLineaPrestamo = By.name ( "_LINEA" );

    //Acuerdo en Cuenta corriente
    public By spanNuevoImporteAcc = By.id ( "span__JBNYC5ACIM_NUEVO" );
    public By checkAcuerdo = By.name ( "_ACTUALIZAACC" );

    //Prestamo
    public By ValorMontoAprox = By.name ( "  _MONTOAPROX" );
    public By BTNOPPRESTAMOS = By.id("BTNOPPRESTAMOS");
    public By InputMontoSolicitado = By.name ( "_BNQFPC5MTO" );
    public By InputPlazo = By.name ( "_BNQFPC5PZO" );
    public By SelectDestinoFondos = By.name ( "_BNQFPC5DES" );
    public By BTNOPSIMULAR = By.id ( "BTNOPSIMULAR" );
    public By BTNOPDESCARTAR = By.id ( "BTNOPDESCARTAR" );
    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );
    public By BTN_SI = By.id ( "BTNCONFIRMATION" );
    public By BTN_NO = By.id ( "BTNCANCELCONFIRMATION" );
    // public By BTNCONFIRMATION = By.id("BTNCONFIRMATION");
    public By ValorCuotaAprox = By.name ( "_BNQFPC5CUO" );

    public By Span_CuotaAprox = By.id ( "span__BNQFPC5CUO" );
    public By Span_MontoAprox = By.id ( "span__MONTOAPROX" );

    public By Span_TasaSeguro = By.id ( "span__TASAEN0" );

    public By Span_ComisLiq = By.id ( "span__BNQFPC5CLI" );
    public By Span_ComisAdmMensual = By.id ( "span__BNQFPC5CAM" );
    public By Span_Impuestos = By.id ( "span__MONTOSELLO" );
    public By Span_IvaIntereses= By.id ( "span__BNQFPC5IVI" );
    public By Span_IvaComision = By.id ( "span__BNQFPC5IVC" );
    public By Span_Operacion = By.id ( "span__BNQFPC5OPE" );
    public By Span_Tna = By.id ( "span__BNQFPC5TNA" );
    public By input_Tna = By.id ( "_BNQFPC5TNA" );
    public By Span_Tem = By.name ( "span__BNQFPC5TEM" );
    public By Span_Tea = By.name ( "span__BNQFPC5TEA" );

    //plan de Pago
    public By GRIDOP_BTNOPCONFIRMAR = By.id ( "GRIDOP_BTNOPCONFIRMAR" );
    public By BTNOPCOMISION = By.id ( "BTNOPCOMISION" );

    //Confirmar Simulacion
    public By BTNOPCARTASIMULADOR = By.id ( "BTNOPCARTASIMULADOR" );

    //Tarjetas Disponibles
    public By CheckTC1 = By.name ( "_TCSEL_0001" );
    public By CheckTC2 = By.name ( "_TCSEL_0002" );
    public By BTNOPPAQUETIZAR = By.id ( "BTNOPPAQUETIZAR" );

    public By SelectCircuito = By.name ( "_CICUITOS_CALIF_E" );
    public By BTNOPADHESION_SERVICIOS = By.name ( "BTNOPADHESION_SERVICIOS" );
    public By BTNOPCERRAR = By.id ( "BTNOPCERRAR" );
    //Categoria Acuerdo BI
    public By tablaAcuerdo = By.id ( "TBL118" );
    public By spanTituloAcc = By.id ( "HTMLTXTTITLE395" );
    public By checkActualizarAcc = By.id ( "_ACTUALIZAACC" );
    public By inputMontoActualAcc = By.id ( "span__JBNYC5ACIM" );
    public By inputMontoNuevoAcc = By.id ( "_JBNYC5ACIM_NUEVO" );
    WebDriver driver;

    public SimulacionProductosPage(WebDriver driver) {

        this.driver = driver;

    }


}


