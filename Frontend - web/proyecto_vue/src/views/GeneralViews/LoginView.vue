<template>
  <body>
      <div class="container" ref="container">
          <div class="container-form">
              <form class="sign-in" @submit.prevent="submitFormSignIn">
                  <h2>Iniciar Sesión</h2>
                  <div class="social-networks">
                      <a href="https://www.instagram.com/ln7dancestudio_/" target="_blank">
                          <ion-icon name="logo-instagram"></ion-icon>
                      </a>
                      <a href="https://www.facebook.com/ln7dancestudio" target="_blank">
                          <ion-icon name="logo-facebook"></ion-icon>
                      </a>
                      <a href="https://www.youtube.com/@ledersonleon8197" target="_blank">
                          <ion-icon name="logo-youtube"></ion-icon>
                      </a>
                      <a href="https://www.tiktok.com/@lnsietedancestudio_" target="_blank">
                          <ion-icon name="logo-tiktok"></ion-icon>
                      </a>
                  </div>
                  <div class="container-input">
                      <ion-icon name="mail"></ion-icon>
                  <input type="email" placeholder="Correo" v-model="LoginData.email" required>
                  </div>
                  <div class="container-input">
                      <ion-icon name="lock-closed-outline"></ion-icon>
                      <input type="password" placeholder="Contraseña" v-model="LoginData.password" required>
                  </div><router-link to="/forgot-password" class="forgot-password-link">¿Olvidaste tu contraseña?</router-link>


                  <button class="button" type="submit">Iniciar Sesión</button>
              </form>
          </div>
          <div class="container-form" @submit.prevent="submitFormSignUp">
              <form class="sign-up">
                  <h2>Registrarse</h2>
                  <div class="container-input">
                      <ion-icon name="person-outline"></ion-icon>
                      <input type="text" placeholder="Nombres" v-model="RegisterData.name" required>
                  </div>
                  <div class="container-input">
                      <ion-icon name="person-outline"></ion-icon>
                      <input type="text" placeholder="Apellidos" v-model="RegisterData.surname" required>
                  </div>
                  <div class="container-input">
                      <ion-icon name="clipboard-outline"></ion-icon>
                      <select v-model="RegisterData.identification" required>
                          <option disabled selected=""></option>
                          <option>Cedula</option>
                          <option>Tarjeta de identidad</option>
                          <option>Pasaporte</option>
                          <option>Otro</option>
                      </select>
                  </div>
                  <div class="container-input">
                      <ion-icon name="id-card-outline"></ion-icon>
                      <input type="number" placeholder="Numero de identificación" v-model="RegisterData.identification_number" required>
                  </div>
                  <div class="container-input">
                      <ion-icon name="call-outline"></ion-icon>
                      <input type="number" placeholder="Telefono" v-model="RegisterData.phone" required>
                  </div>
                  <div class="container-input">
                      <ion-icon name="transgender-outline"></ion-icon>
                      <select v-model="RegisterData.gender" required>
                          <option disabled selected="">Genero</option>
                          <option>Masculino</option>
                          <option>Femenino</option>
                          <option>Otro</option>
                      </select>
                  </div>
                  <div class="container-input">
                      <ion-icon name="walk-outline"></ion-icon>
                      <input type="number" placeholder="Edad" v-model="RegisterData.age" required>
                  </div>
                  <div class="container-input">
                      <ion-icon name="person-outline"></ion-icon>
                      <input type="text" placeholder="Nombre de usuario" v-model="RegisterData.user" required>
                  </div>
                  <div class="container-input">
                      <ion-icon name="mail"></ion-icon>
                      <input type="email" placeholder="Correo" v-model="RegisterData.remail" required>
                  </div>
                  <div class="container-input">
                      <ion-icon name="lock-closed-outline"></ion-icon>
                      <input type="password" placeholder="Contraseña" v-model="RegisterData.rpassword" required>
                  </div>
                  <button class="button" type="submit">Registrarse</button>
              </form>
          </div>
          <div class="container-welcome">
              <div class="welcome-sign-up welcome">
                  <h3>Bienvenido</h3>
                  <p>Ingrese sus datos personales para usar todas las funciones del sitio</p>
                  <button @click="AlternateToggle" class="button">Registrarse</button>
              </div>
              <div class="welcome-sign-in welcome">
                  <h3>¡Hola!</h3>
                  <p>Registrarse con sus datos personales para usar todas las funciones del sitio</p>
                  <button @click="AlternateToggle" class="button">Iniciar Sesión</button>
              </div>
          </div>
      </div>
  </body>
