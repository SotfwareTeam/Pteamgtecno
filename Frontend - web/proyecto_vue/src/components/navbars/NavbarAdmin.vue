<template>
 <nav class="navbar navbar-expand-lg fixed-top nb">
    <div class="container-fluid">
      <router-link class="navbar-brand me-auto" to="/AdminView">
        <img class="imgt" src="../../../PUBLIC/ASSETS/img/icons/Sin título.png" alt="">
      </router-link>
      <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasNavbarLabel">
            <img src="../../../PUBLIC/ASSETS/img/icons/Sin título.png" alt="">
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
          <ul class="navbar-nav justify-content-center flex-grow-1 pe-3">
            <li class="nav-item">
              <router-link class="nav-link mx-lg-2" to="/Admin/inicio">Home</router-link> <!-- correcto -->
            </li>
            <li class="nav-item">
              <router-link class="nav-link mx-lg-2" to="/Admin/eventos">Eventos</router-link> <!-- podrías crear ruta aparte si quieres algo diferente -->
            </li>
            <li class="nav-item">
              <router-link class="nav-link mx-lg-2" to="/Admin/Reporte">Reportes</router-link> <!-- correcto -->
            </li>
            <li class="nav-item">
              <router-link class="nav-link mx-lg-2" to="/Admin/Notificaciones">Notificaciones</router-link> <!-- ¡ojo! acá falta ruta específica si quieres otra vista -->
            </li>
            <li class="nav-item">
              <router-link class="nav-link mx-lg-2" to="/Roles">Administración de Roles</router-link> <!-- correcto -->
            </li>
          </ul>
        </div>
      </div>
      <router-link to="/Admin/profile" class="icon iconp"> <!-- ajusta si quieres un profile de admin -->
        <ion-icon name="person-circle-outline"></ion-icon>
      </router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    </div>
  </nav>
  </template>

  <script>
  export default {
    name: 'NavbarAdmin'
  }
  </script>

  <style scoped>
  /* Estilos específicos del navbar admin si son necesarios */
  </style>
