# language: es
# author: JuanJose

Característica:Registro completo de nuevos usuarios en la plataforma
  Como visitante del sitio
  Quiero poder registrarme mediante el formulario "New User Signup!" y completar mi información
  Para crear una cuenta y acceder a funcionalidades personalizadas

  @registro
  Escenario: Registro de usuario completando todos los pasos del formulario
    Dado que el visitante está en la página de register
    Cuando ingrese los datos necesarios
      | nombres | apellido | numerodeidentificacion | telefono  | edad | nombredeusuario | correo         | contraseña |
      | dfghjk  | dfghqd   | 234567                 | 345674789 | 76   | yik             | rtyu@gmail.com | 9663937ty* |
    Entonces la cuenta debe ser creada y el usuario redirigido a la vista de login