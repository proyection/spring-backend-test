package test.java.io.proyection.projectiontest.system.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProjectionDriver {

    public final static WebDriver inicializarWebDriver(String navegador) {
        WebDriver webDriver = null;
        try {
            switch (navegador.toLowerCase()) {
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "C:\\development\\drivers\\geckodriver.exe");
                    webDriver = new FirefoxDriver(); //el web driver no se debe volver a inicializar hasta finalizar el flujo. Se crea uno por cada flujo (maneja la sesi�n)
                    break;
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "C:\\development\\drivers\\chromedriver.exe");
                    webDriver = new ChromeDriver();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webDriver;
    }


    public final static void cerrarPagina(WebDriver webDriver) {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
