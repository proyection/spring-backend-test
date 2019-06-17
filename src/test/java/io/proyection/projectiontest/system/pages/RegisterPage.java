package test.java.io.proyection.projectiontest.system.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test.java.io.proyection.projectiontest.system.driver.ProjectionDriver;

public class RegisterPage {

    private WebDriver webDriver = null;
    private final static int MAXIMO_TIEMPO = 500;
    private By link = By.xpath("//*[@id=\"mobile-nav\"]/ul/li[1]/a");
    private By cajaNombreUsuario = By.name("firstname");
    private By cajaApellidoUsuario = By.name("lastname");
    private By cajaCorreo = By.name("username");
    private By cajaPassword = By.name("password");
    private By cajaConfirmPassword = By.name("confirmPassword");
    private By botonRegistrar = By.name("registerBtn");
    private By mensajeRespuestaEmail = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div[3]/div");
    private By mensajeRespuestaPassword = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div[4]/div");
    private By mensajeRespuestaConfirmation = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div[5]/div");


    public RegisterPage(String navegador) {
        this.webDriver = ProjectionDriver.inicializarWebDriver(navegador);
    }

    public void ingresarPagina(String urlInicial) throws Exception {
        webDriver.get(urlInicial);
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public void registrar(String nombre, String apellido, String correo, String password, String confirmPassword,
                          String emailExpected, String passwordExpected, String confirmationExpected) throws Exception{
        webDriver.findElement(cajaNombreUsuario).clear();
        webDriver.findElement(cajaNombreUsuario).sendKeys(nombre);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaApellidoUsuario).clear();
        webDriver.findElement(cajaApellidoUsuario).sendKeys(apellido);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaCorreo).clear();
        webDriver.findElement(cajaCorreo).sendKeys(correo);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaPassword).clear();
        webDriver.findElement(cajaPassword).sendKeys(password);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaConfirmPassword).clear();
        webDriver.findElement(cajaConfirmPassword).sendKeys(confirmPassword);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(botonRegistrar).click();
        Thread.sleep(MAXIMO_TIEMPO);
        if(!emailExpected.isEmpty()){
            String text = webDriver.findElement(mensajeRespuestaEmail).getText();
            Assert.assertEquals(text, emailExpected);
        }
        if(!passwordExpected.isEmpty()){
            String text = webDriver.findElement(mensajeRespuestaPassword).getText();
            Assert.assertEquals(text, passwordExpected);
        }
        if(!confirmationExpected.isEmpty()){
            String text = webDriver.findElement(mensajeRespuestaConfirmation).getText();
            Assert.assertEquals(text, confirmationExpected);
        }
    }


    public WebDriver obtenerPagina() {
        return webDriver;
    }

    public void cerrarPagina() {
        ProjectionDriver.cerrarPagina(webDriver);
    }
}
