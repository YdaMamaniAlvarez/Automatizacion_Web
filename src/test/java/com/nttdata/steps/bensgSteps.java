package com.nttdata.steps;

import com.nttdata.page.InventoryPage;
import com.nttdata.page.bensgStorePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;

import java.time.Duration;

public class bensgSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    //constructor
    public bensgSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }


    /* Escribir el usuario */
    public void typeUser(String user) {
        WebElement userInputElement = driver.findElement(bensgStorePage.userInput);
        userInputElement.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bensgStorePage.loginButton));
    }

    /* Escribir el password */
    public void typePassword(String password) {
        driver.findElement(bensgStorePage.passInput).sendKeys(password);
    }

    /* Hacer click en el botón login */
    public void login() {
        driver.findElement(bensgStorePage.loginButton).click();
    }


    //Evento clic para añadir productos al carrito
    public void hacerClickEnBotonAnadirAlCarrito() {
        driver.findElement(bensgStorePage.botonAnadirAlCarrito).click();
    }


    public void validarConfirmacionProdAgredado() {
        // Espera a que el modal aparezca
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(bensgStorePage.modalPopUp));
        Assertions.assertTrue(modal.isDisplayed());

        // Validar el mensaje de confirmación
        String mensajeEsperado = "Producto añadido correctamente a su carrito de compra";
        WebElement mensajeConfirmacionElemento = driver.findElement(bensgStorePage.mensajeConfirmacion);
        String mensajeReal = mensajeConfirmacionElemento.getText();

        // Verificar que el mensaje real contenga el mensaje esperado
        Assertions.assertTrue(mensajeReal.contains(mensajeEsperado));

    }

    public void validarMontoTotal() {
        // Espera a que el modal aparezca
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(bensgStorePage.modalPopUp));
        Assertions.assertTrue(modal.isDisplayed());

        // Validar el monto total
        String montoTotalEsperado = "38,24 PEN";
        WebElement montoTotalElement = driver.findElement(bensgStorePage.montoTotal);
        Assertions.assertEquals(montoTotalEsperado, montoTotalElement.getText());

        // Validar la cantidad de productos
        String cantidadProductosEsperada = "2";
        WebElement cantProdElement = driver.findElement(bensgStorePage.cantProd);
        Assertions.assertTrue(cantProdElement.getText().contains(cantidadProductosEsperada));
    }

    public void finalizarCompra() {
        try {
            // Localizar el botón "Finalizar compra"
            WebElement botonFinalizarCompra = driver.findElement(bensgStorePage.botonFinalizarCompra);

            // Obtener el texto del botón, ignorando el ícono
            String textoBoton = botonFinalizarCompra.getText().replaceAll("[^\\p{L}\\p{Nd}\\p{Zs}]", "").trim();

            // Verificar que el texto es "Finalizar compra"
            String textoEsperado = "FINALIZAR COMPRA";
            Assertions.assertEquals(textoEsperado, textoBoton, "El texto del botón no coincide.");

            // Hacer clic en el botón
            botonFinalizarCompra.click();

            // Esperar hasta que la URL contenga "action=show"
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("action=show"));

            // Verificar la URL actual
            String urlEsperada = "https://qalab.bensg.com/store/es/carrito?action=show";
            String urlActual = driver.getCurrentUrl();
            Assertions.assertEquals(urlEsperada, urlActual, "La URL después de finalizar la compra no es la esperada.");

        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            Assertions.fail("Error al intentar finalizar la compra: " + e.getMessage());
        }

    }

    public String obtenerTituloCarrito() {
        WebElement imagenTitulo = this.driver.findElement(bensgStorePage.tituloCarrito);
        return imagenTitulo.getAttribute("alt");
    }


    public void validarMontoTotalCarrito() {
        // 1. Obtener el monto total esperado
        double montoTotalEsperado = 38.24; // Reemplaza este valor por el cálculo que hayas realizado previamente

        // 2. Esperar a que el elemento que muestra el monto total sea visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement montoTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(bensgStorePage.montoTotalCarrito));

        // 3. Extraer el texto del elemento que muestra el monto total
        String montoTotalActualString = montoTotalElement.getText();

        // 4. Extraer solo el valor numérico del monto total
        String montoTotalActualStringClean = montoTotalActualString.replaceAll("[^0-9,.]", "");
        double montoTotalActual = Double.parseDouble(montoTotalActualStringClean.replace(",", "."));

        // 5. Comparar el monto total actual con el monto total esperado y lanzar una aserción si no coinciden
        Assertions.assertEquals(montoTotalEsperado, montoTotalActual, 0.01); // El tercer parámetro es la tolerancia de la comparación

    }

}
