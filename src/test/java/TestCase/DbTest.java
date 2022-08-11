package TestCase;

import Page.LoginPage;
import Tools.SQLDatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.text.ParseException;


public class DbTest {


//    @Test
//    //Tests google calculator
//    public void Conection() throws InterruptedException {
//
//        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
//        bd.CambiarUsuario ( "BOUZASC" );
//
//    }


//    @Test
//    //Tests WC
//    public void WC() throws InterruptedException, ParseException {
//
//        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
//        bd.wc ( "27350672155" );
//
//    }


//    @Test
//    //Tests LD
//    public void LD() throws InterruptedException, SQLException {
//
//        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
//        bd.completarLD ( "27350672155" );
//
//    }


//    @Test
//    //Tests LD
//    public void VerificarFirmasDisponibles() throws InterruptedException, SQLException {
//
//        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
//        if ( bd.esperarFormularios ( "27350672155" ) != false ) {
//            Assert.assertTrue ( bd.esperarFormularios ( "27350672155" ) );
//        }
//        else {
//            Assert.assertFalse ( bd.esperarFormularios ( "27350672155" ) );
//        }
//
//    }


    @Test
    //Tests LD
    public void estadoEntrevistaWf() throws InterruptedException, SQLException {

        SQLDatabaseConnection bd = new SQLDatabaseConnection ();

        Assert.assertTrue ( bd.estadoEntrevistaWf ( "Aprobar propuesta", "1362163" ) );

    }
}


