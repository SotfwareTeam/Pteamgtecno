#language: es
#author: Miguel Montaño

  Característica: Visualización de competencias de la escuela
    Como usuario registrado en la plataforma
    Quiero ver las competencias en las que participa la escuela
    Para estar informado sobre sus eventos y logros

    Antecedentes:
      Dado que el usuario se encuentra en la pagina de inicio de sesion de Dance
      Cuando ingrese las credenciales correctas (usuario y contraseña)
        | correo                  | contraseña |
        | jpenaquinonez@gmail.com | 1234       |


    @eventos @profesores
  Escenario: Visualizar a los profesores desde la sección de Eventos
  Cuando hace clic en el botón Profesores
  Entonces debe mostrarse una sección emergente con información gestionada por el administrador para losprofesores