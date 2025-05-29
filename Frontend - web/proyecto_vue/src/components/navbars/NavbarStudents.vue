<template>
    <nav class="navbar navbar-expand-lg fixed-top nb">
      <div class="container-fluid">
        <router-link class="navbar-brand me-auto" to="/StudentPage">
          <img class="imgt" src="../../../PUBLIC/ASSETS/img/icons/Sin título.png" alt="">
        </router-link>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar"
             aria-labelledby="offcanvasNavbarLabel">
          <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasNavbarLabel">
              <img src="../../../PUBLIC/ASSETS/img/icons/Sin título.png" alt="">
            </h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
          </div>
          <div class="offcanvas-body">
            <ul class="navbar-nav justify-content-center flex-grow-1 pe-3">
              <li class="nav-item">
                <router-link class="nav-link mx-lg-2" aria-current="page" to="/Estudiante/Inicio">Inicio</router-link>
              </li>
              <li class="nav-item">
                <router-link class="nav-link mx-lg-2" to="/Estudiante/Eventos">Eventos</router-link>
              </li>
              <li class="nav-item">
                <router-link class="nav-link mx-lg-2" to="/Estudiante/Programar_clase">Programacion clases</router-link>
              </li>
            </ul>
          </div>
        </div>
        <router-link to="/Estudiante/Perfil" class="icon iconp">
          <ion-icon name="person-circle-outline"></ion-icon>
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
    </nav>
  </template>

  <script>
  export default {
    name: 'NavbarStudents'
  }
  </script>

  <style scoped>
.imgt{
  width: 110px;
  height: 50px;
}

.nb{
  background: repeating-linear-gradient(#5f2f9a, #b269cd);
}

.navbar-brand{
  font-weight: 500;
  color: rgb(254, 254, 254);
  font-size: 24px;
  transition: 0.3s color;
}


.navbar-toggler {
border: none;
font-size: 1.25rem;
}
.navbar-toggler:focus,.btn-close {
box-shadow: none;
outline: none;
}
.nav-link {
color: rgb(0, 0, 0);
font-weight: 500;
position: relative;
}

.nav-link:hover, .nav-link.active {
color: rgb(255, 255, 255);
}

.nav-link:hover::before,
.nav-link:active::before {
  width: 100%;
  visibility: visible;
}
  </style>
