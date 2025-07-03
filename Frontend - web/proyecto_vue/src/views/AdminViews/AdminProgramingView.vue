<template>
    <div>
      <NavbarAdmin />

      <br><br><br>

      <!-- Programing Calendar -->
      <div class="container mt-5">
        <div class="calendar-title text-center mb-4">
          <h2>Programar Clase</h2>
        </div>
        <div class="calendar calendar-container p-3 rounded border-secondary">
          <div class="row calendar-header d-flex justify-content-between align-items-center">
            <div class="col-2 text-start">
              <button @click="prevMonth" class="btn btn-dark">&lt;</button>
            </div>
            <div class="col-8 text-center text-dark">
              <h3 class="m-0">{{ currentMonthName }} {{ currentYear }}</h3>
            </div>
            <div class="col-2 text-end">
              <button @click="nextMonth" class="btn btn-dark">&gt;</button>
            </div>
          </div>
          <div class="table-responsive mt-3">
            <table class="table table-bordered text-center table-secondary">
              <thead>
                <tr>
                  <th v-for="day in daysOfWeek" :key="day">{{ day }}</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(week, weekIndex) in calendarWeeks" :key="weekIndex">
                  <td
                    v-for="(day, dayIndex) in week"
                    :key="dayIndex"
                    :class="{
                      'selected': selectedDate && day.date === selectedDate.date,
                      'scheduled': isScheduled(day.date),
                      'text-muted': !day.isCurrentMonth
                    }"
                    @click="selectDate(day)"
                  >
                    {{ day.day }}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="calendar-footer d-flex flex-wrap justify-content-center mt-3">
            <button
              class="btn btn-info mx-2 mb-2 text-white"
              data-bs-toggle="modal"
              data-bs-target="#infoModal"
              :disabled="!selectedDate"
            >
              Información
            </button>
            <button
              class="btn btn-success mx-2 mb-2 text-white"
              data-bs-toggle="modal"
              data-bs-target="#programModal"
              :disabled="!selectedDate"
            >
              Programar
            </button>
            <button
              class="btn btn-danger mx-2 mb-2 text-white"
              data-bs-toggle="modal"
              data-bs-target="#deleteModal"
              :disabled="!selectedDate || !isScheduled(selectedDate.date)"
            >
              Eliminar
            </button>
          </div>
        </div>
      </div>

      <!-- Modal Programar -->
      <div class="modal fade" id="programModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content bg-dark text-white">
            <div class="modal-header">
              <h5 class="modal-title">Programar Clase</h5>
              <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <form @submit.prevent="saveClass">
                <div class="mb-3">
                  <label for="profesor" class="form-label">Profesor</label>
                  <select class="form-select" id="profesor" v-model="classData.profesor" required>
                    <option v-for="profesor in profesores" :key="profesor.id_usuario" :value="profesor.id_usuario">
                      {{ profesor.nombre }}
                    </option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="hora" class="form-label">Hora</label>
                  <input type="time" class="form-control" id="hora" v-model="classData.hora" required>
                </div>
                <div class="mb-3">
                  <label for="clase" class="form-label">Clase</label>
                  <input type="text" class="form-control" id="clase" v-model="classData.clase" required>
                </div>
                <div class="mb-3">
                  <label for="dificultad" class="form-label">Dificultad</label>
                  <select class="form-select" id="dificultad" v-model="classData.dificultad" required>
                    <option value="Principiante">Principiante</option>
                    <option value="Medio">Medio</option>
                    <option value="Difícil">Difícil</option>
                  </select>
                </div>
                <div class="modal-footer">
                  <button type="submit" class="btn btn-success">Guardar</button>
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>

      <!-- Modal Eliminar -->
      <div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content bg-dark text-white">
            <div class="modal-header">
              <h5 class="modal-title">Eliminar Programación</h5>
              <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p>¿Estás seguro de que deseas eliminar la programación de este día?</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-danger" @click="deleteClass" data-bs-dismiss="modal">Eliminar</button>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            </div>
          </div>
        </div>
      </div>

      <!-- Modal Información -->
      <div class="modal fade" id="infoModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content bg-dark text-white">
            <div class="modal-header">
              <h5 class="modal-title">Información del Día</h5>
              <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div v-if="selectedClass.length > 0">
                <div v-for="(clase, index) in selectedClass" :key="index" class="mb-3 border-bottom pb-2">
                <p><strong>Profesor:</strong> {{ clase.profesor }}</p>
                <p><strong>Hora:</strong> {{ clase.hora }}</p>
                <p><strong>Clase:</strong> {{ clase.clase }}</p>
                <p><strong>Dificultad:</strong> {{ clase.dificultad }}</p>
                <button
                @click="goToClassView"
                class="btn btn-primary"
                data-bs-dismiss="modal"
              >
                Calificar
              </button>
              </div>
              </div>
              <div v-else>
                <p>No hay clase programada para este día.</p>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
          </div>
        </div>
      </div>

      <FooterAdmin />
    </div>
  </template>
