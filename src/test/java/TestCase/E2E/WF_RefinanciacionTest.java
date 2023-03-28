package TestCase.E2E;

import Config.Acciones;
import Config.BaseTest;
import Task.*;
import Tools.Restart;
import Tools.SQLDatabaseConnection;
import Tools.logs.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;


public class WF_RefinanciacionTest extends BaseTest {

    //Datos del caso
    String cuil = "27333930116";
    String NroEntrevista = "";
    String usuarioPlataforma = "SERPILLOE";
    String usuarioRecupero = "ROJASM";
    String usuarioCentral = "LOMBERAM";


    @BeforeTest
    public void login() throws InterruptedException, AWTException, SQLException {
        Log.reportLog ( "Step 0 - Abrimos BT y logueamos" );
        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( usuarioPlataforma );


        //Logueamos
        acciones.login ().Ingresar ( "QA" );


    }


    @Test
    public void entrevista(String cuil) throws InterruptedException, AWTException, SQLException {
        Log.reportLog ( "Step 1 - Abrimos Bandeja Tareas e Iniciamos Entrevista" );
        Acciones acciones = new Acciones ( driver );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().iniciarEntrevista ( "Entrevista / Identificación" );


        //Ingresar Tipo y Documento
        Log.reportLogScreen ( driver );
        acciones.entrevista ().IdentificacionPersona ( "C.U.I.L.", cuil );
        //Entrevista
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
    @Test(priority = 1, dependsOnMethods = "entrevista")
    public void refinanciacion() throws InterruptedException {
        Log.reportLog ( "Step 2 - Seleccionamos productos a Refinanciar" );
        Acciones acciones = new Acciones ( driver );
        RefinanciacionSeleccionProductos refinanciacionSeleccionProductos =
                new RefinanciacionSeleccionProductos ( driver );
        //Abrir/Retomar Entrevista Nueva
        acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevista );

        //Seleccionar Productos
        refinanciacionSeleccionProductos.seleccionarTodaslasCuentas ();
        refinanciacionSeleccionProductos.seleccionarTodaslasTarjetas ();
        refinanciacionSeleccionProductos.seleccionarTodosPrestamo ();
        refinanciacionSeleccionProductos.actualizarTotal ();
        refinanciacionSeleccionProductos.cuentaDesembolso ();
        refinanciacionSeleccionProductos.cuentaCobro ();
        refinanciacionSeleccionProductos.refinanciar ();


    }


    //Datos Generales Amortizable
    @Test(priority = 2, dependsOnMethods = "refinanciacion")
    public void datosAmortizables() throws InterruptedException, AWTException, SQLException {
        Log.reportLog ( "Step 3 - Datos Generales Amortizable" );
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        RefinanciacionDatosGenerales refinanciacionDatosGenerales = new RefinanciacionDatosGenerales ( driver );
        refinanciacionDatosGenerales.cantidadCuotas ( "48" );
        refinanciacionDatosGenerales.confirmar ();
        //Seguro
        refinanciacionDatosGenerales.selecionarSeguro ();
        refinanciacionDatosGenerales.confirmarSeguro ();
        //Plan de Pagos
        refinanciacionDatosGenerales.confirmarPlanPago ();
        Thread.sleep ( 5000 );
        Assert.assertTrue ( bd.estadoEntrevistaWf ( "Aprobar propuesta", NroEntrevista ) );
    }

    //Retomar desde Recupero
    //Aprobar Propuesta
    @Test(priority = 3, dependsOnMethods = "datosAmortizables")
    public void retomaRecuperoAprobarPropuesta() throws InterruptedException, AWTException, SQLException {
        Log.reportLog ( "Step 4 - Recupero: Aprobar Propuesta" );
        //Reiniciamos con nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioRecupero );
        Acciones acciones = new Acciones ( driver );
        RefinanciacionAprobarPropuesta refinanciacionAprobarPropuesta = new RefinanciacionAprobarPropuesta ( driver );
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        //Abrir bandeja
        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().tomarEntrevista ( NroEntrevista );
        acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevista );
        Thread.sleep ( 2000 );
        //Aprobar tramite
        refinanciacionAprobarPropuesta.aprobar ();
        Thread.sleep ( 5000 );
        Assert.assertTrue ( bd.estadoEntrevistaWf ( "Validar propuesta", NroEntrevista ) );

    }


    //Retomar desde Gerente
    //Valida Propuesta
    @Test(priority = 4, dependsOnMethods = "retomaRecuperoAprobarPropuesta")
    public void retomaGerente() throws InterruptedException, AWTException, SQLException {
        Log.reportLog ( "Step 5 - Gerente: Valida Propuesta" );
        //Reiniciamos con nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioPlataforma );
        Acciones acciones = new Acciones ( driver );
        RefinanciacionValidarPropuesta refinanciacionValidarPropuesta = new RefinanciacionValidarPropuesta ( driver );
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        //Abrir bandeja
        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );
        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().tomarEntrevista ( NroEntrevista );
        acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevista );

        //Confirmar tramite
        refinanciacionValidarPropuesta.confirmar ();
        Thread.sleep ( 5000 );
        Assert.assertTrue ( bd.estadoEntrevistaWf ( "Controlar documentacion", NroEntrevista ) );


    }


    //Retomar desde Centralizadora
    //Valida Propuesta
    @Test(priority = 5, dependsOnMethods = "retomaGerente")
    public void retomaCentralizadora() throws InterruptedException, AWTException, SQLException {

        Log.reportLog ( "Step 6 - Centralizadora: Valida Propuesta" );
        //Reiniciamos con nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioCentral );
        Acciones acciones = new Acciones ( driver );
        RefinanciacionControlaDoc refinanciacionControlaDoc = new RefinanciacionControlaDoc ( driver );
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        //Abrir bandeja
        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );
        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().tomarEntrevista ( NroEntrevista );
        acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevista );

        //Confirmar tramite
        refinanciacionControlaDoc.confirmar ();
        //Fin tramite.
        Thread.sleep ( 5000 );
        Assert.assertTrue ( bd.estadoEntrevistaWf ( "Proceso de cancelacion", NroEntrevista ) );


    }

}
