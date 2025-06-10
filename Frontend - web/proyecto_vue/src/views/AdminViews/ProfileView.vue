<template>
    <div>
      <NavbarAdmin />

      <br><br><br><br>

      <div class="container mt-4">
        <div class="row">
          <div class="col-md-3">
            <img :src="profileImage" alt="Foto de Perfil" class="img-thumbnail">
            <input
              type="file"
              class="form-control mt-2"
              @change="handleImageUpload"
              accept="image/*"
            >
          </div>
          <div class="col-md-9">
            <h2>Perfil del Administrador</h2>
            <form @submit.prevent="saveChanges">
              <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input
                  type="text"
                  class="form-control"
                  id="nombre"
                  v-model="admData.name"
                >
              </div>
              <div class="mb-3">
                <label for="email" class="form-label">Correo Electrónico:</label>
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  v-model="admData.email"
                >
              </div>
              <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono:</label>
                <input
                  type="tel"
                  class="form-control"
                  id="telefono"
                  v-model="admData.phone"
                >
              </div>
              <div class="mb-3">
                <label for="direccion" class="form-label">Dirección:</label>
                <input
                  type="text"
                  class="form-control"
                  id="direccion"
                  v-model="admData.address"
                >
              </div>
              <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <div class="mb-3">
                  <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                </div>
                <div class="mb-3">
                  <router-link to="/" class="btn btn-primary">Cerrar sesión</router-link>
                </div>
              </div>
            </form>
          </div>
        </div>

        <div class="mt-4">
          <h3>Clases y Notas</h3>
          <ul class="list-group">
            <li
              class="list-group-item"
              v-for="(classItem, index) in admClasses"
              :key="index"
            >
              {{ classItem.name }} - Nota: {{ classItem.grade }}
            </li>
          </ul>
        </div>
      </div>

      <FooterAdmin />
    </div>
  </template>


<script>
import FooterAdmin from '@/components/footers/FooterAdmin.vue'
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue'

export default {
  name: 'admProfile',
  components: {
    NavbarAdmin,
    FooterAdmin
  },
  data() {
    return {
      profileImage: '/Assets/img/user.jpg',
      admData: {
        name: '',
        email: '',
        phone: '',
        address: ''
      },
      admClasses: [
        { name: 'Clase de Salsa', grade: 4.5 },
        { name: 'Clase de Bachata', grade: 3.8 }
      ],
      testEmail: 'sotfwareteam@gmail.com' // este debe coincidir con el correo del admin logueado
    }
  },
  mounted() {
    this.getAdminData()
  },
  methods: {
    handleImageUpload(event) {
      const file = event.target.files[0]
      if (file) {
        const reader = new FileReader()
        reader.onload = (e) => {
          this.profileImage = e.target.result
        }
        reader.readAsDataURL(file)
      }
    },

    async getAdminData() {
      try {
        const response = await fetch(`http://localhost/backend/get_user_by_email.php?correo=${this.testEmail}`)
        const data = await response.json()

        if (!data.error) {
          this.admData.name = data.nombre
          this.admData.email = data.correo
          this.admData.phone = data.telefono
          this.admData.address = data.direccion
        } else {
          alert('Usuario no encontrado')
        }
      } catch (error) {
        console.error('Error al obtener datos del admin:', error)
      }
    },

    async saveChanges() {
      try {
        const response = await fetch('http://localhost/backend/update_user_by_email.php', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            email: this.admData.email,
            name: this.admData.name,
            phone: this.admData.phone,
            address: this.admData.address
          })
        })

        const data = await response.json()
        if (data.success) {
          alert('Cambios guardados exitosamente')
        } else {
          alert('Error al guardar los cambios: ' + (data.error || ''))
        }
      } catch (error) {
        console.error('Error al guardar cambios:', error)
      }
    }
  }
}
</script>



  <style scoped>
  .img-thumbnail {
    width: 100%;
    height: auto;
  }

  .card {
    margin-top: 20px;
    margin: 10px 0;
  }

  .container {
    background-color: #ffffff;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 80px; /* Para compensar el navbar fixed */
    margin-bottom: 40px;
  }

  .list-group-item {
    margin-bottom: 5px;
  }

  .btn {
    margin-right: 10px;
  }

  /* Ajustes para el formulario */
  .form-control {
    margin-bottom: 15px;
  }
  </style>