<script>
import axios from 'axios';
import { Modal } from 'bootstrap';
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';
import FooterAdmin from '@/components/footers/FooterAdmin.vue';

export default {
  name: 'AdminProgrammingClass',
  components: {
    NavbarAdmin,
    FooterAdmin
  },
  data() {
    return {
      daysOfWeek: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
      currentDate: new Date(),
      selectedDate: null,
      scheduledClasses: {}, // aquí se guardan las clases cargadas de la base de datos
      profesores: [],
      classData: {
        hora: '',
        clase: '',
        dificultad: 'Principiante'
      },
      modals: {
        program: null,
        delete: null,
        info: null
      }
    }
  },
  computed: {
    currentYear() {
      return this.currentDate.getFullYear();
    },
    currentMonth() {
      return this.currentDate.getMonth();
    },
    currentMonthName() {
      return this.currentDate.toLocaleDateString('es-ES', { month: 'long' });
    },
    calendarWeeks() {
      const firstDay = new Date(this.currentYear, this.currentMonth, 1).getDay();
      const daysInMonth = new Date(this.currentYear, this.currentMonth + 1, 0).getDate();
      const daysInPrevMonth = new Date(this.currentYear, this.currentMonth, 0).getDate();

      let date = 1;
      let prevMonthDate = daysInPrevMonth - firstDay + 1;
      const weeks = [];

      for (let i = 0; i < 6; i++) {
        const week = [];
        for (let j = 0; j < 7; j++) {
          if (i === 0 && j < firstDay) {
            week.push({
              day: prevMonthDate++,
              date: this.formatDate(this.currentYear, this.currentMonth - 1, prevMonthDate - 1),
              isCurrentMonth: false
            });
          } else if (date > daysInMonth) {
            week.push({
              day: date - daysInMonth,
              date: this.formatDate(this.currentYear, this.currentMonth + 1, date - daysInMonth),
              isCurrentMonth: false
            });
            date++;
          } else {
            week.push({
              day: date,
              date: this.formatDate(this.currentYear, this.currentMonth, date),
              isCurrentMonth: true
            });
            date++;
          }
        }
        weeks.push(week);
        if (date > daysInMonth) break;
      }
      return weeks;
    },
    selectedClass() {
      if (!this.selectedDate) return [];
      return this.scheduledClasses[this.selectedDate.date] || [];
    }
  },
  mounted() {
     // Referencias a modales
    this.modals.program = new Modal(document.getElementById('programModal'));
    this.modals.delete = new Modal(document.getElementById('deleteModal'));
    this.modals.info = new Modal(document.getElementById('infoModal'));
   // Cargar datos
    this.cargarProfesores();
    this.cargarClasesProgramadas(); // Cargar clases apenas entra la vista
const infoModalEl = document.getElementById('infoModal');
  infoModalEl.addEventListener('hidden.bs.modal', () => {
    document.activeElement?.blur(); // quita foco del botón activo
  });

  const programModalEl = document.getElementById('programModal');
  programModalEl.addEventListener('hidden.bs.modal', () => {
    document.activeElement?.blur();
  });

  const deleteModalEl = document.getElementById('deleteModal');
  deleteModalEl.addEventListener('hidden.bs.modal', () => {
    document.activeElement?.blur();
  });
},
  methods: {
    formatDate(year, month, day) {
      const mm = String(month + 1).padStart(2, '0');
      const dd = String(day).padStart(2, '0');
      return `${year}-${mm}-${dd}`;
    },
    cargarClasesProgramadas() {
      const mes = String(this.currentMonth + 1).padStart(2, '0');
      const anio = this.currentYear;

      axios.get(`http://localhost/backend/clases_mes.php?mes=${mes}&anio=${anio}`)
        .then(response => {
          if (response.data && Array.isArray(response.data)) {
            this.scheduledClasses = {};
            response.data.forEach(clase => {
              if (!this.scheduledClasses[clase.fecha]) {
                this.scheduledClasses[clase.fecha] = [];
              }
              this.scheduledClasses[clase.fecha].push(clase);
            });
            console.log('✅ Clases cargadas:', this.scheduledClasses);
          }
        })
        .catch(error => {
          console.error('❌ Error cargando clases:', error);
        });
    },
    saveClass() {
      if (!this.selectedDate) return;

      const datos = {
        fecha: this.selectedDate.date,
        profesor: this.classData.profesor,
        hora: this.classData.hora,
        clase: this.classData.clase,
        dificultad: this.classData.dificultad
      };

      axios.post('http://localhost/backend/insertar_clase.php', datos)
        .then(response => {
          console.log('✅ Clase guardada:', response.data);
          this.cargarClasesProgramadas(); // Cargar otra vez después de guardar
          this.resetClassData();
          this.modals.program.hide();
        })
        .catch(error => {
          console.error('❌ Error guardando clase:', error);
        });
    },
    cargarProfesores() {
  axios.get('http://localhost/backend/get_profesores.php')
    .then(response => {
      this.profesores = response.data;
    })
    .catch(error => {
      console.error('❌ Error cargando profesores:', error);
    });
},

goToClassView() {
  this.$router.push('/Admin/Calificaciones');
},


    deleteClass() {
      if (!this.selectedDate) return;

      axios.post('http://localhost/backend/eliminar_clase.php', { fecha: this.selectedDate.date })
        .then(response => {
          console.log('✅ Clase eliminada:', response.data);
          this.cargarClasesProgramadas(); // Cargar otra vez después de eliminar
          this.modals.delete.hide();
        })
        .catch(error => {
          console.error('❌ Error eliminando clase:', error);
        });
    },
    prevMonth() {
      this.currentDate = new Date(this.currentYear, this.currentMonth - 1, 1);
      this.cargarClasesProgramadas();
    },
    nextMonth() {
      this.currentDate = new Date(this.currentYear, this.currentMonth + 1, 1);
      this.cargarClasesProgramadas();
    },
    selectDate(day) {
      this.selectedDate = day;
    },
    isScheduled(date) {
      return !!this.scheduledClasses[date];
    },
    resetClassData() {
      this.classData = {
        profesor: '',
        hora: '',
        clase: '',
        dificultad: 'Principiante'
      };
    }
  }
}
</script>




  <style scoped>
  .container {
    max-width: 700px;
    margin: 50px auto;
    padding: 20px;
    background-color: #fcfafa6b;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }

  .calendar-title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
    text-align: center;
    color: white;
  }

  .calendar-header {
    padding: 20px;
    margin-bottom: 20px;
    background-color: #ffffff;
    color: white;
  }

  .table thead th {
    background-color: #495057;
    color: white;
  }

  .table tbody td {
    cursor: pointer;
    background-color: #fff;
    color: #212529;
  }

  .table tbody td:hover {
    background-color: #e9ecef;
  }

  .table tbody td.selected {
    background-color: #ffc107;
    color: #212529;
  }

  .table tbody td.scheduled {
    background-color: #28a745;
    color: white;
  }

  .table tbody td.text-muted {
    color: #6c757d !important;
  }

  .btn-success, .btn-danger, .btn-warning, .btn-info {
    background-color: #495057;
    border-color: #495057;
  }

  .btn-success:hover, .btn-danger:hover, .btn-warning:hover, .btn-info:hover {
    opacity: 0.8;
  }

  .btn:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }

  .modal-content {
    background-color: #343a40;
    color: white;
  }

  .btn-close-white {
    filter: invert(1);
  }
  </style>
