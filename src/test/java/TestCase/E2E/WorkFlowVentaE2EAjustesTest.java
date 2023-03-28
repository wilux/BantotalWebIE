package TestCase.E2E;

import Config.Acciones;
import Config.BaseTest;
import Tools.Restart;
import Tools.SQLDatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class WorkFlowVentaE2EAjustesTest extends BaseTest {

    //Datos del caso
    String cuil = "20237452608"; //"27350672155 ";
    String NroEntrevista = "";
    String usuarioPlataforma = "SERPILLOE";
    String usuarioGerencia = "RODRIGUA";
    String usuarioCreditos = "PIANCIOLAG";
    String usuarioCentral = "LOMBERAM";


    @BeforeTest
    public void IniciarSimulacion() throws InterruptedException, AWTException, SQLException {

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
        System.out.println ( "Driver Inicial " + driver.toString () );

    }

    @Test(priority = 0, enabled = true)
    //@Test(priority = 0)
    public void Bandeja() throws InterruptedException {
        Acciones acciones = new Acciones ( driver );

        //Abrir Entrevista Nueva
        acciones.bandejaTareas ().iniciarEntrevista ( "Entrevista / Identificación" );


        //Ingresar Tipo y Documento
        acciones.entrevista ().IdentificacionPersona ( "C.U.I.L.", cuil );
        //Entrevista
        acciones.entrevista ().CompletarGenerico ();
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
            acciones.bandejaTareas ().siguienteEntrevista ( NroEntrevista );
            acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevista );
        }

        //Asser si termino en pantalla "Simulacion"
        Assert.assertTrue ( acciones.simulacion ().simulacionVisible () );


    }

    @Test(priority = 1, enabled = true, dependsOnMethods = "Bandeja")
    public void Simulacion() throws InterruptedException {


        Acciones acciones = new Acciones ( driver );


        //Linea
        acciones.simulacion ().Linea ( "309200" ); //309200003

        //Monto
        acciones.simulacion ().Monto ( "1000" );

        //Plazo
        acciones.simulacion ().Plazo ( "12" );

        //Destino
        acciones.simulacion ().DestinoFondos ( "1" );

        //Tarjetas Paquete
        acciones.simulacion ().Tarjetas ( 0 );

        //Simular
        acciones.simulacion ().Simular ();

        //Confirmar
        acciones.simulacion ().Confirmar ();

        Assert.assertTrue ( acciones.get ().Existe ( acciones.simulacion ().BTNOPCOMISION ) );

    }

    @Test(priority = 2, enabled = true, dependsOnMethods = "Simulacion")
    public void ConfirmarPlanDePagos() throws InterruptedException {

        Acciones acciones = new Acciones ( driver );

        acciones.simulacion ().ConfirmarPlanPago ();
        Assert.assertTrue ( acciones.get ().Existe ( acciones.simulacion ().BTNOPCARTASIMULADOR ) );
    }

    @Test(priority = 3, enabled = true, dependsOnMethods = "ConfirmarPlanDePagos")
    public void ConfirmarSimulacion() throws InterruptedException {

        Acciones acciones = new Acciones ( driver );

        acciones.simulacion ().ConfirmarSimulacion ();
        Assert.assertTrue ( acciones.bandejaTareas ().bandejaVisible () );
    }

    @Test(priority = 4, enabled = true, dependsOnMethods = "ConfirmarSimulacion")
    public void CargaAvanzada() throws InterruptedException, ParseException, SQLException {


        Acciones acciones = new Acciones ( driver );
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();

        //Remotar tramite
        //acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );
        acciones.bandejaTareas ().tomarEntrevista ( NroEntrevista );


        //Hacer World Complement
        bd.wc ( cuil );

        //Confirmar

        acciones.cargaAvanzada ().Aceptar ();

        Assert.assertTrue ( acciones.bandejaTareas ().bandejaVisible () );
    }

    @Test(priority = 5, enabled = true, dependsOnMethods = "CargaAvanzada")
    public void Reutilizacion() throws InterruptedException {


        Acciones acciones = new Acciones ( driver );

        //Remotar tramite
        acciones.bandejaTareas ().tomarEntrevista ( NroEntrevista );

        //Aceptar TD
        acciones.reutilizacion ().AceptarTarjetaDebito ();


        //Entrar a Perfil Riesgo
        acciones.reutilizacion ().PerfilRiesgo ();

        Assert.assertTrue ( acciones.get ().Existe ( acciones.matrizRiesgo ().GRIDSUBINTEGRANTES ) );
    }

    @Test(priority = 6, enabled = true, dependsOnMethods = "Reutilizacion")
    public void MatrizRiesgo() throws InterruptedException, AWTException, SQLException {


        Acciones acciones = new Acciones ( driver );
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();

        //Aceptar Perfil Riesgo
        acciones.matrizRiesgo ().Confirmar ();


        //Esperar que los formularios esten para completar
        // Cuando aparece Bandeja tareas es pq termino el proceso
        Assert.assertTrue ( acciones.bandejaTareas ().bandejaVisible () );

    }

    @Test(priority = 7, enabled = true, dependsOnMethods = "MatrizRiesgo")
    public void RevisionGerente() throws InterruptedException, SQLException, AWTException {


        //Firmamos LD
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        bd.completarLD ( cuil );

        //Cerrar y entrar como nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioGerencia );

        System.out.println ( "Driver Gerencia " + driver.toString () );
        //Instanciamos clases que usaremos
        Acciones acciones = new Acciones ( driver );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );


        //Remotar tramite
        acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );

        //Confirmo Revision de Productos
        acciones.revisionProductos ().Confirmar ();


        Assert.assertTrue ( true );
    }


    //Solo cuando excede Limite sino se saltar paso
    @Test(priority = 8, enabled = false, dependsOnMethods = "RevisionGerente")
    public void RevisionCreditos() throws InterruptedException, SQLException, AWTException {


        System.out.println ( "Driver Creditos " + driver.toString () );

        Thread.sleep ( 5000 );
        //Reiniciamos con nuevo usuario
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioCreditos );

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Remotar tramite
        acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );

        //Confirmo Revision de Productos
        acciones.revisionProductos ().Confirmar ();


        Assert.assertTrue ( true );
    }

    @Test(priority = 9, enabled = true, dependsOnMethods = "RevisionGerente")
    public void Liquidacion() throws InterruptedException, SQLException, AWTException {


        //Reiniciamos con nuevo usuario
        Thread.sleep ( 5000 );
        Restart restart = new Restart ( driver );
        driver = restart.As ( usuarioCentral );
        Thread.sleep ( 5000 );
        System.out.println ( "Driver Central " + driver.toString () );
        //Instanciamos clases que usaremos
        Acciones acciones = new Acciones ( driver );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Remotar tramite
        acciones.bandejaTareas ().avanzarEntrevista ( NroEntrevista );

        //Confirmo Revision de Productos
        acciones.revisionParaConfirmar ().Confirmar ();

        //Perfil Riesgo

        acciones.revisionParaConfirmar ().PerfilRiesgo ();

        //Confirmar Matriz Final

        acciones.matrizRiesgo ().ConfirmarFinal ();

        //Liquidar

        acciones.revisionParaConfirmar ().Liquidar ();

        Assert.assertTrue ( true );
    }

    @Test(priority = 10, enabled = true, dependsOnMethods = "Liquidacion")
    public void PlandePagos() throws InterruptedException, SQLException {

        Acciones acciones = new Acciones ( driver );

        acciones.planPagosAmortizables ().Confirmar ();


        acciones.planPagosAmortizables ().VinculaGarantia ();


        //Finalizar

        acciones.revisionParaConfirmar ().Finalizar ();

        Assert.assertTrue ( true );
    }

}
