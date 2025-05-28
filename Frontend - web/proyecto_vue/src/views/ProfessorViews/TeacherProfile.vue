<template>
  <div>
    <NavbarTeachers />

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
          <h2>Perfil del Profesor</h2>
          <form @submit.prevent="saveChanges">
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombre:</label>
              <input
                type="text"
                class="form-control"
                id="nombre"
                v-model="teacherData.name"
              >
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">Correo Electrónico:</label>
              <input
                type="email"
                class="form-control"
                id="email"
                v-model="teacherData.email"
              >
            </div>
            <div class="mb-3">
              <label for="telefono" class="form-label">Teléfono:</label>
              <input
                type="tel"
                class="form-control"
                id="telefono"
                v-model="teacherData.phone"
              >
            </div>
            <div class="mb-3">
              <label for="direccion" class="form-label">Dirección:</label>
              <input
                type="text"
                class="form-control"
                id="direccion"
                v-model="teacherData.address"
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
            v-for="(classItem, index) in teacherClasses"
            :key="index"
          >
            {{ classItem.name }} - Nota: {{ classItem.grade }}
          </li>
        </ul>
      </div>
    </div>

    <FooterTeachers />
  </div>
</template>

<script>
import FooterTeachers from '@/components/footers/FooterTeachers.vue'
import NavbarTeachers from '@/components/navbars/NavbarTeachers.vue'
import axios from 'axios'

export default {
  name: 'TeacherProfile',
  components: {
    NavbarTeachers,
    FooterTeachers
  },
  data() {
    return {
      profileImage: '/Assets/img/user.jpg',
      teacherData: {
        name: '',
        email: '',
        phone: '',
        address: ''
      },
      teacherClasses: []
    }
  },
  mounted() {
    this.loadTeacherProfile();
  },
  methods: {
    handleImageUpload(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.profileImage = e.target.result;
        }
        reader.readAsDataURL(file);
      }
    },
    async loadTeacherProfile() {
      const correo = localStorage.getItem('correo');
      try {
        const response = await axios.get(`http://localhost/backend/get_user_by_email.php?correo=${correo}`);
        const data = response.data;

        this.teacherData = {
          name: data.nombre || '',
          email: data.correo || '',
          phone: data.telefono || '',
          address: data.direccion || ''
        };

        console.log("Perfil cargado:", this.teacherData);

      } catch (error) {
        console.error('Error cargando el perfil:', error);
      }
    },
    async saveChanges() {
  try {
    const response = await axios.post('http://localhost/backend/update_user_by_email.php', this.teacherData, {
      headers: { 'Content-Type': 'application/json' }
    });

    if (response.data.success) {
      alert('Perfil actualizado exitosamente');
      console.log('Datos enviados:', this.teacherData);
    } else {
      alert('Error al actualizar perfil');
      console.error('Error de backend:', response.data.error);
    }
  } catch (error) {
    console.error('Error al guardar cambios:', error);
    alert('Error al conectar con el servidor');
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
