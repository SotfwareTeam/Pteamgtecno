#language:es
  #author:Camilo Perez

Característica: Programación de clases
  Como usuario autenticado
  Quiero programar una clase
  Para vincularme a una clase y visualizarla posteriormente en mi calendario

  Antecedentes:
    Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
    Cuando ingrese las credenciales correctas (usuario y contrasena)
      | correo                 | contraseña |
      | sotfwareteam@gmail.com | 1234       |

  @ProgramarClase
  Esquema del escenario: Programar una clase

    Dado que el usuario se ha autenticado
    Cuando el usuario diligencia los campos requeridos
      | profesor   | hora   | clase   |
      | <profesor> | <hora> | <clase> |
    Entonces se visualiza la clase programada

    Ejemplos:
      | profesor | hora   | clase    |
      | Carlos   | 0800AM | Salsa    |