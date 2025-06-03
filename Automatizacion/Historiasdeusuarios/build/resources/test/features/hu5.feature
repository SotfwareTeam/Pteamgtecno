#language: es
#author: Miguel Montaño

Característica: Modificación de perfil y cierre de sesión
  Como usuario autenticado en el sistema
  Quiero poder modificar mis datos personales y cerrar sesión
  Para actualizar mi información y proteger el acceso a mi cuenta

  @perfil @cerrarsesion
   Escenario: Modificar información del perfil y cerrar sesión exitosamente
    Dado que el usuario se encuentra en la pagina de perfil
    Cuando el usuario hace clic en el ícono de perfil ubicado en la parte superior derecha y registra nueva informacion
      | nombre    | email                 | telefono   | direccion       |
      | Juan Peña | jpenaquinoz@gmail.com | 3184217657 | calle 163#62-95 |
    Entonces se debe mostrar que la informacion cambio y poder salir del aplicativo




