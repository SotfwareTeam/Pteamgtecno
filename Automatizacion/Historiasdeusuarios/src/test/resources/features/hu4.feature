#language: es
#author: Camilo Perez

Característica: Visualización de información de clases
  Como usuario autenticado
  Quiero ver la información de las clases a las que estoy vinculado

  Antecedentes:
    Dado que el usuario ingresa al calendario
    Cuando el usuario diligencia los campos requeridos
      | profesor | hora   | clase |
      | Carlos   | 0800AM | Salsa |

  @flujo
  Escenario: Ver detalles de una clase programada

    Cuando el usuario selecciona una fecha con una clase programada
    Entonces se visualizan las clases programadas de esa fecha