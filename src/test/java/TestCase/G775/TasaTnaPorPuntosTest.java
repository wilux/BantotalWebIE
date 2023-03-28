package TestCase.G775;

import Config.Acciones;
import Config.BaseTest;
import Tools.SQLDatabaseConnection;
import Tools.Tna;
import Tools.logs.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class TasaTnaPorPuntosTest extends BaseTest {

    //Sin Paquete Existente
    String NroEntrevistaSinPaquete = "1283719";
    //Con Paquete Existente
    String NroEntrevistaConPaquete = "1283722";

    @BeforeTest
    public void IniciarSimulacion() throws InterruptedException, AWTException, SQLException {

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( "SERPILLOE" );

        //Logueamos
        acciones.login ().Ingresar ( "QA" );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );


    }


    @Test(priority = 0)
    public void TestSinPaqueteExistenteLlevaSinPaquete() throws InterruptedException {
        Log.reportLog ( "Simulacion Prestamo de un cliente con Sin Paquete Existente, llevando un " +
                                "MonoProducto sin puntos Particulares ni Generales" );

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Iniciamos simulacion para entrevista generada
        acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevistaSinPaquete );

        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevistaSinPaquete );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 0 -> CA. COMUN = Sin paquete
        acciones.simulacion ().Paquete ( paquetes.get ( 0 ) );
        Thread.sleep ( 5000 );


        double tnaSimulado = Double.parseDouble ( acciones.simulacion ().Tna () );

        Log.reportLog ( "La prueba es exitosa si la Tasa Base = TNA " );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "TNA Esperado = " + (tnaBase) );

        Assert.assertTrue ( tnaBase == tnaSimulado );

    }

    @Test(priority = 1)
    public void TestSinPaqueteLlevaPaqConPtosParticulares() throws InterruptedException {
        Log.reportLog ( "Simulacion Prestamo de un cliente con SinPaquete Existente, llevando un " +
                                "Paquete Con puntos Particulares y Generales" );
        Thread.sleep ( 3000 );
        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevistaSinPaquete );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 3 -> BPN SELECTO
        acciones.simulacion ().Paquete ( paquetes.get ( 3 ) );
        Thread.sleep ( 5000 );

        String sqlPuntosGrales = "select JBNYC5TABO from JBNYC5 where JBNYC5Desc='" + paquetes.get ( 3 ) + "'";
        String sqlPuntosParticulares = "select BNQFCD3Bon from  BNQFCD3 where BNQFCD3PQT=3 and BNQFCD3Top=201";

        double ptosGenerales = Double.parseDouble ( bd.getValue ( sqlPuntosGrales, "BPN_WEB_QA" ) );
        double ptosParticulares = Double.parseDouble ( bd.getValue ( sqlPuntosParticulares, "BPN_WEB_QA" ) );
        double tnaSimulado = Double.parseDouble ( acciones.simulacion ().Tna () );

        Log.reportLog ( "La prueba es exitosa si TNA Simulado = TASA Base - Ptos Particulares " );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "Ptos Particulares = " + ptosParticulares );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "TNA Esperado = " + (tnaBase - ptosParticulares) );

        Assert.assertTrue ( tnaSimulado == tnaBase - ptosParticulares );

    }

    @Test(priority = 2)
    public void TestSinPaqueteLlevaPaqConPuntosGralesSinPtoParticulares() throws InterruptedException {
        Log.reportLog ( "Simulacion Prestamo de un cliente Sin Paquete Existente, llevando un " +
                                "Prestamo con paquete Sin puntos Particulares solo Generales" );
        Thread.sleep ( 3000 );
        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevistaSinPaquete );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 4 -> BPN UNICO
        acciones.simulacion ().Paquete ( paquetes.get ( 4 ) );
        Thread.sleep ( 5000 );

        String sqlPuntosGrales = "select JBNYC5TABO from JBNYC5 where JBNYC5Desc='" + paquetes.get ( 4 ) + "'";
        String sqlPuntosParticulares = "select BNQFCD3Bon from  BNQFCD3 where BNQFCD3PQT=2 and BNQFCD3Top=201";

        double ptosGenerales = Double.parseDouble ( bd.getValue ( sqlPuntosGrales, "BPN_WEB_QA" ) );
        double tnaSimulado = Double.parseDouble ( acciones.simulacion ().Tna () );

        Log.reportLog ( "La prueba es exitosa si TNA Simulado = TASA Base - Ptos Generales " );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "Ptos Particulares = " + ptosGenerales );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "TNA Esperado = " + (tnaBase - ptosGenerales) );
        Assert.assertTrue ( tnaSimulado == tnaBase - ptosGenerales );

        //Dejo en Bandeja Tareas
        acciones.simulacion ().Cerrar ();
        //Iniciamos Retomo otro escenario para entrevista generada
        acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevistaConPaquete );

    }


    //Cliente tiene paquete BPN PLus Existente

    @Test(enabled = false) // Cambie las condiciones y no es valido el test
    public void TestConPaqueteLlevaPrestamoConMonoproductoSinPtosParticulares() throws InterruptedException {
        Log.reportLog ( "Simulacion Prestamo de un cliente con Paquete Existente, llevando un " +
                                "MonoProducto sin puntos Particulares solo Generales" );
        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );


        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevistaConPaquete );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 0 -> CA. COMUN = Sin paquete
        acciones.simulacion ().Paquete ( paquetes.get ( 0 ) );
        Thread.sleep ( 5000 );

        String sqlPuntosGrales = "select JBNYC5TABO from JBNYC5 where JBNYC5Desc='BPN PLUS'";
        String sqlPuntosParticulares = "select BNQFCD3Bon from  BNQFCD3 where BNQFCD3PQT=0 and BNQFCD3Top=201";

        double ptosGenerales = Double.parseDouble ( bd.getValue ( sqlPuntosGrales, "BPN_WEB_QA" ) );
        double ptosParticulares = 0.0;
        try {
            ptosParticulares = Double.parseDouble ( bd.getValue ( sqlPuntosParticulares, "BPN_WEB_QA" ) );
        } catch (Exception e) {

        }
        double tnaSimulado = Double.parseDouble ( acciones.simulacion ().Tna () );

        Log.reportLog ( "La prueba es exitosa si TNA Simulado = TASA Base - Ptos Generales " );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "Ptos Generales = " + ptosGenerales );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "TNA Esperado = " + (tnaBase - ptosGenerales) );

        Assert.assertTrue ( tnaSimulado == tnaBase - ptosGenerales );

    }

    @Test(priority = 4)
    public void TestConPaqueteConPtosParticularesHaceUpgradeConPtosParticulares() throws InterruptedException {
        Log.reportLog ( "Simulacion Prestamo de un cliente con Paquete Existente, llevando un " +
                                "upgrade de Paquete con puntos Particulares para la linea" );
        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevistaConPaquete );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 3 -> BPN SELECTO
        acciones.simulacion ().Paquete ( paquetes.get ( 3 ) );
        Thread.sleep ( 5000 );

        String sqlPuntosGrales = "select JBNYC5TABO from JBNYC5 where JBNYC5Desc='" + paquetes.get ( 3 ) + "'";
        String sqlPuntosParticulares = "select BNQFCD3Bon from  BNQFCD3 where BNQFCD3PQT=3 and BNQFCD3Top=201";

        double ptosGenerales = Double.parseDouble ( bd.getValue ( sqlPuntosGrales, "BPN_WEB_QA" ) );
        double ptosParticulares = Double.parseDouble ( bd.getValue ( sqlPuntosParticulares, "BPN_WEB_QA" ) );
        double tnaSimulado = Double.parseDouble ( acciones.simulacion ().Tna () );

        Log.reportLog ( "La prueba es exitosa si TNA Simulado = TASA Base - Ptos Particulares " );
        Log.reportLog ( "Tasa Base = " + tnaBase );
        Log.reportLog ( "Ptos Particulares = " + ptosParticulares );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "TNA Esperado = " + (tnaBase - ptosParticulares) );

        Assert.assertTrue ( tnaSimulado == tnaBase - ptosParticulares );

    }

    @Test(priority = 5)
    public void TestConPaqueteConPuntosHaceUpgradePaqSinPtoParticulares() throws InterruptedException {
        Log.reportLog ( "Simulacion Prestamo de un cliente con Paquete Existente, llevando un " +
                                "upgrade de Paquete sin puntos Particulares para la linea solo Generales" );

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevistaConPaquete );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 4 -> BPN UNICO
        acciones.simulacion ().Paquete ( paquetes.get ( 4 ) );
        Thread.sleep ( 5000 );

        String sqlPuntosGrales = "select JBNYC5TABO from JBNYC5 where JBNYC5Desc='" + paquetes.get ( 4 ) + "'";
        String sqlPuntosParticulares = "select BNQFCD3Bon from  BNQFCD3 where BNQFCD3PQT=2 and BNQFCD3Top=201";

        double ptosGenerales = Double.parseDouble ( bd.getValue ( sqlPuntosGrales, "BPN_WEB_QA" ) );
        double tnaSimulado = Double.parseDouble ( acciones.simulacion ().Tna () );

        Log.reportLog ( "La prueba es exitosa si TNA Simulado = TASA Base - Ptos Generales " );
        Log.reportLog ( "Tasa Base = " + tnaBase );
        Log.reportLog ( "Ptos Generales = " + ptosGenerales );
        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "TNA Esperado = " + (tnaBase - ptosGenerales) );

        Assert.assertTrue ( tnaSimulado == tnaBase - ptosGenerales );

    }


    @Test(priority = 6)
    public void TestConPaqueteCasoBug() throws InterruptedException {
        Log.reportLog ( "Simulacion Prestamo de un cliente con Paquete Existente, llevando un " +
                                "upgrade de Paquete sin puntos Particulares para la linea solo Generales" );

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );


        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevistaConPaquete );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete CA Comun
        acciones.simulacion ().Paquete ( paquetes.get ( 0 ) );
        Thread.sleep ( 5000 );

        //Elegimos Paquete PLus
        acciones.simulacion ().Paquete ( paquetes.get ( 2 ) );
        Thread.sleep ( 5000 );

        //Elegimos Ca Comun
        acciones.simulacion ().Paquete ( paquetes.get ( 0 ) );
        Thread.sleep ( 5000 );

        String sqlPuntosGrales = "select JBNYC5TABO from JBNYC5 where JBNYC5Desc='" + paquetes.get ( 2 ) + "'";
        String sqlPuntosParticulares = "select BNQFCD3Bon from  BNQFCD3 where BNQFCD3PQT=2 and BNQFCD3Top=201";

        double ptosGenerales = Double.parseDouble ( bd.getValue ( sqlPuntosGrales, "BPN_WEB_QA" ) ); //5
        double tnaSimulado = Double.parseDouble ( acciones.simulacion ().Tna () );
        double ptosParticulares = Double.parseDouble ( bd.getValue ( sqlPuntosParticulares, "BPN_WEB_QA" ) ); //1

        Log.reportLog ( "La prueba es exitosa si TNA Simulado = TASA Base - Ptos Generales " );
        Log.reportLog ( "Tasa Base = " + tnaBase );
        Log.reportLog ( "Ptos Generales = " + ptosGenerales );
        Log.reportLog ( "Ptos Particulares = " + ptosParticulares );

        Log.reportLog ( "TNA Simulado = " + tnaSimulado );
        Log.reportLog ( "TNA Esperado = " + (tnaBase - ptosParticulares) );

        Assert.assertTrue ( tnaSimulado == tnaBase - ptosParticulares );

    }
}
