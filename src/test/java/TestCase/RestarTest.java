package TestCase;

import Config.Acciones;
import Config.BaseTest;
import Tools.Restart;
import Tools.SQLDatabaseConnection;
import Tools.logs.Log;
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
        Log.reportLog ( "Step 1 - Ingreso como Plataforma" );
        Log.reportLogScreen ( driver );
        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        Log.reportLogScreen ( driver );
        Assert.assertTrue ( true );
        System.out.println ( "Driver Normal " + driver.toString () );
    }

    @Test(priority = 1, description = "Prueba de Login y reLogin1")
    //Tests relogin
    public void ReLogin1() throws InterruptedException, AWTException, SQLException {
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioGerencia );
        System.out.println ( "Driver usuarioGerencia " + driver.toString () );
        Log.reportLog ( "Step 2 - Ingreso como Gerente" );
        Log.reportLogScreen ( driver );
        //Menu Ejecutar
        Acciones acciones2 = new Acciones ( driver );
        acciones2.menu ().Ejecutar ();
        Log.reportLog ( "Step 3 - Otra foto Test" );
        Log.reportLogScreen ( driver );
        Assert.assertTrue ( true );
    }

    @Test(priority = 3, description = "Prueba de Login y reLogin2")
    public void ReLogin2() throws InterruptedException, AWTException, SQLException {
        //Tests relogin
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioCreditos );
        Log.reportLog ( "Step 3 - Ingreso como Creditos" );
        Log.reportLogScreen ( driver );
        Acciones acciones3 = new Acciones ( driver );
        System.out.println ( "Driver usuarioCreditos " + driver.toString () );
        //
        //Menu Ejecutar
        acciones3.menu ().Ejecutar ();
        Log.reportLog ( "Step 3 - Otra foto Test" );
        Log.reportLogScreen ( driver );
        Assert.assertTrue ( true );

    }

}
