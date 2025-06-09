#language:es
  #author:Camilo Perez

Característica: Programación de clases
  Como usuario autenticado
  Quiero programar una clase
  Para vincularme a una clase y visualizarla posteriormente en mi calendario

//  Antecedentes:
//    Dado que el usuario se encuentra en la pagina de inicio
//    Cuando hace clic en el botón Eventos de la barra de navegación y en el modulo calendario

  @ProgramarClase
  Esquema del escenario: Programar una clase

    Dado que el usuario ingresa al calendario

    Cuando el usuario diligencia los campos requeridos
      | profesor   | hora   | clase   |
      | <profesor> | <hora> | <clase> |
    Entonces se visualiza la clase programada

    Ejemplos:
      | profesor | hora   | clase    |
      | Carlos   | 0800AM | Salsa    |