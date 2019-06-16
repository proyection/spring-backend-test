package test.java.io.proyection.projectiontest.system.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver webDriver = null;
    private final static int MAXIMO_TIEMPO = 3000;
    private By link = By.xpath("//*[@id=\"mobile-nav\"]/ul/li[1]/a");
    private By cajaNombreUsuario = By.name("firstname");
    private By cajaApellidoUsuario = By.name("lastname");
    private By cajaCorreo = By.name("username");
    private By cajaPassword = By.name("password");
    private By cajaConfirmPassword = By.name("confirmPassword");
    private By botonRegistrar = By.name("registerBtn");
    private By mensajeRespuestaEmail = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div[3]/div");
    private By mensajeRespuestaPassword = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div[4]/div");


    public RegisterPage(String navegador) {
        this.webDriver = system.driver.ProjectionDriver.inicializarWebDriver(navegador);
    }

    public void ingresarPagina(String urlInicial) throws Exception {
        webDriver.get(urlInicial);
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public void registrar(String nombre, String apellido, String correo, String password, String confirmPassword) throws Exception{
        webDriver.findElement(cajaNombreUsuario).clear();
        webDriver.findElement(cajaNombreUsuario).sendKeys(nombre);
        webDriver.findElement(cajaApellidoUsuario).clear();
        webDriver.findElement(cajaApellidoUsuario).sendKeys(apellido);
        webDriver.findElement(cajaCorreo).clear();
        webDriver.findElement(cajaCorreo).sendKeys(correo);
        webDriver.findElement(cajaPassword).clear();
        webDriver.findElement(cajaPassword).sendKeys(password);
        webDriver.findElement(cajaConfirmPassword).clear();
        webDriver.findElement(cajaConfirmPassword).sendKeys(confirmPassword);
        webDriver.findElement(botonRegistrar).click();
    }


    public WebDriver obtenerPagina() {
        return webDriver;
    }

    public void cerrarPagina() {
        system.driver.ProjectionDriver.cerrarPagina(webDriver);
    }
}
