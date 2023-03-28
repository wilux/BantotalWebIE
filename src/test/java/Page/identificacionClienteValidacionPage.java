package Page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class identificacionClienteValidacionPage {
    // Value S o N
    public By RCheckGrupo = By.id ( "_BNQFB11PGE" );
    public By RCheckSociedad = By.id ( "_BNQFB11SHE" );
    //MsgText  //Pendiente de confirmación de la Gcia. de Créditos
    public By MsgText = By.className ( "MsgText" );
    public By Estado = By.id ( "_BNQFB11EST" );
    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );
    public By BTNOPCERRAR = By.id ( "BTNOPCERRAR" );
    public By BTN_SI = By.id ( "BTNCONFIRMATION" );
    public By BTN_NO = By.id ( "BTNCANCELCONFIRMATION" );
    WebDriver driver;

    public identificacionClienteValidacionPage(WebDriver driver) {

        this.driver = driver;

    }


}


