<template>
    <NavbarAdmin />
    <br><br>
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
              <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#registerModal">Registrar</button>
              <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">Editar</button>
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
              <tr v-for="(student, index) in students" :key="index" @click="selectStudent(student)">
                <td>{{ student.name }}</td>
                <td>{{ student.age }}</td>
                <td>{{ student.class }}</td>
                <td>
                  <input type="checkbox" v-model="student.attendance" @change="updateAttendance(student)" class="attendance-toggle">
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <button type="button" class="btn btn-primary" @click="registrarAsistencias">Registrar Cambios</button>
      </div>
    </div>

    <!-- Modal para Registrar -->
    <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="registerModalLabel">Registrar Calificación</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <form id="registerForm">
              <div class="mb-3">
                <label for="studentNameInput" class="form-label">Nombre del Estudiante</label>
                <input type="text" class="form-control" id="studentNameInput">
              </div>
              <div class="mb-3">
                <label for="classNameInput" class="form-label">Clase</label>
                <input type="text" class="form-control" id="classNameInput">
              </div>
              <div class="mb-3">
                <label for="gradeInput" class="form-label">Calificación</label>
                <input type="number" class="form-control" id="gradeInput" step="0.1">
              </div>
              <div class="mb-3">
                <label for="obsInput" class="form-label">Observación</label>
                <textarea class="form-control" id="obsInput"></textarea>
              </div>
              <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
          </div>
        </div>
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
            <form id="editForm" @submit.prevent="saveChanges">
              <div class="mb-3">
                <label for="editStudentName" class="form-label">Nombre del Estudiante</label>
                <input type="text" class="form-control" v-model="editForm.name" readonly>
              </div>
              <div class="mb-3">
                <label for="editClassName" class="form-label">Clase</label>
                <input type="text" class="form-control" v-model="editForm.class" readonly>
              </div>
              <div class="mb-3">
                <label for="editGrade" class="form-label">Calificación</label>
                <input type="number" class="form-control" v-model="editForm.grade" step="0.1" min="0" max="5">
              </div>
              <div class="mb-3">
                <label for="editObs" class="form-label">Observación</label>
                <textarea class="form-control" v-model="editForm.observation"></textarea>
              </div>
              <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <br><br>
    <FooterAdmin />
  </template>
  <script>
import FooterAdmin from '@/components/footers/FooterAdmin.vue';
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';
import axios from 'axios';

export default {
  name: 'ClassView',
  components: {
    NavbarAdmin,
    FooterAdmin
  },
  data() {
    return {
      currentStudent: {
        id_usuario: null,
        id_clase: null,
        name: '',
        class: '',
        grade: 0,
        observation: ''
      },
      editForm: {
        name: '',
        class: '',
        grade: 0,
        observation: ''
      },
      students: []
    };
  },
  mounted() {
    // Cargar clase seleccionada desde localStorage
    const claseGuardada = localStorage.getItem('claseSeleccionada');
    if (claseGuardada) {
      const clase = JSON.parse(claseGuardada);
      this.currentStudent.class = clase.nombre;
      this.fetchUsuariosPorClase(clase.id_clase);
    } else {
      console.warn('⚠️ No hay clase seleccionada en localStorage');
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

    async fetchUsuariosPorClase(id_clase) {
      try {
        const res = await axios.get(`http://localhost/backend/get_estudiantes_por_clase.php?id_clase=${id_clase}`);
        this.students = res.data.map(user => ({
          id_usuario: user.id_usuario,
          id_clase: id_clase,
          name: `${user.nombre} ${user.apellido}`,
          class: user.nombre_clase,
          age: 0, // si tienes edad en la BD, puedes incluirla
          attendance: false
        }));
      } catch (err) {
        console.error('❌ Error al cargar estudiantes:', err);
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
        const res = await axios.post('http://localhost/backend/guardar_calificacion.php', payload);
        alert('✅ Calificación guardada');
        console.log('📥 Respuesta:', res.data);
      } catch (err) {
        console.error('❌ Error al guardar calificación:', err);
      }

      const modal = bootstrap.Modal.getInstance(document.getElementById('editModal'));
      modal?.hide();
    },

    async registrarAsistencias() {
  for (const student of this.students) {
    try {
      const payload = {
        id_usuario: student.id_usuario,
        id_clase: student.id_clase,
        asistencia: student.attendance
      };

      console.log('📤 Enviando asistencia:', payload);

      const response = await axios.post('http://localhost/backend/guardar_asistencia.php', payload);
      console.log('📥 Respuesta:', response.data);
    } catch (err) {
      console.error(`❌ Error al guardar asistencia de ${student.name}`, err);
    }
  }

  alert('✅ Asistencias registradas correctamente');
},

    updateAttendance(student) {
      console.log(`🟡 Asistencia para ${student.name}: ${student.attendance}`);
    }
  },
  async selectStudent(student) {
    this.currentStudent = { ...student };
    this.editForm = {
      name: student.name,
      class: student.class,
      grade: 0,
      observation: ''
    };

    try {
      const res = await axios.get(`http://localhost/backend/get_calificacion_usuario.php?id_usuario=${student.id_usuario}&id_clase=${student.id_clase}`);
      if (res.data) {
        this.editForm.grade = parseFloat(res.data.calificacion);
        this.editForm.observation = res.data.observacion;
      }
    } catch (err) {
      console.error('❌ Error al obtener calificación del estudiante:', err);
    }
  }
};
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
