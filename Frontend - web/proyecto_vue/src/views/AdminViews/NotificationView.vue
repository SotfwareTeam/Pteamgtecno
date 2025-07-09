<template>
  <div>
    <NavbarAdmin />
    <br><br><br><br>
    <div class="container mt-5">
      <div class="mb-4">
        <label for="datepicker" class="form-label">Seleccione una fecha:</label>
        <input type="text" id="datepicker" class="form-control" placeholder="Seleccione una fecha" v-model="selectedDateFormatted">
      </div>

      <div class="mb-4" id="class-section" v-if="classes.length > 0">
        <label for="classSelect" class="form-label">Clases programadas:</label>
        <select id="classSelect" class="form-select" v-model="selectedClass">
          <option v-for="clase in classes" :key="clase" :value="clase">{{ clase }}</option>
        </select>
      </div>
      <div class="mb-4" id="class-section" v-else-if="selectedDate">
        <p>No hay clases programadas para esta fecha.</p>
      </div>

      <div id="notification-section">
        <h5 class="mb-3">Enviar Notificación</h5>
        <p>Fecha seleccionada: <span id="selectedDate">{{ formattedSelectedDate }}</span></p>

        <button class="btn btn-p w-100 mb-2" @click="sendNotification('Recordatorio: Mañana es tu clase.')">Enviar Recordatorio</button>
        <button class="btn btn-p w-100 mb-2" @click="sendNotification('Tu clase ha sido cancelada.')">Enviar Cancelación</button>
        <button class="btn btn-p w-100 mb-2" @click="sendNotification('Puedes acercarte a la academia, necesitamos hablar contigo.')">Enviar Solicitud de Contacto</button>

        <textarea class="form-control mb-2" id="customMessage" placeholder="Escribe tu mensaje personalizado aquí" v-model="customMessage"></textarea>

        <div class="form-check mb-3">
          <input class="form-check-input" type="checkbox" id="sendToTeacher" v-model="sendToTeacher">
          <label class="form-check-label" for="sendToTeacher">Enviar también al maestro</label>
        </div>

        <button class="btn btn-e w-100" @click="sendCustomNotification">Enviar Mensaje Personalizado</button>
        <div><br></div>
      </div>
    </div>

    <div class="modal fade" id="notificationModal" tabindex="-1" aria-labelledby="notificationModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="notificationModalLabel">Notificación Enviada</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body" id="modalBodyContent">
            Mensaje enviado: {{ modalMessage }}<br>
            Clase: {{ selectedClass }}<br>
            Fecha: {{ formattedSelectedDate }}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
          </div>
        </div>
      </div>
    </div>
    <br><br><br>
    <FooterAdmin />
  </div>
</template>

<script>
import Pikaday from 'pikaday';
import 'pikaday/css/pikaday.css';
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';
import FooterAdmin from '@/components/footers/FooterAdmin.vue';
import moment from 'moment';
import { Modal } from 'bootstrap';

export default {
  components: {
    NavbarAdmin,
    FooterAdmin,
  },
  data() {
    return {
      selectedDate: null,
      selectedDateFormatted: '',
      selectedClass: null,
      classes: [],
      classMap: {}, // <- Mapea nombre de clase a id_clase
      customMessage: '',
      sendToTeacher: false,
      modalMessage: '',
      markedDates: [],
    };
  },
  computed: {
    formattedSelectedDate() {
      return this.selectedDate
        ? moment(this.selectedDate).format('DD/MM/YYYY')
        : 'Ninguna fecha seleccionada';
    },
  },
  mounted() {
    this.fetchMarkedDates().then(() => {
      new Pikaday({
        field: document.getElementById('datepicker'),
        format: 'DD/MM/YYYY',
        onSelect: (date) => {
          this.selectedDate = date;
          this.loadClasses(moment(date).format('YYYY-MM-DD'));
        },
        disableDayFn: (date) => {
          const formatted = moment(date).format('YYYY-MM-DD');
          return !this.markedDates.includes(formatted);
        },
      });
    });
  },
  methods: {
    async fetchMarkedDates() {
      try {
        const response = await fetch('https://hqt8rl0q-80.use2.devtunnels.ms/backend/get_class_dates.php');
        const fechas = await response.json();
        this.markedDates = Array.isArray(fechas) ? fechas : [];
      } catch (error) {
        console.error('Error al obtener fechas:', error);
      }
    },

    async loadClasses(dateFormatted) {
      try {
        const response = await fetch('https://hqt8rl0q-80.use2.devtunnels.ms/backend/get_classes_by_date.php', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ fecha: dateFormatted }),
        });

        const data = await response.json();

        // data debe ser un array de objetos: [{ id: 1, nombre: 'Salsa Básica' }]
        if (Array.isArray(data)) {
          this.classes = data.map(clase => clase.nombre);
          this.classMap = {};
          data.forEach(clase => {
            this.classMap[clase.nombre] = clase.id;
          });
        } else {
          this.classes = [];
        }
      } catch (error) {
        console.error('Error cargando clases:', error);
        this.classes = [];
      }
    },

    showModal(message) {
      this.modalMessage = message;
      const myModal = new Modal(document.getElementById('notificationModal'));
      myModal.show();
    },

    async sendNotification(message) {
      if (!this.selectedDate || !this.selectedClass) {
        alert('Seleccione una fecha y una clase antes de enviar.');
        return;
      }

      const idClase = this.classMap[this.selectedClass];
      if (!idClase) {
        alert('No se pudo identificar la clase.');
        return;
      }

      try {
        const response = await fetch('https://hqt8rl0q-80.use2.devtunnels.ms/backend/enviar_notificacion.php', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            id_clase: idClase,
            mensaje: message,
            enviar_al_maestro: this.sendToTeacher,
          }),
        });

        const result = await response.json();

        if (result.success) {
          this.showModal(message);
        } else {
          alert('Error al enviar notificación: ' + (result.error || 'Desconocido'));
        }
      } catch (error) {
        console.error('Error enviando notificación:', error);
        if (error instanceof Response) {
        const mensaje = await error.text();
        console.error('Respuesta del servidor:', mensaje);
      }
        alert('Fallo al conectar con el servidor o error interno.');
}
    },

    async sendCustomNotification() {
      if (!this.selectedDate || !this.selectedClass) {
        alert('Seleccione una fecha y una clase.');
        return;
      }

      if (!this.customMessage.trim()) {
        alert('Escriba un mensaje personalizado.');
        return;
      }

      await this.sendNotification(this.customMessage);

      this.customMessage = '';
      this.sendToTeacher = false;
    },
  },
};
</script>



<style scoped>
.container {
  max-width: 400px;
  margin: 20px auto;
  background-color: rgb(255, 255, 255);
  border-radius: 5%;
  padding: 20px; /* Añadí un poco de padding para que el contenido no esté pegado al borde */
}

.btn-p {
  background-color: #9854b1;
  color: white; /* Añadí color de texto para mejor contraste */
}

.btn-e {
  background-color: #b269cd;
  color: white; /* Añadí color de texto para mejor contraste */
}

/* Puedes agregar más estilos específicos si es necesario */
</style>
