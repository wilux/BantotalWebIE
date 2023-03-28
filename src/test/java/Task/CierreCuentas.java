package Task;

import Action.Grid;
import Page.CierredeCuentasVistasPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CierreCuentas extends CierredeCuentasVistasPage {
    WebDriver driver;

    public CierreCuentas(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }


    public void DecisionDeCierre(int value) {
        //value 1 o 2
        driver.findElement ( radioDecision ).click ();
        driver.findElement ( By.cssSelector ( "input[type='radio'][value='" + value + "']" ) ).click ();
    }

    public void MotivoDeCierre(int value) {
        //value 1 a 4
        driver.findElement ( radioMotivo ).click ();
        driver.findElement ( By.cssSelector ( "input[type='radio'][value='" + value + "']" ) ).click ();
    }

    public void BuscarCuenta(String cuenta) {
        driver.findElement ( inputCuenta ).sendKeys ( cuenta );
        driver.findElement ( btnBuscar ).click ();
    }

    public void SeleccionarSubCuenta(String subcuenta) {
        Grid grid = new Grid ( driver );
        grid.rowSelectbyText ( gridCuentas, subcuenta );
        driver.findElement ( btnSelCuenta ).click ();
    }


}


