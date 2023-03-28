package TestCase.G415;

import Action.Grid;
import Config.BaseTest;
import Task.Menu;
import Task.*;
import Tools.Frame;
import Tools.SQLDatabaseConnection;
import Tools.Screenshot;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;


public class Gdem415Test extends BaseTest {


    @Test
    //Tests google calculator
    public void CierreNoContractual() throws InterruptedException, IOException, AWTException, SQLException {

        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( "SERPILLOE" );

        //Login
        Login login = new Login ( driver );
        login.Ingresar ( "QA" );

        Grid grid = new Grid ( driver );
        Menu menu = new Menu ( driver );
        Ejecutar ejecutar = new Ejecutar ( driver );
        BandejaTareas bandejaTareas = new BandejaTareas ( driver );
        CierreCuentas cierreCuentas = new CierreCuentas ( driver );
        Frame frame = new Frame ( driver );
        Screenshot screenshot = new Screenshot ( driver );

        //Ejecutar
        menu.Ejecutar ();

        //BandejaTareas
        ejecutar.Programa ( "hxwf900" );
        frame.BuscarFrame ( bandejaTareas.BTNOPOINICIAR );
        driver.findElement ( bandejaTareas.BTNOPOINICIAR ).click ();
        //Iniciar Cierre CtasCtes
        grid.rowSelectbyText ( bandejaTareas.Proceso_Tareas, "Cierre de Cuenta Vista" );
        driver.findElement ( bandejaTareas.BTNOPOINICIAR ).click ();

        //Pantalla Inicial Cierres Cta Ctes
        frame.BuscarFrame ( cierreCuentas.radioDecision );
        //Decisión de Cierre
        cierreCuentas.DecisionDeCierre ( 1 );
        //Motivo de Cierre
        cierreCuentas.MotivoDeCierre ( 2 );
        //Seleccione Cuenta - Buscar Cuenta
        cierreCuentas.BuscarCuenta ( "644710" );
        cierreCuentas.SeleccionarSubCuenta ( "2" );
        //Click Supervision
        frame.BuscarFrame ( cierreCuentas.btnSupervision );
        driver.findElement ( cierreCuentas.btnSupervision ).click ();
        //Cambiar usuario a Supervisor
        bd.CambiarUsuario ( "ROSSIF" );

        //Cerramos Sesion
        menu.Logout ();

        //Ingreso con nuevo Usuario
        login.Ingresar ( "QA" );
        //BandejaTareas
        menu.Ejecutar ();
        ejecutar.Programa ( "hxwf900" );
        //Retomo tarea
        frame.BuscarFrame ( bandejaTareas.Grilla_Tareas );
        grid.rowSelectbyFila ( bandejaTareas.Grilla_Tareas, bandejaTareas.PrimerTarea );
        driver.findElement ( bandejaTareas.BTNOPOTOMAR ).click ();
        grid.rowSelectbyFila ( bandejaTareas.Grilla_Tareas, bandejaTareas.PrimerTarea );
        driver.findElement ( bandejaTareas.BTNOPOEJECUTAR ).click ();

        screenshot.takeScreenshot ( "c:\\SemaforosSupervisor.png" );

        frame.BuscarFrame ( cierreCuentas.btnApruebaCierre );
        driver.findElement ( cierreCuentas.btnApruebaCierre ).click ();

        //Cambiar usuario a Centralizadora
        bd.CambiarUsuario ( "OJEDAM" );

        //Cerramos Sesion
        menu.Logout ();

        //Ingreso con nuevo Usuario
        login.Ingresar ( "QA" );
        //BandejaTareas
        menu.Ejecutar ();
        ejecutar.Programa ( "hxwf900" );
        //Retomo tarea
        frame.BuscarFrame ( bandejaTareas.Grilla_Tareas );
        grid.rowSelectbyFila ( bandejaTareas.Grilla_Tareas, bandejaTareas.PrimerTarea );
        driver.findElement ( bandejaTareas.BTNOPOTOMAR ).click ();
        //Cierre de CtaCte. Control de Productos Vinculados
        screenshot.takeScreenshot ( "c:\\SemaforosCentalizadora.png" );
        driver.findElement ( cierreCuentas.btnContinuaCierre ).click ();
        //Retomo tarea
        frame.BuscarFrame ( bandejaTareas.Grilla_Tareas );
        grid.rowSelectbyFila ( bandejaTareas.Grilla_Tareas, bandejaTareas.PrimerTarea );
        driver.findElement ( bandejaTareas.BTNOPOTOMAR ).click ();
        //Cierre MANUAL de Cuenta Vista
        screenshot.takeScreenshot ( "c:\\CierreManual.png" );

    }
}
