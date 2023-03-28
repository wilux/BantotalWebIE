package Page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {
    public By Logo = By.id ( "logo" );
    //Menu General
    public By Menu = By.id ( "menuBase" );
    //Acceso
    public By Accesos = By.xpath ( "//a[@init='m0_1']" );
    //Acceso -> Ejecutar
    public By MenuEjecutar = By.xpath ( "//a[@class='leafCompementWithShortcut']" );
    //Inicio
    public By Inicio = By.xpath ( "//a[@init='m0_0']" );
    //Inicio -> WorkFlow
    public By WF = By.xpath ( "//a[@init='m1_0']" );
    //Inicio -> WorkFlow -> BANDEJA DE TAREAS
    public By BandejaTareas = By.xpath ( "//a[.='BANDEJA DE TAREAS']" );
    public By _PROGRAMA = By.id ( "_PROGRAMA" );
    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );
    //Logout
    public By Logout = BandejaTareas = By.xpath ( "//a[.='Salir']" );
    WebDriver driver;

    public MenuPage(WebDriver driver) {

        this.driver = driver;

    }

}


