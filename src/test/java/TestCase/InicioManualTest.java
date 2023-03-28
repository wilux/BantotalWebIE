package TestCase;

import Config.BaseTest;
import Task.Login;
import org.testng.annotations.Test;

import java.awt.*;
import java.lang.reflect.Method;


public class InicioManualTest extends BaseTest {


    @Test(priority = 0, description = "Prueba de Login con Credenciales dadas por usuario")
    //Tests google calculator
    public void Login(Method method) throws InterruptedException, AWTException {


        Login login = new Login ( driver );
        login.IngresarManual ( "QA" );

    }


}
