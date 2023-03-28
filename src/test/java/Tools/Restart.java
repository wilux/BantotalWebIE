package Tools;

import Config.Acciones;
import Config.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.awt.*;
import java.sql.SQLException;


public class Restart extends BaseTest {

    WebDriver driver;
    String usuarioPlataforma = "SERPILLOE";
    String usuarioGerencia = "RODRIGUA";
    String usuarioCreditos = "PIANCIOLAG";
    String usuarioCentral = "LOMBERAM";

    public Restart(WebDriver driver) {

        this.driver = driver;

    }

    public WebDriver As(String user) throws InterruptedException, AWTException, SQLException {

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();


        //Nuevo Usuario
        bd.CambiarUsuario ( user );
        System.out.println ( "Driver Inicial " + driver.toString () );
        //Reiniciamos
        restartDriver ();
        System.out.println ( "Driver Nuevo " + driver.toString () );
        Acciones acciones = new Acciones ( driver );
        //Logueamos
        acciones.login ().Ingresar ( "QA" );
        Thread.sleep ( 5000 );
        return driver;
    }

    public void restartDriver() {
        driver.manage ().deleteAllCookies ();
        driver.quit ();
        driver = null;
        driver = new InternetExplorerDriver ();
        setDriver ( driver );
    }


}
