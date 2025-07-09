#language: es
#author: Camilo Perez

Característica: Visualización de información de clases
  Como usuario autenticado
  Quiero ver la información de las clases a las que estoy vinculado

  Antecedentes:
    Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
    Cuando ingrese las credenciales correctas (usuario y contrasena)
      | correo                 | contraseña |
      | sotfwareteam@gmail.com | 1234       |
    Dado que el usuario se ha autenticado
    Cuando el usuario diligencia los campos requeridos
      | profesor | hora   | clase |
      | Carlos   | 0800AM | Salsa |

  @flujo
  Escenario: Ver detalles de una clase programada

    Cuando el usuario selecciona una fecha con una clase programada
    Entonces se visualizan las clases programadas de esa fecha