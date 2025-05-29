<template>
    <div>
      <NavbarTeachers />

      <div class="container mt-5">
        <div class="row text-center mt-4">
          <div class="col-md-4">
            <h3>Calendario</h3>
            <img src="/Assets/img/salones.jpg" alt="Calendario" class="img-thumbnail"
                 data-bs-toggle="modal" data-bs-target="#espaciosModal">
          </div>
          <div class="col-md-4">
            <h3>Profesores</h3>
            <img src="/Assets/img/profesor.jpg" alt="Profesores" class="img-thumbnail"
                 data-bs-toggle="modal" data-bs-target="#profesoresModal">
          </div>
          <div class="col-md-4">
            <h3>Competencias</h3>
            <img src="/Assets/img/list.png" alt="Competencias" class="img-thumbnail"
                 data-bs-toggle="modal" data-bs-target="#competenciasModal">
          </div>
        </div>
      </div>

      <!-- Carrusel -->
      <div class="container custom-carousel mt-5">
        <h1 class="text-center">Eventos</h1>
        <div id="eventosCarousel" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="/Assets/img/icons/Evento1.jpeg" class="d-block w-100" alt="Evento 1">
            </div>
            <div class="carousel-item">
              <img src="/Assets/img/icons/Evento2.jpeg" class="d-block w-100" alt="Evento 2">
            </div>
            <div class="carousel-item">
              <img src="/Assets/img/icons/Evento3.jpeg" class="d-block w-100" alt="Evento 3">
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

      <!-- Modales -->
      <div class="modal fade" id="espaciosModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Calendario</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
              <p>Aquí puedes ver los detalles de los espacios disponibles.</p>
              <router-link to="/Profesor/Calendario" class="btn btn-primary">Ir a Calendario</router-link>
            </div>
          </div>
        </div>
      </div>

      <div class="modal fade" id="profesoresModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Nuestros Profesores</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
              <div class="row">
                <div class="col-md-4" v-for="(profesor, index) in profesores" :key="index">
                  <div class="card">
                    <img :src="profesor.imagen" class="card-img-top" :alt="'Profesor ' + (index + 1)">
                    <div class="card-body">
                      <h5 class="card-title">{{ profesor.nombre }}</h5>
                      <p class="card-text">{{ profesor.descripcion }}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="modal fade" id="competenciasModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Detalles de Competencias</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
              <p>Información sobre las competencias y torneos.</p>
            </div>
          </div>
        </div>
      </div>

      <FooterTeachers />
    </div>
  </template>

  <script>
import FooterTeachers from '@/components/footers/FooterTeachers.vue';
import NavbarTeachers from '@/components/navbars/NavbarTeachers.vue';



  export default {
    name: 'EventsTeachers',
    components: {
      NavbarTeachers,
      FooterTeachers
    },
    data() {
      return {
        profesores: [
          {
            imagen: '/Assets/img/user.jpg',
            nombre: 'Profesor 1',
            descripcion: 'Experto en salsa y bachata.'
          },
          {
            imagen: '/Assets/img/user.jpg',
            nombre: 'Profesor 2',
            descripcion: 'Especialista en tango y danza contemporánea.'
          },
          {
            imagen: '/Assets/img/user.jpg',
            nombre: 'Profesor 3',
            descripcion: 'Instructor de hip-hop y breakdance.'
          }
        ]
      }
    },
    mounted() {
      // Inicializar carrusel de Bootstrap
      new bootstrap.Carousel(document.getElementById('eventosCarousel'), {
        interval: 3000,
        ride: 'carousel'
      })
    }
  }
  </script>

  <style scoped>
  /* Estilos del carrusel */
  .custom-carousel {
    max-width: 600px;
    margin: 0 auto;
  }

  .custom-carousel .carousel-inner img {
    height: 350px;
    object-fit: cover;
  }

  /* Estilos para las imágenes */
  .col-md-4 img {
    max-height: 200px;
    object-fit: cover;
    cursor: pointer;
    transition: transform 0.3s;
  }

  .col-md-4 img:hover {
    transform: scale(1.05);
  }

  /* Espaciado */
  .container {
    padding-top: 20px;
    padding-bottom: 20px;
  }
  </style>
