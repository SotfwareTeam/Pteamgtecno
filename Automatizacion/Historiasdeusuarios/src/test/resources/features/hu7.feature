#language: es
#author: Miguel Montaño

Característica: Visualización de profesores destacados
    Como usuario registrado en la plataforma
    Quiero consultar los profesores disponibles desde la sección de eventos
    Para conocer quiénes enseñan en la escuela de baile

  Antecedentes:
    Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
    Cuando ingrese las credenciales correctas de ingreso (usuario y contraseña)
      | correo    | contraseña |
      | mn@mn.com | 123456789  |

    @eventos @profesores
    Escenario: Visualizar profesores desde la sección de eventos
      Cuando hace clic en el botón "Eventos" de la barra de navegación y en el modulo calendario
      Entonces debe visualizarse una sección emergente con los profesores más destacados
