package test.java.io.proyection.projectiontest.system.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test.java.io.proyection.projectiontest.system.driver.ProjectionDriver;

public class DashboardPage {

    private WebDriver webDriver = null;

    //Iniciar Sesion
    private By cajaUsuario = By.name("username");
    private By cajaClave = By.name("password");
    private By botonIniciarSesion = By.name("loginBtn");

    //Crear Tarea
    private By botonCrearTarea=By.xpath("//*[@id=\"root\"]/div/div/a");
    private By cajaDescripcion = By.name("summary");
    private By cajaCriterioAceptacion = By.name("acceptanceCriteria");
    private By cajaEstado = By.name("status");
    private By cajaFecha = By.name("limitDate");
    private By botonRegistarTarea=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/input");
    private By mensajeDescripcion=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/div[1]/div");

    //Actualizar Tarea
    private By botonActualizarTarea=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/div[2]/a");
    private By botonEnviar= By.xpath("//*[@id=\"root\"]/div/div/div/div/div/form/input");

    //Cambiar Estado
    private By radioBoton1=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/div[2]/form/div[1]/div/div[1]");
    private By radioBoton2=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/div[2]/form/div[1]/div/div[2]");
    private By radioBoton3=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/div[2]/form/div[1]/div/div[3]");
    private By botonCambiarEstado=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]/div[2]/form/input");

    //Eliminar tarea
    private By botonEliminar=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/div[2]/button");

    //Tarea eliminada
    private By checkboxTareaEliminada=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/div[2]/form/div[2]/div/label");
    private By conocerIdTarea=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/div[1]");
    private By conocerContenedor=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div[2]");
    private By enviarTareaTerminada=By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[2]/div[2]/div[2]/form/input");
    private final static int MAXIMO_TIEMPO  = 2000;


    public DashboardPage(String navegador) {
        webDriver = ProjectionDriver.inicializarWebDriver(navegador);
    }

    public void iniciarSesion(String usuario, String clave) throws Exception {
        webDriver.findElement(cajaUsuario).clear();
        webDriver.findElement(cajaUsuario).sendKeys(usuario);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaClave).clear();
        webDriver.findElement(cajaClave).sendKeys(clave);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(botonIniciarSesion).click();
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public void registrarTarea(String descripcion, String critAceptacion, String estado, String fecha, String summaryNotBlank) throws Exception {

        webDriver.findElement(botonCrearTarea).click();
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaDescripcion).clear();
        webDriver.findElement(cajaDescripcion).sendKeys(descripcion);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaCriterioAceptacion).clear();
        webDriver.findElement(cajaCriterioAceptacion).sendKeys(critAceptacion);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaEstado).sendKeys(estado);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaFecha).clear();
        webDriver.findElement(cajaFecha).sendKeys(fecha);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(botonRegistarTarea).click();
        Thread.sleep(MAXIMO_TIEMPO);
        if (!summaryNotBlank.isEmpty()){
            String text = webDriver.findElement(mensajeDescripcion).getText();
            Assert.assertEquals(text, summaryNotBlank);
        }
    }

    public void actualizarTarea(String descripcion, String critAceptacion, String estado, String fecha) throws Exception {
        webDriver.findElement(botonActualizarTarea).click();
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaDescripcion).clear();
        webDriver.findElement(cajaDescripcion).sendKeys(descripcion);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaCriterioAceptacion).clear();
        webDriver.findElement(cajaCriterioAceptacion).sendKeys(critAceptacion);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaEstado).click();
        webDriver.findElement(cajaEstado).sendKeys(estado);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(cajaFecha).clear();
        webDriver.findElement(cajaFecha).sendKeys(fecha);
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(botonEnviar).click();
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public void cambiarEstado() throws Exception {
        webDriver.findElement(radioBoton2).click();
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(botonCambiarEstado).click();
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public void eliminarTarea() throws Exception {
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(botonEliminar).click();
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.switchTo().alert().accept();
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public void tareaTerminada() throws Exception {

        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(checkboxTareaEliminada).click();
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.findElement(enviarTareaTerminada).click();
        Thread.sleep(MAXIMO_TIEMPO);
        webDriver.switchTo().alert().accept();
    }

    public void ingresarPagina(String urlInicial) throws Exception {
        webDriver.get(urlInicial);
        Thread.sleep(MAXIMO_TIEMPO);
    }

    public WebDriver obtenerPagina() {
        return webDriver;
    }

    public void cerrarPagina() {
        ProjectionDriver.cerrarPagina(webDriver);
    }

}
