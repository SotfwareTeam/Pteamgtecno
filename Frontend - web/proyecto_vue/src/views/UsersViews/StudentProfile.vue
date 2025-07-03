<template>
    <div>
      <NavbarStudents />

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
            <h2>Perfil del Estudiante</h2>
            <form @submit.prevent="saveChanges">
              <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input
                  type="text"
                  class="form-control"
                  id="nombre"
                  v-model="studentData.name"
                >
              </div>
              <div class="mb-3">
                <label for="email" class="form-label">Correo Electrónico:</label>
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  v-model="studentData.email"
                >
              </div>
              <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono:</label>
                <input
                  type="tel"
                  class="form-control"
                  id="telefono"
                  v-model="studentData.phone"
                >
              </div>
              <div class="mb-3">
                <label for="direccion" class="form-label">Dirección:</label>
                <input
                  type="text"
                  class="form-control"
                  id="direccion"
                  v-model="studentData.address"
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
              v-for="(classItem, index) in studentClasses"
              :key="index"
            >
              {{ classItem.name }} - Nota: {{ classItem.grade }}
            </li>
          </ul>
        </div>
      </div>

      <FooterStudents />
    </div>
  </template>

  <script>
  import axios from 'axios';
  import NavbarStudents from '@/components/navbars/NavbarStudents.vue'
  import FooterStudents from '@/components/footers/FooterStudents.vue'

  export default {
    name: 'StudentProfile',
    components: {
      NavbarStudents,
      FooterStudents
    },
    data() {
  return {
    profileImage: '/Assets/img/user.jpg',
    selectedImageFile: null,
    studentData: {
      name: '',
      email: '',
      phone: '',
      address: ''
    },
    studentClasses: [] // 👈 importante: sin datos por defecto
  }
},
    methods: {
      handleImageUpload(event) {
  const file = event.target.files[0];
  if (file) {
    this.selectedImageFile = file; // 🔴 Necesario para enviar al backend
    const reader = new FileReader();
    reader.onload = (e) => {
      this.profileImage = e.target.result;
    };
    reader.readAsDataURL(file);
  }
},
      saveChanges() {
  const [nombre, apellido] = this.studentData.name.split(' ');
  const formData = new FormData();
  formData.append('id_usuario', localStorage.getItem('id_usuario'));
  formData.append('nombre', nombre);
  formData.append('apellido', apellido);
  formData.append('telefono', this.studentData.phone);
  formData.append('direccion', this.studentData.address);
  formData.append('correo', this.studentData.email);

  if (this.selectedImageFile) {
    formData.append('imagen', this.selectedImageFile);
  }

  axios.post('http://localhost/backend/update_user_full.php', formData)
    .then(res => {
      if (res.data.success) {
        alert('Cambios guardados con imagen');
      }
    })
    .catch(err => {
      console.error('Error al guardar:', err);
    });
},

  async uploadImage() {
    const formData = new FormData();
    formData.append('id_usuario', localStorage.getItem('id_usuario'));
    formData.append('imagen', this.selectedImageFile);

    const res = await axios.post('http://localhost/backend/update_user_image.php', formData);
    if (res.data.success) {
      alert('Imagen actualizada');
    } else {
      alert('Error al actualizar imagen');
    }
  }
    },
   mounted() {
  const correo = localStorage.getItem("correo");
  const id_usuario = localStorage.getItem("id_usuario");

  // Cargar datos básicos
 axios.get(`http://localhost/backend/get_user_by_email.php?correo=${correo}`)
  .then(res => {
    const data = res.data;

    if (data.error) {
      console.error("⚠️ Error desde backend:", data.error);
      return;
    }

    this.studentData = {
      name: [data.nombre, data.apellido].filter(Boolean).join(' '),
      email: data.correo,
      phone: data.telefono,
      address: data.direccion
    };

    this.profileImage = `http://localhost/backend/get_user_image.php?correo=${data.correo}`;
  })
  .catch(err => {
    console.error("❌ Error al obtener datos:", err);
  });


  // Cargar notas reales
axios.get(`http://localhost/backend/get_notas_usuario.php?id_usuario=${id_usuario}`)
  .then(res => {
    if (Array.isArray(res.data)) {
      this.studentClasses = res.data.filter(n => n.name && n.grade !== null);
    } else {
      this.studentClasses = []; // evita errores si viene mal
    }
  })
  .catch(err => {
    console.error("Error al cargar notas:", err);
    this.studentClasses = [];
  });
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
