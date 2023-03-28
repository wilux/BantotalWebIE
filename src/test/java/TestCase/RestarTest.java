package TestCase;

import Config.Acciones;
import Config.BaseTest;
import Tools.Restart;
import Tools.SQLDatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;


public class RestarTest extends BaseTest {

    String usuarioPlataforma = "SERPILLOE";
    String usuarioGerencia = "RODRIGUA";
    String usuarioCreditos = "PIANCIOLAG";
    String usuarioCentral = "LOMBERAM";

    @Test(priority = 0, description = "Prueba de Login y reLogin")
    //Tests relogin
    public void ReLogin() throws InterruptedException, AWTException, SQLException {

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();


        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( usuarioPlataforma );
        Acciones acciones = new Acciones ( driver );
        acciones.login ().Ingresar ( "QA" );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        Assert.assertTrue ( true );
        System.out.println ( "Driver Normal " + driver.toString () );
    }

    @Test(priority = 1, description = "Prueba de Login y reLogin1")
    //Tests relogin
    public void ReLogin1() throws InterruptedException, AWTException, SQLException {
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioGerencia );
        System.out.println ( "Driver usuarioGerencia " + driver.toString () );

        //Menu Ejecutar
        Acciones acciones2 = new Acciones ( driver );
        acciones2.menu ().Ejecutar ();
        Assert.assertTrue ( true );
    }

    @Test(priority = 3, description = "Prueba de Login y reLogin2")
    public void ReLogin2() throws InterruptedException, AWTException, SQLException {
        //Tests relogin
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioCreditos );

        Acciones acciones3 = new Acciones ( driver );
        System.out.println ( "Driver usuarioCreditos " + driver.toString () );
        //
        //Menu Ejecutar
        acciones3.menu ().Ejecutar ();
        Assert.assertTrue ( true );

    }

}
