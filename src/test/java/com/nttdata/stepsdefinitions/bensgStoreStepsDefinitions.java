package com.nttdata.stepsdefinitions;

import com.nttdata.core.DriverManager;
import com.nttdata.page.bensgStorePage;
import com.nttdata.steps.InventorySteps;
import com.nttdata.steps.bensgSteps;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;
import static com.nttdata.core.DriverManager.scrollDown;

import org.openqa.selenium.By;

import java.time.Duration;

public class bensgStoreStepsDefinitions {
    private WebDriver driver;
    private bensgSteps bensgSteps;


    private InventorySteps inventorySteps(WebDriver driver) {
        return new InventorySteps(driver);
    }

    //Scenario: Validación del precio de un producto

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/es/iniciar-sesion?back=https%3A%2F%2Fqalab.bensg.com%2Fstore%2Fes%2F");
        screenShot();
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String email, String password) {
        this.bensgSteps = new bensgSteps(DriverManager.getDriver());  // Usar DriverManager.getDriver()
        bensgSteps.typeUser(email);
        bensgSteps.typePassword(password);
        bensgSteps.login();
        screenShot();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        By categoriaLocator;
        By subcategoriaLocator;

        // Determinar los localizadores basados en los parámetros de entrada
        if (categoria.equalsIgnoreCase("Clothes")) {
            categoriaLocator = bensgStorePage.getCategory3;
        } else {
            throw new IllegalArgumentException("Categoría no soportada: " + categoria);
        }

        // Localizador relativo para la subcategoría dentro de la lista de categorías
        if (subcategoria.equalsIgnoreCase("Men")) {
            subcategoriaLocator = By.xpath("//ul[contains(@class, 'category-sub-menu')]//a[contains(@href, '/4-men')]");
        } else {
            throw new IllegalArgumentException("Subcategoría no soportada: " + subcategoria);
        }

        // Navegar a la categoría
        wait.until(ExpectedConditions.elementToBeClickable(categoriaLocator)).click();
        scrollDown();
        screenShot();


        // Esperar hasta que la subcategoría sea visible dentro del menú antes de intentar hacer clic
        WebElement subcategoriaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subcategoriaLocator));
        subcategoriaElement.click();
        scrollDown();
        screenShot();
    }
    

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/es/men/1-1-hummingbird-printed-t-shirt.html#/1-tamano-s/8-color-blanco");
        scrollDown();

        // Espera a que la página cargue completamente
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar y hacer clic en el botón "spin up" para aumentar la cantidad
        WebElement spinUpButton = wait.until(ExpectedConditions.elementToBeClickable(bensgStorePage.btnSpinUpProducto));
        spinUpButton.click();

        // Hacer clic en el botón "Añadir al carrito"
        bensgSteps.hacerClickEnBotonAnadirAlCarrito();

        // Tomar captura de pantalla como evidencia
        screenShot();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        bensgSteps.validarConfirmacionProdAgredado();
        screenShot();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        bensgSteps.validarMontoTotal();
        screenShot();

    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        bensgSteps.finalizarCompra();
        screenShot();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        bensgSteps.obtenerTituloCarrito();
        screenShot();
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        bensgSteps.validarMontoTotalCarrito();
        screenShot();
    }

    //Scenario Outline: Intento de inicio de sesión

    @Given("que estoy en la página de inicio de sesión")
    public void queEstoyEnLaPáginaDeInicioDeSesión() {
    }

    @When("ingreso el usuario {string} y la contraseña {string}")
    public void ingresoElUsuarioYLaContraseña(String arg0, String arg1) {

    }

    @Then("debería ver la página principal")
    public void deberíaVerLaPáginaPrincipal() {
    }

    //Scenario: Busco categorías

    @Given("que estoy en la página principal de la tienda")
    public void queEstoyEnLaPáginaPrincipalDeLaTienda() {
    }

    @When("busco la categoria {string}")
    public void buscoLaCategoria(String arg0) {

    }

    @Then("debería ver un mensaje {string}")
    public void deberíaVerUnMensaje(String arg0) {
    }
}
