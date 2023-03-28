package Action;

import Tools.Frame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckBox {
    WebDriver driver;


    public CheckBox(WebDriver driver) {

        this.driver = driver;


    }


    public void Check(By locator) {
        Frame frame = new Frame ( driver );

        if ( frame.BuscarFrame ( locator ) ) {
            WebElement checkBoxSelected = driver.findElement ( locator );
            WebElement checkBoxDisplayed = driver.findElement ( locator );

            boolean isSelected = checkBoxSelected.isSelected ();
            boolean isDisplayed = checkBoxDisplayed.isDisplayed ();

            if ( !isSelected && isDisplayed ) {
                checkBoxSelected.click ();
            }
            else {
                Assert.fail ( "No hay checkbox" );
            }
        }
        else {
            System.out.println ( "No se encontro " + locator );
        }

    }


    public void UnCheck(By locator) {
        Frame frame = new Frame ( driver );

        if ( frame.BuscarFrame ( locator ) ) {
            WebElement checkBoxSelected = driver.findElement ( locator );
            WebElement checkBoxDisplayed = driver.findElement ( locator );

            boolean isSelected = checkBoxSelected.isSelected ();
            boolean isDisplayed = checkBoxDisplayed.isDisplayed ();

            if ( isSelected && isDisplayed ) {
                checkBoxSelected.click ();
            }
            else {
                Assert.fail ( "No hay checkbox" );
            }
        }
        else {
            System.out.println ( "No se encontro " + locator );
        }
    }
}

