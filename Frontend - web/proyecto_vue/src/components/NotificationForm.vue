<template>
  <br><br><br><br>
    <div class="container">
      <!-- Calendario -->
      <div>
        <br>
      </div>
      <div class="mb-4">
        <label for="datepicker" class="form-label">Seleccione una fecha:</label>
        <input type="text" id="datepicker" class="form-control" placeholder="Seleccione una fecha" ref="datepicker">
      </div>
  
      <!-- Clases Programadas -->
      <div class="mb-4" v-show="selectedDate" id="class-section">
        <label for="classSelect" class="form-label">Clases programadas:</label>
        <select id="classSelect" class="form-select" v-model="selectedClass">
          <option v-for="clase in classesForSelectedDate" :key="clase" :value="clase">{{ clase }}</option>
        </select>
      </div>
  
      <!-- Sección de Notificaciones -->
      <div id="notification-section">
        <h5 class="mb-3">Enviar Notificación</h5>
        <p>Fecha seleccionada: <span>{{ selectedDate || "Ninguna fecha seleccionada" }}</span></p>
  
        <!-- Botones de Envío Rápido -->
        <button class="btn btn-c w-100 mb-2" @click="sendNotification('Recordatorio: Mañana es tu clase.')">Enviar Recordatorio</button>
        <button class="btn btn-c w-100 mb-2" @click="sendNotification('Tu clase ha sido cancelada.')">Enviar Cancelación</button>
        <button class="btn btn-c w-100 mb-2" @click="sendNotification('Puedes acercarte a la academia, necesitamos hablar contigo.')">Enviar Solicitud de Contacto</button>
  
        <!-- Mensaje Personalizado -->
        <textarea class="form-control mb-2" v-model="customMessage" placeholder="Escribe tu mensaje personalizado aquí"></textarea>
  
        <!-- Checkbox Enviar al Maestro -->
        <div class="form-check mb-3">
          <input class="form-check-input" type="checkbox" id="sendToTeacher" v-model="sendToTeacher">
          <label class="form-check-label" for="sendToTeacher">Enviar también al maestro</label>
        </div>
  
        <!-- Botón de Envío Personalizado -->
        <button class="btn btn-f w-100" @click="sendCustomNotification()">Enviar Mensaje Personalizado</button>
        <div>
          <br>
        </div>
    </div>
    </div>
  
    <!-- Modal de Notificación -->
    <div v-if="showModal" class="modal fade show" id="notificationModal" tabindex="-1" aria-labelledby="notificationModalLabel" aria-hidden="true" style="display: block;">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="notificationModalLabel">Notificación Enviada</h5>
            <button type="button" class="btn-close" @click="closeModal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body" id="modalBodyContent">
            <!-- Contenido de la notificación -->
            {{ modalMessage }}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="closeModal">Cerrar</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed, onMounted } from 'vue';
  import Pikaday from 'pikaday';
  
  // Datos reactivos
  const selectedDate = ref(null);
  const selectedClass = ref(null);
  const customMessage = ref('');
  const sendToTeacher = ref(false);
  const showModal = ref(false);
  const modalMessage = ref('');
  
  // Datos de clases
  const classData = {
    '15/09/2024': ['Clase de Salsa', 'Clase de Bachata'],
    '20/09/2024': ['Clase de Tango', 'Clase de Merengue'],
    '25/09/2024': ['Clase de Hip-Hop', 'Clase de Ballet'],
  };
  
  
  
  // Computed para obtener las clases de la fecha seleccionada
  const classesForSelectedDate = computed(() => classData[selectedDate.value] || []);
  
  // Inicializar el picker de fecha
  onMounted(() => {
    const datePicker = document.getElementById('datepicker');
    new Pikaday({
      field: datePicker,
      format: 'DD/MM/YYYY',
      toString: (date) => {
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
      },
      // onSelect: (date) => {
      //   selectedDate.value = datePicker.value;
      // },
    });
  });
  
  // Función para enviar la notificación
  const sendNotification = () => {
    if (!selectedDate.value) {
      alert('Por favor, seleccione una fecha antes de enviar.');
      return;
    }
    if (!selectedClass.value) {
      alert('Por favor, seleccione una clase para enviar la notificación.');
      return;
    }
  
    modalMessage.value = `Notificación enviada: Clase: ${selectedClass.value} Fecha: ${selectedDate.value}`;
    showModal.value = true;
  };
  
  // Función para enviar una notificación personalizada
  const sendCustomNotification = () => {
    if (!selectedDate.value) {
      alert('Por favor, seleccione una fecha antes de enviar.');
      return;
    }
    if (!selectedClass.value) {
      alert('Por favor, seleccione una clase para enviar la notificación.');
      return;
    }
    if (!customMessage.value) {
      alert('Por favor, escribe un mensaje antes de enviar.');
      return;
    }
  
    let fullMessage = `Notificación personalizada enviada: ${customMessage.value}.`;
    fullMessage += sendToTeacher.value ? " También fue enviada al maestro." : "";
    modalMessage.value = fullMessage;
    showModal.value = true;
  };
  
  // Función para cerrar el modal
  const closeModal = () => {
    showModal.value = false;
  };
  </script>
  
  
  
  <style scoped>
  .text{
    color:beige;
    text-align: center;
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 20px;
  }
  
  .card {
      background-color: #fff;
      border-radius: 15px;
      border: none;
  }
  
  .card-title {
      color: #6c757d;
  }
  
  .form-control {
      border-radius: 10px;
  }
  
  .btn-primary {
      background-color: #87CEEB;
      border-color: #87CEEB;
      color: #fff;
  }
  
  .btn-primary:hover {
      background-color: #4682B4;
      border-color: #4682B4;
  }
  
  .form-label {
      color: #6c757d;
  }
  
  .btn:hover {
      transform: scale(0.9);
  }
  
  .btn {
      transition: transform 0.1s ease;
  }
  .mtop{
      margin-top: 50px;
  }
  
  .container {
      max-width: 400px;
      margin: 20px auto;
  }
  
  .btn-c{
      background-color: #9C00D4FF;
  }
  
  .btn-f{
      background-color: #b269cd;
  }
  
  .container{
      background-color: rgb(255, 255, 255);
      border-radius: 5%;
  }
  </style>
  