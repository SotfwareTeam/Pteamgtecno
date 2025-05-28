#language: es
#author: Miguel Montaño

  Característica: Visualización de competencias de la escuela
  Como usuario registrado en la plataforma
  Quiero ver las competencias en las que participa la escuela
  Para estar informado sobre sus eventos y logros

  @eventos @competencias
    Escenario: Consultar competencias desde la sección de eventos
     Dado que el usuario se encuentra en la página principal
      Cuando hace clic en el botón "Eventos" de la barra de navegación
      Y hace clic en la imagen llamada "Competencias"
     Entonces debe mostrarse una sección emergente con información gestionada por el administrador
