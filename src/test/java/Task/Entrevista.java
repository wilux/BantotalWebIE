package Task;

import Action.*;
import Page.DetalleDireccionPage;
import Page.EntrevistaPage;
import Tools.Frame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Entrevista {
    WebDriver driver;


    public Entrevista(WebDriver driver) {

        this.driver = driver;


    }

    public void CompletarGenerico() throws InterruptedException {
        // Modalidad ( "Presencial" );
        // ActividadLaboral ();
        // DatosDelNegocio ();
        // DatosPersonales ();
        DatosContacto ();
        CuentaDebito ();
        Ingresos ( "Dependiente" );
        Confirmar ();

    }


    public String NroEntrevista() {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Get get = new Get ( driver );
        return get.TextOnTag ( entrevistaPage.campoTramite );
    }

    public boolean Error() {
        System.out.println ( "Buscando mensajes de error" );
        Frame frame = new Frame ( driver );
        if ( frame.BuscarFrame ( By.id ( "TXTMESSAGES" ) ) ) {
            System.out.printf ( "Elemento TXTMESSAGES encontrado " );
            WebElement element = driver.findElement ( By.id ( "TXTMESSAGES" ) );
            String imgError = element.findElement ( By.cssSelector ( "img" ) ).getAttribute ( "src" );


            //http://btwebqa.ar.bpn:99/BTWeb/error.gif
            if ( imgError.contains ( "error.gif" ) ) {
                System.out.println ( imgError );
                return true;
            }
            else {
                System.out.println ( imgError );
                return false;
            }
        }
        else {
            return false;
        }
    }


    public void IdentificacionPersona(String tipo, String cuilCuit) throws InterruptedException {
        System.out.println ( "Ingresando Entrevista para el Documento " + cuilCuit );
        //Ingresar Tipo y Documento
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        //
        Click click = new Click ( driver );
        Write write = new Write ( driver );
        //choose.byText ( entrevistaPage.SelectTipo, tipo );
        write.On ( entrevistaPage.InputDocumento, cuilCuit );
        click.On ( entrevistaPage.BTNOPVALIDAR );

    }

    public void Modalidad(String modalidad) {
        System.out.println ( "Completando Modalidad " + modalidad );
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );

        Choose choose = new Choose ( driver );
        choose.byText ( entrevistaPage.SelectModalidad, modalidad );

    }

    public void DatosPersonales() throws InterruptedException {

        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Choose choose = new Choose ( driver );
        Get get = new Get ( driver );
        Click click = new Click ( driver );
        Write write = new Write ( driver );

        //Valores Genericos
        String apellido = "Doe";
        String nombre = "John";
        String fechaNacimiento = "01/01/1990";
        String capacidadLegal = "1";//"MAYOR DE EDAD"
        String sexo = "M";
        String paisNacimiento = "80"; //Argentina
        String estadoCivil = "4";//Soltero
        String provincia = "15"; //Neuquen
        String localidad = "326";//Neuquen
        String paisCiudadania = "80"; //Argentina

        System.out.println ( "Completando Datos Personales" );

        // Pais & Provincia & Pais Ciudadania - Primero por refrescos al cambiar
        if ( get.ValueOnInput ( entrevistaPage.SelectPaisNacimiento ).equals ( "0" ) ) {
            Thread.sleep ( 5000 );
            choose.byValue ( entrevistaPage.SelectPaisNacimiento, paisNacimiento );

        }
        if ( get.ValueOnInput ( entrevistaPage.SelectProvincia ).equals ( "0" ) ) {
            Thread.sleep ( 5000 );
            choose.byValue ( entrevistaPage.SelectProvincia, provincia );
        }
        if ( get.ValueOnInput ( entrevistaPage.SelectPaisCiudadania ).equals ( "65" ) ) { //AFGANISTAN, REP. DEM. DE
            Thread.sleep ( 5000 );
            choose.byValue ( entrevistaPage.SelectPaisCiudadania, paisCiudadania ); // Argentina
        }
        // Pais & Provincia & Pais Ciudadania - Primero por refrescos al cambiar
        Thread.sleep ( 5000 );

        if ( get.ValueOnInput ( entrevistaPage.InputNombre ).equals ( "" ) ) {
            write.Js ( entrevistaPage.InputNombre, nombre );
        }
        if ( get.ValueOnInput ( entrevistaPage.InputApellido ).equals ( "" ) ) {
            write.Js ( entrevistaPage.InputApellido, apellido );
        }
        if ( get.ValueOnInput ( entrevistaPage.InputFechaNac ).length () == 4 ) {
            write.Js ( entrevistaPage.InputFechaNac, fechaNacimiento );
        }

        if ( get.ValueOnInput ( entrevistaPage.SelectSexo ).length () == 0 ) {
            choose.byValue ( entrevistaPage.SelectSexo, sexo );
        }
        if ( get.ValueOnInput ( entrevistaPage.SelectCapacidadLegal ).length () == 0 ) {
            choose.byValue ( entrevistaPage.SelectCapacidadLegal, capacidadLegal );
        }


        if ( get.ValueOnInput ( entrevistaPage.SelectEstadoCivil ).equals ( "1" ) ) { // Casado
            choose.byValue ( entrevistaPage.SelectEstadoCivil, estadoCivil ); // Soltero
        }


        if ( get.ValueOnInput ( entrevistaPage.InputLocalidad ).equals ( "0" ) ) {
            //write.Clear ( entrevistaPage.InputLocalidad );
            write.Js ( entrevistaPage.InputLocalidad, localidad );
        }


    }

    public void ActividadLaboral() {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Choose choose = new Choose ( driver );
        Get get = new Get ( driver );
        String actividad = "1"; //Empleado

        if ( get.ValueOnInput ( entrevistaPage.SelectOcupacion ).equals ( "0" ) ) {
            System.out.println ( "Completando Actividad Laboral" );
            choose.byValue ( entrevistaPage.SelectOcupacion, actividad );
            System.out.println ( "Cambiado a " + actividad );
        }
    }

    public void DatosDelNegocio() {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Write write = new Write ( driver );
        Get get = new Get ( driver );
        String nombreEmpresa = "Empresa SA"; //Empleado
        String actividad = "853100"; //Enseñanza terciaria
        String fechaNegocio = "01/01/2020"; //Fecha

        if ( get.ValueOnInput ( entrevistaPage.InputNombreEmpresa ).length () == 0 ) {
            System.out.println ( "Completando Datos del Negocio - Nombre" );
            write.Js ( entrevistaPage.InputNombreEmpresa, nombreEmpresa );
            System.out.println ( "Cambiado a " + nombreEmpresa );
        }

        if ( get.ValueOnInput ( entrevistaPage.InputActividad ).length () == 0 ) {
            System.out.println ( "Completando Datos del Negocio - Actividad" );
            write.Js ( entrevistaPage.InputActividad, actividad );
            System.out.println ( "Cambiado a " + actividad );
        }

        if ( get.ValueOnInput ( entrevistaPage.InputFechaNegocio ).length () == 4 ) {
            System.out.println ( "Completando Datos del Negocio - Fecha" );
            write.Js ( entrevistaPage.InputFechaNegocio, fechaNegocio );
            System.out.println ( "Cambiado a " + fechaNegocio );
        }
    }

    public void DatosContacto() throws InterruptedException {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Choose choose = new Choose ( driver );
        Get get = new Get ( driver );
        Click click = new Click ( driver );
        Write write = new Write ( driver );

        //Mail

        if ( get.ValueOnInput ( entrevistaPage.InputMail ).equals ( "" ) ) {
            System.out.println ( "Completando Mail" );
            write.On ( entrevistaPage.InputMail, "no@no.com" );
        }
        System.out.println ( "Completando Telefonos" );
        //Telefonos
        //Telefono1
        choose.byValue ( entrevistaPage.SelectTipoTelefono, "1" );
        Thread.sleep ( 5000 );
        choose.byValue ( entrevistaPage.SelectCodigoArea, "299" );
        write.On ( entrevistaPage.InputTelefono, "4729999" );
        //Telefono2
        if ( !get.ValueOnInput ( entrevistaPage.SelectTipoTelefono2 ).equals ( "0" ) ) {
            choose.byValue ( entrevistaPage.SelectTipoTelefono2, "0" );
            Thread.sleep ( 5000 );
            choose.byValue ( entrevistaPage.SelectCodigoArea2, "0" );
            write.Clear ( entrevistaPage.InputTelefono2 );
        }

        //Domicilio
        if ( get.TextOnTag ( entrevistaPage.CampoDomicilio ).length () == 0 ) {
            System.out.println ( "Entrando a Completar Domicilio" );
            Domicilio ();
        }

    }

    public void Domicilio() throws InterruptedException {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Choose choose = new Choose ( driver );
        Get get = new Get ( driver );
        Click click = new Click ( driver );
        Write write = new Write ( driver );

        System.out.println ( "Completando Domicilio" );
        String domicilio = "1"; //Empleado

        System.out.println ( "Entrando a Domicilio Real" );
        click.On ( entrevistaPage.BTNOPDOMICILIOREAL );
        DetalleDireccionPage detalleDireccionPage = new DetalleDireccionPage ( driver );

        click.On ( detalleDireccionPage.InputFechaDesde );
        write.On ( detalleDireccionPage.InputFechaDesde, "01011990" );

        choose.byText ( detalleDireccionPage.SelectCalle, "Calle" );
        write.On ( detalleDireccionPage.InputCalle, "Avenida Siempre Viva" );
        choose.byText ( detalleDireccionPage.SelectNumero, "Número" );
        write.On ( detalleDireccionPage.InputNumero, "123" );
        choose.byValue ( detalleDireccionPage.SelectPais, "80" );
        choose.byValue ( detalleDireccionPage.SelectProvincia, "15" );
        choose.byValue ( detalleDireccionPage.SelectLocalidad, "326" );
        write.On ( detalleDireccionPage.InputCodigoPostal, "Q8300NQN" );
        click.On ( detalleDireccionPage.BTNOPBTNCONFIRMAR );
        click.On ( detalleDireccionPage.BTNCONFIRMATION );
    }


    public void CuentaDebito() throws InterruptedException {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Grid grid = new Grid ( driver );
        Get get = new Get ( driver );
        Click click = new Click ( driver );
        String actividad = "1"; //Empleado

        if ( get.TextOnTag ( entrevistaPage.PrimerFilaCuenta ).length () != 0 ) {
            System.out.println ( "Seleccionando cuenta debito" );
            grid.rowSelectbyFila ( entrevistaPage.GRIDACRED, entrevistaPage.PrimerFilaCuenta );
            System.out.println ( "Seleccionada la primer fila de acreditaciones" );
            click.On ( entrevistaPage.BTNOPELEGIRCTA );
            Thread.sleep ( 5000 );
        }
    }

    public void Ingresos(String tipo) {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Choose choose = new Choose ( driver );
        Write write = new Write ( driver );
        String sector = "";
        String importe = "";

        if ( tipo == "Dependiente" ) {
            sector = "PUB"; //Empleado
            importe = "300000"; //Empleado
            System.out.println ( "Cambiando ingresos de Relacion Dependencia" );
            choose.byValue ( entrevistaPage.SelectSectorEmpleador, sector );
            System.out.println ( "Se Elijio " + sector );
            write.Js ( entrevistaPage.InputIngresosDepedencia, importe );
            System.out.println ( "Se Ingreso como importe " + importe );
        }

        if ( tipo == "Jubilado" ) {
            importe = "300000"; //
            write.Js ( entrevistaPage.InputIngresosJubilado, importe );
        }

        if ( tipo == "Independiente" ) {

        }

    }

    public void Confirmar() throws InterruptedException {
        Thread.sleep ( 3000 );
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Click click = new Click ( driver );
        click.On ( entrevistaPage.BTNOPCONFIRMAR );
        Thread.sleep ( 3000 );
    }

    public void Refinanciar() throws InterruptedException {
        Thread.sleep ( 3000 );
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        CheckBox checkBox = new CheckBox ( driver );
        checkBox.Check ( entrevistaPage.checkRefinanciacion );
        Thread.sleep ( 3000 );
    }

    public void Descartar() throws InterruptedException {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Click click = new Click ( driver );
        click.On ( entrevistaPage.BTNOPDESCARTAR );
        Si ();

    }

    public void Si() throws InterruptedException {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Click click = new Click ( driver );
        click.On ( entrevistaPage.BTN_SI );
        Thread.sleep ( 2000 );
    }

    public void No() throws InterruptedException {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Click click = new Click ( driver );
        click.On ( entrevistaPage.BTN_NO );
        Thread.sleep ( 2000 );
    }

    public void Cerrar() {
        EntrevistaPage entrevistaPage = new EntrevistaPage ( driver );
        Click click = new Click ( driver );
        click.On ( entrevistaPage.BTNOPCERRAR );
    }


}
