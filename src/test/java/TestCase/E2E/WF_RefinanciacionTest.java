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


public class WF_RefinanciacionTest extends BaseTest {

    //Datos del caso
    String cuil = "23298617889  ";
    String NroEntrevista = "1362161";
    String usuarioPlataforma = "SERPILLOE";
    String usuarioRecupero = "ROJASM";
    String usuarioGerencia = "RODRIGUA";
    String usuarioCentral = "LOMBERAM";


    //Iniciar Entrevista
    @Test(priority = 0)
    public void bandeja() throws InterruptedException, AWTException {
        Log.reportLog ( "Step 1 - Abrimos Bandeja Tareas e Iniciamos Entrevista" );
        Acciones acciones = new Acciones ( driver );
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();

        //Logueamos
        acciones.login ().Ingresar ( "QA" );

        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( usuarioPlataforma );

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

    //retomar
    //Iniciar Refinanciacion
    @Test(priority = 1, dependsOnMethods = "bandeja")
    public void refinanciacion() throws InterruptedException {
        Log.reportLog ( "Step 2 - Seleccionamos productos a Refinanciar" );
        Acciones acciones = new Acciones ( driver );
        RefinanciacionSeleccionProductos refinanciacionSeleccionProductos =
                new RefinanciacionSeleccionProductos ( driver );
        //Abrir/Retomar Entrevista Nueva
        acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );

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
    public void datosAmortizables() throws InterruptedException, AWTException {
        Log.reportLog ( "Step 3 - Datos Generales Amortizable" );
        RefinanciacionDatosGenerales refinanciacionDatosGenerales = new RefinanciacionDatosGenerales ( driver );
        refinanciacionDatosGenerales.cantidadCuotas ( "48" );
        refinanciacionDatosGenerales.confirmar ();
        //Seguro
        refinanciacionDatosGenerales.selecionarSeguro ();
        refinanciacionDatosGenerales.confirmarSeguro ();
        //Plan de Pagos
        refinanciacionDatosGenerales.confirmarPlanPago ();

        //Termina y pasa a usuario de Recupero
    }

    //Retomar desde Recupero
    //Aprobar Propuesta
    @Test(priority = 3, dependsOnMethods = "datosAmortizables")
    public void retomaRecuperoAprobarPropuesta() throws InterruptedException, AWTException {
        Thread.sleep ( 5000 );
        Log.reportLog ( "Step 4 - Recupero: Aprobar Propuesta" );
        //Reiniciamos con nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioRecupero );
        Acciones acciones = new Acciones ( driver );
        RefinanciacionAprobarPropuesta refinanciacionAprobarPropuesta = new RefinanciacionAprobarPropuesta ( driver );
        //Abrir bandeja
        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );

        //Aprobar tramite
        refinanciacionAprobarPropuesta.aprobar ();
        //Tramite termina y pasa a otra instancia

    }


    //Retomar desde Gerente
    //Valida Propuesta
    @Test(priority = 4, dependsOnMethods = "retomaRecuperoAprobarPropuesta")
    public void retomaGerente() throws InterruptedException, AWTException {
        Thread.sleep ( 5000 );
        Log.reportLog ( "Step 5 - Gerente: Valida Propuesta" );
        //Reiniciamos con nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioGerencia );
        Acciones acciones = new Acciones ( driver );
        RefinanciacionValidarPropuesta refinanciacionValidarPropuesta = new RefinanciacionValidarPropuesta ( driver );
        //Abrir bandeja
        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );
        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );

        //Confirmar tramite
        refinanciacionValidarPropuesta.confirmar ();
        //Tramite termina y pasa a otra instancia


    }


    //Retomar desde Centralizadora
    //Valida Propuesta
    @Test(priority = 5, dependsOnMethods = "retomaGerente")
    public void retomaCentralizadora() throws InterruptedException, AWTException {
        Thread.sleep ( 5000 );
        Log.reportLog ( "Step 6 - Centralizadora: Valida Propuesta" );
        //Reiniciamos con nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioCentral );
        Acciones acciones = new Acciones ( driver );
        RefinanciacionControlaDoc refinanciacionControlaDoc = new RefinanciacionControlaDoc ( driver );
        //Abrir bandeja
        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );
        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );

        //Confirmar tramite
        refinanciacionControlaDoc.confirmar ();
        //Fin tramite.
        // Se tiene que acreditar prestamo por total, despaquetizar cuenta, etc..


    }

}
