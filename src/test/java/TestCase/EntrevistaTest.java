package TestCase;

import Config.BaseTest;
import Task.Menu;
import Task.*;
import Tools.SQLDatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;


public class EntrevistaTest extends BaseTest {


    @Test
    public void Completar() throws InterruptedException, AWTException, SQLException {

        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( "SERPILLOE" );

        //Login
        Login login = new Login ( driver );
        login.Ingresar ( "QA" );

        Menu menu = new Menu ( driver );
        Ejecutar ejecutar = new Ejecutar ( driver );
        BandejaTareas bandejaTareas = new BandejaTareas ( driver );
        Entrevista entrevista = new Entrevista ( driver );

        //Menu Ejecutar
        menu.Ejecutar ();
        //Abrir BandejaTareas
        ejecutar.Programa ( "hxwf900" );

        //Abrir Entrevista
        bandejaTareas.iniciarEntrevista ( "Entrevista / Identificación" );


        //Ingresar Tipo y Documento
        entrevista.IdentificacionPersona ( "C.U.I.L.", "23353071459 " );
        //Entrevista
        entrevista.CompletarGenerico ();
        //Guardar Nro Entrevista
        String NroEntrevista = entrevista.NroEntrevista ();
        System.out.println ( "Entrevista Nro: " + NroEntrevista );

        //Si no hay errores, la entrevista se completó bien.
        Assert.assertFalse ( entrevista.Error () );

        if ( entrevista.Error () ) {
            System.out.println ( "Existen errores" );
        }
        else {
            entrevista.Cerrar ();
        }


    }
}
