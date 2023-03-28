package Task;

import Page.MenuPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;

public class Menu {
    WebDriver driver;

    public Menu(WebDriver driver) {

        this.driver = driver;
    }

    public void Logout() {
        MenuPage menuPage = new MenuPage ( driver );
        ArrayList<String> tabs = new ArrayList<String> ( driver.getWindowHandles () );
        driver.switchTo ().window ( tabs.get ( 1 ) );

        driver.switchTo ().defaultContent ();
        driver.findElement ( menuPage.Logout ).click ();

        driver.switchTo ().window ( tabs.get ( 0 ) );
    }

    public void Ejecutar() {


        try {
            MenuPage menuPage = new MenuPage ( driver );
//            System.out.println ( "Driver cuando quiero hacer click en Menu Accesos " + driver.toString () );
            driver.findElement ( menuPage.Accesos ).click ();
//            System.out.println ( "Menu Acceso" );
            Thread.sleep ( 200 );
            driver.findElement ( menuPage.MenuEjecutar ).click ();
//            System.out.println ( "Menu Ejecutar" );


        } catch (Exception e) {
            System.out.println ( "No  se puedo entrar el menu Ejecutar, se detiene prueba" );
            Assert.fail ( e.toString () );

        }


    }

    public void WorkFlow() {
        MenuPage menuPage = new MenuPage ( driver );

        try {

            driver.findElement ( menuPage.WF ).click ();
//            System.out.println ( "Menu WorkFlow" );
            Thread.sleep ( 200 );

        } catch (Exception e) {
            System.out.println ( "No se encontro elemento Ejecutar" + e.getMessage () );
            Assert.fail ( e.toString () );
        }


    }

    public void BandejaTareas() {
        MenuPage menuPage = new MenuPage ( driver );

        try {

            driver.findElement ( menuPage.Inicio ).click ();
//            System.out.println ( "Menu Inicio" );
            Thread.sleep ( 200 );
            driver.findElement ( menuPage.WF ).click ();
//            System.out.println ( "Sub menu WorkFlow" );
            Thread.sleep ( 200 );
            driver.findElement ( menuPage.BandejaTareas ).click ();
//            System.out.println ( "Sub Menu Bandeja Tareas" );


        } catch (Exception e) {
            System.out.println ( "No se encontro elemento Ejecutar" + e.getMessage () );
            Assert.fail ( e.toString () );
        }


    }
}