</template>

<script>
//import axios from 'axios';
import { login } from "@/api/login.js";
import { register } from '@/api/register.js';
//import { error } from 'console';
// import { API_URL } from "@/api/api.js";

export default {
  data() {
    return {
      LoginData: {
        email: "",
        password: ""
      },
      RegisterData: {
        name: "",
        surname: "",
        identification: "",
        identification_number: "",
        phone: "",
        gender: "",
        age: "",
        user: "",
        remail: "",
        rpassword: "",
        address: "",
        birthdate: "",
        imagen: "",
        rol: "Estudiante" // estudiante
      }
    };
  },
  methods: {
    async submitFormSignIn() {
      try {

        const data = await login(this.LoginData);
        console.log("Login Response:", data);
        localStorage.setItem('token', data.token)
      
        if (data.rol === "estudiante") {
          localStorage.setItem('id_usuario', data.id_usuario);
          localStorage.setItem('correo', data.correo);
          localStorage.setItem('rol', data.rol);
          this.$router.push('/Estudiante/Inicio');
        } else if (data.rol === "administrador") {
          localStorage.setItem('correo', data.correo);
          localStorage.setItem('rol', data.rol);
          this.$router.push('/Admin/inicio');
        } else if (data.rol === "profesor") {
          localStorage.setItem('correo', data.correo);
          localStorage.setItem('rol', data.rol);
          this.$router.push('/profesor/Inicio');
        } else {
          alert("Correo o contraseña incorrectos");
        }
      } catch (error) {
        console.error("Error al iniciar sesión:", error);
      }
    }
,

    async submitFormSignUp() {
      try {
        const datos = {
          name: this.RegisterData.name,
          surname: this.RegisterData.surname,
          identification: this.RegisterData.identification,
          identification_number: this.RegisterData.identification_number,
          phone: this.RegisterData.phone,
          gender: this.RegisterData.gender,
          age: this.RegisterData.age,
          user: this.RegisterData.user,
          remail: this.RegisterData.remail,
          rpassword: this.RegisterData.rpassword,
          rol: "estudiante",
          address: "No registrado",
          imagen: "default.jpg"
        }
        console.log("Enviando:", datos);

        await register(datos)
          .then((res) => {
            console.log("Respuesta del servidor:", res?.data);
            if (res?.data?.success) {
              alert("Usuario registrado con éxito");
            } else {
              alert("Error al registrar: " + (res?.data?.error || "Respuesta no válida"));
            }
          }).catch((error) => {
            console.error("Error en el envío:", error);
            alert("Hubo un problema al registrar");
          });
        
        alert("Usuario registrado con éxito");

        // Limpiar los datos
        
        this.RegisterData = {
          name: "",
          surname: "",
          gender: "",
          identification: "",
          identification_number: "",
          age: "",
          phone: "",
          remail: "",
          rpassword: "",
          address: "",
          birthdate: "",
          imagen: "",
          rol: "Estudiante"
        };
      } catch (error) {
        console.error("Error en registro:", error);
        alert("Hubo un problema al registrar");
      }
    },

    AlternateToggle() {
      this.$refs.container.classList.toggle("toggle");
    }
  }
};
</script>


<style scoped>
@import '../../assets/styles/LoginView.css';
</style>
