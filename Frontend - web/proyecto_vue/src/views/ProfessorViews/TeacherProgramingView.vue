<template>
    <div>
      <NavbarTeachers />

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
              @click = "enviarFecha"
            >
              Información
            </button>
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
              <div v-if="respuestaServidor">
                <p><strong>Nombre:</strong> {{ respuestaServidor.nombre }}</p>
                <p><strong>Tipo Evento:</strong> {{ respuestaServidor.tipo_evento }}</p>
                <p><strong>Fecha:</strong> {{ respuestaServidor.fecha }}</p>
                <p><strong>Hora:</strong> {{ respuestaServidor.hora }}</p>
                <p><strong>Profesor:</strong> {{ respuestaServidor.profesor }}</p>
              </div>
              <div v-else>
                <p>No hay clase programada para este día.</p>
              </div>
            </div>
            <div class="modal-footer">
              <router-link
                v-if="respuestaServidor"
                to="/Profesor/Inicio"
                class="btn btn-primary"
                data-bs-dismiss="modal"
              >
                Calificar
              </router-link>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
          </div>
        </div>
      </div>

      <FooterTeachers />
    </div>
  </template>

  <script>
import axios from 'axios';
import NavbarTeachers from '@/components/navbars/NavbarTeachers.vue'
import FooterTeachers from '@/components/footers/FooterTeachers.vue'
import { Modal } from 'bootstrap'

export default {
  name: 'StudentProgrammingClass',
  components: {
    NavbarTeachers,
    FooterTeachers
  },
  data() {
    return {
      daysOfWeek: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
      currentDate: new Date(),
      selectedDate: null,
      scheduledClasses: {},
      respuestaServidor: null,
      classData: {
        profesor: '',
        sede: '',
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
      if (!this.selectedDate) return null;
      return this.scheduledClasses[this.selectedDate.date] || null;
    }
  },
  mounted() {
    this.modals.info = new Modal(document.getElementById('infoModal'));
    this.cargarClasesProgramadas();
  },
  methods: {
    enviarFecha() {
      if (!this.selectedDate) return;

      axios.get(`http://localhost/backend/clases.php?fecha=${this.selectedDate.date}`)
        .then(response => {
          if (response.data) {
            console.log('✅ Datos recibidos:', response.data);
            this.respuestaServidor = response.data;
          } else {
            this.respuestaServidor = null;
          }

          this.$nextTick(() => {
            const modal = new Modal(document.getElementById('infoModal'));
            modal.show();
          });
        })
        .catch(error => {
          console.error('❌ Error al cargar datos:', error);
          this.respuestaServidor = null;
        });
    },

    cargarClasesProgramadas() {
      const mes = String(this.currentMonth + 1).padStart(2, '0');
      const año = this.currentYear;

      axios.get(`http://localhost/backend/clases_mes.php?mes=${mes}&anio=${año}`)
        .then(response => {
          if (response.data && Array.isArray(response.data)) {
            this.scheduledClasses = {};
            response.data.forEach(clase => {
              this.scheduledClasses[clase.fecha] = clase;
            });
            console.log('✅ Días con clases:', this.scheduledClasses);
          }
        })
        .catch(error => {
          console.error('❌ Error al cargar las clases del mes:', error);
        });
    },

    formatDate(year, month, day) {
      const mm = String(month + 1).padStart(2, '0');
      const dd = String(day).padStart(2, '0');
      return `${year}-${mm}-${dd}`;
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
      console.log("Seleccionaste el día:", day);
      this.selectedDate = day;
    },

    isScheduled(date) {
      return !!this.scheduledClasses[date];
    },

    saveClass() {
      if (this.selectedDate) {
        this.scheduledClasses[this.selectedDate.date] = { ...this.classData };
        localStorage.setItem('scheduledClasses', JSON.stringify(this.scheduledClasses));
        this.resetClassData();
        this.modals.program.hide();
      }
    },

    resetClassData() {
      this.classData = {
        profesor: '',
        sede: '',
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
