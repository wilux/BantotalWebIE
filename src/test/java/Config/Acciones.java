package Config;

import Action.*;
import Task.Menu;
import Task.*;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class Acciones {
    WebDriver driver;

    public Acciones(WebDriver driver) {
        this.driver = driver;
    }

    public PlanPagosAmortizables planPagosAmortizables() {
        PlanPagosAmortizables planPagosAmortizables = new PlanPagosAmortizables ( driver );
        return planPagosAmortizables;
    }

    public RevisionParaConfirmar revisionParaConfirmar() {
        RevisionParaConfirmar revisionParaConfirmar = new RevisionParaConfirmar ( driver );
        return revisionParaConfirmar;
    }

    public RevisionProductos revisionProductos() {
        RevisionProductos revisionProductos = new RevisionProductos ( driver );
        return revisionProductos;
    }

    public MatrizRiesgo matrizRiesgo() {
        MatrizRiesgo matrizRiesgo = new MatrizRiesgo ( driver );
        return matrizRiesgo;
    }


    public Reutilizacion reutilizacion() {
        Reutilizacion reutilizacion = new Reutilizacion ( driver );
        return reutilizacion;
    }


    public CargaAvanzada cargaAvanzada() {
        CargaAvanzada cargaAvanzada = new CargaAvanzada ( driver );
        return cargaAvanzada;
    }

    public BandejaTareas bandejaTareas() {
        BandejaTareas bandejaTareas = new BandejaTareas ( driver );
        return bandejaTareas;
    }

    public CierreCuentas cierreCuentas() {
        CierreCuentas cierreCuentas = new CierreCuentas ( driver );
        return cierreCuentas;
    }

    public Ejecutar ejecutar() {
        Ejecutar ejecutar = new Ejecutar ( driver );
        return ejecutar;
    }


    public Entrevista entrevista() {
        Entrevista entrevista = new Entrevista ( driver );
        return entrevista;
    }


    public Login login() {
        Login login = new Login ( driver );
        return login;
    }

    public Menu menu() {
        Menu menu = new Menu ( driver );
        return menu;
    }

    public Simulacion simulacion() {
        Simulacion simulacion = new Simulacion ( driver );
        return simulacion;
    }

    public CheckBox checkBox() {
        CheckBox checkBox = new CheckBox ( driver );
        return checkBox;
    }

    public Radio radio() {
        Radio radio = new Radio ( driver );
        return radio;
    }

    public Choose choose() {
        Choose choose = new Choose ( driver );
        return choose;
    }

    public Click click() {
        Click click = new Click ( driver );
        return click;
    }

    public Keyboard keyboard() throws AWTException {
        Keyboard keyboard = new Keyboard ();
        return keyboard;
    }

    public Get get() {
        Get get = new Get ( driver );
        return get;
    }

    public Write write() {
        Write write = new Write ( driver );
        return write;
    }

    public Grid grid() {
        Grid grid = new Grid ( driver );
        return grid;
    }

}
