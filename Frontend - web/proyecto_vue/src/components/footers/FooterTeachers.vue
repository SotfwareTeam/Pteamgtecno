<template>
    <footer class="footer">
      <ul class="social-icon">
          <li class="icon-elem">
              <a href="" class="icon">
                  <ion-icon name="logo-youtube"></ion-icon>
              </a>
          </li>
          <li class="icon-elem">
              <a href="" class="icon">
                  <ion-icon name="logo-instagram"></ion-icon>
              </a>
          </li>
          <li class="icon-elem">
              <a href="" class="icon">
                  <ion-icon name="logo-tiktok"></ion-icon>
              </a>
          </li>
          <li class="icon-elem">
              <a href="" class="icon">
                  <ion-icon name="logo-facebook"></ion-icon>
              </a>
          </li>
          <li class="icon-elem">
              <a href="" class="icon">
                  <ion-icon name="logo-whatsapp"></ion-icon>
              </a>
          </li>
      </ul>
      <ul class="menu">
          <li class="menu-elem">
              <router-link to="/TeacherPage" class="menu-icon">Inicio</router-link>
          </li>
          <li class="menu-elem">
              <a href="#" class="menu-icon">Equipo</a>
          </li>
          <li class="menu-elem">
              <a href="#" class="menu-icon">Contacto</a>
          </li>
          <li class="menu-elem">
              <a href="#" class="menu-icon">Video</a>
          </li>
      </ul>
      <p class="text">@Año | todos los derechos reservados</p>
    </footer>
  </template>
  
  <script>
  export default {
    name: 'FooterTeachers'
  }
  </script>
  
  <style scoped>
  .footer{
  position: relative;
  width: 100%;
  background-color: rgb(0, 0, 0);
  min-height: 100px;
  padding: 20px 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.social-icon{
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  margin: 10px 5px;
  flex-wrap: wrap;
}

.icon-elem{
  list-style: none;
}

.icon{
  color: rgb(255, 255, 255);
  font-size: 32px;
  display: inline-block;
  margin: 0 10px;
  transition: 0.5s;
}
.iconp{
  color: rgb(255, 255, 255);
  font-size: 40px;
  display: inline-block;
  margin: 0 10px;
  transition: 0.5s;
}

.icon:hover{
  transform: translateY(-10px);
}

.menu{
  display: flex;
  justify-content: center;
  position: relative;
  align-items: center;
  margin: 10px 0;
  flex-wrap: wrap;
}
.menu-elem{
  list-style: none;
}

.menu-icon{
  color:rgb(255, 255, 255);
  font-size: 20px;
  display: inline-block;
  text-decoration: none;
  margin: 5px 10px;
  opacity: 0.5;
  transition: 0.3s;
}

.menu-icon:hover{
  opacity: 1;
}

.text{
  color:beige;
  text-align: center;
  margin-top: 15px;
  margin-bottom: 10px;
  font-size: 20px;
}
  </style>