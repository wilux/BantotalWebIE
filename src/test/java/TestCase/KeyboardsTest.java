package TestCase;

import Config.Acciones;
import Config.BaseTest;
import Task.Login;
import Tools.SQLDatabaseConnection;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;


public class KeyboardsTest extends BaseTest {


    @Test
    public void Completar() throws InterruptedException, AWTException, SQLException {

        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( "SERPILLOE" );

        //Login
        Login login = new Login ( driver );
        login.Ingresar ( "QA" );

        Acciones acciones = new Acciones ( driver );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Probar Ctrol + Enter
        acciones.keyboard ().Ctrl ( 's' );


    }
}
