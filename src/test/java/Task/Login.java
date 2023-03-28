package Task;

import Config.Credenciales;
import Page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;


public class Login {
    WebDriver driver;


    public Login(WebDriver driver) {

        this.driver = driver;
    }


    public void setUserName(String strUserName) {
        LoginPage loginPage = new LoginPage ( driver );
        driver.findElement ( loginPage.UserInput ).sendKeys ( strUserName );

    }

    public void loginButton() throws AWTException, InterruptedException {
        LoginPage loginPage = new LoginPage ( driver );
        driver.findElement ( loginPage.LoginButton ).click ();
    }

    public void setPassword(String strPassword) {
        LoginPage loginPage = new LoginPage ( driver );
        driver.findElement ( loginPage.PasswordInput ).sendKeys ( strPassword );

    }

    public void cambiarVentana() {
//        LoginPage loginPage = new LoginPage ( driver );
//        driver.findElement ( loginPage.LoginButton ).click ();


        // To handle parent window
        String MainWindow = driver.getWindowHandle ();
//        System.out.println("Main window handle is " + MainWindow);

        // To handle child window
        Set<String> s1 = driver.getWindowHandles ();
//        System.out.println ( "Child window handle is" + s1 );
        for (String ChildWindow : s1) {
            if ( !MainWindow.equalsIgnoreCase ( ChildWindow ) ) {
                driver.switchTo ().window ( ChildWindow );
                //String pageTitle = driver.getTitle ();
                //System.out.println("The web page title of child window is:"+pageTitle);
                driver.manage ().window ().maximize ();
                break;
            }
        }


    }


    public void loginToBT(String strUserName, String strPassword) throws InterruptedException, AWTException {

        this.setUserName ( strUserName );
        this.setPassword ( strPassword );

        loginButton ();
        Thread.sleep ( 2000 );
        cambiarVentana ();
    }

    public void Ingresar(String ambiente) throws InterruptedException, AWTException {

        Credenciales credenciales = new Credenciales ();
//        System.out.println ( "Driver en Login " + driver.toString () );
        if ( ambiente.equals ( "DF" ) ) {

            driver.get ( "http://btdesafuncional.ar.bpn/BTWeb/hlogin.aspx" );
        }
        else {

            driver.get ( "http://btwebqa.ar.bpn/BTWeb/hlogin.aspx" );
        }


        // Copia el texto al portapapeles
        StringSelection user = new StringSelection ( credenciales.username );
        StringSelection pass = new StringSelection ( credenciales.password );
        Toolkit.getDefaultToolkit ().getSystemClipboard ().setContents ( user, null );

        // Espera 2 segundos para dar tiempo a que se copie el texto
        Thread.sleep ( 2000 );

        // Pega el texto en el campo de texto
        Robot robot = new Robot ();
        robot.keyPress ( KeyEvent.VK_CONTROL );
        robot.keyPress ( KeyEvent.VK_V );
        robot.keyRelease ( KeyEvent.VK_V );
        robot.keyRelease ( KeyEvent.VK_CONTROL );

        Thread.sleep ( 2000 );
        Toolkit.getDefaultToolkit ().getSystemClipboard ().setContents ( pass, null );
        robot.keyPress ( KeyEvent.VK_TAB );
        robot.keyRelease ( KeyEvent.VK_TAB );

        Thread.sleep ( 2000 );
        robot.keyPress ( KeyEvent.VK_CONTROL );
        robot.keyPress ( KeyEvent.VK_V );
        robot.keyRelease ( KeyEvent.VK_V );
        robot.keyRelease ( KeyEvent.VK_CONTROL );

        Thread.sleep ( 2000 );
        robot.keyPress ( KeyEvent.VK_ENTER );
        robot.keyRelease ( KeyEvent.VK_ENTER );

        Thread.sleep ( 5000 );


        setUserName ( credenciales.username );
        setPassword ( credenciales.password );


        loginButton ();
        cambiarVentana ();

    }


    public void IngresarManual(String ambiente) throws InterruptedException, AWTException {


        JTextField usuario = new JTextField ( 10 );
        JTextField password = new JPasswordField ( 10 );

        JPanel myPanel = new JPanel ();
        myPanel.add ( new JLabel ( "Usuario:" ) );
        myPanel.add ( usuario );
        myPanel.add ( Box.createHorizontalStrut ( 15 ) ); // a spacer
        myPanel.add ( new JLabel ( "Password:" ) );
        myPanel.add ( password );

        int result = JOptionPane.showConfirmDialog ( null, myPanel,
                                                     "Ingresa tus credenciales de BT para iniciar " +
                                                             "Prueba Automatizada", JOptionPane.OK_CANCEL_OPTION );
        if ( result == JOptionPane.OK_OPTION ) {


            if ( ambiente.equals ( "DF" ) ) {

                driver.get ( "http://btdesafuncional.ar.bpn/BTWeb/hlogin.aspx" );
            }
            else if ( ambiente.equals ( "QA" ) ) {

                driver.get ( "http://btwebqa.ar.bpn/BTWeb/hlogin.aspx" );
            }
            else {
                System.out.println ( "El Ambiente elegido " + ambiente + " no es valido, se asume QA" );
                System.out.println ( "Ambientes validos QA o DF" );
                driver.get ( "http://btwebqa.ar.bpn/BTWeb/hlogin.aspx" );
            }

            setUserName ( usuario.getText () );
            setPassword ( password.getText () );


            loginButton ();
            cambiarVentana ();
        }
        else {
            Assert.fail ( "Prueba no iniciada por falta de credenciales" );
        }

    }


}
