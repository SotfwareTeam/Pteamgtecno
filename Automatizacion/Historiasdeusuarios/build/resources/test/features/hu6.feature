#language: es
#author: Miguel Montaño

 Característica: Visualización del calendario de clases
    Como usuario autenticado del sistema
    Quiero acceder al calendario de clases desde la sección de eventos
    Para conocer las clases programadas y su horario

   Antecedentes:
     Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
     Cuando ingrese las credenciales correctas (usuario y contraseña)
       | correo     | contraseña   |
       | mn2@mn.com | 201381828Mm. |

   @eventos @calendario

    Escenario: Acceder al calendario de clases desde eventos
     Dado que el usuario se encuentra en la pagina de inicio
     Cuando hace clic en el botón Eventos de la barra de navegación y en el modulo calendario
     Entonces debe visualizarse la página de calendario con las clases programadas
