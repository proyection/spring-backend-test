package test.java.io.proyection.projectiontest.system.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.java.io.proyection.projectiontest.system.driver.ProjectionDriver;

public class LoginPage {

    private WebDriver webDriver = null;
    private By cajaUsuario = By.name("username");
    private By cajaClave = By.name("password");
    private By botonIniciarSesion = By.name("loginBtn");
    private final static int MAXIMO_TIEMPO  = 6000;


    public LoginPage(String navegador) {
        this.webDriver = ProjectionDriver.inicializarWebDriver(navegador);
    }

    public void ingresarPagina(String urlInicial) throws Exception {
        webDriver.get(urlInicial);
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public void iniciarSesion(String usuario, String clave) throws Exception {
        webDriver.findElement(cajaUsuario).clear();
        webDriver.findElement(cajaUsuario).sendKeys(usuario);
        webDriver.findElement(cajaClave).clear();
        webDriver.findElement(cajaClave).sendKeys(clave);
        webDriver.findElement(botonIniciarSesion).click();
    }

    public WebDriver obtenerPagina() {
        return webDriver;
    }

    public void cerrarPagina() {
        ProjectionDriver.cerrarPagina(webDriver);
    }

}
