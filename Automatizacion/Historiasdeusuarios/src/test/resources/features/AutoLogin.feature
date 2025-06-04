#language:es
  #author:JuanJose


Característica: Autenticacion en la pagina de Dance
  como usuario de Dance
  Quiero autenticarme en el portal de Dance
  Para poder acceder al contenido y funcionalidades disponibles en mi cuenta.

  Escenario: Verificar la autenticacion exitosa en la pagina de Applitools
    Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
    Cuando ingrese las credenciales correctas (usuario y contrasena)
      | correo                  | contraseña |
      | jpenaquinonez@gmail.com | 1234       |
    Entonces se debe verificar que el usuario haya sido autenticado correctamente y redirigido a su pagina de inicio de Dance