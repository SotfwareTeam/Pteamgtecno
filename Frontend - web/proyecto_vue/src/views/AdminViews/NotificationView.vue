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
import moment from 'moment'; // Importamos Moment.js para formatear la fecha
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
      customMessage: '',
      sendToTeacher: false,
      modalMessage: '',
      // Datos simulados de clases programadas (estos datos pueden venir de una base de datos)
      classData: {
        '28/04/2025': ['Clase de Salsa', 'Clase de Bachata'],
        '20/09/2024': ['Clase de Tango', 'Clase de Merengue'],
        '25/09/2024': ['Clase de Hip-Hop', 'Clase de Ballet'],
      },
    };
  },
  computed: {
    formattedSelectedDate() {
      return this.selectedDate ? moment(this.selectedDate).format('DD/MM/YYYY') : 'Ninguna fecha seleccionada';
    },
  },
  mounted() {
    const picker = new Pikaday({
      field: document.getElementById('datepicker'),
      format: 'DD/MM/YYYY',
      onSelect: (date) => {
        this.selectedDate = date;
        this.loadClasses(moment(date).format('DD/MM/YYYY'));
      },
    });
    console.log('window.bootstrap en mounted:', window.bootstrap);
  },
  methods: {
    loadClasses(date) {
      this.classes = this.classData[date] || [];
    },
    showModal(message) {
         this.modalMessage = message;
         const myModal = new Modal(document.getElementById('notificationModal'));
         myModal.show()
    },
    sendNotification(message) {
      if (!this.selectedDate) {
        alert('Por favor, seleccione una fecha antes de enviar.');
        return;
      }
      if (!this.selectedClass) {
        alert('Por favor, seleccione una clase para enviar la notificación.');
        return;
      }
      this.showModal(message);
    },
    sendCustomNotification() {
      if (!this.selectedDate) {
        alert('Por favor, seleccione una fecha antes de enviar.');
        return;
      }
      if (!this.selectedClass) {
        alert('Por favor, seleccione una clase para enviar la notificación.');
        return;
      }
      if (this.customMessage) {
        let fullMessage = `Notificación personalizada enviada: ${this.customMessage}.`;
        fullMessage += this.sendToTeacher ? ' También fue enviada al maestro.' : '';
        this.showModal(fullMessage);
        this.customMessage = ''; // Limpiar el mensaje después de enviar
        this.sendToTeacher = false; // Resetear el checkbox
      } else {
        alert('Por favor, escribe un mensaje antes de enviar.');
      }
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