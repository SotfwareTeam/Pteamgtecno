#language: es
#author: Camilo Perez

Característica: Edición de clases
  Como usuario autenticado
  Quiero editar una clase
  Para vincularme a otra clase y visualizarla posteriormente en mi calendario

  Antecedentes:
    Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
    Cuando ingrese las credenciales correctas (usuario y contrasena)
      | correo            | contraseña |
      | juanjop@gmail.com | 1234       |
    Dado que el usuario se ha autenticado
    Cuando el usuario diligencia los campos requeridos
      | profesor | hora   | clase   |
      | Tania    | 1030AM | Bachata |

  @EditarClase
  Escenario: Editar una clase programada

    Cuando el usuario selecciona una clase programada y la edita
      | profesor | hora   | clase |
      | Cristina | 0500PM | Tango |
    Entonces se visualiza la clase ya editada