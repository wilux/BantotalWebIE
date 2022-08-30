package Page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RevisionParaConfirmarPage {
    public By InputObservaciones = By.name ( "_BNQFPC5OBS" );
    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );
    public By BTNOPFINALIZAR = By.id ( "BTNOPFINALIZAR" );
    public By BTNOPLIQUIDAR = By.id ( "BTNOPLIQUIDAR" );
    public By BTNOPRECHAZAR = By.id ( "BTNOPRECHAZAR" );
    public By BTNOPPERFILDERIESGO = By.id ( "BTNOPPERFILDERIESGO" );
    public By BTN_SI = By.id ( "BTNCONFIRMATION" );
    public By BTN_NO = By.id ( "BTNCANCELCONFIRMATION" );
    public By Check_CAUSD = By.id ( "_CA_DOLARES" );
    WebDriver driver;

    public RevisionParaConfirmarPage(WebDriver driver) {

        this.driver = driver;

    }


}


