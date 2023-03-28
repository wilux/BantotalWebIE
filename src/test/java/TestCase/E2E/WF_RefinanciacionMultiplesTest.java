package TestCase.E2E;

import Config.Acciones;
import Config.BaseTest;
import Task.*;
import Tools.Restart;
import Tools.SQLDatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;


public class WF_RefinanciacionMultiplesTest extends BaseTest {

    //Datos del caso
    String NroEntrevista = "";
    String usuarioPlataforma = "SERPILLOE";
    String usuarioRecupero = "ROJASM";
    String usuarioCentral = "LOMBERAM";


    @BeforeTest
    public void login() throws InterruptedException, AWTException, SQLException {

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( usuarioPlataforma );


        //Logueamos
        acciones.login ().Ingresar ( "QA" );


    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"20218926364"}, {"27346671454"}};
    }


    @Test(dataProvider = "data-provider")
    public void entrevista(String cuil) throws InterruptedException, AWTException, SQLException {
        Acciones acciones = new Acciones ( driver );
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();


        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().iniciarEntrevista ( "Entrevista / Identificación" );


        //Ingresar Tipo y Documento

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


        //Reiniciamos con nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioRecupero );

        RefinanciacionAprobarPropuesta refinanciacionAprobarPropuesta = new RefinanciacionAprobarPropuesta ( driver );

        //Abrir bandeja
        //Menu Ejecutar
        acciones = new Acciones ( driver );
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


        //Reiniciamos con nuevo usuario
        driver = restart.As ( usuarioPlataforma );
        RefinanciacionValidarPropuesta refinanciacionValidarPropuesta = new RefinanciacionValidarPropuesta ( driver );
        //Abrir bandeja
        acciones = new Acciones ( driver );
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


        //Reiniciamos con nuevo usuario

        driver = restart.As ( usuarioCentral );

        RefinanciacionControlaDoc refinanciacionControlaDoc = new RefinanciacionControlaDoc ( driver );
        //Abrir bandeja
        acciones = new Acciones ( driver );
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


    @AfterTest
    public void after() {
        driver.quit ();
    }

}
