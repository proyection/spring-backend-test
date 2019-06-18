package test.java.io.proyection.projectiontest.system.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test.java.io.proyection.projectiontest.system.driver.ProjectionDriver;

public class LoginPage {

    private WebDriver webDriver = null;
    private By cajaUsuario = By.name("username");
    private By cajaClave = By.name("password");
    private By botonIniciarSesion = By.name("loginBtn");
    private By mensajeEmail = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div[1]/div");
    private By mensajePassword = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div[2]/div");
    private final static int MAXIMO_TIEMPO  = 500;


    public LoginPage(String navegador) {
        this.webDriver = ProjectionDriver.inicializarWebDriver(navegador);
    }

    public void ingresarPagina(String urlInicial) throws Exception {
        webDriver.get(urlInicial);
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public void iniciarSesion(String usuario, String clave, String emailEsperado, String passwordEsperado) throws Exception {
        webDriver.findElement(cajaUsuario).clear();
        webDriver.findElement(cajaUsuario).sendKeys(usuario);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaClave).clear();
        webDriver.findElement(cajaClave).sendKeys(clave);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(botonIniciarSesion).click();
        Thread.sleep(MAXIMO_TIEMPO);

        if(!emailEsperado.isEmpty()){
            String text = webDriver.findElement(mensajeEmail).getText();
            Assert.assertEquals(text,emailEsperado);
        }
        if(!passwordEsperado.isEmpty()){
            String text = webDriver.findElement(mensajePassword).getText();
            Assert.assertEquals(text,passwordEsperado);
        }
    }

    public WebDriver obtenerPagina() {
        return webDriver;
    }

    public void cerrarPagina() {
        ProjectionDriver.cerrarPagina(webDriver);
    }

}
