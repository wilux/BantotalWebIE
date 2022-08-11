package Page;


import Config.Acciones;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RefinanciacionValidarPropuestaPage {
    //Localizadores

    //Botonera

    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );
    public By BTNOPRECHAZAR = By.id ( "BTNOPRECHAZAR" );
    public By BTNOPVOLVER = By.id ( "BTNOPVOLVER" ); //


    WebDriver driver;

    public RefinanciacionValidarPropuestaPage(WebDriver driver) {

        this.driver = driver;
        Acciones acciones = new Acciones ( driver );

    }


}


