package Page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntrevistaPage {
    //Tramite
    public By campoTramite = By.id ( "span__BNQFPA2NRO" );
    public By InputTramite = By.id ( "_BNQFPA2NRO" );
    //Datos personales
    public By InputApellido = By.id ( "_BNQFPA2APE" );
    public By InputNombre = By.id ( "_BNQFPA2NOM" );
    public By InputFechaNac = By.id ( "_BNQFPA2FNA" );
    public By InputFechaIngresoPais = By.id ( "_SNGC11DAT2" );
    public By SelectCapacidadLegal = By.name ( "_PFCAPL" );
    public By SelectEstadoCivil = By.name ( "_BNQFPA2ECI" );
    public By SelectSexo = By.name ( "_BNQFPA2SEX" );
    public By SelectPaisNacimiento = By.name ( "_PFPNAC" );
    public By SelectPaisCiudadania = By.name ( "_PAIS" );
    public By SelectProvincia = By.name ( "_SNGC11DPTO" );
    public By InputLocalidad = By.name ( "_SNGC11PROV" );//326 Neuquen
    public By SelectOcupacion = By.name ( "_SNGC60OCUP" );
    public By BTNOPDOMICILIOREAL = By.id ( "BTNOPDOMICILIOREAL" );
    public By CampoDomicilio = By.id ( "span__BNQFPA2DOM" );
    public By InputDomicilio = By.id ( "_BNQFPA2DOM" );
    public By SelectTipo = By.name ( "_BNQFPA2TDO" );
    public By InputDocumento = By.id ( "_BNQFPA2NDO" );
    public By BTNOPVALIDAR = By.id ( "BTNOPVALIDAR" );
    public By InputMail = By.name ( "_BNQFPA2MAI" );
    public By NoMail = By.name ( "_NOTIENEEMAIL" );
    public By SelectTipoTelefono = By.name ( "_BNQFPA2TT1" );
    public By SelectTipoTelefono2 = By.name ( "_BNQFPA2TT2" );
    public By SelectCodigoArea = By.name ( "_CODIGODEAERAT1" );
    public By SelectCodigoArea2 = By.name ( "_CODIGODEAERAT2" );
    public By InputTelefono = By.name ( "_BNQFPA2TE1" );
    public By InputTelefono2 = By.name ( "_BNQFPA2TE2" );
    public By GridCtaCredito = By.id ( "GRIDACRED" );
    public By SelectModalidad = By.name ( "_MODALIDAD" );
    public By BTNOPCAMBIARCTA = By.id ( "BTNOPCAMBIARCTA" );
    public By BTNOPELEGIRCTA = By.id ( "BTNOPELEGIRCTA" );
    public By BTNOPCONFIRMAR = By.id ( "BTNOPCONFIRMAR" );
    public By BTNOPDESCARTAR = By.id ( "BTNOPDESCARTAR" );
    public By BTN_SI = By.id ( "BTNCONFIRMATION" );
    public By BTN_NO = By.id ( "BTNCANCELCONFIRMATION" );
    public By BTNOPCERRAR = By.id ( "BTNOPCERRAR" );
    //Datos del Negocio
    public By InputFechaNegocio = By.name ( "_SNGC60FINI" );
    public By InputActividad = By.name ( "_ACTCOD1" );
    public By InputNombreEmpresa = By.name ( "_SNGC60RZSO" );
    public By SelectSegmento = By.name ( "_PJSEGMENTO" );
    // Datos de la Empresa PJ
    public By InputRazonSocial = By.name ( "_PJRAZS" );
    public By InputNombreFantasial = By.name ( "_SNGC11AUX" );
    public By SelectNaturalezaJuridica = By.name ( "_NJCOD" );
    public By InputFechaConstitucion = By.name ( "_PJFCON" );
    public By InputNroRegistro = By.name ( "_SNGC70VAL" );
    //Modelo de Riesgo
    public By TexareaObsvaciones = By.name ( "_BNQFPA2OBS" );
    //MsgText
    public By MsgText = By.className ( "MsgText" );
    //Cuenta Credito
    public By tabla_HTMLTBLCAT245 = By.id ( "HTMLTBLCAT245" );
    public By GRIDACRED = By.id ( "GRIDACRED" );
    public By td = By.tagName ( "td" );
    public By CampoAcreditaBPN = By.id ( "span__ACREDITAENBPN_0001" );
    public By PrimerFilaCuenta = By.id ( "span__BNQFPA4CTA_0001" );
    //_BNQFPA2CTA
    public By SelectCuentaBT = By.name ( "_BNQFPA2CTA" );
    //span__BNQFPA4CTA_0001
    //Input hidden _ACREDITAENBPN_0001 SI o NO 
    //span__ACREDITAENBPN_0001
    //_BNQFPA4EST_0001
    public By SelectSectorEmpleador = By.name ( "_BNQFPA2ORD" );
    public By InputIngresosDepedencia = By.name ( "_BNQFPA2IRD" );
    public By InputIngresosIndependiente = By.name ( "_BNQFPA2IIN" );
    public By InputIngresosJubilado = By.name ( "_BNQFPA2IJU" );

    public By TipoPersona = By.id ( "_PETIPO" );
    //Titulos
    public By Titulo = By.id ( "HTMLTXTTITLE1" );
    //Refinanciacion
    public By checkRefinanciacion = By.name ( "_REFINANCIAR_SN" );
    WebDriver driver;

    public EntrevistaPage(WebDriver driver) {

        this.driver = driver;

    }


}


