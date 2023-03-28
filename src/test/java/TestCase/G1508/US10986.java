package TestCase.G1508;

import Config.Acciones;
import Config.BaseTest;
import Page.EdicionPaquetePage;
import Task.AbmPaquetes;
import Tools.SQLDatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.lang.reflect.Method;
import java.sql.SQLException;

import static Tools.extentreports.ExtentTestManager.startTest;


public class US10986 extends BaseTest {


    @Test(priority = 1, description = "TASK 10972 - Agregar 3 campos  a ABM paquetes")
    public void IniciarAbmPaquetes(Method method) throws InterruptedException, AWTException, SQLException {

        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( "SERPILLOE" );

        Acciones acciones = new Acciones ( driver );
        AbmPaquetes abmPaquetes = new AbmPaquetes ( driver );
        EdicionPaquetePage edicionPaquetePage = new EdicionPaquetePage ( driver );

        acciones.login ().Ingresar ( "QA" );
        abmPaquetes.Iniciar ();
        abmPaquetes.Filtrar ( "9" );
        abmPaquetes.Estado ( "A" );
        abmPaquetes.SeleccionarFila ();
        abmPaquetes.Edicion ();

    }

    @Test(priority = 2, description = "TASK 10972 - Campo 1")
    public void ControlarCampoA(Method method) throws InterruptedException {

        Acciones acciones = new Acciones ( driver );
        EdicionPaquetePage edicionPaquetePage = new EdicionPaquetePage ( driver );

        //ExtentReports Description
        startTest ( method.getName (), "TASK 10972 - Controlando existencia de campo selectJBNYC5TCA" );

        Assert.assertTrue ( acciones.get ().Existe ( edicionPaquetePage.selectJBNYC5TCA ) );

    }

    @Test(priority = 3, description = "TASK 10972 - Campo 1")
    public void ControlarCampoB(Method method) throws InterruptedException {

        Acciones acciones = new Acciones ( driver );
        EdicionPaquetePage edicionPaquetePage = new EdicionPaquetePage ( driver );

        //ExtentReports Description
        startTest ( method.getName (), "TASK 10972 - Controlando existencia de campo selectJBNYC5TCA" );


        Assert.assertTrue ( acciones.get ().Existe ( edicionPaquetePage.input_JBNYC5POR ) );

    }

    @Test(priority = 4, description = "TASK 10972 - Campo 1")
    public void ControlarCampoC(Method method) throws InterruptedException {

        Acciones acciones = new Acciones ( driver );
        EdicionPaquetePage edicionPaquetePage = new EdicionPaquetePage ( driver );

        //ExtentReports Description
        startTest ( method.getName (), "TASK 10972 - Controlando existencia de campo selectJBNYC5TCA" );


        Assert.assertTrue ( acciones.get ().Existe ( edicionPaquetePage.input_JBNYC5TOA ) );
    }
}
