package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class bensgStorePage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Localizadores de Logueo
    public static final By userInput = By.id("field-email");
    public static final By passInput = By.id("field-password");
    public static final By loginButton = By.id("submit-login");

    // localizador para el elemento de categoría Clothes
    public static final By getCategory3 = By.xpath("//a[@href='https://qalab.bensg.com/store/es/3-clothes']");
    // localizador para el elemento de sub-categoría Men
    public static final By getCategory4 = By.xpath("//a[@href='https://qalab.bensg.com/store/es/4-men']");

    //Localizador para elemento Producto en sección Men
    public static final By primerProductoMen = By.xpath("//a[@href='https://qalab.bensg.com/store/es/men/1-1-hummingbird-printed-t-shirt.html#/1-tamano-s/8-color-blanco']");

    //Localizador para el elemento botón spin Up para aumentar cantidad de productos
    public static final By btnSpinUpProducto = By.xpath("//button/i[contains(@class, 'touchspin-up')]");

    /*
     * Lo necesario para el MODAL
     */

    //Localizador del botón para añadir el producto al carrito
    public static final By botonAnadirAlCarrito = By.xpath("//button[@class='btn btn-primary add-to-cart']");

    //Localizador del modal
    public static final By modalPopUp = By.id("blockcart-modal");

    //Localizador del mensaje que se lee dentro del modal
    public static final By mensajeConfirmacion = By.xpath("//h4[@id='myModalLabel' and contains(text(), 'Producto añadido correctamente a su carrito de compra')]");

    //Localizador del monto total de la compra que aparece dentro del modal
    public static final By montoTotal = By.xpath("//p[@class='product-total']/span[@class='value']");

    //Localizador de la cantidad de productos seleccionados que aparece dentro del modal
    public static final By cantProd = By.xpath("//span[@class='product-quantity']");

    // Localizador para finalizar compra
    public static final By botonFinalizarCompra = By.xpath("//a[contains(@class, 'btn btn-primary') and contains(text(), 'Finalizar compra')]");

    //Localizador para validar título de la página
    public static final By tituloCarrito = By.id("_desktop_logo");

    //Localizador para validar el Monto total en el carrito
    public static final By montoTotalCarrito = By.xpath("//div[@class='cart-summary-line cart-total']/span[@class='value']");


}
