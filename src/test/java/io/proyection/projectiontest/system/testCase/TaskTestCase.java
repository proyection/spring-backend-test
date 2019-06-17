package test.java.io.proyection.projectiontest.system.testCase;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import test.java.io.proyection.projectiontest.system.data.Excel;
import test.java.io.proyection.projectiontest.system.pages.DashboardPage;
import io.proyection.projectiontest.system.util.Utilitario;

public class TaskTestCase {
    private DashboardPage dashboardPage;
    private String rutaCapturaPantalla;

    @BeforeTest
    @Parameters({"navegador", "rutaCapturaPantalla"})
    public void inicioTest(String navegador, String rutaCapturaPantalla)  {
        dashboardPage = new DashboardPage(navegador);
        this.rutaCapturaPantalla = rutaCapturaPantalla;
    }

    @DataProvider(name = "registrarTask") // permite pasar objetos
    public static Object[][] datosRegistrar(ITestContext contexto) {
        String ruta = contexto.getCurrentXmlTest().getParameter("rutaExcelRegistrarTask");
        return Excel.leerExcel(ruta);
    }

    @DataProvider(name = "actualizarTask") // permite pasar objetos
    public static Object[][] datosActualizar(ITestContext contexto) {
        String ruta = contexto.getCurrentXmlTest().getParameter("rutaExcelActualizarTask");
        return Excel.leerExcel(ruta);
    }

    @DataProvider(name = "extraTask") // permite pasar objetos
    public static Object[][] datosExtra(ITestContext contexto) {
        String ruta = contexto.getCurrentXmlTest().getParameter("rutaExcelExtraTask");
        return Excel.leerExcel(ruta);
    }

    @Test(dataProvider = "registrarTask", priority = 3)
    public void dashboard_RegistrarTask(String url, String correo, String password, String descripcion,
                                          String criAceptacion, String estado, String summaryNotBlank) {
        try {
            dashboardPage.ingresarPagina(url);
            dashboardPage.iniciarSesion(correo,password);
            dashboardPage.registrarTarea(descripcion,criAceptacion,estado,"09/02/2020",summaryNotBlank);

        } catch(AssertionError e) {
            Utilitario.caputarPantallarError(rutaCapturaPantalla, e.getMessage(), dashboardPage.obtenerPagina());
            Assert.fail(e.getMessage());
        } catch(Exception e) {
            Assert.fail("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "actualizarTask", priority = 4)
    public void dashboard_ActualizarTask(String url, String correo, String password, String descripcion,
                                          String criAceptacion, String estado) {
        try {
            dashboardPage.ingresarPagina(url);
            dashboardPage.iniciarSesion(correo,password);
            dashboardPage.actualizarTarea(descripcion,criAceptacion,estado,"09/02/2020");

        } catch(AssertionError e) {
            Utilitario.caputarPantallarError(rutaCapturaPantalla, e.getMessage(), dashboardPage.obtenerPagina());
            Assert.fail(e.getMessage());
        } catch(Exception e) {
            Assert.fail("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "extraTask", priority = 5)
    public void dashboard_CambiarEstadoTask(String url, String correo, String password) {
        try {
            dashboardPage.ingresarPagina(url);
            dashboardPage.iniciarSesion(correo,password);
            dashboardPage.cambiarEstado();

        } catch(AssertionError e) {
            Utilitario.caputarPantallarError(rutaCapturaPantalla, e.getMessage(), dashboardPage.obtenerPagina());
            Assert.fail(e.getMessage());
        } catch(Exception e) {
            Assert.fail("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "extraTask", priority = 6)
    public void dashboard_EliminarTask(String url, String correo, String password) {
        try {
            dashboardPage.ingresarPagina(url);
            dashboardPage.iniciarSesion(correo,password);
            dashboardPage.eliminarTarea();

        } catch(AssertionError e) {
            Utilitario.caputarPantallarError(rutaCapturaPantalla, e.getMessage(), dashboardPage.obtenerPagina());
            Assert.fail(e.getMessage());
        } catch(Exception e) {
            Assert.fail("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "extraTask", priority = 7)
    public void dashboard_DoneTask(String url, String correo, String password) {
        try {
            dashboardPage.ingresarPagina(url);
            dashboardPage.iniciarSesion(correo,password);
            dashboardPage.tareaTerminada();

        } catch(AssertionError e) {
            Utilitario.caputarPantallarError(rutaCapturaPantalla, e.getMessage(), dashboardPage.obtenerPagina());
            Assert.fail(e.getMessage());
        } catch(Exception e) {
            Assert.fail("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterTest
    public void finTest() {
        dashboardPage.cerrarPagina();
    }


}
