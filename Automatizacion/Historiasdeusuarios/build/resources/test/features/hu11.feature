#language: es
#author: Camilo Perez

Característica: Cancelación de clases
  Como usuario autenticado
  Quiero cancelar una clase
  Para desvincularme a una clase y dejar de visualizarla en mi calendario

  Antecedentes:
    Dado que el usuario ingresa al calendario
    Cuando el usuario diligencia los campos requeridos
      | profesor | hora   | clase    |
      | Carlos   | 0800AM | Salsa    |

  @CancelarClase
  Escenario: Cancelar una clase previamente programada

    Cuando el usuario selecciona una clase programada y la cancela
      | profesor | hora   | clase   |
      | Tania    | 1030AM | Bachata |
    Entonces se deja de visualizar la clase programada