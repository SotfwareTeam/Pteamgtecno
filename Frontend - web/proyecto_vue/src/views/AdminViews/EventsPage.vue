<template>
    <div>
      <NavbarAdmin />
      <br><br>
      <br><br>

      <div class="row text-center mt-4">
        <div class="col-md-4">
          <h3>Calendario</h3>
          <img src="../../../PUBLIC/ASSETS/img/salones.jpg" alt="Calendario" class="img-thumbnail" data-bs-toggle="modal" data-bs-target="#espaciosModal">
        </div>
        <div class="col-md-4">
          <h3>Profesores</h3>
          <img src="../../../PUBLIC/ASSETS/img/profesor.jpg" alt="Profesores" class="img-thumbnail" data-bs-toggle="modal" data-bs-target="#profesoresModal">
        </div>
        <div class="col-md-4">
          <h3>Competencias</h3>
          <img src="../../../PUBLIC/ASSETS/img/list.png" alt="Competencias" class="img-thumbnail" data-bs-toggle="modal" data-bs-target="#competenciasModal">
        </div>
      </div>
    </div>

    <div class="container custom-carousel mt-5">
      <h1 class="text-center">Eventos</h1>
      <div id="eventosCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="../../../Public/Assets/img/icons/Evento1.jpeg" class="d-block w-100" alt="Evento 1">
          </div>
          <div class="carousel-item">
            <img src="../../../Public/Assets/img/icons/Evento2.jpeg" class="d-block w-100" alt="Evento 2">
          </div>
          <div class="carousel-item">
            <img src="../../../Public/Assets/img/icons/Evento3.jpeg" class="d-block w-100" alt="Evento 3">
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#eventosCarousel" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Anterior</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#eventosCarousel" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Siguiente</span>
        </button>
      </div>
    </div>
    <br><br>

    <div class="modal fade" id="espaciosModal" tabindex="-1" aria-labelledby="espaciosModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="espaciosModalLabel">Calendario</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body">
            <p>Aquí puedes ver los detalles de los espacios disponibles.</p>
            <button class="btn btn-primary" onclick="window.location.href='/Admin/programacion_clases'">Ir a Calendario</button>
          </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="profesoresModal" tabindex="-1" aria-labelledby="profesoresModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="profesoresModalLabel">Nuestros Profesores</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-md-4">
                <div class="card">
                  <img src="../../../PUBLIC/ASSETS/img/user.jpg" class="card-img-top" alt="Profesor 1">
                  <div class="card-body">
                    <h5 class="card-title">Profesor 1</h5>
                    <p class="card-text">Experto en salsa y bachata.</p>
                    <button class="btn btn-primary"> Editar profesor</button>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="card">
                  <img src="../../../PUBLIC/ASSETS/img/user.jpg" class="card-img-top" alt="Profesor 2">
                  <div class="card-body">
                    <h5 class="card-title">Profesor 2</h5>
                    <p class="card-text">Especialista en tango y danza contemporánea.</p>
                    <button class="btn btn-primary"> Editar profesor</button>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="card">
                  <img src="../../../PUBLIC/ASSETS/img/user.jpg" class="card-img-top" alt="Profesor 3">
                  <div class="card-body">
                    <h5 class="card-title">Profesor 3</h5>
                    <p class="card-text">Instructor de hip-hop y breakdance.</p>
                    <button class="btn btn-primary"> Editar profesor</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="competenciasModal" tabindex="-1" aria-labelledby="competenciasModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="competenciasModalLabel">Detalles de Competencias</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body">
            <p>Información sobre las competencias y torneos.</p>
          </div>
        </div>
      </div>
    </div>

    <FooterAdmin />
  </template>

  <script>
  import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';
  import FooterAdmin from '@/components/footers/FooterAdmin.vue';

  export default {
    components: {
      NavbarAdmin,
      FooterAdmin,
    },
    mounted() {
      // Inicializar los componentes de Bootstrap (como el carrusel y los modals)
      const carouselElement = document.querySelector('#eventosCarousel');
      if (carouselElement) {
        new bootstrap.Carousel(carouselElement);
      }

      const espaciosModal = document.getElementById('espaciosModal');
      const profesoresModal = document.getElementById('profesoresModal');
      const competenciasModal = document.getElementById('competenciasModal');

      if (espaciosModal) {
        espaciosModal.addEventListener('show.bs.modal', event => {
          // Puedes agregar lógica aquí cuando se muestra el modal de espacios
        });
      }

      if (profesoresModal) {
        profesoresModal.addEventListener('show.bs.modal', event => {
          // Puedes agregar lógica aquí cuando se muestra el modal de profesores
        });
      }

      if (competenciasModal) {
        competenciasModal.addEventListener('show.bs.modal', event => {
          // Puedes agregar lógica aquí cuando se muestra el modal de competencias
        });
      }
    }
  };
  </script>

  <style scoped>
  /* Ajustes personalizados para el carrusel */
  .custom-carousel {
    max-width: 600px; /* Ancho máximo del carrusel */
    margin: 0 auto; /* Centrar el carrusel */
  }

  /* Ajustes para las imágenes del carrusel */
  .custom-carousel .carousel-inner img {
    height: 350px; /* Altura ajustada para las imágenes */
    object-fit: cover; /* Recorta la imagen para que ocupe el espacio */
  }

  /* Sección de imágenes */
  .col-md-4 img {
    max-height: 200px;
    object-fit: cover;
  }
  </style>
