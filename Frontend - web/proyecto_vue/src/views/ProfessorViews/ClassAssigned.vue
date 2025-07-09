<template>
  <div>
    <NavbarTeachers />
    <br>
    <!-- Contenedor principal -->
    <div class="container mt-5 pt-5">
      <div class="row">
        <!-- Calificación de Estudiantes -->
        <div class="col-md-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Calificación de Estudiantes</h5>
              <p>Estudiante: <span id="student-name">{{ currentStudent.name }}</span></p>
              <p>Clase a Calificar: <span id="class-name">{{ currentStudent.class }}</span></p>
              <p>Calificación: <span id="student-grade">{{ currentStudent.grade }}</span></p>
              <p>Observación a Mejorar: <span id="student-obs">{{ currentStudent.observation }}</span></p>
              <button
                class="btn btn-secondary"
                data-bs-toggle="modal"
                data-bs-target="#editModal"
              >
                Editar
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Tabla de Asistencia -->
      <div class="row mt-4">
        <div class="col-md-12">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Años</th>
                <th>Clase</th>
                <th>Asistencia</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(student, index) in students"
                :key="index"
                @click="selectStudent(student)"
              >
                <td>{{ student.name }}</td>
                <td>{{ student.age }}</td>
                <td>{{ student.class }}</td>
                <td>
                  <input
                    type="checkbox"
                    class="attendance-toggle"
                    v-model="student.attendance"
                    @change="updateAttendance(student)"
                  >
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <button type="button" class="btn btn-primary" @click="registrarAsistencias">Registrar Cambios</button>

      </div>
    </div>

    <!-- Modal para Editar -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editModalLabel">Editar Calificación</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="saveChanges">
              <div class="mb-3">
                <label for="editStudentName" class="form-label">Nombre del Estudiante</label>
                <input type="text" class="form-control" id="editStudentName" v-model="editForm.name">
              </div>
              <div class="mb-3">
                <label for="editClassName" class="form-label">Clase</label>
                <input
                type="text"
                class="form-control"
                id="editClassName"
                v-model="editForm.class"
                readonly
              >
              </div>
              <div class="mb-3">
                <label for="editGrade" class="form-label">Calificación</label>
                <input
                  type="number"
                  class="form-control"
                  id="editGrade"
                  v-model="editForm.grade"
                  step="0.1"
                  min="0"
                  max="5"
                >
              </div>
              <div class="mb-3">
                <label for="editObs" class="form-label">Observación</label>
                <textarea class="form-control" id="editObs" v-model="editForm.observation"></textarea>
              </div>
              <button type="submit" class="btn btn-primary">Registrar Cambios</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <br><br>

    <FooterTeachers />
  </div>
</template>

<script>
import NavbarTeachers from '@/components/navbars/NavbarTeachers.vue'
import FooterTeachers from '@/components/footers/FooterTeachers.vue'
import { Modal } from 'bootstrap'
import axios from 'axios'

export default {
  name: 'ClassAssigned',
  components: {
    NavbarTeachers,
    FooterTeachers
  },
  data() {
    return {
      claseSeleccionada: null,
      currentStudent: {
        name: '',
        class: '',
        grade: 4.0,
        observation: ''
      },
      editForm: {
        name: '',
        class: '',
        grade: 0,
        observation: ''
      },
      systemNews: [
        'Hay un nuevo Evento',
        'Se registró un alumno en tu clase de Salsa',
        'Tu clase tiene el mejor promedio de la academia'
      ],
      students: [
      ]
    }
  },
  mounted() {
  this.editModal = new Modal(document.getElementById('editModal'));
  this.editForm = { ...this.currentStudent };

  const claseGuardada = localStorage.getItem('claseSeleccionada');
  if (claseGuardada) {
    this.claseSeleccionada = JSON.parse(claseGuardada);
    this.fetchUsuariosPorClase(this.claseSeleccionada.id_clase);
  } else {
    console.warn("⚠️ No se encontró clase en localStorage");
  }
},
  methods: {
    selectStudent(student) {
    this.currentStudent = {
      ...student,
      grade: 3.5,
      observation: 'Observación para ' + student.name
    };
    this.editForm = { ...this.currentStudent };
  },
  updateAttendance(student) {
    console.log(`🟡 Asistencia para ${student.name}: ${student.attendance}`);
  },
  async fetchUsuarios() {
    try {
      const res = await axios.get('https://hqt8rl0q-80.use2.devtunnels.ms/backend/usuarios.php');
      this.students = res.data.filter(user => user.id_clase !== null).map(user => ({
        id_usuario: user.id_usuario,
        id_clase: user.id_clase,
        name: `${user.nombre} ${user.apellido}`,
        class: user.nombre_clase || 'Sin clase',
        age: 0, // si tienes edad, ponla desde la BD
        attendance: false
      }));
    } catch (err) {
      console.error('Error al cargar usuarios:', err);
    }
  },
  async registrarAsistencias() {
  for (const student of this.students) {
    try {
      const payload = {
        id_usuario: this.currentStudent.id_usuario,
        id_clase: this.currentStudent.id_clase,
        calificacion: this.currentStudent.grade,
        promedio: this.currentStudent.grade,
        observacion: this.currentStudent.observation
      };

      console.log('📤 Enviando:', payload);

      const response = await axios.post('https://hqt8rl0q-80.use2.devtunnels.ms/backend/guardar_asistencia.php', payload);
      console.log('📥 Respuesta:', response.data);
    } catch (err) {
      console.error(`❌ Error al guardar asistencia de ${student.name}`, err);
    }
  }
  },
  async saveChanges() {
  const payload = {
      id_usuario: this.currentStudent.id_usuario,
      id_clase: this.currentStudent.id_clase,
      calificacion: this.editForm.grade,
      promedio: this.editForm.grade,
      observacion: this.editForm.observation
  };

  console.log('📤 Enviando calificación:', payload);

  try {
    const response = await axios.post('https://hqt8rl0q-80.use2.devtunnels.ms/backend/guardar_calificacion.php', payload);
    console.log('📥 Respuesta:', response.data);
    alert('✅ Calificación guardada');
  } catch (err) {
    console.error('❌ Error al guardar calificación:', err);
  }

  this.editModal.hide();
},
async fetchUsuariosPorClase(id_clase) {
  try {
    const res = await axios.get(`https://hqt8rl0q-80.use2.devtunnels.ms/backend/get_estudiantes_por_clase.php?id_clase=${id_clase}`);
    this.students = res.data.map(user => ({
      id_usuario: user.id_usuario,
      id_clase: id_clase,
      name: `${user.nombre} ${user.apellido}`,
       class: user.nombre_clase || 'Clase',
      age: 0,
      attendance: false
    }));

    if (res.data.length > 0) {
      this.claseSeleccionada.nombre = res.data[0].nombre_clase;
    }

  } catch (err) {
    console.error('❌ Error al cargar estudiantes:', err);
  }
}
  }
}

</script>

<style scoped>
.container {
  padding-top: 80px;
}

.card {
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  height: 100%;
}

thead th {
  background-color: #b007ff;
  color: #000;
}

.table tbody tr {
  cursor: pointer;
}

.table tbody tr:hover {
  background-color: #f8f9fa;
}

.btn-primary {
  background-color: #7667f8;
  border-color: #8768f7;
  color: #000;
}

.btn-primary:hover {
  background-color: #b007ff;
  border-color: #b007ff;
}

.attendance-toggle {
  cursor: pointer;
  transform: scale(1.5);
}
</style>
