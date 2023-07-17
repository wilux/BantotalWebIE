package Task;

import Config.Credenciales;
import Page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Login {
    WebDriver driver;


    public Login(WebDriver driver) {

        this.driver = driver;
    }


    public void setUserName(String strUserName) {
        LoginPage loginPage = new LoginPage ( driver );
        driver.findElement ( loginPage.UserInput ).sendKeys ( strUserName );

    }

    public void loginButton() throws  InterruptedException {
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


    public void cambiarUsuario(String strUserName) {
        // Setup Edge driver via WebDriverManager
        WebDriverManager.edgedriver ().setup ();

        // Create a new instance of Edge driver
        WebDriver driver = new EdgeDriver ();

        // Create a new instance of EdgeOptions
        EdgeOptions options = new EdgeOptions ();
        options.addArguments ( "--headless" );

//        // Create a new instance of Edge driver with options
//        WebDriver driver = new EdgeDriver(options);

        driver.manage ().timeouts ().implicitlyWait ( 10, TimeUnit.SECONDS );

        // Navigate to Google.com
        driver.get ( "http://chngusrqa.ar.bpn/" );


        driver.findElement ( By.cssSelector ( "#edUsuarioBT" ) ).clear ();
        driver.findElement ( By.cssSelector ( "#edUsuarioBT" ) ).sendKeys ( strUserName );
        driver.findElement ( By.cssSelector ( "#btAceptar" ) ).click ();

        // Close the browser
        driver.quit ();
    }


    public void Ingresar(String ambiente) throws InterruptedException {

        Credenciales credenciales = new Credenciales ();
//        System.out.println ( "Driver en Login " + driver.toString () );
        if ( ambiente.equals ( "DF" ) ) {

            driver.get ( "http://btdesafuncional.ar.bpn/BTWeb/hlogin.aspx" );
        }
        else {

            driver.get ( "http://btwebqa.ar.bpn/BTWeb/hlogin.aspx" );
        }


        setUserName ( credenciales.username );
        setPassword ( credenciales.password );

        loginButton ();
        cambiarVentana ();


    }


}
