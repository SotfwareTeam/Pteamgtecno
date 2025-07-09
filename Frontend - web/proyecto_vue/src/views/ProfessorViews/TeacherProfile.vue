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
import FooterTeachers from '@/components/footers/FooterTeachers.vue';
import NavbarTeachers from '@/components/navbars/NavbarTeachers.vue';
import axios from 'axios';

export default {
  name: 'TeacherProfile',
  components: {
    NavbarTeachers,
    FooterTeachers
  },
  data() {
    return {
      profileImage: '/Assets/img/user.jpg',
      selectedImageFile: null,
      teacherData: {
        name: '',
        email: '',
        phone: '',
        address: ''
      },
      teacherClasses: []
    };
  },
  mounted() {
    this.loadTeacherProfile();
    this.loadTeacherClasses();
  },
  methods: {
    handleImageUpload(event) {
      const file = event.target.files[0];
      if (file) {
        this.selectedImageFile = file;
        const reader = new FileReader();
        reader.onload = (e) => {
          this.profileImage = e.target.result; // Vista previa inmediata
        };
        reader.readAsDataURL(file);
      }
    },

    async loadTeacherProfile() {
      const correo = localStorage.getItem('correo');
      try {
        const response = await axios.get(`https://hqt8rl0q-80.use2.devtunnels.ms/backend/get_user_data.php?correo=${correo}`);
        const data = response.data;

        if (data.error) {
          console.error("❌ Usuario no encontrado:", data.error);
          return;
        }

        this.teacherData = {
          name: [data.nombre, data.apellido].filter(Boolean).join(' '),
          email: data.correo,
          phone: data.telefono,
          address: data.direccion
        };

        this.profileImage = data.imagen || '/Assets/img/user.jpg';
      } catch (error) {
        console.error('❌ Error cargando el perfil:', error);
      }
    },

    async loadTeacherClasses() {
      const id_usuario = localStorage.getItem('id_usuario');
      try {
        const res = await axios.get(`https://hqt8rl0q-80.use2.devtunnels.ms/backend/get_notas_usuario.php?id_usuario=${id_usuario}`);
        if (Array.isArray(res.data)) {
          this.teacherClasses = res.data.filter(n => n.name && n.grade !== null);
        } else {
          this.teacherClasses = [];
        }
      } catch (err) {
        console.error("❌ Error al cargar clases:", err);
        this.teacherClasses = [];
      }
    },

    async saveChanges() {
      const formData = new FormData();
      const [nombre, ...rest] = this.teacherData.name.trim().split(' ');
      const apellido = rest.join(' ') || '';

      formData.append('nombre', nombre);
      formData.append('apellido', apellido);
      formData.append('telefono', this.teacherData.phone);
      formData.append('direccion', this.teacherData.address);
      formData.append('correo', this.teacherData.email);

      if (this.selectedImageFile) {
        formData.append('imagen', this.selectedImageFile);
      }

      try {
        const res = await axios.post('https://hqt8rl0q-80.use2.devtunnels.ms/backend/update_user_by_full.php', formData);
        console.log("✅ Respuesta:", res.data);

        if (res.data.success) {
          alert('Cambios guardados con éxito');
        } else {
          alert('Error al guardar: ' + res.data.error);
        }
      } catch (err) {
        console.error('❌ Error al guardar:', err);
        alert('Error de red al guardar');
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
