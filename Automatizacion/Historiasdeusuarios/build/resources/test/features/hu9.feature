#language: es
#author: Miguel Montaño

  Característica: Visualización de competencias de la escuela
  Como usuario registrado en la plataforma
  Quiero ver las competencias en las que participa la escuela
  Para estar informado sobre sus eventos y logros

    Antecedentes:
      Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
      Cuando ingrese las credenciales correctas (usuario y contraseña)
        | correo     | contraseña   |
        | mn2@mn.com | 201381828Mm. |


    @eventos @competencias
    Escenario: Visualizar competencias desde la sección de eventos
      Cuando hace clic en el botón "Competencias" de la barra de navegación y en el modulo Competencias
     Entonces debe mostrarse una sección emergente con información gestionada por el administrador para las competencias
