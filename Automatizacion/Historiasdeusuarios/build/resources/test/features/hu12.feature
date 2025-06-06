#language: es
#author: Camilo Perez

Característica: Edición de clases
  Como usuario autenticado
  Quiero editar una clase
  Para vincularme a otra clase y visualizarla posteriormente en mi calendario

  Antecedentes:
    Dado que el usuario ingresa al calendario
    Cuando el usuario diligencia los campos requeridos
      | profesor | hora   | clase   |
      | Tania    | 1030AM | Bachata |

  @EditarClase
  Escenario: Editar una clase programada

    Cuando el usuario selecciona una clase programada y la edita
      | profesor | hora   | clase |
      | Cristina | 0500PM | Tango |
    Entonces se visualiza la clase ya editada