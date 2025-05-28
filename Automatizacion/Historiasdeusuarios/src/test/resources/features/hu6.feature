#language: es
#author: Miguel Montaño

 Característica: Visualización del calendario de clases
    Como usuario autenticado del sistema
    Quiero acceder al calendario de clases desde la sección de eventos
    Para conocer las clases programadas y su horario

    @eventos @calendario

    Escenario: Acceder al calendario de clases desde eventos
      Dado que el usuario se encuentra en la página principal
      Cuando hace clic en el botón "Eventos" de la barra de navegación
       Y hace clic en la imagen llamada "clases"
       Y hace clic en el botón "Clases"
       Entonces debe visualizarse la página de calendario con las clases programadas
