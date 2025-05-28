<template>
    <div>
      <NavbarAdmin />

<div class="container1 mt-5">
        <div class="row text-center mt-5">
          <div class="col-md-8">
            <h3>Calendario</h3>
            <img src="/Assets/img/salones.jpg" alt="Calendario" class="img-thumbnail"
                 data-bs-toggle="modal" data-bs-target="#espaciosModal">
          </div>
          <div class="col-md-4">
            <h3>Profesores</h3>
            <img src="/Assets/img/profesor.jpg" alt="Profesores" class="img-thumbnail"
                 data-bs-toggle="modal" data-bs-target="#profesoresModal">
          </div>
        </div>
      </div>
<!-- Calendario -->
<div class="container mt-5">
        <div class="calendar-title text-center mb-4">
          <h2>Proximos Eventos</h2>
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
      <!-- Modal Información -->
      <div class="modal fade" id="infoModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content bg-dark text-white">
            <div class="modal-header">
              <h5 class="modal-title">Información del Día</h5>
              <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div v-if="selectedClass">
                <p><strong>Profesor:</strong> {{ selectedClass.profesor }}</p>
                <p><strong>Sede:</strong> {{ selectedClass.sede }}</p>
                <p><strong>Hora:</strong> {{ selectedClass.hora }}</p>
                <p><strong>Clase:</strong> {{ selectedClass.clase }}</p>
                <p><strong>Dificultad:</strong> {{ selectedClass.dificultad }}</p>
              </div>
              <div v-else>
                <p>No hay clase programada para este día.</p>
              </div>
            </div>
            <div class="modal-footer">
              <router-link 
                v-if="selectedClass" 
                to="/calificar" 
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

  <!-- Modales -->
      <div class="modal fade" id="espaciosModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Calendario</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
              <p>Aquí puedes ver los detalles de los espacios disponibles.</p>
              <router-link to="/Profesor/Calendario" class="btn btn-primary">Ir a Calendario</router-link>
            </div>
          </div>
        </div>
      </div>

      <div class="modal fade" id="profesoresModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Nuestros Profesores</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
              <div class="row">
                <div class="col-md-4" v-for="(profesor, index) in profesores" :key="index">
                  <div class="card">
                    <img :src="profesor.imagen" class="card-img-top" :alt="'Profesor ' + (index + 1)">
                    <div class="card-body">
                      <h5 class="card-title">{{ profesor.nombre }}</h5>
                      <p class="card-text">{{ profesor.descripcion }}</p>
                       <button class="btn btn-primary"> Editar profesor</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>

    <FooterAdmin />
  </template>

  <script>
  import axios from 'axios';
  import { Modal } from 'bootstrap'
  import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';
  import FooterAdmin from '@/components/footers/FooterAdmin.vue';

  export default {
      name: 'EventsAdmin',
    components: {
      NavbarAdmin,
      FooterAdmin,
    }, data() {
      return {
        daysOfWeek: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        currentDate: new Date(),
        selectedDate: null,
        scheduledClasses: {},
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
        },
       profesores: [
        {
          imagen: '/Assets/img/user.jpg',
          nombre: 'Profesor 1',
          descripcion: 'Experto en salsa y bachata.'
        },
        {
          imagen: '/Assets/img/user.jpg',
          nombre: 'Profesor 2',
          descripcion: 'Especialista en tango y danza contemporánea.'
        },
        {
          imagen: '/Assets/img/user.jpg',
          nombre: 'Profesor 3',
          descripcion: 'Instructor de hip-hop y breakdance.'
        }
      ]
    }
  },       
    computed: {
      currentYear() {
        return this.currentDate.getFullYear()
      },
      currentMonth() {
        return this.currentDate.getMonth()
      },
      currentMonthName() {
        return this.currentDate.toLocaleDateString('es-ES', { month: 'long' })
      },
      calendarWeeks() {
        const firstDay = new Date(this.currentYear, this.currentMonth, 1).getDay()
        const daysInMonth = new Date(this.currentYear, this.currentMonth + 1, 0).getDate()
        const daysInPrevMonth = new Date(this.currentYear, this.currentMonth, 0).getDate()

        let date = 1
        let prevMonthDate = daysInPrevMonth - firstDay + 1
        const weeks = []

        for (let i = 0; i < 6; i++) {
          const week = []

          for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
              week.push({
                day: prevMonthDate++,
                date: this.formatDate(this.currentYear, this.currentMonth - 1, prevMonthDate - 1),
                isCurrentMonth: false
              })
            } else if (date > daysInMonth) {
              week.push({
                day: date - daysInMonth,
                date: this.formatDate(this.currentYear, this.currentMonth + 1, date - daysInMonth),
                isCurrentMonth: false
              })
              date++
            } else {
              week.push({
                day: date,
                date: this.formatDate(this.currentYear, this.currentMonth, date),
                isCurrentMonth: true
              })
              date++
            }
          }

          weeks.push(week)
          if (date > daysInMonth) break
        }

        return weeks
      },
      selectedClass() {
        if (!this.selectedDate) return null
        return this.scheduledClasses[this.selectedDate.date] || null
      }
    },
    mounted() {
      // Inicializar los componentes de Bootstrap (los modals)
      this.modals.program = new Modal(document.getElementById('programModal'))
      this.modals.delete = new Modal(document.getElementById('deleteModal'))
      this.modals.info = new Modal(document.getElementById('infoModal'))

      // Cargar clases programadas desde localStorage (o API)
      const savedClasses = localStorage.getItem('scheduledClasses')
      if (savedClasses) {
        this.scheduledClasses = JSON.parse(savedClasses)
      }
    },
    methods: {
      enviarFecha() {
  if (!this.selectedDate) return;

  console.log("Simulando petición para fecha:", this.selectedDate.date)

  // Aquí puedes definir tu data simulada "hardcoded" desde consola o lógica interna
  const clasesSimuladas = {
    '2025-04-16': {
      nombre: 'Bachata',
      tipo_evento: 'Clase de Baile',
      fecha: '2025-04-29',
      hora: '18:30',
      profesor: 'Camilo R.'
    },
    '2025-04-17': {
      nombre: 'Salsa',
      tipo_evento: 'Clase de Baile',
      fecha: '2025-05-27',
      hora: '20:00',
      profesor: 'Andrea M.'
    }
  }

  // Busca la clase simulada según la fecha seleccionada
  const clase = clasesSimuladas[this.selectedDate.date]

  // Simula la respuesta del servidor
  if (clase) {
    console.log('🧪 Datos simulados:', clase)
    this.respuestaServidor = clase
  } else {
    this.respuestaServidor = null
  }

  // Muestra el modal después de actualizar los datos
  this.$nextTick(() => {
    const modal = new Modal(document.getElementById('infoModal'))
    modal.show()
  })
}

, formatDate(year, month, day) {
        const mm = String(month + 1).padStart(2, '0') // mes con 2 dígitos
        const dd = String(day).padStart(2, '0')       // día con 2 dígitos
        return `${year}-${mm}-${dd}`
      },
      prevMonth() {
        this.currentDate = new Date(this.currentYear, this.currentMonth - 1, 1)
      },
      nextMonth() {
        this.currentDate = new Date(this.currentYear, this.currentMonth + 1, 1)
      },
      selectDate(day) {
        console.log("Seleccionaste el día:", day);
        this.selectedDate = day
      },
      isScheduled(date) {
        return !!this.scheduledClasses[date]
      },
      saveClass() {
        if (this.selectedDate) {
          this.scheduledClasses[this.selectedDate.date] = { ...this.classData }
          localStorage.setItem('scheduledClasses', JSON.stringify(this.scheduledClasses))
          this.resetClassData()
          this.modals.program.hide()
        }
      },
            deleteClass() {
        if (this.selectedDate && this.scheduledClasses[this.selectedDate.date]) {
          delete this.scheduledClasses[this.selectedDate.date]
          localStorage.setItem('scheduledClasses', JSON.stringify(this.scheduledClasses))
        }
      },
      resetClassData() {
        this.classData = {
          profesor: '',
          sede: '',
          hora: '',
          clase: '',
          dificultad: 'Principiante'
        }
      }
    }
}
  </script>

  <style scoped>
  
.custom-carousel .carousel-inner img {
  height: 350px;
  object-fit: cover;
}

/* Estilos para las imágenes */
.col-md-4 img {
  max-height: 200px;
  object-fit: cover;
  cursor: pointer;
  transition: transform 0.3s;
}

.col-md-4 img:hover {
  transform: scale(1.05);
}

/* Espaciado */
.container1 {
  padding-top: 20px;
  padding-bottom: 20px;
}
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
  }  </style>
