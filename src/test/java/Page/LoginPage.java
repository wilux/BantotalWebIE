package Page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    public By UserInput = By.id ( "_USER" );
    public By PasswordInput = By.id ( "_PASSWORD" );
    public By LoginButton = By.id ( "BTNOPINICIARSESION" );


    WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;

    }

}


