package TestCase;

import Config.BaseTest;
import Task.Login;
import org.testng.annotations.Test;
import java.lang.reflect.Method;


public class InicioTest extends BaseTest {


    @Test(priority = 0, description = "Prueba de Login correcto")
    public void Login(Method method) throws InterruptedException {


        Login login = new Login ( driver );
        login.Ingresar ( "QA" );

    }


}
