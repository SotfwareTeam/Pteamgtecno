#language: es
#author: Miguel Montaño

Característica: Visualización de profesores destacados
    Como usuario registrado en la plataforma
    Quiero consultar los profesores disponibles desde la sección de eventos
    Para conocer quiénes enseñan en la escuela de baile

    @eventos @profesores
    Escenario: Visualizar profesores desde la sección de eventos
      Dado que el usuario se encuentra en la página principal
      Cuando hace clic en el botón "Eventos" de la barra de navegación
      Y hace clic en la imagen llamada "profesores"
      Entonces debe visualizarse una sección emergente con los profesores más destacados
