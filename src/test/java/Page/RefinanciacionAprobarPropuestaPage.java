package Page;


import Config.Acciones;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RefinanciacionAprobarPropuestaPage {
    //Localizadores

    //Botonera

    public By BTNOPAPROBAR = By.id ( "BTNOPAPROBAR" );
    public By BTNOPRESIMULAR = By.id ( "BTNOPRESIMULAR" );
    public By BTNOPRECHAZAR = By.id ( "BTNOPRECHAZAR" ); // value 1 o 2 // Fecha Fija Plazo Fijo
    public By BTNOPVOLVER = By.id ( "BTNOPVOLVER" );


    WebDriver driver;

    public RefinanciacionAprobarPropuestaPage(WebDriver driver) {

        this.driver = driver;
        Acciones acciones = new Acciones ( driver );

    }


}


