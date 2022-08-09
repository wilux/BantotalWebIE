package TestCase.E2E;

import Config.Acciones;
import Config.BaseTest;
import Tools.SQLDatabaseConnection;
import Tools.logs.Log;
import com.google.common.base.Stopwatch;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class WF_RefinanciacionTest extends BaseTest {

    //Datos del caso
    String cuil = "27947868697 ";
    String NroEntrevista = "1362115";
    String usuarioPlataforma = "SERPILLOE";


    //Login e iniciar Bandeja
    @BeforeTest
    public void IniciarSimulacion() throws InterruptedException, AWTException {

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( usuarioPlataforma );


        //Logueamos
        acciones.login ().Ingresar ( "QA" );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );


    }

    //Iniciar Entrevista
    @Test(priority = 0)
    public void Bandeja() throws InterruptedException {
        Log.reportLog ( "Step 1 - Abrimos Bandeja Tareas e Iniciamos Entrevista" );
        Acciones acciones = new Acciones ( driver );

        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().iniciarEntrevista ( "Entrevista / Identificación" );


        //Ingresar Tipo y Documento
        Log.reportLogScreen ( driver );
        acciones.entrevista ().IdentificacionPersona ( "C.U.I.L.", cuil );
        //Entrevista
        acciones.entrevista ().Modalidad ( "Presencial" );
        acciones.entrevista ().ActividadLaboral ();
        acciones.entrevista ().DatosDelNegocio ();
        acciones.entrevista ().DatosPersonales ();
        acciones.entrevista ().DatosContacto ();
        acciones.entrevista ().CuentaDebito ();
        acciones.entrevista ().Ingresos ( "Dependiente" );
        acciones.entrevista ().Refinanciar ();
        acciones.entrevista ().Confirmar ();
        //Guardar Nro Entrevista
        NroEntrevista = acciones.entrevista ().NroEntrevista ();
        System.out.println ( "Entrevista Nro: " + NroEntrevista );


        //Si la entrevista retorna un error detenemos sino continuamos
        if ( acciones.entrevista ().Error () ) {
            System.out.println ( "Existen errores con la Entrevista" );
            Assert.fail ();
        }
        else {
            acciones.entrevista ().Cerrar ();
            Assert.assertTrue ( true );
            acciones.bandejaTareas ().siguienteEntrevista ( NroEntrevista );

        }

    }


    //Iniciar Refinanciacion
    @Test(priority = 1, dependsOnMethods = "Bandeja")
    public void Refinanciacion() throws InterruptedException {
        Log.reportLog ( "Step 1 - Abrimos Bandeja Tareas e Iniciamos Entrevista" );
        Acciones acciones = new Acciones ( driver );

        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );


    }


}
