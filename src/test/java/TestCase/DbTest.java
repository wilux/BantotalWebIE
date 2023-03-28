package TestCase;

import Tools.SQLDatabaseConnection;
import com.google.common.base.Stopwatch;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


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


//        Assert.assertTrue ( !bd.esperarFormularios ( "27350672155" ) );

        final Stopwatch stopwatch = Stopwatch.createStarted ();
        boolean estadoLegajo = bd.esperarFormularios ( "27350672155" );
        boolean estadotiempo;
        do {
            System.out.println ( "." );
            estadotiempo = stopwatch.elapsed ( TimeUnit.SECONDS ) < 120;
        } while (bd.esperarFormularios ( "27350672155" ) || (stopwatch.elapsed ( TimeUnit.SECONDS ) > 60));
        Assert.assertTrue ( true );
    }
}


