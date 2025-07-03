#language: es
#author: Miguel Montaño

Característica: Modificación de perfil y cierre de sesión
  Como usuario autenticado en el sistema
  Quiero poder modificar mis datos personales y cerrar sesión
  Para actualizar mi información y proteger el acceso a mi cuenta

  Antecedentes:
    Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
    Cuando ingrese las credenciales correctas (usuario y contraseña)
      | correo     | contraseña   |
      | mn2@mn.com | 201381828Mm. |

  @perfil @cerrarsesion
   Escenario: Modificar información del perfil y cerrar sesión exitosamente
    Dado el usuario se dirija a la pantalla de perfil
    Cuando el usuario hace clic en el ícono de perfil ubicado en la parte superior derecha y registra nueva informacion
      | nombre    | email                 | telefono   | direccion       |
      | Juan Peña | jpenaquinoz@gmail.com | 3184217657 | calle 163#62-95 |
    Entonces se debe mostrar que la informacion cambio y poder salir del aplicativo




