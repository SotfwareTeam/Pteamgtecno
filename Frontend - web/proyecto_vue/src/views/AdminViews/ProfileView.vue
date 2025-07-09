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
                  <button to="/" @click="logout" class="btn btn-primary">Cerrar sesión</button>
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
import FooterAdmin from '@/components/footers/FooterAdmin.vue';
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';
import axios from 'axios';

export default {
  name: 'admProfile',
  components: {
    NavbarAdmin,
    FooterAdmin
  },
  data() {
    return {
      profileImage: '/Assets/img/user.jpg',
      selectedImageFile: null,
      admData: {
        name: '',
        email: '',
        phone: '',
        address: ''
      },
      admClasses: []
    };
  },
  mounted() {
    this.getAdminData();
  },
  methods: {
    handleImageUpload(event) {
      const file = event.target.files[0];
      if (file) {
        this.selectedImageFile = file;
        const reader = new FileReader();
        reader.onload = (e) => {
          this.profileImage = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },

    logout(){
      localStorage.removeItem('token'),
      localStorage.removeItem('correo'),
      localStorage.removeItem('rol'),
      this.$router.push('/')
    },

    async getAdminData() {
  const correo = localStorage.getItem("correo");

  try {
    const res = await axios.get(`https://hqt8rl0q-80.use2.devtunnels.ms/backend/get_user_data.php?correo=${correo}`);
    const data = res.data;

    if (!data.error) {
      this.admData = {
        name: [data.nombre, data.apellido].filter(Boolean).join(' '),
        email: data.correo,
        phone: data.telefono,
        address: data.direccion
      };

      // Usamos endpoint PHP para mostrar la imagen
      this.profileImage = `https://hqt8rl0q-80.use2.devtunnels.ms/backend/get_user_image.php?correo=${data.correo}`;
    } else {
      alert('Usuario no encontrado');
    }
  } catch (error) {
    console.error('Error al obtener datos del admin:', error);
  }
}
,

    async saveChanges() {
      const formData = new FormData();
      const [nombre, ...rest] = this.admData.name.trim().split(' ');
      const apellido = rest.join(' ') || '';

      formData.append('nombre', nombre);
      formData.append('apellido', apellido);
      formData.append('telefono', this.admData.phone);
      formData.append('direccion', this.admData.address);
      formData.append('correo', this.admData.email);

      if (this.selectedImageFile) {
        formData.append('imagen', this.selectedImageFile);
      }

      try {
        const res = await axios.post('https://hqt8rl0q-80.use2.devtunnels.ms/backend/update_user_full.php', formData);
        console.log("✅ Respuesta:", res.data);

        if (res.data.success) {
          alert('Cambios guardados exitosamente');
        } else {
          alert('Error al guardar los cambios: ' + (res.data.error || ''));
        }
      } catch (error) {
        console.error('Error al guardar cambios:', error);
      }
    }
  }
};
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
