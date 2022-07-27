package Config;


import Tools.logs.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.swing.*;


public abstract class BaseTest {

    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver newDriver) {
        driver = newDriver;
    }


    @BeforeSuite
    public void before() {
        Log.info ( "Tests is starting!" );
        System.setProperty ( "webdriver.ie.driver", "webdriver/IEDriverServer.exe" );
        driver = new InternetExplorerDriver ();
    }


    @AfterSuite
    public void after() {
//        Desktop.getDesktop ().open ( new File ( "TestReport/Test-Automaton-Report.html" ) );
        Log.info ( "Tests are ending!" );
//        if ( driver != null ) {
//            driver.quit ();
//        }

        JFrame jf = new JFrame ();
        jf.setAlwaysOnTop ( true );
        int reply = JOptionPane.showConfirmDialog ( jf, "¿Queres Cerrar Navegador?", "Test Terminado",
                                                    JOptionPane.YES_NO_OPTION );

        if ( reply == JOptionPane.YES_OPTION ) {
            driver.quit ();

        }
        else {
            System.exit ( 0 );
        }
    }


}