@testfeatureStore
Feature: Product - Store

  @validarPrecioProducto
  Scenario: Validación del precio de un producto
    Given estoy en la página de la tienda
    And me logueo con mi usuario "mreyes@trade.com" y clave "A@NTT_Lima"
    When navego a la categoria "Clothes" y subcategoria "Men"
    And agrego 2 unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito

  @validarIntentoInicioSesion
  Scenario Outline: Intento de inicio de sesión
    Given que estoy en la página de inicio de sesión
    When ingreso el usuario "<usuario>" y la contraseña "<contraseña>"
    Then debería ver la página principal

    Examples:
      | usuario          | contraseña  | descripción            |
      | mreyes@trade.com | A@NTT_Lima  | Credenciales válidas   |
      | jcanor@trade.com | A@NTT_Piura | Credenciales inválidas |

  @validarCategoriaAutos
  Scenario: Busco categorías
    Given que estoy en la página principal de la tienda
    When busco la categoria "Autos"
    Then debería ver un mensaje "No se han encontrado coincidencias con tu búsqueda"






