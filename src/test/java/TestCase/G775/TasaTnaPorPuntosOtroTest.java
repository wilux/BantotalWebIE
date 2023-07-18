package TestCase.G775;

import Config.Acciones;
import Config.BaseTest;
import Tools.SQLDatabaseConnection;
import Tools.Tna;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class TasaTnaPorPuntosOtroTest extends BaseTest {

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
    public void TestConPaqueteCambios() throws InterruptedException, AWTException {


        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

        //Iniciamos simulacion para entrevista generada
        acciones.bandejaTareas ().ejecutarEntrevista ( NroEntrevistaConPaquete );

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


        Assert.assertEquals ( tnaBase - ptosParticulares, tnaSimulado );

    }
}
