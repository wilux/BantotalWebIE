package Page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EjecutarPage {
    public By _PROGRAMA = By.id ( "_PROGRAMA" );
    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );
    WebDriver driver;

    public EjecutarPage(WebDriver driver) {

        this.driver = driver;

    }


}


