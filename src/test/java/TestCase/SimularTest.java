package TestCase;

import Config.Acciones;
import Config.BaseTest;
import Tools.SQLDatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;


public class SimularTest extends BaseTest {


    @Test
    //Tests google calculator
    public void Simular() throws InterruptedException, AWTException, SQLException {


        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );


        bd.CambiarUsuario ( "SERPILLOE" );

        //Logueamos
        acciones.login ().Ingresar ( "QA" );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Abrir Entrevista
        acciones.bandejaTareas ().iniciarEntrevista ( "Entrevista / Identificación" );


        //Ingresar Tipo y Documento
        acciones.entrevista ().IdentificacionPersona ( "C.U.I.L.", "23353071459 " );
        //Entrevista
        acciones.entrevista ().CompletarGenerico ();
        //Guardar Nro Entrevista
        String NroEntrevista = acciones.entrevista ().NroEntrevista ();
        System.out.println ( "Entrevista Nro: " + NroEntrevista );


        //Si la entrevista retorna un error detenemos sino continuamos
        if ( acciones.entrevista ().Error () ) {
            System.out.println ( "Existen errores con la Entrevista" );
            Assert.fail ();
        }
        else {
            acciones.entrevista ().Cerrar ();
            acciones.bandejaTareas ().siguienteEntrevista ( NroEntrevista );
            acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevista );
        }

        //Si llegamos hasta la pantalla de simulacion la prueba fue exitosa.
        Assert.assertTrue ( acciones.simulacion ().Existe () );

    }
}
