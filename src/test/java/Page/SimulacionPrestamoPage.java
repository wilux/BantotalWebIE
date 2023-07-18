package Page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimulacionPrestamoPage {
    //Mensajes
    public By MsgTextArriba = By.className ( "MsgText" );
    public By MsgTextMedio = By.className ( "HTMLTXTTEXT1" );
    public By MsgTextAbajo = By.className ( "HTMLTXTTEXT5" );

    //Prestamo
    public By SelectLineaPrestamo = By.name ( "_LINEA" );
    public By InputMontoSolicitado = By.name ( "_BNQFPC5MTO" );
    public By InputPlazo = By.name ( "_BNQFPC5PZO" );
    public By SelectDestinoFondos = By.name ( "_BNQFPC5DES" );
    public By BTNOPSIMULAR = By.id ( "BTNOPSIMULAR" );
    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );
    public By BTN_SI = By.id ( "BTNCONFIRMATION" );
    public By BTN_NO = By.id ( "BTNCANCELCONFIRMATION" );
    public By BTNOPMONTOMAXIMO = By.id("BTNOPMONTOMAXIMO");
    public By ValorCuotaAprox = By.name ( "_BNQFPC5CUO" );
    public By ValorMontoAprox = By.name ( "  _MONTOAPROX" );
    public By Span_Tna = By.id ( "span__BNQFPC5TNA" );
    public By input_Tna = By.id ( "_BNQFPC5TNA" );
    public By Span_Tem = By.name ( "span__BNQFPC5TEM" );
    public By Span_Tea = By.name ( "span__BNQFPC5TEA" );

    //plan de Pago
    public By GRIDOP_BTNOPCONFIRMAR = By.id ( "GRIDOP_BTNOPCONFIRMAR" );
    public By BTNOPCOMISION = By.id ( "BTNOPCOMISION" );

    //Confirmar Simulacion
    public By BTNOPCARTASIMULADOR = By.id ( "BTNOPCARTASIMULADOR" );


    WebDriver driver;

    public SimulacionPrestamoPage(WebDriver driver) {

        this.driver = driver;

    }


}


