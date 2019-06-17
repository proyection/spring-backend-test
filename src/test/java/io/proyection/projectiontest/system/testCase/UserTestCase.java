package test.java.io.proyection.projectiontest.system.testCase;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import test.java.io.proyection.projectiontest.system.data.Excel;
import test.java.io.proyection.projectiontest.system.pages.LoginPage;
import test.java.io.proyection.projectiontest.system.pages.RegisterPage;
import io.proyection.projectiontest.system.util.Utilitario;


public class UserTestCase {

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private String rutaCapturaPantalla;

    @BeforeTest
    @Parameters({"navegador", "rutaCapturaPantalla"})
    public void inicioTest(String navegador, String rutaCapturaPantalla)  {
        loginPage = new LoginPage(navegador);
        registerPage = new RegisterPage(navegador);
        this.rutaCapturaPantalla = rutaCapturaPantalla;
    }

    @DataProvider(name = "datosIniciarSesion") // permite pasar objetos
    public static Object[][] datosIniciarSesion(ITestContext contexto) {
        String ruta = contexto.getCurrentXmlTest().getParameter("rutaExcelLogin");
        return Excel.leerExcel(ruta);
    }

    @DataProvider(name = "datosRegistrar") // permite pasar objetos
    public static Object[][] datosRegistrar(ITestContext contexto) {
        String ruta = contexto.getCurrentXmlTest().getParameter("rutaExcelRegister");
        return Excel.leerExcel(ruta);
    }

    @Test(dataProvider = "datosIniciarSesion")
    public void iniciarSesion_FlujoBasico(String nombreCasoPrueba, String url, String usuario, String clave, String valorEsperado) {
        try {
            System.out.println(nombreCasoPrueba);
            loginPage.ingresarPagina(url);
            loginPage.iniciarSesion(usuario, clave);
            // Esto es una validacion fake, valida que el mensaje sea igual al del excel
            Assert.assertEquals("Se guardó de manera correcta la Categoría", valorEsperado);
        } catch(AssertionError e) {
            Utilitario.caputarPantallarError(rutaCapturaPantalla, e.getMessage(), loginPage.obtenerPagina());
            Assert.fail(e.getMessage());
        } catch(Exception e) {
            Assert.fail("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "datosRegistrar")
    public void registrar_FlujoBasico(String nombreCasoPrueba, String url, String nombre, String apellido, String correo, String password,
                                      String confirmPassword, String emailEsperado, String passwordEsperado, String confirmacionEsperado) {
        try {
            System.out.println(nombreCasoPrueba);
            registerPage.ingresarPagina(url);
            registerPage.registrar(nombre, apellido, correo, password, confirmPassword,emailEsperado,passwordEsperado, confirmacionEsperado);

        } catch(AssertionError e) {
            Utilitario.caputarPantallarError(rutaCapturaPantalla, e.getMessage(), registerPage.obtenerPagina());
            Assert.fail(e.getMessage());
        } catch(Exception e) {
            Assert.fail("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterTest
    public void finTest() {
        loginPage.cerrarPagina();
        registerPage.cerrarPagina();
    }
}
