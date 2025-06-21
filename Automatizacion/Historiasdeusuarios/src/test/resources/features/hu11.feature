#language: es
#author: Camilo Perez

Característica: Cancelación de clases
  Como usuario autenticado
  Quiero cancelar una clase
  Para desvincularme a una clase y dejar de visualizarla en mi calendario

  Antecedentes:
    Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
    Cuando ingrese las credenciales correctas (usuario y contrasena)
      | correo            | contraseña |
      | juanjop@gmail.com | 1234       |
    Dado que el usuario se ha autenticado

  @CancelarClase
  Escenario: Cancelar una clase previamente programada

    Cuando el usuario selecciona una clase programada y la cancela
      | profesor | hora   | clase   |
      | Tania    | 1030AM | Bachata |
    Entonces se deja de visualizar la clase programada